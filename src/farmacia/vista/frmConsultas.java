/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista;

import com.toedter.calendar.JDateChooser;
import farmacia.calculos.EstiloTablaHeader;
import farmacia.calculos.EstiloTablaRenderer;
import farmacia.calculos.configuracionImagenes;
import farmacia.calculos.configuracionesTabla;
import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.mysql.DAOManagerSQL;
import farmacia.jdbc.modelado.boletacabecera;
import farmacia.jdbc.modelado.boletadetalle;
import farmacia.jdbc.modelado.empleado;
import farmacia.jdbc.modelado.empresa;
import farmacia.jdbc.modelado.empresacliente;
import farmacia.jdbc.modelado.facturacabecera;
import farmacia.jdbc.modelado.facturadetalle;
import farmacia.jdbc.modelado.persona;
import farmacia.jdbc.modelado.personacliente;
import farmacia.jdbc.modelado.producto;
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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author fecyp
 */
public class frmConsultas extends JInternalFrame implements ActionListener, MouseListener {

    JDateChooser fechainicisl, fechafinal;
    JTable tablaconsulta;
    JTable tabladetalle;
    JButton bndetalle, bnbuscar;
    JLabel jlinicio, jlfin;
    JPanel paneNorth, paneSouth;
    public DefaultTableModel modeloconsulta, modelodetalle;
    public JLabel contadorconsulta, contadordetalle;
    public TableRowSorter<TableModel> elQueOrdena;
    public int indexSelecion = -1;
    public configuracionesTabla config = new configuracionesTabla();
    public configuracionImagenes configIma = new configuracionImagenes();
    public Font fontboton = new Font("Geneva", 1, 13);
    public Color c = new java.awt.Color(255, 204, 102);
    private JPanel principal;
    private JComboBox jcbtipoConsulta;

