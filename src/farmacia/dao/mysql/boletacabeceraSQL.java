package farmacia.dao.mysql;

import farmacia.dao.boletacabeceraDAO;
import farmacia.modelado.boletacabecera;
import java.util.List;

public class boletacabeceraSQL implements boletacabeceraDAO{

    final String INSERT = "INSERT INTO boletacabecera(correlativoboleta, numeroboleta, fechaemisionboleta, idpersonacliente, idempleado, status) "
    +"VALUES (?, ?, ?, ?, ?, ?) ";
    final String UPDATE = "UPDATE boletacabecera SET correlativoboleta = ?, numeroboleta = ?, fechaemisionboleta = ?, idpersonacliente = ?, idempleado = ?, status = ?";
    final String DELETE = "UPDATE boletacabecera SET status = 0 WHERE idboletacabecera = ?";
    final String GETALL = "SELECT * FROM boletacabecera WHERE status = 1";//solo obtiene los activos 
    final String GETONE = "SELECT * FROM empresa WHERE idempresa = ?";

    @Override
    public void insertar(boletacabecera obj) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(boletacabecera obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(boletacabecera obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isActive(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<boletacabecera> obtenertodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boletacabecera obtener(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long estaRelacionado(Long id, Long id2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
