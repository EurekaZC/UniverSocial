
package pojosastronomia;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Cristina Zas Perez
 * @since 1.0
 */
public class Evento implements Serializable{
    // ATRIBUTOS --------------------------------------------------------------------
    public static final long serialVersionUID = 2L;
    private Integer idEvento;
    private String nombre, tipo, descripcion;
    private Date inicio, finalEvento;
    
    //CONSTRUCTOR --------------------------------------------------------------------------
    public Evento() {
        
    }

    public Evento(Integer idEvento, String nombre, String tipo, String descripcion, Date inicio, Date finalEvento) {
        this.idEvento = idEvento;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.inicio = inicio;
        this.finalEvento = finalEvento;
    }
    
    // GETTERS -----------------------------------------------------------------------------------------
    public Integer getIdEvento() {
        return idEvento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getInicio() {
        return inicio;
    }

    public Date getFinalEvento() {
        return finalEvento;
    }
    
    // SETTERS ---------------------------------------------------------------------------------------
    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public void setFinalEvento(Date finalEvento) {
        this.finalEvento = finalEvento;
    }

    @Override
    public String toString() {
        return "Evento{" + "idEvento=" + idEvento + ", nombre=" + nombre + ", tipo=" + tipo + ", descripcion=" + descripcion + ", inicio=" + inicio + ", finalEvento=" + finalEvento + '}';
    }
    
    
}
