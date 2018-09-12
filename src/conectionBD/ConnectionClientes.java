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
public class ConnectionClientes {
    Connection conexion=null;
    public ConnectionClientes(Object[] lista,Object[] lista2) throws SQLException 
    {
        try {
            conexionBd();
            transaccion(lista,lista2);
            
        } catch (Exception e) {
        }
    }

    private void conexionBd() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/farmaciav2", "root", "");
            conexion.setAutoCommit(false);
    }

    private void transaccion(Object[] lista,Object[] lista2) throws SQLException {
          String empleado = "INSERT INTO `cliente`(`dniper`,`estado`)"
                    + "VALUES(?,?)";            
            String persona = "INSERT INTO `persona`(`dniper`, `nombreper`, `apellidoper`, `direccionper`, `telefonoper`, `estado`)"
                    + "VALUES(?,?,?,?,?,?)";  
           PreparedStatement empleados = null,personas = null;
        try {
             personas=conexion.prepareStatement(persona);
            personas.setString(1, (String) lista2[0]);
              personas.setString(2, (String) lista2[1]);
                personas.setString(3,(String) lista2[2]);
                  personas.setString(4,(String) lista2[3]);
                  personas.setString(5,(String) lista2[4]);
                  personas.setBoolean(6, (boolean) lista2[5]);
                  personas.executeUpdate();
             empleados=conexion.prepareStatement(empleado);
            empleados.setString(1,(String)lista[0]);
                   empleados.setBoolean(2, (boolean)lista[1]);
                  empleados.executeUpdate();
                  
            conexion.commit();
        } catch (SQLException ex) {
            System.out.println("algo salio mal"+ex.getMessage());
            conexion.rollback();
        }finally
        {
            if(empleados!=null)
            {
                empleados.close();
            }
            if(personas!=null)
            {
                personas.close();
            }
        }
    }
  
}
