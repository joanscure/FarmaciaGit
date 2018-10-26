/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.hibernate.dao.implementacion;

import farmacia.hibernate.dao.BoletaDAO;
import farmacia.hibernate.dao.DAOException;
import farmacia.hibernate.dao.DAOManager;
import farmacia.hibernate.modelo.Boleta;
import farmacia.hibernate.modelo.Boletacabecera;
import farmacia.hibernate.modelo.Boletadetalle;
import farmacia.hibernate.modelo.Empleado;
import farmacia.hibernate.modelo.Persona;
import farmacia.hibernate.modelo.Personacliente;
import farmacia.hibernate.modelo.Producto;
import farmacia.hibernate.util.NewHibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BoletaIMPL implements BoletaDAO {

    private Session sesion;
    private Transaction tx;

    public BoletaIMPL(Session sesion) {
        this.sesion = sesion;
    }

    public BoletaIMPL() {
    }

    @Override
    public Integer insertar(Boleta obj) throws DAOException {
        Integer id = null;
        try {
            iniciarOperacion();
            id = (Integer) sesion.save(obj.getCabecera());
            List<Boletadetalle> lista = obj.getDetalle();
            for (Boletadetalle i : lista) {
                i.setBoletacabecera(obj.getCabecera());
                sesion.save(i);
            }

            tx.commit();
        } catch (HibernateException ex) {
            manejarExcepcion(ex);
        } finally {
            sesion.close();
        }
        return id;
    }

    @Override
    public void modificar(Boleta obj) throws DAOException {
        try {
            iniciarOperacion();
            sesion.update(obj.getCabecera());
            List<Boletadetalle> lista = obj.getDetalle();
            for (Boletadetalle i : lista) {
                i.setBoletacabecera(obj.getCabecera());
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
    public void eliminar(Boleta obj) throws farmacia.hibernate.dao.DAOException {
        try {
            iniciarOperacion();
            obj.getCabecera().setStatus(false);
            sesion.update(obj.getCabecera());
            List<Boletadetalle> lista = obj.getDetalle();
            for (Boletadetalle i : lista) {
                i.setBoletacabecera(obj.getCabecera());
                i.setStatus(false);
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
    public List<Boleta> obtenertodos() throws farmacia.hibernate.dao.DAOException {
        List<Boletacabecera> cabeceras = new ArrayList<>();
        List<Boletadetalle> detalles = new ArrayList<>();
        List<Boleta> boletas = new ArrayList<>();
        try {
            iniciarOperacion();
            cabeceras = sesion.createQuery("from Boletacabecera where status = 1").list();
            for (Boletacabecera i : cabeceras) {
                detalles = sesion.createQuery("from Boletadetalle where idboletacabecera = "
                        + "'" + i.getIdboletacabecera() + "'").list();
                boletas.add(new Boleta(i, detalles));
            }
            tx.commit();
        } catch (HibernateException ex) {
            manejarExcepcion(ex);
        } finally {
            sesion.close();
        }
        return boletas;
    }

    @Override
    public Boleta obtener(Integer id) throws farmacia.hibernate.dao.DAOException {
        Boleta obj = null;
        try {
            iniciarOperacion();
            Boletacabecera cab = null;
            List<Boletadetalle> det = new ArrayList<>();
            cab = (Boletacabecera) sesion.get(Boletacabecera.class, id);
            det = sesion.createQuery("from Boletadetalle where idboletacabecera = "
                    + "'" + id + "'").list();
            obj = new Boleta(cab, det);
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

    public static void main(String[] args) throws DAOException {
        DAOManager man = new DAOManagerIMPL();
        Empleado empleado = man.getEmpleadoDAO().obtener(1);

        Persona per = new Persona("jose", "miguel", "summer", "12345678", true);
        Personacliente cliente = new Personacliente(per, true);
        Integer idCliente = man.getPersonaClienteDAO().ingresarNuevo(cliente, per);
        cliente.setIdpersonacliente(idCliente);

        List<Boletadetalle> lista = new ArrayList<>();
        Producto producto = man.getProductoDAO().obtener(1);

        for (int i = 0; i < 10; i++) {
            Boletadetalle det;
            det = new Boletadetalle(null, producto, 12, 12, true);
            lista.add(det);
        }

        Boletacabecera cab = new Boletacabecera(empleado, cliente, "2222", "12345678", new Date(213213L), true);

        Boleta bol = new Boleta(cab, lista);
        man.getBoleta().insertar(bol);
        man.getBoleta().eliminar(man.getBoleta().obtener(7));

        System.out.println("--------------------------");
    }

}
