package farmacia.hibernate.dao;

import farmacia.hibernate.modelo.Empresa;
import farmacia.hibernate.modelo.Empresacliente;

public interface EmpresaclienteDAO extends DAO<Empresacliente, Integer> {

    Integer insertarNuevo(Empresacliente cliente, Empresa emp) throws DAOException;
        
}
