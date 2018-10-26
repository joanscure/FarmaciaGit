package farmacia.hibernate.dao.implementacion;

import farmacia.hibernate.dao.DAOException;
import farmacia.hibernate.dao.EmpresaclienteDAO;
import farmacia.hibernate.modelo.Empresa;
import farmacia.hibernate.modelo.Empresacliente;
import farmacia.hibernate.util.NewHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmpresaclienteIMPL implements EmpresaclienteDAO {

    private Session sesion;
    private Transaction tx;

    public EmpresaclienteIMPL(Session sesion) {
        this.sesion = sesion;
    }

    public EmpresaclienteIMPL() {
    }

    @Override
    public Integer insertar(Empresacliente obj) throws DAOException {
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
    public void modificar(Empresacliente obj) throws DAOException {
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
    public void eliminar(Empresacliente obj) throws farmacia.hibernate.dao.DAOException {
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
    public List<Empresacliente> obtenertodos() throws farmacia.hibernate.dao.DAOException {
        List<Empresacliente> lista = new ArrayList<>();
        try {
            iniciarOperacion();
            lista = sesion.createQuery("from Empresacliente where status = 1").list();
            tx.commit();
        } catch (HibernateException ex) {
            manejarExcepcion(ex);
        } finally {
            sesion.close();
        }
        return lista;
    }

    @Override
    public Empresacliente obtener(Integer id) throws farmacia.hibernate.dao.DAOException {
        Empresacliente obj = null;
        try {
            iniciarOperacion();
            obj = (Empresacliente) sesion.get(Empresacliente.class, id);
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
    public Integer insertarNuevo(Empresacliente cliente, Empresa emp) throws DAOException {
        Integer id = null;
        try {
            iniciarOperacion();
            Integer idEmpresa = (Integer) sesion.save(emp);
            cliente.setIdempresacliente(idEmpresa);
            id = (Integer) sesion.save(cliente);
            tx.commit();
        } catch (HibernateException ex) {
            manejarExcepcion(ex);
        } finally {
            sesion.close();
        }
        return id;
    }
    
    public static void main(String[] args) throws DAOException {
        Empresa emp = new Empresa("123456", "2312321", "3123123", "2131231", true);
        Empresacliente ciente = new Empresacliente(emp, true);
        EmpresaclienteIMPL ap = new EmpresaclienteIMPL();
        System.out.println(ap.insertarNuevo(ciente, emp));
    }

}
