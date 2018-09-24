package farmacia.jdbc.dao.mysql;

import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.empresaDAO;
import farmacia.jdbc.modelado.empresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class empresaSQL implements empresaDAO {

    private Connection conexion;

    private final String INSERT = "INSERT INTO empresa(rucempresa, razonsocial, telefono, direccion, status) "
            + "VALUES (?, ?, ?, ?, ?) ";
    private final String UPDATE = "UPDATE empresa SET rucempresa = ?, razonsocial = ?, telefono = ?, direccion = ?, status = ? WHERE idempresa = ?";
    private final String DELETE = "UPDATE empresa SET status = 0 WHERE idempresa = ?";
    private final String GETALL = "SELECT * FROM empresa WHERE status = 1";
    private final String GETONE = "SELECT * FROM empresa WHERE idempresa = ? AND status = 1";

    public empresaSQL(Connection conn) {
        this.conexion = conn;
    }

    @Override
    public Long insertar(empresa obj) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conexion.prepareStatement(INSERT);
            stat.setString(1, String.valueOf(obj.getRucempresa()));
            stat.setString(2, obj.getRazonsocial());
            stat.setString(3, obj.getTelefono());
            stat.setString(4, obj.getDireccion());
            stat.setBoolean(5, obj.isStatus());
            if(stat.executeUpdate() == 0){
                throw new DAOException("Error al ingresar un registro.");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()){
                obj.setIdempresa(rs.getLong(1));
            }else{
                throw new DAOException("Error al ingresar un registro. No se puede asignar ID.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilSQL.cerrar(stat,rs);
        }
        return obj.getIdempresa();
    }

    @Override
    public void modificar(empresa obj) throws DAOException {
        PreparedStatement stat = null;
        try{
            stat = conexion.prepareStatement(UPDATE);
            stat.setString(1, String.valueOf(obj.getRucempresa()));
            stat.setString(2, obj.getRazonsocial());
            stat.setString(3, obj.getTelefono());
            stat.setString(4, obj.getDireccion());
            stat.setBoolean(5, obj.isStatus());
            stat.setLong(6, obj.getIdempresa());
            if (stat.executeUpdate() == 0){
                throw new DAOException("Error al modificar un registro.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        }finally{
            UtilSQL.cerrar(stat);
        }
        
    }

    @Override
    public void eliminar(empresa obj) throws DAOException {
        PreparedStatement stat = null;
        try{
            stat = conexion.prepareStatement(DELETE);
            stat.setLong(1, obj.getIdempresa());
            if (stat.executeUpdate() == 0){
                throw new DAOException("Error al elimiar un registro.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.",ex);
        }finally{
            UtilSQL.cerrar(stat);
        }
    }

    @Override
    public List<empresa> obtenertodos() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<empresa> lista = new ArrayList<>();
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
    public empresa obtener(Long id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        empresa emp = null;
        try {
            stat = conexion.prepareStatement(GETONE);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                emp = convertir(rs);
            } else {
                throw new DAOException("No se ha encontrado registro");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilSQL.cerrar(stat, rs);
        }
        return emp;
    }

    @Override
    public empresa convertir(ResultSet rs) throws SQLException {
        empresa emp = null;
        String ruc = rs.getString("rucempresa");
        char[] rucempresa = ruc.toCharArray();
        String razonsocial = rs.getString("razonsocial");
        String telefoo = rs.getString("telefono");
        String direccion = rs.getString("telefono");
        emp = new empresa(rucempresa, razonsocial, telefoo, direccion);
        emp.setIdempresa(rs.getLong("idempresa"));
        emp.setStatus(rs.getBoolean("status"));
        return emp;
    }

}
