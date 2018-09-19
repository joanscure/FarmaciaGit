package farmacia.jdbc.dao.mysql;

import farmacia.jdbc.dao.boletadetalleDAO;
import farmacia.jdbc.modelado.boletadetalle;
import java.sql.Connection;
import java.util.List;

public class boletadetalleSQL implements boletadetalleDAO {

    private Connection conexion;

    private final String INSERT = "INSERT INTO boletadetalle(idboletacabecera, idproducto, cantidad, subtotal, status) "
            + "VALUES (?, ?, ?, ?, ?) ";
    private final String UPDATE = "UPDATE boletadetalle SET idboletacabecera = ?, idproducto = ?, cantidad = ?, subtotal = ?, status = ?";
    private final String DELETE = "UPDATE boletadetalle SET status = 0 WHERE idboletadetalle = ?";
    private final String GETALL = "SELECT * FROM boletadetalle WHERE status = 1";//solo obtiene los activos 
    private final String GETONE = "SELECT * FROM boletadetalle WHERE idboletadetalle = ?";

    public boletadetalleSQL(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void insertar(boletadetalle obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(boletadetalle obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(boletadetalle obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isActive(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<boletadetalle> obtenertodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boletadetalle obtener(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
