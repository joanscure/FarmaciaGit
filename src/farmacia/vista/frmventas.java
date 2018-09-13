/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista;

import com.toedter.calendar.JDateChooser;
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
    JPanel paneprincipal,panewest,paneeast;
    //datos venta
    JLabel jlcliente,jbdtipocomprobante;
    JTextField txtidcliente,txtnombrecliente;
    JButton bnagregarCliente;
    JComboBox bcxtipocomprobante;
    JDateChooser fechaventa;
    
    
    //datos producto
    JLabel jlidproducto,jlstock,jlprecio,jlnombre;
    JTextField txtnombre,codigo;
    JButton bnagregproducto;
    
    //agrega
    JLabel jlcantidad,total;
    JTextField txtcantidad,txttotal;
    JButton bnagregar,bnquitar;
    
    
    //productos
  

    public frmventas() {
        iniciar_componentes();
    }
   public void  iniciar_componentes()
      {
          
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
