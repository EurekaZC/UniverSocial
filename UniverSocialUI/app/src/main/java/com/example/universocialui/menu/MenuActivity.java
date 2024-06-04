package com.example.universocialui.menu;

import static com.example.universocialui.constants.constants.*;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.universocialui.R;
import com.example.universocialui.events.AllEventsActivity;
import com.example.universocialui.help.HelpActivity;
import com.example.universocialui.login.SplashActivity;
import com.example.universocialui.profile.ProfileActivity;
import java.io.IOException;
import java.net.Socket;
import pojosastronomia.Usuario;
import astronomiacc.AstronomiaCC;
import pojosastronomia.Excepciones;

public class MenuActivity extends AppCompatActivity {

    private TextView welcomeTextView;
    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        welcomeTextView = findViewById(R.id.welcomeTextView);

        // Obtener el ID del usuario desde SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        userId = sharedPreferences.getInt("userId", -1);

        // Verificar si el ID del usuario es válido, si es válido, cargar el nombre del usuario
        if (userId != -1) {
            new LoadUserNameTask().execute(userId);
        } else {
            Toast.makeText(this, "Error: User ID is not valid", Toast.LENGTH_SHORT).show();
        }

        // Botón Cerrar Sesión
        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción para cerrar sesión y regresar a la pantalla de inicio (SplashActivity)
                Intent intent = new Intent(MenuActivity.this, SplashActivity.class);
                startActivity(intent);
                finish(); // Finalizar la actividad actual
            }
        });

        // Botón Eventos Astronómicos
        Button eventsButton = findViewById(R.id.eventsButton);
        eventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción para ir a la pantalla de todos los eventos (AllEventsActivity)
                Intent intent = new Intent(MenuActivity.this, AllEventsActivity.class);
                startActivity(intent);
            }
        });

        // Botón Perfil
        Button profileButton = findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción para ir a la pantalla de perfil (ProfileActivity)
                Intent intent = new Intent(MenuActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        // Botón Ayuda
        Button helpButton = findViewById(R.id.helpButton);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción para ir a la pantalla de ayuda (HelpActivity)
                Intent intent = new Intent(MenuActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });
    }

    private class LoadUserNameTask extends AsyncTask<Integer, Void, String> {
        private Excepciones excepcion;

        @Override
        protected String doInBackground(Integer... params) {
            int userId = params[0];
            String userName = null;

            try {
                // Crear el socket en doInBackground
                String equipoServidor = EQUIPO_SERVIDOR;
                int puertoServidor = PUERTO_SERVIDOR;
                Socket socketCliente = new Socket(equipoServidor, puertoServidor);
                AstronomiaCC cc = new AstronomiaCC(socketCliente);
                Usuario usuario = cc.leerUsuario(userId);
                userName = usuario.getNombre();
            } catch (Excepciones ex) {
                excepcion = ex;
            } catch (IOException e) {
                excepcion = new Excepciones();
                excepcion.setMensajeUsuario("Fallo en la comunicación con el servidor.");
            }

            return userName;
        }

        @Override
        protected void onPostExecute(String userName) {
            if (excepcion != null) {
                Toast.makeText(MenuActivity.this, excepcion.getMensajeUsuario(), Toast.LENGTH_SHORT).show();
            } else if (userName != null) {
                String welcomeMessage = "Hola " + userName + ", ¿qué quieres hacer?";
                welcomeTextView.setText(welcomeMessage);
            } else {
                Toast.makeText(MenuActivity.this, "No se pudo cargar el nombre del usuario", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
