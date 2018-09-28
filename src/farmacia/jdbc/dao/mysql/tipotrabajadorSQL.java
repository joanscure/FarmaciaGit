package farmacia.jdbc.dao.mysql;

import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.tipotrabajadorDAO;
import farmacia.jdbc.modelado.persona;
import farmacia.jdbc.modelado.tipotrabajador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class tipotrabajadorSQL implements tipotrabajadorDAO {

    private Connection conexion;

    private final String INSERT = "INSERT INTO tipotrabajador(nombretipotrabajador, accederventas, accederproductos, accederclientes, accederconsultas, accederempleados, accedertipousuario, "
            + "accedercambioclave, accederanulaciones, accedereliminarproducto, accedereliminarcliente, accedereliminarempleado, accedereliminartipoempleado, status ) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
    private final String UPDATE = "UPDATE tipotrabajador SET nombretipotrabajador = ?, accederventas = ?, accederproductos = ?, accederclientes = ?, accederconsultas = ?, accederempleados = ?, accedertipousuario = ?, "
            + "accedercambioclave = ?, accederanulaciones = ?, accedereliminarproducto = ?, accedereliminarcliente = ?, accedereliminarempleado = ?, accedereliminartipoempleado = ?, status = ?";
    private final String DELETE = "UPDATE tipotrabajador SET status = 0 WHERE idtipotrabajador = ?";
    private final String GETALL = "SELECT * FROM tipotrabajador WHERE status = 1";
    private final String GETONE = "SELECT * FROM tipotrabajador WHERE idtipotrabajador = ?";

    public tipotrabajadorSQL(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public Long insertar(tipotrabajador obj) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conexion.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            stat.setString(1, obj.getNombretipotrabajador());
            stat.setBoolean(2, obj.isAccederventas());
            stat.setBoolean(3, obj.isAccederproductos());
            stat.setBoolean(4, obj.isAccederclientes());
            stat.setBoolean(5, obj.isAccederconsultas());
            stat.setBoolean(6, obj.isAccedereliminarempleado());
            stat.setBoolean(7, obj.isAccedertipousuario());
            stat.setBoolean(8, obj.isAccedercambioclave());
            stat.setBoolean(9, obj.isAccederanulaciones());
            stat.setBoolean(10, obj.isAccedereliminarproducto());
            stat.setBoolean(11, obj.isAccedereliminarcliente());
            stat.setBoolean(12, obj.isAccedereliminarempleado());
            stat.setBoolean(13, obj.isAccedereliminartipoempleado());
            stat.setBoolean(14, obj.isStatus());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Error al ingresar un registro.");
            }

            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                obj.setIdtipotrabajador(rs.getLong(1));
            } else {
                throw new DAOException("Error al ingresar un registro. No se puede asignar ID.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilSQL.cerrar(stat, rs);
        }
        return obj.getIdtipotrabajador();
    }

    @Override
    public void modificar(tipotrabajador obj) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conexion.prepareStatement(INSERT);
            stat.setString(1, obj.getNombretipotrabajador());
            stat.setBoolean(2, obj.isAccederventas());
            stat.setBoolean(3, obj.isAccederproductos());
            stat.setBoolean(4, obj.isAccederclientes());
            stat.setBoolean(5, obj.isAccederconsultas());
            stat.setBoolean(6, obj.isAccedereliminarempleado());
            stat.setBoolean(7, obj.isAccedertipousuario());
            stat.setBoolean(8, obj.isAccedercambioclave());
            stat.setBoolean(9, obj.isAccederanulaciones());
            stat.setBoolean(10, obj.isAccedereliminarproducto());
            stat.setBoolean(11, obj.isAccedereliminarcliente());
            stat.setBoolean(12, obj.isAccedereliminarempleado());
            stat.setBoolean(13, obj.isAccedereliminartipoempleado());
            stat.setBoolean(14, obj.isStatus());
            stat.setLong(15, obj.getIdtipotrabajador());
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
    public void eliminar(tipotrabajador obj) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conexion.prepareStatement(DELETE);
            stat.setLong(1, obj.getIdtipotrabajador());
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
    public List<tipotrabajador> obtenertodos() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<tipotrabajador> lista = new ArrayList<>();
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
    public tipotrabajador obtener(Long id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        tipotrabajador tipo = null;
        try {
            stat = conexion.prepareStatement(GETONE);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                tipo = convertir(rs);
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilSQL.cerrar(stat, rs);
        }
        return tipo;
    }

    @Override
    public tipotrabajador convertir(ResultSet rs) throws SQLException {
        tipotrabajador tipo;

        String nombretipotrabajador = rs.getString("nombretipotrabajador");//32 NULO
        boolean accederventas = rs.getBoolean("accederventas");
        boolean accederproductos = rs.getBoolean("accederproductos");
        boolean accederclientes = rs.getBoolean("accederclientes");
        boolean accederconsultas = rs.getBoolean("accederconsultas");
        boolean accederempleados = rs.getBoolean("accederempleados");
        boolean accedertipousuario = rs.getBoolean("accedertipousuario");
        boolean accedercambioclave = rs.getBoolean("accedercambioclave");
        boolean accederanulaciones = rs.getBoolean("accederanulaciones");
        boolean accedereliminarproducto = rs.getBoolean("accedereliminarproducto");
        boolean accedereliminarcliente = rs.getBoolean("accedereliminarcliente");
        boolean accedereliminarempleado = rs.getBoolean("accedereliminarempleado");
        boolean accedereliminartipoempleado = rs.getBoolean("accedereliminartipoempleado");

        tipo = new tipotrabajador(nombretipotrabajador, accederventas, accederproductos, accederclientes, accederconsultas, accederempleados, accedertipousuario, accedercambioclave, accederanulaciones, accedereliminarproducto, accedereliminarcliente, accedereliminarempleado, accedereliminartipoempleado);
        tipo.setIdtipotrabajador(rs.getLong("idtipotrabajador"));
        tipo.setStatus(rs.getBoolean("status"));

        return tipo;
    }

}
