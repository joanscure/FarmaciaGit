package farmacia.hibernate.dao.implementacion;

import farmacia.hibernate.dao.DAOException;
import farmacia.hibernate.dao.TipotrabajadorDAO;
import farmacia.hibernate.modelo.Tipotrabajador;
import farmacia.hibernate.util.NewHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class TipotrabajadorIMPL implements TipotrabajadorDAO {

    private SessionFactory sessionFac;
    private Transaction tx;

    public TipotrabajadorIMPL(SessionFactory sessionFac) {
        this.sessionFac = this.sessionFac;
    }

    @Override
    public Integer insertar(Tipotrabajador obj) throws DAOException {
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
    public void modificar(Tipotrabajador obj) throws DAOException {
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
    public void eliminar(Tipotrabajador obj) throws farmacia.hibernate.dao.DAOException {
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
    public List<Tipotrabajador> obtenertodos() throws farmacia.hibernate.dao.DAOException {
        List<Tipotrabajador> lista = new ArrayList<>();
        Session ses = null;
        try {
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            tx = ses.beginTransaction();
            lista = ses.createQuery("from Tipotrabajador where status = 1").list();
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
    public Tipotrabajador obtener(Integer id) throws farmacia.hibernate.dao.DAOException {
        Session ses = null;
        Tipotrabajador obj = null;
        try{
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            ses.beginTransaction();
            obj = (Tipotrabajador) ses.get(Tipotrabajador.class, id);
            ses.getTransaction().commit();
        } catch (HibernateException ex){
            if (ses.getTransaction().isActive()){
                ses.getTransaction().rollback();
            }
            throw new DAOException("Error en transaccion",ex);
        } finally{
            ses.close();
        }
        
        return obj;
    }
}
