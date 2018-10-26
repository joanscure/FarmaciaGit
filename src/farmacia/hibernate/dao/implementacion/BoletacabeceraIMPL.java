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

    private Session sesion;
    private Transaction tx;

    public BoletacabeceraIMPL(Session sesion) {
        this.sesion = sesion;
    }

    public BoletacabeceraIMPL() {
    }

    @Override
    public Integer insertar(Boletacabecera obj) throws DAOException {
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
    public void modificar(Boletacabecera obj) throws DAOException {
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
    public void eliminar(Boletacabecera obj) throws farmacia.hibernate.dao.DAOException {
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
    public List<Boletacabecera> obtenertodos() throws farmacia.hibernate.dao.DAOException {
        List<Boletacabecera> lista = new ArrayList<>();
        try {
            iniciarOperacion();
            lista = sesion.createQuery("from Boletacabecera where status = 1").list();
            tx.commit();
        } catch (HibernateException ex) {
            manejarExcepcion(ex);
        } finally {
            sesion.close();
        }
        return lista;
    }

    @Override
    public Boletacabecera obtener(Integer id) throws farmacia.hibernate.dao.DAOException {
        Boletacabecera obj = null;
        try {
            iniciarOperacion();
            obj = (Boletacabecera) sesion.get(Boletacabecera.class, id);
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
