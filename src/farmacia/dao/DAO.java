
package farmacia.dao;

import java.util.List;


public interface DAO<O, K> {
    
    void insertar(O obj);//inserta un registro
    
    void modificar (O obj);//modifica un registro
    
    void eliminar(O obj);//elmina (status false o 0) un registro. Eliminacion logica
     
    boolean isActive(K id);//delvuelve el estado del producto, si esta eliminado (status true o false)
    
    List<O> obtenertodos();//todos los registros en un list
    
    O obtener(K id);//obtiene un registro con su identidicador 
    
    Long estaRelacionado(Long id, Long id2);//devuelve el indice cuando existe relacion y null cuando no
    
}
