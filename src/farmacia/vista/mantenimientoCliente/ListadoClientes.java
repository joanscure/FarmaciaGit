/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista.mantenimientoCliente;

import com.mxrck.autocompleter.TextAutoCompleter;
import farmacia.calculos.EstiloTablaHeader;
import farmacia.calculos.EstiloTablaRenderer;
import farmacia.diseño.estrategias.EstrategiaPaneListado;
import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.mysql.DAOManagerSQL;
import farmacia.jdbc.modelado.persona;
import farmacia.jdbc.modelado.personacliente;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
public class ListadoClientes extends EstrategiaPaneListado implements ActionListener, KeyListener {


    ListadoClientes(String titulo) {
        super(titulo);
       
    }

    public void actualizartabla() throws DAOException {
        for (int i = 0; i < modelo.getRowCount();) {
            modelo.removeRow(i);
        }
        DAOManagerSQL manager = null;
        try {
            manager = new DAOManagerSQL("localhost", "basefarmacia", "root", "");
           
            List<personacliente> lista = manager.getPersonaClienteDAO().obtenertodos();
            List<persona> lista2 = manager.getPersonaDAO().obtenertodos();
            
            for (int i = 0; i < lista.size(); i++) {
                for (int j = 0; j < lista2.size(); j++) {
                    if (Long.compare(lista.get(i).getIdpersona() , lista2.get(j).getIdPersona())==0) {
                        Object obj[] = {lista.get(i).getIdpersonacliente(), lista.get(i).getIdpersona(), lista2.get(j).getNombre(),
                            lista2.get(j).getAppaterno(), lista2.get(j).getApmaterno(), String.valueOf(lista2.get(j).getNumerodni()), lista2.get(j).getPersonaedad(), lista2.get(j).getDireccion(), lista2.get(j).getTelefono(), lista.get(i).isStatus()};

                        modelo.addRow(obj);
                    }
                }

            }
            contador.setText("Existen "+modelo.getRowCount()+" clientes");
            manager.cerrarConexion();
        } catch (DAOException ex) {
            throw new DAOException("error al buscar" + ex.getMessage());
        }
    }

    public  void Iniciar_componentes(String titulo){
        tabla = new JTable(20, 20);

        modelo = new DefaultTableModel();
        pane1 = new JPanel(new BorderLayout());
        pane1.setBackground(c);
        JPanel pane_buscador = new JPanel();
        pane_buscador.setBackground(c);
        buscarPor = new JComboBox();
        buscarPor.addItem("Por Nombre");
        buscarPor.addItem("Por Apellido");
        buscarPor.addItem("Por DNI");
        buscarPor.addItem("Por Edad");
        txtBuscar = new JTextField(10);

        buscar = new JButton(configIma.obtenerIcono("buscar.png", 15));
        pane_buscador.add(buscarPor);
        pane_buscador.add(txtBuscar);
        pane_buscador.add(buscar);
        autocompletar = new TextAutoCompleter(txtBuscar);
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
        tabla.addKeyListener(this);
        txtBuscar.addKeyListener(this);
        buscarPor.addKeyListener(this);

    }

    public JScrollPane getTabla() {

        Object[][] data = new Object[0][0];
        String[] lista = {"idcliente", "idpersona", "Nombre", "Apellido Paterno", "Apellido Materno", "Numero de DNI", "Edad", "Direccion", "Telefono", "estado"};
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
                frmClientes.jbEliminar.setEnabled(true);
                frmClientes.jbModificar.setEnabled(true);
            }
        }
        );
        tabla.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0,false), "selectColumnCell");
        pane.setBackground(c);
        int[] tamaño = {0, 0, 150, 150, 150, 180, 100, 200, 80, 0};
        config.fijarTamaño(tabla, tamaño);
        int[] columnas = {0, 1, 9};
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
            } else if (buscarPor.getSelectedItem().toString().equals("Por Apellido")) {
                elQueOrdena.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase().trim(), 3, 4));
            } else if (buscarPor.getSelectedItem().toString().equals("Por DNI")) {
                elQueOrdena.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase().trim(), 5));
            } else if (buscarPor.getSelectedItem().toString().equals("Por Edad")) {
                elQueOrdena.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase().trim(), 6));
            }

            if (tabla.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "¡No Se encontraron Clientes!", "Informacion", JOptionPane.ERROR_MESSAGE);
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
            if (tabla.getSelectedRow() == -1) {
                return;
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                frmClientes.jbModificar.doClick();

            } else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                frmClientes.jbEliminar.doClick();
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
            frmClientes.jbSalir.doClick();
        }

        if (e.getExtendedKeyCode() == KeyEvent.VK_CONTROL) {
            teclamas = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_N && teclamas) {
            frmClientes.jbNuevo.doClick();
            teclamas = false;
        }
    }

}
