package com.example.astronomiaui;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.astronomiaui.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import astronomiacc.AstronomiaCC;
import pojosastronomia.Evento;
import pojosastronomia.Excepciones;
import pojosastronomia.Provincia;
import pojosastronomia.Usuario;
import pojosastronomia.Mensaje;
public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread tarea = new Thread(() -> {
                    AstronomiaCC c = null;
                    String resultado = null;
                    try {
                        c = new AstronomiaCC();
                        // ----------------------------------------------------- >>> P R O V I N C I A
                        // leer 1
                        //resultado = String.valueOf(c.leerProvincia(2));

                        // leer todas
                        //resultado = String.valueOf(c.leerProvincias());

                        // ----------------------------------------------------- >>> E V E N T O
                        // insertar
                        /*Evento evento = new Evento();
                        evento.setNombre("Evento de Prueba");
                        evento.setTipo("Tipo de Prueba");
                        evento.setDescripcion("Descripción de Prueba");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date fechaInicio = sdf.parse("2023-01-01");
                        Date fechaFinal = sdf.parse("2023-01-05");
                        evento.setInicio(fechaInicio);
                        evento.setFinalEvento(fechaFinal);
                        resultado = String.valueOf(c.insertarEvento(evento));*/

                        // leer 1
                        /*resultado = String.valueOf(c.leerEvento(2));*/

                        // leer todos
                        /*resultado = c.leerEventos().toString();*/

                        // ----------------------------------------------------- >>> U S U A R I O
                        // insertar
                        /*Provincia provincia = new Provincia(2, "cantabria", "cantabria");
                        Usuario u = new Usuario();
                        u.setNombre("jejejeje");
                        u.setApe1("Zas");
                        u.setApe2("Perez");
                        u.setGenero("M");
                        u.setEmail("prrrrrroid@gmail.com");
                        u.setTelefono("634444576");
                        u.setNivelConocimiento("N");
                        u.setContrasena("contrasena");
                        u.setProvincia(provincia);

                        resultado = String.valueOf(c.insertarUsuario(u));*/

                        // modificar
                        /*Provincia provincia = new Provincia(3, "cantabria", "cantabria");
                        Usuario u = new Usuario();
                        u.setNombre("IFDGAEWRShu");
                        u.setApe1("zas");
                        u.setApe2("Carmona");
                        u.setGenero("M");
                        u.setEmail("emaiFDGWSil@email.com");
                        u.setTelefono("695554615");
                        u.setNivelConocimiento("N");
                        u.setContrasena("contrasena");
                        u.setProvincia(provincia);
                        resultado = String.valueOf(c.modificarUsuario(2, u));*/

                        //eliminar
                        //resultado = String.valueOf(c.eliminarUsuario(1)); //numero de registros afectados

                        // leer 1
                        //resultado = String.valueOf(c.leerUsuario(2));

                        // leer todos
                        //resultado = c.leerUsuarios().toString();

                        // ----------------------------------------------------- >>> M E N S A J E
                        // insertar
                        /*Provincia p = new Provincia(2, "Barcelona", "Cataluña");
                        Usuario u = new Usuario(1, "Carmen", "Perez", "Gomez", "M", "carmentxu@gmail.com", "123456789", "E", "A1B2C3D4E5F6", p);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date fechaInicio = sdf.parse("2023-08-12");
                        Date fechaFinal = sdf.parse("2023-08-13");
                        Evento e = new Evento(2, "Lluvia de Perseidas", "Lluvia de meteoros", "Una lluvia de meteoros de las Perseidas que será visible en el hemisferio norte.", fechaInicio, fechaFinal);
                        Mensaje m = new Mensaje();
                        m.setMensaje("Esto es otra pruebaaaaaaaaaa");
                        m.setHoraMensaje(new Date());
                        m.setUsuario(u);
                        m.setEvento(e);
                        resultado = String.valueOf(c.insertarMensaje(m));*/

                        // leer todos
                        //resultado = c.leerMensajes().toString();


                    } catch (Excepciones e) {
                        throw new RuntimeException(e);
                    } /*catch (ParseException e) {
                        throw new RuntimeException(e);
                    }*/

                    Log.e("Resultado", resultado);
                    Snackbar.make(view, resultado, Snackbar.LENGTH_LONG)
                            .setAnchorView(R.id.fab)
                            .setAction("Action", null).show();
                });
                tarea.start();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}