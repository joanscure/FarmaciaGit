package farmacia.dao.mysql;

import farmacia.dao.productodescuentoDAO;
import farmacia.modelado.productodescuento;
import java.util.List;

public class productodescuentoSQL implements productodescuentoDAO{


    String INSERT = "INSERT INTO productodescuento(idproducto, iddescuento, status) "+
    "VALUES (?, ?, ?) ";
    String UPDATE = "UPDATE productodescuento SET idproducto = ?, iddescuento = ? , status = ?";
    String DELETE = "UPDATE productodescuento SET status = 0 WHERE idproductodescuento = ?";
    String GETALL = "SELECT * FROM productodescuento WHERE status = 1";
    String GETONE = "SELECT * FROM productodescuento WHERE idproductodescuento = ?";

    @Override
    public void insertar(productodescuento obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(productodescuento obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(productodescuento obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isActive(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<productodescuento> obtenertodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public productodescuento obtener(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long estaRelacionado(Long id, Long id2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
