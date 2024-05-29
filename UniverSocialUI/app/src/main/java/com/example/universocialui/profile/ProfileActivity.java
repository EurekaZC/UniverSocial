package com.example.universocialui.profile;

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
import com.example.universocialui.login.SplashActivity;
import com.example.universocialui.menu.MenuActivity;
import java.io.IOException;
import java.net.Socket;
import astronomiacc.AstronomiaCC;
import pojosastronomia.Excepciones;
import pojosastronomia.Usuario;

public class ProfileActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView provinceTextView;
    private View novatoCircle;
    private View avanzadoCircle;
    private View expertoCircle;
    private TextView descriptionTextView;
    private Button logoutButton;
    private Button editProfileButton;
    private Button menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        nameTextView = findViewById(R.id.nameTextView);
        provinceTextView = findViewById(R.id.provinceTextView);
        novatoCircle = findViewById(R.id.novatoCircle);
        avanzadoCircle = findViewById(R.id.avanzadoCircle);
        expertoCircle = findViewById(R.id.expertoCircle);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        logoutButton = findViewById(R.id.logoutButton);
        editProfileButton = findViewById(R.id.editProfileButton);
        menuButton = findViewById(R.id.menuButton);

        // Recuperar el ID del usuario desde SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", -1);

        if (userId != -1) {
            // Cargar datos del usuario
            new LoadUserProfileTask().execute(userId);
        } else {
            Toast.makeText(this, "No se pudo cargar el perfil del usuario", Toast.LENGTH_SHORT).show();
        }

        // Botón de cerrar sesión
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Limpiar SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                // Redirigir a SplashActivity
                Intent intent = new Intent(ProfileActivity.this, SplashActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Botón de editar perfil
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });

        // Botón de menú
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private class LoadUserProfileTask extends AsyncTask<Integer, Void, Usuario> {
        private Excepciones excepcion;

        @Override
        protected Usuario doInBackground(Integer... params) {
            int userId = params[0];
            Usuario usuario = null;

            try {
                // Crear el socket en doInBackground
                String equipoServidor = "192.168.1.122";
                int puertoServidor = 30500;
                Socket socketCliente = new Socket(equipoServidor, puertoServidor);

                // Pasar el socket a AstronomiaCC
                AstronomiaCC cc = new AstronomiaCC(socketCliente);
                usuario = cc.leerUsuario(userId);
            } catch (Excepciones ex) {
                excepcion = ex;
            } catch (IOException e) {
                excepcion = new Excepciones();
                excepcion.setMensajeUsuario("Fallo en la comunicación con el servidor.");
            }

            return usuario;
        }

        @Override
        protected void onPostExecute(Usuario usuario) {
            if (excepcion != null) {
                Toast.makeText(ProfileActivity.this, excepcion.getMensajeUsuario(), Toast.LENGTH_SHORT).show();
            } else if (usuario != null) {
                // Actualizar la interfaz de usuario con los datos del perfil
                nameTextView.setText(usuario.getNombre() + " " + usuario.getApe1() + " " + usuario.getApe2());
                provinceTextView.setText(usuario.getProvincia().getProvincia());
                descriptionTextView.setText(usuario.getEmail()); // Suponiendo que la descripción es el email
                setKnowledgeLevel(usuario.getNivelConocimiento());
            } else {
                Toast.makeText(ProfileActivity.this, "No se pudieron cargar los datos del usuario", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setKnowledgeLevel(String knowledgeLevel) {
        novatoCircle.setBackgroundResource(R.drawable.circle_background_empty);
        avanzadoCircle.setBackgroundResource(R.drawable.circle_background_empty);
        expertoCircle.setBackgroundResource(R.drawable.circle_background_empty);

        switch (knowledgeLevel) {
            case "N":
                novatoCircle.setBackgroundResource(R.drawable.circle_background_filled);
                break;
            case "A":
                novatoCircle.setBackgroundResource(R.drawable.circle_background_filled);
                avanzadoCircle.setBackgroundResource(R.drawable.circle_background_filled);
                break;
            case "E":
                novatoCircle.setBackgroundResource(R.drawable.circle_background_filled);
                avanzadoCircle.setBackgroundResource(R.drawable.circle_background_filled);
                expertoCircle.setBackgroundResource(R.drawable.circle_background_filled);
                break;
        }
    }
}
