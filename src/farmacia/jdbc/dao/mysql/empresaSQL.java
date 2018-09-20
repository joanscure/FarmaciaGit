package farmacia.jdbc.dao.mysql;

import farmacia.jdbc.dao.empresaDAO;
import farmacia.jdbc.modelado.empresa;
import java.sql.Connection;
import java.util.List;

public class empresaSQL implements empresaDAO {

    private Connection conexion;

    private final String INSERT = "INSERT INTO empresa(idempresa, rucempresa, razonsocial, telefono, direccion, status) VALUES (?, ?, ?, ?, ?, ?) ";
    private final String UPDATE = "UPDATE empresa SET rucempresa = ?, razonsocial = ?, telefono = ?. direccion = ?, status = ? WHERE idempresa = ?";
    private final String DELETE = "UPDATE empresa SET status = 0 WHERE idempresa = ?";
    private final String GETALL = "SELECT * FROM empresa WHERE status = 1";
    private final String GETONE = "SELECT * FROM empresa WHERE idempresa = ?";

    public empresaSQL(Connection conn) {
        this.conexion = conn;
    }

    @Override
    public void insertar(empresa obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(empresa obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(empresa obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isActive(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<empresa> obtenertodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public empresa obtener(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<empresa> buscarPorRuc(String ruc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<empresa> buscarPorRazonSocial(String razonsocial) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
