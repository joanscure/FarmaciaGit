package farmacia.jdbc.dao.mysql;

import farmacia.jdbc.dao.boletacabeceraDAO;
import farmacia.jdbc.modelado.boletacabecera;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class boletacabeceraSQL implements boletacabeceraDAO {

    private Connection conexion;

    private final String INSERT = "INSERT INTO boletacabecera(correlativoboleta, numeroboleta, fechaemisionboleta, idpersonacliente, idempleado, status) "
            + "VALUES (?, ?, ?, ?, ?, ?) ";
    private final String UPDATE = "UPDATE boletacabecera SET correlativoboleta = ?, numeroboleta = ?, fechaemisionboleta = ?, idpersonacliente = ?, idempleado = ?, status = ?";
    private final String DELETE = "UPDATE boletacabecera SET status = 0 WHERE idboletacabecera = ?";
    private final String GETALL = "SELECT * FROM boletacabecera WHERE status = 1";//solo obtiene los activos 
    private final String GETONE = "SELECT * FROM boletacabecera WHERE idboletacabecera = ?";

    public boletacabeceraSQL(Connection conexion) {
        this.conexion = conexion;
    }

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
    public List<boletacabecera> obtenerPorCliente(Long idpersonacliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<boletacabecera> obtenerPorEmpleado(Long idempleado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<boletacabecera> obtenerPorFecha(Date a, Date b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
