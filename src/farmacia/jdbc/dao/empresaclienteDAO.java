package farmacia.jdbc.dao;

import farmacia.jdbc.modelado.empresa;
import farmacia.jdbc.modelado.empresacliente;

public interface empresaclienteDAO extends DAO<empresacliente, Long> {

    void insertarNuevo(empresacliente cliente, empresa emp);
}
