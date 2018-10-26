/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.hibernate.dao.implementacion;

import farmacia.hibernate.dao.BoletaDAO;
import farmacia.hibernate.dao.DAOException;
import farmacia.hibernate.modelo.Boleta;
import farmacia.hibernate.modelo.Boleta;
import farmacia.hibernate.util.NewHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author fecyp
 */
public class BoletaIMPL implements BoletaDAO {

     private Session sesion;
    private Transaction tx;

    public BoletaIMPL(Session session) {
        this.sesion = sesion;
    }

    public BoletaIMPL() {
    }

    @Override
    public Integer insertar(Boleta obj) throws DAOException {
        Integer id = null;
        try {
            iniciarOperacion();
            id = (Integer) sesion.save(obj);
            tx.commit();

        } catch (HibernateException ex) {
            manejarExcepcion(ex);

        } finally {
            sesion.close();
        }
        return id;
    }

    @Override
    public void modificar(Boleta obj) throws DAOException {
        try {
            iniciarOperacion();
            sesion.update(obj);
            tx.commit();

        } catch (HibernateException ex) {
            manejarExcepcion(ex);

        } finally {
            sesion.close();
        }
    }

    @Override
    public void eliminar(Boleta obj) throws farmacia.hibernate.dao.DAOException {
//        try {
//            obj.setStatus(false);
//            iniciarOperacion();
//            sesion.update(obj);
//            tx.commit();
//
//        } catch (HibernateException ex) {
//            manejarExcepcion(ex);
//
//        } finally {
//            sesion.close();
//        }
    }

    @Override
    public List<Boleta> obtenertodos() throws farmacia.hibernate.dao.DAOException {
        List<Boleta> lista = new ArrayList<>();
        try {
            iniciarOperacion();
            lista = sesion.createQuery("from Boleta where status = 1").list();
            tx.commit();
        } catch (HibernateException ex) {
            manejarExcepcion(ex);
        } finally {
            sesion.close();
        }
        return lista;
    }

    @Override
    public Boleta obtener(Integer id) throws farmacia.hibernate.dao.DAOException {
        Boleta obj = null;
        try {
            iniciarOperacion();
            obj = (Boleta) sesion.get(Boleta.class, id);
            tx.commit();
        } catch (HibernateException ex) {
            manejarExcepcion(ex);
        } finally {
            sesion.close();
        }

        return obj;
    }

    @Override
    public void iniciarOperacion() throws DAOException {
        try {
            sesion = NewHibernateUtil.getSessionFactory().openSession();
            tx = sesion.beginTransaction();
        } catch (HibernateException ex) {
            throw new DAOException("Error en transferencia.", ex);
        }
    }

    @Override
    public void manejarExcepcion(HibernateException ex) throws DAOException {
        tx.rollback();
        throw new DAOException("Error en transferencia.", ex);
    }

//    Connection conexion;
//
//    public BoletaIMPL(Connection conn) {
//        this.conexion = conn;
//    }
//
//    @Override
//    public Long insertar(boleta obj) throws DAOException {
//        Long idboleta = null;
//        try {
//
//            conexion.setAutoCommit(false);// para  poder controlar que parte se envia y si hay un error en todo ese segemento poder hacer el roolback
//            // instaciamos cada dao para poder utilizar sus funciones insertar modificar etc
//
//            BoletacabeceraIMPL cabeceraSQL = new BoletacabeceraIMPL(conexion);
//            boletadetalleSQL detalleSQL = new boletadetalleSQL(conexion);
//            //damos valores a la cabecera y detalle para que se puedan buscar mediante sus atributos
//            Boletacabecera cabecera = obj.getCabecera();
//            List<boletadetalle> listadodetalle = obj.getDetalle();
//
//            idboleta = cabeceraSQL.insertar(cabecera);
//
//            for (int i = 0; i < listadodetalle.size(); i++) {
//                boletadetalle detalleN = listadodetalle.get(i);
//                detalleN.setIdboletacabecera(idboleta);
//                detalleSQL.insertar(detalleN);
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
//        return idboleta;
//    }
//
//    @Override
//    public void modificar(boleta obj) throws DAOException {
//        try {
//
//            conexion.setAutoCommit(false);// para  poder controlar que parte se envia y si hay un error en todo ese segemento poder hacer el roolback
//            // instaciamos cada dao para poder utilizar sus funciones insertar modificar etc
//
//            BoletacabeceraIMPL cabeceraSQL = new BoletacabeceraIMPL(conexion);
//            boletadetalleSQL detalleSQL = new boletadetalleSQL(conexion);
//            //damos valores a la cabecera y detalle para que se puedan buscar mediante sus atributos
//            Boletacabecera cabecera = obj.getCabecera();
//            List<boletadetalle> listadodetalle = obj.getDetalle();
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
//    public void eliminar(boleta obj) throws DAOException {
//        try {
//            conexion.setAutoCommit(false);
//            BoletacabeceraIMPL cabSQL = new BoletacabeceraIMPL(conexion);
//            boletadetalleSQL detalleSQL = new boletadetalleSQL(conexion);
//
//            Boletacabecera cabecera = obj.getCabecera();
//            boletadetalle detalle = obj.getOneDetalle(0);
//
//            detalleSQL.eliminarDetallesBoleta(obj.getCabecera().getIdboletacabecera());
//            cabSQL.eliminar(cabecera);
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
//    }
//
//    @Override
//    public List<boleta> obtenertodos() throws DAOException {
//        boleta b = null;
//        List<Boletacabecera> cabeceras = null;
//        List<boleta> lista = new ArrayList<>();
//        try {
//            conexion.setAutoCommit(false);
//            BoletacabeceraIMPL cabSQL = new BoletacabeceraIMPL(conexion);
//            boletadetalleSQL detalleSQL = new boletadetalleSQL(conexion);
//            
//            cabeceras = new ArrayList<>(cabSQL.obtenertodos());
//            
//            for (Boletacabecera i : cabeceras) {
//                Long id = i.getIdboletacabecera();
//                b = obtener(id);
//                lista.add(b);
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
//    public boleta obtener(Long id) throws DAOException {
//        boleta b = null;
//        Boletacabecera cabecera = null;
//        List<boletadetalle> lista = null;
//        try {
//            conexion.setAutoCommit(false);
//            BoletacabeceraIMPL cabSQL = new BoletacabeceraIMPL(conexion);
//            boletadetalleSQL detalleSQL = new boletadetalleSQL(conexion);
//
//            cabecera = cabSQL.obtener(id);
//            lista = new ArrayList<>(detalleSQL.obtenerDetallesBoleta(id));
//            
//            b = new boleta(cabecera, lista);
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
//        return b;
//    }
//
//    @Override
//    public boleta convertir(ResultSet rs) throws DAOException {
//        throw new DAOException("No se puede utilizar este método. Modelacion abstracta.");
//    }

   
}
