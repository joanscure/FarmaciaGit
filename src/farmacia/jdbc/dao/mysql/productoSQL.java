package farmacia.jdbc.dao.mysql;

import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.productoDAO;
import farmacia.jdbc.modelado.producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class productoSQL implements productoDAO {

    private Connection conexion;

    private final String INSERT = "INSERT INTO producto(nombreproducto, descripcionproducto, dosisproducto, precioventa, igv, preciofinal, stock, status) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
    private final String UPDATE = "UPDATE producto SET nombreproducto = ?, descripcionproducto = ?, dosisproducto = ?, precioventa = ?, igv = ?, preciofinal = ?, stock = ?, status = ? "
            + "WHERE idproducto = ?";
    private final String DELETE = "UPDATE producto SET status = 0 WHERE idproducto = ?";
    private final String GETALL = "SELECT * FROM producto WHERE status = 1";
    private final String GETONE = "SELECT * FROM producto WHERE idproducto = ? AND status = 1";

    public productoSQL(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public Long insertar(producto obj) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            
            stat = conexion.prepareStatement(INSERT,PreparedStatement.RETURN_GENERATED_KEYS );
            stat.setString(1, obj.getNombreproducto());
            stat.setString(2, obj.getDescripcionproducto());
            stat.setString(3, obj.getDosisproducto());
            stat.setDouble(4, obj.getPreciofinal());
            stat.setDouble(5, obj.getIgv());
            stat.setDouble(6, obj.getPreciofinal());
            stat.setInt(7, obj.getStock());
            stat.setBoolean(8, obj.isStatus());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Error al ingresar un registro.");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                obj.setIdproducto(rs.getLong(1));
            } else {
                throw new DAOException("Error al ingresar un registro. No se puede asignar ID.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL."+ex.getMessage());
        } finally {
            UtilSQL.cerrar(stat, rs);
        }
        return obj.getIdproducto();
    }

    @Override
    public void modificar(producto obj) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conexion.prepareStatement(UPDATE);
            stat.setString(1, obj.getNombreproducto());
            stat.setString(2, obj.getDescripcionproducto());
            stat.setString(3, obj.getDosisproducto());
            stat.setDouble(4, obj.getPreciofinal());
            stat.setDouble(5, obj.getIgv());
            stat.setDouble(6, obj.getPreciofinal());
            stat.setInt(7, obj.getStock());
            stat.setBoolean(8, obj.isStatus());
            stat.setLong(9, obj.getIdproducto());
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
    public void eliminar(producto obj) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conexion.prepareStatement(DELETE);
            stat.setLong(1, obj.getIdproducto());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Error al eliminar registro.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilSQL.cerrar(stat);
        }
    }

    @Override
    public List<producto> obtenertodos() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<producto> lista = new ArrayList<>();
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
    public producto obtener(Long id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        producto pro = null;
        try {
            stat = conexion.prepareStatement(GETONE);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                pro = convertir(rs);
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilSQL.cerrar(stat, rs);
        }
        return pro;

    }

    @Override
    public producto convertir(ResultSet rs) throws DAOException {
        producto pro = null;
        try{
        String nombreproducto = rs.getString("nombreproducto");
        String descipcionproducto = rs.getString("descripcionproducto");
        String dosisproducto = rs.getString("dosisproducto");
        double precioventa = rs.getDouble("precioventa");
        double igv = rs.getDouble("igv");
        double preciofinal = rs.getDouble("preciofinal");
        int stock = rs.getInt("stock");
        pro = new producto(nombreproducto, descipcionproducto, dosisproducto, precioventa, igv, preciofinal, stock);
        pro.setIdproducto(rs.getLong("idproducto"));
        pro.setStatus(rs.getBoolean("status"));
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new DAOException("Error en SQL.", ex);
        }
        return pro;

    }

}
