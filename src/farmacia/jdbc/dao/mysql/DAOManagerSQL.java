package farmacia.jdbc.dao.mysql;

import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.DAOManager;
import farmacia.jdbc.dao.boletaDAO;
import farmacia.jdbc.dao.boletacabeceraDAO;
import farmacia.jdbc.dao.boletadetalleDAO;
import farmacia.jdbc.dao.descuentoDAO;
import farmacia.jdbc.dao.empleadoDAO;
import farmacia.jdbc.dao.empresaDAO;
import farmacia.jdbc.dao.empresaclienteDAO;
import farmacia.jdbc.dao.facturaDAO;
import farmacia.jdbc.dao.facturaSQL;
import farmacia.jdbc.dao.facturacabeceraDAO;
import farmacia.jdbc.dao.facturadetalleDAO;
import farmacia.jdbc.dao.personaDAO;
import farmacia.jdbc.dao.personaclienteDAO;
import farmacia.jdbc.dao.productoDAO;
import farmacia.jdbc.dao.productodescuentoDAO;
import farmacia.jdbc.dao.tipotrabajadorDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOManagerSQL implements DAOManager {

    private Connection conexion;

    private boletacabeceraDAO boletacab = null;
    private boletadetalleDAO boletadet = null;
    private descuentoDAO descuentos = null;
    private empleadoDAO empleados = null;
    private empresaDAO empresas = null;    
    private empresaclienteDAO empresascliente = null;
    private facturacabeceraDAO facturacab = null;
    private facturadetalleDAO facturadet = null;
    private personaDAO personas = null;    
    private personaclienteDAO personascliente = null;
    private productoDAO productos = null;
    private productodescuentoDAO productodes = null;
    private tipotrabajadorDAO tipostrabajador = null;
    private boletaDAO boleta=null;
    private facturaDAO factura = null;
    
    public DAOManagerSQL(String host, String datebase, String user, String password) throws SQLException {
        conexion = DriverManager.getConnection("jdbc:mysql://" + host + "/" + datebase, user, password);
    }

    public void cerrarConexion() throws DAOException{
        if (conexion != null){
            try{
                conexion.close();
            }catch (SQLException ex){
                throw new DAOException("Error al cerrar conexion.", ex);
            }
        }
    }
    
    @Override
    public boletacabeceraDAO getBoletaCabeceraDAO() {
        if(boletacab == null){
            boletacab = new boletacabeceraSQL(conexion);
        }
        return boletacab;
    }

    @Override
    public boletadetalleDAO getBoletaDetalleDAO() {
        if(boletadet == null){
            boletadet = new boletadetalleSQL(conexion);
        }
        return boletadet;
    }

    @Override
    public descuentoDAO getDescuentoDAO() {
        if(descuentos== null){
            descuentos = new descuentoSQL(conexion);
        }
        return descuentos;
    }

    @Override
    public empleadoDAO getEmpleadoDAO() {
        if (empleados == null){
            empleados = new empleadoSQL(conexion);
        }
        return empleados;
    }

    @Override
    public empresaDAO getEmpresaDAO() {
        if (empresas == null){
            empresas = new empresaSQL(conexion);
        }
        return empresas;
    }

    @Override
    public empresaclienteDAO getEmpresaClienteDAO() {
        if (empresascliente == null){
            empresascliente = new empresaclienteSQL(conexion);
        }
        return empresascliente;
    }

    @Override
    public facturacabeceraDAO getFacturaCabeceraDAO() {
        if (facturacab == null){
            facturacab = new facturacabeceraSQL(conexion);
        }
        return facturacab;
    }

    @Override
    public facturadetalleDAO getFActuraDetalleDAO() {
        if (facturadet == null){
            facturadet = new facturadetalleSQL(conexion);
        }
        return facturadet;
    }

    @Override
    public personaDAO getPersonaDAO() {
        if (personas == null){
            personas = new personaSQL(conexion);
        }
        return personas;
    }

    @Override
    public personaclienteDAO getPersonaClienteDAO() {
        if (personascliente == null){
            personascliente = new personaclienteSQL(conexion);
        }
        return personascliente;
    }

    @Override
    public productoDAO getProductoDAO() {
        if (productos == null){
            productos = new productoSQL(conexion);
        }
        return productos;
    }

    @Override
    public productodescuentoDAO getProductoDescuentoDAO() {
        if (productodes == null){
            productodes = new productodescuentoSQL(conexion);
        }
        return productodes;
    }

    @Override
    public tipotrabajadorDAO getTipoTrabajador() {
        if (tipostrabajador == null){
            tipostrabajador = new tipotrabajadorSQL(conexion);
        }
        return tipostrabajador;
    }

    @Override
    public boletaDAO getBoleta() {
        if (boleta == null){
            boleta = new boletaSQL(conexion);
        }
        return boleta;
    }

    @Override
    public facturaDAO getFactura() {
         if (factura == null){
            factura = new facturaSQL(conexion);
        }
        return factura;
    }

}
