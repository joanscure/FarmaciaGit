/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.diseño.estrategias;

import com.mxrck.autocompleter.TextAutoCompleter;
import farmacia.calculos.configuracionImagenes;
import farmacia.calculos.configuracionesTabla;
import farmacia.jdbc.dao.DAOException;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author fecyp
 */
public abstract class EstrategiaFrameVistas extends JFrame {

    public JTable tabla;
    public DefaultTableModel modelo;
    public JPanel principal;
    public JPanel pane1;
    public JButton buscar, agregar, salir;
    public JTextField txtBuscar;
    public JComboBox buscarPor;
    public JLabel contador;
    public TextAutoCompleter autocompletar;
    public TableRowSorter<TableModel> elQueOrdena;
    public int indexSelecion = -1;
    public String dni;
    public configuracionesTabla config = new configuracionesTabla();
    public configuracionImagenes configIma = new configuracionImagenes();
    public Font fontboton = new Font("Geneva", 1, 13);
    public Color c = new java.awt.Color(255, 204, 102);
    public boolean control = true;
    public boolean teclamas = false;
    public EstrategiaFrameVistas(String titulo)
    {
         iniciar_componentes(titulo);
        personalizartipoletra();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(683, 553);
        agregar.setEnabled(false);
        txtBuscar.requestFocus();
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                salir.doClick();
            }

        });
    }
    public void personalizartipoletra() {
        buscar.setFont(fontboton);
        txtBuscar.setFont(fontboton);
        buscarPor.setFont(fontboton);
        contador.setFont(fontboton);
        tabla.setFont(new Font("Geneva", 0, 13));
        tabla.getTableHeader().setFont(fontboton);

    }
    public abstract void iniciar_componentes(String titulo);
     public abstract  void actualizartabla() throws DAOException;
    
}
