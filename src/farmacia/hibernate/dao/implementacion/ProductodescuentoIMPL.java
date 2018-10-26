package farmacia.hibernate.dao.implementacion;

import farmacia.hibernate.dao.ProductodescuentoDAO;
import farmacia.hibernate.modelo.Productodescuento;
import farmacia.hibernate.util.NewHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ProductodescuentoIMPL implements ProductodescuentoDAO {

    private SessionFactory sessionFac;
    private Transaction tx;

    public ProductodescuentoIMPL(SessionFactory sessionFac) {
        this.sessionFac = this.sessionFac;
    }

    @Override
    public Integer insertar(Productodescuento obj) throws farmacia.hibernate.dao.DAOException {
        Integer id = null;
        Session ses = null;
        try{
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            tx = ses.beginTransaction();
            id = (Integer) ses.save(obj);
            tx.commit();
            
            
        } catch (HibernateException ex){
            tx.rollback();
            throw new farmacia.hibernate.dao.DAOException("Error en transaccion",ex);
        } 
        
        finally{
            ses.close();
            sessionFac.close();
        }
        return id;
    }

    @Override
    public void modificar(Productodescuento obj) throws farmacia.hibernate.dao.DAOException {
        Session ses = null;
        try{
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            tx = ses.beginTransaction();
            ses.update(obj);
            tx.commit();
        } catch (HibernateException ex){
            tx.rollback();
            throw new farmacia.hibernate.dao.DAOException("Error en transaccion",ex);
        } 
        
        finally{
            ses.close();
            sessionFac.close();
        }
    }

    @Override
    public void eliminar(Productodescuento obj) throws farmacia.hibernate.dao.DAOException {
        Session ses = null;
        try{
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            obj.setStatus(false);
            tx = ses.beginTransaction();
            ses.update(obj);
            tx.commit();
        } catch (HibernateException ex){
            tx.rollback();
            throw new farmacia.hibernate.dao.DAOException("Error en transaccion",ex);
        } 
        
        finally{
            ses.close();
            sessionFac.close();
        }
    }

    @Override
    public List<Productodescuento> obtenertodos() throws farmacia.hibernate.dao.DAOException {
        List<Productodescuento> lista = new ArrayList<>();
        Session ses = null;
        try{
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            tx = ses.beginTransaction();
            lista = ses.createQuery("from Productodescuento where status = 1").list();
            tx.commit();
        } catch (HibernateException ex){
            tx.rollback();
            throw new farmacia.hibernate.dao.DAOException("Error en transaccion",ex);
        } 
        
        finally{
            ses.close();
            sessionFac.close();
        }
        return lista;
    }

    @Override
    public Productodescuento obtener(Integer id) throws farmacia.hibernate.dao.DAOException {
        Session ses = null;
        Productodescuento obj = null;
        try{
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            tx = ses.beginTransaction();
            obj = (Productodescuento) ses.get(Productodescuento.class, id);
            tx.commit();
        } catch (HibernateException ex){
            tx.rollback();
            throw new farmacia.hibernate.dao.DAOException("Error en transaccion",ex);
        } 
        
        finally{
            ses.close();
            sessionFac.close();
        }
        return obj;
    }

}
