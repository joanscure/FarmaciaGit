package farmacia.dao.mysql;

import farmacia.dao.empresaclienteDAO;
import farmacia.modelado.empresacliente;
import java.util.List;

public class empresaclienteSQL implements empresaclienteDAO{

    String INSERT = "INSERT INTO empresacliente(idempresa, fecharegistro, status) "
    +"VALUES (?, ?, ?, ?, ?, ?) ";
    String UPDATE = "UPDATE empresacliente SET idempresa = ?, fecharegistro = ?, status = ?";
    String DELETE = "UPDATE empresacliente SET status = 0 WHERE idempresacliente = ?";
    String GETALL = "SELECT * FROM empresacliente WHERE status = 1";//solo obtiene los activos 
    String GETONE = "SELECT * FROM empresacliente WHERE idempresacliente = ?";

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
    public boolean isActive(Long id) {
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

    @Override
    public Long estaRelacionado(Long id, Long id2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
