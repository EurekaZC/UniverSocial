package com.example.universocialui.profile;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.universocialui.R;
import com.example.universocialui.login.Password;

import java.io.IOException;
import java.net.Socket;
import astronomiacc.AstronomiaCC;
import pojosastronomia.Excepciones;
import pojosastronomia.Usuario;
import pojosastronomia.Provincia;

public class EditProfileActivity extends AppCompatActivity {

    private EditText editTextNombre;
    private EditText editTextApellido1;
    private EditText editTextApellido2;
    private EditText editTextMovil;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private RadioGroup radioGroupGenero;
    private RadioGroup radioGroupConocimiento;
    private Spinner spinnerComunidad;
    private Spinner spinnerProvincia;
    private Button btnGuardar;
    private ImageView returnButton;

    private Usuario usuarioActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextApellido1 = findViewById(R.id.editTextApellido1);
        editTextApellido2 = findViewById(R.id.editTextApellido2);
        editTextMovil = findViewById(R.id.editTextMovil);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        radioGroupGenero = findViewById(R.id.radioGroupGenero);
        radioGroupConocimiento = findViewById(R.id.radioGroupConocimiento);
        spinnerComunidad = findViewById(R.id.spinnerComunidad);
        spinnerProvincia = findViewById(R.id.spinnerProvincia);
        btnGuardar = findViewById(R.id.btnGuardar);
        returnButton = findViewById(R.id.returnButton);

        // Configurar los Spinners
        setupSpinners();

        // Cargar datos del usuario
        loadUserProfile();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("EditProfileActivity", "Guardar botón clicado");
                Toast.makeText(EditProfileActivity.this, "Guardar botón clicado", Toast.LENGTH_SHORT).show();

