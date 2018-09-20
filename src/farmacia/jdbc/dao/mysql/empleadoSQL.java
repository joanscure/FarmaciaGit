package farmacia.jdbc.dao.mysql;

import farmacia.jdbc.dao.empleadoDAO;
import farmacia.jdbc.modelado.empleado;
import java.sql.Connection;
import java.util.List;

public class empleadoSQL implements empleadoDAO {

    private Connection conexion;

    private final String INSERT = "INSERT INTO empleado(idpersona, login, password, fechaalta, idtipotrabajador, status) "
            + "VALUES (?, ?, ?, ?, ?, ?) ";
    private final String UPDATE = "UPDATE empleado SET idpersona = ?, login = ?, password = ?, fechaalta = ?, idtipotrabajador = ?, status = ?";
    private final String DELETE = "UPDATE empleado SET status = 0 WHERE idempleado = ?";
    private final String GETALL = "SELECT * FROM empleado WHERE status = 1";//solo obtiene los activos 
    private final String GETONE = "SELECT * FROM empleado WHERE idempleado = ?";

    public empleadoSQL(Connection con) {
        this.conexion = con;
    }

    @Override
    public void insertar(empleado obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(empleado obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(empleado obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isActive(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<empleado> obtenertodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public empleado obtener(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<empleado> obtenerPorTipoTrabajador(Long idtipotrabajador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long obtenerIdPersona(Long idempleado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
