package farmacia.jdbc.dao.mysql;

import farmacia.jdbc.dao.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilSQL { 
    //CLASE UTIL PARA USAR METODOS QUE SE REPITEN CONSTANTEMENTE
    public static void cerrar(PreparedStatement st, ResultSet rs) throws DAOException {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException ex) {
                throw new DAOException("Error al cerrar conexion.", ex);
            }
        }
        
    }
    
    public static void cerrar(ResultSet rs) throws DAOException {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                throw new DAOException("Error al cerrar conexion.", ex);
            }
        }
    }
    
    public static void cerrar(PreparedStatement st) throws DAOException {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException ex) {
                throw new DAOException("Error al cerrar conexion.", ex);
            }
        }
    }
    
}
