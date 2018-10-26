package farmacia.hibernate.dao.implementacion;

import farmacia.jdbc.dao.DAOException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class UtilIMPL { 
    //CLASE UTIL PARA USAR METODOS QUE SE REPITEN CONSTANTEMENTE
    public static void cerrar(SessionFactory sessionFac, Session session) throws DAOException {
        try{
            if(!sessionFac.isClosed()){
                sessionFac.close();
            }
            if(session.isOpen()){
                session.close();
            }
        }catch(HibernateException ex){
            throw new DAOException("Error al cerrar session.", ex);
        }
    }
    
    public static void cerrar(SessionFactory sessionFac) throws DAOException {
        try{
            if(!sessionFac.isClosed()){
                sessionFac.close();
            }
        }catch(HibernateException ex){
            throw new DAOException("Error al cerrar session.", ex);
        }
    }
    
    public static void cerrar(Session session) throws DAOException {
        try{
            if(session.isOpen()){
                session.close();
            }
        }catch(HibernateException ex){
            throw new DAOException("Error al cerrar session.", ex);
        }
    }
    
    public static void manejarExcepcion(Transaction tx) throws DAOException 
    { 
        tx.rollback(); 
        throw new DAOException("Ocurrió un error en la capa de acceso a datos."); 
    } 
    
}
