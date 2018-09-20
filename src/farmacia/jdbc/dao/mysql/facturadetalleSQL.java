package farmacia.jdbc.dao.mysql;

import farmacia.jdbc.dao.facturadetalleDAO;
import farmacia.jdbc.modelado.facturadetalle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class facturadetalleSQL implements facturadetalleDAO {

    private Connection conexion;

    private final String INSERT = "INSERT INTO facturadetalle(idfacturacabecera, idproducto, cantidad, subtotal, status) "
            + "VALUES (?, ?, ?, ?, ?) ";
    private final String UPDATE = "UPDATE facturadetalle SET idfacturacabecera = ?, idproducto = ?, cantidad = ?, subtotal = ?, status = ?";
    private final String DELETE = "UPDATE facturadetalle SET status = 0 WHERE idfacturadetalle = ?";
    private final String GETALL = "SELECT * FROM facturadetalle WHERE status = 1";
    private final String GETONE = "SELECT * FROM facturadetalle WHERE idfacturadetalle = ?";

    public facturadetalleSQL(Connection conexion) {
        this.conexion = conexion;
    }
    
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
    public List<facturadetalle> obtenertodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public facturadetalle obtener(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public facturadetalle convertir(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
