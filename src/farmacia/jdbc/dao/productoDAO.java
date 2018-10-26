package farmacia.jdbc.dao;

import farmacia.jdbc.modelado.producto;
import java.util.List;

public interface productoDAO extends DAO<producto, Long> {
    public void cambiarStock(producto obj)throws DAOException;
}
