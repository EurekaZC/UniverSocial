/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android;

import astronomiacc.AstronomiaCC;
import cadastronomia.CadAstronomia;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojosastronomia.Evento;
import pojosastronomia.Excepciones;

/**
 *
 * @author jinet
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
        AstronomiaCC c;
        try {
            c = new AstronomiaCC();
            System.out.println(c.leerEventos());
//            int cantidad = c.insertarEvento(e);
//            System.out.println(cantidad);
        } catch (Excepciones ex) {
            System.out.println(ex);
        }

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

    }
    
}
