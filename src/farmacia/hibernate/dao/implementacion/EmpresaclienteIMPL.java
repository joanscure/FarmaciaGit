package farmacia.hibernate.dao.implementacion;

import farmacia.hibernate.dao.DAOException;
import farmacia.hibernate.dao.EmpresaclienteDAO;
import farmacia.hibernate.modelo.Empresa;
import farmacia.hibernate.modelo.Empresacliente;
import farmacia.hibernate.util.NewHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class EmpresaclienteIMPL implements EmpresaclienteDAO {

    private SessionFactory sessionFac;
    private Transaction tx;

    EmpresaclienteIMPL(SessionFactory sessionFac) {
        this.sessionFac = sessionFac;
    }

    @Override
    public Integer insertar(Empresacliente obj) throws farmacia.hibernate.dao.DAOException {
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
    public void modificar(Empresacliente obj) throws farmacia.hibernate.dao.DAOException {
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
    public void eliminar(Empresacliente obj) throws farmacia.hibernate.dao.DAOException {
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
    public List<Empresacliente> obtenertodos() throws farmacia.hibernate.dao.DAOException {
        List<Empresacliente> lista = new ArrayList<>();
        Session ses = null;
        try {
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            tx = ses.beginTransaction();
            lista = ses.createQuery("from Empresacliente where status = 1").list();
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
    public Empresacliente obtener(Integer id) throws farmacia.hibernate.dao.DAOException {
        Session ses = null;
        Empresacliente obj = null;
        try {
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            tx = ses.beginTransaction();
            obj = (Empresacliente) ses.get(Empresacliente.class, id);
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

    @Override
    public void insertarNuevo(Empresacliente cliente, Empresa emp) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
