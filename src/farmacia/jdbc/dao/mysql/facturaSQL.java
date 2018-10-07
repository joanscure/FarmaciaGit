/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.jdbc.dao.mysql;

import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.facturaDAO;
import farmacia.jdbc.dao.facturacabeceraDAO;
import farmacia.jdbc.dao.facturadetalleDAO;
import farmacia.jdbc.modelado.factura;
import farmacia.jdbc.modelado.facturacabecera;
import farmacia.jdbc.modelado.facturadetalle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author fecyp
 */
public class facturaSQL implements facturaDAO {

    Connection conexion;

    public facturaSQL(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public Long insertar(factura obj) throws DAOException {
        try {
            conexion.setAutoCommit(false);// para  poder controlar que parte se envia y si hay un error en todo ese segemento poder hacer el roolback
            // instaciamos cada dao para poder utilizar sus funciones insertar modificar etc
            facturacabeceraDAO cabeceradao = new facturacabeceraSQL(conexion);
            facturadetalleDAO detalledao = new facturadetalleSQL(conexion);
            //damos valores a la cabecera y detalle para que se puedan buscar mediante sus atributos
            facturacabecera cabecera = obj.getCabecera();
            List<facturadetalle> listadodetalle = obj.getDetalle();

            cabeceradao.insertar(cabecera);
            for (int i = 0; i < listadodetalle.size(); i++) {

                detalledao.insertar(listadodetalle.get(i));
            }
            conexion.commit();

        } catch (SQLException ex) {
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                throw new DAOException("Error en transaccion.", ex1);
            }
            throw new DAOException("Error en sql.", ex);
        }
        return 0L;
    }

    @Override
    public void modificar(factura obj) throws DAOException {
        throw new DAOException("No se puede modificar este registro");//no se puede modificar esto
    }

    @Override
    public void eliminar(factura obj) throws DAOException {
        try {
            conexion.setAutoCommit(false);
            facturacabeceraDAO cabeceradao = new facturacabeceraSQL(conexion);
            facturadetalleDAO detalledao = new facturadetalleSQL(conexion);
            facturacabecera cabecera = obj.getCabecera();
            facturadetalle detalle = obj.getOneDetalle(0);
            detalledao.eliminar(detalle);
            cabeceradao.eliminar(cabecera);
            conexion.commit();

        } catch (SQLException ex) {
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                throw new DAOException("Error en transaccion.", ex1);
            }
            throw new DAOException("Error en sql.", ex);
        }
    }

    @Override
    public List<factura> obtenertodos() throws DAOException {
        throw new DAOException("Metodo no implementado");
    }

    @Override
    public factura obtener(Long id) throws DAOException {
        throw new DAOException("Metodo no implementado");
    }

    @Override
    public factura convertir(ResultSet rs) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
