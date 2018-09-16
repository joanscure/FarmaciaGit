package farmacia.dao.mysql;

import farmacia.dao.personaclienteDAO;
import farmacia.modelado.personacliente;
import java.util.List;

public class personaclienteSQL implements personaclienteDAO{

    String INSERT = "INSERT INTO personacliente(idpersona, status) "+
    "VALUES (?, ?) ";
    String UPDATE = "UPDATE personacliente SET idpersona = ?, status = ?";
    String DELETE = "UPDATE personacliente SET status = 0 WHERE idpersonacliente = ?";
    String GETALL = "SELECT * FROM personacliente WHERE status = 1";
    String GETONE = "SELECT * FROM personacliente WHERE idpersonacliente = ?";

    @Override
    public void insertar(personacliente obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(personacliente obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(personacliente obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isActive(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<personacliente> obtenertodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public personacliente obtener(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long estaRelacionado(Long id, Long id2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
