
package astronomiacc;

import static com.example.universocialui.constants.constants.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
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
 * @version 1.0
 */
public class AstronomiaCC {
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

    public AstronomiaCC(Socket socketCliente) {
        this.socketCliente = socketCliente;
    }
    public AstronomiaCC() throws Excepciones {
        try {
            String equipoServidor = EQUIPO_SERVIDOR;
            int puertoServidor = PUERTO_SERVIDOR;
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

    public Provincia leerProvinciaPorEvento(Integer idEvento) throws Excepciones {
        // Creamos y populamos la peticion
        Peticion p = new Peticion();
        p.setIdOperacion(Operaciones.PROVINCIA_POR_EVENTO);
        p.setIdEntidad(idEvento);

        // Ahora que ya tenemos la peticion populada, tenemos que enviarla al servidor
        Respuesta r = null;
        Provincia provincia = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            oos = new ObjectOutputStream(socketCliente.getOutputStream());
            oos.writeObject(p);

            ois = new ObjectInputStream(socketCliente.getInputStream());
            r = (Respuesta) ois.readObject();

            if (r.getEntidad() != null) {
                provincia = (Provincia) r.getEntidad();
            } else if (r.getE() != null) {
                throw r.getE();
            }

        } catch (IOException ex) {
            manejadorIOException(ex);
        } catch (ClassNotFoundException ex) {
            manejadorClassNotFoundException(ex);
        } finally {
            try {
                if (ois != null) ois.close();
                if (oos != null) oos.close();
            } catch (IOException ex) {
                manejadorIOException(ex);
            }
        }
        return provincia;
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
    
    public int eliminarUsuario(Integer idUsuario) throws Excepciones{
        
        // Creamos y populamos la peticion
        Peticion p = new Peticion();
        p.setIdOperacion(Operaciones.ELIMINAR_USUARIO);
        p.setIdEntidad(idUsuario); 
        
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
    
    public Usuario leerUsuario(Integer idUsuario) throws Excepciones {
    Peticion p = new Peticion();
    p.setIdOperacion(Operaciones.LEER_USUARIO);
    p.setIdEntidad(idUsuario); // Le pasamos el id del evento que se desea visualizar

    Respuesta r = null;
    Usuario usuario = null;

    try {
        ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
        oos.writeObject(p);

        ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
        r = (Respuesta) ois.readObject();

        ois.close();
        oos.close();

        socketCliente.close();

        if (r.getEntidad() != null) {
            usuario = (Usuario) r.getEntidad();
        } else if (r.getE() != null) {
            throw r.getE();
        }
    } catch (IOException ex) {
        manejadorIOException(ex);
    } catch (ClassNotFoundException ex) {
        manejadorClassNotFoundException(ex);
    }

    return usuario;
}
    
    public ArrayList<Usuario> leerUsuarios() throws Excepciones {

        // Creamos y populamos la peticion
        Peticion p = new Peticion();
        p.setIdOperacion(Operaciones.LEER_USUARIOS);

        
        // Ahora que ya tenemos la peticion populada, tenemos que enviarla al servidor
        Respuesta r = null;
        
        ArrayList<Usuario> listaUsuarios = null; 
        
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            oos.writeObject(p);
            
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            r = (Respuesta) ois.readObject();
            
            ois.close();
            oos.close();
            
            socketCliente.close();
            if (r.getEntidad() != null){
                listaUsuarios = (ArrayList<Usuario>) r.getEntidad();
                return listaUsuarios;
            } else if (r.getE() != null) {
                throw r.getE();
            }
            
            
        } catch (IOException ex) {
            manejadorIOException(ex);
        } catch (ClassNotFoundException ex) {
            manejadorClassNotFoundException(ex);
        }
        return listaUsuarios;
    }

    public Usuario buscarUsuarioPorEmail(String email) throws Excepciones {
        Peticion p = new Peticion();
        p.setIdOperacion(Operaciones.BUCAR_USUARIO_POR_EMAIL);
        p.setEntidad(email); 

        Respuesta r = null;
        Usuario usuario = null;

        try {
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            oos.writeObject(p);

            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            r = (Respuesta) ois.readObject();

            // Cerramos recursos ya que ya tenemos la respuesta 
            ois.close();
            oos.close();
            socketCliente.close();

            if (r.getEntidad() != null) {
                usuario = (Usuario) r.getEntidad();
            } else if (r.getE() != null) {
                throw r.getE();
            }
        } catch (IOException ex) {
            manejadorIOException(ex);
        } catch (ClassNotFoundException ex) {
            manejadorClassNotFoundException(ex);
        }

        return usuario;
    }

    public int recuperarCuenta(Integer idUsuario) throws Excepciones {

        // Creamos y populamos la petición
        Peticion p = new Peticion();
        p.setIdOperacion(Operaciones.RECUPERAR_CUENTA); // Asegúrate de que esta constante está definida en tu enumeración de operaciones
        p.setIdEntidad(idUsuario);

        // Ahora que ya tenemos la petición populada, tenemos que enviarla al servidor
        Respuesta r = null;
        int cantidad = 0;

        try {
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            oos.writeObject(p);

            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            r = (Respuesta) ois.readObject();

            // Cerramos recursos ya que ya tenemos la respuesta
            ois.close();
            oos.close();

            socketCliente.close();

            if (r.getCantidad() != null) {
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

    public ArrayList<Evento> obtenerEventosPorProvinciaUsuario(Integer idUsuario) throws Excepciones {

        // Creamos y populamos la peticion
        Peticion p = new Peticion();
        p.setIdOperacion(Operaciones.EVENTOS_POR_PROVINCIA_USUARIO);
        p.setIdEntidad(idUsuario);


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
    
     //--------------------------------------------------------------------------------- \ TABLA MENSAJE /----------------------------------------------------------------------------------------
    
   public int insertarMensaje(Mensaje mensaje) throws Excepciones{
        
        // Creamos y populamos la peticion
        Peticion p = new Peticion();
        p.setIdOperacion(Operaciones.INSERTAR_MENSAJE);
        p.setEntidad(mensaje); // le pasamos el evento que hay que insertar
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
    
    public ArrayList<Mensaje> leerMensajes() throws Excepciones {

        // Creamos y populamos la peticion
        Peticion p = new Peticion();
        p.setIdOperacion(Operaciones.LEER_MENSAJES);

        
        // Ahora que ya tenemos la peticion populada, tenemos que enviarla al servidor
        Respuesta r = null;
        
        ArrayList<Mensaje> listaMensajes = null; 
        
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
            oos.writeObject(p);
            
            ObjectInputStream ois = new ObjectInputStream(socketCliente.getInputStream());
            r = (Respuesta) ois.readObject();
            
            ois.close();
            oos.close();
            
            socketCliente.close();
            if (r.getEntidad() != null){
                listaMensajes = (ArrayList<Mensaje>) r.getEntidad();
                return listaMensajes;
            } else if (r.getE() != null) {
                throw r.getE();
            }
            
            
        } catch (IOException ex) {
            manejadorIOException(ex);
        } catch (ClassNotFoundException ex) {
            manejadorClassNotFoundException(ex);
        }    
        return listaMensajes;
    }
    


}
    

