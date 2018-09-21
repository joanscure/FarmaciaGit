package farmacia.jdbc.dao.mysql;

import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.boletadetalleDAO;
import farmacia.jdbc.modelado.boletadetalle;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class boletadetalleSQL implements boletadetalleDAO {

    private Connection conexion;

    private final String INSERT = "INSERT INTO boletadetalle(idboletacabecera, idproducto, cantidad, subtotal, status) "
            + "VALUES (( SELECT idboletacabecera from boletacabecera order by idboletacabecera desc limit 1),?, ?, ?, ?) ";
    private final String UPDATE = "UPDATE boletadetalle SET idboletacabecera = ?, idproducto = ?, cantidad = ?, subtotal = ?, status = ?";
    private final String DELETE = "UPDATE boletadetalle SET status = 0 WHERE idboletadetalle = ?";
    private final String GETALL = "SELECT * FROM boletadetalle WHERE status = 1";//solo obtiene los activos 
    private final String GETONE = "SELECT * FROM boletadetalle WHERE idboletadetalle = ?";

    public boletadetalleSQL(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void insertar(boletadetalle obj) throws DAOException {
       PreparedStatement stat = null;
        
        try {
            stat = conexion.prepareStatement(INSERT);
        
            stat.setLong(1,  obj.getIdproducto());
            stat.setDouble(2,  obj.getCantidad());
            stat.setDouble(3,  obj.getSubtotal());
            stat.setBoolean(4, (boolean) obj.isStatus());
            
           stat.executeUpdate(); 
       
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            UtilSQL.cerrar(stat);
        }

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
    public List<boletadetalle> obtenertodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boletadetalle obtener(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boletadetalle convertir(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
