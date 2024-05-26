package com.example.universocialui.chat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.universocialui.R;

import java.util.ArrayList;
import java.util.List;

import pojosastronomia.Mensaje;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView chatRecyclerView;
    private ChatAdapter chatAdapter;
    private List<Mensaje> messages;
    private EditText messageEditText;
    private Button sendButton;
    private Button backButton;
    private String currentUser = "Iker Lombilla"; // Simulación del usuario actual

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        messageEditText = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);
        backButton = findViewById(R.id.backButton);

        // Datos de ejemplo para los mensajes
        messages = new ArrayList<>();
//        messages.add(new ChatMessage("Laura García", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
//        messages.add(new ChatMessage("Iker Lombilla", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));
//        messages.add(new ChatMessage("Iker Lombilla", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."));

        // Configurar RecyclerView
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatAdapter = new ChatAdapter(this, messages, currentUser);
        chatRecyclerView.setAdapter(chatAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageEditText.getText().toString();
                if (!messageText.isEmpty()) {
//                    Mensaje message = new Mensaje(currentUser, messageText);
//                    messages.add(message);
                    chatAdapter.notifyItemInserted(messages.size() - 1);
                    messageEditText.setText("");
                    chatRecyclerView.scrollToPosition(messages.size() - 1);
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}



