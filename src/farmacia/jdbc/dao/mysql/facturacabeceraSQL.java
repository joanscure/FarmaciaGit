package farmacia.jdbc.dao.mysql;

import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.facturacabeceraDAO;
import farmacia.jdbc.modelado.facturacabecera;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class facturacabeceraSQL implements facturacabeceraDAO {

    private Connection conexion;

    private final String INSERT = "INSERT INTO facturacabecera(correlativofactura, numerofactura, fechaemisionfactura, idempresacliente, idempleado, status) "
            + "VALUES (?, ?, ?, ?, ?, ?) ";
    private final String UPDATE = "UPDATE facturacabecera SET correlativofactura = ?, numerofactura = ?, fechaemisionfactura = ?, idempresacliente = ?, idempleado = ?, status = ? "
            + "WHERE idfacturacabecera = ?";
    private final String DELETE = "UPDATE facturacabecera SET status = 0 WHERE idfacturacabecera = ?";
    private final String GETALL = "SELECT * FROM facturacabecera WHERE status = 1";//solo obtiene los activos 
    private final String GETONE = "SELECT * FROM facturacabecera WHERE idfacturacabecera = ?";
    private final String GETALLTIME = "SELECT * FROM facturacabecera fc WHERE fc.fechaemisionfactura >=? AND bc.fechaemisionfactura <=? AND status=1 ORDER by fechaemisionfactura";
    private final String GETID = "SELECT idfacturacabecera  FROM facturacabecera fc WHERE fc.correlativofactura=? AND fc.numerofactura=?";

    public facturacabeceraSQL(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public Long insertar(facturacabecera obj) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conexion.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

            stat.setString(1, obj.getCorrelativofactura());
            stat.setString(2, obj.getNumerofactura());
            stat.setDate(3, new Date(obj.getFechaemisionfactura().getTime()));
            stat.setLong(4, obj.getIdempresacliente());
            stat.setLong(5, obj.getIdempleado());
            stat.setBoolean(6, (boolean) obj.isStatus());

            if (stat.executeUpdate() == 0) {
                throw new DAOException("Error al ingresar un registro.");
            }

            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                obj.setIdfacturacabecera(rs.getLong(1));
            }
        } catch (SQLException ex) {
            throw new DAOException("Error de sql." + ex.getMessage());
        } finally {
            UtilSQL.cerrar(stat, rs);
        }
        return obj.getIdfacturacabecera();
    }

    @Override
    public void modificar(facturacabecera obj) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conexion.prepareStatement(UPDATE);

            stat.setString(1, obj.getCorrelativofactura());
            stat.setString(2, obj.getNumerofactura());
            stat.setDate(3, new Date(obj.getFechaemisionfactura().getTime()));
            stat.setLong(4, obj.getIdempresacliente());
            stat.setLong(5, obj.getIdempleado());
            stat.setBoolean(6, (boolean) obj.isStatus());
            stat.setLong(7, obj.getIdfacturacabecera());

            if (stat.executeUpdate() == 0) {
                throw new DAOException("Error al ingresar un registro.");
            }

        } catch (SQLException ex) {
            throw new DAOException("Error de sql." + ex.getMessage());
        } finally {
            UtilSQL.cerrar(stat);
        }
    }

    @Override
    public void eliminar(facturacabecera obj) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conexion.prepareStatement(DELETE);
            stat.setLong(1, obj.getIdfacturacabecera());
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
    public List<facturacabecera> obtenertodos() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<facturacabecera> lista = new ArrayList<>();
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
    public facturacabecera obtener(Long id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        facturacabecera f;
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
            UtilSQL.cerrar(stat, rs);
        }
        return f;
    }

    @Override
    public facturacabecera convertir(ResultSet rs) throws DAOException {
        facturacabecera f = null;
        try {
            String correlativofactura = rs.getString("correlativofactura");
            String numerofactura = rs.getString("numeroboleta");
            Date fechaemisionfactura = rs.getDate("fechaemisionfactura");
            Long idempresacliente = rs.getLong("idempresacliente");
            Long idempleado = rs.getLong("idempleado");
            f = new facturacabecera(idempresacliente, idempleado, correlativofactura, numerofactura, fechaemisionfactura);
            f.setIdfacturacabecera(rs.getLong("idfacturacabecera"));
            f.setStatus(rs.getBoolean("status"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new DAOException("Error en SQL.", ex);
        }
        return f;
    }

    @Override
    public List<facturacabecera> obtenerportiempo(java.util.Date min, java.util.Date max) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<facturacabecera> lista = new ArrayList<>();
        try {
            stat = conexion.prepareStatement(GETALLTIME);
            stat.setDate(1, new Date(min.getTime()));
            stat.setDate(2, new Date(max.getTime()));
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
    public Long obtenerid(facturacabecera fc) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Long b;
        try {
            stat = conexion.prepareStatement(GETID);
            stat.setString(1, fc.getCorrelativofactura());
            stat.setString(2, fc.getNumerofactura());
            rs = stat.executeQuery();
            if (rs.next()) {
                b = rs.getLong("idfacturacabecera");
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

}
