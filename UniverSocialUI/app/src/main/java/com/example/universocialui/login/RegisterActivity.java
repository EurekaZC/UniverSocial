package com.example.universocialui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;;
import android.widget.RadioGroup;
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
        EditText editTextComunidad = findViewById(R.id.editTextComunidad);
        Button btnRegistrar = findViewById(R.id.btnRegistrar);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, SplashActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AstronomiaCC cliente = null;
                // Recopilar datos de los campos de entrada
                String nombre = editTextNombre.getText().toString();
                String apellido1 = editTextApellido1.getText().toString();
                String apellido2 = editTextApellido2.getText().toString();
                String movil = editTextMovil.getText().toString();
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();
                String comunidad = editTextComunidad.getText().toString();

                int selectedGeneroId = radioGroupGenero.getCheckedRadioButtonId();
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

                int selectedConocimientoId = radioGroupConocimiento.getCheckedRadioButtonId();
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

                // Validar que las contraseñas coinciden
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Crear objeto Provincia (aquí asumimos que comunidad es el nombre de la provincia)
                Provincia provincia = new Provincia(2, comunidad, comunidad);

                // Crear objeto Usuario y asignar los valores
                Usuario usuario = new Usuario();
                usuario.setNombre(nombre);
                usuario.setApe1(apellido1);
                usuario.setApe2(apellido2);
                usuario.setGenero(genero);
                usuario.setEmail(email);
                usuario.setTelefono(movil);
                usuario.setNivelConocimiento(conocimiento);
                usuario.setContrasena(password);
                usuario.setProvincia(provincia);

                // Guardar el usuario en la base de datos
                try {
                cliente.insertarUsuario(usuario);
                } catch (Excepciones e) {
                        throw new RuntimeException(e);
                }

                // Mostrar mensaje de confirmación
                Toast.makeText(RegisterActivity.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();

                // Redirigir al usuario a la pantalla de inicio
                Intent intent = new Intent(RegisterActivity.this, SplashActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
