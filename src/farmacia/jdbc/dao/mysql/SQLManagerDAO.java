package farmacia.jdbc.dao.mysql;

import farmacia.jdbc.modelado.*;
import farmacia.jdbc.dao.DAOManager;
import farmacia.jdbc.dao.boletacabeceraDAO;
import farmacia.jdbc.dao.boletadetalleDAO;
import farmacia.jdbc.dao.descuentoDAO;
import farmacia.jdbc.dao.empleadoDAO;
import farmacia.jdbc.dao.empresaDAO;
import farmacia.jdbc.dao.empresaclienteDAO;
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

public class SQLManagerDAO implements DAOManager {

    private Connection conn;

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
    
    public SQLManagerDAO(String host, String datebase, String user, String password) throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + datebase, user, password);
    }

    @Override
    public boletacabeceraDAO getBoletaCabeceraDAO() {
        if(boletacab == null){
            boletacab = new boletacabeceraSQL(conn);
        }
        return boletacab;
    }

    @Override
    public boletadetalleDAO getBoletaDetalleDAO() {
        if(boletadet == null){
            boletadet = new boletadetalleSQL(conn);
        }
        return boletadet;
    }

    @Override
    public descuentoDAO getDescuentoDAO() {
        if(descuentos== null){
            descuentos = new descuentoSQL(conn);
        }
        return descuentos;
    }

    @Override
    public empleadoDAO getEmpleadoDAO() {
        if (empleados == null){
            empleados = new empleadoSQL(conn);
        }
        return empleados;
    }

    @Override
    public empresaDAO getEmpresaDAO() {
        if (empresas == null){
            empresas = new empresaSQL(conn);
        }
        return empresas;
    }

    @Override
    public empresaclienteDAO getEmpresaClienteDAO() {
        if (empresascliente == null){
            empresascliente = new empresaclienteSQL(conn);
        }
        return empresascliente;
    }

    @Override
    public facturacabeceraDAO getFacturaCabeceraDAO() {
        if (facturacab == null){
            facturacab = new facturacabeceraSQL(conn);
        }
        return facturacab;
    }

    @Override
    public facturadetalleDAO getFActuraDetalleDAO() {
        if (facturadet == null){
            facturadet = new facturadetalleSQL(conn);
        }
        return facturadet;
    }

    @Override
    public personaDAO getPersonaDAO() {
        if (personas == null){
            personas = new personaSQL(conn);
        }
        return personas;
    }

    @Override
    public personaclienteDAO getPersonaClienteDAO() {
        if (personascliente == null){
            personascliente = new personaclienteSQL(conn);
        }
        return personascliente;
    }

    @Override
    public productoDAO getProductoDAO() {
        if (productos == null){
            productos = new productoSQL(conn);
        }
        return productos;
    }

    @Override
    public productodescuentoDAO getProductoDescuentoDAO() {
        if (productodes == null){
            productodes = new productodescuentoSQL(conn);
        }
        return productodes;
    }

    @Override
    public tipotrabajadorDAO getTipoTrabajador() {
        if (tipostrabajador == null){
            tipostrabajador = new tipotrabajadorSQL(conn);
        }
        return tipostrabajador;
    }

}
