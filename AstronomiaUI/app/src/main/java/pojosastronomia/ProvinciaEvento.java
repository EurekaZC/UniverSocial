
package pojosastronomia;

import java.io.Serializable;

/**
 *
 * @author Cristina Zas Perez
 * @since 1.0
 */
public class ProvinciaEvento implements Serializable{
    // ATRIBUTOS ----------------------------------------------------------------------------------
    public static final long serialVersionUID = 6L;
    private Integer idProvinciaEvento;
    private Provincia provincia;
    private Evento evento;
    
    // CONSTRUCTOR ----------------------------------------------------------------------------
    public ProvinciaEvento() {
        
    }

    public ProvinciaEvento(Integer idProvinciaEvento, Provincia provincia, Evento evento) {
        this.idProvinciaEvento = idProvinciaEvento;
        this.provincia = provincia;
        this.evento = evento;
    }
    
    // GETTERS -------------------------------------------------------------------------------------
    public Integer getIdProvinciaEvento() {
        return idProvinciaEvento;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public Evento getEvento() {
        return evento;
    }
    
    // SETTERS ------------------------------------------------------------------------------------
    public void setIdProvinciaEvento(Integer idProvinciaEvento) {
        this.idProvinciaEvento = idProvinciaEvento;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    @Override
    public String toString() {
        return "ProvinciaEvento{" + "idProvinciaEvento=" + idProvinciaEvento + ", provincia=" + provincia + ", evento=" + evento + '}';
    }
    
    
}
