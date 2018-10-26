package farmacia.hibernate.dao.implementacion;

import farmacia.hibernate.dao.EmpresaclienteDAO;
import farmacia.hibernate.modelo.Empresa;
import farmacia.hibernate.modelo.Empresacliente;
import java.util.List;

public class EmpresaclienteIMPL implements EmpresaclienteDAO {

    @Override
    public void insertarNuevo(Empresacliente cliente, Empresa emp) throws farmacia.hibernate.dao.DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer insertar(Empresacliente obj) throws farmacia.hibernate.dao.DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(Empresacliente obj) throws farmacia.hibernate.dao.DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Empresacliente obj) throws farmacia.hibernate.dao.DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Empresacliente> obtenertodos() throws farmacia.hibernate.dao.DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Empresacliente obtener(Integer id) throws farmacia.hibernate.dao.DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    private Connection conexion;
//
//    private final String INSERT = "INSERT INTO empresacliente(idempresa, fecharegistro, status) "
//            + "VALUES (?, ?, ?) ";
//    private final String UPDATE = "UPDATE empresacliente SET idempresa = ?, fecharegistro = ?, status = ? WHERE idempresacliente = ?";
//    private final String DELETE = "UPDATE empresacliente SET status = 0 WHERE idempresacliente = ?";
//    private final String GETALL = "SELECT * FROM empresacliente WHERE status = 1";//solo obtiene los activos 
//    private final String GETONE = "SELECT * FROM empresacliente WHERE idempresacliente = ?";
//
//    public EmpresaclienteIMPL(Connection conn) {
//        this.conexion = conn;
//    }
//
//    @Override
//    public Long insertar(empresacliente obj) throws DAOException {
//        PreparedStatement stat = null;
//        ResultSet rs = null;
//        try {
//            stat = conexion.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
//            stat.setLong(1, obj.getIdempresa());
//            stat.setDate(2, new java.sql.Date(obj.getFecharegistro().getTime()));
//            stat.setBoolean(3, obj.isStatus());
//            if (stat.executeUpdate() == 0){
//                throw new DAOException("Error al ingresar un registro");
//            }
//            rs = stat.getGeneratedKeys();
//            if (rs.next()){
//                obj.setIdempresacliente(rs.getLong(1));
//            }else{
//                throw new DAOException("Error al ingresar un registro. No se puede asignar ID.");
//            }
//        } catch (SQLException ex) {
//            throw new DAOException("Error en SQL.", ex);
//        } finally {
//            UtilIMPL.cerrar(stat, rs);
//        }
//        return obj.getIdempresacliente();
//    }
//
//    @Override
//    public void modificar(empresacliente obj) throws DAOException {
//        PreparedStatement stat = null;
//        try {
//            stat = conexion.prepareStatement(UPDATE);
//            stat.setLong(1, obj.getIdempresa());
//            stat.setDate(2, new java.sql.Date(obj.getFecharegistro().getTime()));
//            stat.setBoolean(3, obj.isStatus());
//            stat.setLong(4, obj.getIdempresacliente());
//            if (stat.executeUpdate() == 0){
//                throw new DAOException("Error al ingresar un registro");
//            }
//        } catch (SQLException ex) {
//            throw new DAOException("Error en SQL.", ex);
//        } finally {
//            UtilIMPL.cerrar(stat);
//        }
//    }
//
//    @Override
//    public void eliminar(empresacliente obj) throws DAOException {
//        PreparedStatement stat = null;
//        try{
//            stat = conexion.prepareStatement(DELETE);
//            stat.setLong(1, obj.getIdempresacliente());
//            
//            if (stat.executeUpdate() == 0){
//                throw new DAOException("Error al elimiar un registro.");
//            }
//        } catch (SQLException ex) {
//            throw new DAOException("Error en SQL.",ex);
//        }finally{
//            UtilIMPL.cerrar(stat);
//        }
//    }
//
//    @Override
//    public List<empresacliente> obtenertodos() throws DAOException {
//        PreparedStatement stat = null;
//        ResultSet rs = null;
//        List<empresacliente> lista = new ArrayList<>();
//        try {
//            stat = conexion.prepareStatement(GETALL);
//            rs = stat.executeQuery();
//            while(rs.next()){
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
//    public empresacliente obtener(Long id) throws DAOException {
//        PreparedStatement stat = null;
//        ResultSet rs = null;
//        empresacliente emp = null;
//        try {
//            stat = conexion.prepareStatement(GETONE);
//            stat.setLong(1, id);
//            rs = stat.executeQuery();
//            if (rs.next()) {
//                emp = convertir(rs);
//            }
//        } catch (SQLException ex) {
//            throw new DAOException("Error en SQL.", ex);
//        } finally {
//            UtilIMPL.cerrar(stat, rs);
//        }
//        return emp;
//    }
//
//    @Override
//    public empresacliente convertir(ResultSet rs) throws DAOException {
//        empresacliente emp = null;
//        try{
//        Long idempresacliente = rs.getLong("idempresacliente");//PK
//        Long idempresa = rs.getLong("idempresa");
//        Long fecha = rs.getDate("fecharegistro").getTime();
//        Date fecharegistro = new Date(fecha);
//        boolean status = rs.getBoolean("status");
//        emp = new empresacliente(idempresa, fecharegistro);
//        emp.setIdempresacliente(idempresacliente);
//        emp.setStatus(status);
//        }catch (SQLException ex){
//            System.out.println(ex.getMessage());
//            throw new DAOException("Error en SQL.", ex);
//        }
//        return emp;
//        
//    }
//
//    @Override
//    public void insertarNuevo(empresacliente cliente, empresa emp) throws DAOException{
//         try {
//            conexion.setAutoCommit(false);
//            EmpresaIMPL empSQL = new EmpresaIMPL(conexion);
//             
//            Long idEmpNueva = empSQL.insertar(emp);
//           
//            cliente.setIdempresa(idEmpNueva);//inserta la empresa en primer lugar y modifica el idempresa del cliente
//            
//            insertar(cliente);//inserta al empleado
//          
//            conexion.commit();
//        } catch (SQLException ex) {
//            try {
//                conexion.rollback();
//            } catch (SQLException ex1) {
//                throw new DAOException("Error en transaccion.", ex1);
//            }
//            throw new DAOException("Error en SQL.", ex);
//        } 
//    }
//
}
