/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.hibernate.dao.implementacion;

import farmacia.hibernate.dao.FacturaDAO;
import farmacia.hibernate.modelo.Factura;
import java.util.List;
import org.hibernate.SessionFactory;

public class FacturaIMPL implements FacturaDAO {

    FacturaIMPL(SessionFactory sessionFac) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer insertar(Factura obj) throws farmacia.hibernate.dao.DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(Factura obj) throws farmacia.hibernate.dao.DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Factura obj) throws farmacia.hibernate.dao.DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Factura> obtenertodos() throws farmacia.hibernate.dao.DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Factura obtener(Integer id) throws farmacia.hibernate.dao.DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    Connection conexion;
//
//    public FacturaIMPL(Connection conexion) {
//        this.conexion = conexion;
//    }
//
//    @Override
//    public Long insertar(factura obj) throws DAOException {
//        Long idfactura = null;
//        try {
//            conexion.setAutoCommit(false);// para  poder controlar que parte se envia y si hay un error en todo ese segemento poder hacer el roolback
//            // instaciamos cada dao para poder utilizar sus funciones insertar modificar etc
//            facturacabeceraSQL cabeceraSQL = new facturacabeceraSQL(conexion);
//            facturadetalleSQL detalleSQL = new facturadetalleSQL(conexion);
//            //damos valores a la cabecera y detalle para que se puedan buscar mediante sus atributos
//            facturacabecera cabecera = obj.getCabecera();
//            List<facturadetalle> listadodetalle = obj.getDetalle();
//
//            idfactura = cabeceraSQL.insertar(cabecera);
//
//            for (int i = 0; i < listadodetalle.size(); i++) {
//                facturadetalle detalleN = listadodetalle.get(i);
//                detalleN.setIdfacturacabecera(idfactura);
//                detalleSQL.insertar(detalleN);
//            }
//
//            conexion.commit();
//
//        } catch (SQLException ex) {
//            try {
//                conexion.rollback();
//            } catch (SQLException ex1) {
//                throw new DAOException("Error en transaccion.", ex1);
//            }
//            throw new DAOException("Error en sql.", ex);
//        }
//        return idfactura;
//    }
//
//    @Override
//    public void modificar(factura obj) throws DAOException {
//        try {
//
//            conexion.setAutoCommit(false);// para  poder controlar que parte se envia y si hay un error en todo ese segemento poder hacer el roolback
//            // instaciamos cada dao para poder utilizar sus funciones insertar modificar etc
//
//            facturacabeceraSQL cabeceraSQL = new facturacabeceraSQL(conexion);
//            facturadetalleSQL detalleSQL = new facturadetalleSQL(conexion);
//            //damos valores a la cabecera y detalle para que se puedan buscar mediante sus atributos
//            facturacabecera cabecera = obj.getCabecera();
//            List<facturadetalle> listadodetalle = obj.getDetalle();
//
//            cabeceraSQL.modificar(cabecera);
//
//            for (int i = 0; i < listadodetalle.size(); i++) {
//                detalleSQL.modificar(listadodetalle.get(i));
//            }
//
//            conexion.commit();
//
//        } catch (SQLException ex) {
//            try {
//                conexion.rollback();
//            } catch (SQLException ex1) {
//                System.out.println(ex.getMessage());
//                throw new DAOException("Error en transaccion.", ex1);
//            }
//            System.out.println(ex.getMessage());
//            throw new DAOException("Error en SQL.", ex);
//        }
//    }
//
//    @Override
//    public void eliminar(factura obj) throws DAOException {
//        try {
//            conexion.setAutoCommit(false);
//            facturacabeceraSQL cabeceradao = new facturacabeceraSQL(conexion);
//            facturadetalleSQL detalledao = new facturadetalleSQL(conexion);
//
//            facturacabecera cabecera = obj.getCabecera();
//            facturadetalle detalle = obj.getOneDetalle(0);
//
//            detalledao.eliminar(detalle);
//            cabeceradao.eliminar(cabecera);
//
//            conexion.commit();
//
//        } catch (SQLException ex) {
//            try {
//                conexion.rollback();
//            } catch (SQLException ex1) {
//                throw new DAOException("Error en transaccion.", ex1);
//            }
//            throw new DAOException("Error en sql.", ex);
//        }
//    }
//
//    public List<factura> obtenertodos() throws DAOException {
//        factura f = null;
//        List<facturacabecera> cabeceras = null;
//        List<factura> lista = new ArrayList<>();
//        try {
//            conexion.setAutoCommit(false);
//            facturacabeceraSQL cabSQL = new facturacabeceraSQL(conexion);
//            facturadetalleSQL detalleSQL = new facturadetalleSQL(conexion);
//            
//            cabeceras = new ArrayList<>(cabSQL.obtenertodos());
//            
//            for (facturacabecera i : cabeceras) {
//                Long id = i.getIdfacturacabecera();
//                f = obtener(id);
//                lista.add(f);
//            }
//            
//            conexion.commit();
//
//        } catch (SQLException ex) {
//            try {
//                conexion.rollback();
//            } catch (SQLException ex1) {
//                System.err.println(ex1.getMessage());
//                throw new DAOException("Error en transaccion.", ex1);
//            }
//            System.err.println(ex.getMessage());
//            throw new DAOException("Error en SQL.", ex);
//        }
//        return lista;
//    }
//
//    @Override
//    public factura obtener(Long id) throws DAOException {
//        factura f = null;
//        facturacabecera cabecera = null;
//        List<facturadetalle> lista = null;
//        try {
//            conexion.setAutoCommit(false);
//            facturacabeceraSQL cabSQL = new facturacabeceraSQL(conexion);
//            facturadetalleSQL detalleSQL = new facturadetalleSQL(conexion);
//
//            cabecera = cabSQL.obtener(id);
//            lista = new ArrayList<>(detalleSQL.obtenerDetallesFactura(id));
//            
//            f = new factura(cabecera, lista);
//
//            conexion.commit();
//
//        } catch (SQLException ex) {
//            try {
//                conexion.rollback();
//            } catch (SQLException ex1) {
//                System.err.println(ex1.getMessage());
//                throw new DAOException("Error en transaccion.", ex1);
//            }
//            System.err.println(ex.getMessage());
//            throw new DAOException("Error en SQL.", ex);
//        }
//        return f;
//    }
//
//    @Override
//    public factura convertir(ResultSet rs) throws DAOException {
//        throw new DAOException("No se puede utilizar este método. Modelacion abstracta.");
//    }

}
