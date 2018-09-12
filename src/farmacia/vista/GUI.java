/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista;

import farmacia.calculos.NumComprobante;
import farmacia.vista.mantenimientoCliente.frmClientes;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import conectionBD.ConnectionBoleta;
import conectionBD.ConnectionFactura;
import java.awt.Event;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author fecyp
 */
public class GUI extends JFrame implements ActionListener,KeyListener{
   
    JRadioButton conruc,sinruc;
    
    JLabel etiquetaRuc;
   public JTextField txtRuc;
    JButton registrarRuc;
    
    JLabel etiquetaDni;
   public JTextField txtDni;
    JButton registrarDni;
    
    JLabel etiquetaCliente;
    public JTextField txtCliente;
    
    JLabel etiquetaFactura;
    JTextField txtNumFactura;
    JLabel rucEmpresa;
    JTextField txtRucEmpresa;
    JLabel codVendedor;
    JTextField txtCodVendedor;
    JLabel nombreVendedor;
    JTextField txtNombreVendedor;
    JLabel dniVendedor;
    JTextField txtDniVendedor;
    JLabel etiquetaFecha;
    JTextField txtFecha;
    
    JMenuBar barra = new JMenuBar();
    JMenu jMusuario = new JMenu("Usuario");
    JMenu jMadministrador = new JMenu("Administrador");
    JMenuItem cambiarUser,salir,mostrarUser,mostrarClientes,generarReport,nuevoUsuario,nuevoSuperUs,nuevaFactura;
    JMenu crearNuevo;
     
    Productos producto_pane;
    JTable tablaFactura=new JTable(20,20);
    JLabel contador;
    DefaultTableModel modelo;
    JLabel codProducto;
    JButton buscarProducto;
    JButton eliminarProducto;
    JButton GrabarComprobante;
    
    ButtonGroup agrupacion=new ButtonGroup();
    JPanel principal;
    JPanel pane_east,pane_cabecera,pane_west,pane_south;
    
    JLabel subtotal,precioSub, igv,precioIGV, total,precioTotal,descuento,precioDesc;
//     TextAutoCompleter autoruc,autodni;
    
