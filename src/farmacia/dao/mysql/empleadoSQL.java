package farmacia.dao.mysql;

import farmacia.dao.empleadoDAO;
import farmacia.modelado.empleado;
import java.util.List;

public class empleadoSQL implements empleadoDAO{

    String INSERT = "INSERT INTO empleado(idpersona, login, password, fechaalta, idtipotrabajador, status) "
    +"VALUES (?, ?, ?, ?, ?, ?) ";
    String UPDATE = "UPDATE empleado SET idpersona = ?, login = ?, password = ?, fechaalta = ?, idtipotrabajador = ?, status = ?";
    String DELETE = "UPDATE empleado SET status = 0 WHERE idempleado = ?";
    String GETALL = "SELECT * FROM empleado WHERE status = 1";//solo obtiene los activos 
    String GETONE = "SELECT * FROM empleado WHERE idempleado = ?";


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
    public Long estaRelacionado(Long id, Long id2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
