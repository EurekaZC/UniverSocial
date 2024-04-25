
package pojosastronomia;

import java.io.Serializable;

/**
 *
 * @author Cristina Zas Perez
 * @since 1.0
 */
public class Peticion implements Serializable{
    
    public static final long serialVersionUID = 4L;
    Integer idOperacion;
    Integer idEntidad;
    Object entidad;

    public Peticion() {
    }

    public Integer getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(Integer idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Integer getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(Integer idEntidad) {
        this.idEntidad = idEntidad;
    }

    public Object getEntidad() {
        return entidad;
    }

    public void setEntidad(Object entidad) {
        this.entidad = entidad;
    }

    @Override
    public String toString() {
        return "Peticion{" + "idOperacion=" + idOperacion + ", idEntidad=" + idEntidad + ", entidad=" + entidad + '}';
    }
    
    
}