    ConnectionFactura conexion1;
    ConnectionBoleta conexion2;
    String numFactura,numBoleta;
    public boolean valido=false;
    GUI()
    {
        super("Farmacia unp");
        principal=new JPanel(new BorderLayout());
        pane_cabecera=new JPanel(new BorderLayout());
        setContentPane(principal);
        
        cambiarUser= new JMenuItem("Cambiar Usuario");
        salir= new JMenuItem("salir");
        crearNuevo= new JMenu("Crear Nuevo Usuario");
        nuevoUsuario=new JMenuItem("Nuevo Empleado");
        nuevaFactura=new JMenuItem("Nueva Factura");
        nuevoSuperUs=new JMenuItem("Nuevo Administrador");
        mostrarClientes=new JMenuItem("Mostrar Listado de Clientes");
        mostrarUser= new JMenuItem("Mostrar Listado de Usuarios");
        generarReport= new JMenuItem("Generar Reporte de Facturas");
        jMusuario.add(nuevaFactura);
        jMusuario.add(cambiarUser);
        jMusuario.add(salir);
        salir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
        
        jMadministrador.add(crearNuevo);
        crearNuevo.add(nuevoSuperUs);
        crearNuevo.add(nuevoUsuario);
        jMadministrador.add(mostrarUser);
        jMadministrador.add(mostrarClientes);
        jMadministrador.add(generarReport);
        barra.add(jMusuario);
        barra.add(jMadministrador);
        
        pane_cabecera.add(crearPaneWest(),BorderLayout.WEST);
        pane_cabecera.add(crearPaneEast(),BorderLayout.EAST);
        
        pane_west=new JPanel(new BorderLayout());
        pane_west.add(pane_cabecera,BorderLayout.NORTH);
        pane_south=crearFactura();
        pane_west.add(pane_south,BorderLayout.CENTER);
        pane_west.add(crearTotal(),BorderLayout.SOUTH);
        principal.add(pane_west,BorderLayout.WEST);
        crearPaneProducto();
        this.setJMenuBar(barra);
    
        
        setDefaultCloseOperation(3);
       
        buscarProducto.addActionListener(this);
        registrarDni.addActionListener(this);
        registrarRuc.addActionListener(this);
        txtDni.addActionListener(this);
        txtRuc.addActionListener(this);
        txtDni.addKeyListener(this);
        txtRuc.addKeyListener(this);
        conruc.addActionListener(this);
        sinruc.addActionListener(this);
        eliminarProducto.addActionListener(this);
        GrabarComprobante.addActionListener(this);
        cambiarUser.addActionListener(this);
        salir.addActionListener(this);
        nuevoSuperUs.addActionListener(this);
        nuevoUsuario.addActionListener(this);
        mostrarUser.addActionListener(this);
        generarReport.addActionListener(this);
        mostrarClientes.addActionListener(this);
        txtRuc.addActionListener(this);
        nuevaFactura.addActionListener(this);
//         autoruc=new TextAutoCompleter(txtRuc);
//         autodni=new TextAutoCompleter(txtDni);
//         generarAutocompletarDni();
//         generarAutocompletarRuc();
        txtDni.setEditable(false);
        txtRuc.setEditable(false);
        pack();
         setLocationRelativeTo(null);
        setResizable(false);
        
        txtDni.requestFocus();
    }
    JPanel crearPaneWest()
    {
        JPanel main_pane=new JPanel();
        
        JPanel elruc=new JPanel(new FlowLayout());
        conruc=new JRadioButton("Factura");
        sinruc=new JRadioButton("Boleta",true);
        
        agrupacion.add(sinruc);
        agrupacion.add(conruc);
        elruc.setLayout(new GridLayout(1,2));
        elruc.add(sinruc);
        elruc.add(conruc);
        
        JPanel pane_ruc=new JPanel();
        etiquetaRuc=new JLabel("Ruc:");
        txtRuc=new JTextField(10);
        registrarRuc=new JButton(obtenerIcono("agregar.png"));
        pane_ruc.add(etiquetaRuc);
        pane_ruc.add(txtRuc);
        pane_ruc.add(registrarRuc);
        etiquetaRuc.setEnabled(false);
        txtRuc.setEnabled(false);
        registrarRuc.setEnabled(false);
        
        JPanel pane_dni=new JPanel();
         etiquetaDni=new JLabel("Dni:");
        txtDni=new JTextField(10);
        registrarDni=new JButton(obtenerIcono("agregar.png"));
        pane_dni.add(etiquetaDni);
        pane_dni.add(txtDni);
        pane_dni.add(registrarDni);
        
        JPanel pane_c=new JPanel (new BorderLayout());
        JPanel pane_cliente=new JPanel();
        etiquetaCliente=new JLabel("Nombre/Razon Social: ");
        txtCliente=new JTextField("Cliente Generico",10);
        txtCliente.setEnabled(false);
        pane_cliente.add(etiquetaCliente);
        pane_cliente.add(txtCliente);
        pane_c.add(pane_cliente,BorderLayout.WEST);
        
        main_pane.setLayout(new GridLayout(4,1));
        main_pane.add(elruc);
        main_pane.add(pane_ruc);
        main_pane.add(pane_dni);
        main_pane.add(pane_c);
        
        
        
        JPanel panelGrand = new JPanel(new BorderLayout());
        
        panelGrand.add(main_pane,BorderLayout.NORTH);
        
        JPanel menu_pane = new JPanel(new BorderLayout());
        menu_pane.add(panelGrand,BorderLayout.NORTH);
        menu_pane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20),
                BorderFactory.createTitledBorder("")));
        
        return menu_pane;
    }
     JPanel crearPaneEast()
     {
        JPanel main_pane=new JPanel();
        
        JPanel pane_factura=new JPanel(new GridLayout(1,2));
        etiquetaFactura=new JLabel("Numero de Boleta");
        
        txtNumFactura=new JTextField(10);
        txtNumFactura.setEnabled(false);
        pane_factura.add(etiquetaFactura);
        pane_factura.add(txtNumFactura);
        
        JPanel pane_ruc=new JPanel(new GridLayout(1,2));
        rucEmpresa=new JLabel("Ruc de la empresa:");
        txtRucEmpresa=new JTextField("12345678901",10);
        txtRucEmpresa.setEnabled(false);
        pane_ruc.add(rucEmpresa);
        pane_ruc.add(txtRucEmpresa);
        
        JPanel pane_codVendedor=new JPanel(new GridLayout(1,2));
        codVendedor=new JLabel("Codigo de Usuario:\t");
        txtCodVendedor=new JTextField(10);
        txtCodVendedor.setEnabled(false);
        pane_codVendedor.add(codVendedor);
        pane_codVendedor.add(txtCodVendedor);
        
        JPanel pane_nombre=new JPanel(new GridLayout(1,2));
         nombreVendedor=new JLabel("Nombre de Usuario:\t");
        txtNombreVendedor=new JTextField(10);
        txtNombreVendedor.setEnabled(false);
        pane_nombre.add(nombreVendedor);
        pane_nombre.add(txtNombreVendedor);
        
        JPanel pane_dni=new JPanel(new GridLayout(1,2));
        dniVendedor=new JLabel("Dni del Usuario:\t");
        txtDniVendedor=new JTextField(10);
        txtDniVendedor.setEnabled(false);
        pane_dni.add(dniVendedor);
        pane_dni.add(txtDniVendedor);
         
        JPanel pane_fecha=new JPanel(new GridLayout(1,2));
        etiquetaFecha=new JLabel("Fecha:\t");
        txtFecha=new JTextField(10);
        txtFecha.setEnabled(false);
        Date fecha=new Date();
        SimpleDateFormat formato=new SimpleDateFormat("dd/MM/YYYY");
        txtFecha.setText(formato.format(fecha));
        pane_fecha.add(etiquetaFecha);
        pane_fecha.add(txtFecha);
        
        main_pane.setLayout(new GridLayout(6,1));
        main_pane.add(pane_factura);
        main_pane.add(pane_ruc);
        main_pane.add(pane_codVendedor);
        main_pane.add(pane_nombre);
        main_pane.add(pane_dni);
        main_pane.add(pane_fecha);
        
        JPanel panelGrand = new JPanel(new BorderLayout());
        panelGrand.add(main_pane,BorderLayout.CENTER);
         
        JPanel menu_pane = new JPanel(new BorderLayout());
        menu_pane.add(panelGrand,BorderLayout.WEST);
         menu_pane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20),
                BorderFactory.createTitledBorder("")));
        return menu_pane;
     }
      public Icon obtenerIcono(String imagen) {
        ImageIcon img = new ImageIcon("imagenes/"+imagen);
        Icon imagenes = new ImageIcon(img.getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
      return imagenes;
    }
    
    void crearPaneProducto()
    {
        pane_east=new JPanel(new BorderLayout());
        producto_pane=new Productos(this);
        JPanel pane=new JPanel();
        pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));
        pane.add(producto_pane);
        
        producto_pane.setVisible(false);
        pane_east.add(pane);
        pane_east.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));

        principal.add(pane_east,BorderLayout.EAST);
    }
    JPanel crearFactura()
    {
        JPanel pane_principal=new JPanel(new BorderLayout());
        JPanel pane_north=new JPanel(new FlowLayout());
        JPanel pane_codPro=new JPanel();
        codProducto=new JLabel("Agregar Productos");
        pane_codPro.add(codProducto);
        contador=new JLabel("1 Productos");
        buscarProducto=new JButton(obtenerIcono("buscar.png"));
        eliminarProducto=new JButton("eliminar",obtenerIcono("eliminar.png",15));
         eliminarProducto.setEnabled(false);
        GrabarComprobante=new JButton("Grabar",obtenerIcono("guardar.png", 15));
         GrabarComprobante.setEnabled(false);
        pane_north.add(pane_codPro);
        pane_north.add(buscarProducto);
        pane_north.add(eliminarProducto);
        pane_north.add(GrabarComprobante);
        
        
        JPanel center_pane=new JPanel(new GridLayout(1,1));
        center_pane.add(factura_tabla());
        pane_principal.add(center_pane,BorderLayout.CENTER);
        pane_principal.add(pane_north,BorderLayout.NORTH);
        pane_principal.add(contador,BorderLayout.SOUTH);
        pane_principal.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createTitledBorder("")));
        return pane_principal;
    }
    
             public Icon obtenerIcono(String imagen, int index) {
        ImageIcon img = new ImageIcon("imagenes/"+imagen);
        Icon imagenes = new ImageIcon(img.getImage().getScaledInstance(index, index, Image.SCALE_DEFAULT));
      return imagenes;
    }
    JPanel crearTotal()
    {
        JPanel pane_total=new JPanel(new GridLayout(2,2));
        
        JPanel pane_subtotal=new JPanel(new BorderLayout());
        JPanel pane1=new JPanel(new BorderLayout());
        subtotal=new JLabel("SUB-TOTAL: ");
        pane1.add(subtotal,BorderLayout.WEST);
        JPanel pane2=new JPanel(new BorderLayout());
        precioSub=new JLabel("0.00");
        pane2.add(precioSub,BorderLayout.EAST);
        pane_subtotal.add(pane1,BorderLayout.NORTH);
        pane_subtotal.add(pane2,BorderLayout.SOUTH);
        pane_subtotal.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10,10,10),
                BorderFactory.createTitledBorder("")));
        precioSub.setFont(new Font("Tahoma", 1, 16));
                
        JPanel pane_igv=new JPanel(new BorderLayout());
        JPanel pane3=new JPanel(new BorderLayout());
        igv=new JLabel("IGV:");
        pane3.add(igv,BorderLayout.WEST);
        JPanel pane4=new JPanel(new BorderLayout());
        precioIGV=new JLabel("0.00");
        pane4.add(precioIGV,BorderLayout.EAST);
        pane_igv.add(pane3,BorderLayout.NORTH);
        pane_igv.add(pane4,BorderLayout.SOUTH);
        pane_igv.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10,10,10),
                BorderFactory.createTitledBorder("")));
        precioIGV.setFont(new Font("Tahoma", 1, 16));
        
        JPanel pane_Descuento=new JPanel(new BorderLayout());
        JPanel pane5=new JPanel(new BorderLayout());
        descuento=new JLabel("DESCUENTO: ");
        pane5.add(descuento,BorderLayout.WEST);
        JPanel pane6=new JPanel(new BorderLayout());
        precioDesc=new JLabel("0%");
        pane6.add(precioDesc,BorderLayout.EAST);
        pane_Descuento.add(pane5,BorderLayout.NORTH);
        pane_Descuento.add(pane6,BorderLayout.SOUTH);
        pane_Descuento.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10,10,10),
                BorderFactory.createTitledBorder("")));
        precioDesc.setFont(new Font("Tahoma", 1, 16));
        
        JPanel pane_totalve=new JPanel(new BorderLayout());
        JPanel pane7=new JPanel(new BorderLayout());
        total=new JLabel("TOTAL: ");
        pane7.add(total,BorderLayout.WEST);
        JPanel pane8=new JPanel(new BorderLayout());
        precioTotal=new JLabel("0.00");
        pane8.add(precioTotal,BorderLayout.EAST);
        pane_totalve.add(pane7,BorderLayout.NORTH);
        pane_totalve.add(pane8,BorderLayout.SOUTH);
        pane_totalve.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10,10,10),
                BorderFactory.createTitledBorder("")));
        precioTotal.setForeground(Color.red);
        precioTotal.setFont(new Font("Tahoma", 1, 16));
        
        
        pane_total.add(pane_subtotal);
        pane_total.add(pane_igv);
        pane_total.add(pane_Descuento);
        pane_total.add(pane_totalve);
        
        JPanel menu_pane = new JPanel(new BorderLayout());
        menu_pane.add(pane_total,BorderLayout.CENTER);
        menu_pane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 0, 0, 0),
                BorderFactory.createTitledBorder("")));
        return menu_pane;
        
    }
    public JScrollPane factura_tabla()
{
	
	Object[][] data=new Object[0][0];
	String[] lista={"N°","COD","DESCRIP.","CANT","PRECIO","DESC","SubTotal"};
	 modelo=new DefaultTableModel(data,lista)
			 {
		 public boolean isCellEditable(int row,int column)
		 {
			 return false;
		 }
			 };
    JScrollPane pane=new JScrollPane();
    tablaFactura.setModel(modelo);
	tablaFactura.getTableHeader().setReorderingAllowed(false);
         tablaFactura.getSelectionModel().addListSelectionListener(e->{
             
           tablaFactura.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
           eliminarProducto.setEnabled(true);
          
        });
           tablaFactura.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
           pane.setViewportView(tablaFactura);
        tablaFactura.getColumnModel().getColumn(0).setPreferredWidth(100);
        tablaFactura.getColumnModel().getColumn(1).setPreferredWidth(100);
        
        tablaFactura.getColumnModel().getColumn(3).setPreferredWidth(100);
        tablaFactura.getColumnModel().getColumn(4).setPreferredWidth(100);
        tablaFactura.getColumnModel().getColumn(5).setPreferredWidth(100);
        tablaFactura.getColumnModel().getColumn(6).setPreferredWidth(100);
        tablaFactura.getColumnModel().getColumn(2).setPreferredWidth(250);
	
    return pane;
}
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();
        if(source==buscarProducto){
            producto_pane.setVisible(!producto_pane.isVisible());
            producto_pane.txtBuscar.requestFocus();
           
            pack();
        }
        else if(source==registrarDni)
        {
           
        }
        else if(source==registrarRuc)
        {
        }
        else if(source==conruc)
        {
           txtRuc.setEnabled(true);
           txtDni.setEnabled(false);
            txtDni.setText("");
            txtCliente.setText("Cliente Generico");
           registrarDni.setEnabled(false);
           etiquetaCliente.setEnabled(false);
             etiquetaRuc.setEnabled(true);  
             registrarRuc.setEnabled(true);
             txtRuc.requestFocus();
             
             etiquetaFactura.setText("Numero de Factura");
        }
        else if(source==sinruc)
        {
             txtRuc.setText("");
            txtCliente.setText("Cliente Generico");
             txtDni.setEnabled(true);
           registrarDni.setEnabled(true);
           etiquetaCliente.setEnabled(true);
             etiquetaRuc.setEnabled(false);
             txtRuc.setEnabled(false);
             registrarRuc.setEnabled(false);
             txtDni.requestFocus();
             etiquetaFactura.setText("Numero de Boleta");
        }
        else if(source==cambiarUser)
         {
            //cambiar usuario
             this.dispose();
         }else if(source== salir)
         {
             System.exit(0);
         }
         else if(source==nuevoUsuario)
         {
            
         }
         else if(source==nuevoSuperUs)
         {
         }else if(source==generarReport)
         {
             //generar reportes
         }
