package farmacia.jdbc.dao;

import farmacia.jdbc.modelado.facturacabecera;
import java.util.Date;
import java.util.List;

public interface facturacabeceraDAO extends DAO<facturacabecera, Long> {
 public List<facturacabecera> obtenerportiempo(java.util.Date min,java.util.Date max) throws DAOException ;
  public Long obtenerid(facturacabecera bc)throws DAOException ;
}
