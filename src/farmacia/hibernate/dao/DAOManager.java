package farmacia.hibernate.dao;


public interface DAOManager {

    BoletacabeceraDAO getBoletaCabeceraDAO();

    BoletadetalleDAO getBoletaDetalleDAO();

    DescuentoDAO getDescuentoDAO();

    EmpleadoDAO getEmpleadoDAO();

    EmpresaDAO getEmpresaDAO();

    EmpresaclienteDAO getEmpresaClienteDAO();

    FacturacabeceraDAO getFacturaCabeceraDAO();

    FacturadetalleDAO getFActuraDetalleDAO();

    PersonaDAO getPersonaDAO();

    PersonaclienteDAO getPersonaClienteDAO();

    ProductoDAO getProductoDAO();

    ProductodescuentoDAO getProductoDescuentoDAO();

    TipotrabajadorDAO getTipoTrabajadorDAO();
    
    BoletaDAO getBoleta();
    
    FacturaDAO getFactura();


}
