package farmacia.jdbc.dao;

import farmacia.jdbc.modelado.descuento;
import farmacia.jdbc.modelado.producto;
import farmacia.jdbc.modelado.productodescuento;
import java.util.List;

public interface productodescuentoDAO extends DAO<productodescuento, Long> {

    List<descuento> obtenerDescuentosProducto(Long idproducto);
    //obtiene los descuentos de tal producto

    List<producto> obtenerProductosDescuento(Long iddescuento);
    //obtiene los productos que tienen tal descuento
}
