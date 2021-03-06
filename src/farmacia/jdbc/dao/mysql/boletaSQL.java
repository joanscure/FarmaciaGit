/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.jdbc.dao.mysql;

import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.boletaDAO;
import farmacia.jdbc.modelado.boleta;
import farmacia.jdbc.modelado.boletacabecera;
import farmacia.jdbc.modelado.boletadetalle;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fecyp
 */
public class boletaSQL implements boletaDAO {

    Connection conexion;

    public boletaSQL(Connection conn) {
        this.conexion = conn;
    }

    @Override
    public Long insertar(boleta obj) throws DAOException {
        Long idboleta = null;
        try {

            conexion.setAutoCommit(false);// para  poder controlar que parte se envia y si hay un error en todo ese segemento poder hacer el roolback
            // instaciamos cada dao para poder utilizar sus funciones insertar modificar etc

            boletacabeceraSQL cabeceraSQL = new boletacabeceraSQL(conexion);
            boletadetalleSQL detalleSQL = new boletadetalleSQL(conexion);
            //damos valores a la cabecera y detalle para que se puedan buscar mediante sus atributos
            boletacabecera cabecera = obj.getCabecera();
            List<boletadetalle> listadodetalle = obj.getDetalle();

            idboleta = cabeceraSQL.insertar(cabecera);

            for (int i = 0; i < listadodetalle.size(); i++) {
                boletadetalle detalleN = listadodetalle.get(i);
                detalleN.setIdboletacabecera(idboleta);
                detalleSQL.insertar(detalleN);
            }

            conexion.commit();

        } catch (SQLException ex) {
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                System.out.println(ex.getMessage());
                throw new DAOException("Error en transaccion.", ex1);
            }
            System.out.println(ex.getMessage());
            throw new DAOException("Error en SQL.", ex);
        }
        return idboleta;
    }

    @Override
    public void modificar(boleta obj) throws DAOException {
        try {

            conexion.setAutoCommit(false);// para  poder controlar que parte se envia y si hay un error en todo ese segemento poder hacer el roolback
            // instaciamos cada dao para poder utilizar sus funciones insertar modificar etc

            boletacabeceraSQL cabeceraSQL = new boletacabeceraSQL(conexion);
            boletadetalleSQL detalleSQL = new boletadetalleSQL(conexion);
            //damos valores a la cabecera y detalle para que se puedan buscar mediante sus atributos
            boletacabecera cabecera = obj.getCabecera();
            List<boletadetalle> listadodetalle = obj.getDetalle();

            cabeceraSQL.modificar(cabecera);

            for (int i = 0; i < listadodetalle.size(); i++) {
                detalleSQL.modificar(listadodetalle.get(i));
            }

            conexion.commit();

        } catch (SQLException ex) {
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                System.out.println(ex.getMessage());
                throw new DAOException("Error en transaccion.", ex1);
            }
            System.out.println(ex.getMessage());
            throw new DAOException("Error en SQL.", ex);
        }
    }

    @Override
    public void eliminar(boleta obj) throws DAOException {
        try {
            conexion.setAutoCommit(false);
            boletacabeceraSQL cabSQL = new boletacabeceraSQL(conexion);
            boletadetalleSQL detalleSQL = new boletadetalleSQL(conexion);

            boletacabecera cabecera = obj.getCabecera();
            boletadetalle detalle = obj.getOneDetalle(0);

            detalleSQL.eliminarDetallesBoleta(obj.getCabecera().getIdboletacabecera());
            cabSQL.eliminar(cabecera);
            conexion.commit();

        } catch (SQLException ex) {
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                System.err.println(ex1.getMessage());
                throw new DAOException("Error en transaccion.", ex1);
            }
            System.err.println(ex.getMessage());
            throw new DAOException("Error en SQL.", ex);
        }
    }

    @Override
    public List<boleta> obtenertodos() throws DAOException {
        boleta b = null;
        List<boletacabecera> cabeceras = null;
        List<boleta> lista = new ArrayList<>();
        try {
            conexion.setAutoCommit(false);
            boletacabeceraSQL cabSQL = new boletacabeceraSQL(conexion);
            boletadetalleSQL detalleSQL = new boletadetalleSQL(conexion);
            
            cabeceras = new ArrayList<>(cabSQL.obtenertodos());
            
            for (boletacabecera i : cabeceras) {
                Long id = i.getIdboletacabecera();
                b = obtener(id);
                lista.add(b);
            }
            
            conexion.commit();

        } catch (SQLException ex) {
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                System.err.println(ex1.getMessage());
                throw new DAOException("Error en transaccion.", ex1);
            }
            System.err.println(ex.getMessage());
            throw new DAOException("Error en SQL.", ex);
        }
        return lista;
    }

    @Override
    public boleta obtener(Long id) throws DAOException {
        boleta b = null;
        boletacabecera cabecera = null;
        List<boletadetalle> lista = null;
        try {
            conexion.setAutoCommit(false);
            boletacabeceraSQL cabSQL = new boletacabeceraSQL(conexion);
            boletadetalleSQL detalleSQL = new boletadetalleSQL(conexion);

            cabecera = cabSQL.obtener(id);
            lista = new ArrayList<>(detalleSQL.obtenerDetallesBoleta(id));
            
            b = new boleta(cabecera, lista);

            conexion.commit();

        } catch (SQLException ex) {
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                System.err.println(ex1.getMessage());
                throw new DAOException("Error en transaccion.", ex1);
            }
            System.err.println(ex.getMessage());
            throw new DAOException("Error en SQL.", ex);
        }
        return b;
    }

    @Override
    public boleta convertir(ResultSet rs) throws DAOException {
        throw new DAOException("No se puede utilizar este método. Modelacion abstracta.");
    }
}
