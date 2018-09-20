package farmacia.jdbc.dao;

import farmacia.jdbc.modelado.boletacabecera;
import java.util.Date;
import java.util.List;

public interface boletacabeceraDAO extends DAO<boletacabecera, Long> {

    List<boletacabecera> obtenerPorCliente(Long idpersonacliente);
    //obtiene las boletas del cliente pasado por parametro

    List<boletacabecera> obtenerPorEmpleado(Long idempleado);
    //obtiene las boletas emitidas por tal empleado

    List<boletacabecera> obtenerPorFecha(Date a, Date b);
    //obtiene las boletas en un rango pasado como parametro

}
