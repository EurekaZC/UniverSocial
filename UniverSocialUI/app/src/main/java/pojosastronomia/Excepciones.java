
package pojosastronomia;

import java.io.Serializable;

/**
 *
 * @author Cristina Zas Perez
 * @since 1.0
 */
public class Excepciones extends Exception implements Serializable{
    public static final long serialVersionUID = 3L;
    String mensajeUsuario;
    String mensajeErrorBd;
    String sentenciaSQL;
    Integer codigoErrorBd;

    public Excepciones() {
    }

    public Excepciones(String mensajeUsuario, String mensajeErrorBd, String sentenciaSQL, Integer codigoErrorBd) {
        this.mensajeUsuario = mensajeUsuario;
        this.mensajeErrorBd = mensajeErrorBd;
        this.sentenciaSQL = sentenciaSQL;
        this.codigoErrorBd = codigoErrorBd;
    }

    public String getMensajeUsuario() {
        return mensajeUsuario;
    }

    public String getMensajeErrorBd() {
        return mensajeErrorBd;
    }

    public String getSentenciaSQL() {
        return sentenciaSQL;
    }

    public Integer getCodigoErrorBd() {
        return codigoErrorBd;
    }

    public void setMensajeUsuario(String mensajeUsuario) {
        this.mensajeUsuario = mensajeUsuario;
    }

    public void setMensajeErrorBd(String mensajeErrorBd) {
        this.mensajeErrorBd = mensajeErrorBd;
    }

    public void setSentenciaSQL(String sentenciaSQL) {
        this.sentenciaSQL = sentenciaSQL;
    }

    public void setCodigoErrorBd(Integer codigoErrorBd) {
        this.codigoErrorBd = codigoErrorBd;
    }

    @Override
    public String toString() {
        return "Excepciones{" + "mensajeUsuario=" + mensajeUsuario + ", mensajeErrorBd=" + mensajeErrorBd + ", sentenciaSQL=" + sentenciaSQL + ", codigoErrorBd=" + codigoErrorBd + '}';
    }
    
}
