package farmacia.jdbc.dao;

import farmacia.jdbc.modelado.personacliente;

public interface personaclienteDAO extends DAO<personacliente, Long> {

    Long obtenerIdPersona(Long personacliente);
    //obtiene el idpersona del cliente para trabajar con sus metodos 

}
