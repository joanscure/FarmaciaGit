/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista;

import com.toedter.calendar.JDateChooser;
import farmacia.calculos.configuracionImagenes;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.*;

/**
 *
 * @author fecyp
 */
public class frmventas extends JFrame implements ActionListener,KeyListener 
{
    //paneles
    JPanel paneprincipal,paneventa,panebotones;
    //datos venta
    JLabel jlcliente,jbdtipocomprobante;
    JTextField txtidcliente,txtnombrecliente;
    JButton bnagregarCliente;
    JComboBox bcxtipocomprobante;
    JDateChooser fechaventa;
    
    //pane botones
    JButton bnguardar,bnnuevo,bnrecibo,bncancelar,bnsalir;
    //datos producto
    JLabel jlidproducto,jlstock,jlprecio,jlnombre;
    JTextField txtnombreProducto,codigo;
    JButton bnagregproducto;
    
    //agrega
    JLabel jlcantidad,total;
    JTextField txtcantidad,txttotal;
    JButton bnagregar,bnquitar;
    
    
    //productos
  
//configuraciones
    configuracionImagenes configImage=new configuracionImagenes();
    Font fontbutton=new Font("Geneva", 1, 14);
    Font fonttexto= new Font("Geneva", 1, 13);
    Color colorButton=Color.WHITE;
    Color paneClaro= new Color(255, 255, 153);
    Color paneOscuro= new Color(255, 204, 102);
    
    public frmventas() {
        iniciar_componentes();
    }
   public void  iniciar_componentes()
      {
          paneprincipal=new JPanel(new BorderLayout());
         crear_paneventa();
         paneventa.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
          crear_panebotones();
          panebotones.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
         paneprincipal.add(paneventa,BorderLayout.WEST);
          paneprincipal.add(panebotones,BorderLayout.EAST);
         
           add(paneprincipal);
          //config ventana
          setDefaultCloseOperation(DISPOSE_ON_CLOSE);
          setVisible(true);
          pack();
          
      }
   
   public void crear_paneventa()
   {
       paneventa=new JPanel(new BorderLayout());
       JPanel panecabecera=new JPanel(new BorderLayout());
       JPanel  panedatosventa=new JPanel(new BorderLayout());
       panedatosventa.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(1, 1, 1, 1),
                BorderFactory.createTitledBorder("Datos de la venta")));
       
       JPanel paneidcliente=new JPanel(new BorderLayout());
       jlcliente= new JLabel("Cliente:");
       txtidcliente=new JTextField(5);
       txtidcliente.setEnabled(false);
       paneidcliente.add(jlcliente,BorderLayout.WEST);
       paneidcliente.add(jlcliente,BorderLayout.EAST);

      JPanel panenombrecliente=new JPanel(new BorderLayout());
      txtnombrecliente=new JTextField(10);
      panenombrecliente.add(paneidcliente,BorderLayout.NORTH);
      panenombrecliente.add(txtnombrecliente,BorderLayout.SOUTH);
      
      JPanel cliente=new JPanel(new BorderLayout());
      bnagregarCliente=new JButton("",new ImageIcon(getClass().getResource("/Files/buscar.png")));
      cliente.add(panenombrecliente,BorderLayout.WEST);
      cliente.add(bnagregarCliente,BorderLayout.EAST);
      panedatosventa.add(cliente,BorderLayout.WEST);
      panecabecera.add(panedatosventa,BorderLayout.NORTH);
      paneventa.add(panecabecera,BorderLayout.WEST);
      
   }
   public void crear_panebotones()
   {
        panebotones=new JPanel(new GridLayout(1,5));
       bnguardar=new JButton("Realizar Venta",configImage.obtenerIcono("generarventas.png", 42));
       bnnuevo=new JButton("Nueva Venta",configImage.obtenerIcono("nuevo.png", 42));
       bnrecibo=new JButton("Ver Recibo",configImage.obtenerIcono("verrecibo.png", 42));
       bncancelar=new JButton("Cancelar Venta",configImage.obtenerIcono("cancelarventa.png", 42));
       bnsalir=new JButton("Salir",configImage.obtenerIcono("salirventa.png", 42));
       panebotones.add(bnguardar);
       panebotones.add(bnnuevo);
       panebotones.add(bnrecibo);
       panebotones.add(bncancelar);
       panebotones.add(bnsalir);
   }
   public void changeColor()
   {
        bnguardar.setBackground(colorButton);
       bnnuevo.setBackground(colorButton);
       bnrecibo.setBackground(colorButton);
       bncancelar.setBackground(colorButton);
       bnsalir.setBackground(colorButton);
       bnagregarCliente.setBackground(colorButton);
       
   }
   public void changeFont()
   {
        bnguardar.setFont(fontbutton);
       bnnuevo.setFont(fontbutton);
       bnrecibo.setFont(fontbutton);
       bncancelar.setFont(fontbutton);
       bnsalir.setFont(fontbutton);
       
       jlcliente.setFont(fonttexto);
       txtidcliente.setFont(fonttexto);
       txtnombrecliente.setFont(fonttexto);
   }
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    public static void main(String[] args) {
        new frmventas().setVisible(true);
        
    }
}
