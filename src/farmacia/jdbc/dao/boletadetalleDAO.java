package farmacia.jdbc.dao;

import farmacia.jdbc.modelado.boletadetalle;
import java.util.List;

public interface boletadetalleDAO extends DAO<boletadetalle, Long> {

    List<boletadetalle> obtenerPorBoleta(Long idboletacabecera);
    //obtiene los detalles de una determinada boleta

    List<boletadetalle> obtenerPorProducto(Long idproducto);
    //obtiene los detalles donde se encuentre tal producto
}
