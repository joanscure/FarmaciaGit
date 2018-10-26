package farmacia.hibernate.dao.implementacion;

import farmacia.hibernate.dao.DAOException;
import farmacia.hibernate.dao.PersonaDAO;
import farmacia.hibernate.modelo.Persona;
import farmacia.hibernate.util.NewHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class PersonaIMPL implements PersonaDAO {

    private Session sesion;
    private Transaction tx;

    public PersonaIMPL(Session session) {
        this.sesion = sesion;
    }

    public PersonaIMPL() {
    }

    @Override
    public Integer insertar(Persona obj) throws DAOException {
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
    public void modificar(Persona obj) throws DAOException {
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
    public void eliminar(Persona obj) throws farmacia.hibernate.dao.DAOException {
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
    public List<Persona> obtenertodos() throws farmacia.hibernate.dao.DAOException {
        List<Persona> lista = new ArrayList<>();
        try {
            iniciarOperacion();
            lista = sesion.createQuery("from Persona where status = 1").list();
            tx.commit();
        } catch (HibernateException ex) {
            manejarExcepcion(ex);
        } finally {
            sesion.close();
        }
        return lista;
    }

    @Override
    public Persona obtener(Integer id) throws farmacia.hibernate.dao.DAOException {
        Persona obj = null;
        try {
            iniciarOperacion();
            obj = (Persona) sesion.get(Persona.class, id);
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

    public static void main(String[] args) {
        PersonaIMPL ap = new PersonaIMPL();
        Persona p = new Persona("Carlos", "Beblera", "Chigon", "12345678", true);
        try {
            ap.insertar(p);
            ap.eliminar(ap.obtener(1));
            List<Persona> lista = ap.obtenertodos();
            for (Persona i : lista) {
                System.out.println(i.getIdpersona() + " " + i.getNombre());
            }
        } catch (DAOException ex) {
            Logger.getLogger(PersonaIMPL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            NewHibernateUtil.cerrar();
        }

    }

}
