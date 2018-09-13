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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
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
public class frmventas extends JFrame implements ActionListener, KeyListener {

    //paneles
    JPanel paneprincipal, paneventa, panebotones;
    //datos venta
    JLabel jlcliente, jbdtipocomprobante, jlfecha;
    JTextField txtidcliente, txtnombrecliente;
    JButton bnagregarCliente;
    JComboBox cbxtipocomprobante;
    JDateChooser fechaventa;

    //pane botones
    JButton bnguardar, bnnuevo, bnrecibo, bncancelar, bnsalir;
    //datos producto
    JLabel jlidproducto, jlstock, jlprecio, jlnombreproducto;
    JTextField txtnombreProducto, txtcodigo, txtstock, txtprecio;
    JButton bnagregproducto;

    //agrega
    JLabel jlcantidad, jltotal;
    JTextField txtcantidad, txttotal;
    JButton bnagregar, bnquitar;

    //productos
//configuraciones
    configuracionImagenes configImage = new configuracionImagenes();
    Font fontbutton = new Font("Geneva", 1, 14);
    Font fonttexto = new Font("Geneva", 1, 13);
    Color colorButton = Color.WHITE;
    Color paneClaro = new Color(255, 255, 153);
    Color paneOscuro = new Color(255, 204, 102);

    public frmventas() {
        iniciar_componentes();
        changeFont();
        changeColor();
        personalizarboton();
    }

