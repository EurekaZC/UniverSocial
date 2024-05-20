
package pojosastronomia;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Cristina Zas Perez
 * @since 1.0
 */
public class Respuesta implements Serializable{
    public static final long serialVersionUID = 7L;
    Integer idOperacion;
    Integer cantidad;
    Object entidad;
    ArrayList<Object> listaEntidades; // hay que quitar esto
    Excepciones e;

    public Respuesta() {
    }

    public Respuesta(Integer idOperacion, Integer cantidad, Object entidad, ArrayList<Object> listaEntidades, Excepciones e) {
        this.idOperacion = idOperacion;
        this.cantidad = cantidad;
        this.entidad = entidad;
        this.listaEntidades = listaEntidades;
        this.e = e;
    }

    public Integer getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(Integer idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Object getEntidad() {
        return entidad;
    }

    public void setEntidad(Object entidad) {
        this.entidad = entidad;
    }

    public ArrayList<Object> getListaEntidades() {
        return listaEntidades;
    }

    public void setListaEntidades(ArrayList<Object> listaEntidades) {
        this.listaEntidades = listaEntidades;
    }

    public Excepciones getE() {
        return e;
    }

    public void setE(Excepciones e) {
        this.e = e;
    }

    @Override
    public String toString() {
        return "Respuesta{" + "idOperacion=" + idOperacion + ", cantidad=" + cantidad + ", entidad=" + entidad + ", listaEntidades=" + listaEntidades + ", e=" + e + '}';
    }
    
}
