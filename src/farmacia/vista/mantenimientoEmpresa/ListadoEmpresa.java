/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista.mantenimientoEmpresa;

import com.mxrck.autocompleter.TextAutoCompleter;
import farmacia.calculos.EstiloTablaHeader;
import farmacia.calculos.EstiloTablaRenderer;
import farmacia.diseño.estrategias.EstrategiaPaneListado;
import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.mysql.DAOManagerSQL;
import farmacia.jdbc.modelado.empresa;
import farmacia.jdbc.modelado.empresacliente;
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
public class ListadoEmpresa extends EstrategiaPaneListado implements ActionListener {
  public static  JTable tabla;
   

    ListadoEmpresa(String titulo) {
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
           
            List<empresacliente> lista = manager.getEmpresaClienteDAO().obtenertodos();
            List<empresa> lista2 = manager.getEmpresaDAO().obtenertodos();
            for (int i = 0; i < lista.size(); i++) {
                for (int j = 0; j < lista2.size(); j++) {
                    if (Long.compare(lista.get(i).getIdempresa(), lista2.get(j).getIdempresa())==0) {
                        Object obj[] = {lista.get(i).getIdempresacliente(), lista.get(i).getIdempresa(), lista2.get(j).getRazonsocial(),
                            String.valueOf(lista2.get(j).getRucempresa()), lista2.get(j).getDireccion(), lista2.get(j).getTelefono(),  lista.get(j).getFecharegistro(), lista.get(i).isStatus()};

                        modelo.addRow(obj);
                    }
                }

            }
            contador.setText("Existen "+modelo.getRowCount()+" empresas");
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
        buscarPor.addItem("Por Razon social");
        buscarPor.addItem("Por RUC");
        txtBuscar = new JTextField(10);

        buscar = new JButton(configIma.obtenerIcono("buscar.png", 15));
        pane_buscador.add(buscarPor);
        pane_buscador.add(txtBuscar);
        pane_buscador.add(buscar);
        contador = new JLabel("Existen 0 usuarios");
        pane1.add(pane_buscador, BorderLayout.NORTH);
        pane1.add(getTabla(), BorderLayout.CENTER);
        pane1.add(contador, BorderLayout.SOUTH);
        pane1.setPreferredSize(new Dimension(700, 400));

        setLayout(new FlowLayout());
        pane1.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20),
                BorderFactory.createTitledBorder("Listado de "+titulo)));

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
        
        tabla.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0,false), "selectColumnCell");
        pane.setBackground(c);
        int[] tamaño = {0, 0, 150, 150, 250, 150, 150, 0};
        config.fijarTamaño(tabla, tamaño);
        int[] columnas = {0, 1, 7};
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
            if (buscarPor.getSelectedItem().toString().equals("Por Razon social")) {
                elQueOrdena.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase().trim(), 2));
            }else if (buscarPor.getSelectedItem().toString().equals("Por RUC")) {
                elQueOrdena.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase().trim(), 3));
            }

            if (tabla.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "¡No Se encontraron  Empresas!","Informacion",JOptionPane.ERROR_MESSAGE);
                txtBuscar.requestFocus();
            } else {
                tabla.requestFocus();
            }

        } 

    }

  

}
