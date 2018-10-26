package farmacia.hibernate.dao.implementacion;

import farmacia.hibernate.dao.DAOException;
import farmacia.hibernate.dao.DAOManager;
import farmacia.hibernate.dao.PersonaclienteDAO;
import farmacia.hibernate.modelo.Persona;
import farmacia.hibernate.modelo.Personacliente;
import farmacia.hibernate.util.NewHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PersonaclienteIMPL implements PersonaclienteDAO{

    private Session sesion;
    private Transaction tx;

    public PersonaclienteIMPL(Session session) {
        this.sesion = sesion;
    }

    public PersonaclienteIMPL() {
    }

    @Override
    public Integer insertar(Personacliente obj) throws DAOException {
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
    public void modificar(Personacliente obj) throws DAOException {
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
    public void eliminar(Personacliente obj) throws farmacia.hibernate.dao.DAOException {
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
    public List<Personacliente> obtenertodos() throws farmacia.hibernate.dao.DAOException {
        List<Personacliente> lista = new ArrayList<>();
        try {
            iniciarOperacion();
            lista = sesion.createQuery("from Personacliente where status = 1").list();
            tx.commit();
        } catch (HibernateException ex) {
            manejarExcepcion(ex);
        } finally {
            sesion.close();
        }
        return lista;
    }

    @Override
    public Personacliente obtener(Integer id) throws farmacia.hibernate.dao.DAOException {
        Personacliente obj = null;
        try {
            iniciarOperacion();
            obj = (Personacliente) sesion.get(Personacliente.class, id);
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
    public Integer ingresarNuevo(Personacliente cliente, Persona per) throws DAOException {
        Integer id = null; 
        try {

            iniciarOperacion();
            PersonaIMPL persona = new PersonaIMPL();

            Integer idPersona = persona.insertar(per);
            
            cliente.getPersona().setIdpersona(idPersona);

            id = insertar(cliente);

            tx.commit();

        } catch (HibernateException ex) {
            manejarExcepcion(ex);

        } finally {
            sesion.close();
        }
        return id;
    }
    
    public static void main(String[] args) throws DAOException {
        DAOManager man = new DAOManagerIMPL(); 
        
        Persona per = new Persona("jose", "miguel", "summer", "12345678", true);
        
        
        Personacliente cliente = new Personacliente(per, true);
        Integer idCliente = man.getPersonaClienteDAO().insertar(cliente);
        cliente.setIdpersonacliente(idCliente);
        System.out.println("asdasda");
    }

}
