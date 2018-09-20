package farmacia.jdbc.dao;

import farmacia.jdbc.modelado.facturadetalle;
import java.util.List;

public interface facturadetalleDAO extends DAO<facturadetalle, Long> {

    List<facturadetalle> obtenerPorFactura(Long idfacturabecera);
    //obtiene los detalles de una determinada factura

    List<facturadetalle> obtenerPorProducto(Long idproducto);
    //obtiene los detalles donde este un determinado producto
}
