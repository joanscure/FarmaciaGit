package farmacia.jdbc.dao;

import java.util.List;

public interface DAO<O, K> {

    void insertar(O obj) throws DAOException;//inserta un registro

    void modificar(O obj) throws DAOException;//modifica un registro

    void eliminar(O obj) throws DAOException;//elmina (status false o 0) un registro. Eliminacion logica

    boolean isActive(K id) throws DAOException;//delvuelve el estado del producto, si esta eliminado (status true o false)

    List<O> obtenertodos() throws DAOException;//todos los registros en un list

    O obtener(K id) throws DAOException;//obtiene un registro con su identidicador 

}
