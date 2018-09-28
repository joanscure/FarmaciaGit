package farmacia.jdbc.dao;

public interface DAOManager {

    boletacabeceraDAO getBoletaCabeceraDAO();

    boletadetalleDAO getBoletaDetalleDAO();

    descuentoDAO getDescuentoDAO();

    empleadoDAO getEmpleadoDAO();

    empresaDAO getEmpresaDAO();

    empresaclienteDAO getEmpresaClienteDAO();

    facturacabeceraDAO getFacturaCabeceraDAO();

    facturadetalleDAO getFActuraDetalleDAO();

    personaDAO getPersonaDAO();

    personaclienteDAO getPersonaClienteDAO();

    productoDAO getProductoDAO();

    productodescuentoDAO getProductoDescuentoDAO();

    tipotrabajadorDAO getTipoTrabajadorDAO();
    
    boletaDAO getBoleta();
    
    facturaDAO getFactura();


}
