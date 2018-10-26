package farmacia.jdbc.dao;

import farmacia.jdbc.modelado.boletacabecera;
import java.util.List;

public interface boletacabeceraDAO extends DAO<boletacabecera, Long> {

    public List<boletacabecera> obtenerportiempo(java.util.Date min,java.util.Date max) throws DAOException ;
    public Long obtenerid(boletacabecera bc)throws DAOException ;
}
