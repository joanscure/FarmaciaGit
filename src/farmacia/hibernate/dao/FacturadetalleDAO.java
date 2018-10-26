package farmacia.hibernate.dao;

import farmacia.hibernate.modelo.Facturadetalle;
import java.util.List;


public interface FacturadetalleDAO extends DAO<Facturadetalle, Integer> {

    void eliminarDetallesFactura(Integer idFacturacabecera) throws DAOException;

    List<Facturadetalle> obtenerDetallesFactura(Integer idFacturacabecera) throws DAOException;
}
