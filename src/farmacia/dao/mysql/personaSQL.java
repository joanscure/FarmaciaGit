package farmacia.dao.mysql;

import farmacia.dao.personaDAO;
import farmacia.modelado.persona;
import java.util.List;

public class personaSQL implements personaDAO{

    String INSERT = "INSERT INTO persona(nombre, appaterno, apmaterno, numerodni, personaedad, direccion, telefono, status) "+
    "VALUES (?, ?) ";
    String UPDATE = "UPDATE persona SET nombre = ?, appaterno = ?, apmaterno = ?, numerodni = ?, personaedad = ?, direccion = ?, telefono = ?, status = ?";
    String DELETE = "UPDATE persona SET status = 0 WHERE idpersona = ?";
    String GETALL = "SELECT * FROM persona WHERE status = 1";
    String GETONE = "SELECT * FROM persona WHERE idpersona = ?";


    @Override
    public void insertar(persona obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(persona obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(persona obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isActive(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<persona> obtenertodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public persona obtener(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long estaRelacionado(Long id, Long id2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
