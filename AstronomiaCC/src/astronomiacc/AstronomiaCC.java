
package astronomiacc;

import cadastronomia.CadAstronomia;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojosastronomia.Evento;
import pojosastronomia.Excepciones;
import pojosastronomia.Operaciones;
import pojosastronomia.Peticion;
import pojosastronomia.Provincia;
import pojosastronomia.Respuesta;
import pojosastronomia.Usuario;

/**
 *
 * @author Cristina Zas Perez
 * @version 1.0
 */
public class AstronomiaCC {
    // Lo tienen que recibir todos los metodos, asi que lo ponemos como atributo de la clase
    Socket socketCliente;

    private void manejadorIOException(IOException ex) throws Excepciones {
        Excepciones e = new Excepciones();
        e.setMensajeUsuario("Fallo en las comunicaciones. Consulte con el administrador.");
        e.setMensajeErrorBd(ex.getMessage());

        throw e;
    }

    private void manejadorClassNotFoundException(ClassNotFoundException ex) throws Excepciones {
        Excepciones e = new Excepciones();
        e.setMensajeUsuario("Error general en el sistema. Consulte con el administrador.");
        e.setMensajeErrorBd(ex.getMessage());

        throw e;
    }
    
    public AstronomiaCC() throws Excepciones {
        try {
//            String equipoServidor = "172.16.222.69";
            String equipoServidor = "192.168.1.122";
            int puertoServidor = 30500;
            socketCliente = new Socket(equipoServidor, puertoServidor);
            socketCliente.setSoTimeout(10000); // para establecer un tiempo de espera máximo en la comunicacion

        } catch (IOException ex) {
            manejadorIOException(ex);
        }
    }
    //--------------------------------------------------------------------------------- \ TABLA PROVINCIA /----------------------------------------------------------------------------------------    
    
