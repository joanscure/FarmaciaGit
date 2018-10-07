package farmacia.jdbc.dao.mysql;

import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.personaclienteDAO;
import farmacia.jdbc.modelado.persona;
import farmacia.jdbc.modelado.personacliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class personaclienteSQL implements personaclienteDAO{

    private Connection conexion;
    
    private final String INSERT = "INSERT INTO personacliente(idpersona, status) "+
    "VALUES (?, ?) ";
    private final String UPDATE = "UPDATE personacliente SET idpersona = ? WHERE idpersonacliente = ?";
    private final String DELETE = "UPDATE personacliente SET status = 0 WHERE idpersonacliente = ?";
    private final String GETALL = "SELECT * FROM personacliente WHERE status = 1";
    private final String GETONE = "SELECT * FROM personacliente WHERE idpersonacliente = ?";

    public personaclienteSQL(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public Long insertar(personacliente obj) throws DAOException {  
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conexion.prepareStatement(INSERT,PreparedStatement.RETURN_GENERATED_KEYS);
           
            stat.setLong(1, obj.getIdpersona());
            stat.setBoolean(2, obj.isStatus());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Error al ingresar un registro.");
            }
           
            rs = stat.getGeneratedKeys();
            if(rs.next()){
               obj.setIdpersonacliente(rs.getLong(1));
            }else{
                throw new DAOException("Error al ingresar un registro. No se puede asignar ID.");
            }

        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilSQL.cerrar(stat, rs);
        }
        return obj.getIdpersonacliente();    
        
    }

    @Override
    public void modificar(personacliente obj) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conexion.prepareStatement(UPDATE);
           
            stat.setLong(1, obj.getIdpersona());
            
            stat.setLong(2, obj.getIdpersonacliente());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Error al modificar un registro.");
            }
           
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilSQL.cerrar(stat);
        }
    }

    @Override
    public void eliminar(personacliente obj) throws DAOException {
         PreparedStatement stat = null;
        try {
            stat = conexion.prepareStatement(DELETE);
            stat.setLong(1, obj.getIdpersonacliente());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Error al eliminar un registro.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilSQL.cerrar(stat);
        }
    }


    @Override
    public List<personacliente> obtenertodos() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<personacliente> lista = new ArrayList<>();
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
    public personacliente obtener(Long id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        personacliente p = null;
        try {
            stat = conexion.prepareStatement(GETONE);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                p = convertir(rs);
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilSQL.cerrar(stat, rs);
        }
        return p;
    }

    @Override
    public personacliente convertir(ResultSet rs) throws DAOException {
        personacliente per = null;
        try{
        Long idpersonacliente = rs.getLong("idpersonacliente");
        Long idpersona = rs.getLong("idpersona");
        boolean status = rs.getBoolean("status");
        per = new personacliente(idpersona);
        per.setStatus(status);
        per.setIdpersonacliente(idpersonacliente);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new DAOException("Error en SQL.", ex);
        }
        return per;
    }

    @Override
    public void ingresarNuevo(personacliente cliente, persona per) throws DAOException {
        try{
            conexion.setAutoCommit(false);
            personaSQL perSQL = new personaSQL(conexion);
            cliente.setIdpersona(perSQL.insertar(per));
            insertar(cliente);
            conexion.commit();
        } catch (SQLException ex) {
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                throw new DAOException("Error en transaccion.", ex1);
            }
            throw new DAOException("Error en SQL.", ex);
        } 
    }


    
}
