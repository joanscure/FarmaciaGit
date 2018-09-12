package conectionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fecyp
 */
public class ConnectionEmpresa {
    Connection conexion=null;
    public ConnectionEmpresa(Object[] lista) throws SQLException 
    {
        try {
            conexionBd();
            transaccion(lista);
            
        } catch (Exception e) {
        }
    }

    private void conexionBd() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/farmaciav2", "root", "");
            conexion.setAutoCommit(false);
    }

    private void transaccion(Object[] lista2) throws SQLException {
               
            String empresa = "INSERT INTO `empresa`(`rucem`, `razonsocialem`, `nombrecomercialem`, `formajuridicaem`, `direccionem`, `telefonoem`, `estado`)"
                    + "VALUES(?,?,?,?,?,?,?)";  
           PreparedStatement empresas = null;
        try {
             empresas=conexion.prepareStatement(empresa);
            empresas.setString(1, (String) lista2[0]);
              empresas.setString(2, (String) lista2[1]);
                empresas.setString(3,(String) lista2[2]);
                  empresas.setString(4,(String) lista2[3]);
                  empresas.setString(5,(String) lista2[4]);
                   empresas.setString(6,(String) lista2[5]);
                  empresas.setBoolean(7, (boolean) lista2[6]);
                  empresas.executeUpdate();
                  
            conexion.commit();
        } catch (SQLException ex) {
            System.out.println("algo salio mal"+ex.getMessage());
            conexion.rollback();
        }finally
        {
            if(empresas!=null)
            {
                empresas.close();
            }
            
        }
    }
  
}
