package farmacia.hibernate.dao.implementacion;

import farmacia.hibernate.dao.DAOException;
import farmacia.hibernate.dao.FacturadetalleDAO;
import farmacia.hibernate.modelo.Facturadetalle;
import farmacia.hibernate.util.NewHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class FacturadetalleIMPL implements FacturadetalleDAO {

    private SessionFactory sessionFac;
    private Transaction tx;

    public FacturadetalleIMPL(SessionFactory sessionFac) {
        this.sessionFac = sessionFac;
    }

    @Override
    public void eliminarDetallesFactura(Integer idFacturacabecera) throws farmacia.hibernate.dao.DAOException {
        Session ses = null;
        try {
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            tx = ses.beginTransaction();
            List<Facturadetalle> lista = obtenerDetallesFactura(idFacturacabecera);
            for (Facturadetalle i : lista) {
                eliminar(i);
            }
            tx.commit();
        } catch (HibernateException ex) {
            tx.rollback();
            throw new DAOException("Error en transaccion", ex);
        } finally {
            ses.close();
            sessionFac.close();
        }
    }

    @Override
    public List<Facturadetalle> obtenerDetallesFactura(Integer idFacturacabecera) throws farmacia.hibernate.dao.DAOException {
        List<Facturadetalle> lista = new ArrayList<>();
        Session ses = null;
        try {
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            tx = ses.beginTransaction();
            lista = ses.createQuery("from Facturadetalle where idFacturacabecera = "
                    + "'" + idFacturacabecera.toString() + "'").list();
            tx.commit();
        } catch (HibernateException ex) {
            tx.rollback();
            throw new DAOException("Error en transaccion", ex);
        } finally {
            ses.close();
            sessionFac.close();
        }
        return lista;
    }

    @Override
    public Integer insertar(Facturadetalle obj) throws farmacia.hibernate.dao.DAOException {
        Integer id = null;
        Session ses = null;
        try {
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            tx = ses.beginTransaction();
            id = (Integer) ses.save(obj);
            tx.commit();

        } catch (HibernateException ex) {
            tx.rollback();
            throw new DAOException("Error en transaccion", ex);
        } finally {
            ses.close();
            sessionFac.close();
        }
        return id;
    }

    @Override
    public void modificar(Facturadetalle obj) throws farmacia.hibernate.dao.DAOException {
        Session ses = null;
        try {
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            tx = ses.beginTransaction();
            ses.update(obj);
            tx.commit();
        } catch (HibernateException ex) {
            tx.rollback();
            throw new DAOException("Error en transaccion", ex);
        } finally {
            ses.close();
            sessionFac.close();
        }
    }

    @Override
    public void eliminar(Facturadetalle obj) throws farmacia.hibernate.dao.DAOException {
        Session ses = null;
        try {
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            obj.setStatus(false);
            tx = ses.beginTransaction();
            ses.update(obj);
            tx.commit();
        } catch (HibernateException ex) {
            tx.rollback();
            throw new DAOException("Error en transaccion", ex);
        } finally {
            ses.close();
            sessionFac.close();
        }
    }

    @Override
    public List<Facturadetalle> obtenertodos() throws farmacia.hibernate.dao.DAOException {
        List<Facturadetalle> lista = new ArrayList<>();
        Session ses = null;
        try {
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            tx = ses.beginTransaction();
            lista = ses.createQuery("from Facturadetalle where status = 1").list();
            tx.commit();
        } catch (HibernateException ex) {
            tx.rollback();
            throw new DAOException("Error en transaccion", ex);
        } finally {
            ses.close();
            sessionFac.close();
        }
        return lista;
    }

    @Override
    public Facturadetalle obtener(Integer id) throws farmacia.hibernate.dao.DAOException {
        Session ses = null;
        Facturadetalle obj = null;
        try {
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            tx = ses.beginTransaction();
            obj = (Facturadetalle) ses.get(Facturadetalle.class, id);
            tx.commit();
        } catch (HibernateException ex) {
            tx.rollback();
            throw new DAOException("Error en transaccion", ex);
        } finally {
            ses.close();
            sessionFac.close();
        }
        return obj;
    }

}
