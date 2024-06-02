
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
    private String nombre, ape1, ape2, genero, email, telefono, nivelConocimiento, contrasena, descripcion;
    private boolean estaBorrado;
    private Provincia provincia;
    
    // CONSTRUCTOR ---------------------------------------------------------------------
    public Usuario() {
        
    }
    
    public Usuario(Integer idUsuario, String nombre) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
    }
    public Usuario(Integer idUsuario, String nombre, String ape1, String ape2, String genero, String email, String telefono, String nivelConocimiento, String contrasena, boolean estaBorrado, Provincia provincia) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.ape1 = ape1;
        this.ape2 = ape2;
        this.genero = genero;
        this.email = email;
        this.telefono = telefono;
        this.nivelConocimiento = nivelConocimiento;
        this.contrasena = contrasena;
        this.estaBorrado = estaBorrado;
        this.provincia = provincia;
    }

    public Usuario(Integer idUsuario, String nombre, String ape1, String ape2, String genero, String email, String telefono, String nivelConocimiento, String contrasena, boolean estaBorrado, Provincia provincia, String descripcion) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.ape1 = ape1;
        this.ape2 = ape2;
        this.genero = genero;
        this.email = email;
        this.telefono = telefono;
        this.nivelConocimiento = nivelConocimiento;
        this.contrasena = contrasena;
        this.estaBorrado = estaBorrado;
        this.provincia = provincia;
        this.descripcion = descripcion;
    }
    
    // GETTERS Y SETTERS ---------------------------------------------------------------------------------

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApe1() {
        return ape1;
    }

    public void setApe1(String ape1) {
        this.ape1 = ape1;
    }

    public String getApe2() {
        return ape2;
    }

    public void setApe2(String ape2) {
        this.ape2 = ape2;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNivelConocimiento() {
        return nivelConocimiento;
    }

    public void setNivelConocimiento(String nivelConocimiento) {
        this.nivelConocimiento = nivelConocimiento;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstaBorrado() {
        return estaBorrado;
    }

    public void setEstaBorrado(boolean estaBorrado) {
        this.estaBorrado = estaBorrado;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nombre=" + nombre + ", ape1=" + ape1 + ", ape2=" + ape2 + ", genero=" + genero + ", email=" + email + ", telefono=" + telefono + ", nivelConocimiento=" + nivelConocimiento + ", contrasena=" + contrasena + ", descripcion=" + descripcion + ", estaBorrado=" + estaBorrado + ", provincia=" + provincia + '}';
    }
    
    
    
}
