package farmacia.hibernate.dao;

import farmacia.hibernate.modelo.Empresa;
import farmacia.hibernate.modelo.Empresacliente;

public interface EmpresaclienteDAO extends DAO<Empresacliente, Integer> {

    void insertarNuevo(Empresacliente cliente, Empresa emp) throws DAOException;
        
}
