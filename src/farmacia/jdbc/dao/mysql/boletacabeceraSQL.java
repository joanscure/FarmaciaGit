package farmacia.jdbc.dao.mysql;

import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.boletacabeceraDAO;
import farmacia.jdbc.modelado.boletacabecera;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class boletacabeceraSQL implements boletacabeceraDAO {

    private Connection conexion;

    private final String INSERT = "INSERT INTO boletacabecera(correlativoboleta, numeroboleta, fechaemisionboleta, idpersonacliente, idempleado, status) "
            + "VALUES (?, ?, ?, ?, ?, ?) ";
    private final String UPDATE = "UPDATE boletacabecera SET correlativoboleta = ?, numeroboleta = ?, fechaemisionboleta = ?, idpersonacliente = ?, idempleado = ?, status = ? "
            + "WHERE idboletacabecera = ?";
    private final String DELETE = "UPDATE boletacabecera SET status = 0 WHERE idboletacabecera = ?";
    private final String GETALL = "SELECT * FROM boletacabecera WHERE status = 1";//solo obtiene los activos 
    private final String GETONE = "SELECT * FROM boletacabecera WHERE idboletacabecera = ? AND status = 1";
    private final String GETALLTIME="SELECT * FROM boletacabecera bc WHERE bc.fechaemisionboleta >=? AND bc.fechaemisionboleta <=? AND status=1 ORDER by fechaemisionboleta";
    private final String GETID="SELECT idboletacabecera  FROM boletacabecera bc WHERE bc.correlativoboleta=? AND bc.numeroboleta=?";
    public boletacabeceraSQL(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public Long insertar(boletacabecera obj) throws DAOException {

        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conexion.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

            stat.setString(1, obj.getCorrelativoboleta());
            stat.setString(2, obj.getNumeroboleta());
            stat.setDate(3, new Date(obj.getFechaemisionboleta().getTime()));
            stat.setLong(4, obj.getIdpersonacliente());
            stat.setLong(5, obj.getIdempleado());
            stat.setBoolean(6, (boolean) obj.isStatus());

            if (stat.executeUpdate() == 0) {
                throw new DAOException("Error al ingresar un registro.");
            }

            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                obj.setIdboletacabecera(rs.getLong(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            UtilSQL.cerrar(stat, rs);
        }
        return obj.getIdboletacabecera();
    }

    @Override
    public void modificar(boletacabecera obj) throws DAOException {
        
        PreparedStatement stat = null;

        try {
            stat = conexion.prepareStatement(UPDATE);

            stat.setString(1, obj.getCorrelativoboleta());
            stat.setString(2, obj.getNumeroboleta());
            stat.setDate(3, new Date(obj.getFechaemisionboleta().getTime()));
            stat.setLong(4, obj.getIdpersonacliente());
            stat.setLong(5, obj.getIdempleado());
            stat.setBoolean(6, (boolean) obj.isStatus());
            stat.setLong(7, obj.getIdboletacabecera());
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
    public void eliminar(boletacabecera obj) throws DAOException {
        //REVISAR CLAVE REFERENCIADA AL MOMENTO DE ELIMINAR
        PreparedStatement stat = null;
        try {
            stat = conexion.prepareStatement(DELETE);
            stat.setLong(1, obj.getIdboletacabecera());
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
    public List<boletacabecera> obtenertodos() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<boletacabecera> lista = new ArrayList<>();
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
    public boletacabecera obtener(Long id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        boletacabecera b;
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
    public boletacabecera convertir(ResultSet rs) throws DAOException {
        boletacabecera b = null;
        try{
        String correlativoboleta = rs.getString("correlativoboleta");
        String numeroboleta = rs.getString("numeroboleta");
        Date fechaemisionboleta = rs.getDate("fechaemisionboleta");
        Long idpersonacliente = rs.getLong("idpersonacliente");
        Long idempleado = rs.getLong("idempleado");
        b = new boletacabecera(correlativoboleta, numeroboleta, fechaemisionboleta, idpersonacliente, idempleado);
        b.setIdboletacabecera(rs.getLong("idboletacabecera"));
        b.setStatus(rs.getBoolean("status"));
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new DAOException("Error en SQL.", ex);
        }
        return b;

    }
     public List<boletacabecera> obtenerportiempo(java.util.Date min,java.util.Date max) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<boletacabecera> lista = new ArrayList<>();
        try {
            stat = conexion.prepareStatement(GETALLTIME);
            stat.setDate(1,new Date(min.getTime()));
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
    public Long obtenerid(boletacabecera bc) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Long b;
        try {
            stat = conexion.prepareStatement(GETID);
            stat.setString(1, bc.getCorrelativoboleta());
            stat.setString(2, bc.getNumeroboleta());
            rs = stat.executeQuery();
            if (rs.next()) {
                b = rs.getLong("idboletacabecera");
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
