package farmacia.hibernate.dao.implementacion;

import farmacia.hibernate.dao.FacturacabeceraDAO;
import farmacia.hibernate.modelo.Facturacabecera;
import java.util.List;

public class FacturacabeceraIMPL implements FacturacabeceraDAO {

    @Override
    public Integer insertar(Facturacabecera obj) throws farmacia.hibernate.dao.DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(Facturacabecera obj) throws farmacia.hibernate.dao.DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Facturacabecera obj) throws farmacia.hibernate.dao.DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Facturacabecera> obtenertodos() throws farmacia.hibernate.dao.DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Facturacabecera obtener(Integer id) throws farmacia.hibernate.dao.DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    private Connection conexion;
//
//    private final String INSERT = "INSERT INTO facturacabecera(correlativofactura, numerofactura, fechaemisionfactura, idempresacliente, idempleado, status) "
//            + "VALUES (?, ?, ?, ?, ?, ?) ";
//    private final String UPDATE = "UPDATE facturacabecera SET correlativofactura = ?, numerofactura = ?, fechaemisionfactura = ?, idempresacliente = ?, idempleado = ?, status = ? "
//            + "WHERE idfacturacabecera = ?";
//    private final String DELETE = "UPDATE facturacabecera SET status = 0 WHERE idfacturacabecera = ?";
//    private final String GETALL = "SELECT * FROM facturacabecera WHERE status = 1";//solo obtiene los activos 
//    private final String GETONE = "SELECT * FROM facturacabecera WHERE idfacturacabecera = ?";
//
//    public FacturacabeceraIMPL(Connection conexion) {
//        this.conexion = conexion;
//    }
//
//    @Override
//    public Long insertar(facturacabecera obj) throws DAOException {
//        PreparedStatement stat = null;
//        ResultSet rs = null;
//        try {
//            stat = conexion.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
//
//            stat.setString(1, obj.getCorrelativofactura());
//            stat.setString(2, obj.getNumerofactura());
//            stat.setDate(3, new Date(obj.getFechaemisionfactura().getTime()));
//            stat.setLong(4, obj.getIdempresacliente());
//            stat.setLong(5, obj.getIdempleado());
//            stat.setBoolean(6, (boolean) obj.isStatus());
//
//            if (stat.executeUpdate() == 0) {
//                throw new DAOException("Error al ingresar un registro.");
//            }
//
//            rs = stat.getGeneratedKeys();
//            if (rs.next()) {
//                obj.setIdfacturacabecera(rs.getLong(1));
//            }
//        } catch (SQLException ex) {
//            throw new DAOException("Error de sql."+ ex.getMessage());
//        } finally {
//            UtilIMPL.cerrar(stat, rs);
//        }
//        return obj.getIdfacturacabecera();
//    }
//
//    @Override
//    public void modificar(facturacabecera obj) throws DAOException {
//        PreparedStatement stat = null;
//        try {
//            stat = conexion.prepareStatement(UPDATE);
//
//            stat.setString(1, obj.getCorrelativofactura());
//            stat.setString(2, obj.getNumerofactura());
//            stat.setDate(3, new Date(obj.getFechaemisionfactura().getTime()));
//            stat.setLong(4, obj.getIdempresacliente());
//            stat.setLong(5, obj.getIdempleado());
//            stat.setBoolean(6, (boolean) obj.isStatus());
//            stat.setLong(7, obj.getIdfacturacabecera());
//            
//            if (stat.executeUpdate() == 0) {
//                throw new DAOException("Error al ingresar un registro.");
//            }
//
//        } catch (SQLException ex) {
//            throw new DAOException("Error de sql."+ ex.getMessage());
//        } finally {
//            UtilIMPL.cerrar(stat);
//        }
//    }
//
//    @Override
//    public void eliminar(facturacabecera obj) throws DAOException {
//        PreparedStatement stat = null;
//        try {
//            stat = conexion.prepareStatement(DELETE);
//            stat.setLong(1, obj.getIdfacturacabecera());
//            if (stat.executeUpdate() == 0) {
//                throw new DAOException("Error al eliminar un registro.");
//            }
//        } catch (SQLException ex) {
//            throw new DAOException("Error en SQL.", ex);
//        } finally {
//            UtilIMPL.cerrar(stat);
//        }
//    }
//
//    @Override
//    public List<facturacabecera> obtenertodos() throws DAOException {
//        PreparedStatement stat = null;
//        ResultSet rs = null;
//        List<facturacabecera> lista = new ArrayList<>();
//        try {
//            stat = conexion.prepareStatement(GETALL);
//            rs = stat.executeQuery();
//            while (rs.next()) {
//                lista.add(convertir(rs));
//            }
//        } catch (SQLException ex) {
//            throw new DAOException("Error en SQL.", ex);
//        } finally {
//            UtilIMPL.cerrar(stat, rs);
//        }
//        return lista;
//    }
//
//    @Override
//    public facturacabecera obtener(Long id) throws DAOException {
//        PreparedStatement stat = null;
//        ResultSet rs = null;
//        facturacabecera f;
//        try {
//            stat = conexion.prepareStatement(GETONE);
//            stat.setLong(1, id);
//            rs = stat.executeQuery();
//            if (rs.next()) {
//                f = convertir(rs);
//            } else {
//                throw new DAOException("No se ha encontrado registro.");
//            }
//        } catch (SQLException ex) {
//            throw new DAOException("Error en SQL.", ex);
//        } finally {
//            UtilIMPL.cerrar(stat, rs);
//        }
//        return f;
//    }
//
//    @Override
//    public facturacabecera convertir(ResultSet rs) throws DAOException {
//        facturacabecera f = null;
//        try{
//        String correlativofactura = rs.getString("correlativofactura");
//        String numerofactura = rs.getString("numeroboleta");
//        Date fechaemisionfactura = rs.getDate("fechaemisionfactura");
//        Long idempresacliente = rs.getLong("idempresacliente");
//        Long idempleado = rs.getLong("idempleado");
//        f = new facturacabecera(idempresacliente, idempleado, correlativofactura, numerofactura, fechaemisionfactura);
//        f.setIdfacturacabecera(rs.getLong("idfacturacabecera"));
//        f.setStatus(rs.getBoolean("status"));
//        }catch (SQLException ex){
//            System.out.println(ex.getMessage());
//            throw new DAOException("Error en SQL.", ex);
//        }
//        return f;
//    }
//
}
