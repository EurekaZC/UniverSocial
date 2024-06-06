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

public class HelpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);

        // Cargar preguntas y respuestas desde XML
        String[] questions = getResources().getStringArray(R.array.questions);
        String[] answers = getResources().getStringArray(R.array.answers);

        // Configurar RecyclerView
        RecyclerView faqRecyclerView = findViewById(R.id.faqRecyclerView);
        faqRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        FAQAdapter adapter = new FAQAdapter(Arrays.asList(questions), Arrays.asList(answers));
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
