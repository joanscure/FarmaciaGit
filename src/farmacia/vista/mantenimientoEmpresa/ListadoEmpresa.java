/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista.mantenimientoEmpresa;

import farmacia.vista.mantenimientoCliente.*;
import com.mxrck.autocompleter.TextAutoCompleter;
import farmacia.calculos.configuracionImagenes;
import farmacia.calculos.configuracionesTabla;
import static farmacia.vista.mantenimientoCliente.frmClientes.jbEliminar;
import static farmacia.vista.mantenimientoCliente.frmClientes.jbModificar;
import static farmacia.vista.mantenimientoCliente.frmClientes.jbSalir;
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
public class ListadoEmpresa extends JPanel implements ActionListener, KeyListener {

    JTable tabla;
    DefaultTableModel modelo;
    JPanel principal;
    JPanel pane1;
    JButton buscar;
    public JTextField txtBuscar;
    JComboBox buscarPor;
    JLabel contador;
    frmEmpresa regis;
    TextAutoCompleter autocompletar;
    TableRowSorter<TableModel> elQueOrdena;
    int indexSelecion = -1;
    String dni;
    configuracionesTabla config = new configuracionesTabla();
    configuracionImagenes configIma = new configuracionImagenes();
    Font fontboton = new Font("Geneva", 1, 13);
    Color c = new java.awt.Color(255, 204, 102);
    public boolean control = true;
    public boolean teclamas=false;

    ListadoEmpresa(frmEmpresa regis) {
        this.regis = regis;
        iniciar_componentes();
        perzonalizartipoletra();
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
        buscarPor.addItem("Por Razon social");
        buscarPor.addItem("Por RUC");
        txtBuscar = new JTextField(10);

        buscar = new JButton(configIma.obtenerIcono("buscar.png", 15));
        pane_buscador.add(buscarPor);
        pane_buscador.add(txtBuscar);
        pane_buscador.add(buscar);
        autocompletar = new TextAutoCompleter(txtBuscar);
        contador = new JLabel("Existen 0 usuarios");
        pane1.add(pane_buscador, BorderLayout.NORTH);
        pane1.add(clientes_tabla(), BorderLayout.CENTER);
        pane1.add(contador, BorderLayout.SOUTH);
        pane1.setPreferredSize(new Dimension(700, 400));

        setLayout(new FlowLayout());
        pane1.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20),
                BorderFactory.createTitledBorder("Listado de Empresas")));

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
        String[] lista = {"idclienteEmpresa", "idEmpresa", "Razon Social", "Numero de RUC", "Direccion", "Telefono", "Fecha Registro","estado"};
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
                frmEmpresa.jbEliminar.setEnabled(true);
                frmEmpresa.jbModificar.setEnabled(true);
            }
        }
        );
        pane.setBackground(c);
        int[] tamaño = {0, 0, 150, 150, 250, 150, 150, 0};
        config.fijarTamaño(tabla, tamaño);
        int[] columnas = {0, 1, 7};
        config.ocultarColumnas(tabla, columnas);

        return pane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == buscar || source == txtBuscar) {
            if (buscarPor.getSelectedItem().toString().equals("Por Razon social")) {
                elQueOrdena.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase().trim(), 2));
            }else if (buscarPor.getSelectedItem().toString().equals("Por RUC")) {
                elQueOrdena.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase().trim(), 6));
            }

            if (tabla.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "¡No Se encontraron  Empresas!","Informacion",JOptionPane.ERROR_MESSAGE);
                txtBuscar.requestFocus();
            } else {
                tabla.requestFocus();
            }

        } 

    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Object source = e.getSource();
        if (source == tabla) {
            if(tabla.getSelectedRow()==-1)
            {
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
//            control = true;
                frmEmpresa.jbModificar.doClick();

            } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                frmEmpresa.jbEliminar.doClick();
            }
        }
        else if(source==txtBuscar)
        {
             if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                  buscarPor.setPopupVisible(true);
               buscarPor.requestFocus();
            }
         
        }else if (source == buscarPor) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                buscarPor.setPopupVisible(false);
                buscarPor.transferFocus();
            }
        }
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                frmEmpresa.jbSalir.doClick();
            }
         
        
        if (e.getExtendedKeyCode()== KeyEvent.VK_CONTROL ) {
                  teclamas=true;
            }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
      
       if (e.getKeyCode() == KeyEvent.VK_N &&teclamas) {
                    frmEmpresa.jbNuevo.doClick();
                    teclamas=false;
                }
    }

}
