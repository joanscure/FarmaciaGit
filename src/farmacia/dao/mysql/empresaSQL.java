package farmacia.dao.mysql;

import farmacia.dao.empresaDAO;
import farmacia.modelado.empresa;
import java.util.List;

public class empresaSQL implements empresaDAO{


    final String INSERT = "INSERT INTO empresa(idempresa, rucempresa, razonsocial, telefono, direccion, status) VALUES (?, ?, ?, ?, ?, ?) ";
    final String UPDATE = "UPDATE empresa SET rucempresa = ?, razonsocial = ?, telefono = ?. direccion = ?, status = ? WHERE idempresa = ?";
    final String DELETE = "UPDATE empresa SET status = 0 WHERE idempresa = ?";
    final String GETALL = "SELECT * FROM empresa WHERE status = 1";
    final String GETONE = "SELECT * FROM empresa WHERE idempresa = ?";

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
    public Long estaRelacionado(Long id, Long id2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
