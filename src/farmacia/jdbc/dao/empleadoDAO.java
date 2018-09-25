package farmacia.jdbc.dao;

import farmacia.jdbc.modelado.empleado;
import farmacia.jdbc.modelado.persona;
import java.util.List;

public interface empleadoDAO extends DAO<empleado, Long> {

    void insertarNuevo(persona per, empleado emp) throws DAOException;
}
