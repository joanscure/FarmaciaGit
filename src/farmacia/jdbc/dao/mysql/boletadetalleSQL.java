package farmacia.jdbc.dao.mysql;

import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.boletadetalleDAO;
import farmacia.jdbc.modelado.boletadetalle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class boletadetalleSQL implements boletadetalleDAO {

    private Connection conexion;

    private final String INSERT = "INSERT INTO boletadetalle(idboletacabecera, idproducto, cantidad, subtotal, status) "
            + "VALUES (?, ?, ?, ?, ?) ";
    private final String UPDATE = "UPDATE boletadetalle SET idboletacabecera = ?, idproducto = ?, cantidad = ?, subtotal = ?, status = ? "
            + "WHERE idboletadetalle = ?";
    private final String DELETE = "UPDATE boletadetalle SET status = 0 WHERE idboletadelle = ?";
    private final String DELETEALL = "UPDATE boletadetalle SET status = 0 WHERE idboletacabecera = ?";
    private final String GETALL = "SELECT * FROM boletadetalle WHERE status = 1";//solo obtiene los activos 
    private final String GETONE = "SELECT * FROM boletadetalle WHERE idboletadetalle = ? AND status = 1";
    private final String GETDETAILS = "SELECT * FROM boletadetalle WHERE idboletacabecera = ? AND status = 1";

    public boletadetalleSQL(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public Long insertar(boletadetalle obj) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conexion.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

            stat.setLong(1, obj.getIdboletacabecera());
            stat.setLong(2, obj.getIdproducto());
            stat.setDouble(3, obj.getCantidad());
            stat.setDouble(4, obj.getSubtotal());
            stat.setBoolean(5, (boolean) obj.isStatus());

            if (stat.executeUpdate() == 0) {
                throw new DAOException("Error al ingresar un registro.");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                obj.setIdboletadetalle(rs.getLong(1));
            } else {
                throw new DAOException("Error al ingresar un registro. No se puede asignar ID.");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            UtilSQL.cerrar(stat, rs);
        }
        return obj.getIdboletadetalle();
    }

    @Override
    public void modificar(boletadetalle obj) throws DAOException {
        PreparedStatement stat = null;

        try {
            stat = conexion.prepareStatement(UPDATE);

            stat.setLong(1, obj.getIdboletacabecera());
            stat.setLong(2, obj.getIdproducto());
            stat.setDouble(3, obj.getCantidad());
            stat.setDouble(4, obj.getSubtotal());
            stat.setBoolean(5, (boolean) obj.isStatus());
            stat.setLong(6, obj.getIdboletadetalle());
            
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Error al modificar un registro.");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilSQL.cerrar(stat);
        }
    }

    @Override
    public void eliminar(boletadetalle obj) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conexion.prepareStatement(DELETE);
            stat.setLong(1, obj.getIdboletacabecera());
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
    public List<boletadetalle> obtenertodos() throws DAOException {
        List<boletadetalle> lista = new ArrayList<>();
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
            UtilSQL.cerrar(stat, rs);
        }
        return lista;
    }

    @Override
    public boletadetalle obtener(Long id) throws DAOException {

        boletadetalle b = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            stat = conexion.prepareStatement(GETONE);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                b = convertir(rs);
            } else {
                throw new DAOException("No se ha encontrado registro.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilSQL.cerrar(stat, rs);
        }
        return b;
    }

    @Override
    public boletadetalle convertir(ResultSet rs) throws DAOException {
        boletadetalle b = null;
        try{
        Long idboletacabecera = rs.getLong("idboletacabecera");
        Long idproducto = rs.getLong("idproducto");
        double cantidad = rs.getDouble("cantidad");
        double subtotal = rs.getDouble("subtotal");
        b = new boletadetalle(idproducto, cantidad, subtotal);
        b.setIdboletacabecera(idboletacabecera);
        b.setIdboletadetalle(rs.getLong("idboletadetalle"));
        b.setStatus(rs.getBoolean("status"));
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new DAOException("Error en SQL.", ex);
        }

        return b;
    }

    @Override
    public void eliminarDetallesBoleta(Long idboletacabecera) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conexion.prepareStatement(DELETEALL);
            stat.setLong(1, idboletacabecera);
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Error al eliminar un registro.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilSQL.cerrar(stat);
        }
    }

    @Override
    public List<boletadetalle> obtenerDetallesBoleta(Long idboletacabecera) throws DAOException {
        List<boletadetalle> lista = new ArrayList<>();
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conexion.prepareStatement(GETDETAILS);
            stat.setLong(1, idboletacabecera);
            
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

}
