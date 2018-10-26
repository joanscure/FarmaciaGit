/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.reportes;

import com.mysql.jdbc.Connection;
import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author fecyp
 */
public class Reportes {

    Connection conexion;

    public Reportes(String host, String datebase, String user, String password) {
        try {
            conexion = (Connection) DriverManager.getConnection("jdbc:mysql://" + host + "/" + datebase, user, password);
        } catch (SQLException ex) {
            System.out.println("Error al generar la conexion." + ex);
        }
    }

    public void reporteboleta(Long idboleta, double total) {
        Map<String, Object> p = new HashMap();

        p.put("Total", total);
        p.put("id", idboleta);
        JasperReport report;
        JasperPrint print;

        try {
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath() + "/src/farmacia/reportes/report1.jrxml");
            print = JasperFillManager.fillReport(report, p, conexion);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Comprobante");
            view.setVisible(true);
            view.toFront();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void reportefactura(Long idfactura, double total) {
        Map<String, Object> p = new HashMap();

        p.put("Total", total);
        p.put("idf", idfactura);
        JasperReport report;
        JasperPrint print;

        try {
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath() + "/src/farmacia/reportes/report2.jrxml");
            print = JasperFillManager.fillReport(report, p, conexion);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Comprobante");
            view.setVisible(true);
            view.toFront();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void reporteproducto() {
        Map<String, Object> p = new HashMap();

        JasperReport report;
        JasperPrint print;

        try {
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath() + "/src/farmacia/reportes/reporteproducto.jrxml");
            print = JasperFillManager.fillReport(report, null, conexion);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Listados ");
            view.setVisible(true);
            view.toFront();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void reporteclientes() {
        Map<String, Object> p = new HashMap();

        JasperReport report;
        JasperPrint print;

        try {
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath() + "/src/farmacia/reportes/reportecliente.jrxml");
            print = JasperFillManager.fillReport(report, null, conexion);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Listados");
            view.setVisible(true);
            view.toFront();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void reporteEmpleado() {
        Map<String, Object> p = new HashMap();

        JasperReport report;
        JasperPrint print;

        try {
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath() + "/src/farmacia/reportes/reporteempleado.jrxml");
            print = JasperFillManager.fillReport(report, null, conexion);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Listados");
            view.setVisible(true);
            view.toFront();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void reporteEmpresa() {
        Map<String, Object> p = new HashMap();

        JasperReport report;
        JasperPrint print;

        try {
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath() + "/src/farmacia/reportes/reporteempresa.jrxml");
            print = JasperFillManager.fillReport(report, null, conexion);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Listados");
            view.setVisible(true);
            view.toFront();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            Reportes repor = new Reportes("localhost", "basefarmacia", "root", "");
            repor.reporteclientes();
        } catch (Exception ex) {
            System.out.println("Error");
        }
    }
}
