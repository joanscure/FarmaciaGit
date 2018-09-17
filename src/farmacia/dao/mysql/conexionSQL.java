/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author fecyp
 */
public class conexionSQL {

    public String dv = "basereserva";
    public String url = "jdbc:mysql://127.0.0.1/" + dv;
    public String user = "root";
    public String pass = "";

    public conexionSQL() {

    }

    public com.mysql.jdbc.Connection conectar() {
        com.mysql.jdbc.Connection link = null;
        try {
            link = (com.mysql.jdbc.Connection) DriverManager.getConnection(this.url, this.user, this.pass);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return link;
    }

    public void cerrarConexion(Connection conexion) throws SQLException {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (Exception ex) {

            }
        }
    }
}
