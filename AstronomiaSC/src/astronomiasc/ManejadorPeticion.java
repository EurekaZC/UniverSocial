/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astronomiasc;

import cadastronomia.CadAstronomia;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojosastronomia.Evento;
import pojosastronomia.Excepciones;
import pojosastronomia.Operaciones;
import pojosastronomia.Peticion;
import pojosastronomia.Respuesta;

/**
 *
 * @author usuario
 */
public class ManejadorPeticion extends Thread {
    
    Socket clienteConectado;

    public ManejadorPeticion(Socket clienteConectado) {
        this.clienteConectado = clienteConectado;
    }
    
    private void manejadorIOExceptionOIS(IOException ex, ObjectInputStream ois){
         System.out.println(ex);
//            if (ois != null) {
//                Excepciones e = new Excepciones();
//                e.setMensajeUsuario("Error en la comunicación. Consulte con el administrador.");
//                e.setMensajeErrorBd(ex.getMessage());
//                Respuesta r = new Respuesta();
//                r.setE(e);
//                try {
//                    ObjectOutputStream oos = new ObjectOutputStream(clienteConectado.getOutputStream());
//                    oos.writeObject(r);
//                    oos.close();
//                } catch (IOException ex1) {
//                    System.out.println(ex1);
//                }
//            }
    }
    
    private void manejadorIOExceptionOOS(IOException ex, ObjectOutputStream oos){
         System.out.println(ex);
//            if (oos != null) {
//                Excepciones e = new Excepciones();
//                e.setMensajeUsuario("Error en la comunicación. Consulte con el administrador.");
//                e.setMensajeErrorBd(ex.getMessage());
//                Respuesta r = new Respuesta();
//                r.setE(e);
//                try {
//                    ObjectOutputStream oos2 = new ObjectOutputStream(clienteConectado.getOutputStream());
//                    oos2.writeObject(r);
//                    oos2.close();
//                } catch (IOException ex1) {
//                    System.out.println(ex1);
//                }
//            }
    }
    
    private void manejadorClassNotFoundException(ClassNotFoundException ex){
        System.out.println(ex);
        
        Excepciones e = new Excepciones();
        e.setMensajeUsuario("Error en la comunicación. Consulte con el administrador.");
        e.setMensajeErrorBd(ex.getMessage());
        Respuesta r = new Respuesta();
        r.setE(e);
        
        ObjectOutputStream oos = null;
        try{
            oos = new ObjectOutputStream(clienteConectado.getOutputStream());
                    oos.writeObject(r);
                    oos.close();
        } catch (IOException ex1) {
           manejadorIOExceptionOOS(ex1, oos);
        }
    }
    
