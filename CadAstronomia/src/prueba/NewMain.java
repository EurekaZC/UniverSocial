/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba;

import cadastronomia.CadAstronomia;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojosastronomia.Evento;
import pojosastronomia.Excepciones;
import pojosastronomia.Mensaje;
import pojosastronomia.Provincia;
import pojosastronomia.Usuario;


/**
 *
 * @author Cristina Zas Pérez
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
             
//      -------------------------------------------------------------------------------------------------------------------------------------------------------------------- >>> P R O V I N C I A

//      ------------------------------------- \ MOSTRAR PROVINCIA/ ------------------------------------------------------------------

//        try {
//            
//            CadAstronomia cad = new CadAstronomia();
//            Provincia p = cad.leerProvincia(2);
//            System.out.println(p);
//            
//        } catch (Excepciones ex) {
//            
//            java.util.logging.Logger.getLogger(NewMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//            
//        }

//      -------------------------------------- \ MOSTRAR  PROVINCIAS / ----------------------------------------------------
       
//        try {
//            
//            CadAstronomia cad = new CadAstronomia();
//            ArrayList lp = cad.leerProvincias();
//            System.out.println(lp);
//            
//        } catch (Excepciones ex) {
//            
//            java.util.logging.Logger.getLogger(NewMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//            
//        }

//      -------------------------------------------------------------------------------------------------------------------------------------------------------------------- >>> U S U A R I O

//      ------------------------------------- \ INSERTAR USUARIO/ ------------------------------------------------------------------
        
//        Provincia provincia = new Provincia(2, "cantabria", "cantabria");
//        Usuario u = new Usuario();
//        
//        u.setNombre("prueba");
//        u.setApe1("Zas");
//        u.setApe2("Perez");
//        u.setGenero("M");
//        u.setEmail("prueba@gmail.com");
//        u.setTelefono("643342417");
//        u.setNivelConocimiento("N");
//        u.setContrasena("contrasena");
//        u.setProvincia(provincia);
//
//        CadAstronomia cad;
//        try {
//            cad = new CadAstronomia();
//            cad.insertarUsuario(u);
//        } catch (Excepciones ex) {
//            System.out.println(ex.getMensajeUsuario());
//            System.out.println("Log: " + ex.getCodigoErrorBd() + " - " + ex.getMensajeErrorBd() + " - " + ex.getSentenciaSQL());
//        }

            
//      ------------------------------------- \ MODIFICAR USUARIO/ ------------------------------------------------------------------

//        Provincia provincia = new Provincia(3, "cantabria", "cantabria");
//
//        Usuario u = new Usuario();
//        u.setNombre("Ivanchu");
//        u.setApe1("zas");
//        u.setApe2("Carmona");
//        u.setGenero("M");
//        u.setEmail("emailemail@email.com");
//        u.setTelefono("697834615");
//        u.setNivelConocimiento("N");
//        u.setContrasena("contrasena");
//        u.setProvincia(provincia);
//
//        //para modificar el usuario con id=2
//        int ra = 0;
//        try {
//            CadAstronomia cad = new CadAstronomia();
//            ra = cad.modificarUsuario(2, u); //numero de registros afectados
//        } catch (Excepciones ex) {
//            System.out.println(ex.getMensajeUsuario());
//            System.out.println("Log: " + ex.getCodigoErrorBd() + " - " + ex.getMensajeErrorBd() + " - " + ex.getSentenciaSQL());
//        }
//        System.out.println("Registros afectados: " + ra);
//
//        
          
//      ------------------------------------- \ ELIMINAR USUARIO/ ------------------------------------------------------------------

//        Provincia provincia = new Provincia(3, "cantabria", "cantabria");
//        
//        Usuario u = new Usuario();
//        u.setNombre("Ivan");
//        u.setApe1("Zas");
//        u.setApe2("Carmona");
//        u.setGenero("M");
//        u.setEmail("email@email.com");
//        u.setTelefono("123412345");
//        u.setNivelConocimiento("N");
//        u.setContrasena("contrasena");
//        u.setProvincia(provincia);
        
//        try {
//            
//            CadAstronomia cad = new CadAstronomia();
//            int ra = cad.eliminarUsuario(5); //numero de registros afectados
//            // estaria bien aqui gestionar que si llo ra son cero, es porque no hay registros con ese id
//            System.out.println("Registros afectados: " + ra);
//            
//        } catch (Excepciones ex) {
//            
//            System.out.println(ex.getMensajeUsuario());
//            System.out.println("Log: " + ex.getCodigoErrorBd() + " - " + ex.getMensajeErrorBd() + " - " + ex.getSentenciaSQL());
//            
//        }

//      -------------------------------------- \ MOSTRAR UN USUARIO/ ----------------------------------------------------
//
//        try {
//            
//            CadAstronomia cad = new CadAstronomia();
//            Usuario u = cad.leerUsuario(2);
//            System.out.println(u);
//            
//        } catch (Excepciones ex) {
//            
//            java.util.logging.Logger.getLogger(NewMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//            
//        }

//      -------------------------------------- \ MOSTRAR  USUARIOS / ----------------------------------------------------
       
//        try {
//            
//            CadAstronomia cad = new CadAstronomia();
//            ArrayList lu = cad.leerUsuarios();
//            System.out.println(lu);
//            
//        } catch (Excepciones ex) {
//            
//            java.util.logging.Logger.getLogger(NewMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//            
//        }

//      -------------------------------------------------------------------------------------------------------------------------------------------------------------------- >>> E V E N T O

//       ------------------------------------- \ INSERTAR EVENTO / -------------------------------------------------------------------
//        try {
//            CadAstronomia consola = new CadAstronomia(); // Suponiendo que la clase principal se llama ConsolaAstronomia
//
//            // Crear un evento de prueba
//            Evento evento = new Evento();
//            evento.setNombre("Evento de Prueba");
//            evento.setTipo("Tipo de Prueba");
//            evento.setDescripcion("Descripción de Prueba");
//
//            // Manejo de fechas
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//            Date fechaInicio = sdf.parse("2023-01-01");
//            Date fechaFinal = sdf.parse("2023-01-05");
//
//            evento.setInicio(fechaInicio);
//            evento.setFinalEvento(fechaFinal);
//
//            int registrosAfectados = consola.insertarEvento(evento);
//            System.out.println("Registros afectados: " + registrosAfectados);
//
//        } catch (ParseException e) {
//            System.out.println("Error al parsear las fechas.");
//        } catch (Excepciones ex) {
//            java.util.logging.Logger.getLogger(NewMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
 
//      ------------------------------------- \ MODIFICAR EVENTO / ------------------------------------------------------------------

//        Evento e = new Evento();
//        e.setNombre("Orionidas");
//        e.setTipo("Lluvia de meteoros");
//        e.setDescripcion("Lluvia de estrellas en la region de orion");
//
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Date inicio;
//            inicio = sdf.parse("2023-11-15");
//            e.setInicio(inicio);
//            Date finalEvento = sdf.parse("2023-11-22");
//            e.setFinalEvento(finalEvento);
//            CadAstronomia cad = new CadAstronomia();
//            int registrosAfectados = cad.modificarEvento(2, e);
//            System.out.println("Registros afectados: " + registrosAfectados);
//
//        } catch (ParseException ex) {
//            System.out.println("La fecha debe de tener el formato: yyyy-MM-dd");
//           // java.util.logging.Logger.getLogger(NewMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//
//        } catch (Excepciones ex) {
//            System.out.println(ex.getMensajeUsuario());
//            System.out.println("Log: "+ex.getCodigoErrorBd() + " - "+ex.getMensajeErrorBd()+ " - "+ex.getSentenciaSQL());
//        }
        
//      ------------------------------------- \ ELIMINAR EVENTO / ------------------------------------------------------------------

//        Evento e = new Evento();
//
//        try {
//            CadAstronomia cad = new CadAstronomia();
//            int ra = cad.eliminarEvento(2); //numero de registros afectados
//            // estaria bien aqui gestionar que si llo ra son cero, es porque no hay registros con ese id
//            System.out.println("Registros afectados: " + ra);
//        } catch (Excepciones ex) {
//            System.out.println(ex.getMensajeUsuario());
//            System.out.println("Log: " + ex.getCodigoErrorBd() + " - " + ex.getMensajeErrorBd() + " - " + ex.getSentenciaSQL());
//        }

//      ------------------------------------- \ MOSTRAR UN EVENTO / ------------------------------------------------------------------

//        Evento e = new Evento();
//        CadAstronomia cad;
//        
//        try {
//            
//            cad = new CadAstronomia();
//            e = cad.leerEvento(2);
//            System.out.println(e);
//            
//        } catch (Excepciones ex) {
//            
//            System.out.println(ex.getMensajeUsuario());
//            System.out.println("Log: " + ex.getCodigoErrorBd() + " - " + ex.getMensajeErrorBd() + " - " + ex.getSentenciaSQL());
//            
//        }

//      ------------------------------------- \ MOSTRAR EVENTOS / ------------------------------------------------------------------

//        Evento e = new Evento();
//        CadAstronomia cad;
//        
//        try {
//            
//            cad = new CadAstronomia();
//            ArrayList le  = cad.leerEventos();
//            System.out.println(le);
//            
//        } catch (Excepciones ex) {
//            
//            System.out.println(ex.getMensajeUsuario());
//            System.out.println("Log: " + ex.getCodigoErrorBd() + " - " + ex.getMensajeErrorBd() + " - " + ex.getSentenciaSQL());
//            
//        }

//      ------------------------------------- \ MOSTRAR EVENTOS EN BASE A LA PROVINCIA DEL USUARIO / ------------------------------------------------------------------

//        Evento e = new Evento();
//        CadAstronomia cad;
//        
//        try {
//            
//            cad = new CadAstronomia();
//            ArrayList le  = cad.obtenerEventosPorProvinciaUsuario(0);
//            System.out.println(le);
//            
//        } catch (Excepciones ex) {
//            
//            System.out.println(ex.getMensajeUsuario());
//            System.out.println("Log: " + ex.getCodigoErrorBd() + " - " + ex.getMensajeErrorBd() + " - " + ex.getSentenciaSQL());
//            
//        }

//      -------------------------------------------------------------------------------------------------------------------------------------------------------------------- >>> M E N S A J E

//       ------------------------------------- \ INSERTAR MENSAJE/ -------------------------------------------------------------------

//        CadAstronomia cad;
//        
//        try {
//            Provincia p = new Provincia(2, "Barcelona", "Cataluña");
//            Usuario u = new Usuario(1, "Carmen", "Perez", "Gomez", "M", "carmentxu@gmail.com", "123456789", "E", "A1B2C3D4E5F6", p);
//            
//            // Manejo de fechas
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Date fechaInicio = sdf.parse("2023-08-12");
//            Date fechaFinal = sdf.parse("2023-08-13");
//
//            Evento e = new Evento(2, "Lluvia de Perseidas", "Lluvia de meteoros", "Una lluvia de meteoros de las Perseidas que será visible en el hemisferio norte.", fechaInicio, fechaFinal);
//            
//            // Creamos el mensaje de prueba
//            Mensaje m = new Mensaje();
//            m.setMensaje("Esto es otra pruebaaaaaaaaaa");
//            m.setHoraMensaje(new Date());
//            m.setUsuario(u);
//            m.setEvento(e);
//
//            cad = new CadAstronomia();
//            cad.insertarMensaje(m); 
//            
//        } catch (ParseException ex) {
//            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Excepciones e) {
//            System.out.println(e.getMensajeUsuario());
//            System.out.println("Log: " + e.getCodigoErrorBd() + " - " + e.getMensajeErrorBd() + " - " + e.getSentenciaSQL());
//    }

//       ------------------------------------- \ MOSTRAR MENSAJES/ -------------------------------------------------------------------

//        Mensaje e = new Mensaje();
//        CadAstronomia cad;
//        
//        try {
//            
//            cad = new CadAstronomia();
//            ArrayList lm  = cad.leerMensajes();
//            System.out.println(lm);
//            
//        } catch (Excepciones ex) {
//            
//            System.out.println(ex.getMensajeUsuario());
//            System.out.println("Log: " + ex.getCodigoErrorBd() + " - " + ex.getMensajeErrorBd() + " - " + ex.getSentenciaSQL());
//            
//        }

    }
    
}
