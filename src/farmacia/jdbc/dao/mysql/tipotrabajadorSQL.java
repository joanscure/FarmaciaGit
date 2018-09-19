package farmacia.jdbc.dao.mysql;

import farmacia.jdbc.dao.tipotrabajadorDAO;
import farmacia.jdbc.modelado.tipotrabajador;
import java.sql.Connection;
import java.util.List;

public class tipotrabajadorSQL implements tipotrabajadorDAO{

    private Connection conexion;
    
    private final String INSERT = "INSERT INTO tipotrabajador(nombretipotrabajador, accederventas, accederproductos, accederclientes, accederconsultas, accederempleados, accedertipousuario, "+
    "accedercambioclave, accederanulaciones, accedereliminarproducto, accedereliminarcliente, accedereliminarempleado, accedereliminartipoempleado, status ) "+
    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
    private final String UPDATE = "UPDATE tipotrabajador SET nombretipotrabajador = ?, accederventas = ?, accederproductos = ?, accederclientes = ?, accederconsultas = ?, accederempleados = ?, accedertipousuario = ?, "+
    "accedercambioclave = ?, accederanulaciones = ?, accedereliminarproducto = ?, accedereliminarcliente = ?, accedereliminarempleado = ?, accedereliminartipoempleado = ?, status = ?";
    private final String DELETE = "UPDATE tipotrabajador SET status = 0 WHERE idtipotrabajador = ?";
    private final String GETALL = "SELECT * FROM tipotrabajador WHERE status = 1";
    private final String GETONE = "SELECT * FROM tipotrabajador WHERE idtipotrabajador = ?";

    public tipotrabajadorSQL(Connection conexion) {
        this.conexion = conexion;
    }

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

    
}
