package farmacia.hibernate.dao;

import farmacia.hibernate.modelo.Empleado;
import farmacia.hibernate.modelo.Persona;

public interface EmpleadoDAO extends DAO<Empleado, Integer> {

    void insertarNuevo(Persona per, Empleado emp) throws DAOException;
    
    void actualizarpassword(Empleado obj)throws DAOException;
    
    String obtenerOcupacion(Empleado obj)throws DAOException;
}
