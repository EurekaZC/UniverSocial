package com.example.universocialui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.universocialui.R;

import astronomiacc.AstronomiaCC;
import pojosastronomia.Excepciones;
import pojosastronomia.Provincia;
import pojosastronomia.Usuario;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

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
                R.array.comunidades_array, android.R.layout.simple_spinner_item);
        comunidadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerComunidad.setAdapter(comunidadAdapter);

        ArrayAdapter<CharSequence> provinciaAdapter = ArrayAdapter.createFromResource(this,
                R.array.provincias_array, android.R.layout.simple_spinner_item);
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
                    genero = "Hombre";
                } else if (selectedGeneroId == R.id.radioMujer) {
                    genero = "Mujer";
                } else if (selectedGeneroId == R.id.radioOtro) {
                    genero = "Otro";
                } else {
                    genero = "No especificado";
                }

                String conocimiento = "";
                if (selectedConocimientoId == R.id.radioNovato) {
                    conocimiento = "Novato";
                } else if (selectedConocimientoId == R.id.radioAvanzado) {
                    conocimiento = "Avanzado";
                } else if (selectedConocimientoId == R.id.radioExperto) {
                    conocimiento = "Experto";
                } else {
                    conocimiento = "No especificado";
                }

                // Validar que las contraseñas coincidan
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Aquí puedes agregar la lógica para registrar al usuario con los datos recopilados
                // Por ahora, mostraremos un mensaje de confirmación
                Toast.makeText(RegisterActivity.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
