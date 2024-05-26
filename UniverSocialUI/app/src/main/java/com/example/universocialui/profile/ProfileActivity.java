package com.example.universocialui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.universocialui.R;
import com.example.universocialui.events.AllEventsActivity;
import com.example.universocialui.login.SplashActivity;
import com.example.universocialui.menu.MenuActivity;

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

        // Cargar datos del usuario
        loadUserProfile();

        // Botón de cerrar sesión
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    private void loadUserProfile() {
//        // Este es un ejemplo de cómo cargar los detalles del usuario. Deberías reemplazar esto con la lógica real.
//        User user = new User("Nombre Apellidos", "Provincia", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", "Novato");
//
//        nameTextView.setText(user.getName());
//        provinceTextView.setText(user.getProvince());
//        descriptionTextView.setText(user.getDescription());
//
//        // Configurar los círculos de nivel de conocimiento
//        setKnowledgeLevel(user.getKnowledgeLevel());
    }

    private void setKnowledgeLevel(String knowledgeLevel) {
        novatoCircle.setBackgroundResource(R.drawable.circle_background_empty);
        avanzadoCircle.setBackgroundResource(R.drawable.circle_background_empty);
        expertoCircle.setBackgroundResource(R.drawable.circle_background_empty);

        switch (knowledgeLevel) {
            case "Novato":
                novatoCircle.setBackgroundResource(R.drawable.circle_background_filled);
                break;
            case "Avanzado":
                novatoCircle.setBackgroundResource(R.drawable.circle_background_filled);
                avanzadoCircle.setBackgroundResource(R.drawable.circle_background_filled);
                break;
            case "Experto":
                novatoCircle.setBackgroundResource(R.drawable.circle_background_filled);
                avanzadoCircle.setBackgroundResource(R.drawable.circle_background_filled);
                expertoCircle.setBackgroundResource(R.drawable.circle_background_filled);
                break;
        }
    }
}
