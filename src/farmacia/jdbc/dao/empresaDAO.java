package farmacia.jdbc.dao;

import farmacia.jdbc.modelado.empresa;
import java.util.List;

public interface empresaDAO extends DAO<empresa, Long> {

    List<empresa> buscarPorRuc(String ruc);
    //busca por RUC. Se puede usar el LIKE

    List<empresa> buscarPorRazonSocial(String razonsocial);
    //busca por Razon social. Se puede usar el LIKE
}
