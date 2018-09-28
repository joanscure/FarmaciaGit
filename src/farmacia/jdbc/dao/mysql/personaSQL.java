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
    private final String UPDATE = "UPDATE persona SET nombre = ?, appaterno = ?, apmaterno = ?, numerodni = ?, personaedad = ?, direccion = ?, telefono = ?, status = ? "
            + "WHERE idpersona = ?";
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
    public Long insertar(persona obj) throws DAOException {

        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conexion.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

            stat.setString(1, (String) obj.getNombre());
            stat.setString(2, (String) obj.getAppaterno());
            stat.setString(3, (String) obj.getApmaterno());
            stat.setString(4, String.valueOf(obj.getNumerodni()));
            stat.setInt(5, obj.getPersonaedad());
            stat.setString(6, (String) obj.getDireccion());
            stat.setString(7, (String) obj.getTelefono());
            stat.setBoolean(8, obj.isStatus());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Error al ingresar un registro.");
            }

            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                obj.setIdPersona(rs.getLong(1));
            } else {
                throw new DAOException("Error al ingresar un registro. No se puede asignar ID.");
            }

        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilSQL.cerrar(stat, rs);
        }
        System.out.println(obj.getIdPersona());
        return obj.getIdPersona();
    }

    @Override
    public void modificar(persona obj) throws DAOException {

        PreparedStatement stat = null;
        try {
            stat = conexion.prepareStatement(UPDATE);
            stat.setString(1, (String) obj.getNombre());
            stat.setString(2, (String) obj.getAppaterno());
            stat.setString(3, (String) obj.getApmaterno());
            stat.setString(4, String.valueOf(obj.getNumerodni()));
            stat.setInt(5, obj.getPersonaedad());
            stat.setString(6, (String) obj.getDireccion());
            stat.setString(7, (String) obj.getTelefono());
            stat.setBoolean(8, obj.isStatus());
            stat.setLong(9, obj.getIdPersona());
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
    public void eliminar(persona obj) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conexion.prepareStatement(DELETE);
            stat.setLong(1, obj.getIdPersona());
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
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilSQL.cerrar(stat, rs);
        }
        return p;
    }

}
