/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android;

import astronomiacc.AstronomiaCC;
import cadastronomia.CadAstronomia;
import java.awt.BorderLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojosastronomia.Evento;
import pojosastronomia.Excepciones;
import pojosastronomia.Mensaje;
import pojosastronomia.Provincia;
import pojosastronomia.Usuario;
import prueba.NewMain;

/**
 *
 * @author Cristina Zas Perez
 * @version 1.0
 */
public class simuladorAndroid {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
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
//         
//
//        } catch (ParseException ex) {
//            System.out.println("La fecha debe de tener el formato: yyyy-MM-dd");
//           // java.util.logging.Logger.getLogger(NewMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//
//        }        
//        
//        AstronomiaCC c;
//        try {
//            c = new AstronomiaCC();
//            System.out.println(c.leerEventos());
////            int cantidad = c.insertarEvento(e);
////            System.out.println(cantidad);
//        } catch (Excepciones ex) {
//            System.out.println(ex);
//        }

//        AstronomiaCC c;
//
//        try {
//
//            c = new AstronomiaCC();
//            System.out.println(c.leerEvento(2));
//
//        } catch (Excepciones ex) {
//
//            System.out.println(ex.getMensajeUsuario());
//            System.out.println("Log: " + ex.getCodigoErrorBd() + " - " + ex.getMensajeErrorBd() + " - " + ex.getSentenciaSQL());
//
//        }

// ------------------------------------------------------------------------------------------------------------------------------------------------------------------ >>> P R O V I N C I A

//-------------------------------------------\ LEER UNA /--------------------------------------
//        AstronomiaCC c;
//        try{
//            c = new AstronomiaCC();
//            System.out.println(c.leerProvincia(2));  
//        }catch (Excepciones ex) {
//            System.out.println(ex);
//        }

//-------------------------------------------\ LEER TODAS /--------------------------------------
//            AstronomiaCC c;
//        try {
//            c = new AstronomiaCC();
//            System.out.println(c.leerProvincias());
//        } catch (Excepciones ex) {
//            System.out.println(ex);
//        }

// ------------------------------------------------------------------------------------------------------------------------------------------------------------------ >>> E V E N T O

//-------------------------------------------\ INSERTAR /--------------------------------------
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
//         
//
//        } catch (ParseException ex) {
//            System.out.println("La fecha debe de tener el formato: yyyy-MM-dd");
//           // java.util.logging.Logger.getLogger(NewMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//
//        }        
//        
//        AstronomiaCC c;
//        try {
//            c = new AstronomiaCC();
//            int cantidad = c.insertarEvento(e);
//            System.out.println(cantidad);
//        } catch (Excepciones ex) {
//            System.out.println(ex);
//        }

//-------------------------------------------\ LEER UNO /--------------------------------------
//        AstronomiaCC c;
//        try {
//            c = new AstronomiaCC();
//            System.out.println(c.leerEvento(2));
//        } catch (Excepciones ex) {
//            System.out.println(ex);
//        }

//-------------------------------------------\ LEER TODOS /--------------------------------------
//        AstronomiaCC c;
//        try {
//            c = new AstronomiaCC();
//            System.out.println(c.leerEventos());
//        } catch (Excepciones ex) {
//            System.out.println(ex);
//        }

// ------------------------------------------------------------------------------------------------------------------------------------------------------------------ >>> U S U A R I O

//-------------------------------------------\ INSERTAR /--------------------------------------
//        Provincia provincia = new Provincia(2, "cantabria", "cantabria");
//        Usuario u = new Usuario();
//        
//        u.setNombre("Cristina");
//        u.setApe1("Zas");
//        u.setApe2("Perez");
//        u.setGenero("M");
//        u.setEmail("cristiniponciiiiii@gmail.com");
//        u.setTelefono("634503576");
//        u.setNivelConocimiento("N");
//        u.setContrasena("contrasena");
//        u.setProvincia(provincia);
//
//        AstronomiaCC c;
//        try {
//            c = new AstronomiaCC();
//            int cantidad = c.insertarUsuario(u);
//            System.out.println(cantidad);
//        } catch (Excepciones ex) {
//            System.out.println(ex.getMensajeUsuario());
//            System.out.println("Log: " + ex.getCodigoErrorBd() + " - " + ex.getMensajeErrorBd() + " - " + ex.getSentenciaSQL());
//        }

//-------------------------------------------\ MODIFICAR /--------------------------------------
// Provincia provincia = new Provincia(3, "cantabria", "cantabria");
//
//        Usuario u = new Usuario();
//        u.setNombre("IFDGAEWRShu");
//        u.setApe1("zas");
//        u.setApe2("Carmona");
//        u.setGenero("M");
//        u.setEmail("emaiFDGWSil@email.com");
//        u.setTelefono("695554615");
//        u.setNivelConocimiento("N");
//        u.setContrasena("contrasena");
//        u.setProvincia(provincia);
//
//        //para modificar el usuario con id=2
//        int ra = 0;
//        try {
//            AstronomiaCC c = new AstronomiaCC();
//            ra = c.modificarUsuario(2, u); //numero de registros afectados
//        } catch (Excepciones ex) {
//            System.out.println(ex.getMensajeUsuario());
//            System.out.println("Log: " + ex.getCodigoErrorBd() + " - " + ex.getMensajeErrorBd() + " - " + ex.getSentenciaSQL());
//        }
//        System.out.println("Registros afectados: " + ra);

//-------------------------------------------\ ELIMINAR /--------------------------------------
//  try {
//            AstronomiaCC c = new AstronomiaCC();
//            int ra = c.eliminarUsuario(1); //numero de registros afectados
//            System.out.println("Registros afectados: " + ra);
//            
//        } catch (Excepciones ex) {
//            
//            System.out.println(ex.getMensajeUsuario());
//            System.out.println("Log: " + ex.getCodigoErrorBd() + " - " + ex.getMensajeErrorBd() + " - " + ex.getSentenciaSQL());
//            
//        }
  
//-------------------------------------------\ LEER UNO /--------------------------------------
//        AstronomiaCC c;
//        try {
//            c = new AstronomiaCC();
//            System.out.println(c.leerUsuario(2));
//        } catch (Excepciones ex) {
//            System.out.println(ex);
//        }

//-------------------------------------------\ LEER TODOS /--------------------------------------
//        AstronomiaCC c;
//        try {
//            c = new AstronomiaCC();
//            System.out.println(c.leerUsuarios());
//        } catch (Excepciones ex) {
//            System.out.println(ex);
//        }
  
    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------ >>> M E N S A J E
    
//-------------------------------------------\ INSERTAR /--------------------------------------
//    AstronomiaCC c;
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
//            c = new AstronomiaCC();
//            c.insertarMensaje(m); 
//            
//        } catch (ParseException ex) {
//            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Excepciones e) {
//            System.out.println(e.getMensajeUsuario());
//            System.out.println("Log: " + e.getCodigoErrorBd() + " - " + e.getMensajeErrorBd() + " - " + e.getSentenciaSQL());
//    }

//-------------------------------------------\ LEER TODOS /--------------------------------------
//        AstronomiaCC c;
//        try {
//            c = new AstronomiaCC();
//            System.out.println(c.leerMensajes());
//        } catch (Excepciones ex) {
//            System.out.println(ex);
//        }
//    

     } 
}
