/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.jdbc.dao.mysql;

import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.boletaDAO;
import farmacia.jdbc.dao.boletacabeceraDAO;
import farmacia.jdbc.dao.boletadetalleDAO;
import farmacia.jdbc.modelado.boleta;
import farmacia.jdbc.modelado.boletacabecera;
import farmacia.jdbc.modelado.boletadetalle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fecyp
 */
public class boletaSQL implements boletaDAO{
    Connection conexion;
     public boletaSQL(Connection conn) {
        this.conexion = conn;
    }
  
   

    @Override
    public Long insertar(boleta obj) throws DAOException {
        try {
            conexion.setAutoCommit(false);// para  poder controlar que parte se envia y si hay un error en todo ese segemento poder hacer el roolback
            boletacabeceraDAO cabeceradao=new boletacabeceraSQL(conexion);
            boletadetalleDAO detalledao=new boletadetalleSQL(conexion);
            
            boletacabecera cabecera=obj.getCabecera();
            List<boletadetalle> listadodetalle=obj.getDetalle();
            
            cabeceradao.insertar(cabecera);
            for (int i = 0; i < listadodetalle.size(); i++) {
               
                detalledao.insertar(listadodetalle.get(i));
            }
            conexion.commit();
            
        } catch (SQLException ex) {
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
               System.err.println("algo salio mal1 :c " + ex.getMessage());
            }
          System.err.println("algo salio mal1 :c " + ex.getMessage());
        }
       return 0L;
    }

    @Override
    public void modificar(boleta obj) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(boleta obj) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<boleta> obtenertodos() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boleta obtener(Long id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boleta convertir(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//     public static void main(String[] args) throws SQLException, DAOException {
//       DAOManagerSQL man = null;
//        
//        man = new DAOManagerSQL("localhost", "basefarmacia", "root", "");
//        Date fecha=new Date();
//        long a = 1;//debe existir un cliente con este id
//        long b = 2;//debe existir un empleado con este id
//        long c=1;//debe existir unprodcto con este id
//          //para resetear el autoincrement:   ALTER TABLE (nombre de la tabla) AUTO_INCREMENT = 0;
    
//       
//        boletacabecera boletacabecera = new boletacabecera(12345, 123, fecha, a,b );
//        
//        List<boletadetalle> listadodetalle=new ArrayList<>();
//        listadodetalle.add(new boletadetalle(c,12.4,15.0));
//        listadodetalle.add(new boletadetalle(c,13.4,17.0));
//        listadodetalle.add(new boletadetalle(c,14.4,16.0));
//        
//        
//        boleta boleta=new boleta(boletacabecera, listadodetalle);
//        man.getBoleta().insertar(boleta);
//        man.cerrarConexion();
//        
//    }
}
