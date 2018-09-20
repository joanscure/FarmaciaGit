package farmacia.jdbc.dao.mysql;

import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.personaDAO;
import farmacia.jdbc.modelado.persona;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class personaSQL implements personaDAO {
    
    private Connection conexion;
    
    private final String INSERT = "INSERT INTO persona(nombre, appaterno, apmaterno, numerodni, personaedad, direccion, telefono, status) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
    private final String UPDATE = "UPDATE persona SET nombre = ?, appaterno = ?, apmaterno = ?, numerodni = ?, personaedad = ?, direccion = ?, telefono = ?, status = ?";
    private final String DELETE = "UPDATE persona SET status = 0 WHERE idpersona = ?";
    private final String GETALL = "SELECT * FROM persona WHERE status = 1";
    private final String GETONE = "SELECT * FROM persona WHERE idpersona = ? AND status = 1";
    
    public personaSQL(Connection conexion) {
        this.conexion = conexion;
    }
    
    @Override
    public persona convertir(ResultSet rs) throws SQLException {
        persona p = null;
        String nombre = rs.getString("nombre");
        String appaterno = rs.getString("appaterno");
        String apmaterno = rs.getString("apmaterno");
        String dni = rs.getString("numerodni");
        char[] numerodni = dni.toCharArray();
        int personaedad = rs.getInt("personaedad");
        String direccion = rs.getString("direccion");
        String telefono = rs.getString("telefono");
        p = new persona(nombre, appaterno, apmaterno, numerodni, personaedad, direccion, telefono);
        p.setIdPersona(rs.getLong("idpersona"));
        p.setStatus(rs.getBoolean("status"));
        return p;
    }
    
    @Override
    public void insertar(persona obj) throws DAOException {
        
        PreparedStatement stat = null;
        
        try {
            stat = conexion.prepareStatement(INSERT);
            stat.setString(1, (String) obj.getNombre());
            stat.setString(2, (String) obj.getAppaterno());
            stat.setString(3, (String) obj.getApmaterno());
            stat.setString(4, String.valueOf(obj.getNumerodni()));
            stat.setInt(5, obj.getPersonaedad());
            stat.setString(6, (String) obj.getDireccion());
            stat.setString(7, (String) obj.getTelefono());
            stat.setBoolean(8, obj.isStatus());
            
            int resultado = stat.executeUpdate();
            
            if (resultado == 0) {
                throw new DAOException("Error al ingresar dato.");
            }
            
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilSQL.cerrar(stat);
        }
    }
    
    @Override
    public void modificar(persona obj) throws DAOException {

        PreparedStatement stat = null;
        try{
            stat = conexion.prepareStatement(UPDATE);
            stat.setString(1, (String) obj.getNombre());
            stat.setString(2, (String) obj.getAppaterno());
            stat.setString(3, (String) obj.getApmaterno());
            stat.setString(4, String.valueOf(obj.getNumerodni()));
            stat.setInt(5, obj.getPersonaedad());
            stat.setString(6, (String) obj.getDireccion());
            stat.setString(7, (String) obj.getTelefono());
            stat.setBoolean(8, obj.isStatus());
            if (stat.executeUpdate() == 0){
                throw new DAOException("Error al ingresar registro.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        }finally{
            UtilSQL.cerrar(stat);
        }
    }
    
    @Override
    public void eliminar(persona obj) throws DAOException{
        PreparedStatement stat = null;
        try{
            stat = conexion.prepareStatement(DELETE);
            stat.setLong(1, obj.getIdPersona());
            if(stat.executeUpdate() == 0){
                throw new DAOException("Error al eliminar registro");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        }finally{
            UtilSQL.cerrar(stat);
        }
    }
    
    
    @Override
    public List<persona> obtenertodos() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<persona> lista = new ArrayList<>();
        try {
            stat = conexion.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                lista.add(convertir(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilSQL.cerrar(stat, rs);
        }
        return lista;
    }
    
    @Override
    public persona obtener(Long id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        persona p = null;
        try {
            stat = conexion.prepareStatement(GETONE);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                p = convertir(rs);
            } else {
                throw new DAOException("No se ha encontrado registro.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilSQL.cerrar(stat, rs);
        }
        return p;
    }
    
    public static void main(String[] args) {
        DAOManagerSQL man = null;
        try {
            man = new DAOManagerSQL("localhost", "basefarmacia", "root", "");
            persona p;
            String nombre = "Manuel";
            String appaterno = "Perez";
            String apmaterno = "Ramirez";
            String dni = "12345678";
            char[] numerodni = dni.toCharArray();
            int personaedad = 13;
            String direccion = "Calle los robles 123";
            String telefono = "323231231";
            p = new persona(nombre, appaterno, apmaterno, numerodni, personaedad, direccion, telefono);
            man.getPersonaDAO().insertar(p);
            
        } catch (SQLException ex) {
            System.out.println("Error en SQL." + ex.getMessage());
        } catch (DAOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                man.cerrarConexion();
            } catch (DAOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
    }
    
}
