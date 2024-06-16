package com.example.universocialui.chat;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.universocialui.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import astronomiacc.AstronomiaCC;
import pojosastronomia.Evento;
import pojosastronomia.Excepciones;
import pojosastronomia.Mensaje;
import pojosastronomia.Usuario;

public class ChatActivity extends AppCompatActivity {

    // Definición de vistas y variables necesarias para la actividad de chat
    private RecyclerView chatRecyclerView;
    private ChatAdapter chatAdapter;
    private List<Mensaje> messages;
    private List<Mensaje> allMessages;
    private EditText messageEditText;
    private Button sendButton;
    private Button backButton;
    private int userId;
    private int eventId;
    private Usuario currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        // Inicialización de vistas
        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        messageEditText = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);
        backButton = findViewById(R.id.backButton);

        // Obtener el ID del usuario desde SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        userId = sharedPreferences.getInt("userId", -1);

        // Obtener el ID del evento desde SharedPreferences
        SharedPreferences eventPrefs = getSharedPreferences("EventPrefs", MODE_PRIVATE);
        eventId = eventPrefs.getInt("eventId", -1);

        // Verificar si el ID del evento es válido
        if (eventId == -1) {
            Toast.makeText(this, "Error: Event ID is not valid", Toast.LENGTH_SHORT).show();
            finish(); // Terminar la actividad si no hay un evento válido
            return;
        }

        // Inicialización de listas de mensajes
        messages = new ArrayList<>();
        allMessages = new ArrayList<>();

        // Configuración del RecyclerView y su adaptador
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatAdapter = new ChatAdapter(this, messages, currentUser != null ? currentUser.getNombre() : "Unknown User");
        chatRecyclerView.setAdapter(chatAdapter);

        // Configuración del botón de enviar
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageEditText.getText().toString();
                if (!messageText.isEmpty() && currentUser != null) {
                    Mensaje mensaje = new Mensaje(null, messageText, new Date(), currentUser, new Evento(eventId));
                    new SendMessageTask().execute(mensaje);
                    messageEditText.setText("");
                }
            }
        });

        // Configuración del botón de volver
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Cargar usuario y mensajes
        new LoadUserTask().execute(userId);
        new LoadMessagesTask().execute();
    }

    // Método para filtrar mensajes por evento
    private void filterMessagesByEvent(int eventId) {
        messages.clear();
        for (Mensaje mensaje : allMessages) {
            if (mensaje.getEvento().getIdEvento() == eventId) {
                messages.add(mensaje);
            }
        }
        // Ordenar los mensajes por fecha y hora
        Collections.sort(messages, new Comparator<Mensaje>() {
            @Override
            public int compare(Mensaje m1, Mensaje m2) {
                return m1.getHoraMensaje().compareTo(m2.getHoraMensaje());
            }
        });

        // Actualizar el adaptador y mover la vista al último mensaje
        chatAdapter.notifyDataSetChanged();
        chatRecyclerView.scrollToPosition(messages.size() - 1);
    }

    // Tarea asíncrona para cargar el usuario
    private class LoadUserTask extends AsyncTask<Integer, Void, Usuario> {
        private Excepciones excepcion;

        @Override
        protected Usuario doInBackground(Integer... params) {
            int userId = params[0];
            try {
                AstronomiaCC cc = new AstronomiaCC();
                return cc.leerUsuario(userId);
            } catch (Excepciones ex) {
                excepcion = ex;
                return null;
            }
        }

        @Override
        protected void onPostExecute(Usuario usuario) {
            if (excepcion != null) {
                Toast.makeText(ChatActivity.this, excepcion.getMensajeUsuario(), Toast.LENGTH_SHORT).show();
            } else if (usuario != null) {
                currentUser = usuario;
                chatAdapter.setCurrentUserName(usuario.getNombre());
            } else {
                Toast.makeText(ChatActivity.this, "Error al cargar el usuario", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Tarea asíncrona para cargar los mensajes
    private class LoadMessagesTask extends AsyncTask<Void, Void, ArrayList<Mensaje>> {
        private Excepciones excepcion;

        @Override
        protected ArrayList<Mensaje> doInBackground(Void... params) {
            ArrayList<Mensaje> mensajes = null;

            try {
                AstronomiaCC cc = new AstronomiaCC();
                mensajes = cc.leerMensajes();
            } catch (Excepciones ex) {
                excepcion = ex;
            }

            return mensajes;
        }

        @Override
        protected void onPostExecute(ArrayList<Mensaje> mensajes) {
            if (excepcion != null) {
                Toast.makeText(ChatActivity.this, excepcion.getMensajeUsuario(), Toast.LENGTH_SHORT).show();
            } else {
                allMessages.clear();
                allMessages.addAll(mensajes);
                filterMessagesByEvent(eventId);
            }
        }
    }

    // Tarea asíncrona para enviar un mensaje
    private class SendMessageTask extends AsyncTask<Mensaje, Void, Mensaje> {
        private Excepciones excepcion;

        @Override
        protected Mensaje doInBackground(Mensaje... params) {
            Mensaje mensaje = params[0];

            try {
                AstronomiaCC cc = new AstronomiaCC();
                cc.insertarMensaje(mensaje);
                return mensaje;
            } catch (Excepciones ex) {
                excepcion = ex;
                return null;
            }
        }

        @Override
        protected void onPostExecute(Mensaje mensaje) {
            if (excepcion != null) {
                Toast.makeText(ChatActivity.this, excepcion.getMensajeUsuario(), Toast.LENGTH_SHORT).show();
            } else if (mensaje != null) {
                // Agregar el nuevo mensaje a la lista y actualizar la vista
                allMessages.add(mensaje);
                filterMessagesByEvent(eventId);
            } else {
                Toast.makeText(ChatActivity.this, "Error al enviar el mensaje", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
