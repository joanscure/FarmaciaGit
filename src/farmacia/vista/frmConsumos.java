/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista;

import farmacia.calculos.EstiloTablaHeader;
import farmacia.calculos.EstiloTablaRenderer;
import farmacia.calculos.configuracionImagenes;
import farmacia.calculos.configuracionesTabla;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author fecyp
 */
public class frmConsumos extends JInternalFrame implements ActionListener ,MouseListener{

    JTable tablaCliente, tablaConsumo;
    JPanel paneEast, paneWest;
    JButton bnreporte, bnbuscarproducto, bnbuscarCliente;

    public DefaultTableModel modelocliente, modeloproducto;
    public JTextField txtBuscarproducto, txtbuscarcliente;
    public JComboBox jcbBuscarProducto, jcbBuscarCliente;
    public JLabel contadorproducto, contadorCliente;
    public TableRowSorter<TableModel> elQueOrdena;
    public int indexSelecion = -1;
    public configuracionesTabla config = new configuracionesTabla();
    public configuracionImagenes configIma = new configuracionImagenes();
    public Font fontboton = new Font("Geneva", 1, 13);
    public Color c = new java.awt.Color(255, 204, 102);
    private JPanel principal;

    public frmConsumos() {
         super("Formulario Consumos" , false, true, false, true);
        Iniciar_componentes();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        
        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                frmprincipal.visibleconsumo=false;
            }

        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
         Object source = e.getSource();
        if (source == bnbuscarCliente || source == txtbuscarcliente) {
            if (jcbBuscarCliente.getSelectedItem().toString().equals("Por Nombre")) {
                elQueOrdena.setRowFilter(RowFilter.regexFilter(txtbuscarcliente.getText().toUpperCase().trim(), 2));
            } else if (jcbBuscarCliente.getSelectedItem().toString().equals("Por Apellido")) {
                elQueOrdena.setRowFilter(RowFilter.regexFilter(txtbuscarcliente.getText().toUpperCase().trim(), 3, 4));
            } else if (jcbBuscarCliente.getSelectedItem().toString().equals("Por Documento")) {
                elQueOrdena.setRowFilter(RowFilter.regexFilter(txtbuscarcliente.getText().toUpperCase().trim(), 5));
            } else if (jcbBuscarCliente.getSelectedItem().toString().equals("Por Edad")) {
                elQueOrdena.setRowFilter(RowFilter.regexFilter(txtbuscarcliente.getText().toUpperCase().trim(), 6));
            }

            if (tablaCliente.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "¡No Se encontraron Clientes!", "Informacion", JOptionPane.ERROR_MESSAGE);
                txtbuscarcliente.requestFocus();
            } else {
                tablaCliente.requestFocus();
            }

        }
        else if (source == bnbuscarproducto || source == txtBuscarproducto) {
            if (jcbBuscarProducto.getSelectedItem().toString().equals("Por Nombre")) {
                elQueOrdena.setRowFilter(RowFilter.regexFilter(txtBuscarproducto.getText().toUpperCase().trim(), 1));
            } 
            else if (jcbBuscarProducto.getSelectedItem().toString().equals("Por Codigo")) {
                elQueOrdena.setRowFilter(RowFilter.regexFilter(txtBuscarproducto.getText().toUpperCase().trim(), 0));
            } 

            if (tablaConsumo.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "¡No Se encontraron Productos!","Informacion",JOptionPane.ERROR_MESSAGE);
                txtBuscarproducto.requestFocus();
            } else {
                txtBuscarproducto.requestFocus();
            }

        }
    }

    public JScrollPane getTablaCliente() {

        Object[][] data = new Object[0][0];
        String[] lista = {"idcliente", "idpersona", "Nombre", "Apellido Paterno", "Apellido Materno", "Numero de DNI", "estado"};
        modelocliente = new DefaultTableModel(data, lista) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JScrollPane pane = new JScrollPane(tablaCliente);
        tablaCliente.setModel(modelocliente);
        tablaCliente.getTableHeader().setReorderingAllowed(false);
        elQueOrdena = new TableRowSorter<TableModel>(modelocliente);
        tablaCliente.setRowSorter(elQueOrdena);
        tablaCliente.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tablaCliente.getTableHeader().setReorderingAllowed(false);

        tablaCliente.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "selectColumnCell");
        pane.setBackground(c);
        int[] tamaño = {0, 0, 180, 180, 150, 180, 0};
        config.fijarTamaño(tablaCliente, tamaño);
        int[] columnas = {0, 1, 6};
        config.ocultarColumnas(tablaCliente, columnas);
        tablaCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaCliente.getTableHeader().setDefaultRenderer(new EstiloTablaHeader());
        tablaCliente.setDefaultRenderer(Object.class, new EstiloTablaRenderer());
        return pane;
    }

    public JScrollPane getTablaProducto() {

        Object[][] data = new Object[0][0];
        String[] lista = {"Codigo", "Nombre", "cantidad", "Precio total","Fecha de compra"};
        modeloproducto = new DefaultTableModel(data, lista) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JScrollPane pane = new JScrollPane(tablaConsumo);
        tablaConsumo.setModel(modeloproducto);
        tablaConsumo.getTableHeader().setReorderingAllowed(false);
        elQueOrdena = new TableRowSorter<TableModel>(modeloproducto);
        tablaConsumo.setRowSorter(elQueOrdena);
        tablaConsumo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tablaConsumo.getTableHeader().setReorderingAllowed(false);

        tablaConsumo.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "selectColumnCell");
        pane.setBackground(c);
        int[] tamaño = {120, 180, 180, 120,150};
        config.fijarTamaño(tablaConsumo, tamaño);

        tablaConsumo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaConsumo.getTableHeader().setDefaultRenderer(new EstiloTablaHeader());
        tablaConsumo.setDefaultRenderer(Object.class, new EstiloTablaRenderer());
        return pane;
    }

    private void Iniciar_componentes() {
        tablaConsumo = new JTable(20, 20);

        modeloproducto = new DefaultTableModel();
        paneEast = new JPanel(new BorderLayout());
        paneEast.setBackground(c);
        JPanel pane_buscador = new JPanel();
        pane_buscador.setBackground(c);
        jcbBuscarProducto = new JComboBox();
        jcbBuscarProducto.addItem("Por Codigo");
        jcbBuscarProducto.addItem("Por Nombre");
        txtBuscarproducto = new JTextField(10);
        bnreporte = new JButton("Generar Reporte");
        bnreporte.setEnabled(false);
        bnbuscarproducto = new JButton(configIma.obtenerIcono("buscar.png", 15));
        pane_buscador.add(jcbBuscarProducto);
        pane_buscador.add(txtBuscarproducto);
        pane_buscador.add(bnbuscarproducto);
        pane_buscador.add(bnreporte);
        contadorproducto = new JLabel("Existen 0 usuarios");
        paneEast.add(pane_buscador, BorderLayout.NORTH);
        paneEast.add(getTablaProducto(), BorderLayout.CENTER);
        paneEast.add(contadorproducto, BorderLayout.SOUTH);
        paneEast.setPreferredSize(new Dimension(700, 400));

        setLayout(new FlowLayout());
        paneEast.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20),
                BorderFactory.createTitledBorder("Listado de Productos Consumidos")));

        tablaCliente = new JTable(20, 20);
        modelocliente = new DefaultTableModel();
        paneWest = new JPanel(new BorderLayout());
        paneWest.setBackground(c);
        JPanel pane_buscador2 = new JPanel();
        pane_buscador2.setBackground(c);
        jcbBuscarCliente = new JComboBox();
        jcbBuscarCliente.addItem("Por Nombre");
        jcbBuscarCliente.addItem("Por Apellido");
        jcbBuscarCliente.addItem("Por DNI");
        jcbBuscarCliente.addItem("Por Edad");
        txtbuscarcliente = new JTextField(10);

        bnbuscarCliente = new JButton(configIma.obtenerIcono("buscar.png", 15));
        pane_buscador2.add(jcbBuscarCliente);
        pane_buscador2.add(txtbuscarcliente);
        pane_buscador2.add(bnbuscarCliente);
        contadorCliente = new JLabel("Existen 0 usuarios");
        paneWest.add(pane_buscador2, BorderLayout.NORTH);
        paneWest.add(getTablaCliente(), BorderLayout.CENTER);
        paneWest.add(contadorCliente, BorderLayout.SOUTH);
        paneWest.setPreferredSize(new Dimension(700, 400));

        setLayout(new FlowLayout());
        paneWest.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20),
                BorderFactory.createTitledBorder("Listado de clientes")));

        jcbBuscarCliente.addActionListener(this);
        bnbuscarCliente.addActionListener(this);
        txtbuscarcliente.addActionListener(this);

        setLayout(new GridLayout(1, 1));
        principal = new JPanel(new BorderLayout());
        principal.add(paneWest, BorderLayout.WEST);
        principal.add(paneEast,BorderLayout.EAST);

        setBackground(c);
        jcbBuscarProducto.addActionListener(this);
        bnbuscarproducto.addActionListener(this);
        txtBuscarproducto.addActionListener(this);
        add(principal);
    }
    public static void main(String[] args) {
        new frmConsumos().setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source=e.getSource();
        if(source==tablaCliente)
        {
            bnreporte.setEnabled(true);
            
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    public void BuscarDatos()
    {
        
    }
}
