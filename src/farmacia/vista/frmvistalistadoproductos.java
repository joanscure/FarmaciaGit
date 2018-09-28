/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista;

import com.mxrck.autocompleter.TextAutoCompleter;
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
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
 * // esta clase es para mostrarse cada vez que se presione el boton agregar productos
 * @author fecyp
 */
public class frmvistalistadoproductos extends JFrame implements ActionListener, KeyListener, MouseListener {

    public JTable tabla;
    DefaultTableModel modelo;
    JPanel principal;
    JPanel pane1;
    JButton buscar, agregar, salir;
    ;
    public JTextField txtBuscar;
    JComboBox buscarPor;
    JLabel contador;
    TextAutoCompleter autocompletar;
    TableRowSorter<TableModel> elQueOrdena;
    int indexSelecion = -1;
    String dni;
    configuracionesTabla config = new configuracionesTabla();
    configuracionImagenes configIma = new configuracionImagenes();
    Font fontboton = new Font("Geneva", 1, 13);
    Color c = new java.awt.Color(255, 204, 102);
//    Color c=Color.white;
    public boolean control = true;

    frmvistalistadoproductos() {
        iniciar_componentes();
        perzonalizartipoletra();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(683, 553);
        setResizable(false);
        setLocationRelativeTo(null);
        agregar.setEnabled(false);
        buscarPor.addActionListener(this);
        buscar.addActionListener(this);
        txtBuscar.addActionListener(this);
        tabla.addKeyListener(this);
        txtBuscar.addKeyListener(this);
        buscarPor.addKeyListener(this);
        salir.addActionListener(this);
        agregar.addActionListener(this);
        tabla.addMouseListener(this);
        txtBuscar.requestFocus();
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                salir.doClick();
            }

        });
    }

    public void perzonalizartipoletra() {
        buscar.setFont(fontboton);
        txtBuscar.setFont(fontboton);
        buscarPor.setFont(fontboton);
        contador.setFont(fontboton);
        tabla.setFont(new Font("Geneva", 0, 13));
        tabla.getTableHeader().setFont(fontboton);

    }

    private void iniciar_componentes() {
        tabla = new JTable(20, 20);

        modelo = new DefaultTableModel();
        pane1 = new JPanel(new BorderLayout());
        pane1.setBackground(c);
        JPanel pane_buscador = new JPanel();
        pane_buscador.setBackground(c);
        buscarPor = new JComboBox();
        buscarPor.addItem("Por Codigo");
        buscarPor.addItem("Por Nombre");
        txtBuscar = new JTextField(10);
        agregar = new JButton("Agregar");
        salir = new JButton("salir");

        buscar = new JButton(configIma.obtenerIcono("buscar.png", 15));
        pane_buscador.add(buscarPor);
        pane_buscador.add(txtBuscar);
        pane_buscador.add(buscar);
        pane_buscador.add(buscar);
        pane_buscador.add(agregar);
        pane_buscador.add(salir);
        autocompletar = new TextAutoCompleter(txtBuscar);
        contador = new JLabel("Existen 0 usuarios");
        pane1.add(pane_buscador, BorderLayout.NORTH);
        pane1.add(clientes_tabla(), BorderLayout.CENTER);
        pane1.add(contador, BorderLayout.SOUTH);
        pane1.setPreferredSize(new Dimension(700, 400));

        setLayout(new FlowLayout());
        pane1.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20),
                BorderFactory.createTitledBorder("Listado de Productos")));

        setLayout(new GridLayout(1, 1));
        add(pane1);
        setSize(500, 600);
        setBackground(c);
        buscarPor.addActionListener(this);
        buscar.addActionListener(this);
        txtBuscar.addActionListener(this);
        tabla.addKeyListener(this);
        txtBuscar.addKeyListener(this);
        buscarPor.addKeyListener(this);

    }

    public JScrollPane clientes_tabla() {

        Object[][] data = new Object[0][0];
        String[] lista = {"Codigo", "Nombre", "Descripcion", "Dosis", "Precio Venta", "IGV", "Precio Total", "Stock", "estado"};
        modelo = new DefaultTableModel(data, lista) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JScrollPane pane = new JScrollPane(tabla);
        tabla.setModel(modelo);
        tabla.getTableHeader().setReorderingAllowed(false);
        elQueOrdena = new TableRowSorter<TableModel>(modelo);
        tabla.setRowSorter(elQueOrdena);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (control) {
                agregar.setEnabled(true);
            }
        }
        );
        pane.setBackground(c);
        int[] tamaño = {80, 180, 180, 120, 100, 80, 80, 80, 0};
        config.fijarTamaño(tabla, tamaño);
        int[] columnas = {8};
        config.ocultarColumnas(tabla, columnas);
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
            } else if (buscarPor.getSelectedItem().toString().equals("Por Codigo")) {
                elQueOrdena.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase().trim(), 1));
            }

            if (tabla.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "¡No Se encontraron Productos!", "Informacion", JOptionPane.ERROR_MESSAGE);
                txtBuscar.requestFocus();
            } else {
                tabla.requestFocus();
            }

        } else if (source == salir) {
            setVisible(false);
            txtBuscar.setText("");
            elQueOrdena.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase().trim(), 1));

        } else if (source == agregar) {
            frmVentas.txtcodigo.setText((String) modelo.getValueAt(tabla.getSelectedRow(), 0));
            frmVentas.txtnombreProducto.setText((String) modelo.getValueAt(tabla.getSelectedRow(), 1));
            frmVentas.txtprecio.setText((String) modelo.getValueAt(tabla.getSelectedRow(), 6));
            frmVentas.txtstock.setText((String) modelo.getValueAt(tabla.getSelectedRow(), 7));
            tabla.clearSelection();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Object source = e.getSource();
        if (source == tabla) {
            if (tabla.getSelectedRow() == -1) {
                return;
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                int index = tabla.getSelectedRow();
                if (index == 0) {
                    index = tabla.getRowCount();
                }
                index--;
                control = false;
                tabla.changeSelection(index, 0, false, false);
                //se pasa el index como parametro o se usa el selected
                control = true;
                agregar.doClick();

            }
        } else if (source == txtBuscar) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                buscarPor.setPopupVisible(true);
                buscarPor.requestFocus();
            }

        } else if (source == buscarPor) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                buscarPor.setPopupVisible(false);
                buscarPor.transferFocus();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            salir.doClick();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source == tabla) {
            if (e.getClickCount() == 2) {
                agregar.doClick();
            }
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

}
