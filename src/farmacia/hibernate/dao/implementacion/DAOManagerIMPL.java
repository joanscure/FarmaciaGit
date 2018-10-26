package farmacia.hibernate.dao.implementacion;

import farmacia.hibernate.dao.*;
import farmacia.hibernate.dao.DAOManager;
import farmacia.hibernate.util.NewHibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class DAOManagerIMPL implements DAOManager {

    Session sesion = null;

    private BoletacabeceraDAO boletacab = null;
    private BoletadetalleDAO boletadet = null;
    private DescuentoDAO descuentos = null;
    private EmpleadoDAO empleados = null;
    private EmpresaDAO empresas = null;
    private EmpresaclienteDAO empresascliente = null;
    private FacturacabeceraDAO facturacab = null;
    private FacturadetalleDAO facturadet = null;
    private PersonaDAO personas = null;
    private PersonaclienteDAO personascliente = null;
    private ProductoDAO productos = null;
    private ProductodescuentoDAO productodes = null;
    private TipotrabajadorDAO tipostrabajador = null;
    private BoletaDAO boleta = null;
    private FacturaDAO factura = null;

    public DAOManagerIMPL() throws DAOException {
        try {
            sesion = NewHibernateUtil.getSessionFactory().openSession();
        } catch (HibernateException ex) {
            throw new DAOException("Error al generar SessionFactory.", ex);
        }
    }

    public DAOManagerIMPL(Session session) {
        this.sesion = sesion;
    }

    @Override
    public PersonaDAO getPersonaDAO() {
        if (personas == null) {
            personas = new PersonaIMPL(sesion);
        }
        return personas;
    }

    @Override
    public BoletacabeceraDAO getBoletaCabeceraDAO() {
        if (boletacab == null) {
            boletacab = new BoletacabeceraIMPL(sesion);
        }
        return boletacab;
    }

    @Override
    public BoletadetalleDAO getBoletaDetalleDAO() {
        if (boletadet == null) {
            boletadet = new BoletadetalleIMPL(sesion);
        }
        return boletadet;
    }

    @Override
    public DescuentoDAO getDescuentoDAO() {
        if (descuentos == null) {
            descuentos = new DescuentoIMPL(sesion);
        }
        return descuentos;
    }

    @Override
    public EmpleadoDAO getEmpleadoDAO() {
        if (empleados == null) {
            empleados = new EmpleadoIMPL(sesion);
        }
        return empleados;
    }

    @Override
    public EmpresaDAO getEmpresaDAO() {
        if (empresas == null) {
            empresas = new EmpresaIMPL(sesion);
        }
        return empresas;
    }

    @Override
    public EmpresaclienteDAO getEmpresaClienteDAO() {
        if (empresascliente == null) {
            empresascliente = new EmpresaclienteIMPL(sesion);
        }
        return empresascliente;
    }

    @Override
    public FacturacabeceraDAO getFacturaCabeceraDAO() {
        if (facturacab == null) {
            facturacab = new FacturacabeceraIMPL(sesion);
        }
        return facturacab;
    }

    @Override
    public FacturadetalleDAO getFActuraDetalleDAO() {
        if (facturadet == null) {
            facturadet = new FacturadetalleIMPL(sesion);
        }
        return facturadet;
    }

    @Override
    public PersonaclienteDAO getPersonaClienteDAO() {
        if (personascliente == null) {
            personascliente = new PersonaclienteIMPL(sesion);
        }
        return personascliente;
    }

    @Override
    public ProductoDAO getProductoDAO() {
        if (productos == null) {
            productos = new ProductoIMPL(sesion);
        }
        return productos;
    }

    @Override
    public ProductodescuentoDAO getProductoDescuentoDAO() {
        if (productodes == null) {
            productodes = new ProductodescuentoIMPL(sesion);
        }
        return productodes;
    }

    @Override
    public TipotrabajadorDAO getTipoTrabajadorDAO() {
        if (tipostrabajador == null) {
            tipostrabajador = new TipotrabajadorIMPL(sesion);
        }
        return tipostrabajador;
    }

    @Override
    public BoletaDAO getBoleta() {
        if (boleta == null) {
            boleta = new BoletaIMPL(sesion);
        }
        return boleta;
    }

    @Override
    public FacturaDAO getFactura() {
        if (factura == null) {
            factura = new FacturaIMPL(sesion);
        }
        return factura;
    }

}
