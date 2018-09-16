package farmacia.dao.mysql;

import farmacia.dao.facturadetalleDAO;
import farmacia.modelado.facturadetalle;
import java.util.List;

public class facturadetalleSQL implements facturadetalleDAO{

    String INSERT = "INSERT INTO facturadetalle(idfacturacabecera, idproducto, cantidad, subtotal, status) "+
    "VALUES (?, ?, ?, ?, ?) ";
    String UPDATE = "UPDATE facturadetalle SET idfacturacabecera = ?, idproducto = ?, cantidad = ?, subtotal = ?, status = ?";
    String DELETE = "UPDATE facturadetalle SET status = 0 WHERE idfacturadetalle = ?";
    String GETALL = "SELECT * FROM facturadetalle WHERE status = 1";
    String GETONE = "SELECT * FROM facturadetalle WHERE idfacturadetalle = ?";

    @Override
    public void insertar(facturadetalle obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(facturadetalle obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(facturadetalle obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isActive(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<facturadetalle> obtenertodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public facturadetalle obtener(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long estaRelacionado(Long id, Long id2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
