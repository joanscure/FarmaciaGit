package farmacia.jdbc.dao;

import farmacia.jdbc.modelado.descuento;
import java.util.List;

public interface descuentoDAO extends DAO<descuento, Long> {
    
    List<descuento> buscarPorNombre(String nombredescuento);
    //buscar por nombre. Se puede usar el LIKE
}
