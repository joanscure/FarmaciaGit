package farmacia.jdbc.dao.mysql;

import farmacia.jdbc.dao.descuentoDAO;
import farmacia.jdbc.modelado.descuento;
import java.sql.Connection;
import java.util.List;

public class descuentoSQL implements descuentoDAO {

    private Connection conexion;

    private final String INSERT = "INSERT INTO descuento(nombredescuento, condicion, porcentaje, descripciondescuento, status) "
            + "VALUES (?, ?, ?, ?, ?) ";
    private final String UPDATE = "UPDATE descuento SET nombredescuento = ?, condicion = ?, porcentaje = ?, descripciondescuento = ?, status = ?";
    private final String DELETE = "UPDATE descuento SET status = 0 WHERE iddescuento = ?";
    private final String GETALL = "SELECT * FROM descuento WHERE status = 1";//solo obtiene los activos 
    private final String GETONE = "SELECT * FROM descuento WHERE iddescuento = ?";

    public descuentoSQL(Connection conn) {
        this.conexion = conn;
    }

    @Override
    public void insertar(descuento obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(descuento obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(descuento obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isActive(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<descuento> obtenertodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public descuento obtener(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
