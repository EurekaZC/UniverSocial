package com.example.universocialui.profile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.universocialui.R;

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

        // Cargar datos del usuario
        loadUserProfile();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
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

                int selectedGeneroId = radioGroupGenero.getCheckedRadioButtonId();
                int selectedConocimientoId = radioGroupConocimiento.getCheckedRadioButtonId();

                String genero = "";
                if (selectedGeneroId == R.id.radioHombre) {
                    genero = "Hombre";
                } else if (selectedGeneroId == R.id.radioMujer) {
                    genero = "Mujer";
                } else if (selectedGeneroId == R.id.radioOtro) {
                    genero = "Otro";
                }

                String conocimiento = "";
                if (selectedConocimientoId == R.id.radioNovato) {
                    conocimiento = "Novato";
                } else if (selectedConocimientoId == R.id.radioAvanzado) {
                    conocimiento = "Avanzado";
                } else if (selectedConocimientoId == R.id.radioExperto) {
                    conocimiento = "Experto";
                }

                String comunidad = spinnerComunidad.getSelectedItem().toString();
                String provincia = spinnerProvincia.getSelectedItem().toString();

                // Validar que las contraseñas coincidan
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(EditProfileActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Aquí puedes agregar la lógica para actualizar los datos del usuario con los datos recopilados
                // Por ahora, mostraremos un mensaje de confirmación
                Toast.makeText(EditProfileActivity.this, "Datos guardados", Toast.LENGTH_SHORT).show();
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
//        // Este es un ejemplo de cómo cargar los detalles del usuario. Deberías reemplazar esto con la lógica real.
//        User user = new User("Nombre", "Apellido1", "Apellido2", "Movil", "Email", "Hombre", "Novato", "Comunidad", "Provincia", "Password");
//
//        editTextNombre.setHint(user.getName());
//        editTextApellido1.setHint(user.getApellido1());
//        editTextApellido2.setHint(user.getApellido2());
//        editTextMovil.setHint(user.getMovil());
//        editTextEmail.setHint(user.getEmail());
//
//        // Las contraseñas no deben ser visibles como hint por razones de seguridad
//        // editTextPassword.setHint(user.getPassword());
//        // editTextConfirmPassword.setHint(user.getPassword());
//
//        // Configurar los radio buttons de género
//        switch (user.getGenero()) {
//            case "Hombre":
//                radioGroupGenero.check(R.id.radioHombre);
//                break;
//            case "Mujer":
//                radioGroupGenero.check(R.id.radioMujer);
//                break;
//            case "Otro":
//                radioGroupGenero.check(R.id.radioOtro);
//                break;
//        }
//
//        // Configurar los radio buttons de conocimiento
//        switch (user.getConocimiento()) {
//            case "Novato":
//                radioGroupConocimiento.check(R.id.radioNovato);
//                break;
//            case "Avanzado":
//                radioGroupConocimiento.check(R.id.radioAvanzado);
//                break;
//            case "Experto":
//                radioGroupConocimiento.check(R.id.radioExperto);
//                break;
        }

        // Configurar los spinners de comunidad y provincia
        // Aquí deberías cargar las listas de comunidades y provincias y seleccionar la del usuario
        // Por ejemplo:
        // ArrayAdapter<CharSequence> comunidadAdapter = ArrayAdapter.createFromResource(this, R.array.comunidades_array, android.R.layout.simple_spinner_item);
        // comunidadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // spinnerComunidad.setAdapter(comunidadAdapter);
        // spinnerComunidad.setSelection(comunidadAdapter.getPosition(user.getComunidad()));
        //
        // ArrayAdapter<CharSequence> provinciaAdapter = ArrayAdapter.createFromResource(this, R.array.provincias_array, android.R.layout.simple_spinner_item);
        // provinciaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // spinnerProvincia.setAdapter(provinciaAdapter);
        // spinnerProvincia.setSelection(provinciaAdapter.getPosition(user.getProvincia()));

}
