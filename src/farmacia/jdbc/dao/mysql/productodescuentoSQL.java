package farmacia.jdbc.dao.mysql;

import farmacia.jdbc.dao.productodescuentoDAO;
import farmacia.jdbc.modelado.descuento;
import farmacia.jdbc.modelado.producto;
import farmacia.jdbc.modelado.productodescuento;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class productodescuentoSQL implements productodescuentoDAO {

    private Connection conexion;

    private final String INSERT = "INSERT INTO productodescuento(idproducto, iddescuento, status) "
            + "VALUES (?, ?, ?) ";
    private final String UPDATE = "UPDATE productodescuento SET idproducto = ?, iddescuento = ? , status = ?";
    private final String DELETE = "UPDATE productodescuento SET status = 0 WHERE idproductodescuento = ?";
    private final String GETALL = "SELECT * FROM productodescuento WHERE status = 1";
    private final String GETONE = "SELECT * FROM productodescuento WHERE idproductodescuento = ?";

    public productodescuentoSQL(Connection conexion) {
        this.conexion = conexion;
    }

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
    public List<productodescuento> obtenertodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public productodescuento obtener(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public productodescuento convertir(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
