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
    
//    @Override
//    public boletacabeceraDAO getBoletaCabeceraDAO() {
//        if(boletacab == null){
//            boletacab = new boletacabeceraSQL(conexion);
//        }
//        return boletacab;
//    }
//
//    @Override
//    public boletadetalleDAO getBoletaDetalleDAO() {
//        if(boletadet == null){
//            boletadet = new boletadetalleSQL(conexion);
//        }
//        return boletadet;
//    }
//
//    @Override
//    public descuentoDAO getDescuentoDAO() {
//        if(descuentos== null){
//            descuentos = new descuentoSQL(conexion);
//        }
//        return descuentos;
//    }
//
//    @Override
//    public empleadoDAO getEmpleadoDAO() {
//        if (empleados == null){
//            empleados = new empleadoSQL(conexion);
//        }
//        return empleados;
//    }
//
//    @Override
//    public empresaDAO getEmpresaDAO() {
//        if (empresas == null){
//            empresas = new empresaSQL(conexion);
//        }
//        return empresas;
//    }
//
//    @Override
//    public empresaclienteDAO getEmpresaClienteDAO() {
//        if (empresascliente == null){
//            empresascliente = new empresaclienteSQL(conexion);
//        }
//        return empresascliente;
//    }
//
//    @Override
//    public facturacabeceraDAO getFacturaCabeceraDAO() {
//        if (facturacab == null){
//            facturacab = new facturacabeceraSQL(conexion);
//        }
//        return facturacab;
//    }
//
//    @Override
//    public facturadetalleDAO getFActuraDetalleDAO() {
//        if (facturadet == null){
//            facturadet = new facturadetalleSQL(conexion);
//        }
//        return facturadet;
//    }

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DescuentoDAO getDescuentoDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmpleadoDAO getEmpleadoDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmpresaDAO getEmpresaDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmpresaclienteDAO getEmpresaClienteDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FacturacabeceraDAO getFacturaCabeceraDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FacturadetalleDAO getFActuraDetalleDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PersonaclienteDAO getPersonaClienteDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProductoDAO getProductoDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProductodescuentoDAO getProductoDescuentoDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TipotrabajadorDAO getTipoTrabajadorDAO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BoletaDAO getBoleta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FacturaDAO getFactura() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