    public Provincia leerProvincia (Integer idProvincia) throws Excepciones {
         // Creamos y populamos la peticion
        Peticion p = new Peticion();
        p.setIdOperacion(Operaciones.LEER_PROVINCIA);
        p.setIdEntidad(idProvincia); // Le pasamos el id del evento que se desea visualizar

        // Ahora que ya tenemos la peticion populada, tenemos que enviarla al servidor
        Respuesta r = null;
        
        Provincia provincia = null; 
        
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            oos.writeObject(p);
            
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            r = (Respuesta) ois.readObject();
            
            ois.close();
            oos.close();
            
            socketCliente.close();
            
            if (r.getEntidad() != null){
                provincia = (Provincia) r.getEntidad();
                return provincia;
            } else if (r.getE() != null) {
                throw r.getE();
            }
            
            
        } catch (IOException ex) {
            manejadorIOException(ex);
        } catch (ClassNotFoundException ex) {
            manejadorClassNotFoundException(ex);
        }    
        return provincia;
    }

    public ArrayList<Provincia> leerProvincias() throws Excepciones {

        // Creamos y populamos la peticion
        Peticion p = new Peticion();
        p.setIdOperacion(Operaciones.LEER_PROVINCIAS);

        
        // Ahora que ya tenemos la peticion populada, tenemos que enviarla al servidor
        Respuesta r = null;
        
        ArrayList<Provincia> listaProvinicias = null; 
        
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            oos.writeObject(p);
            
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            r = (Respuesta) ois.readObject();
            
            ois.close();
            oos.close();
            
            socketCliente.close();
            if (r.getEntidad() != null){
                listaProvinicias = (ArrayList<Provincia>) r.getEntidad();
                return listaProvinicias;
            } else if (r.getE() != null) {
                throw r.getE();
            }    
            
        } catch (IOException ex) {
            manejadorIOException(ex);
        } catch (ClassNotFoundException ex) {
            manejadorClassNotFoundException(ex);
        }    
        return listaProvinicias;
    }

    //--------------------------------------------------------------------------------- \ TABLA USUARIO /----------------------------------------------------------------------------------------
    
    public int insertarUsuario(Usuario u) throws Excepciones{
        
        // Creamos y populamos la peticion
        Peticion p = new Peticion();
        p.setIdOperacion(Operaciones.INSERTAR_USUARIO);
        p.setEntidad(u); 
        // Ahora que ya tenemos la peticion populada, tenemos que enviarla al servidor
        Respuesta r = null;
        int cantidad = 0;
        
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            oos.writeObject(p);
            
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            r = (Respuesta) ois.readObject();
            
            // cerramos recursos ya que ya tenemos la respuesta 
            ois.close();
            oos.close();
            
            socketCliente.close();
            
            if (r.getCantidad() != null){
                cantidad = r.getCantidad();
            } else if (r.getE() != null) {
                throw r.getE();
            }

        } catch (IOException ex) {
            manejadorIOException(ex);
        } catch (ClassNotFoundException ex) {
            manejadorClassNotFoundException(ex);
        }    
        return cantidad;
    }
    
    public int modificarUsuario(Integer idUsuario, Usuario u) throws Excepciones{
        
        // Creamos y populamos la peticion
        Peticion p = new Peticion();
        p.setIdOperacion(Operaciones.ACTUALIZAR_USUARIO);
        p.setIdEntidad(idUsuario); 
        p.setEntidad(u); 
        // Ahora que ya tenemos la peticion populada, tenemos que enviarla al servidor
        Respuesta r = null;
        int cantidad = 0;
        
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            oos.writeObject(p);
            
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            r = (Respuesta) ois.readObject();
            
            // cerramos recursos ya que ya tenemos la respuesta 
            ois.close();
            oos.close();
            
            socketCliente.close();
            
            if (r.getCantidad() != null){
                cantidad = r.getCantidad();
            } else if (r.getE() != null) {
                throw r.getE();
            }

        } catch (IOException ex) {
            manejadorIOException(ex);
        } catch (ClassNotFoundException ex) {
            manejadorClassNotFoundException(ex);
        }    
        return cantidad;
    }
    
    //--------------------------------------------------------------------------------- \ TABLA EVENTO /----------------------------------------------------------------------------------------    
    
    public Evento leerEvento(Integer idEvento) throws Excepciones {
    Peticion p = new Peticion();
    p.setIdOperacion(Operaciones.LEER_EVENTO);
    p.setIdEntidad(idEvento); // Le pasamos el id del evento que se desea visualizar

    Respuesta r = null;
    Evento evento = null;

    try {
        ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
        oos.writeObject(p);

        ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
        r = (Respuesta) ois.readObject();

        ois.close();
        oos.close();

        socketCliente.close();

        if (r.getEntidad() != null) {
            evento = (Evento) r.getEntidad();
        } else if (r.getE() != null) {
            throw r.getE();
        }
    } catch (IOException ex) {
        manejadorIOException(ex);
    } catch (ClassNotFoundException ex) {
        manejadorClassNotFoundException(ex);
    }

    return evento;
}
    
    public ArrayList<Evento> leerEventos() throws Excepciones {

        // Creamos y populamos la peticion
        Peticion p = new Peticion();
        p.setIdOperacion(Operaciones.LEER_EVENTOS);

        
        // Ahora que ya tenemos la peticion populada, tenemos que enviarla al servidor
        Respuesta r = null;
        
        ArrayList<Evento> listaEventos = null; 
        
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            oos.writeObject(p);
            
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            r = (Respuesta) ois.readObject();
            
            ois.close();
            oos.close();
            
            socketCliente.close();
            if (r.getEntidad() != null){
                listaEventos = (ArrayList<Evento>) r.getEntidad();
                return listaEventos;
            } else if (r.getE() != null) {
                throw r.getE();
            }
            
            
        } catch (IOException ex) {
            manejadorIOException(ex);
        } catch (ClassNotFoundException ex) {
            manejadorClassNotFoundException(ex);
        }    
        return listaEventos;
    }
    
    public int insertarEvento(Evento e) throws Excepciones{
        
        // Creamos y populamos la peticion
        Peticion p = new Peticion();
        p.setIdOperacion(Operaciones.INSERTAR_EVENTO);
        p.setEntidad(e); // le pasamos el evento que hay que insertar
        // Ahora que ya tenemos la peticion populada, tenemos que enviarla al servidor
        Respuesta r = null;
        int cantidad = 0;
        
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            oos.writeObject(p);
            
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            r = (Respuesta) ois.readObject();
            
            // cerramos recursos ya que ya tenemos la respuesta 
            ois.close();
            oos.close();
            
            socketCliente.close();
            
            if (r.getCantidad() != null){
                cantidad = r.getCantidad();
            } else if (r.getE() != null) {
                throw r.getE();
            }

        } catch (IOException ex) {
            manejadorIOException(ex);
        } catch (ClassNotFoundException ex) {
            manejadorClassNotFoundException(ex);
        }    
        return cantidad;
    }


}
    

