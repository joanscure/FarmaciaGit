package farmacia.hibernate.dao.implementacion;

import farmacia.hibernate.dao.*;
import farmacia.hibernate.dao.DAOManager;
import farmacia.hibernate.util.NewHibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

public class DAOManagerIMPL implements DAOManager {

    SessionFactory sessionFac = null;

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
    private BoletaDAO boleta=null;
    private FacturaDAO factura = null;
    
    public DAOManagerIMPL() throws DAOException {
        try {
            sessionFac = NewHibernateUtil.getSessionFactory();
        } catch (HibernateException ex) {
            throw new DAOException("Error al generar SessionFactory.", ex);
        }
    }

    public DAOManagerIMPL(SessionFactory sessionFac) {
        this.sessionFac = sessionFac;
    }
    
    public void cerrarSessionFacory() throws DAOException{
        if (!sessionFac.isClosed()){
            try{
                sessionFac.close();
            }catch (HibernateException ex){
                throw new DAOException("Error al cerrar SessionFactory.", ex);
            }
        }
    }

    @Override
    public PersonaDAO getPersonaDAO() {
        if (personas == null){
            personas = new PersonaIMPL(sessionFac);
        }
        return personas;
    }

    @Override
    public BoletacabeceraDAO getBoletaCabeceraDAO() {
        if (boletacab == null){
            boletacab = new BoletacabeceraIMPL(sessionFac);
        }
        return boletacab;
    }

    @Override
    public BoletadetalleDAO getBoletaDetalleDAO() {
        if (boletadet == null){
            boletadet = new BoletadetalleIMPL(sessionFac);
        }
        return boletadet;
    }
        

    @Override
    public DescuentoDAO getDescuentoDAO() {
        if (descuentos == null){
            descuentos = new DescuentoIMPL(sessionFac);
        }
        return descuentos;
    }

    @Override
    public EmpleadoDAO getEmpleadoDAO() {
        if (empleados == null){
            empleados = new EmpleadoIMPL(sessionFac);
        }
        return empleados;
    }

    @Override
    public EmpresaDAO getEmpresaDAO() {
        if (empresas == null){
            empresas = new EmpresaIMPL(sessionFac);
        }
        return empresas;
    }

    @Override
    public EmpresaclienteDAO getEmpresaClienteDAO() {
        if (empresascliente == null){
            empresascliente = new EmpresaclienteIMPL(sessionFac);
        }
        return empresascliente;
    }

    @Override
    public FacturacabeceraDAO getFacturaCabeceraDAO() {
        if (facturacab == null){
            facturacab = new FacturacabeceraIMPL(sessionFac);
        }
        return facturacab;
    }

    @Override
    public FacturadetalleDAO getFActuraDetalleDAO() {
        if (facturadet == null){
            facturadet = new FacturadetalleIMPL(sessionFac);
        }
        return facturadet;
    }

    @Override
    public PersonaclienteDAO getPersonaClienteDAO() {
        if (personascliente == null){
            personascliente = new PersonaclienteIMPL(sessionFac);
        }
        return personascliente;
    }

    @Override
    public ProductoDAO getProductoDAO() {
        if (productos == null){
            productos = new ProductoIMPL(sessionFac);
        }
        return productos;
    }

    @Override
    public ProductodescuentoDAO getProductoDescuentoDAO() {
        if (productodes == null){
            productodes = new ProductodescuentoIMPL(sessionFac);
        }
        return productodes;
    }

    @Override
    public TipotrabajadorDAO getTipoTrabajadorDAO() {
        if (tipostrabajador == null){
            tipostrabajador = new TipotrabajadorIMPL(sessionFac);
        }
        return tipostrabajador;
    }

    @Override
    public BoletaDAO getBoleta() {
        if (boleta == null){
            boleta = new BoletaIMPL(sessionFac);
        }
        return boleta;
    }

    @Override
    public FacturaDAO getFactura() {
        if (factura == null){
            factura = new FacturaIMPL(sessionFac);
        }
        return factura;
    }

}
