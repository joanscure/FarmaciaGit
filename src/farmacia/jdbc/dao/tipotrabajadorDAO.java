package farmacia.jdbc.dao;

import farmacia.jdbc.modelado.tipotrabajador;
import java.util.List;

public interface tipotrabajadorDAO extends DAO<tipotrabajador, Long> {

    List<tipotrabajador> obtnerPorNombre(String nombretipotrabajador);
    //obtiene los tipos de trabajadores por su nombre. Se puede usar LIKE
}
