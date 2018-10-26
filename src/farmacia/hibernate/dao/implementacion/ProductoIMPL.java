package farmacia.hibernate.dao.implementacion;

import farmacia.hibernate.dao.DAOException;
import farmacia.hibernate.dao.ProductoDAO;
import farmacia.hibernate.modelo.Producto;
import farmacia.hibernate.util.NewHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ProductoIMPL implements ProductoDAO {

   private SessionFactory sessionFac;
    private Transaction tx;

    public ProductoIMPL(SessionFactory sessionFac) {
        this.sessionFac = this.sessionFac;
    }

    @Override
    public Integer insertar(Producto obj) throws DAOException {
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
            throw new DAOException("Error en transaccion",ex);
        } 
        
        finally{
            ses.close();
            sessionFac.close();
        }
        return id;
    }

    @Override
    public void modificar(Producto obj) throws DAOException {
        Session ses = null;
        try{
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            tx = ses.beginTransaction();
            ses.update(obj);
            tx.commit();
        } catch (HibernateException ex){
            tx.rollback();
            throw new DAOException("Error en transaccion",ex);
        } 
        
        finally{
            ses.close();
            sessionFac.close();
        }
    }

    @Override
    public void eliminar(Producto obj) throws farmacia.hibernate.dao.DAOException {
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
            throw new DAOException("Error en transaccion",ex);
        } 
        
        finally{
            ses.close();
            sessionFac.close();
        }
    }

    @Override
    public List<Producto> obtenertodos() throws farmacia.hibernate.dao.DAOException {
        List<Producto> lista = new ArrayList<>();
        Session ses = null;
        try{
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            tx = ses.beginTransaction();
            lista = ses.createQuery("from Producto where status = 1").list();
            tx.commit();
        } catch (HibernateException ex){
            tx.rollback();
            throw new DAOException("Error en transaccion",ex);
        } 
        
        finally{
            ses.close();
            sessionFac.close();
        }
        return lista;
    }

    @Override
    public Producto obtener(Integer id) throws farmacia.hibernate.dao.DAOException {
        Session ses = null;
        Producto obj = null;
        try{
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            tx = ses.beginTransaction();
            obj = (Producto) ses.get(Producto.class, id);
            tx.commit();
        } catch (HibernateException ex){
            tx.rollback();
            throw new DAOException("Error en transaccion",ex);
        } 
        
        finally{
            ses.close();
            sessionFac.close();
        }
        return obj;
    }
}
