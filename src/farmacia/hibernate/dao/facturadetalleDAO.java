package farmacia.hibernate.dao;

import farmacia.jdbc.dao.*;
import farmacia.jdbc.modelado.boletadetalle;
import farmacia.jdbc.modelado.facturadetalle;
import java.util.List;

public interface facturadetalleDAO extends DAO<facturadetalle, Long> {

    void eliminarDetallesFactura(Long idfacturacabecera) throws DAOException;

    List<facturadetalle> obtenerDetallesFactura(Long idfacturacabecera) throws DAOException;
}
