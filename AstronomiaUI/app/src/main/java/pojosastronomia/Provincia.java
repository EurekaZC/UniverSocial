
package pojosastronomia;

import java.io.Serializable;


/**
 *
 * @author Cristina Zas Perez
 * @since 1.0
 */
public class Provincia implements Serializable{
    
    // ATRIBUTOS -------------------------------------------------------------------
    public static final long serialVersionUID = 5L;
    private Integer idProvincia;
    private String provincia;
    private String comunidad;
    
    // CONSTRUCTORES -------------------------------------------------------------
    public Provincia() {
        
    }

    public Provincia(Integer idProvincia, String provincia, String comunidad) {
        this.idProvincia = idProvincia;
        this.provincia = provincia;
        this.comunidad = comunidad;
    }
    
    // GETTERS --------------------------------------------------------------------------------
    public Integer getIdProvincia() {
        return idProvincia;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getComunidad() {
        return comunidad;
    }
    
    // SETTERS ---------------------------------------------------------------------------------
    public void setIdProvincia(Integer idProvincia) {
        this.idProvincia = idProvincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    @Override
    public String toString() {
        return "Provincia{" + "idProvincia=" + idProvincia + ", provincia=" + provincia + ", comunidad=" + comunidad + '}';
    }
    
  
}