    public void iniciar_componentes() {
        paneprincipal = new JPanel(new BorderLayout());
        crear_paneventa();
        paneventa.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(1, 1, 1, 1),
                BorderFactory.createTitledBorder("")));
        crear_panebotones();
        panebotones.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(1, 1, 1, 1),
                BorderFactory.createTitledBorder("")));
        paneprincipal.add(paneventa, BorderLayout.WEST);
        paneprincipal.add(panebotones, BorderLayout.EAST);
        paneprincipal.setBackground(colorButton);
        add(paneprincipal);
        //config ventana
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        pack();

    }

    public void crear_paneventa() {
        paneventa = new JPanel(new BorderLayout());
        paneventa.setBackground(paneClaro);

        JPanel panecabecera = new JPanel(new BorderLayout());
        panecabecera.setBackground(paneClaro);

        JPanel paneizquierda = new JPanel(new BorderLayout());
        paneizquierda.setBackground(paneClaro);

        JPanel panedatos = new JPanel(new BorderLayout());
        panedatos.setBackground(paneClaro);

        JPanel panedatosventa = new JPanel();
        panedatosventa.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(1, 1, 1, 1),
                BorderFactory.createTitledBorder("Datos de la venta")));
        panedatosventa.setBackground(paneClaro);
        panedatosventa.add(crearDatosVenta());

        //datos de producto
        JPanel panedatosproducto = new JPanel(new BorderLayout());
        panedatosproducto.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(1, 1, 1, 1),
                BorderFactory.createTitledBorder("Datos del Producto")));
        panedatosproducto.setBackground(paneClaro);

        panedatosproducto.add(crearDatosProducto(), BorderLayout.WEST);
        //

        panedatos.add(panedatosventa, BorderLayout.NORTH);
        panedatos.add(panedatosproducto, BorderLayout.SOUTH);

        JPanel paneExtra = new JPanel(new BorderLayout());
        paneExtra.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createTitledBorder("")));
        paneExtra.setBackground(paneOscuro);
        paneExtra.add(crear_paneinformacionproducto());

        paneizquierda.add(panedatos, BorderLayout.NORTH);
        paneizquierda.add(paneExtra, BorderLayout.SOUTH);

        panecabecera.add(paneizquierda, BorderLayout.WEST);
        paneventa.add(panecabecera, BorderLayout.NORTH);

    }

    public JPanel crearDatosVenta() {
        JPanel panedatosventa = new JPanel(new BorderLayout());
        panedatosventa.setBackground(paneClaro);
        JPanel paneidcliente = new JPanel(new BorderLayout());
        paneidcliente.setBackground(paneClaro);
        jlcliente = new JLabel("Cliente:");
        txtidcliente = new JTextField(5);
        txtidcliente.setVisible(false);
        paneidcliente.add(jlcliente, BorderLayout.WEST);
        paneidcliente.add(txtidcliente, BorderLayout.EAST);

        JPanel panenombrecliente = new JPanel(new BorderLayout());
        panenombrecliente.setBackground(paneClaro);
        txtnombrecliente = new JTextField(10);
        bnagregarCliente = new JButton("", new ImageIcon(getClass().getResource("/Files/buscar.png")));
        panenombrecliente.add(bnagregarCliente, BorderLayout.EAST);
        panenombrecliente.add(txtnombrecliente, BorderLayout.WEST);

        JPanel cliente = new JPanel(new BorderLayout());
        cliente.setBackground(paneClaro);
        cliente.add(panenombrecliente, BorderLayout.SOUTH);
        cliente.add(paneidcliente, BorderLayout.NORTH);
        ///
        JPanel datosComprobante = new JPanel(new BorderLayout());

        JPanel tipocomprobante = new JPanel(new BorderLayout());
        jbdtipocomprobante = new JLabel("Comprobante: ");
        String[] itemcomprobante = {"Boleta", "Factura"};
        cbxtipocomprobante = new JComboBox(itemcomprobante);
        cbxtipocomprobante.setPreferredSize(new Dimension(150, 30));
        tipocomprobante.add(jbdtipocomprobante, BorderLayout.NORTH);
        tipocomprobante.add(cbxtipocomprobante, BorderLayout.SOUTH);
        tipocomprobante.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        tipocomprobante.setBackground(paneClaro);

        JPanel fechacomprobantes = new JPanel(new BorderLayout());
        jlfecha = new JLabel("Fecha :");
        fechaventa = new JDateChooser();
        fechaventa.setPreferredSize(new Dimension(150, 30));
        fechacomprobantes.add(jlfecha, BorderLayout.NORTH);
        fechacomprobantes.add(fechaventa, BorderLayout.SOUTH);
        fechacomprobantes.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        fechacomprobantes.setBackground(paneClaro);

        datosComprobante.add(tipocomprobante, BorderLayout.WEST);
        datosComprobante.add(fechacomprobantes, BorderLayout.EAST);

        panedatosventa.add(cliente, BorderLayout.WEST);
        panedatosventa.add(datosComprobante, BorderLayout.EAST);
        return panedatosventa;
    }

    public JPanel crearDatosProducto() {
        JPanel panedatosproducto = new JPanel(new BorderLayout());
        panedatosproducto.setBackground(paneClaro);

        JPanel westpaneproducto = new JPanel(new BorderLayout());
        westpaneproducto.setBackground(paneClaro);
        JPanel panelidproducto = new JPanel(new BorderLayout());
        jlidproducto = new JLabel("Codigo de Producto:");
        JPanel panetextoboton = new JPanel(new BorderLayout());
        bnagregproducto = new JButton("", new ImageIcon(getClass().getResource("/Files/buscar.png")));
        txtcodigo = new JTextField(10);
        panetextoboton.add(txtcodigo, BorderLayout.WEST);
        panetextoboton.add(bnagregproducto, BorderLayout.EAST);
        panetextoboton.setBackground(paneClaro);
        panelidproducto.setBackground(paneClaro);
        panelidproducto.add(jlidproducto, BorderLayout.NORTH);
        panelidproducto.add(panetextoboton, BorderLayout.SOUTH);

        JPanel nombrepane = new JPanel(new BorderLayout());
        txtnombreProducto = new JTextField(10);
        jlnombreproducto = new JLabel("Nombre ");
        nombrepane.add(txtnombreProducto, BorderLayout.SOUTH);
        nombrepane.add(jlnombreproducto, BorderLayout.NORTH);
        nombrepane.setBackground(paneClaro);
        westpaneproducto.add(panelidproducto, BorderLayout.NORTH);
        westpaneproducto.add(nombrepane, BorderLayout.SOUTH);

        JPanel eastpaneproducto = new JPanel(new FlowLayout());
        eastpaneproducto.setBackground(paneClaro);
        eastpaneproducto.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JPanel todoeast = new JPanel(new BorderLayout());
        todoeast.setBackground(paneClaro);

        JPanel panestock = new JPanel(new BorderLayout());
        jlstock = new JLabel("STOCK: ");
        txtstock = new JTextField(10);
        txtstock.setEnabled(false);
        panestock.add(jlstock, BorderLayout.NORTH);
        panestock.add(txtstock, BorderLayout.SOUTH);
        panestock.setBackground(paneClaro);
        JPanel panecantidad = new JPanel(new BorderLayout());
        jlprecio = new JLabel("PRECIO: ");
        txtprecio = new JTextField(10);
        txtprecio.setEnabled(false);
        panecantidad.add(jlprecio, BorderLayout.NORTH);
        panecantidad.add(txtprecio, BorderLayout.SOUTH);
        panecantidad.setBackground(paneClaro);

        todoeast.add(panestock, BorderLayout.WEST);
        todoeast.add(panecantidad, BorderLayout.EAST);
        eastpaneproducto.add(todoeast);

        panedatosproducto.add(westpaneproducto, BorderLayout.WEST);
        panedatosproducto.add(eastpaneproducto, BorderLayout.EAST);
        return panedatosproducto;
    }

    public void crear_panebotones() {
        panebotones = new JPanel(new GridLayout(5, 1));
        bnguardar = new JButton("Realizar Venta", configImage.obtenerIcono("generarventas.png", 42));
        bnnuevo = new JButton("Nueva Venta", configImage.obtenerIcono("nuevo.png", 42));
        bnrecibo = new JButton("Ver Recibo", configImage.obtenerIcono("verrecibo.png", 42));
        bncancelar = new JButton("Cancelar Venta", configImage.obtenerIcono("cancelarventa.png", 42));
        bnsalir = new JButton("Salir", configImage.obtenerIcono("salirventa.png", 42));

        panebotones.add(bnnuevo);
        panebotones.add(bnguardar);
        panebotones.add(bnrecibo);
        panebotones.add(bncancelar);
        panebotones.add(bnsalir);
        panebotones.setBackground(colorButton);
    }

    public JPanel crear_paneinformacionproducto() {
        JPanel paneExtra = new JPanel(new FlowLayout());
        paneExtra.setBackground(paneOscuro);

        JPanel panecantidad = new JPanel(new BorderLayout());
        jlcantidad=new JLabel("Cantidad: " );
        txtcantidad=new JTextField(5);
        panecantidad.add(jlcantidad,BorderLayout.WEST);
        panecantidad.add(txtcantidad,BorderLayout.EAST);
        panecantidad.setBackground(paneOscuro);
        
        JPanel paneltotal = new JPanel(new BorderLayout());
        jltotal=new JLabel("Total: ");
        txttotal=new JTextField(5);
        paneltotal.add(jltotal,BorderLayout.WEST);
        paneltotal.add(txttotal,BorderLayout.EAST);
        paneltotal.setBackground(paneOscuro);
        
        JPanel panebotonesconfig = new JPanel(new BorderLayout());
        bnagregar=new JButton("",configImage.obtenerIcono("agregar.png",30));
        bnquitar=new JButton("",configImage.obtenerIcono("bnsacar.png",30));
        panebotonesconfig.setBackground(paneOscuro);
        panebotonesconfig.add(bnagregar,BorderLayout.WEST);
        panebotonesconfig.add(bnquitar,BorderLayout.EAST);
        paneExtra.add(panecantidad);
         paneExtra.add(paneltotal);
          paneExtra.add(panebotonesconfig);
        return paneExtra;
    }

    public void changeColor() {
        bnguardar.setBackground(colorButton);
        bnnuevo.setBackground(colorButton);
        bnrecibo.setBackground(colorButton);
        bncancelar.setBackground(colorButton);
        bnsalir.setBackground(colorButton);
        bnagregarCliente.setBackground(colorButton);

    }

    public void changeFont() {
        bnguardar.setFont(fontbutton);
        bnnuevo.setFont(fontbutton);
        bnrecibo.setFont(fontbutton);
        bncancelar.setFont(fontbutton);
        bnsalir.setFont(fontbutton);

        jlcliente.setFont(fonttexto);
        txtidcliente.setFont(fonttexto);
        txtnombrecliente.setFont(fonttexto);
        jlfecha.setFont(fonttexto);
        fechaventa.setFont(fonttexto);
        jbdtipocomprobante.setFont(fonttexto);
        cbxtipocomprobante.setFont(fonttexto);
    }

    public void personalizarboton() {
        bnnuevo.setHorizontalTextPosition(SwingConstants.CENTER);
        bnnuevo.setVerticalTextPosition(SwingConstants.BOTTOM);

        bnguardar.setHorizontalTextPosition(SwingConstants.CENTER);
        bnguardar.setVerticalTextPosition(SwingConstants.BOTTOM);

        bnrecibo.setHorizontalTextPosition(SwingConstants.CENTER);
        bnrecibo.setVerticalTextPosition(SwingConstants.BOTTOM);

        bncancelar.setHorizontalTextPosition(SwingConstants.CENTER);
        bncancelar.setVerticalTextPosition(SwingConstants.BOTTOM);

        bnsalir.setHorizontalTextPosition(SwingConstants.CENTER);
        bnsalir.setVerticalTextPosition(SwingConstants.BOTTOM);
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
        try {
            javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        new frmventas().setVisible(true);

    }
}
