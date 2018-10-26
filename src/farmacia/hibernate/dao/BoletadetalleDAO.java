package farmacia.hibernate.dao;

import farmacia.hibernate.modelo.Boletadetalle;
import java.util.List;

public interface BoletadetalleDAO extends DAO<Boletadetalle, Integer> {

    void eliminarDetallesBoleta(Integer idBoletacabecera) throws DAOException;
    
    List<Boletadetalle> obtenerDetallesBoleta(Integer idBoletacabecera) throws DAOException;
}
