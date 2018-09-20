package farmacia.jdbc.dao;

import farmacia.jdbc.modelado.persona;
import java.util.List;

public interface personaDAO extends DAO<persona, Long> {

    List<persona> buscarPorNombre(String nombre);
    //busca una persona por su nombre. Se puede usar el LIKE

    List<persona> buscarPorApellidos(String apellidos);
    //busca una persona por sus apellidos (ambos). Se puede usar el LIKE

    List<persona> buscarPorDni(String dni);
    //busca por dni. Se puede usar el LIKE

}
