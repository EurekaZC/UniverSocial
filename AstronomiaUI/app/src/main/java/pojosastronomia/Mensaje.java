
package pojosastronomia;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Cristina Zas Perez
 * @since 1.0
 */
public class Mensaje implements Serializable{
    
    // ATRIBUTOS -----------------------------------------------------------------------
    public static final long serialVersionUID = 1L;
    private Integer idMensaje;
    private String mensaje;
    private Date horaMensaje;
    private Usuario usuario;
    private Evento evento;
    
    // CONSTRUCTOR ------------------------------------------------------------------
    public Mensaje() {
        
    }

    public Mensaje(Integer idMensaje, String mensaje, Date horaMensaje, Usuario usuario, Evento evento) {
        this.idMensaje = idMensaje;
        this.mensaje = mensaje;
        this.horaMensaje = horaMensaje;
        this.usuario = usuario;
        this.evento = evento;
    }

     // GETTERS Y SETTERS ------------------------------------------------------------------
    public Integer getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getHoraMensaje() {
        return horaMensaje;
    }

    public void setHoraMensaje(Date horaMensaje) {
        this.horaMensaje = horaMensaje;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    @Override
    public String toString() {
        return "Mensaje{" + "idMensaje=" + idMensaje + ", mensaje=" + mensaje + ", horaMensaje=" + horaMensaje + ", usuario=" + usuario + ", evento=" + evento + '}';
    }

   
}
