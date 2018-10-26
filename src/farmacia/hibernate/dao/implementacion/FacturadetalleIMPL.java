package farmacia.hibernate.dao.implementacion;

import farmacia.hibernate.dao.DAOException;
import farmacia.hibernate.dao.FacturadetalleDAO;
import farmacia.hibernate.modelo.Facturadetalle;
import farmacia.hibernate.util.NewHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class FacturadetalleIMPL implements FacturadetalleDAO {

    private Session sesion;
    private Transaction tx;

    public FacturadetalleIMPL(Session session) {
        this.sesion = sesion;
    }

    public FacturadetalleIMPL() {
    }

    @Override
    public Integer insertar(Facturadetalle obj) throws DAOException {
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
    public void modificar(Facturadetalle obj) throws DAOException {
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
    public void eliminar(Facturadetalle obj) throws farmacia.hibernate.dao.DAOException {
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
    public List<Facturadetalle> obtenertodos() throws farmacia.hibernate.dao.DAOException {
        List<Facturadetalle> lista = new ArrayList<>();
        try {
            iniciarOperacion();
            lista = sesion.createQuery("from Facturadetalle where status = 1").list();
            tx.commit();
        } catch (HibernateException ex) {
            manejarExcepcion(ex);
        } finally {
            sesion.close();
        }
        return lista;
    }

    @Override
    public Facturadetalle obtener(Integer id) throws farmacia.hibernate.dao.DAOException {
        Facturadetalle obj = null;
        try {
            iniciarOperacion();
            obj = (Facturadetalle) sesion.get(Facturadetalle.class, id);
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
    public void eliminarDetallesFactura(Integer idFacturacabecera) throws DAOException {
        try {
            
            iniciarOperacion();
            List<Facturadetalle> lista = obtenerDetallesFactura(idFacturacabecera);
            for (Facturadetalle i : lista){
                eliminar(obtener(i.getIdfacturadetalle()));
            }
            tx.commit();

        } catch (HibernateException ex) {
            manejarExcepcion(ex);

        } finally {
            sesion.close();
        }
    }

    @Override
    public List<Facturadetalle> obtenerDetallesFactura(Integer idFacturacabecera) throws DAOException {
        List<Facturadetalle> lista = new ArrayList<>();
        try {
            iniciarOperacion();
            lista = sesion.createQuery("from Facturadetalle where idFacturacabecera = "
                    +"'"+idFacturacabecera+"' and status = 1").list();
            tx.commit();
        } catch (HibernateException ex) {
            manejarExcepcion(ex);
        } finally {
            sesion.close();
        }
        return lista;
    }

}
