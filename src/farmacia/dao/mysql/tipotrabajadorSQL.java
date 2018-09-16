package farmacia.dao.mysql;

import farmacia.dao.tipotrabajadorDAO;
import farmacia.modelado.tipotrabajador;
import java.util.List;

public class tipotrabajadorSQL implements tipotrabajadorDAO{

    String INSERT = "INSERT INTO tipotrabajador(nombretipotrabajador, accederventas, accederproductos, accederclientes, accederconsultas, accederempleados, accedertipousuario, "+
    "accedercambioclave, accederanulaciones, accedereliminarproducto, accedereliminarcliente, accedereliminarempleado, accedereliminartipoempleado, status ) "+
    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
    String UPDATE = "UPDATE tipotrabajador SET nombretipotrabajador = ?, accederventas = ?, accederproductos = ?, accederclientes = ?, accederconsultas = ?, accederempleados = ?, accedertipousuario = ?, "+
    "accedercambioclave = ?, accederanulaciones = ?, accedereliminarproducto = ?, accedereliminarcliente = ?, accedereliminarempleado = ?, accedereliminartipoempleado = ?, status = ?";
    String DELETE = "UPDATE tipotrabajador SET status = 0 WHERE idtipotrabajador = ?";
    String GETALL = "SELECT * FROM tipotrabajador WHERE status = 1";
    String GETONE = "SELECT * FROM tipotrabajador WHERE idtipotrabajador = ?";

    @Override
    public void insertar(tipotrabajador obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(tipotrabajador obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(tipotrabajador obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isActive(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<tipotrabajador> obtenertodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public tipotrabajador obtener(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long estaRelacionado(Long id, Long id2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
