package farmacia.jdbc.dao.mysql;

import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.empleadoDAO;
import farmacia.jdbc.dao.personaDAO;
import farmacia.jdbc.modelado.empleado;
import farmacia.jdbc.modelado.persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class empleadoSQL implements empleadoDAO {

    private Connection conexion;

    private final String INSERT = "INSERT INTO empleado(idpersona, login, password, fechaalta, idtipotrabajador, status) "
            + "VALUES (?, ?, ?, ?, ?, ?)";
    private final String UPDATE = "UPDATE empleado SET idpersona = ?, fechaalta = ?, status = ? "
            + "WHERE idempleado = ?";
    private final String DELETE = "UPDATE empleado SET status = 0 WHERE idempleado = ?";
    private final String GETALL = "SELECT * FROM empleado WHERE status = 1";//solo obtiene los activos 
    private final String GETONE = "SELECT * FROM empleado WHERE idempleado = ?";
    private final String CHANGEPASS="UPDATE empleado set password=? "
            + "WHERE login=?";

    public empleadoSQL(Connection con) {
        this.conexion = con;
    }

    @Override
    public Long insertar(empleado obj) throws DAOException {
        //insertar suponuendo que el objeto recibido tiene un registro de persona valido
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            DAOManagerSQL man = new DAOManagerSQL(conexion);
            stat = conexion.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            stat.setLong(1, obj.getIdpersona());
            stat.setString(2, obj.getLogin());
            stat.setString(3, obj.getPassword());
            stat.setDate(4, new java.sql.Date (obj.getFechaalta().getTime()));
            stat.setLong(5, obj.getIdtipotrabajador());
            stat.setBoolean(6, obj.isStatus());

            if (man.getPersonaDAO().obtener(obj.getIdpersona()) == null 
                    || man.getTipoTrabajadorDAO().obtener(obj.getIdtipotrabajador()) == null) {
                throw new DAOException("Error al ingresar un registro. Clave foranea no existente.");
            }

            if (stat.executeUpdate() == 0) {
                throw new DAOException("Error al ingresar un registro.");
            }

            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                obj.setIdempleado(rs.getLong(1));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilSQL.cerrar(stat, rs);

        }
        return obj.getIdempleado();
    }

    @Override
    public void insertarNuevo(persona per, empleado emp) throws DAOException {
        try {
            conexion.setAutoCommit(false);
            personaSQL perSQL = new personaSQL(conexion);
            tipotrabajadorSQL trabajadorSQL = new tipotrabajadorSQL(conexion);
            
            emp.setIdpersona(perSQL.insertar(per));//inserta la persona en primer lugar y modifica el idpersonadel empleado
            insertar(emp);//inserta al empleado
            
            if (trabajadorSQL.obtener(emp.getIdtipotrabajador()) == null) {
                throw new DAOException("Error al ingresar un registro. Clave foranea no existente.");
            }
            conexion.commit();
        } catch (SQLException ex) {
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                throw new DAOException("Error en transaccion.", ex1);
            }
            throw new DAOException("Error en SQL.", ex);
        } 
        //System.out.println(emp.getIdempleado() +", "+ per.getIdPersona());
    }

    @Override
    public void modificar(empleado obj) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conexion.prepareStatement(UPDATE);
            stat.setLong(1, obj.getIdpersona());
            stat.setDate(2, new java.sql.Date(obj.getFechaalta().getTime()));
            stat.setBoolean(3, obj.isStatus());
            stat.setLong(4, obj.getIdempleado());
           
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Error al modificar un registro.");
            }

        } catch (SQLException ex) {
            throw new DAOException("Error en SQL."+ ex.getMessage());
        } finally {
            UtilSQL.cerrar(stat);
        }
    }

    @Override
    public void eliminar(empleado obj) throws DAOException {
        PreparedStatement stat = null;
        try{
            stat = conexion.prepareStatement(DELETE);
            stat.setLong(1, obj.getIdempleado());
            if(stat.executeUpdate() == 0){
                throw new DAOException("Error al eliminar un registro.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL.", ex);
        }finally{
            UtilSQL.cerrar(stat);
        }
    }

    @Override
    public List<empleado> obtenertodos() throws DAOException{
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<empleado> lista = new ArrayList<>();
        try {
            stat = conexion.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while(rs.next()){
                lista.add(convertir(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilSQL.cerrar(stat, rs);
        }
        return lista;
    }

    @Override
    public empleado obtener(Long id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        empleado emp = null;
        try {
            stat = conexion.prepareStatement(GETONE);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                emp = convertir(rs);
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilSQL.cerrar(stat, rs);
        }
        return emp;
    }

    @Override
    public empleado convertir(ResultSet rs) throws DAOException {
        empleado emp = null;
        try{
        Long idpersona = rs.getLong("idpersona"); //indice
        Long idtipotrabajador = rs.getLong("idtipotrabajador");//indice
        String login = rs.getString("login");//32
        String password = rs.getString("password");//32
        Date fechaalta = rs.getDate("fechaalta");//nulo
        emp = new empleado(idpersona, idtipotrabajador, login, password, fechaalta);
        emp.setStatus(rs.getBoolean("status"));
        emp.setIdempleado(rs.getLong("idempleado"));
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new DAOException("Error en SQL.", ex);
        }
        return emp;
    }

   

    @Override
    public void actualizarpassword(empleado emp) throws DAOException {
       PreparedStatement stat = null;
        try{
            stat = conexion.prepareStatement(CHANGEPASS);
            stat.setString(1, emp.getPassword());
            stat.setString(2, emp.getLogin());
            
            if(stat.executeUpdate() == 0){
                throw new DAOException("No se encontró el usuario");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de SQL.", ex);
        }finally{
            UtilSQL.cerrar(stat);
        }
    }

}
