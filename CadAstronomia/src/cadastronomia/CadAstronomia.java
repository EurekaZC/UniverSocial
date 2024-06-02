package cadastronomia;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import pojosastronomia.Evento;
import pojosastronomia.Excepciones;
import pojosastronomia.Mensaje;
import pojosastronomia.Provincia;
import pojosastronomia.Usuario;



/**
 * Métodos de gestión del componente de acceso a datos
 *
 * @author Cristina Zas Perez
 * @version 1.0
 */
public class CadAstronomia {

    Connection conexion;

    // --------------------------------------------------------- \ CONSTRUCTOR/ ------------------------------------------
    /**
     * Constructor vacío de la clase de CadAstronomía
     *
     * @throws pojosastronomia.Excepciones La exepción se produce cuando hay
     * algún problema con la carga del jdbc
     */
    public CadAstronomia() throws Excepciones {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            Excepciones e = new Excepciones();
            e.setMensajeErrorBd(ex.getMessage());
            e.setMensajeUsuario("Error general del sistema. Consulte con el administrador");
            throw e;
        }
    }

    /**
     * Método que establece conexión con la base de datos ASTRONOMIA
     *
     * @throws pojosastronomia.Excepciones La excepción se produce cuando no se
     * crea conexión con la base de datos
     */
    private void conectar() throws Excepciones {
        try {
//           conexion = DriverManager.getConnection("jdbc:oracle:thin:@172.16.222.69:1521:test", "ASTRONOMIA", "kk");
            conexion = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.122:1521:XE", "ASTRONOMIA", "kk");
        } catch (SQLException ex) {
            Excepciones e = new Excepciones();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorBd(ex.getMessage());
            e.setMensajeUsuario("Error general del sistema. Consulte con el administrador");
            throw e;
        }

    }

    // ------------------------------------------------------- \ TABLA PROVINCIA /-------------------------------------------------------
    /**
     * Lee una provincia desde la base de datos utilizando su identificador
     * único.
     *
     * @param idProvincia el identificador único de la provincia a leer.
     * @return la instancia de la provincia leída desde la base de datos.
     * @throws Excepciones si ocurre un error durante la conexión a la base de
     * datos o al ejecutar la consulta SQL.
     */
    public Provincia leerProvincia(Integer idProvincia) throws Excepciones { // ---------------------------------------------------------
        conectar();
        Provincia provincia = new Provincia();
        String dml = "SELECT * FROM provincia WHERE id_provincia = ?";

        try {

            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);

            sentenciaPreparada.setObject(1, idProvincia, Types.INTEGER);

            ResultSet resultado = sentenciaPreparada.executeQuery();

            if (resultado.next()) {
                // Ahora populamos ese objeto con los datos
                provincia.setIdProvincia(((BigDecimal) resultado.getObject("ID_PROVINCIA")).intValue());
                provincia.setProvincia(resultado.getString("PROVINCIA"));
                provincia.setComunidad(resultado.getString("COMUNIDAD"));

                sentenciaPreparada.close();
                conexion.close();
            }

        } catch (SQLException ex) {

            Excepciones e = new Excepciones();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorBd(ex.getMessage());
            e.setSentenciaSQL(dml);
            e.setMensajeUsuario("Error general del sistema. Consulte con el administrador");
            throw e;
        }

        return provincia;
    }

    /**
     * Lee todas las provincias almacenadas en la base de datos.
     *
     * @return un ArrayList que contiene todas las provincias leídas desde la
     * base de datos.
     * @throws Excepciones si ocurre un error durante la conexión a la base de
     * datos o al ejecutar la consulta SQL.
     */
    public ArrayList<Provincia> leerProvincias() throws Excepciones { // ----------------------------------------------------------------
        conectar();
        ArrayList<Provincia> listaProvincia = new ArrayList();
        String dql1 = "SELECT * FROM provincia";

        try {
            Statement sentencia = conexion.createStatement();

            ResultSet resultado = sentencia.executeQuery(dql1);
            while (resultado.next()) { //devuelve false cuando no ha sido capaz de posicionar en otro registro, puesto que ya no hay. El next informa entonces del fin de registros.
                Provincia p = new Provincia();

                p.setIdProvincia(((BigDecimal) resultado.getObject("ID_PROVINCIA")).intValue());
                p.setProvincia(resultado.getString("PROVINCIA"));
                p.setComunidad(resultado.getString("COMUNIDAD"));

                // Una vez hemos populado todos los datos, toca añadir el usuario al ArrayList 
                listaProvincia.add(p);

            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {

            Excepciones e = new Excepciones();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorBd(ex.getMessage());
            e.setSentenciaSQL(dql1);
            e.setMensajeUsuario("Error general del sistema. Consulte con el administrador");
            throw e;
        }

        return listaProvincia;
    }

    // ------------------------------------------------------- \ TABLA USUARIO /-----------------------------------------------------------------
    /**
     * Método que permite insertar un nuevo usuario en la base de datos
     *
     * @param usuario Objeto Usuario que almacena los datos del usuario a
     * almacenar en la base de datos
     * @throws pojosastronomia.Excepciones La excepcion se produce cuando se ha
     * violado alguna constraint de la base de datos
     */
    public void insertarUsuario(Usuario usuario) throws Excepciones {
        conectar();
        String llamada = "call insertar_usuario(?,?,?,?,?,?,?,?,?,?)";
        try {
            CallableStatement sentenciaLlamable = conexion.prepareCall(llamada);

            sentenciaLlamable.setString(1, usuario.getNombre());
            sentenciaLlamable.setString(2, usuario.getApe1());
            sentenciaLlamable.setString(3, usuario.getApe2());
            sentenciaLlamable.setString(4, usuario.getGenero());
            sentenciaLlamable.setString(5, usuario.getEmail());
            sentenciaLlamable.setString(6, usuario.getTelefono());
            sentenciaLlamable.setString(7, usuario.getNivelConocimiento());
            sentenciaLlamable.setString(8, usuario.getContrasena());
            sentenciaLlamable.setObject(9, usuario.getProvincia().getIdProvincia(), Types.INTEGER);
            sentenciaLlamable.setString(10, usuario.getDescripcion());

            sentenciaLlamable.executeUpdate();

            sentenciaLlamable.close();
            conexion.close();

        } catch (SQLException ex) {
            Excepciones e = new Excepciones();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorBd(ex.getMessage());
            e.setSentenciaSQL(llamada);

            switch (ex.getErrorCode()) {
                case 1400: // código de violación de un not null
                    e.setMensajeUsuario("Todos los campos son obligatorios menos el segundo apellido.");
                    break;
                case 1: // código de violación de una UK
                    e.setMensajeUsuario("El telefono y el email no se pueden repetir. Ya hay un registro con este telefono o email.");
                    break;
                case 2290: // código de violación de una CC
                    e.setMensajeUsuario("Se ha introducido algun dato sin tener en cuenta el formato:  1) El genero solo puede ser 'M' de mujer, 'H' de hombre u 'O' de otro  2) el email debe contener el @  3) El telefono debe tener 9 caracteres como maximo.");
                    break;
                case 2291: // código de error de la FK
                    e.setMensajeUsuario("La provincia seleccionada no existe");
                    break;
                default:
                    e.setMensajeUsuario("Error general en el sistema, contacte con el administrador.");
            }
            throw e;
        }
    }

    /**
     * Método que permite modificar un usuario en la base de datos
     *
     * @param idUsuario variable de tipo Integer que almacena el id de registro
     * del usuario al que corresponde ese registro en la base de datos
     * @param usuario Objeto Usuario que almacena los datos del usuario a
     * modificar
     * @return Cantidad de usuarios modificados en la base de datos
     * @throws pojosastronomia.Excepciones La excepcion se produce cuando se ha
     * violado alguna constraint de la base de datos
     */
    public int modificarUsuario(Integer idUsuario, Usuario usuario) throws Excepciones {
        conectar();
        int registrosAfectados = 0;
        String dml = "update USUARIO set NOMBRE=?, APE1=?, APE2=?, GENERO=?, EMAIL=?, TELEFONO=?, NIVEL_CONOCIMIENTO=?, CONTRASENA=?, ID_PROVINCIA=?, DESCRIPCION=? where ID_USUARIO=?";

        try {
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);

            sentenciaPreparada.setString(1, usuario.getNombre());
            sentenciaPreparada.setString(2, usuario.getApe1());
            sentenciaPreparada.setString(3, usuario.getApe2());
            sentenciaPreparada.setString(4, usuario.getGenero());
            sentenciaPreparada.setString(5, usuario.getEmail());
            sentenciaPreparada.setString(6, usuario.getTelefono());
            sentenciaPreparada.setString(7, usuario.getNivelConocimiento());
            sentenciaPreparada.setString(8, usuario.getContrasena());
            sentenciaPreparada.setObject(9, usuario.getProvincia().getIdProvincia(), Types.INTEGER);
            sentenciaPreparada.setString(10, usuario.getDescripcion()); 
            sentenciaPreparada.setObject(11, idUsuario);

            registrosAfectados = sentenciaPreparada.executeUpdate();

            sentenciaPreparada.close();
            conexion.close();

        } catch (SQLException ex) {
            Excepciones e = new Excepciones();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorBd(ex.getMessage());
            e.setSentenciaSQL(dml);

            switch (ex.getErrorCode()) {
                case 1407: // código de violación de un not null
                    e.setMensajeUsuario("Todos los campos son obligatorios menos el segundo apellido.");
                    break;
                case 1: // código de violación de una UK
                    e.setMensajeUsuario("El telefono y el email no se pueden repetir. Ya hay un registro con este telefono o email.");
                    break;
                case 2290: // código de violación de una CC
                    e.setMensajeUsuario("Se ha introducido algun dato sin tener en cuenta el formato:  1) El genero solo puede ser 'M' de mujer, 'H' de hombre u 'O' de otro  2) el email debe contener el @  3) El telefono debe tener 9 caracteres como maximo.");
                    break;
                case 2291: // código de error de la FK
                    e.setMensajeUsuario("La provincia seleccionada no existe.");
                    break;
                default:
                    e.setMensajeUsuario("Error general en el sistema, contacte con el administrador.");
            }
            throw e;
        }

        return registrosAfectados;
    }
    
      /**
     * Método que permite eliminar un usuario de la base de datos
     *
     * @param idUsuario varibale de tipo Integer que almacena el id de registro
     * del usuario
     * @return La cantidad de registros afectados
     * @throws pojosastronomia.Excepciones La excepción se produce al no
     * encontrar un usuario con los datos propuestos
     */
    public int eliminarUsuario(Integer idUsuario) throws Excepciones { // ---------------------------------------------------------------------------------------------------------------------------------------
        conectar();
        String llamada = "call borrar_usuario(?)";
        try {

            CallableStatement sentenciaLlamable = conexion.prepareCall(llamada);

            sentenciaLlamable.setInt(1, idUsuario);

            sentenciaLlamable.executeUpdate();

            sentenciaLlamable.close();
            conexion.close();

        } catch (SQLException ex) {

            Excepciones e = new Excepciones();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorBd(ex.getMessage());
            e.setSentenciaSQL(llamada);

            switch (ex.getErrorCode()) {

                case 45000: //no se encuentra el id del usuario
                    e.setMensajeUsuario("El usuario seleccionado no existe.");
                    break;

                default:
                    e.setMensajeUsuario("Error general en el sistema, contacte con el administrador.");

            }
            throw e;
        }
        return 1;
    }

    /**
     * Método que permite leer un usuario registrado
     *
     * @param idUsuario variable de tipo Integer que almacena el id de registro
     * del usuario al que corresponde ese registro en la base de datos
     * @return objeto Usuario
     * @throws pojosastronomia.Excepciones La excepcion se produce cuando se ha
     * violado alguna constraint de la base de datos
     */
    public Usuario leerUsuario(Integer idUsuario) throws Excepciones {
        conectar();
        Usuario usuario = new Usuario();
        String dml = "SELECT u.*, p.* FROM usuario u, provincia p WHERE u.id_usuario = ? AND u.id_provincia = p.id_provincia";

        try {
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.setObject(1, idUsuario, Types.INTEGER);
            ResultSet resultado = sentenciaPreparada.executeQuery();

            if (resultado.next()) {
                // Ahora populamos ese objeto con los datos
                usuario.setIdUsuario(((BigDecimal) resultado.getObject("ID_USUARIO")).intValue());
                usuario.setNombre(resultado.getString("NOMBRE"));
                usuario.setApe1(resultado.getString("APE1"));
                usuario.setApe2(resultado.getString("APE2"));
                usuario.setGenero(resultado.getString("GENERO"));
                usuario.setEmail(resultado.getString("EMAIL"));
                usuario.setTelefono(resultado.getString("TELEFONO"));
                usuario.setNivelConocimiento(resultado.getString("NIVEL_CONOCIMIENTO"));
                usuario.setContrasena(resultado.getString("CONTRASENA"));
                usuario.setEstaBorrado(resultado.getBoolean("ESTA_BORRADO"));
                usuario.setDescripcion(resultado.getString("DESCRIPCION")); 
                
                // también tenemos que popular la tabla de la clave foranea, por lo que creamos el objeto y demás
                Provincia p = new Provincia();
                p.setIdProvincia(((BigDecimal) resultado.getObject("ID_PROVINCIA")).intValue());
                p.setProvincia(resultado.getString("PROVINCIA"));
                p.setComunidad(resultado.getString("COMUNIDAD"));
                // Añadimos los datos de provincia al usuario
                usuario.setProvincia(p);

                sentenciaPreparada.close();
                conexion.close();
            }
        } catch (SQLException ex) {
            Excepciones e = new Excepciones();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorBd(ex.getMessage());
            e.setSentenciaSQL(dml);
            e.setMensajeUsuario("Error general del sistema. Consulte con el administrador");
            throw e;
        }
        return usuario;
    }

    /**
     * Método que permite leer los usuarios registrados en la base de datos
     *
     * @return arraylist de tipo Usuario
     * @throws pojosastronomia.Excepciones La excepcion se produce cuando se ha
     * violado alguna constraint de la base de datos
     */
    public ArrayList<Usuario> leerUsuarios() throws Excepciones {
        conectar();
        ArrayList<Usuario> listaUsuario = new ArrayList();
        String dql1 = "select * from provincia p, usuario u where p.id_provincia = u.id_provincia";
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql1);
            while (resultado.next()) {
                // tenemos primero que crear un objeto de la clase usuario
                Usuario u = new Usuario();
                // Ahora populamos ese objeto con los datos
                u.setIdUsuario(((BigDecimal) resultado.getObject("ID_USUARIO")).intValue());
                u.setNombre(resultado.getString("NOMBRE"));
                u.setApe1(resultado.getString("APE1"));
                u.setApe2(resultado.getString("APE2"));
                u.setGenero(resultado.getString("GENERO"));
                u.setEmail(resultado.getString("EMAIL"));
                u.setTelefono(resultado.getString("TELEFONO"));
                u.setNivelConocimiento(resultado.getString("NIVEL_CONOCIMIENTO"));
                u.setEstaBorrado(resultado.getBoolean("ESTA_BORRADO"));
                u.setDescripcion(resultado.getString("DESCRIPCION")); 
                
                // también tenemos que popular la tabla de la clave foranea, por lo que creamos el objeto y demás
                Provincia p = new Provincia();
                p.setIdProvincia(((BigDecimal) resultado.getObject("ID_PROVINCIA")).intValue());
                p.setProvincia(resultado.getString("PROVINCIA"));
                p.setComunidad(resultado.getString("COMUNIDAD"));
                // Añadimos los datos de provincia al usuario
                u.setProvincia(p);
                // Una vez hemos populado todos los datos, toca añadir el usuario al ArrayList 
                listaUsuario.add(u);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
        } catch (SQLException ex) {
            Excepciones e = new Excepciones();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorBd(ex.getMessage());
            e.setSentenciaSQL(dql1);
            e.setMensajeUsuario("Error general del sistema. Consulte con el administrador");
            throw e;
        }
        return listaUsuario;
    }

    /**
     * Método que permite buscar un usuario por su email
     *
     * @param email variable de tipo String que almacena el email del usuario a
     * buscar
     * @return objeto Usuario
     * @throws pojosastronomia.Excepciones La excepcion se produce cuando se ha
     * violado alguna constraint de la base de datos
     */
    public Usuario buscarUsuarioPorEmail(String email) throws Excepciones {
        conectar();
        Usuario usuario = new Usuario();
        String dml = "SELECT u.*, p.* FROM usuario u, provincia p WHERE u.email = ? AND u.id_provincia = p.id_provincia";

        try {
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.setString(1, email);
            ResultSet resultado = sentenciaPreparada.executeQuery();

            if (resultado.next()) {
                // Populamos el objeto usuario con los datos
                usuario.setIdUsuario(((BigDecimal) resultado.getObject("ID_USUARIO")).intValue());
                usuario.setNombre(resultado.getString("NOMBRE"));
                usuario.setApe1(resultado.getString("APE1"));
                usuario.setApe2(resultado.getString("APE2"));
                usuario.setGenero(resultado.getString("GENERO"));
                usuario.setEmail(resultado.getString("EMAIL"));
                usuario.setTelefono(resultado.getString("TELEFONO"));
                usuario.setNivelConocimiento(resultado.getString("NIVEL_CONOCIMIENTO"));
                usuario.setContrasena(resultado.getString("CONTRASENA"));
                usuario.setEstaBorrado(resultado.getBoolean("ESTA_BORRADO"));
                usuario.setDescripcion(resultado.getString("DESCRIPCION")); 

                // Populamos el objeto Provincia
                Provincia p = new Provincia();
                p.setIdProvincia(((BigDecimal) resultado.getObject("ID_PROVINCIA")).intValue());
                p.setProvincia(resultado.getString("PROVINCIA"));
                p.setComunidad(resultado.getString("COMUNIDAD"));
                usuario.setProvincia(p);
            }
            sentenciaPreparada.close();
            conexion.close();
        } catch (SQLException ex) {
            Excepciones e = new Excepciones();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorBd(ex.getMessage());
            e.setSentenciaSQL(dml);
            e.setMensajeUsuario("Error general del sistema. Consulte con el administrador");
            throw e;
        }
        return usuario;
    }

    //--------------------------------------------------------------------------------- \ TABLA EVENTO /----------------------------------------------------------------------------------------
    /**
     * Método que permite insertar un nuevo evento en la base de datos
     *
     * @param evento objteo Evento que almacena los datos del evento a insertar
     * @throws pojosastronomia.Excepciones La excepcion se produce cuando se ha
     * violado alguna constraint de la base de datos
     */
    public int insertarEvento(Evento evento) throws Excepciones { // ---------------------------------------------------------------------------------------------------------------------------------------
        conectar();
        int registrosAfectados = 0;
        String dml = "insert into EVENTO (ID_EVENTO, NOMBRE, TIPO, DESCRIPCION, INICIO, FINAL) values (SECUENCIA_ID_EVENTO.nextval, ?, ?, ?, ?, ?)";

        try {

            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);

            sentenciaPreparada.setString(1, evento.getNombre());
            sentenciaPreparada.setString(2, evento.getTipo());
            sentenciaPreparada.setString(3, evento.getDescripcion());
            // ahora tenemos el manejo de fechas
            Date fechaEvento = evento.getInicio();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String inicioComoCadena = sdf.format(fechaEvento);
            Date fechaUtil = sdf.parse(inicioComoCadena);
            java.sql.Date inicioSql = new java.sql.Date(fechaUtil.getTime());
            sentenciaPreparada.setObject(4, inicioSql, Types.DATE);

            Date finalEvento = evento.getFinalEvento();
            String finalCadena = sdf.format(finalEvento);
            Date finalUtil = sdf.parse(finalCadena);
            java.sql.Date finalSql = new java.sql.Date(finalUtil.getTime());
            sentenciaPreparada.setObject(5, finalSql, Types.DATE);

            registrosAfectados = sentenciaPreparada.executeUpdate();

            sentenciaPreparada.close();
            conexion.close();

        } catch (SQLException ex) {

            Excepciones e = new Excepciones();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorBd(ex.getMessage());
            e.setSentenciaSQL(dml);

            switch (ex.getErrorCode()) {

                case 1407: //codigo de violacion de un notnull
                    e.setMensajeUsuario("Todos los campos son obligatorios menos el segundo apellido.");
                    break;

                case 1: //codigo de violacion de una uk
                    e.setMensajeUsuario("El nombre del evento no se puede repetir, ya hay un evento con ese nombre.");
                    break;

                default:
                    e.setMensajeUsuario("Error general en el sistema, contacte con el administrador.");

            }

            throw e;

        } catch (ParseException ex) {
            Excepciones e = new Excepciones();
            e.setMensajeUsuario("La fecha debe de tener el formato: yyyy-MM-dd");
            throw e;
        }

        return registrosAfectados;
    }

    /**
     * Método que permite modificar un evento registrado en la base de datos
     *
     * @param idEvento variable de tipo Integer que almacena el id
     * correspondiente al evento que se desea modificar
     * @param evento objeto Evento que almacena la informacion a modificar del
     * evento
     * @return La cantidad de registros afectados
     * @throws pojosastronomia.Excepciones La excepcion se produce cuando se ha
     * violado alguna constraint de la base de datos
     */
    public int modificarEvento(Integer idEvento, Evento evento) throws Excepciones { // ---------------------------------------------------------------------------------------------------------------------------------------
        conectar();
        int registrosAfectados = 0;
        String dml = "update EVENTO set NOMBRE=?, TIPO=?, DESCRIPCION=?, INICIO=?, FINAL=? where ID_EVENTO=?";

        try {
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);

            sentenciaPreparada.setString(1, evento.getNombre());
            sentenciaPreparada.setString(2, evento.getTipo());
            sentenciaPreparada.setString(3, evento.getDescripcion());
            // ahora tenemos el manejo de fechas
            Date fechaEvento = evento.getInicio();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String inicioComoCadena = sdf.format(fechaEvento);
            Date fechaUtil = sdf.parse(inicioComoCadena);
            java.sql.Date inicioSql = new java.sql.Date(fechaUtil.getTime());
            sentenciaPreparada.setObject(4, inicioSql, Types.DATE);

            Date finalEvento = evento.getFinalEvento();
            String finalCadena = sdf.format(finalEvento);
            Date finalUtil = sdf.parse(finalCadena);
            java.sql.Date finalSql = new java.sql.Date(finalUtil.getTime());
            sentenciaPreparada.setObject(5, finalSql, Types.DATE);

            sentenciaPreparada.setObject(6, idEvento);

            registrosAfectados = sentenciaPreparada.executeUpdate();

            sentenciaPreparada.close();
            conexion.close();

        } catch (SQLException ex) {

            Excepciones e = new Excepciones();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorBd(ex.getMessage());
            e.setSentenciaSQL(dml);

            switch (ex.getErrorCode()) {

                case 1407: //codigo de violacion de un notnull
                    e.setMensajeUsuario("Todos los campos son obligatorios menos el segundo apellido.");
                    break;

                case 1: //codigo de violacion de una uk
                    e.setMensajeUsuario("El nombre del evento no se puede repetir, ya hay un evento con ese nombre.");
                    break;

                default:
                    e.setMensajeUsuario("Error general en el sistema, contacte con el administrador.");

            }
            throw e;

        } catch (ParseException ex) {
            Excepciones e = new Excepciones();
            e.setMensajeUsuario("La fecha debe de tener el formato: yyyy-MM-dd");
            throw e;

        }

        return registrosAfectados;
    }

    /**
     * Método que permite eliminar un evento registrado en la base de datos
     *
     * @param idEvento variable de tipo Integer que almacena el id del evento a
     * eliminar
     * @return La cantidad de registros afectados
     * @throws pojosastronomia.Excepciones La excepcion se produce cuando se ha
     * violado alguna constraint de la base de datos
     */
    public int eliminarEvento(Integer idEvento) throws Excepciones { // ---------------------------------------------------------------------------------------------------------------------------------------
        conectar();
        int registrosAfectados = 0;
        String dml = "delete from EVENTO where ID_EVENTO=?";
        try {
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);
            sentenciaPreparada.setObject(1, idEvento, Types.INTEGER);

            registrosAfectados = sentenciaPreparada.executeUpdate();

            sentenciaPreparada.close();
            conexion.close();

        } catch (SQLException ex) {
            Excepciones e = new Excepciones();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorBd(ex.getMessage());
            e.setSentenciaSQL(dml);

            switch (ex.getErrorCode()) {

                case 2292: // codigo de restricción de integridad. No se puede borrar el registro padre, sin haber borrado el registro hijo antes
                    e.setMensajeUsuario("No se puede eliminar un registro sin borrar antes el registro del evento en la tabla provincia-evento."); // el id_usuario es clave foranea de la tabla chat
                    break;
                default:
                    e.setMensajeUsuario("Error general en el sistema, contacte con el administrador.");

            }
            throw e;
        }
        return registrosAfectados;

    }

    /**
     * Método que permite visualizar un unico evento registrado en la base de
     * datos
     *
     * @param idEvento variable de tipo Integer que almacena el id del evento
     * que se desea visualizar
     * @return objeto Evento donde se almacena la informacion del evento a
     * visualizar
     * @throws pojosastronomia.Excepciones La excepcion se produce cuando se ha
     * violado alguna constraint de la base de datos
     */
    public Evento leerEvento(Integer idEvento) throws Excepciones { // ---------------------------------------------------------------------------------------------------------------------------------------
        conectar();
        Evento ev = new Evento();
        String dql1 = "select * from evento e where e.id_evento= " + idEvento;
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql1); //nos devuelve un objeto de la clase resultset, que es parecido a un arraylist

            while (resultado.next()) { //devuelve false cuando no ha sido capaz de posicionar en otro registro, puesto que ya no hay. El next informa entonces del fin de registros.

                ev.setIdEvento(((BigDecimal) resultado.getObject("ID_EVENTO")).intValue());
                ev.setNombre(resultado.getString("NOMBRE"));
                ev.setTipo(resultado.getString("TIPO"));
                ev.setDescripcion(resultado.getString("DESCRIPCION"));
                ev.setInicio(resultado.getDate("INICIO"));
                ev.setFinalEvento(resultado.getDate("FINAL"));

            }

            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {

            Excepciones e = new Excepciones();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorBd(ex.getMessage());
            e.setSentenciaSQL(dql1);
            e.setMensajeUsuario("Error general del sistema. Consulte con el administrador");
            throw e;
        }
        return ev;
    }

    /**
     * Método que permite visualizar todos los eventos registrados en la base de
     * datos.
     *
     * @return objeto ArrayList de tipo Evento donde se almacenan todos los
     * eventos a visualizar
     * @throws pojosastronomia.Excepciones La excepcion se produce cuando se ha
     * violado alguna constraint de la base de datos
     */
    public ArrayList<Evento> leerEventos() throws Excepciones { // ---------------------------------------------------------------------------------------------------------------------------------------
        conectar();
        ArrayList<Evento> listaEventos = new ArrayList();
        String dql1 = "select * from evento e";
//        String dql1 = "select * from evento e, provincia-evento pe, provincia p  where e.id_evento= pe.id_evento and pe.id_provincia=p.id_provincia";
        try {
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(dql1); //nos devuelve un objeto de la clase resultset, que es parecido a un arraylist
            while (resultado.next()) { //devuelve false cuando no ha sido capaz de posicionar en otro registro, puesto que ya no hay. El next informa entonces del fin de registros.
                //tenemos primero que crear un objeto de la clase usuario
                Evento e = new Evento();
                // Ahora populamos ese objeto con los datos
                //hacemos casting porque devuelve un int y necesitamos que devuelva un integer y hacemos el arreglo de getInt porque sabemos que no va a haber nulos y así no da fallo
                e.setIdEvento(((BigDecimal) resultado.getObject("ID_EVENTO")).intValue());
                e.setNombre(resultado.getString("NOMBRE"));
                e.setTipo(resultado.getString("TIPO"));
                e.setDescripcion(resultado.getString("DESCRIPCION"));
                e.setInicio(resultado.getDate("INICIO"));
                e.setFinalEvento(resultado.getDate("FINAL"));

                listaEventos.add(e);
            }
            resultado.close();
            sentencia.close();
            conexion.close();

        } catch (SQLException ex) {

            Excepciones e = new Excepciones();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorBd(ex.getMessage());
            e.setSentenciaSQL(dql1);
            e.setMensajeUsuario("Error general del sistema. Consulte con el administrador");
            throw e;
        }
        return listaEventos;
    }
    
    public ArrayList<Evento> obtenerEventosPorProvinciaUsuario(Integer idUsuario) throws Excepciones { // ---------------------------------------------------------------------------------------------------------------------------------------
       conectar();
    ArrayList<Evento> eventos = new ArrayList<>();
    String dql1 = "SELECT E.* " +
                  "FROM EVENTO E " +
                  "INNER JOIN \"PROVINCIA-EVENTO\" PE ON E.ID_EVENTO = PE.ID_EVENTO " +
                  "INNER JOIN PROVINCIA P ON PE.ID_PROVINCIA = P.ID_PROVINCIA " +
                  "INNER JOIN USUARIO U ON P.ID_PROVINCIA = U.ID_PROVINCIA " +
                  "WHERE U.ID_USUARIO = ?";
    try {
        PreparedStatement sentencia = conexion.prepareStatement(dql1);
        sentencia.setInt(1, idUsuario);
        ResultSet resultado = sentencia.executeQuery();
        while (resultado.next()) {
            Evento evento = new Evento();
            evento.setIdEvento(resultado.getInt("ID_EVENTO"));
            evento.setNombre(resultado.getString("NOMBRE"));
            evento.setTipo(resultado.getString("TIPO"));
            evento.setInicio(resultado.getDate("INICIO"));
            evento.setFinalEvento(resultado.getDate("FINAL"));
            evento.setDescripcion(resultado.getString("DESCRIPCION"));

            eventos.add(evento);
        }
        resultado.close();
        sentencia.close();
        conexion.close();
    } catch (SQLException e) {
        Excepciones excepcion = new Excepciones();
        excepcion.setMensajeUsuario("Error al obtener los eventos.");
        excepcion.setMensajeErrorBd(e.getMessage());
        throw excepcion;
    }
    return eventos;
    }
    
    // ------------------------------------------------------- \ TABLA MENSAJE /-----------------------------------------------------------------
    /**
     * Inserta un nuevo mensaje en la base de datos.
     *
     * @param mensaje el mensaje que se desea insertar.
     * @throws Excepciones si ocurre un error durante la conexión a la base de
     * datos o al ejecutar la inserción del mensaje.
     */
    public void insertarMensaje(Mensaje mensaje) throws Excepciones {
        conectar();
        String dml = "INSERT INTO MENSAJE (ID_MENSAJE, MENSAJE, HORA_MENSAJE, ID_USUARIO, ID_EVENTO) VALUES (SECUENCIA_ID_MENSAJE.nextval, ?, ?, ?, ?)";
        try {
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(dml);

            sentenciaPreparada.setString(1, mensaje.getMensaje());
        sentenciaPreparada.setTimestamp(2, new Timestamp(mensaje.getHoraMensaje().getTime()));
        sentenciaPreparada.setInt(3, mensaje.getUsuario().getIdUsuario()); 
        sentenciaPreparada.setInt(4, mensaje.getEvento().getIdEvento()); 

        sentenciaPreparada.executeUpdate();

        sentenciaPreparada.close();
        conexion.close();
        } catch (SQLException ex) {
            Excepciones e = new Excepciones();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorBd(ex.getMessage());
            e.setSentenciaSQL(dml);

            switch (ex.getErrorCode()) {
                case 1407:
                    e.setMensajeUsuario("El mensaje no puede estar vacío.");
                    break;
                case 1:
                    e.setMensajeUsuario("Error al insertar el mensaje.");
                    break;
                default:
                    e.setMensajeUsuario("Error general en el sistema, contacte con el administrador.");
            }
            throw e;
        }
    }
 
    /**
     * Lee todos los mensajes almacenados en la base de datos junto con la
     * información del usuario y el evento asociados.
     *
     * @return un ArrayList que contiene todos los mensajes leídos desde la base
     * de datos, junto con la información del usuario y el evento asociados.
     * @throws Excepciones si ocurre un error durante la conexión a la base de
     * datos o al ejecutar la consulta SQL.
     */
    public ArrayList<Mensaje> leerMensajes() throws Excepciones {
        conectar();
        ArrayList<Mensaje> listaMensajes = new ArrayList<>();
        String dql = "SELECT m.ID_MENSAJE, m.MENSAJE, m.HORA_MENSAJE, "
                + "u.ID_USUARIO, u.NOMBRE, u.APE1, u.APE2, u.GENERO, u.EMAIL, u.TELEFONO, u.NIVEL_CONOCIMIENTO, "
                + "e.ID_EVENTO, e.NOMBRE AS NOMBRE_EVENTO, e.TIPO, e.INICIO, e.FINAL, e.DESCRIPCION "
                + "FROM mensaje m "
                + "INNER JOIN usuario u ON m.ID_USUARIO = u.ID_USUARIO "
                + "INNER JOIN evento e ON m.ID_EVENTO = e.ID_EVENTO";

        try {
        Statement sentencia = conexion.createStatement();
        ResultSet resultado = sentencia.executeQuery(dql);

        while (resultado.next()) {
            Mensaje mensaje = new Mensaje();
            mensaje.setIdMensaje(resultado.getInt("ID_MENSAJE"));
            mensaje.setMensaje(resultado.getString("MENSAJE"));
            mensaje.setHoraMensaje(resultado.getTimestamp("HORA_MENSAJE"));

            Usuario usuario = new Usuario();
            usuario.setIdUsuario(resultado.getInt("ID_USUARIO"));
            usuario.setNombre(resultado.getString("NOMBRE"));
            usuario.setApe1(resultado.getString("APE1"));
            usuario.setApe2(resultado.getString("APE2"));
            usuario.setGenero(resultado.getString("GENERO"));
            usuario.setEmail(resultado.getString("EMAIL"));
            usuario.setTelefono(resultado.getString("TELEFONO"));
            usuario.setNivelConocimiento(resultado.getString("NIVEL_CONOCIMIENTO"));

            Evento evento = new Evento();
            evento.setIdEvento(resultado.getInt("ID_EVENTO"));
            evento.setNombre(resultado.getString("NOMBRE_EVENTO"));
            evento.setTipo(resultado.getString("TIPO"));
            evento.setDescripcion(resultado.getString("DESCRIPCION"));
            evento.setInicio(resultado.getTimestamp("INICIO"));
            evento.setFinalEvento(resultado.getTimestamp("FINAL"));

            mensaje.setUsuario(usuario);
            mensaje.setEvento(evento);

            listaMensajes.add(mensaje);
        }

        resultado.close();
        sentencia.close();
        conexion.close();
        
        } catch (SQLException ex) {

            Excepciones e = new Excepciones();
            e.setCodigoErrorBd(ex.getErrorCode());
            e.setMensajeErrorBd(ex.getMessage());
            e.setSentenciaSQL(dql);
            e.setMensajeUsuario("Error general del sistema. Consulte con el administrador");
            throw e;

        }

        return listaMensajes;

    }


    

}
