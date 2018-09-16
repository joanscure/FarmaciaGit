package farmacia.dao.mysql;

import farmacia.dao.boletadetalleDAO;
import farmacia.modelado.boletadetalle;
import java.util.List;

public class boletadetalleSQL implements boletadetalleDAO{

    final String INSERT = "INSERT INTO boletadetalle(idboletacabecera, idproducto, cantidad, subtotal, status) "
    +"VALUES (?, ?, ?, ?, ?) ";
    final String UPDATE = "UPDATE boletadetalle SET idboletacabecera = ?, idproducto = ?, cantidad = ?, subtotal = ?, status = ?;
    final String DELETE = "UPDATE boletadetalle SET status = 0 WHERE idboletadetalle = ?";
    final String GETALL = "SELECT * FROM boletadetalle WHERE status = 1";//solo obtiene los activos 
    final String GETONE = "SELECT * FROM boletadetalle WHERE idboletadetalle = ?";

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

    @Override
    public Long estaRelacionado(Long id, Long id2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
