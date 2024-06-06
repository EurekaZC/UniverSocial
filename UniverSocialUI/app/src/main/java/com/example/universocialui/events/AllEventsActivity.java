package com.example.universocialui.events;

import static com.example.universocialui.constants.constants.*;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.universocialui.R;
import com.example.universocialui.menu.MenuActivity;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import astronomiacc.AstronomiaCC;
import pojosastronomia.Evento;
import pojosastronomia.Excepciones;

public class AllEventsActivity extends AppCompatActivity {

    private RecyclerView eventsRecyclerView;
    private EventAdapter adapter;
    private List<Evento> events = new ArrayList<>();
    private int userId;
    private String userProvince;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_events);

        eventsRecyclerView = findViewById(R.id.eventsRecyclerView);
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EventAdapter(this, events);
        eventsRecyclerView.setAdapter(adapter);

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        userId = sharedPreferences.getInt("userId", -1);
        userProvince = sharedPreferences.getString("userProvince", null);

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

        // Botón Mi Provincia
        Button myProvinceButton = findViewById(R.id.provinceButton);
        myProvinceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoadEventsTask().execute("province");
            }
        });

        // Botón España
        Button spainButton = findViewById(R.id.spainButton);
        spainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoadEventsTask().execute("spain");
            }
        });

        // Cargar eventos de España por defecto
        new LoadEventsTask().execute("spain");
    }

    private class LoadEventsTask extends AsyncTask<String, Void, List<Evento>> {
        private Excepciones excepcion;

        @Override
        protected List<Evento> doInBackground(String... params) {
            List<Evento> eventos = new ArrayList<>();
            String type = params[0];

            try {
                // Crear el socket en doInBackground
                String equipoServidor = EQUIPO_SERVIDOR;
                int puertoServidor = PUERTO_SERVIDOR;
                Socket socketCliente = new Socket(equipoServidor, puertoServidor);
                AstronomiaCC cc = new AstronomiaCC(socketCliente);

                if ("province".equals(type)) {
                    eventos = cc.obtenerEventosPorProvinciaUsuario(userId);
                } else if ("spain".equals(type)) {
                    eventos = cc.leerEventos();
                }

            } catch (Excepciones ex) {
                excepcion = ex;
            } catch (IOException e) {
                excepcion = new Excepciones();
                excepcion.setMensajeUsuario("Fallo en la comunicación con el servidor.");
            }

            return eventos;
        }

        @Override
        protected void onPostExecute(List<Evento> eventos) {
            if (excepcion != null) {
                Toast.makeText(AllEventsActivity.this, excepcion.getMensajeUsuario(), Toast.LENGTH_SHORT).show();
            } else {
                // Filtrar eventos para mostrar solo los eventos desde la fecha actual en adelante
                List<Evento> eventosFiltrados = filtrarEventosPorFecha(eventos);
                events.clear();
                events.addAll(eventosFiltrados);
                adapter.notifyDataSetChanged();
            }
        }

        private List<Evento> filtrarEventosPorFecha(List<Evento> eventos) {
            Date fechaActual = new Date();
            return eventos.stream()
                    .filter(evento -> evento.getInicio().after(fechaActual) || evento.getInicio().equals(fechaActual))
                    .collect(Collectors.toList());
        }
    }
}
