package farmacia.hibernate.dao.implementacion;

import farmacia.hibernate.dao.DescuentoDAO;
import farmacia.hibernate.modelo.Descuento;
import java.util.List;

public class DescuentoIMPL implements DescuentoDAO {

    @Override
    public Integer insertar(Descuento obj) throws farmacia.hibernate.dao.DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(Descuento obj) throws farmacia.hibernate.dao.DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Descuento obj) throws farmacia.hibernate.dao.DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Descuento> obtenertodos() throws farmacia.hibernate.dao.DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Descuento obtener(Integer id) throws farmacia.hibernate.dao.DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*
    private Connection conexion;

    private final String INSERT = "INSERT INTO descuento(nombredescuento, condicion, porcentaje, descripciondescuento, status) "
            + "VALUES (?, ?, ?, ?, ?)";
    private final String UPDATE = "UPDATE descuento SET nombredescuento = ?, condicion = ?, porcentaje = ?, descripciondescuento = ?, status = ? "
            + "WHERE iddescuento = ?";
    private final String DELETE = "UPDATE descuento SET status = 0 WHERE iddescuento = ?";
    private final String GETALL = "SELECT * FROM descuento WHERE status = 1";//solo obtiene los activos 
    private final String GETONE = "SELECT * FROM descuento WHERE iddescuento = ? AND status = 1";

    public DescuentoIMPL(Connection conn) {
        this.conexion = conn;
    }

    @Override
    public Long insertar(descuento obj) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        try {
            stat = conexion.prepareStatement(INSERT);
        
            stat.setString(1, (String) obj.getNombredescuento());
            stat.setString(2, (String) obj.getCondicion());
            stat.setDouble(3, (double) obj.getPorcentaje());
            stat.setString(4, (String) obj.getDescripciondescuento());
            stat.setBoolean(5, (boolean) obj.isStatus());
            
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Error al ingresar un registro.");
            }
            
            rs = stat.getGeneratedKeys();
            if(rs.next()){
                obj.setIddescuento(rs.getLong(1));
            }else{
                throw new DAOException("Error al ingresar un registro. No se puede asignar ID.");
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilIMPL.cerrar(stat,rs);
        }
        return obj.getIddescuento();
    }

    @Override
    public void modificar(descuento obj) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conexion.prepareStatement(UPDATE);
            stat.setString(1, obj.getNombredescuento());
            stat.setString(2, obj.getCondicion());
            stat.setDouble(3, obj.getPorcentaje());
            stat.setString(4, obj.getDescripciondescuento());
            stat.setBoolean(5, obj.isStatus());
            stat.setLong(6, obj.getIddescuento());
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
    public void eliminar(descuento obj) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conexion.prepareStatement(DELETE);
            stat.setLong(1, obj.getIddescuento());
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
    public List<descuento> obtenertodos() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<descuento> lista = new ArrayList<>();
        try {
            stat = conexion.prepareStatement(GETONE);
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
    public descuento obtener(Long id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        descuento des = null;
        try {
            stat = conexion.prepareStatement(GETONE);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                des = convertir(rs);
            }
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL.", ex);
        } finally {
            UtilIMPL.cerrar(stat, rs);
        }
        return des;
    }

    @Override
    public descuento convertir(ResultSet rs) throws DAOException {
        descuento des = null;
        try{
        String nombredescuento = rs.getString("nombredescuento");
        String condicion = rs.getString("condicion");
        double porecntaje = rs.getDouble("porcentaje");
        String descripciondescuento = rs.getString("descripciondescuento");
        boolean status = rs.getBoolean("status");
        des = new descuento(nombredescuento, condicion, porecntaje, descripciondescuento);
        des.setIddescuento(rs.getLong("iddescuento"));
        des.setStatus(rs.getBoolean("status"));
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new DAOException("Error en SQL.", ex);
        }
        return des;
        
    }
*/
    
}
