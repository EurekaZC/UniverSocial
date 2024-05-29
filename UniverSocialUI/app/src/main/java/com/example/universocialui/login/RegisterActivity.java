package com.example.universocialui.login;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.universocialui.R;
import com.example.universocialui.menu.MenuActivity;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import astronomiacc.AstronomiaCC;
import pojosastronomia.Excepciones;
import pojosastronomia.Provincia;
import pojosastronomia.Usuario;

public class RegisterActivity extends AppCompatActivity {
    private Map<String, Integer> provinciasMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        // Cargar los arrays de recursos
        String[] provinciasArray = getResources().getStringArray(R.array.provinces_array);
        int[] provinciasIdsArray = getResources().getIntArray(R.array.provinces_ids);

        // Inicializar el mapa de provincias a IDs
        provinciasMap = new HashMap<>();
        for (int i = 0; i < provinciasArray.length; i++) {
            provinciasMap.put(provinciasArray[i], provinciasIdsArray[i]);
        }

        ImageView returnButton = findViewById(R.id.returnButton);
        EditText editTextNombre = findViewById(R.id.editTextNombre);
        EditText editTextApellido1 = findViewById(R.id.editTextApellido1);
        EditText editTextApellido2 = findViewById(R.id.editTextApellido2);
        EditText editTextMovil = findViewById(R.id.editTextMovil);
        RadioGroup radioGroupGenero = findViewById(R.id.radioGroupGenero);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        EditText editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        RadioGroup radioGroupConocimiento = findViewById(R.id.radioGroupConocimiento);
        Spinner spinnerComunidad = findViewById(R.id.spinnerComunidad);
        Spinner spinnerProvincia = findViewById(R.id.spinnerProvincia);
        Button btnRegistrar = findViewById(R.id.btnRegistrar);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, SplashActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Inicializar los Spinners
        ArrayAdapter<CharSequence> comunidadAdapter = ArrayAdapter.createFromResource(this,
                R.array.comunities_array, android.R.layout.simple_spinner_item);
        comunidadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerComunidad.setAdapter(comunidadAdapter);

        ArrayAdapter<CharSequence> provinciaAdapter = ArrayAdapter.createFromResource(this,
                R.array.provinces_array, android.R.layout.simple_spinner_item);
        provinciaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProvincia.setAdapter(provinciaAdapter);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Recopilar datos de los campos de entrada
                String nombre = editTextNombre.getText().toString();
                String apellido1 = editTextApellido1.getText().toString();
                String apellido2 = editTextApellido2.getText().toString();
                String movil = editTextMovil.getText().toString();
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();
                String comunidad = spinnerComunidad.getSelectedItem().toString();
                String provincia = spinnerProvincia.getSelectedItem().toString();

                int selectedGeneroId = radioGroupGenero.getCheckedRadioButtonId();
                int selectedConocimientoId = radioGroupConocimiento.getCheckedRadioButtonId();

                String genero = "";
                if (selectedGeneroId == R.id.radioHombre) {
                    genero = "H";
                } else if (selectedGeneroId == R.id.radioMujer) {
                    genero = "M";
                } else if (selectedGeneroId == R.id.radioOtro) {
                    genero = "O";
                } else {
                    genero = "N";
                }

                String conocimiento = "";
                if (selectedConocimientoId == R.id.radioNovato) {
                    conocimiento = "N";
                } else if (selectedConocimientoId == R.id.radioAvanzado) {
                    conocimiento = "A";
                } else if (selectedConocimientoId == R.id.radioExperto) {
                    conocimiento = "E";
                } else {
                    conocimiento = "N";
                }

                // Validar que las contraseñas coincidan
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Hashear la contraseña
                String hashedPassword = null;
                try {
                    hashedPassword = Password.passCodificada(password);
                    Log.d("RegisterActivity", "Hashed Password: " + hashedPassword);
                } catch (Excepciones e) {
                    Toast.makeText(RegisterActivity.this, e.getMensajeUsuario(), Toast.LENGTH_SHORT).show();
                    return;
                }

                // Obtener el ID de la provincia seleccionada
                Integer provinciaId = provinciasMap.get(provincia);
                if (provinciaId == null) {
                    Toast.makeText(RegisterActivity.this, "Provincia no válida", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Crear el objeto Usuario
                Provincia provinciaObj = new Provincia();
                provinciaObj.setIdProvincia(provinciaId);
                Usuario usuario = new Usuario();
                usuario.setNombre(nombre);
                usuario.setApe1(apellido1);
                usuario.setApe2(apellido2);
                usuario.setTelefono(movil);
                usuario.setEmail(email);
                usuario.setGenero(genero);
                usuario.setNivelConocimiento(conocimiento);
                usuario.setContrasena(hashedPassword);
                usuario.setProvincia(provinciaObj);

                // Llamar a la tarea de registro
                new RegistrarUsuarioTask().execute(usuario);
            }
        });
    }

    private class RegistrarUsuarioTask extends AsyncTask<Usuario, Void, Boolean> {
        private Excepciones excepcion;

        @Override
        protected Boolean doInBackground(Usuario... params) {
            Usuario usuario = params[0];
            boolean registrado = false;

            try {
                String equipoServidor = "192.168.1.122"; // Cambia esto según sea necesario
                int puertoServidor = 30500;
                Socket socketCliente = new Socket(equipoServidor, puertoServidor);

                AstronomiaCC cc = new AstronomiaCC(socketCliente);
                cc.insertarUsuario(usuario);
                registrado = true;
            } catch (Excepciones ex) {
                excepcion = ex;
            } catch (IOException e) {
                Log.e("RegisterActivity", "IOException: " + e.getMessage(), e);
            }

            return registrado;
        }

        @Override
        protected void onPostExecute(Boolean registrado) {
            if (excepcion != null) {
                Toast.makeText(RegisterActivity.this, excepcion.getMensajeUsuario(), Toast.LENGTH_SHORT).show();
            } else if (registrado) {
                Toast.makeText(RegisterActivity.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(RegisterActivity.this, "Error en el registro", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