                try {
                    // Recopilar datos de los campos de entrada
                    String nombre = editTextNombre.getText().toString().isEmpty() ? usuarioActual.getNombre() : editTextNombre.getText().toString();
                    String apellido1 = editTextApellido1.getText().toString().isEmpty() ? usuarioActual.getApe1() : editTextApellido1.getText().toString();
                    String apellido2 = editTextApellido2.getText().toString().isEmpty() ? usuarioActual.getApe2() : editTextApellido2.getText().toString();
                    String movil = editTextMovil.getText().toString().isEmpty() ? usuarioActual.getTelefono() : editTextMovil.getText().toString();
                    String email = editTextEmail.getText().toString().isEmpty() ? usuarioActual.getEmail() : editTextEmail.getText().toString();
                    String password = editTextPassword.getText().toString();
                    String confirmPassword = editTextConfirmPassword.getText().toString();

                    int selectedGeneroId = radioGroupGenero.getCheckedRadioButtonId();
                    String genero = "";
                    if (selectedGeneroId == R.id.radioHombre) {
                        genero = "H";
                    } else if (selectedGeneroId == R.id.radioMujer) {
                        genero = "M";
                    } else if (selectedGeneroId == R.id.radioOtro) {
                        genero = "O";
                    }

                    int selectedConocimientoId = radioGroupConocimiento.getCheckedRadioButtonId();
                    String conocimiento = "";
                    if (selectedConocimientoId == R.id.radioNovato) {
                        conocimiento = "N";
                    } else if (selectedConocimientoId == R.id.radioAvanzado) {
                        conocimiento = "A";
                    } else if (selectedConocimientoId == R.id.radioExperto) {
                        conocimiento = "E";
                    }

                    String comunidad = spinnerComunidad.getSelectedItem().toString();
                    String provincia = spinnerProvincia.getSelectedItem().toString();

                    // Validar que las contraseñas coincidan si se ingresó una nueva contraseña
                    if (!password.isEmpty() && !password.equals(confirmPassword)) {
                        Toast.makeText(EditProfileActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Si la contraseña no ha sido cambiada, usar la existente
                    String contrasenaFinal = password.isEmpty() ? usuarioActual.getContrasena() : Password.passCodificada(password);

                    // Crear un objeto Usuario actualizado
                    Provincia provinciaSeleccionada = new Provincia(null, provincia, comunidad);
                    Usuario usuarioActualizado = new Usuario(
                            usuarioActual.getIdUsuario(), // ID del usuario existente
                            nombre,
                            apellido1,
                            apellido2,
                            genero,
                            email,
                            movil,
                            conocimiento,
                            contrasenaFinal, // Mantener la contraseña existente si no se cambia
                            usuarioActual.getEstaBorrado(),
                            provinciaSeleccionada // Utilizar la provincia seleccionada
                    );

                    // Llamar al método para actualizar el usuario
                    Log.d("EditProfileActivity", "Datos del usuario a actualizar: " + usuarioActualizado.toString());
                    new UpdateUserProfileTask().execute(usuarioActualizado);
                } catch (Exception e) {
                    Log.e("EditProfileActivity", "Error al recopilar o actualizar datos del usuario", e);
                }
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadUserProfile() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", -1);

        if (userId != -1) {
            new LoadUserProfileTask().execute(userId);
        } else {
            Toast.makeText(this, "No se pudo cargar el perfil del usuario", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(EditProfileActivity.this, excepcion.getMensajeUsuario(), Toast.LENGTH_SHORT).show();
            } else if (usuario != null) {
                usuarioActual = usuario;

                // Mostrar datos actuales como texto gris en los campos de entrada
                editTextNombre.setHint(usuario.getNombre());
                editTextApellido1.setHint(usuario.getApe1());
                editTextApellido2.setHint(usuario.getApe2());
                editTextMovil.setHint(usuario.getTelefono());
                editTextEmail.setHint(usuario.getEmail());

                // Configurar los radio buttons de género
                switch (usuario.getGenero()) {
                    case "H":
                        radioGroupGenero.check(R.id.radioHombre);
                        break;
                    case "M":
                        radioGroupGenero.check(R.id.radioMujer);
                        break;
                    case "O":
                        radioGroupGenero.check(R.id.radioOtro);
                        break;
                }

                // Configurar los radio buttons de conocimiento
                switch (usuario.getNivelConocimiento()) {
                    case "N":
                        radioGroupConocimiento.check(R.id.radioNovato);
                        break;
                    case "A":
                        radioGroupConocimiento.check(R.id.radioAvanzado);
                        break;
                    case "E":
                        radioGroupConocimiento.check(R.id.radioExperto);
                        break;
                }

                // Seleccionar la comunidad y provincia del usuario
                selectSpinnerItemByValue(spinnerComunidad, usuario.getProvincia().getComunidad());
                selectSpinnerItemByValue(spinnerProvincia, usuario.getProvincia().getProvincia());
            } else {
                Toast.makeText(EditProfileActivity.this, "No se pudieron cargar los datos del usuario", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setupSpinners() {
        ArrayAdapter<CharSequence> comunidadAdapter = ArrayAdapter.createFromResource(this, R.array.comunities_array, android.R.layout.simple_spinner_item);
        comunidadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerComunidad.setAdapter(comunidadAdapter);

        ArrayAdapter<CharSequence> provinciaAdapter = ArrayAdapter.createFromResource(this, R.array.provinces_array, android.R.layout.simple_spinner_item);
        provinciaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProvincia.setAdapter(provinciaAdapter);
    }

    private void selectSpinnerItemByValue(Spinner spinner, String value) {
        ArrayAdapter adapter = (ArrayAdapter) spinner.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            if (adapter.getItem(position).equals(value)) {
                spinner.setSelection(position);
                return;
            }
        }
    }

    private class UpdateUserProfileTask extends AsyncTask<Usuario, Void, Boolean> {
        private Excepciones excepcion;

        @Override
        protected Boolean doInBackground(Usuario... params) {
            Usuario usuarioActualizado = params[0];
            boolean actualizado = false;

            try {
                // Crear el socket en doInBackground
                String equipoServidor = "192.168.1.122";
                int puertoServidor = 30500;
                Socket socketCliente = new Socket(equipoServidor, puertoServidor);

                // Pasar el socket a AstronomiaCC
                AstronomiaCC cc = new AstronomiaCC(socketCliente);
                int result = cc.modificarUsuario(usuarioActualizado.getIdUsuario(), usuarioActualizado);
                actualizado = result > 0; // Verifica que se haya actualizado al menos un registro
            } catch (Excepciones ex) {
                excepcion = ex;
            } catch (IOException e) {
                excepcion = new Excepciones();
                excepcion.setMensajeUsuario("Fallo en la comunicación con el servidor.");
            }

            return actualizado;
        }

        @Override
        protected void onPostExecute(Boolean actualizado) {
            if (excepcion != null) {
                Toast.makeText(EditProfileActivity.this, excepcion.getMensajeUsuario(), Toast.LENGTH_SHORT).show();
            } else if (actualizado) {
                Toast.makeText(EditProfileActivity.this, "Datos guardados", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(EditProfileActivity.this, "No se pudieron guardar los datos", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
