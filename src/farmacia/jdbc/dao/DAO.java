package farmacia.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DAO<O, K> {

    Long insertar(O obj) throws DAOException;//inserta un registro

    void modificar(O obj) throws DAOException;//modifica un registro

    void eliminar(O obj) throws DAOException;//elmina (status false o 0) un registro. Eliminacion logica

    List<O> obtenertodos() throws DAOException;//todos los registros en un list

    O obtener(K id) throws DAOException;//obtiene un registro con su identidicador 

    O convertir(ResultSet rs) throws SQLException;
}
