package farmacia.hibernate.dao.implementacion;

import farmacia.hibernate.dao.DAOException;
import farmacia.hibernate.dao.DescuentoDAO;
import farmacia.hibernate.modelo.Descuento;
import farmacia.hibernate.util.NewHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DescuentoIMPL implements DescuentoDAO {

    private Session sesion;
    private Transaction tx;

    public DescuentoIMPL(Session sesion) {
        this.sesion = sesion;
    }

    public DescuentoIMPL() {
    }

    @Override
    public Integer insertar(Descuento obj) throws DAOException {
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
    public void modificar(Descuento obj) throws DAOException {
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
    public void eliminar(Descuento obj) throws farmacia.hibernate.dao.DAOException {
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
    public List<Descuento> obtenertodos() throws farmacia.hibernate.dao.DAOException {
        List<Descuento> lista = new ArrayList<>();
        try {
            iniciarOperacion();
            lista = sesion.createQuery("from Descuento where status = 1").list();
            tx.commit();
        } catch (HibernateException ex) {
            manejarExcepcion(ex);
        } finally {
            sesion.close();
        }
        return lista;
    }

    @Override
    public Descuento obtener(Integer id) throws farmacia.hibernate.dao.DAOException {
        Descuento obj = null;
        try {
            iniciarOperacion();
            obj = (Descuento) sesion.get(Descuento.class, id);
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

    
}
