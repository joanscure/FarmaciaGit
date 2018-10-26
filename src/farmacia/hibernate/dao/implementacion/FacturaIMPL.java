/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.hibernate.dao.implementacion;

import farmacia.hibernate.dao.DAOException;
import farmacia.hibernate.dao.DAOManager;
import farmacia.hibernate.dao.FacturaDAO;
import farmacia.hibernate.modelo.Facturacabecera;
import farmacia.hibernate.modelo.Facturadetalle;
import farmacia.hibernate.modelo.Empleado;
import farmacia.hibernate.modelo.Empresa;
import farmacia.hibernate.modelo.Empresacliente;
import farmacia.hibernate.modelo.Factura;
import farmacia.hibernate.modelo.Producto;
import farmacia.hibernate.util.NewHibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class FacturaIMPL implements FacturaDAO {

    private Session sesion;
    private Transaction tx;

    public FacturaIMPL(Session sesion) {
        this.sesion = sesion;
    }

    public FacturaIMPL() {
    }

    @Override
    public Integer insertar(Factura obj) throws DAOException {
        Integer id = null;
        try {
            iniciarOperacion();
            id = (Integer) sesion.save(obj.getCabecera());
            List<Facturadetalle> lista = obj.getDetalle();
            for (Facturadetalle i : lista) {
                i.setFacturacabecera(obj.getCabecera());
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
    public void modificar(Factura obj) throws DAOException {
        try {
            iniciarOperacion();
            sesion.update(obj.getCabecera());
            List<Facturadetalle> lista = obj.getDetalle();
            for (Facturadetalle i : lista) {
                i.setFacturacabecera(obj.getCabecera());
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
    public void eliminar(Factura obj) throws farmacia.hibernate.dao.DAOException {
        try {
            iniciarOperacion();
            obj.getCabecera().setStatus(false);
            sesion.update(obj.getCabecera());
            List<Facturadetalle> lista = obj.getDetalle();
            for (Facturadetalle i : lista) {
                i.setFacturacabecera(obj.getCabecera());
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
    public List<Factura> obtenertodos() throws farmacia.hibernate.dao.DAOException {
        List<Facturacabecera> cabeceras = new ArrayList<>();
        List<Facturadetalle> detalles = new ArrayList<>();
        List<Factura> Facturas = new ArrayList<>();
        try {
            iniciarOperacion();
            cabeceras = sesion.createQuery("from Facturacabecera where status = 1").list();
            for (Facturacabecera i : cabeceras) {
                detalles = sesion.createQuery("from Facturadetalle where idFacturacabecera = "
                        + "'" + i.getIdfacturacabecera() + "'").list();
                Facturas.add(new Factura(i, detalles));
            }
            tx.commit();
        } catch (HibernateException ex) {
            manejarExcepcion(ex);
        } finally {
            sesion.close();
        }
        return Facturas;
    }

    @Override
    public Factura obtener(Integer id) throws farmacia.hibernate.dao.DAOException {
        Factura obj = null;
        try {
            iniciarOperacion();
            Facturacabecera cab = null;
            List<Facturadetalle> det = new ArrayList<>();
            cab = (Facturacabecera) sesion.get(Facturacabecera.class, id);
            det = sesion.createQuery("from Facturadetalle where idFacturacabecera = "
                    + "'" + id + "'").list();
            obj = new Factura(cab, det);
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

        Empresa emp = new Empresa("123455677", "232412", "123251", "12312312", true);
        Empresacliente cliente = new Empresacliente(emp, true);
        
        Integer idCliente = man.getEmpresaClienteDAO().insertarNuevo(cliente, emp);
        cliente.setIdempresacliente(idCliente);
        List<Facturadetalle> lista = new ArrayList<>();
        Producto producto = man.getProductoDAO().obtener(1);
        for (int i = 0; i < 10; i++) {
            Facturadetalle det;
            det = new Facturadetalle(null, producto, 12, 12, true);
            lista.add(det);
        }
        Facturacabecera cab = new Facturacabecera(empleado, cliente, "2222", "12345678", new Date(213213L), true);
        Factura bol = new Factura(cab, lista);
        man.getFactura().insertar(bol);
        System.out.println("--------------------------");
    }

}
