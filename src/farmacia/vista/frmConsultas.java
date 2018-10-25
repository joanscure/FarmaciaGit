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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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
public class frmConsultas extends JInternalFrame implements ActionListener {

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

    public frmConsultas() {
        super("Formulario Consultas", false, true, false, true);
        Iniciar_componentes();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();

        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                frmprincipal.visibleconsulta = false;
            }

        });
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
        }
    }

    public JScrollPane getTablaconsulta() {

        Object[][] data = new Object[0][0];
        String[] lista = {"idboletacabecera", "Correlativo", "Numero boleta", "Fecha", "Cliente", "Empleado", "estado"};
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

        tablaconsulta.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "selectColumnCell");
        pane.setBackground(c);

        int[] columnas = {0, 6};
        config.ocultarColumnas(tablaconsulta, columnas);
        tablaconsulta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaconsulta.getTableHeader().setDefaultRenderer(new EstiloTablaHeader());
        tablaconsulta.setDefaultRenderer(Object.class, new EstiloTablaRenderer());
        return pane;
    }

    public JScrollPane gettablaDetalle() {

        Object[][] data = new Object[0][0];
        String[] lista = {"idboletadetalle", "idboletacabecera", "idproducto", "cantidad ", "Sub-total", "estado"};
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

        tabladetalle.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "selectColumnCell");
        pane.setBackground(c);
        int[] columnas = {0, 1, 5};
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
        JPanel pane_buscador = new JPanel();
        pane_buscador.setBackground(c);
        JPanel inicio = new JPanel(new BorderLayout());
        jlinicio = new JLabel("Desde: ");
        fechainicisl = new JDateChooser();
        fechainicisl.setPreferredSize(new Dimension(122, 30));
        fechainicisl.setDate(new Date());
        inicio.add(jlinicio, BorderLayout.WEST);
        inicio.add(fechainicisl, BorderLayout.EAST);
        JPanel fin = new JPanel(new BorderLayout());
        jlfin = new JLabel("Desde: ");
        fechafinal = new JDateChooser();
        fechafinal.setPreferredSize(new Dimension(122, 30));
        fechafinal.setDate(new Date());
        fin.add(jlfin, BorderLayout.WEST);
        fin.add(fechafinal, BorderLayout.EAST);
        bnbuscar = new JButton("Buscar ventas", configIma.obtenerIcono("buscar.png", 15));
        pane_buscador.add(inicio);
        pane_buscador.add(fin);
        pane_buscador.add(bnbuscar);
        inicio.setBackground(c);
        fin.setBackground(c);
        contadorconsulta = new JLabel("Existen 0 usuarios");
        paneNorth.add(pane_buscador, BorderLayout.NORTH);
        paneNorth.add(getTablaconsulta(), BorderLayout.CENTER);
        paneNorth.add(contadorconsulta, BorderLayout.SOUTH);
        paneNorth.setPreferredSize(new Dimension(700, 300));

        setLayout(new FlowLayout());
        paneNorth.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20),
                BorderFactory.createTitledBorder("Listado de boletas")));

        JPanel panedetalle = new JPanel(new BorderLayout());
        bndetalle = new JButton("Detalle <<");
        panedetalle.add(bndetalle,BorderLayout.EAST);
        panedetalle.setBackground(c);

        tabladetalle = new JTable(20, 20);
        modelodetalle = new DefaultTableModel();
        paneSouth = new JPanel(new BorderLayout());
        paneSouth.setBackground(c);

        contadordetalle = new JLabel("Existen 0 usuarios");
        paneSouth.add(gettablaDetalle(), BorderLayout.CENTER);
        paneSouth.add(contadordetalle, BorderLayout.SOUTH);
        paneSouth.setPreferredSize(new Dimension(700, 200));

        setLayout(new FlowLayout());
        paneSouth.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20),
                BorderFactory.createTitledBorder("detalle")));

        bnbuscar.addActionListener(this);
        bndetalle.addActionListener(this);

        setLayout(new GridLayout(1, 1));
        principal = new JPanel(new BorderLayout());
        principal.add(paneNorth, BorderLayout.NORTH);
        principal.add(panedetalle, BorderLayout.CENTER);
        principal.add(paneSouth, BorderLayout.SOUTH);
        paneSouth.setVisible(false);
        setBackground(c);

        add(principal);

    }

}
