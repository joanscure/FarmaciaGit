package farmacia.hibernate.dao.implementacion;

import farmacia.jdbc.dao.mysql.*;
import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.facturadetalleDAO;
import farmacia.jdbc.modelado.boletadetalle;
import farmacia.jdbc.modelado.facturadetalle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class facturadetalleSQL implements facturadetalleDAO {

    private Connection conexion;

    private final String INSERT = "INSERT INTO facturadetalle(idfacturacabecera, idproducto, cantidad, subtotal, status) "
            + "VALUES (?, ?, ?, ?, ?) ";
    private final String UPDATE = "UPDATE facturadetalle SET idfacturacabecera = ?, idproducto = ?, cantidad = ?, subtotal = ?, status = ? "
            + "WHERE idfacturadetalle = ?";
    private final String DELETE = "UPDATE facturadetalle SET status = 0 WHERE idfacturadetalle = ?";
    private final String DELETEALL = "UPDATE facturadetalle SET status = 0 WHERE idfacturacabecera = ?";
    private final String GETALL = "SELECT * FROM facturadetalle WHERE status = 1";
    private final String GETONE = "SELECT * FROM facturadetalle WHERE idfacturadetalle = ?";
    private final String GETDEAILS = "SELECT * FROM facturadetalle WHERE idfacturacabecera = ?";

    public facturadetalleSQL(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public Long insertar(facturadetalle obj) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conexion.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

            stat.setLong(1, obj.getIdfacturacabecera());
            stat.setLong(2, obj.getIdproducto());
            stat.setDouble(3, obj.getCantidad());
            stat.setDouble(4, obj.getSubtotal());
            stat.setBoolean(5, (boolean) obj.isStatus());

            if (stat.executeUpdate() == 0) {
                throw new DAOException("Error al ingresar un registro.");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                obj.setIdfacturadetalle(rs.getLong(1));
            } else {
                throw new DAOException("Error al ingresar un registro. No se puede asignar ID.");
            }

        } catch (SQLException ex) {
            throw new DAOException("Error en sql.", ex);
        } finally {
            UtilIMPL.cerrar(stat, rs);
        }
        return obj.getIdfacturadetalle();
    }

    @Override
    public void modificar(facturadetalle obj) throws DAOException {
        PreparedStatement stat = null;

        try {
            stat = conexion.prepareStatement(UPDATE);

            stat.setLong(1, obj.getIdfacturacabecera());
            stat.setLong(2, obj.getIdproducto());
            stat.setDouble(3, obj.getCantidad());
            stat.setDouble(4, obj.getSubtotal());
            stat.setBoolean(5, (boolean) obj.isStatus());
            stat.setLong(6, obj.getIdfacturadetalle());
            
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Error al modificar un registro.");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilIMPL.cerrar(stat);
        }

    }

    @Override
    public void eliminar(facturadetalle obj) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conexion.prepareStatement(DELETE);
            stat.setLong(1, obj.getIdfacturadetalle());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Error al eliminar un registro.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilIMPL.cerrar(stat);
        }
    }

    @Override
    public List<facturadetalle> obtenertodos() throws DAOException {
        List<facturadetalle> lista = new ArrayList<>();
        PreparedStatement stat = null;
        ResultSet rs = null;
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
    public facturadetalle obtener(Long id) throws DAOException {
        facturadetalle f = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            stat = conexion.prepareStatement(GETONE);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                f = convertir(rs);
            } else {
                throw new DAOException("No se ha encontrado registro.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilIMPL.cerrar(stat, rs);
        }
        return f;
    }

    @Override
    public facturadetalle convertir(ResultSet rs) throws DAOException {
        facturadetalle f = null;
        try{
        Long idfacturacabecera = rs.getLong("idfacturacabecera");
        Long idproducto = rs.getLong("idproducto");
        double cantidad = rs.getDouble("cantidad");
        double subtotal = rs.getDouble("subtotal");
        f = new facturadetalle(idproducto, cantidad, subtotal);
        f.setIdfacturacabecera(idfacturacabecera);
        f.setIdfacturadetalle(rs.getLong("idboletadetalle"));
        f.setStatus(rs.getBoolean("status"));
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new DAOException("Error en SQL.", ex);
        }
        return f;
    }

    @Override
    public void eliminarDetallesFactura(Long idfacturacabecera) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conexion.prepareStatement(DELETEALL);
            stat.setLong(1, idfacturacabecera);
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Error al eliminar un registro.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilIMPL.cerrar(stat);
        }
    }

    @Override
    public List<facturadetalle> obtenerDetallesFactura(Long idfacturacabecera) throws DAOException {
        List<facturadetalle> lista = new ArrayList<>();
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conexion.prepareStatement(GETDEAILS);
            stat.setLong(1, idfacturacabecera);
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

}