//         else if(source==txtRuc)
//         { 
//           if(autoruc.itemExists(txtRuc.getText()))
//             {
//                 Inicio.rucEmpresa=txtRuc.getText();
//                 llenarDatosEmpresa(Inicio.rucEmpresa);
//                producto_pane.setVisible(true);
//                 producto_pane.txtBuscar.requestFocus();
//                  pack();
//             }
//           else 
//           {
//               if(txtRuc.getText().length()==11)
//               {
//                  if(registrarEmpresa==null)
//                    {
//                        registrarEmpresa=new frmEmpresa(this);
//                    }
//                    ventanaClientes.setcontrol(true, true);
//                    registrarEmpresa.pane2.txtruc.setText(txtRuc.getText());
//               }
//             }
//            
//         }
//         else if(source==txtDni)
//         {
//            if(autodni.itemExists(txtDni.getText()))
//             {
//                 Inicio.dniCliente=txtDni.getText();
//                 llenarDatosCliente(Inicio.dniCliente);
//                producto_pane.setVisible(true);
//                 producto_pane.txtBuscar.requestFocus();
//                  pack();
//             }
//            else 
//            {
//                if(txtDni.getText().length()==8)
//                {
//                     if(ventanaClientes==null)
//                         {
//                             ventanaClientes=new frmClientes(this);
//                             }
//                     ventanaClientes.setcontrol(true, true);
//                      ventanaClientes.pane2.txtdni.setText(txtDni.getText());
//                }
//            }
//         }
         else if(source==eliminarProducto)
         {
             //devolver los productos a la base de datos
             String idproducto=(String) tablaFactura.getValueAt(tablaFactura.getSelectedRow(), 1);
             int cstock =producto_pane.buscarCant(idproducto);
            int c=(int) modelo.getValueAt(tablaFactura.getSelectedRow(), 3);
             c=c+cstock;
             
             producto_pane.actualizarstock(c,idproducto);
             if(cstock==0)
                producto_pane.habilitarEstado(idproducto);
             producto_pane.cargarDatos();
             
             
              modelo.removeRow(tablaFactura.getSelectedRow());
              tablaFactura.clearSelection();
              producto_pane.actualizarDatosFactura();
               if(modelo.getRowCount()==0)
             {
                 GrabarComprobante.setEnabled(false);
             }
              eliminarProducto.setEnabled(false);
         }
         else if(source==GrabarComprobante)
         {
           
         }
         else if (source==nuevaFactura)
         {
             int option = JOptionPane.showConfirmDialog(null,"¿Seguro que desea generar una nueva Factura\nSe borraran los datos no guardados", 
                    "Nueva Factura", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(option == JOptionPane.YES_OPTION){
             for (int i = 0; i < modelo.getRowCount(); ) {
                 String idproducto=(String) tablaFactura.getValueAt(i, 1);
             int cstock =producto_pane.buscarCant(idproducto);
            int c=(int) modelo.getValueAt(i, 3);
             c=c+cstock;
             
             producto_pane.actualizarstock(c,idproducto);
             if(cstock==0)
                producto_pane.habilitarEstado(idproducto);
             producto_pane.cargarDatos();
                 modelo.removeRow(i);
             }
             producto_pane.actualizarDatosFactura();
             GrabarComprobante.setEnabled(false);
             eliminarProducto.setEnabled(false);
            
             sinruc.setSelected(true);
             valido=false;
             txtCliente.setText("Cliente Generico");
             txtDni.setText("");
             txtRuc.setText("");
             contador.setText("0 productos");
                    }
         }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        if(ke.getSource().equals(txtDni)) 
        {
            if(ke.getKeyChar()<48 || ke.getKeyChar()>57) ke.consume();  
            if(txtDni.getText().length()>7) ke.consume();
        }
       else if(ke.getSource().equals(txtRuc)) 
        {
            if(ke.getKeyChar()<48 || ke.getKeyChar()>57) ke.consume();  
            if(txtRuc.getText().length()>10) ke.consume();
        }
         
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
   
}
