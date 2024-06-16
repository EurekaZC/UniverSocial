package com.example.universocialui.login;

import static com.example.universocialui.constants.constants.*;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import astronomiacc.AstronomiaCC;
import pojosastronomia.Excepciones;
import pojosastronomia.Provincia;
import pojosastronomia.Usuario;

public class RegisterActivity extends AppCompatActivity {
    private Map<String, Integer> provinciasMap;
    private Map<String, List<String>> comunidadesProvinciasMap;
    private Spinner spinnerProvincia; // Declaración como variable miembro
    private EditText editTextEmail; // Declaración como variable miembro
    private Spinner spinnerComunidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        // Inicializar los mapas
        provinciasMap = new HashMap<>();
        comunidadesProvinciasMap = new HashMap<>();

        // Cargar los arrays de recursos
        String[] provinciasArray = getResources().getStringArray(R.array.provinces_array);
        int[] provinciasIdsArray = getResources().getIntArray(R.array.provinces_ids);
        String[] comunidadesArray = getResources().getStringArray(R.array.comunities_array);

        // Inicializar el mapa de provincias a IDs
        for (int i = 0; i < provinciasArray.length; i++) {
            provinciasMap.put(provinciasArray[i], provinciasIdsArray[i]);
        }

        // Inicializar el mapa de comunidades a provincias
        comunidadesProvinciasMap.put("Andalucía", new ArrayList<>(Arrays.asList("Almería", "Cádiz", "Córdoba", "Granada", "Huelva", "Jaén", "Málaga", "Sevilla")));
        comunidadesProvinciasMap.put("Aragón", new ArrayList<>(Arrays.asList("Huesca", "Teruel", "Zaragoza")));
        comunidadesProvinciasMap.put("Asturias", new ArrayList<>(Arrays.asList("Asturias")));
        comunidadesProvinciasMap.put("Islas Baleares", new ArrayList<>(Arrays.asList("Islas Baleares")));
        comunidadesProvinciasMap.put("Canarias", new ArrayList<>(Arrays.asList("Las Palmas", "Santa Cruz de Tenerife")));
        comunidadesProvinciasMap.put("Cantabria", new ArrayList<>(Arrays.asList("Cantabria")));
        comunidadesProvinciasMap.put("Castilla-La Mancha", new ArrayList<>(Arrays.asList("Albacete", "Ciudad Real", "Cuenca", "Guadalajara", "Toledo")));
        comunidadesProvinciasMap.put("Castilla y León", new ArrayList<>(Arrays.asList("Ávila", "Burgos", "León", "Palencia", "Salamanca", "Segovia", "Soria", "Valladolid", "Zamora")));
        comunidadesProvinciasMap.put("Cataluña", new ArrayList<>(Arrays.asList("Barcelona", "Gerona", "Lérida", "Tarragona")));
        comunidadesProvinciasMap.put("Comunidad Valenciana", new ArrayList<>(Arrays.asList("Alicante", "Castellón", "Valencia")));
        comunidadesProvinciasMap.put("Extremadura", new ArrayList<>(Arrays.asList("Badajoz", "Cáceres")));
        comunidadesProvinciasMap.put("Galicia", new ArrayList<>(Arrays.asList("La Coruña", "Lugo", "Orense", "Pontevedra")));
        comunidadesProvinciasMap.put("Madrid", new ArrayList<>(Arrays.asList("Madrid")));
        comunidadesProvinciasMap.put("Murcia", new ArrayList<>(Arrays.asList("Murcia")));
        comunidadesProvinciasMap.put("Navarra", new ArrayList<>(Arrays.asList("Navarra")));
        comunidadesProvinciasMap.put("País Vasco", new ArrayList<>(Arrays.asList("Álava", "Guipúzcoa", "Vizcaya")));
        comunidadesProvinciasMap.put("La Rioja", new ArrayList<>(Arrays.asList("La Rioja")));
        comunidadesProvinciasMap.put("Ceuta", new ArrayList<>(Arrays.asList("Ceuta")));
        comunidadesProvinciasMap.put("Melilla", new ArrayList<>(Arrays.asList("Melilla")));

        ImageView returnButton = findViewById(R.id.returnButton);
        EditText editTextNombre = findViewById(R.id.editTextNombre);
        EditText editTextApellido1 = findViewById(R.id.editTextApellido1);
        EditText editTextApellido2 = findViewById(R.id.editTextApellido2);
        EditText editTextMovil = findViewById(R.id.editTextMovil);
        RadioGroup radioGroupGenero = findViewById(R.id.radioGroupGenero);
        editTextEmail = findViewById(R.id.editTextEmail); // Inicialización
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        EditText editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        RadioGroup radioGroupConocimiento = findViewById(R.id.radioGroupConocimiento);
        spinnerComunidad = findViewById(R.id.spinnerComunidad);
        spinnerProvincia = findViewById(R.id.spinnerProvincia); // Inicialización
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

        // Establecer el listener para el spinnerComunidad
        spinnerComunidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String comunidadSeleccionada = spinnerComunidad.getSelectedItem().toString();
                List<String> provincias = comunidadesProvinciasMap.get(comunidadSeleccionada);
                if (provincias != null) {
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(RegisterActivity.this, android.R.layout.simple_spinner_item, provincias);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerProvincia.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada
            }
        });

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

                // Validar el número de teléfono
                if (!movil.isEmpty() && movil.length() != 9) {
                    Toast.makeText(RegisterActivity.this, "El nº de móvil debe ser de 9 caracteres", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validar el correo electrónico
                if (!email.isEmpty() && !email.matches(".+@.+\\..+")) {
                    Toast.makeText(RegisterActivity.this, "Email erróneo", Toast.LENGTH_SHORT).show();
                    return;
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
        private String email;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            email = editTextEmail.getText().toString();
        }

        @Override
        protected Boolean doInBackground(Usuario... params) {
            Usuario usuario = params[0];
            boolean registrado = false;

            try {
                String equipoServidor = EQUIPO_SERVIDOR;
                int puertoServidor = PUERTO_SERVIDOR;
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
                // Registro exitoso, ahora obtener el usuario por email
                new ObtenerUsuarioPorEmailTask().execute(email);
            } else {
                Toast.makeText(RegisterActivity.this, "Error en el registro", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class ObtenerUsuarioPorEmailTask extends AsyncTask<String, Void, Usuario> {
        private Excepciones excepcion;

        @Override
        protected Usuario doInBackground(String... params) {
            String email = params[0];
            Usuario usuario = null;

            try {
                String equipoServidor = EQUIPO_SERVIDOR;
                int puertoServidor = PUERTO_SERVIDOR;
                Socket socketCliente = new Socket(equipoServidor, puertoServidor);

                AstronomiaCC cc = new AstronomiaCC(socketCliente);
                usuario = cc.buscarUsuarioPorEmail(email);
            } catch (Excepciones ex) {
                excepcion = ex;
            } catch (IOException e) {
                Log.e("RegisterActivity", "IOException: " + e.getMessage(), e);
            }

            return usuario;
        }

        @Override
        protected void onPostExecute(Usuario usuario) {
            if (excepcion != null) {
                Toast.makeText(RegisterActivity.this, excepcion.getMensajeUsuario(), Toast.LENGTH_SHORT).show();
            } else if (usuario != null) {
                // Guardar userId en SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("userId", usuario.getIdUsuario());
                editor.putString("userProvince", spinnerProvincia.getSelectedItem().toString());
                editor.apply();

                Toast.makeText(RegisterActivity.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(RegisterActivity.this, "Error al obtener el usuario", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
