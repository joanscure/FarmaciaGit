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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author fecyp
 */
public abstract class EstrategiaPaneListado extends JPanel {
    
    public static  JTable tabla;
    public DefaultTableModel modelo;
    public JPanel principal;
    public JPanel pane1;
    public JButton buscar;
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
    public EstrategiaPaneListado(String titulo)
    {
        Iniciar_componentes(titulo);
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
    public abstract void Iniciar_componentes(String titulo);
    public abstract JScrollPane getTabla();
    public abstract void actualizartabla()throws DAOException;
}