    public frmConsultas() throws DAOException {
        super("Formulario Consultas", false, true, false, true);
        Iniciar_componentes();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        actualizarTablaBoletacabecera();
        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                frmprincipal.visibleconsulta = false;
            }

        });
        jcbtipoConsulta.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == bndetalle) {
            if (paneSouth.isVisible()) {
                bndetalle.setText("Detalle <<");
                paneSouth.setVisible(false);
            } else {
                bndetalle.setText("Detalle >>");
                paneSouth.setVisible(true);

            }
            pack();
        } else if (source == bnbuscar || source == jcbtipoConsulta) {
            try {
                if (jcbtipoConsulta.getItemAt(jcbtipoConsulta.getSelectedIndex()).equals("Boleta")) {
                    actualizarTablaBoletacabecera();
                } else {
                    actualizarTablaFacturacabecera();
                }
            } catch (DAOException ex) {
                System.out.println("error");
            }
        }

    }

    public JScrollPane getTablaconsulta() {

        Object[][] data = new Object[0][0];
        String[] lista = {"idcabecera", "Correlativo", "Numero Comprobante", "Fecha", "Cliente", "Empleado", "estado"};
        modeloconsulta = new DefaultTableModel(data, lista) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JScrollPane pane = new JScrollPane(tablaconsulta);
        tablaconsulta.setModel(modeloconsulta);
        tablaconsulta.getTableHeader().setReorderingAllowed(false);
        elQueOrdena = new TableRowSorter<TableModel>(modeloconsulta);
        tablaconsulta.setRowSorter(elQueOrdena);
        tablaconsulta.getTableHeader().setReorderingAllowed(false);
        tablaconsulta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tablaconsulta.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "selectColumnCell");
        pane.setBackground(c);
        int[] tamaño = {0, 100, 100, 120, 250, 250, 0};
        config.fijarTamaño(tablaconsulta, tamaño);
        int[] columnas = {0, 6};
        config.ocultarColumnas(tablaconsulta, columnas);
        tablaconsulta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaconsulta.getTableHeader().setDefaultRenderer(new EstiloTablaHeader());
        tablaconsulta.setDefaultRenderer(Object.class, new EstiloTablaRenderer());
        return pane;
    }

    public JScrollPane gettablaDetalle() {

        Object[][] data = new Object[0][0];
        String[] lista = {"iddetalle", "idcabecera", "idproducto", "Nombre Producto", "descripcion", "dosis", "cantidad ", "Sub-total", "estado"};
        modelodetalle = new DefaultTableModel(data, lista) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JScrollPane pane = new JScrollPane(tabladetalle);
        tabladetalle.setModel(modelodetalle);
        tabladetalle.getTableHeader().setReorderingAllowed(false);
        elQueOrdena = new TableRowSorter<TableModel>(modelodetalle);
        tabladetalle.setRowSorter(elQueOrdena);
        tabladetalle.getTableHeader().setReorderingAllowed(false);
        tabladetalle.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabladetalle.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "selectColumnCell");
        pane.setBackground(c);
        int[] tamaño = {0, 0, 120, 150, 200, 120, 100, 100, 0};
        config.fijarTamaño(tabladetalle, tamaño);
        int[] columnas = {0, 1, 8};
        config.ocultarColumnas(tabladetalle, columnas);
        tabladetalle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabladetalle.getTableHeader().setDefaultRenderer(new EstiloTablaHeader());
        tabladetalle.setDefaultRenderer(Object.class, new EstiloTablaRenderer());
        return pane;
    }

    private void Iniciar_componentes() {
        tablaconsulta = new JTable(20, 20);

        modeloconsulta = new DefaultTableModel();
        paneNorth = new JPanel(new BorderLayout());
        paneNorth.setBackground(c);
        JPanel pane_buscador = new JPanel(new FlowLayout());
        pane_buscador.setBackground(c);
        JPanel inicio = new JPanel(new BorderLayout());
        jlinicio = new JLabel("Desde: ");
        fechainicisl = new JDateChooser();
        fechainicisl.setPreferredSize(new Dimension(122, 30));
        fechainicisl.setDate(new Date());
        inicio.add(jlinicio, BorderLayout.WEST);
        inicio.add(fechainicisl, BorderLayout.EAST);
        JPanel fin = new JPanel(new BorderLayout());
        jlfin = new JLabel("hasta: ");
        fechafinal = new JDateChooser();
        fechafinal.setPreferredSize(new Dimension(122, 30));
        fechafinal.setDate(new Date());
        fin.add(jlfin, BorderLayout.WEST);
        fin.add(fechafinal, BorderLayout.EAST);
        bnbuscar = new JButton("Buscar ventas", configIma.obtenerIcono("buscar.png", 15));
        String[] lista = {"Boleta", "Factura"};
        JPanel tipo=new JPanel();
        jcbtipoConsulta = new JComboBox(lista);
        tipo.add(jcbtipoConsulta);
        pane_buscador.add(tipo);
        pane_buscador.add(inicio);
        pane_buscador.add(fin);
        pane_buscador.add(bnbuscar);
        inicio.setBackground(c);
        fin.setBackground(c);
        tipo.setBackground(c);
        contadorconsulta = new JLabel("Existen 0 usuarios");
        paneNorth.add(pane_buscador, BorderLayout.NORTH);
        paneNorth.add(getTablaconsulta(), BorderLayout.CENTER);
        paneNorth.add(contadorconsulta, BorderLayout.SOUTH);
        paneNorth.setPreferredSize(new Dimension(850, 400));

        setLayout(new FlowLayout());
        paneNorth.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20),
                BorderFactory.createTitledBorder("Listado de boletas")));

        JPanel panedetalle = new JPanel(new BorderLayout());
        bndetalle = new JButton("Detalle <<");
        panedetalle.add(bndetalle, BorderLayout.EAST);
        panedetalle.setBackground(c);
        bndetalle.setEnabled(false);
        tabladetalle = new JTable(20, 20);
        modelodetalle = new DefaultTableModel();
        paneSouth = new JPanel(new BorderLayout());
        paneSouth.setBackground(c);

        contadordetalle = new JLabel("Existen 0 usuarios");
        paneSouth.add(gettablaDetalle(), BorderLayout.CENTER);
        paneSouth.add(contadordetalle, BorderLayout.SOUTH);
        paneSouth.setPreferredSize(new Dimension(850, 400));

        setLayout(new FlowLayout());
        paneSouth.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20),
                BorderFactory.createTitledBorder("Detalles")));

        bnbuscar.addActionListener(this);
        bndetalle.addActionListener(this);
        tablaconsulta.addMouseListener(this);

        setLayout(new GridLayout(1, 1));
        principal = new JPanel(new BorderLayout());
        principal.add(paneNorth, BorderLayout.NORTH);
        principal.add(panedetalle, BorderLayout.CENTER);
        principal.add(paneSouth, BorderLayout.SOUTH);
        paneSouth.setVisible(false);
        setBackground(c);

        add(principal);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source == tablaconsulta) {
            int index = tablaconsulta.getSelectedRow();
            if (index == -1) {
                return;
            }
            bndetalle.setEnabled(true);
            try {
                if (jcbtipoConsulta.getItemAt(jcbtipoConsulta.getSelectedIndex()).equals("Boleta")) {
                    actualizarTablaBoletacabecera();
                } else {
                    actualizarTablaFacturacabecera();
                }
            } catch (DAOException ex) {
                System.out.println("Error");
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

    public void actualizarTablaBoletaDetalle() throws DAOException {
        for (int i = 0; i < modelodetalle.getRowCount();) {
            modelodetalle.removeRow(i);
        }
        int index = tablaconsulta.getSelectedRow();
        if (index == -1) {
            return;
        }
        Long idcabecera = new Long((long) tablaconsulta.getValueAt(index, 0));
        DAOManagerSQL manager = null;
        try {
            manager = new DAOManagerSQL("localhost", "basefarmacia", "root", "");

            List<boletadetalle> lista = manager.getBoletaDetalleDAO().obtenerDetallesBoleta(idcabecera);

            for (int i = 0; i < lista.size(); i++) {
                producto p = manager.getProductoDAO().obtener(lista.get(i).getIdproducto());
                String nombre = p.getNombreproducto();
                String dosis = p.getDosisproducto();
                String descri = p.getDescripcionproducto();

                Object obj[] = {lista.get(i).getIdboletadetalle(), lista.get(i).getIdboletacabecera(), lista.get(i).getIdproducto(),
                    nombre, descri, dosis, lista.get(i).getCantidad(), lista.get(i).getSubtotal(), lista.get(i).isStatus()};

                modelodetalle.addRow(obj);

            }
            contadordetalle.setText("Existen " + modelodetalle.getRowCount() + " productos");
            manager.cerrarConexion();
        } catch (DAOException ex) {
            throw new DAOException("error al buscar" + ex.getMessage());
        }
    }

    public void actualizarTablaBoletacabecera() throws DAOException {
        for (int i = 0; i < modeloconsulta.getRowCount();) {
            modeloconsulta.removeRow(i);
        }
        DAOManagerSQL manager = null;
        Date min = fechainicisl.getDate();
        Date max = fechafinal.getDate();
        if (min.compareTo(max) > 0) {
            JOptionPane.showMessageDialog(null, "no se puede buscar en ese rango", "error", JOptionPane.ERROR_MESSAGE);
        }
        try {
            manager = new DAOManagerSQL("localhost", "basefarmacia", "root", "");

            List<boletacabecera> lista = manager.getBoletaCabeceraDAO().obtenerportiempo(min, max);

            for (int i = 0; i < lista.size(); i++) {
                personacliente cliente = manager.getPersonaClienteDAO().obtener(lista.get(i).getIdpersonacliente());
                persona per = manager.getPersonaDAO().obtener(cliente.getIdpersona());
                String nombre = per.getNombre() + " " + per.getAppaterno() + " " + per.getApmaterno();
                empleado emp = manager.getEmpleadoDAO().obtener(lista.get(i).getIdempleado());
                persona peremp = manager.getPersonaDAO().obtener(emp.getIdpersona());
                String empleado = peremp.getNombre() + " " + peremp.getAppaterno() + " " + peremp.getApmaterno();
                Object obj[] = {lista.get(i).getIdboletacabecera(), lista.get(i).getCorrelativoboleta(), lista.get(i).getNumeroboleta(),
                    lista.get(i).getFechaemisionboleta(), nombre, empleado, lista.get(i).isStatus()};

                modeloconsulta.addRow(obj);

            }
            contadorconsulta.setText("Existen " + modeloconsulta.getRowCount() + " ventas");
            manager.cerrarConexion();
        } catch (DAOException ex) {
            throw new DAOException("error al buscar" + ex.getMessage());
        }
    }

    public void actualizarTablaFacturaDetalle() throws DAOException {
        for (int i = 0; i < modelodetalle.getRowCount();) {
            modelodetalle.removeRow(i);
        }
        int index = tablaconsulta.getSelectedRow();
        if (index == -1) {
            return;
        }
        Long idcabecera = new Long((long) tablaconsulta.getValueAt(index, 0));
        DAOManagerSQL manager = null;
        try {
            manager = new DAOManagerSQL("localhost", "basefarmacia", "root", "");

            List<facturadetalle> lista = manager.getFActuraDetalleDAO().obtenerDetallesFactura(idcabecera);

            for (int i = 0; i < lista.size(); i++) {
                producto p = manager.getProductoDAO().obtener(lista.get(i).getIdproducto());
                String nombre = p.getNombreproducto();
                String dosis = p.getDosisproducto();
                String descri = p.getDescripcionproducto();

                Object obj[] = {lista.get(i).getIdfacturadetalle(), lista.get(i).getIdfacturacabecera(), lista.get(i).getIdproducto(),
                    nombre, descri, dosis, lista.get(i).getCantidad(), lista.get(i).getSubtotal(), lista.get(i).isStatus()};

                modelodetalle.addRow(obj);

            }
            contadordetalle.setText("Existen " + modelodetalle.getRowCount() + " productos");
            manager.cerrarConexion();
        } catch (DAOException ex) {
            throw new DAOException("error al buscar" + ex.getMessage());
        }
    }

    public void actualizarTablaFacturacabecera() throws DAOException {
        for (int i = 0; i < modeloconsulta.getRowCount();) {
            modeloconsulta.removeRow(i);
        }
        DAOManagerSQL manager = null;
        Date min = fechainicisl.getDate();
        Date max = fechafinal.getDate();
        if (min.compareTo(max) > 0) {
            JOptionPane.showMessageDialog(null, "no se puede buscar en ese rango", "error", JOptionPane.ERROR_MESSAGE);
        }
        try {
            manager = new DAOManagerSQL("localhost", "basefarmacia", "root", "");

            List<facturacabecera> lista = manager.getFacturaCabeceraDAO().obtenerportiempo(min, max);

            for (int i = 0; i < lista.size(); i++) {
                empresacliente cliente = manager.getEmpresaClienteDAO().obtener(lista.get(i).getIdempresacliente());
                empresa em = manager.getEmpresaDAO().obtener(cliente.getIdempresa());
                String nombre = em.getRazonsocial();
                empleado emp = manager.getEmpleadoDAO().obtener(lista.get(i).getIdempleado());
                persona peremp = manager.getPersonaDAO().obtener(emp.getIdpersona());
                String empleado = peremp.getNombre() + " " + peremp.getAppaterno() + " " + peremp.getApmaterno();
                Object obj[] = {lista.get(i).getIdfacturacabecera(), lista.get(i).getCorrelativofactura(), lista.get(i).getNumerofactura(),
                    lista.get(i).getFechaemisionfactura(), nombre, empleado, lista.get(i).isStatus()};

                modeloconsulta.addRow(obj);

            }
            contadorconsulta.setText("Existen " + modeloconsulta.getRowCount() + " ventas");
            manager.cerrarConexion();
        } catch (DAOException ex) {
            throw new DAOException("error al buscar" + ex.getMessage());
        }
    }
}
