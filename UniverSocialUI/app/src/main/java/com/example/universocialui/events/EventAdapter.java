package com.example.universocialui.events;

import static com.example.universocialui.constants.constants.*;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.universocialui.R;

import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.List;
import pojosastronomia.Evento;
import pojosastronomia.Excepciones;
import pojosastronomia.Provincia;
import astronomiacc.AstronomiaCC;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private final List<Evento> events;
    private final Context context;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

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
        holder.startDateTextView.setText(dateFormat.format(event.getInicio()));

        // Cargar la provincia de forma asíncrona
        new LoadProvinceTask(holder.provinceTextView).execute(event);

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

    private class LoadProvinceTask extends AsyncTask<Evento, Void, String> {
        private TextView provinceTextView;
        private Excepciones excepcion;

        public LoadProvinceTask(TextView provinceTextView) {
            this.provinceTextView = provinceTextView;
        }

        @Override
        protected String doInBackground(Evento... eventos) {
            Evento event = eventos[0];
            String provinciaNombre = null;
            try {
                AstronomiaCC cc = new AstronomiaCC(new Socket(EQUIPO_SERVIDOR, PUERTO_SERVIDOR));
                Provincia provincia = cc.leerProvincia(event.getIdEvento());
                if (provincia != null) {
                    provinciaNombre = provincia.getProvincia();
                }
            } catch (Excepciones | IOException ex) {
                excepcion = new Excepciones();
                excepcion.setMensajeUsuario("Fallo en la comunicación con el servidor.");
            }
            return provinciaNombre;
        }

        @Override
        protected void onPostExecute(String provinciaNombre) {
            if (provinciaNombre != null) {
                provinceTextView.setText(provinciaNombre);
            } else if (excepcion != null) {
                provinceTextView.setText("Error");
            }
        }
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView provinceTextView;
        TextView startDateTextView;
        Button infoButton;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            provinceTextView = itemView.findViewById(R.id.provinceTextView);
            startDateTextView = itemView.findViewById(R.id.startDateTextView);
            infoButton = itemView.findViewById(R.id.infoButton);
        }
    }
}
