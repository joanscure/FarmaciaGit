package farmacia.hibernate.dao.implementacion;

import farmacia.jdbc.dao.mysql.*;
import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.productodescuentoDAO;
import farmacia.jdbc.modelado.descuento;
import farmacia.jdbc.modelado.producto;
import farmacia.jdbc.modelado.productodescuento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class productodescuentoSQL implements productodescuentoDAO {

    private Connection conexion;

    private final String INSERT = "INSERT INTO productodescuento(idproducto, iddescuento, status) "
            + "VALUES (?, ?, ?) ";
    private final String UPDATE = "UPDATE productodescuento SET idproducto = ?, iddescuento = ? , status = ? WHERE idproductodescuento = ?";
    private final String DELETE = "UPDATE productodescuento SET status = 0 WHERE idproductodescuento = ?";
    private final String GETALL = "SELECT * FROM productodescuento WHERE status = 1";
    private final String GETONE = "SELECT * FROM productodescuento WHERE idproductodescuento = ?";

    public productodescuentoSQL(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public Long insertar(productodescuento obj) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conexion.prepareStatement(INSERT);
            stat.setLong(1, obj.getIdproducto());
            stat.setLong(2, obj.getIddescuento());
            stat.setBoolean(3, obj.isStatus());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Error al ingresar un registro.");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                obj.setIdproductodescuento(rs.getLong(1));
            } else {
                throw new DAOException("Error al ingresar un registro. No se puede asignar ID.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilIMPL.cerrar(stat, rs);
        }
        return obj.getIdproductodescuento();
    }

    @Override
    public void modificar(productodescuento obj) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conexion.prepareStatement(UPDATE);
            stat.setLong(1, obj.getIdproducto());
            stat.setLong(2, obj.getIddescuento());
            stat.setBoolean(3, obj.isStatus());
            stat.setLong(4, obj.getIdproductodescuento());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Error al modificar un registro.");
            }

        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilIMPL.cerrar(stat);
        }
    }

    @Override
    public void eliminar(productodescuento obj) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conexion.prepareStatement(DELETE);
            stat.setLong(1, obj.getIdproductodescuento());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Error al eliminar registro.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilIMPL.cerrar(stat);
        }
    }

    @Override
    public List<productodescuento> obtenertodos() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<productodescuento> lista = new ArrayList<>();
        try {
            stat = conexion.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                lista.add(convertir(rs));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilIMPL.cerrar(stat, rs);
        }
        return lista;
    }

    @Override
    public productodescuento obtener(Long id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        productodescuento pro = null;
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
            UtilIMPL.cerrar(stat, rs);
        }
        return pro;

    }

    @Override
    public productodescuento convertir(ResultSet rs) throws DAOException {
        productodescuento prodes = null;
        try{
        Long idproductodescuento = rs.getLong("idproductodescuento");//PK
        Long idproducto = rs.getLong("idproducto");//indice
        Long iddescuento = rs.getLong("iddescuento"); //indice
        boolean status = rs.getBoolean("status");
        prodes = new productodescuento(idproducto, iddescuento);
        prodes.setIdproductodescuento(idproductodescuento);
        prodes.setStatus(status);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new DAOException("Error en SQL.", ex);
        }
        return prodes;
    }

}
