package com.example.universocialui.events;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.universocialui.MainActivity;
import com.example.universocialui.R;
import com.example.universocialui.menu.MenuActivity;


import java.util.ArrayList;
import java.util.List;

import pojosastronomia.Evento;

public class AllEventsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_events);

        // Datos de ejemplo para los eventos
        List<Evento> events = new ArrayList<>();
//        events.add(new Evento(1, "Título evento 1", "Comunidad 1", "Tipo de evento 1"));
//        events.add(new Evento(2, "Título evento 2", "Comunidad 2", "Tipo de evento 2"));
//        events.add(new Evento(3, "Título evento 3", "Comunidad 3", "Tipo de evento 3"));
//        events.add(new Evento(4, "Título evento 4", "Comunidad 4", "Tipo de evento 4"));

        // Configurar RecyclerView
        RecyclerView eventsRecyclerView = findViewById(R.id.eventsRecyclerView);
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        EventAdapter adapter = new EventAdapter(this, events);
        eventsRecyclerView.setAdapter(adapter);

        // Botón Volver al Menú
        Button backToMenuButton = findViewById(R.id.backToMenuButton);
        backToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllEventsActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Botones Mi Provincia y Filtros (sin funcionalidad por ahora)
        Button myProvinceButton = findViewById(R.id.myProvinceButton);
        myProvinceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Añadir funcionalidad para Mi Provincia aquí
            }
        });

        Button filtersButton = findViewById(R.id.filtersButton);
        filtersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Añadir funcionalidad para Filtros aquí
            }
        });
    }
}

