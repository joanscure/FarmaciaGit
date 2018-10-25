package farmacia.hibernate.dao;

import farmacia.jdbc.dao.*;
import farmacia.jdbc.modelado.boletadetalle;
import java.util.List;

public interface boletadetalleDAO extends DAO<boletadetalle, Long> {

    void eliminarDetallesBoleta(Long idboletacabecera) throws DAOException;
    
    List<boletadetalle> obtenerDetallesBoleta(Long idboletacabecera) throws DAOException;
}