    private void manejadorExcepciones(Excepciones ex){
        System.out.println(ex);
    
        Respuesta r = new Respuesta();
        r.setE(ex);
        
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(clienteConectado.getOutputStream());
            oos.writeObject(r);
            oos.close();
            clienteConectado.close();
        } catch (IOException ex1) {
            manejadorIOExceptionOOS(ex1, oos);
        }
    }
    
    @Override
    public void run() {;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(clienteConectado.getInputStream());
            Peticion p = (Peticion) ois.readObject();
            System.out.println(p);
            
            switch (p.getIdOperacion()){
                
                case Operaciones.LEER_EVENTOS:
                    leerEventos(p);
                    break;
                
                case Operaciones.INSERTAR_EVENTO:
                    insertarEvento(p);
                    break;
            }
            
            clienteConectado.close();

        } catch (IOException ex) {
           
           manejadorIOExceptionOIS(ex, ois);
    
       } catch (ClassNotFoundException ex) {
            manejadorClassNotFoundException(ex);
       }
    }
   
    private void leerEvento(Peticion p) {
        ObjectOutputStream oos = null;
        try {
            CadAstronomia cad = new CadAstronomia();
            Integer idEvento = p.getIdEntidad(); // Obtenemos el Id del evento del que se solicita información

            Respuesta r = new Respuesta();
            r.setIdOperacion(p.getIdOperacion());
            r.setEntidad(cad.leerEvento(idEvento)); // Utilizamos el método leerEvento de la clase CadAstronomia para obtener el evento
            oos = new ObjectOutputStream(clienteConectado.getOutputStream());
            oos.writeObject(r);
            oos.close();
        } catch (IOException ex) {
            manejadorIOExceptionOOS(ex, oos);
        } catch (Excepciones ex) {
            manejadorExcepciones(ex);
        }
    }

    private void leerEventos(Peticion p){
        
        ArrayList<Evento> listaEventos = new ArrayList();
        
       
            //        // Vamos a harcodear datos para ver que funciona
//        Evento e = new Evento();
//        e.setIdEvento(1);
//        e.setNombre("Prueba");
//        e.setTipo("prueba");
//        e.setDescripcion("esto es una prueba");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        Date fechaInicio;
//        Date fechaFinal;
//        try {
//            fechaInicio = sdf.parse("2023-01-01");
//            fechaFinal = sdf.parse("2023-01-05");
//            e.setInicio(fechaInicio);
//            e.setFinalEvento(fechaFinal);
//        } catch (ParseException ex) {
//            Logger.getLogger(ManejadorPeticion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        Evento ev = new Evento();
//        ev.setIdEvento(1);
//        ev.setNombre("kk");
//        ev.setTipo("kk");
//        ev.setDescripcion("kkkkkkk");
//
//        try {
//            fechaInicio = sdf.parse("2020-01-02");
//            fechaFinal = sdf.parse("2020-01-03");
//            e.setInicio(fechaInicio);
//            e.setFinalEvento(fechaFinal);
//        } catch (ParseException ex) {
//            Logger.getLogger(ManejadorPeticion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        listaEventos.add(e);
//        listaEventos.add(ev);
    ObjectOutputStream oos = null;
        try {
            CadAstronomia cad = new CadAstronomia();

            Respuesta r = new Respuesta();
            r.setIdOperacion(p.getIdOperacion());
            r.setEntidad(cad.leerEventos());
            oos = new ObjectOutputStream(clienteConectado.getOutputStream());
            oos.writeObject(r);
            oos.close();
        } catch (IOException ex) {
            manejadorIOExceptionOOS(ex,oos);

        } catch (Excepciones ex) {
           manejadorExcepciones(ex);
        }
    }
    
    
     private void insertarEvento(Peticion p){
        
       
        ObjectOutputStream oos = null;
        try {
            Evento e = (Evento) p.getEntidad();

            CadAstronomia cad = new CadAstronomia();
            cad.insertarEvento(e);

            Respuesta r = new Respuesta();
            r.setIdOperacion(p.getIdOperacion());
            r.setCantidad(1);

            oos = new ObjectOutputStream(clienteConectado.getOutputStream());
            oos.writeObject(r);
            oos.close();

        } catch (IOException ex) {
            manejadorIOExceptionOOS(ex, oos);

        } catch (Excepciones ex) {
            manejadorExcepciones(ex);
        }
    }

    /**
     * Método que implementa el diálogo con el cliente
     * @param clienteConectado Socket que se usa para realizar la comunicación con el cliente
    */
//    public static void gestionarDialogo(Socket clienteConectado) {
//        try {
//            ObjectInputStream ois = new ObjectInputStream(clienteConectado.getInputStream());
//            String nombre = (String) ois.readObject();
//            System.out.println("Nombre recibido del Cliente: " + nombre);
//            
//            System.out.println("El servidor responde al cliente" + nombre);
//            ObjectOutputStream oos = new ObjectOutputStream(clienteConectado.getOutputStream());
//            oos.writeObject("Hola Don " + nombre);
//            Thread.sleep(5000);
//            ObjectOutputStream oos2 = new ObjectOutputStream(clienteConectado.getOutputStream());
//            oos2.writeObject("Adios Don " + nombre);
//            
//            ois.close();
//            oos.close();
//            oos2.close();
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        } catch (ClassNotFoundException ex) {
//            System.out.println(ex.getMessage());
//            Logger.getLogger(AstronomiaSC.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(AstronomiaSC.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
