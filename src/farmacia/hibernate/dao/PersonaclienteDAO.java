package farmacia.hibernate.dao;

import farmacia.hibernate.modelo.Persona;
import farmacia.hibernate.modelo.Personacliente;


public interface PersonaclienteDAO extends DAO<Personacliente, Integer> {

    Integer ingresarNuevo(Personacliente cliente, Persona per) throws DAOException;
}
