package farmacia.jdbc.dao;

import farmacia.jdbc.modelado.persona;
import farmacia.jdbc.modelado.personacliente;

public interface personaclienteDAO extends DAO<personacliente, Long> {

    void ingresarNuevo(personacliente cliente, persona per) throws DAOException;
}
