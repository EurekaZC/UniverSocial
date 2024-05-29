package com.example.universocialui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.universocialui.R;
import com.example.universocialui.menu.MenuActivity;

import java.io.IOException;
import java.net.Socket;

import astronomiacc.AstronomiaCC;
import pojosastronomia.Excepciones;
import pojosastronomia.Usuario;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ImageView returnButton = findViewById(R.id.returnButton);
        emailEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SplashActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Por favor, ingrese su email y contraseña", Toast.LENGTH_SHORT).show();
            } else {
                String hashedPassword = null;
                try {
                    hashedPassword = Password.passCodificada(password);
                } catch (Excepciones e) {
                    throw new RuntimeException(e);
                }
                Log.d("else", "antes del task");
                new AutenticarUsuarioTask().execute(email, hashedPassword);
            }
        });
    }

    private class AutenticarUsuarioTask extends AsyncTask<String, Void, Boolean> {
        private Excepciones excepcion;
        private Usuario usuario;

        @Override
        protected Boolean doInBackground(String... params) {
            Log.d("LoginActivity", "AutenticarUsuarioTask - doInBackground");
            String email = params[0];
            String hashedPassword = params[1];
            boolean autenticado = false;

            try {
                // Crear el socket en doInBackground
                String equipoServidor = "192.168.1.122";
                int puertoServidor = 30500;
                Socket socketCliente = new Socket(equipoServidor, puertoServidor);

                // Pasar el socket a AstronomiaCC
                AstronomiaCC cc = new AstronomiaCC(socketCliente);
                usuario = cc.buscarUsuarioPorEmail(email);

                if (usuario != null) {
                    String storedPassword = usuario.getContrasena();
                    if (storedPassword != null) {
                        Log.d("LoginActivity", "Contraseña almacenada: " + storedPassword);
                        Log.d("LoginActivity", "Contraseña hasheada: " + hashedPassword);
                        if (storedPassword.equals(hashedPassword)) {
                            autenticado = true;
                        }
                    } else {
                        Log.e("LoginActivity", "Contraseña almacenada es null");
                    }
                } else {
                    Log.e("LoginActivity", "Usuario no encontrado");
                }
            } catch (Excepciones ex) {
                excepcion = ex;
                Log.e("LoginActivity", "Excepción: " + ex.getMessage());
            } catch (IOException e) {
                Log.e("LoginActivity", "IOException: " + e.getMessage(), e);
            }

            return autenticado;
        }

        @Override
        protected void onPostExecute(Boolean autenticado) {
            Log.d("LoginActivity", "AutenticarUsuarioTask - onPostExecute");
            if (excepcion != null) {
                Toast.makeText(LoginActivity.this, excepcion.getMensajeUsuario(), Toast.LENGTH_SHORT).show();
            } else if (autenticado) {
                Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                // Guardar el ID del usuario en SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("userId", usuario.getIdUsuario()); // Guardar el ID del usuario
                editor.apply();

                Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Email o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
