package farmacia.hibernate.dao;

import farmacia.hibernate.modelo.Empleado;
import farmacia.hibernate.modelo.Persona;

public interface EmpleadoDAO extends DAO<Empleado, Integer> {

    void insertarNuevo(Persona per, Empleado emp) throws DAOException;
    void actualizarpassword(Empleado emp)throws DAOException;
    String obtenerOcupacion(Empleado emp)throws DAOException;
}
