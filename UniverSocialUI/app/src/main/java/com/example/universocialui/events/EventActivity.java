package com.example.universocialui.events;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.universocialui.R;
import com.example.universocialui.chat.ChatActivity;

public class EventActivity extends AppCompatActivity {

    private ImageView eventImageView;
    private TextView titleTextView;
    private TextView communityTextView;
    private TextView eventTypeTextView;
    private TextView eventDescriptionTextView;
    private Button chatButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event);

        eventImageView = findViewById(R.id.eventImageView);
        titleTextView = findViewById(R.id.titleTextView);
        communityTextView = findViewById(R.id.communityTextView);
        eventTypeTextView = findViewById(R.id.eventTypeTextView);
        eventDescriptionTextView = findViewById(R.id.eventDescriptionTextView);
        chatButton = findViewById(R.id.chatButton);
        backButton = findViewById(R.id.backButton);

        // Obtener el ID del evento desde el intent
        int eventId = getIntent().getIntExtra("event_id", -1);

        // Cargar los detalles del evento usando el eventId
        loadEventDetails(eventId);

        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Guardar el eventId en SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("EventPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("eventId", eventId);
                editor.apply();

                // Iniciar la actividad del chat
                Intent intent = new Intent(EventActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventActivity.this, AllEventsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadEventDetails(int eventId) {
        // Este es un ejemplo de cómo cargar los detalles del evento. Deberías reemplazar esto con la lógica real.
        // Evento event = new Evento(eventId, "Título del evento", "Tipo del evento");
        // event.setCommunity("Comunidad del evento");
        // event.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");

        // Configurar los valores en la interfaz
        // titleTextView.setText(event.getTitle());
        // communityTextView.setText(event.getCommunity());
        // eventTypeTextView.setText(event.getEventType());
        // eventDescriptionTextView.setText(event.getDescription());

        // Configura la imagen del evento si la tienes
        // eventImageView.setImageResource(event.getImageResId());
    }
}
