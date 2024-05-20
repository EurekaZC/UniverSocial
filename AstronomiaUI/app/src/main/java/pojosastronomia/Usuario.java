
package pojosastronomia;

import java.io.Serializable;

/**
 *
 * @author Cristina Zas Perez
 * @since 1.0
 */
public class Usuario implements Serializable{
    // ATRIBUTOS --------------------------------------------------------------------------------
    public static final long serialVersionUID = 8L;
    private Integer idUsuario;
    private String nombre, ape1, ape2, genero, email, telefono, nivelConocimiento, contrasena;
    private Provincia provincia;
    
    // CONSTRUCTOR ---------------------------------------------------------------------
    public Usuario() {
        
    }

    public Usuario(Integer idUsuario, String nombre, String ape1, String ape2, String genero, String email, String telefono, String nivelConocimiento, String contrasena, Provincia provincia) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.ape1 = ape1;
        this.ape2 = ape2;
        this.genero = genero;
        this.email = email;
        this.telefono = telefono;
        this.nivelConocimiento = nivelConocimiento;
        this.contrasena = contrasena;
        this.provincia = provincia;
    }
    
    // GETTERS ---------------------------------------------------------------------------------
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApe1() {
        return ape1;
    }

    public String getApe2() {
        return ape2;
    }

    public String getGenero() {
        return genero;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getNivelConocimiento() {
        return nivelConocimiento;
    }

    public String getContrasena() {
        return contrasena;
    }

    public Provincia getProvincia() {
        return provincia;
    }
    
    // SETTERS ---------------------------------------------------------------------------
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApe1(String ape1) {
        this.ape1 = ape1;
    }

    public void setApe2(String ape2) {
        this.ape2 = ape2;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setNivelConocimiento(String nivelConocimiento) {
        this.nivelConocimiento = nivelConocimiento;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nombre=" + nombre + ", ape1=" + ape1 + ", ape2=" + ape2 + ", genero=" + genero + ", email=" + email + ", telefono=" + telefono + ", nivelConocimiento=" + nivelConocimiento + ", contrasena=" + contrasena + ", provincia=" + provincia + '}';
    }
    
}
