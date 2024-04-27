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
import pojosastronomia.Provincia;
import pojosastronomia.Usuario;

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
 Provincia provincia = new Provincia(3, "cantabria", "cantabria");

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

        //para modificar el usuario con id=2
        int ra = 0;
        try {
            AstronomiaCC c = new AstronomiaCC();
            ra = c.modificarUsuario(2, u); //numero de registros afectados
        } catch (Excepciones ex) {
            System.out.println(ex.getMensajeUsuario());
            System.out.println("Log: " + ex.getCodigoErrorBd() + " - " + ex.getMensajeErrorBd() + " - " + ex.getSentenciaSQL());
        }
        System.out.println("Registros afectados: " + ra);

//-------------------------------------------\ ELIMINAR /--------------------------------------
//-------------------------------------------\ LEER UNO /--------------------------------------
//-------------------------------------------\ LEER TODOS /--------------------------------------
    }
    
}
