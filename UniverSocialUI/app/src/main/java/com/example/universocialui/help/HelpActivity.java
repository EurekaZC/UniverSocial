package com.example.universocialui.help;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.universocialui.R;
import com.example.universocialui.menu.MenuActivity;

import java.util.Arrays;
import java.util.List;

public class HelpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);

        // Datos de ejemplo para las preguntas frecuentes
        List<String> questions = Arrays.asList(
                "Pregunta 1",
                "Pregunta 2",
                "Pregunta 3",
                "Pregunta 4"
        );

        List<String> answers = Arrays.asList(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        );

        // Configurar RecyclerView
        RecyclerView faqRecyclerView = findViewById(R.id.faqRecyclerView);
        faqRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        FAQAdapter adapter = new FAQAdapter(questions, answers);
        faqRecyclerView.setAdapter(adapter);

        // Botón Volver al Menú
        Button backToMenuButton = findViewById(R.id.backToMenuButton);
        backToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Botón Preguntas Frecuentes (No funcionalidad definida)
        Button faqButton = findViewById(R.id.faqButton);
        faqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Añadir funcionalidad para Preguntas Frecuentes aquí
            }
        });

        // Botón Contacto
        Button contactButton = findViewById(R.id.contactButton);
        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpActivity.this, ContactActivity.class);
                startActivity(intent);
            }
        });
    }
}
