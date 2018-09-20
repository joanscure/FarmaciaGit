package farmacia.jdbc.dao.mysql;

import farmacia.jdbc.dao.productoDAO;
import farmacia.jdbc.modelado.producto;
import java.sql.Connection;
import java.util.List;

public class productoSQL implements productoDAO {

    private Connection conexion;

    private final String INSERT = "INSERT INTO producto(nombreproducto, descripcionproducto, dosisproducto, precioventa, igv, preciofinal, stock, status) "
            + "VALUES (?, ?) ";
    private final String UPDATE = "UPDATE producto SET nombreproducto = ?, descripcionproducto = ?, dosisproducto = ?, precioventa = ?, igv = ?, preciofinal = ?, stock = ?, status = ?";
    private final String DELETE = "UPDATE producto SET status = 0 WHERE idpersonacliente = ?";
    private final String GETALL = "SELECT * FROM producto WHERE status = 1";
    private final String GETONE = "SELECT * FROM producto WHERE idproducto = ?";

    public productoSQL(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void insertar(producto obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(producto obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(producto obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isActive(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<producto> obtenertodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public producto obtener(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
