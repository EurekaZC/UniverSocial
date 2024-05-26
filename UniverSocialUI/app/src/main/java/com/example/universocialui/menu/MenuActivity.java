package com.example.universocialui.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.universocialui.R;
import com.example.universocialui.login.SplashActivity;
import com.example.universocialui.events.AllEventsActivity;
import com.example.universocialui.profile.ProfileActivity;
import com.example.universocialui.help.HelpActivity;

public class MenuActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

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
}
