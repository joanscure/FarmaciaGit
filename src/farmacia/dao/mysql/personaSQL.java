package farmacia.dao.mysql;

import farmacia.dao.personaDAO;
import farmacia.modelado.persona;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class personaSQL implements personaDAO {

    String INSERT = "INSERT INTO persona(nombre, appaterno, apmaterno, numerodni, personaedad, direccion, telefono, status) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
    String UPDATE = "UPDATE persona SET nombre = ?, appaterno = ?, apmaterno = ?, numerodni = ?, personaedad = ?, direccion = ?, telefono = ?, status = ?";
    String DELETE = "UPDATE persona SET status = 0 WHERE idpersona = ?";
    String GETALL = "SELECT * FROM persona WHERE status = 1";
    String GETONE = "SELECT * FROM persona WHERE idpersona = ? AND status = 1";

    
    @Override
    public void insertar(persona obj) {
        PreparedStatement persona = null;
        conexionSQL conexion = new conexionSQL();
        conexion.conectar();
        
        try {
            persona = conexion.prepareStatement(INSERT);
            
            persona.setString(1, (String) obj.getNombre());
            persona.setString(2, (String) obj.getAppaterno());
            persona.setString(3, (String) obj.getApmaterno());
            persona.setString(4, (String) obj.getNumerodni().toString());
            persona.setInt(5, obj.getPersonaedad());
            persona.setString(6, (String) obj.getDireccion());
            persona.setString(7, (String) obj.getTelefono());
            persona.setBoolean(8, (boolean)obj.isStatus());
            persona.executeUpdate();

            conexion.commit();
        } catch (SQLException ex) {
            System.out.println("algo salio mal" + ex.getMessage());
            conexion.rollback();
        } finally {
            if (persona != null) {
                persona.close();
            }
            conexion.cerrarConexion(conexion);

        }
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
