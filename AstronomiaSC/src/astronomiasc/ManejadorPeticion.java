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
import pojosastronomia.Mensaje;
import pojosastronomia.Operaciones;
import pojosastronomia.Peticion;
import pojosastronomia.Provincia;
import pojosastronomia.Respuesta;
import pojosastronomia.Usuario;

/**
 *
 * @author Cristina Zas Perez
 * @since 1.0
 */
public class ManejadorPeticion extends Thread {
    
    Socket clienteConectado;

    public ManejadorPeticion(Socket clienteConectado) {
        this.clienteConectado = clienteConectado;
    }

    @Override
    public void run() {;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(clienteConectado.getInputStream());
            Peticion p = (Peticion) ois.readObject();
            System.out.println(p);
            
            switch (p.getIdOperacion()){
                
                case Operaciones.LEER_PROVINCIA:
                    leerProvincia(p);
                    break;
                    
                case Operaciones.LEER_PROVINCIAS:
                    leerProvincias(p);
                    break;
                
                case Operaciones.INSERTAR_USUARIO:
                    insertarUsuario(p);
                    break;
                
                case Operaciones.ACTUALIZAR_USUARIO:
                    modificarUsuario(p);
                    break;
                    
                case Operaciones.ELIMINAR_USUARIO:
                    eliminarUsuario(p);
                    break;
                    
                case Operaciones.LEER_USUARIO:
                    leerUsuario(p);
                    break;
                    
                case Operaciones.LEER_USUARIOS:
                    leerUsuarios(p);
                    break;
                    
                case Operaciones.BUCAR_USUARIO_POR_EMAIL:
                    buscarUsuarioPorEmail(p);
                    break;
                    
                case Operaciones.INSERTAR_EVENTO:
                    insertarEvento(p);
                    break;
                    
                case Operaciones.LEER_EVENTO:
                    leerEvento(p);
                    break;
                    
                case Operaciones.LEER_EVENTOS:
                    leerEventos(p);
                    break;
                    
                case Operaciones.EVENTOS_POR_PROVINCIA_USUARIO:
                    obtenerEventosPorProvinciaUsuario(p);
                    break;

                case Operaciones.INSERTAR_MENSAJE:
                    insertarMensaje(p);
                    break;
                    
                case Operaciones.LEER_MENSAJES:
                    leerMensajes(p);
                    break;
                
            }
            
            clienteConectado.close();

        } catch (IOException ex) {
           
           manejadorIOExceptionOIS(ex, ois);
    
       } catch (ClassNotFoundException ex) {
            manejadorClassNotFoundException(ex);
       }
    }

    // -------------------------------------------------------------------------- >>> P R O V I N C I A 
    
    private void leerProvincia(Peticion p){
        ObjectOutputStream oos = null;
        try {
            CadAstronomia cad = new CadAstronomia();
            Integer idProvincia = p.getIdEntidad();

            Respuesta r = new Respuesta();
            r.setIdOperacion(p.getIdOperacion());
            r.setEntidad(cad.leerProvincia(idProvincia));
            oos = new ObjectOutputStream(clienteConectado.getOutputStream());
            oos.writeObject(r);
            oos.close();
        } catch (IOException ex) {
            manejadorIOExceptionOOS(ex,oos);

        } catch (Excepciones ex) {
           manejadorExcepciones(ex);
        }
    }
    
    private void leerProvincias(Peticion p){
        
    ArrayList<Provincia> listaProvincias = new ArrayList();
    ObjectOutputStream oos = null;
        try {
            CadAstronomia cad = new CadAstronomia();

            Respuesta r = new Respuesta();
            r.setIdOperacion(p.getIdOperacion());
            r.setEntidad(cad.leerProvincias());
            oos = new ObjectOutputStream(clienteConectado.getOutputStream());
            oos.writeObject(r);
            oos.close();
        } catch (IOException ex) {
            manejadorIOExceptionOOS(ex,oos);

        } catch (Excepciones ex) {
           manejadorExcepciones(ex);
        }
    }
    // -------------------------------------------------------------------------- >>> U S U A R I O
    private void insertarUsuario(Peticion p){

        ObjectOutputStream oos = null;
        try {
            Usuario u = (Usuario) p.getEntidad();

            CadAstronomia cad = new CadAstronomia();
            cad.insertarUsuario(u);

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
    
    private void modificarUsuario(Peticion p){
       
        ObjectOutputStream oos = null;
        try {
            Usuario u = (Usuario) p.getEntidad();
            Integer idUsuario = p.getIdEntidad();

            CadAstronomia cad = new CadAstronomia();
            cad.modificarUsuario(idUsuario, u);

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
    
    private void eliminarUsuario(Peticion p){
       
        ObjectOutputStream oos = null;
        try {
            Integer idUsuario = p.getIdEntidad();

            CadAstronomia cad = new CadAstronomia();
            cad.eliminarUsuario(idUsuario);

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
    
    private void leerUsuario(Peticion p) {
        ObjectOutputStream oos = null;
        try {
            CadAstronomia cad = new CadAstronomia();
            Integer idUsuario = p.getIdEntidad(); // Obtenemos el Id del evento del que se solicita información

            Respuesta r = new Respuesta();
            r.setIdOperacion(p.getIdOperacion());
            r.setEntidad(cad.leerUsuario(idUsuario)); // Utilizamos el método leerEvento de la clase CadAstronomia para obtener el evento
            oos = new ObjectOutputStream(clienteConectado.getOutputStream());
            oos.writeObject(r);
            oos.close();
        } catch (IOException ex) {
            manejadorIOExceptionOOS(ex, oos);
        } catch (Excepciones ex) {
            manejadorExcepciones(ex);
        }
    }
    
    private void leerUsuarios(Peticion p){

    ObjectOutputStream oos = null;
        try {
            CadAstronomia cad = new CadAstronomia();

            Respuesta r = new Respuesta();
            r.setIdOperacion(p.getIdOperacion());
            r.setEntidad(cad.leerUsuarios());
            oos = new ObjectOutputStream(clienteConectado.getOutputStream());
            oos.writeObject(r);
            oos.close();
        } catch (IOException ex) {
            manejadorIOExceptionOOS(ex,oos);

        } catch (Excepciones ex) {
            manejadorExcepciones(ex);
        }
    }

    private void buscarUsuarioPorEmail(Peticion p) {
        ObjectOutputStream oos = null;
        try {
            String email = (String) p.getEntidad();

            CadAstronomia cad = new CadAstronomia();
            Usuario usuario = cad.buscarUsuarioPorEmail(email);

            System.out.println("usuario:"+usuario);
            Respuesta r = new Respuesta();
            r.setIdOperacion(p.getIdOperacion());
            r.setEntidad(usuario);

            oos = new ObjectOutputStream(clienteConectado.getOutputStream());
            oos.writeObject(r);
            oos.close();

        } catch (IOException ex) {
            manejadorIOExceptionOOS(ex, oos);
        } catch (Excepciones ex) {
            manejadorExcepciones(ex);
        }
    }

    
    // -------------------------------------------------------------------------- >>> E V E N T O
   
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
    
    private void obtenerEventosPorProvinciaUsuario(Peticion p) {
        ObjectOutputStream oos = null;
        try {
            CadAstronomia cad = new CadAstronomia();
            Integer idUsuario = p.getIdEntidad(); 

            Respuesta r = new Respuesta();
            r.setIdOperacion(p.getIdOperacion());
            r.setEntidad(cad.obtenerEventosPorProvinciaUsuario(idUsuario)); 
            oos = new ObjectOutputStream(clienteConectado.getOutputStream());
            oos.writeObject(r);
            oos.close();
        } catch (IOException ex) {
            manejadorIOExceptionOOS(ex, oos);
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
     
     // -------------------------------------------------------------------------- >>> M E N S A J E
   private void insertarMensaje(Peticion p){
        ObjectOutputStream oos = null;
        try {
            Mensaje mensaje = (Mensaje) p.getEntidad();

            CadAstronomia cad = new CadAstronomia();
            cad.insertarMensaje(mensaje);

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
   
    private void leerMensajes(Peticion p){
    ObjectOutputStream oos = null;
        try {
            CadAstronomia cad = new CadAstronomia();

            Respuesta r = new Respuesta();
            r.setIdOperacion(p.getIdOperacion());
            r.setEntidad(cad.leerMensajes());
            oos = new ObjectOutputStream(clienteConectado.getOutputStream());
            oos.writeObject(r);
            oos.close();
        } catch (IOException ex) {
            manejadorIOExceptionOOS(ex,oos);

        } catch (Excepciones ex) {
           manejadorExcepciones(ex);
        }
    }

// ----------------------------------------\ EXCEPCIONES /----------------------------------------------------------
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

}
