/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista.mantenimientoTipoUsuario;

import com.mxrck.autocompleter.TextAutoCompleter;
import farmacia.calculos.EstiloTablaHeader;
import farmacia.calculos.EstiloTablaRenderer;
import farmacia.diseño.estrategias.EstrategiaPaneListado;
import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.mysql.DAOManagerSQL;
import farmacia.jdbc.modelado.tipotrabajador;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author fecyp
 */
public class ListadoTipousuario extends EstrategiaPaneListado implements ActionListener {
  public static  JTable tabla;

    ListadoTipousuario(String titulo) {
     super(titulo);
    }

    public void perzonalizartipoletra() {
        buscar.setFont(fontboton);
        txtBuscar.setFont(fontboton);
        buscarPor.setFont(fontboton);
        contador.setFont(fontboton);
        tabla.setFont(new Font("Geneva", 0, 13));
        tabla.getTableHeader().setFont(fontboton);

    }

    public void actualizartabla() throws DAOException {
        for (int i = 0; i < modelo.getRowCount();) {
            modelo.removeRow(i);
        }
        DAOManagerSQL manager = null;
        try {
            manager = new DAOManagerSQL("localhost", "basefarmacia", "root", "");
            List<tipotrabajador> lista = manager.getTipoTrabajadorDAO().obtenertodos();
            for (int i = 0; i < lista.size(); i++) {
                Object obj[] = {lista.get(i).getIdtipotrabajador(), lista.get(i).getNombretipotrabajador(), lista.get(i).isAccederventas(),
                    lista.get(i).isAccederproductos(), lista.get(i).isAccederclientes(), lista.get(i).isAccederconsultas(), lista.get(i).isAccederempleados(),
                    lista.get(i).isAccedertipousuario(), lista.get(i).isAccedercambioclave(), lista.get(i).isAccederanulaciones(),
                    lista.get(i).isAccedereliminarproducto(), lista.get(i).isAccedereliminarcliente(), lista.get(i).isAccedereliminarempleado(),
                    lista.get(i).isAccedereliminartipoempleado(), lista.get(i).isStatus()};

                modelo.addRow(obj);

            }
            contador.setText("Existen "+modelo.getRowCount()+" usuarios");
            manager.cerrarConexion();
        } catch (DAOException ex) {
            throw new DAOException("error al buscar" + ex.getMessage());
        }
    }

    public void Iniciar_componentes(String titulo) {
        tabla = new JTable(20, 20);

        modelo = new DefaultTableModel();
        pane1 = new JPanel(new BorderLayout());
        pane1.setBackground(c);
        JPanel pane_buscador = new JPanel();
        pane_buscador.setBackground(c);
        buscarPor = new JComboBox();
        buscarPor.addItem("Por Nombre");
        buscarPor.addItem("Por Descripcion");
        buscarPor.addItem("Por Codigo");
        txtBuscar = new JTextField(10);
        buscar = new JButton(configIma.obtenerIcono("buscar.png", 15));
        pane_buscador.add(buscarPor);
        pane_buscador.add(txtBuscar);
        pane_buscador.add(buscar);
        bnreport=new JButton("Generar Reporte");
        pane_buscador.add(bnreport);
        contador = new JLabel("Existen 0 usuarios");
        pane1.add(pane_buscador, BorderLayout.NORTH);
        pane1.add(getTabla(), BorderLayout.CENTER);
        pane1.add(contador, BorderLayout.SOUTH);
        pane1.setPreferredSize(new Dimension(700, 400));

        setLayout(new FlowLayout());
        pane1.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20),
                BorderFactory.createTitledBorder("Listado de "+ titulo)));

        setLayout(new GridLayout(1, 1));
        add(pane1);
        setSize(500, 600);
        setBackground(c);
        buscarPor.addActionListener(this);
        buscar.addActionListener(this);
        txtBuscar.addActionListener(this);
      

    }

    public JScrollPane getTabla() {

        Object[][] data = new Object[0][0];
        String[] lista = {"idtipo", "Descripcion", "Acc.ventas(2)", "Acc.produto(3)", "Acc.Clientes(4)", "Acc.Consultas(5)",
            "Acc.Empleados(6)", "Acc.tipoUsuario(7)", "Acc.Cambio Clave(8)", "Acc.Anular Ventas(9)", "Acc. Eliminar Productos(10)", "Acc. Eliminar Clientes(11)",
            "Acc.Eliminar Empleados(12)", "Acc. Eliminar Tipo Empleados(13)", "estado(14)"};
        modelo = new DefaultTableModel(data, lista) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
tabla.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0,false), "selectColumnCell");
        JScrollPane pane = new JScrollPane(tabla);
        tabla.setModel(modelo);
        tabla.getTableHeader().setReorderingAllowed(false);
        elQueOrdena = new TableRowSorter<TableModel>(modelo);
        tabla.setRowSorter(elQueOrdena);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.getTableHeader().setReorderingAllowed(false);
       
        pane.setBackground(c);
        int[] tamaño = {0, 180, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 0};
        config.fijarTamaño(tabla, tamaño);
        int[] columnas = {0, 14};
        config.ocultarColumnas(tabla, columnas);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getTableHeader().setDefaultRenderer(new EstiloTablaHeader());
        tabla.setDefaultRenderer(Object.class, new EstiloTablaRenderer());
        return pane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == buscar || source == txtBuscar) {
            if (buscarPor.getSelectedItem().toString().equals("Por Nombre")) {
                elQueOrdena.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase().trim(), 2));
            } else if (buscarPor.getSelectedItem().toString().equals("Por Desripcion")) {
                elQueOrdena.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase().trim(), 3));
            } else if (buscarPor.getSelectedItem().toString().equals("Por Codigo")) {
                elQueOrdena.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase().trim(), 1));
            }

            if (tabla.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "¡No Se encontraron Tipos de Usuario!", "Informacion", JOptionPane.ERROR_MESSAGE);
                txtBuscar.requestFocus();
            } else {
                tabla.requestFocus();
            }

        }

    }

    @Override
    public void generarReporte() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
