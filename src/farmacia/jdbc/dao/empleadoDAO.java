package farmacia.jdbc.dao;

import farmacia.jdbc.modelado.empleado;
import java.util.List;

public interface empleadoDAO extends DAO<empleado, Long> {

    List<empleado> obtenerPorTipoTrabajador(Long idtipotrabajador);
    //obtiene empleados por tipo de trabajador

    Long obtenerIdPersona(Long idempleado);
    //obtiene el idpersona del empleado para trabajarlo con los metodos de persona
}
