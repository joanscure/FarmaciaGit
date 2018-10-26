package farmacia.hibernate.dao.implementacion;

import farmacia.hibernate.dao.DAOException;
import farmacia.hibernate.dao.FacturacabeceraDAO;
import farmacia.hibernate.modelo.Facturacabecera;
import farmacia.hibernate.util.NewHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class FacturacabeceraIMPL implements FacturacabeceraDAO {

    private SessionFactory sessionFac;
    private Transaction tx;

    FacturacabeceraIMPL(SessionFactory sessionFac) {
        this.sessionFac = sessionFac;
    }

    @Override
    public Integer insertar(Facturacabecera obj) throws DAOException {
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
    public void modificar(Facturacabecera obj) throws DAOException {
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
    public void eliminar(Facturacabecera obj) throws DAOException {
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
    public List<Facturacabecera> obtenertodos() throws DAOException {
        List<Facturacabecera> lista = new ArrayList<>();
        Session ses = null;
        try {
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            tx = ses.beginTransaction();
            lista = ses.createQuery("from Facturacabecera where status = 1").list();
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
    public Facturacabecera obtener(Integer id) throws DAOException {
        Session ses = null;
        Facturacabecera obj = null;
        try {
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            tx = ses.beginTransaction();
            obj = (Facturacabecera) ses.get(Facturacabecera.class, id);
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
