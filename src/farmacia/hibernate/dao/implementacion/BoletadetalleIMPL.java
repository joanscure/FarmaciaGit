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

    private Session sesion;
    private Transaction tx;

    public BoletadetalleIMPL(Session session) {
        this.sesion = sesion;
    }

    public BoletadetalleIMPL() {
    }

    @Override
    public Integer insertar(Boletadetalle obj) throws DAOException {
        Integer id = null;
        try {
            iniciarOperacion();
            id = (Integer) sesion.save(obj);
            tx.commit();

        } catch (HibernateException ex) {
            manejarExcepcion(ex);

        } finally {
            sesion.close();
        }
        return id;
    }

    @Override
    public void modificar(Boletadetalle obj) throws DAOException {
        try {
            iniciarOperacion();
            sesion.update(obj);
            tx.commit();

        } catch (HibernateException ex) {
            manejarExcepcion(ex);

        } finally {
            sesion.close();
        }
    }

    @Override
    public void eliminar(Boletadetalle obj) throws farmacia.hibernate.dao.DAOException {
        try {
            obj.setStatus(false);
            iniciarOperacion();
            sesion.update(obj);
            tx.commit();

        } catch (HibernateException ex) {
            manejarExcepcion(ex);

        } finally {
            sesion.close();
        }
    }

    @Override
    public List<Boletadetalle> obtenertodos() throws farmacia.hibernate.dao.DAOException {
        List<Boletadetalle> lista = new ArrayList<>();
        try {
            iniciarOperacion();
            lista = sesion.createQuery("from Boletadetalle where status = 1").list();
            tx.commit();
        } catch (HibernateException ex) {
            manejarExcepcion(ex);
        } finally {
            sesion.close();
        }
        return lista;
    }

    @Override
    public Boletadetalle obtener(Integer id) throws farmacia.hibernate.dao.DAOException {
        Boletadetalle obj = null;
        try {
            iniciarOperacion();
            obj = (Boletadetalle) sesion.get(Boletadetalle.class, id);
            tx.commit();
        } catch (HibernateException ex) {
            manejarExcepcion(ex);
        } finally {
            sesion.close();
        }

        return obj;
    }

    @Override
    public void iniciarOperacion() throws DAOException {
        try {
            sesion = NewHibernateUtil.getSessionFactory().openSession();
            tx = sesion.beginTransaction();
        } catch (HibernateException ex) {
            throw new DAOException("Error en transferencia.", ex);
        }
    }

    @Override
    public void manejarExcepcion(HibernateException ex) throws DAOException {
        tx.rollback();
        throw new DAOException("Error en transferencia.", ex);
    }

    @Override
    public void eliminarDetallesBoleta(Integer idBoletacabecera) throws DAOException {
        try {
            
            iniciarOperacion();
            List<Boletadetalle> lista; 
            lista = sesion.createQuery("from Boletadetalle where idboletacabecera = "
                    +"'"+idBoletacabecera+"' and status = 1").list();
            for (Boletadetalle i : lista){
                i.setStatus(true);
                sesion.update(i);
            }
            tx.commit();
        } catch (HibernateException ex) {
            manejarExcepcion(ex);

        } finally {
            sesion.close();
        }
    }

    @Override
    public List<Boletadetalle> obtenerDetallesBoleta(Integer idBoletacabecera) throws DAOException {
        List<Boletadetalle> lista = new ArrayList<>();
        try {
            iniciarOperacion();
            lista = sesion.createQuery("from Boletadetalle where idboletacabecera = "
                    +"'"+idBoletacabecera+"' and status = 1").list();
            tx.commit();
        } catch (HibernateException ex) {
            manejarExcepcion(ex);
        } finally {
            sesion.close();
        }
        return lista;
    }
}
