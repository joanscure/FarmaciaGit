package farmacia.jdbc.dao;

import farmacia.jdbc.modelado.facturacabecera;
import java.util.Date;
import java.util.List;

public interface facturacabeceraDAO extends DAO<facturacabecera, Long> {

    List<facturacabecera> obtenerPorCliente(Long idempresacliente);
    //obtiene las facturas del cliente pasado por parametro

    List<facturacabecera> obtenerPorEmpleado(Long idempleado);
    //obtiene las facturas emitidas por tal empleado

    List<facturacabecera> obtenerPorFecha(Date a, Date b);
    //obtiene las factura en un rango pasado como parametro

}
