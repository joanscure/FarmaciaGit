package farmacia.dao.mysql;

import farmacia.dao.descuentoDAO;
import farmacia.modelado.descuento;
import java.util.List;

public class descuentoSQL implements descuentoDAO{

    String INSERT = "INSERT INTO descuento(nombredescuento, condicion, porcentaje, descripciondescuento, status) "
    +"VALUES (?, ?, ?, ?, ?) ";
    String UPDATE = "UPDATE descuento SET nombredescuento = ?, condicion = ?, porcentaje = ?, descripciondescuento = ?, status = ?";
    String DELETE = "UPDATE descuento SET status = 0 WHERE iddescuento = ?";
    String GETALL = "SELECT * FROM descuento WHERE status = 1";//solo obtiene los activos 
    String GETONE = "SELECT * FROM descuento WHERE iddescuento = ?";



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

    @Override
    public Long estaRelacionado(Long id, Long id2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
