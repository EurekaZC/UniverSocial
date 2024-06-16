package com.example.universocialui.events;

import static com.example.universocialui.constants.constants.*;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.universocialui.R;
import com.example.universocialui.chat.ChatActivity;
import com.example.universocialui.menu.MenuActivity;

import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;

import astronomiacc.AstronomiaCC;
import pojosastronomia.Evento;
import pojosastronomia.Excepciones;


public class EventActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView eventTypeTextView;
    private TextView dateTextView;
    private TextView eventDescriptionTextView;
    private Button chatButton;
    private Button backButton;
    private Button menuButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event);

        titleTextView = findViewById(R.id.titleTextView);
        eventTypeTextView = findViewById(R.id.eventTypeTextView);
        dateTextView = findViewById(R.id.dateTextView);
        eventDescriptionTextView = findViewById(R.id.eventDescriptionTextView);
        chatButton = findViewById(R.id.chatButton);
        backButton = findViewById(R.id.backButton);
        menuButton = findViewById(R.id.menuButton);

        // Obtener el ID del evento desde el intent
        int eventId = getIntent().getIntExtra("event_id", -1);

        // Cargar los detalles del evento usando el eventId
        new LoadEventDetailsTask().execute(eventId);

        chatButton.setOnClickListener(v -> {
            // Guardar el eventId en SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("EventPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("eventId", eventId);
            editor.apply();

            // Iniciar la actividad del chat
            Intent intent = new Intent(EventActivity.this, ChatActivity.class);
            startActivity(intent);
        });

        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(EventActivity.this, AllEventsActivity.class);
            startActivity(intent);
        });

        menuButton.setOnClickListener(v -> {
            Intent intent = new Intent(EventActivity.this, MenuActivity.class);
            startActivity(intent);        });
    }

    private class LoadEventDetailsTask extends AsyncTask<Integer, Void, Evento> {
        private Excepciones excepcion;

        @Override
        protected Evento doInBackground(Integer... params) {
            int eventId = params[0];
            Evento evento = null;

            try {
                // Crear el socket en doInBackground
                String equipoServidor = EQUIPO_SERVIDOR;
                int puertoServidor = PUERTO_SERVIDOR;
                Socket socketCliente = new Socket(equipoServidor, puertoServidor);
                AstronomiaCC cc = new AstronomiaCC(socketCliente);

                evento = cc.leerEvento(eventId);

            } catch (Excepciones ex) {
                excepcion = ex;
            } catch (IOException e) {
                excepcion = new Excepciones();
                excepcion.setMensajeUsuario("Fallo en la comunicación con el servidor.");
            }

            return evento;
        }

        @Override
        protected void onPostExecute(Evento evento) {
            if (excepcion != null) {
                Toast.makeText(EventActivity.this, excepcion.getMensajeUsuario(), Toast.LENGTH_SHORT).show();
            } else if (evento != null) {
                titleTextView.setText(evento.getNombre());
                eventTypeTextView.setText(evento.getTipo());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                dateTextView.setText("Inicio: " + sdf.format(evento.getInicio()) + " - Fin: " + sdf.format(evento.getFinalEvento()));
                eventDescriptionTextView.setText(evento.getDescripcion());
            }
        }
    }
}
