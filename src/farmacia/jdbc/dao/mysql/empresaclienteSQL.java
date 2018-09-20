package farmacia.jdbc.dao.mysql;

import farmacia.jdbc.dao.empresaclienteDAO;
import farmacia.jdbc.modelado.empresacliente;
import java.sql.Connection;
import java.util.List;

public class empresaclienteSQL implements empresaclienteDAO {

    private Connection conexion;

    private final String INSERT = "INSERT INTO empresacliente(idempresa, fecharegistro, status) "
            + "VALUES (?, ?, ?, ?, ?, ?) ";
    private final String UPDATE = "UPDATE empresacliente SET idempresa = ?, fecharegistro = ?, status = ?";
    private final String DELETE = "UPDATE empresacliente SET status = 0 WHERE idempresacliente = ?";
    private final String GETALL = "SELECT * FROM empresacliente WHERE status = 1";//solo obtiene los activos 
    private final String GETONE = "SELECT * FROM empresacliente WHERE idempresacliente = ?";

    public empresaclienteSQL(Connection conn) {
        this.conexion = conn;
    }
    
    @Override
    public void insertar(empresacliente obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(empresacliente obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(empresacliente obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<empresacliente> obtenertodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public empresacliente obtener(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
