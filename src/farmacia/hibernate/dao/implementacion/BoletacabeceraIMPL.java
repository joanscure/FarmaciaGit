package farmacia.hibernate.dao.implementacion;

import farmacia.hibernate.dao.BoletacabeceraDAO;
import farmacia.hibernate.dao.DAOException;
import farmacia.hibernate.modelo.Boletacabecera;
import farmacia.hibernate.util.NewHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class BoletacabeceraIMPL implements BoletacabeceraDAO {

    private SessionFactory sessionFac;
    private Transaction tx;
    
    BoletacabeceraIMPL(SessionFactory sessionFac) {
        this.sessionFac = sessionFac;
    }

    @Override
    public Integer insertar(Boletacabecera obj) throws DAOException {
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
    public void modificar(Boletacabecera obj) throws DAOException {
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
    public void eliminar(Boletacabecera obj) throws DAOException {
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
    public List<Boletacabecera> obtenertodos() throws DAOException {
        List<Boletacabecera> lista = new ArrayList<>();
        Session ses = null;
        try{
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            tx = ses.beginTransaction();
            lista = ses.createQuery("from Boletacabecera where status = 1").list();
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
    public Boletacabecera obtener(Integer id) throws DAOException {
        Session ses = null;
        Boletacabecera obj = null;
        try{
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            tx = ses.beginTransaction();
            obj = (Boletacabecera) ses.get(Boletacabecera.class, id);
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

    public static void main(String[] args) {
        
    }
}
