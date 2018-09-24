package farmacia.jdbc.dao.mysql;

import farmacia.jdbc.dao.facturacabeceraDAO;
import farmacia.jdbc.modelado.facturacabecera;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class facturacabeceraSQL implements facturacabeceraDAO{

    private Connection conexion;

    private final String INSERT = "INSERT INTO facturacabecera(correlativofactura, numerofactura, fechaemisionfactura, idpersonacliente, idempleado, status) "
            + "VALUES (?, ?, ?, ?, ?, ?) ";
    private final String UPDATE = "UPDATE facturacabecera SET correlativofactura = ?, numerofactura = ?, fechaemisionfactura = ?, idpersonacliente = ?, idempleado = ?, status = ?";
    private final String DELETE = "UPDATE facturacabecera SET status = 0 WHERE idfacturacabecera = ?";
    private final String GETALL = "SELECT * FROM facturacabecera WHERE status = 1";//solo obtiene los activos 
    private final String GETONE = "SELECT * FROM facturacabecera WHERE idfacturacabecera = ?";

    public facturacabeceraSQL(Connection conexion) {
        this.conexion = conexion;
    }
    @Override
    public Long insertar(facturacabecera obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(facturacabecera obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(facturacabecera obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<facturacabecera> obtenertodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public facturacabecera obtener(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public facturacabecera convertir(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }




    
}
