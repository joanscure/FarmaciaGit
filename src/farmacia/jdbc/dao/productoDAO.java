package farmacia.jdbc.dao;

import farmacia.jdbc.modelado.producto;
import java.util.List;

public interface productoDAO extends DAO<producto, Long> {

    List<producto> buscarPorNombre(String nombre);
    //busca productos por su nombre. Se puede usar like
}
