package farmacia.hibernate.dao.implementacion;

import farmacia.hibernate.dao.DAOException;
import farmacia.hibernate.dao.EmpleadoDAO;
import farmacia.hibernate.modelo.Empleado;
import farmacia.hibernate.modelo.Persona;
import farmacia.hibernate.util.NewHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmpleadoIMPL implements EmpleadoDAO {

    private Session sesion;
    private Transaction tx;

    public EmpleadoIMPL(Session session) {
        this.sesion = sesion;
    }

    public EmpleadoIMPL() {
    }

    @Override
    public Integer insertar(Empleado obj) throws DAOException {
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
    public void modificar(Empleado obj) throws DAOException {
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
    public void eliminar(Empleado obj) throws farmacia.hibernate.dao.DAOException {
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
    public List<Empleado> obtenertodos() throws farmacia.hibernate.dao.DAOException {
        List<Empleado> lista = new ArrayList<>();
        try {
            iniciarOperacion();
            lista = sesion.createQuery("from Empleado where status = 1").list();
            tx.commit();
        } catch (HibernateException ex) {
            manejarExcepcion(ex);
        } finally {
            sesion.close();
        }
        return lista;
    }

    @Override
    public Empleado obtener(Integer id) throws farmacia.hibernate.dao.DAOException {
        Empleado obj = null;
        try {
            iniciarOperacion();
            obj = (Empleado) sesion.get(Empleado.class, id);
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
    public void insertarNuevo(Persona per, Empleado emp) throws DAOException {
        try {
            
            iniciarOperacion();
            TipotrabajadorIMPL trabajador = new TipotrabajadorIMPL(sesion);
            PersonaIMPL persona = new PersonaIMPL(sesion);
            
            if(trabajador.obtener(emp.getTipotrabajador().getIdtipotrabajador()) == null){
                throw new DAOException("No existe clave foranea");
            }
            
            Integer idPersona = persona.insertar(per);
            emp.getPersona().setIdpersona(idPersona);
            insertar(emp);
            
            tx.commit();

        } catch (HibernateException ex) {
            manejarExcepcion(ex);

        } finally {
            sesion.close();
        }
    }

    @Override
    public void actualizarpassword(Empleado obj) throws DAOException {
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
    public String obtenerOcupacion(Empleado obj) throws DAOException {
        return obj.getTipotrabajador().getNombretipotrabajador();
    }

}
