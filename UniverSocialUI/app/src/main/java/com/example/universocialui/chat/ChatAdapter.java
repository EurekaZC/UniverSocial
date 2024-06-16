package com.example.universocialui.chat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.universocialui.R;
import com.example.universocialui.profile.ProfileActivity;
import java.text.SimpleDateFormat;
import java.util.List;
import pojosastronomia.Mensaje;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MessageViewHolder> {

    private Context context;
    private List<Mensaje> messages;
    private String currentUser;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    // Constructor del adaptador
    public ChatAdapter(Context context, List<Mensaje> messages, String currentUser) {
        this.context = context;
        this.messages = messages;
        this.currentUser = currentUser;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflar el diseño de un item de mensaje de chat
        View view = LayoutInflater.from(context).inflate(R.layout.item_chat_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        // Obtener el mensaje en la posición actual
        Mensaje mensaje = messages.get(position);
        // Establecer el nombre del usuario que envió el mensaje
        holder.usernameTextView.setText(mensaje.getUsuario().getNombre());
        // Establecer el texto del mensaje
        holder.messageTextView.setText(mensaje.getMensaje());
        // Establecer la fecha y hora del mensaje
        holder.dateTextView.setText(dateFormat.format(mensaje.getHoraMensaje()));

        // Configurar un click listener para el nombre de usuario
        holder.usernameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Al hacer clic, iniciar la actividad de perfil del usuario
                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra("userId", mensaje.getUsuario().getIdUsuario());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        // Devolver el número total de mensajes
        return messages.size();
    }

    // Método para actualizar el nombre del usuario actual y notificar al adaptador
    public void setCurrentUserName(String currentUserName) {
        this.currentUser = currentUserName;
        notifyDataSetChanged();  // Notificar al adaptador que los datos han cambiado para que se actualice la vista
    }

    // Clase interna para el ViewHolder del RecyclerView
    static class MessageViewHolder extends RecyclerView.ViewHolder {

        // Vistas para mostrar el nombre del usuario, el mensaje y la fecha
        TextView usernameTextView;
        TextView messageTextView;
        TextView dateTextView;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            // Obtener referencias a las vistas del layout item_chat_message
            usernameTextView = itemView.findViewById(R.id.usernameTextView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
        }
    }
}

