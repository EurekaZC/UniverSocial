package com.example.universocialui.events;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.universocialui.R;

import java.util.List;

import pojosastronomia.Evento;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private final List<Evento> events;
    private final Context context;

    public EventAdapter(Context context, List<Evento> events) {
        this.context = context;
        this.events = events;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Evento event = events.get(position);
        holder.titleTextView.setText(event.getNombre());
//        holder.communityTextView.setText(event.getC());
//        holder.eventTypeTextView.setText(event.getEventType());
        // Configura la imagen del evento si la tienes
        // holder.eventImageView.setImageResource(event.getImageResId());

        holder.infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, com.example.universocialui.events.EventActivity.class);
                intent.putExtra("event_id", event.getIdEvento());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {
        ImageView eventImageView;
        TextView titleTextView;
        TextView communityTextView;
        TextView eventTypeTextView;
        Button infoButton;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventImageView = itemView.findViewById(R.id.eventImageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            communityTextView = itemView.findViewById(R.id.communityTextView);
            eventTypeTextView = itemView.findViewById(R.id.eventTypeTextView);
            infoButton = itemView.findViewById(R.id.infoButton);
        }
    }
}
