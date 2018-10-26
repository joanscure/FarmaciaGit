package farmacia.hibernate.dao;


import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;

public interface DAO<O, PK extends Serializable> {

    Integer insertar(O obj) throws DAOException;//inserta un registro

    void modificar(O obj) throws DAOException;//modifica un registro

    void eliminar(O obj) throws DAOException;//elmina (status false o 0) un registro. Eliminacion logica

    List<O> obtenertodos() throws DAOException;//todos los registros en un list

    O obtener(PK id) throws DAOException;//obtiene un registro con su identidicador
    
    void iniciarOperacion() throws DAOException;
    
    void manejarExcepcion(HibernateException ex) throws DAOException;

}
