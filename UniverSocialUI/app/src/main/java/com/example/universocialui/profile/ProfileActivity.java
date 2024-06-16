package com.example.universocialui.profile;

import static com.example.universocialui.constants.constants.*;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.universocialui.R;
import com.example.universocialui.login.SplashActivity;
import com.example.universocialui.menu.MenuActivity;
import java.io.IOException;
import java.net.Socket;
import astronomiacc.AstronomiaCC;
import pojosastronomia.Excepciones;
import pojosastronomia.Usuario;

public class ProfileActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView provinceTextView;
    private TextView surnameTextView;
    private TextView emailTextView;
    private View novatoCircle;
    private View avanzadoCircle;
    private View expertoCircle;
    private TextView descriptionTextView;
    private Button editProfileButton;
    private Button menuButton;
    private Button optionsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        nameTextView = findViewById(R.id.nameTextView);
        provinceTextView = findViewById(R.id.provinceTextView);
        emailTextView = findViewById(R.id.emailTextView);
        novatoCircle = findViewById(R.id.novatoCircle);
        surnameTextView = findViewById(R.id.surnameTextView);
        avanzadoCircle = findViewById(R.id.avanzadoCircle);
        expertoCircle = findViewById(R.id.expertoCircle);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        editProfileButton = findViewById(R.id.editProfileButton);
        menuButton = findViewById(R.id.menuButton);
        optionsButton = findViewById(R.id.optionsButton);

        // Botón de editar perfil
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });

        // Botón de menú
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Botón de opciones
        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptionsMenu(v);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Recuperar el ID del usuario desde el Intent
        Intent intent = getIntent();
        int userId = intent.getIntExtra("userId", -1);

        if (userId == -1) {
            // Si no hay un ID de usuario en el Intent, usar el ID del usuario desde SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            userId = sharedPreferences.getInt("userId", -1);
        }

        if (userId != -1) {
            // Cargar datos del usuario
            new LoadUserProfileTask().execute(userId);
        } else {
            Toast.makeText(this, "No se pudo cargar el perfil del usuario", Toast.LENGTH_SHORT).show();
        }
    }

    private void showOptionsMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.profile_options, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.logout) {
                    logout();
                    return true;
                } else if (id == R.id.delete_account) {
                    confirmDeleteAccount();
                    return true;
                } else {
                    return false;
                }
            }
        });
        popupMenu.show();
    }

    private void logout() {
        // Limpiar SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        // Redirigir a SplashActivity
        Intent intent = new Intent(ProfileActivity.this, SplashActivity.class);
        startActivity(intent);
        finish();
    }

    private void confirmDeleteAccount() {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar cuenta")
                .setMessage("¿Está seguro de que desea eliminar su cuenta?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Eliminar la cuenta
                        new DeleteUserAccountTask().execute();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private class DeleteUserAccountTask extends AsyncTask<Void, Void, Boolean> {
        private Excepciones excepcion;

        @Override
        protected Boolean doInBackground(Void... voids) {
            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            int userId = sharedPreferences.getInt("userId", -1);

            if (userId == -1) {
                return false;
            }

            boolean eliminado = false;
            try {
                String equipoServidor = EQUIPO_SERVIDOR;
                int puertoServidor = PUERTO_SERVIDOR;
                Socket socketCliente = new Socket(equipoServidor, puertoServidor);

                AstronomiaCC cc = new AstronomiaCC(socketCliente);
                int result = cc.eliminarUsuario(userId);
                eliminado = result > 0; // Verifica que se haya eliminado al menos un registro
            } catch (Excepciones ex) {
                excepcion = ex;
            } catch (IOException e) {
                excepcion = new Excepciones();
                excepcion.setMensajeUsuario("Fallo en la comunicación con el servidor.");
            }

            return eliminado;
        }

        @Override
        protected void onPostExecute(Boolean eliminado) {
            if (excepcion != null) {
                Toast.makeText(ProfileActivity.this, excepcion.getMensajeUsuario(), Toast.LENGTH_SHORT).show();
            } else if (eliminado) {
                // Mostrar mensaje de despedida y cerrar sesión
                new AlertDialog.Builder(ProfileActivity.this)
                        .setTitle("Cuenta eliminada")
                        .setMessage("Le echaremos de menos en UniverSocial")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                logout();
                            }
                        })
                        .show();
            } else {
                Toast.makeText(ProfileActivity.this, "Error al eliminar la cuenta", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class LoadUserProfileTask extends AsyncTask<Integer, Void, Usuario> {
        private Excepciones excepcion;

        @Override
        protected Usuario doInBackground(Integer... params) {
            int userId = params[0];
            Usuario usuario = null;

            try {
                // Crear el socket en doInBackground
                String equipoServidor = "192.168.1.122";
                int puertoServidor = 30500;
                Socket socketCliente = new Socket(equipoServidor, puertoServidor);

                // Pasar el socket a AstronomiaCC
                AstronomiaCC cc = new AstronomiaCC(socketCliente);
                usuario = cc.leerUsuario(userId);
            } catch (Excepciones ex) {
                excepcion = ex;
            } catch (IOException e) {
                excepcion = new Excepciones();
                excepcion.setMensajeUsuario("Fallo en la comunicación con el servidor.");
            }

            return usuario;
        }

        @Override
        protected void onPostExecute(Usuario usuario) {
            if (excepcion != null) {
                Toast.makeText(ProfileActivity.this, excepcion.getMensajeUsuario(), Toast.LENGTH_SHORT).show();
            } else if (usuario != null) {
                // Actualizar la interfaz de usuario con los datos del perfil
                nameTextView.setText(usuario.getNombre());
                surnameTextView.setText(usuario.getApe1() + " " + usuario.getApe2());
                emailTextView.setText(usuario.getEmail());
                descriptionTextView.setText(usuario.getDescripcion());
                provinceTextView.setText(usuario.getProvincia().getProvincia());
                setKnowledgeLevel(usuario.getNivelConocimiento());
            } else {
                Toast.makeText(ProfileActivity.this, "No se pudieron cargar los datos del usuario", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setKnowledgeLevel(String knowledgeLevel) {
        novatoCircle.setBackgroundResource(R.drawable.circle_background_empty);
        avanzadoCircle.setBackgroundResource(R.drawable.circle_background_empty);
        expertoCircle.setBackgroundResource(R.drawable.circle_background_empty);

        switch (knowledgeLevel) {
            case "N":
                novatoCircle.setBackgroundResource(R.drawable.circle_background_filled);
                break;
            case "A":
                novatoCircle.setBackgroundResource(R.drawable.circle_background_filled);
                avanzadoCircle.setBackgroundResource(R.drawable.circle_background_filled);
                break;
            case "E":
                novatoCircle.setBackgroundResource(R.drawable.circle_background_filled);
                avanzadoCircle.setBackgroundResource(R.drawable.circle_background_filled);
                expertoCircle.setBackgroundResource(R.drawable.circle_background_filled);
                break;
        }
    }
}
