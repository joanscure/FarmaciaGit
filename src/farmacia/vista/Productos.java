/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * agregar a la factura
 * @author fecyp
 */
   public class Productos extends JPanel implements ActionListener, KeyListener{
    JComboBox filtros;
    JLabel etiquetaProducto;
    GUI interfaz;
    JTable tablaProducto=new JTable();
    JTextField txtBuscar,txtCantidad;
    JButton buscar,agregar,finalizar;
    JLabel jlbCantidad;
    DefaultTableModel modelo;
    JPanel principal_pane;
    TextAutoCompleter autocompletar;
    TableRowSorter<TableModel> elQueOrdena;
    int index;
    Productos( GUI interfaz){
        this.interfaz=interfaz;
        principal_pane=new JPanel();
        
        principal_pane.setLayout(new BorderLayout());
        principal_pane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createTitledBorder("Listado de Productos")));
        iniciarComponentes();


        add(principal_pane);
        
        
        agregar.addActionListener(this);
        finalizar.addActionListener(this);
        buscar.addActionListener(this);
        txtBuscar.addKeyListener(this);
        txtBuscar.addActionListener(this);
        filtros.addActionListener(this);
         autocompletar=new TextAutoCompleter(txtBuscar);
        txtCantidad.setText("1");
        cargarDatos("idProducto");
       tablaProducto.addKeyListener(this);
       txtCantidad.addKeyListener(this);
       txtCantidad.addActionListener(this);
       agregar.setEnabled(false);
       agregar.addKeyListener(this);
        cargarDatos();
    }
    public JScrollPane productos_tabla()
{
	
	Object[][] data=new Object[0][0];
	String[] lista={"COD","PRODUCTO","DOSIS","TIPO","STOCK","COSTO"};
	 
          modelo=new DefaultTableModel(data,lista)
			 {
		 public boolean isCellEditable(int row,int column)
		 {
			 return false;
		 }
			 };
        
    JScrollPane pane=new JScrollPane(tablaProducto);
    tablaProducto.setModel(modelo);
	tablaProducto.getTableHeader().setReorderingAllowed(false);
        elQueOrdena = new TableRowSorter<TableModel>(modelo);
        tablaProducto.setRowSorter(elQueOrdena);
        tablaProducto.getSelectionModel().addListSelectionListener(e->{
             index=tablaProducto.getSelectedRow();
            agregar.setEnabled(true);
            
            
        });
        tablaProducto.getColumnModel().getColumn(0).setPreferredWidth(60);
        tablaProducto.getColumnModel().getColumn(1).setPreferredWidth(200);
        tablaProducto.getColumnModel().getColumn(2).setPreferredWidth(100);
        tablaProducto.getColumnModel().getColumn(3).setPreferredWidth(100);
        tablaProducto.getColumnModel().getColumn(4).setPreferredWidth(60);
        tablaProducto.getColumnModel().getColumn(5).setPreferredWidth(70);
	
    return pane;
}
    @Override
    public void actionPerformed(ActionEvent e) {
         Object source=e.getSource();
         if(source==finalizar)
         {
          
             setVisible(false);
             interfaz.pack();
         }
        
         else if(source==buscar || source==txtBuscar)
         {
            if(filtros.getSelectedItem().toString().equals("Por Codigo"))
                elQueOrdena.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase().trim(), 0));
            else if(filtros.getSelectedItem().toString().equals("Por Nombre"))
                 elQueOrdena.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase().trim(), 1));
            else if(filtros.getSelectedItem().toString().equals("Por Tipo"))
                 elQueOrdena.setRowFilter(RowFilter.regexFilter(txtBuscar.getText().toUpperCase().trim(), 3));
            
            tablaProducto.changeSelection(0, 1, false, false);
            tablaProducto.requestFocus();
           
         }
         else if(source==filtros)
         {
             autocompletar.removeAllItems();
             if(filtros.getSelectedItem().toString().equals("Por Codigo"))
             {
                 cargarDatos("idProducto");
             }
            else if(filtros.getSelectedItem().toString().equals("Por Nombre"))
            {
                 cargarDatos("descripcionProducto");
            }
            else if(filtros.getSelectedItem().toString().equals("Por Tipo"))
            {
                autocompletar.addItem("TABLETA");
                autocompletar.addItem("INYECTABLE");
                autocompletar.addItem("SUSPENSION");
                autocompletar.addItem("JARABE");
            }
         }
         
         else
         if(source==agregar ||source==txtCantidad)
         {
             int cantidadActual=Integer.parseInt((String) tablaProducto.getValueAt(index, 4));
             int cantidadPedidad=Integer.parseInt(txtCantidad.getText());
             int nuevaCantidad=cantidadActual-cantidadPedidad;
             String idproducto=(String) tablaProducto.getValueAt(index, 0);
             double costo=Double.parseDouble((String) tablaProducto.getValueAt(index, 5));
             if(nuevaCantidad>=0)
             {
                 actualizarstock(nuevaCantidad,idproducto);
                 
                 Object[] fila=llenarFactura(cantidadPedidad);
                    if(nuevaCantidad==0)
                    {

                        borrarProducto(idproducto);
                    }
                    if(interfaz.modelo.getRowCount()==0)
                    {
                        interfaz.modelo.addRow(fila);
                    }
                    else {
                       
                        for (int i = 0; i < interfaz.modelo.getRowCount(); i++) {
                          
                            if(idproducto.equals(interfaz.modelo.getValueAt(i, 1)))
                            {
                                
                                    int c=(int)(fila[3]);
                                 int aux=((int)interfaz.modelo.getValueAt(i, 3))+c;
                                 double descuentoAux=generarDescuento((double)10, aux);
                                 double total=(costo*aux)-((costo*aux)*descuentoAux/100);
                                 fila[3]=aux;
                                 fila[5]=(costo*aux)*descuentoAux/100;
                                 fila[6]=total;
                                 interfaz.modelo.removeRow(i);
                                 
                            }
                        }
                         interfaz.modelo.addRow(fila);
                    }
                    
            }
                
             else 
             {
                 JOptionPane.showConfirmDialog(null, "Solo contamos con "+cantidadActual+" del PRoducto seleccionado", "Error",  JOptionPane.DEFAULT_OPTION,0);
             }
              cargarDatos();
              actualizarDatosFactura();
              agregar.setEnabled(false);
              txtCantidad.setText("1");
              interfaz.contador.setText(interfaz.modelo.getRowCount()+" Productos");
              interfaz.GrabarComprobante.setEnabled(true);
         }
    }
    public void cargarDatos(String opcion)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/farmaciav2", "root", "");
            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM producto";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                 autocompletar.addItem(rs.getString(opcion));
            }  
            
            conexion.close();            
        } catch (Exception e) {
            System.out.println("Imposible realizar conexion con la BD11"
                    +e.getMessage());            
        }
    }
    public double buscarDatos(String codigo)
    {
        double desc=10;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/farmaciav2", "root", "");
            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM producto WHERE idproducto LIKE '"+codigo+"'";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                 desc=(rs.getDouble("maximodescuento")*10)+0.1;
            }  
            
            conexion.close();            
        } catch (Exception e) {
            System.out.println("Imposible realizar conexion con la BD11"
                    +e.getMessage());            
        }
        return desc;
    }
    public int buscarCant(String codigo)
    {
        int desc=0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/farmaciav2", "root", "");
            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM producto WHERE idproducto LIKE '"+codigo+"'";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                 desc=(rs.getInt("stockproducto"));
            }  
            
            conexion.close();            
        } catch (Exception e) {
            System.out.println("Imposible realizar conexion con la BD11"
                    +e.getMessage());            
        }
        return desc;
    }

    public void cargarDatos() {
         for (int i = 0; i < modelo.getRowCount(); ) {
              modelo.removeRow(i);
          }
          try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/farmaciav2", "root", "");
            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM `producto` WHERE `estado` LIKE 1 ";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                 Object fila[]=new Object[6];
                 fila[0]=rs.getString("idproducto");
                 fila[1]=rs.getString("descripcionproducto");
                  fila[2]=rs.getString("dosisproducto");
                   fila[3]=rs.getString("formafarmaceutica");
                    fila[4]=rs.getString("stockproducto");
                     fila[5]=rs.getString("preciofinal");
                     modelo.addRow(fila);
            }  
            conexion.close();            
        } catch (Exception e) {
            System.out.println("Imposible realizar conexion con la BD"
                    +e.getMessage());            
        }
    }
    public Object[] llenarFactura(int cantidad)
    {
        int selected=index;
       
        double desc=generarDescuento(buscarDatos((String) modelo.getValueAt(selected,0)),cantidad);
        double subTotal=Double.parseDouble((String) modelo.getValueAt(selected,5))*cantidad;
        double cantidadDesc=desc*subTotal/100;
        BigDecimal bd = new BigDecimal(cantidadDesc);
    bd = bd.setScale(2, RoundingMode.HALF_UP);
    BigDecimal bd2 = new BigDecimal((subTotal-cantidadDesc));
    bd2 = bd2.setScale(2, RoundingMode.HALF_UP);
        Object []fila=new Object[7];
                fila[0]=0;
                fila[1]=modelo.getValueAt(selected,0);
                fila[2]=modelo.getValueAt(selected,1)+" "+modelo.getValueAt(selected,2);
                fila[3]=cantidad;
                fila[4]=modelo.getValueAt(selected,5);
                fila[5]=(bd.doubleValue());
                fila[6]=((bd2.doubleValue()));
              return fila;
                
    }
     double generarDescuento(double desc,int cantidad)
      {
          //100% 10/4 2,
          if(cantidad< 5)
          {
              return 0;
          }
          else if(cantidad<20)
          {
              return desc/4;
          }
          else if(cantidad< 50)
          {
               return desc/2;
          }
          return desc;
      }
    public void actualizarstock(int cantidad,String codigo)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/farmaciav2", "root", "");
            Statement st = conexion.createStatement();
            String sql = "UPDATE `producto` SET `stockproducto`='"+cantidad+"' WHERE idproducto LIKE '"+codigo+"'";            
            st.executeUpdate(sql);            
            conexion.close();            
        } catch (Exception e) {
            System.out.println("Imposible realizar conexion con la BD"
                    +e.getMessage());            
        }
    }
    public void borrarProducto(String codigo)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/farmaciav2", "root", "");
            Statement st = conexion.createStatement();
            String sql = "UPDATE `producto` SET `estado`=0  WHERE idproducto LIKE '"+codigo+"'";            
            st.executeUpdate(sql);            
            conexion.close();            
        } catch (Exception e) {
            System.out.println("Imposible realizar conexion con la BD"
                    +e.getMessage());            
        }
    }
    public void habilitarEstado(String codigo)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/farmaciav2", "root", "");
            Statement st = conexion.createStatement();
            String sql = "UPDATE `producto` SET `estado`=1  WHERE idproducto LIKE '"+codigo+"'";            
            st.executeUpdate(sql);            
            conexion.close();            
        } catch (Exception e) {
            System.out.println("Imposible realizar conexion con la BD"
                    +e.getMessage());            
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        if(ke.getSource().equals(txtCantidad)) 
        {
            if(ke.getKeyChar()<48 || ke.getKeyChar()>57) ke.consume();  
            if(txtCantidad.getText().length()>2) ke.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
       
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if(ke.getSource()==agregar){
            if(ke.getKeyCode()==KeyEvent.VK_ENTER) 
        {
            this.getRootPane().setDefaultButton(agregar);
        }
    }
    }

    private void iniciarComponentes() {
       
        JPanel pane_north=new JPanel(new GridLayout(1,3));
       filtros = new JComboBox();
        filtros.addItem("Por Codigo");
        filtros.addItem("Por Nombre");
        filtros.addItem("Por Tipo");
        filtros.setSelectedIndex(0);
        buscar=new JButton(interfaz.obtenerIcono("buscar.png"));
        txtBuscar=new JTextField(10);
        etiquetaProducto=new JLabel("Buscar Productos: ");
        
        pane_north.add(etiquetaProducto);
        pane_north.add(txtBuscar);
        pane_north.add(filtros);
        pane_north.add(buscar);
        
        
        JPanel pane_center=new JPanel(new GridLayout(1,1));
        pane_center.add(productos_tabla());
        
        JPanel panesouth=new JPanel(new BorderLayout());
        JPanel pane_south=new JPanel(new GridLayout(1,3));
        JPanel pane_cantidad=new JPanel();
        finalizar=new JButton("Finalizar");
        jlbCantidad=new JLabel("Cantidad");
        txtCantidad=new JTextField(5);
        agregar=new JButton("Agregar a La Factura");
        pane_cantidad.add(jlbCantidad);
        pane_cantidad.add(txtCantidad);
        pane_south.add(pane_cantidad);
        pane_south.add(agregar);
        pane_south.add(finalizar);
        panesouth.add(pane_south,BorderLayout.CENTER);
        
        principal_pane.add(pane_north,BorderLayout.NORTH);
        principal_pane.add(pane_center,BorderLayout.CENTER);
        principal_pane.add(panesouth,BorderLayout.SOUTH);
        
    }

    public void actualizarDatosFactura() {
       double sumatotal=0;
       double descuento=0;
        for (int i = 0; i < interfaz.tablaFactura.getRowCount(); i++) {
              sumatotal+=(double)interfaz.tablaFactura.getValueAt(i, 6);
            descuento+=(double)interfaz.tablaFactura.getValueAt(i, 5);
        }
        
        double igv=sumatotal*0.18;
        double subtotal= sumatotal-igv;
        interfaz.precioSub.setText(String.format("%.2f",subtotal));
        interfaz.precioIGV.setText(String.format("%.2f",igv));
        interfaz.precioTotal.setText(String.format("%.2f",sumatotal));
         interfaz.precioDesc.setText(String.format("%.2f",descuento));
    }
   
}
