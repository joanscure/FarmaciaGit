package farmacia.hibernate.dao.implementacion;

import farmacia.hibernate.dao.BoletacabeceraDAO;
import farmacia.hibernate.dao.DAOException;
import farmacia.hibernate.modelo.Boletacabecera;
import java.util.List;
import org.hibernate.SessionFactory;


public class BoletacabeceraIMPL implements BoletacabeceraDAO {

    BoletacabeceraIMPL(SessionFactory sessionFac) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer insertar(Boletacabecera obj) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(Boletacabecera obj) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Boletacabecera obj) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Boletacabecera> obtenertodos() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boletacabecera obtener(Integer id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    /*
    private Connection conexion;

    private final String INSERT = "INSERT INTO boletacabecera(correlativoboleta, numeroboleta, fechaemisionboleta, idpersonacliente, idempleado, status) "
            + "VALUES (?, ?, ?, ?, ?, ?) ";
    private final String UPDATE = "UPDATE boletacabecera SET correlativoboleta = ?, numeroboleta = ?, fechaemisionboleta = ?, idpersonacliente = ?, idempleado = ?, status = ? "
            + "WHERE idboletacabecera = ?";
    private final String DELETE = "UPDATE boletacabecera SET status = 0 WHERE idboletacabecera = ?";
    private final String GETALL = "SELECT * FROM boletacabecera WHERE status = 1";//solo obtiene los activos 
    private final String GETONE = "SELECT * FROM boletacabecera WHERE idboletacabecera = ? AND status = 1";

    public BoletacabeceraIMPL(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public Long insertar(Boletacabecera obj) throws DAOException {

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
            UtilIMPL.cerrar(stat, rs);
        }
        return obj.getIdboletacabecera();
    }

    @Override
    public void modificar(Boletacabecera obj) throws DAOException {
        
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
            UtilIMPL.cerrar(stat);
        }
    }

    @Override
    public void eliminar(Boletacabecera obj) throws DAOException {
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
            UtilIMPL.cerrar(stat);
        }
    }

    @Override
    public List<Boletacabecera> obtenertodos() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Boletacabecera> lista = new ArrayList<>();
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
    public Boletacabecera obtener(Long id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Boletacabecera b;
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
            UtilIMPL.cerrar(stat, rs);
        }
        return b;
    }

    @Override
    public Boletacabecera convertir(ResultSet rs) throws DAOException {
        Boletacabecera b = null;
        try{
        String correlativoboleta = rs.getString("correlativoboleta");
        String numeroboleta = rs.getString("numeroboleta");
        Date fechaemisionboleta = rs.getDate("fechaemisionboleta");
        Long idpersonacliente = rs.getLong("idpersonacliente");
        Long idempleado = rs.getLong("idempleado");
        b = new Boletacabecera(correlativoboleta, numeroboleta, fechaemisionboleta, idpersonacliente, idempleado);
        b.setIdboletacabecera(rs.getLong("idboletacabecera"));
        b.setStatus(rs.getBoolean("status"));
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new DAOException("Error en SQL.", ex);
        }
        return b;

    }
    */
}
