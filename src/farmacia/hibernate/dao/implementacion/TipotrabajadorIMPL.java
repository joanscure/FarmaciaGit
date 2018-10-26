package farmacia.hibernate.dao.implementacion;

import farmacia.hibernate.dao.DAOException;
import farmacia.hibernate.dao.TipotrabajadorDAO;
import farmacia.hibernate.modelo.Tipotrabajador;
import farmacia.hibernate.util.NewHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class TipotrabajadorIMPL implements TipotrabajadorDAO {

    private Session sesion;
    private Transaction tx;

    public TipotrabajadorIMPL(Session session) {
        this.sesion = sesion;
    }

    public TipotrabajadorIMPL() {
    }

    @Override
    public Integer insertar(Tipotrabajador obj) throws DAOException {
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
    public void modificar(Tipotrabajador obj) throws DAOException {
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
    public void eliminar(Tipotrabajador obj) throws farmacia.hibernate.dao.DAOException {
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
    public List<Tipotrabajador> obtenertodos() throws farmacia.hibernate.dao.DAOException {
        List<Tipotrabajador> lista = new ArrayList<>();
        try {
            iniciarOperacion();
            lista = sesion.createQuery("from Tipotrabajador where status = 1").list();
            tx.commit();
        } catch (HibernateException ex) {
            manejarExcepcion(ex);
        } finally {
            sesion.close();
        }
        return lista;
    }

    @Override
    public Tipotrabajador obtener(Integer id) throws farmacia.hibernate.dao.DAOException {
        Tipotrabajador obj = null;
        try {
            iniciarOperacion();
            obj = (Tipotrabajador) sesion.get(Tipotrabajador.class, id);
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
