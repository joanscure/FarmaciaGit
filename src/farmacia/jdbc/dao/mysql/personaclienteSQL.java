package farmacia.jdbc.dao.mysql;

import farmacia.jdbc.dao.personaclienteDAO;
import farmacia.jdbc.modelado.personacliente;
import java.sql.Connection;
import java.util.List;

public class personaclienteSQL implements personaclienteDAO{

    private Connection conexion;
    
    private final String INSERT = "INSERT INTO personacliente(idpersona, status) "+
    "VALUES (?, ?) ";
    private final String UPDATE = "UPDATE personacliente SET idpersona = ?, status = ?";
    private final String DELETE = "UPDATE personacliente SET status = 0 WHERE idpersonacliente = ?";
    private final String GETALL = "SELECT * FROM personacliente WHERE status = 1";
    private final String GETONE = "SELECT * FROM personacliente WHERE idpersonacliente = ?";

    public personaclienteSQL(Connection conexion) {
        this.conexion = conexion;
    }

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


    
}
