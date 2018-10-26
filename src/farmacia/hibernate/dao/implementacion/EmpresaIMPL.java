package farmacia.hibernate.dao.implementacion;

import farmacia.hibernate.dao.DAOException;
import farmacia.hibernate.dao.EmpresaDAO;
import farmacia.hibernate.modelo.Empresa;
import farmacia.hibernate.util.NewHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class EmpresaIMPL implements EmpresaDAO {

    private SessionFactory sessionFac;
    private Transaction tx;
    
    EmpresaIMPL(SessionFactory sessionFac) {
        this.sessionFac = sessionFac;
    }

    @Override
    public Integer insertar(Empresa obj) throws farmacia.hibernate.dao.DAOException {
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
    public void modificar(Empresa obj) throws farmacia.hibernate.dao.DAOException {
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
    public void eliminar(Empresa obj) throws farmacia.hibernate.dao.DAOException {
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
    public List<Empresa> obtenertodos() throws farmacia.hibernate.dao.DAOException {
         List<Empresa> lista = new ArrayList<>();
        Session ses = null;
        try{
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            tx = ses.beginTransaction();
            lista = ses.createQuery("from Empresa where status = 1").list();
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
    public Empresa obtener(Integer id) throws farmacia.hibernate.dao.DAOException {
        Session ses = null;
        Empresa obj = null;
        try{
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            tx = ses.beginTransaction();
            obj = (Empresa) ses.get(Empresa.class, id);
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
