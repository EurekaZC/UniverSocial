package com.example.universocialui.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.universocialui.R;

import java.util.List;

import pojosastronomia.Mensaje;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private final List<Mensaje> messages;
    private final Context context;
    private final String currentUser;

    public ChatAdapter(Context context, List<Mensaje> messages, String currentUser) {
        this.context = context;
        this.messages = messages;
        this.currentUser = currentUser;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_message, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        Mensaje message = messages.get(position);

//        holder.messageTextView.setText(message.getText());
//        holder.usernameTextView.setText(message.getUsername());
//
//        if (message.getUsername().equals(currentUser)) {
//            holder.usernameTextView.setBackgroundResource(R.drawable.user_message_background);
//            holder.messageTextView.setBackgroundResource(R.drawable.user_message_background);
//        } else {
//            holder.usernameTextView.setBackgroundResource(R.drawable.other_message_background);
//            holder.messageTextView.setBackgroundResource(R.drawable.other_message_background);
//        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTextView;
        TextView messageTextView;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.usernameTextView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
        }
    }
}
