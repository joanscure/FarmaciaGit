package farmacia.jdbc.dao;

import farmacia.jdbc.modelado.empresacliente;

public interface empresaclienteDAO extends DAO<empresacliente, Long> {

    Long obtenerIdEmpresa(Long idempresacliente);
    //obtiene el idempresa para usar los metodos de esa clase
}
