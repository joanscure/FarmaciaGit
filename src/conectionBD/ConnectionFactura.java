/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conectionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author fecyp
 */
public class ConnectionFactura {

    Connection conexion = null;

    public ConnectionFactura(Object[] lista, Object[][] lista2) throws SQLException {

        try {
            conexionBd();
            transaccion(lista, lista2);

        } catch (Exception e) {
        }
    }

    private void conexionBd() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection(
                "jdbc:mysql://localhost/farmaciav2", "root", "");
        conexion.setAutoCommit(false);
    }

    private void transaccion(Object[] lista2, Object[][] lista) throws SQLException {
        String facturadetalle = "INSERT INTO `facturadetalle`(`idfactura`, `idproducto`, `cantidad`, `subtotal`, `descuentocompra`)"
                + "VALUES(?,?,?,?,?)";
        String facturacabera = "INSERT INTO `facturacabecera`(`idfactura`, `fechafactura`, `rucempresa`, `idempleado`, `estado`)"
                + "VALUES(?,?,?,?,?)";
        PreparedStatement facturaD = null, facturaC = null;
        try {
            facturaC = conexion.prepareStatement(facturacabera);
            facturaC.setString(1, (String) lista2[0]);
            facturaC.setString(2, (String) lista2[1]);
            facturaC.setString(3, (String) lista2[2]);
            facturaC.setInt(4, (int) lista2[3]);
            facturaC.setBoolean(5, (boolean) lista2[4]);
            facturaC.executeUpdate();
            Object[] listaAux = new Object[5];
            for (int i = 0; i < lista.length; i++) {
                for (int j = 0; j < 5; j++) {
                    listaAux[j] = lista[i][j];
                }
                detalle(facturadetalle, facturaD, listaAux);
            }

            conexion.commit();
        } catch (SQLException ex) {
            System.out.println("algo salio mal" + ex.getMessage());
            conexion.rollback();
        } finally {
            if (facturaD != null) {
                facturaD.close();
            }
            if (facturaD != null) {
                facturaD.close();
            }
        }
    }

    public void detalle(String facturadetalle, PreparedStatement facturaD, Object[] lista) throws SQLException {
        facturaD = conexion.prepareStatement(facturadetalle);
        facturaD.setString(1, (String) lista[0]);
        facturaD.setString(2, (String) lista[1]);
        facturaD.setInt(3, (int) lista[2]);
        facturaD.setDouble(4, (double) lista[3]);
        facturaD.setDouble(5, (double) lista[4]);
        facturaD.executeUpdate();

    }
}
