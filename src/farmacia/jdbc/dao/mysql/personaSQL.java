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
    
    
    public persona convertir(ResultSet rs) throws SQLException{
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
    
        PreparedStatement persona = null;
        
        try {
            persona = conexion.prepareStatement(INSERT);
            persona.setString(1, (String) obj.getNombre());
            persona.setString(2, (String) obj.getAppaterno());
            persona.setString(3, (String) obj.getApmaterno());
            persona.setString(4, String.valueOf(obj.getNumerodni()));
            persona.setInt(5, obj.getPersonaedad());
            persona.setString(6, (String) obj.getDireccion());
            persona.setString(7, (String) obj.getTelefono());
            persona.setBoolean(8, (boolean)obj.isStatus());
            
            int resultado = persona.executeUpdate();
            
            if (resultado == 0){
                throw new DAOException("Error al ingresar dato.");
            }

        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            if (persona != null) {
                try {
                    persona.close();
                } catch (SQLException ex) {
                    throw new DAOException("Error en SQL.", ex);
                }
            }
       }
    }

    @Override
    public void modificar(persona obj) {

        PreparedStatement persona = null;
            
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
    public List<persona> obtenertodos() throws DAOException{
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<persona> lista = new ArrayList<>();
        try{
            stat = conexion.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while(rs.next()){
                lista.add(convertir(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.",ex);
        }finally{
            if (stat != null){
                try{
                    stat.close();
                }catch (SQLException ex){
                    throw new DAOException("Error en SQL.", ex);
                }
            }
            if (rs != null){
                try{
                    rs.close();
                }catch (SQLException ex){
                    throw new DAOException("Error en SQL.", ex);
                }
            }
        }
        return lista;
    }

    @Override
    public persona obtener(Long id) throws DAOException{
        PreparedStatement stat = null;
        ResultSet rs = null;
        persona p = null;
        try{
            stat = conexion.prepareStatement(GETONE);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            if (rs.next()){
                p = convertir(rs);
            }else{
                throw new DAOException("No se ha encontrado registro.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.",ex);
        }finally{
            if (stat != null){
                try{
                    stat.close();
                }catch (SQLException ex){
                    throw new DAOException("Error en SQL.", ex);
                }
            }
            if (rs != null){
                try{
                    rs.close();
                }catch (SQLException ex){
                    throw new DAOException("Error en SQL.", ex);
                }
            }
        }
        return p;
    }


    
    public static void main(String[] args) throws SQLException, DAOException {
        Connection conn = null;
        
        try{
            /*int i = 0;            
            do{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/basefarmacia", "root", "");
            personaDAO dao = new personaSQL(conn);
            //-----------------------
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
            //---------------------------
            dao.insertar(p);
            }while(i++<6);
            */
            conn = DriverManager.getConnection("jdbc:mysql://localhost/basefarmacia", "root", "");
            personaDAO dao = new personaSQL(conn);
            List personas = dao.obtenertodos();
            for (Object p : personas) {
                System.out.println(p.toString());
            }
            
        }finally{
            if (conn != null){
                conn.close();
            }
        }
        
        
       
        
    }
}
