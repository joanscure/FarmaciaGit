package farmacia.hibernate.dao.implementacion;

import farmacia.hibernate.dao.BoletadetalleDAO;
import farmacia.hibernate.dao.DAOException;
import farmacia.hibernate.modelo.Boletadetalle;
import farmacia.hibernate.util.NewHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class BoletadetalleIMPL implements BoletadetalleDAO {

    private SessionFactory sessionFac;
    private Transaction tx;

    public BoletadetalleIMPL(SessionFactory sessionFac) {
        this.sessionFac = sessionFac;
    }
    
    @Override
    public void eliminarDetallesBoleta(Integer idBoletacabecera) throws farmacia.hibernate.dao.DAOException {
        Session ses = null;
        try{
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            tx = ses.beginTransaction();
            List<Boletadetalle> lista = obtenerDetallesBoleta(idBoletacabecera);
            for (Boletadetalle i : lista) {
                eliminar(i);
            }
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
    public List<Boletadetalle> obtenerDetallesBoleta(Integer idBoletacabecera) throws farmacia.hibernate.dao.DAOException {
        List<Boletadetalle> lista = new ArrayList<>();
        Session ses = null;
        try{
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            tx = ses.beginTransaction();
            lista = ses.createQuery("from Boletadetalle where idboletacabecera = "+ 
                    "'"+idBoletacabecera.toString()+"'").list();
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
    public Integer insertar(Boletadetalle obj) throws farmacia.hibernate.dao.DAOException {
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
    public void modificar(Boletadetalle obj) throws farmacia.hibernate.dao.DAOException {
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
    public void eliminar(Boletadetalle obj) throws farmacia.hibernate.dao.DAOException {
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
    public List<Boletadetalle> obtenertodos() throws farmacia.hibernate.dao.DAOException {
        List<Boletadetalle> lista = new ArrayList<>();
        Session ses = null;
        try{
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            tx = ses.beginTransaction();
            lista = ses.createQuery("from Boletadetalle where status = 1").list();
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
    public Boletadetalle obtener(Integer id) throws farmacia.hibernate.dao.DAOException {
        Session ses = null;
        Boletadetalle obj = null;
        try{
            sessionFac = NewHibernateUtil.getSessionFactory();
            ses = sessionFac.openSession();
            tx = ses.beginTransaction();
            obj = (Boletadetalle) ses.get(Boletadetalle.class, id);
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
