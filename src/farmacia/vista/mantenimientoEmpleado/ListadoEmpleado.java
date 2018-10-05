/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista.mantenimientoEmpleado;

import app.bolivia.swing.JCTextField;
import farmacia.vista.mantenimientoCliente.*;
import com.mxrck.autocompleter.TextAutoCompleter;
import farmacia.calculos.EstiloTablaHeader;
import farmacia.calculos.EstiloTablaRenderer;
import farmacia.calculos.configuracionImagenes;
import farmacia.calculos.configuracionesTabla;
import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.mysql.DAOManagerSQL;
import farmacia.jdbc.modelado.empleado;
import farmacia.jdbc.modelado.persona;
import farmacia.jdbc.modelado.personacliente;
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
public class ListadoEmpleado extends JPanel implements ActionListener, KeyListener {

    JTable tabla;
    DefaultTableModel modelo;
    JPanel principal;
    JPanel pane1;
    JButton buscar;
    public JCTextField txtBuscar;
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
    public boolean control = true;
    public boolean teclamas=false;

    public ListadoEmpleado() {
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
     public void actualizartabla() throws DAOException {
        for (int i = 0; i < modelo.getRowCount();) {
            modelo.removeRow(i);
        }
        DAOManagerSQL manager = null;
        try {
            manager = new DAOManagerSQL("localhost", "basefarmacia", "root", "");
           
            List<empleado> lista = manager.getEmpleadoDAO().obtenertodos();
            List<persona> lista2 = manager.getPersonaDAO().obtenertodos();
            
            for (int i = 0; i < lista.size(); i++) {
                for (int j = 0; j < lista2.size(); j++) {
                    if (Long.compare(lista.get(i).getIdpersona() , lista2.get(j).getIdPersona())==0) {
                        Object obj[] = {lista.get(i).getIdempleado(), lista.get(i).getIdpersona(), lista2.get(j).getNombre(),
                            lista2.get(j).getAppaterno(), lista2.get(j).getApmaterno(), String.valueOf(lista2.get(j).getNumerodni()), 
                            lista2.get(j).getPersonaedad(), lista2.get(j).getDireccion(), lista2.get(j).getTelefono(),
                                lista.get(i).getLogin(),lista.get(i).getPassword(),lista.get(i).getIdtipotrabajador(), lista.get(i).isStatus()};

                        modelo.addRow(obj);
                    }
                }

            }
            contador.setText("Existen "+modelo.getRowCount()+" empleados");
            manager.cerrarConexion();
        } catch (DAOException ex) {
            throw new DAOException("error al buscar" + ex.getMessage());
        }
    }

    private void iniciar_componentes() {
        tabla = new JTable(20, 20);

        modelo = new DefaultTableModel();
        pane1 = new JPanel(new BorderLayout());
        pane1.setBackground(c);
        JPanel pane_buscador = new JPanel();
        pane_buscador.setBackground(c);
        buscarPor = new JComboBox();
        buscarPor.addItem("Por Nombre");
        buscarPor.addItem("Por Apellido");
        buscarPor.addItem("Por Documento");
        buscarPor.addItem("Por Edad");
        txtBuscar = new JCTextField();
        txtBuscar.setPlaceholder("Buscar");
        buscar = new JButton(configIma.obtenerIcono("buscar.png", 15));
        pane_buscador.add(buscarPor);
        pane_buscador.add(txtBuscar);
        pane_buscador.add(buscar);
        autocompletar = new TextAutoCompleter(txtBuscar);
        contador = new JLabel("Existen 0 usuarios");
        pane1.add(pane_buscador, BorderLayout.NORTH);
        pane1.add(empleados_tabla(), BorderLayout.CENTER);
        pane1.add(contador, BorderLayout.SOUTH);
        pane1.setPreferredSize(new Dimension(700, 400));

        setLayout(new FlowLayout());
        pane1.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20),
                BorderFactory.createTitledBorder("Listado de Clientes")));

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

    public JScrollPane empleados_tabla() {

        Object[][] data = new Object[0][0];
        String[] lista = {"idempleado", "idpersona" ,"Nombre", "Apellido Paterno",
            "Apellido Materno", "Numero de Documento","Edad", "Direccion", "Telefono","Nombre usuario","clave","ocupacion","estado"};
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
                frmEmpleados.jbEliminar.setEnabled(true);
                frmEmpleados.jbModificar.setEnabled(true);
            }
        }
        );
        tabla.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0,false), "selectColumnCell");
        pane.setBackground(c);
        int[] tamaño = {0, 0, 80, 150, 150, 180,100, 200, 80, 0,0,0,0};
        config.fijarTamaño(tabla, tamaño);
        int[] columnas = {0, 1,9,10,11,12};
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
                elQueOrdena.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase().trim(), 3));
            } else if (buscarPor.getSelectedItem().toString().equals("Por Apellido")) {
                elQueOrdena.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase().trim(), 4, 5));
            } else if (buscarPor.getSelectedItem().toString().equals("Por Documento")) {
                elQueOrdena.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase().trim(), 6));
            }else if (buscarPor.getSelectedItem().toString().equals("Por Edad")) {
                elQueOrdena.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase().trim(), 7));
            }

            if (tabla.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "¡No Se encontraron Empleados!","Informacion",JOptionPane.ERROR_MESSAGE);
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
//                int index = tabla.getSelectedRow();
//                if (index == 0) {
//                    index = tabla.getRowCount();
//                }
//                index--;
//                control = false;
//                tabla.changeSelection(index, 0, false, false);
//                //se pasa el index como parametro o se usa el selected
////            control = true;
                frmEmpleados.jbModificar.doClick();

            } else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                frmEmpleados.jbEliminar.doClick();
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
                frmEmpleados.jbSalir.doClick();
            }
         
        
        if (e.getExtendedKeyCode()== KeyEvent.VK_CONTROL ) {
                  teclamas=true;
            }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
      
       if (e.getKeyCode() == KeyEvent.VK_N &&teclamas) {
                    frmEmpleados.jbNuevo.doClick();
                    teclamas=false;
                }
    }

}
