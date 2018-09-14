/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista.mantenimientoProductos;

import farmacia.vista.mantenimientoCliente.*;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import farmacia.calculos.configuracionImagenes;
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
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author fecyp
 */
public class Registrar extends JPanel implements ActionListener, KeyListener {

    frmProducto regis;

    JPanel pane;
    JLabel nombre, descripcion, dosispro, precioventa, igv, preciofinal, stock, idproducto;
    public JTextField txtnombre, txtdescripcion, txtdosis, txtprecioventa, txtidproducto, txtigv, txtiddescuento, txtpreciofinal, txtstock;
    configuracionImagenes iamgeConfig = new configuracionImagenes();
    Color c = new java.awt.Color(255, 255, 153);
    Font fontboton = new Font("Geneva", 1, 13);
    boolean teclaunida = false;

    public Registrar(frmProducto regis) {
        this.regis = regis;
        iniciar_componentes();
        setBackground(c);
        personalizartipoletra();

        txtnombre.addActionListener(this);
        txtdescripcion.addActionListener(this);
        txtdosis.addActionListener(this);
        txtprecioventa.addActionListener(this);
        txtidproducto.addActionListener(this);
        txtigv.addActionListener(this);
        txtiddescuento.addActionListener(this);
        txtpreciofinal.addActionListener(this);
        txtstock.addActionListener(this);

        txtnombre.addKeyListener(this);
        txtdescripcion.addKeyListener(this);
        txtdosis.addKeyListener(this);
        txtprecioventa.addKeyListener(this);
        txtidproducto.addKeyListener(this);
        txtigv.addKeyListener(this);
        txtiddescuento.addKeyListener(this);
        txtpreciofinal.addKeyListener(this);
        txtstock.addKeyListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == txtnombre) {
            txtnombre.transferFocus();

        } else if (source == txtdescripcion) {
            txtdescripcion.transferFocus();

        } else if (source == txtdosis) {
            txtdosis.transferFocus();
        } else if (source == txtprecioventa) {
            if(txtprecioventa.getText().isEmpty())
                return;
            
            double preciototal = Integer.parseInt(txtprecioventa.getText()) * 1.18;
            BigDecimal bd = new BigDecimal(preciototal);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            
            double igv = preciototal - Integer.parseInt(txtprecioventa.getText());
            BigDecimal bd2 = new BigDecimal(igv);
            bd2 = bd2.setScale(2, RoundingMode.HALF_UP);
            txtigv.setText(bd2.doubleValue() + "");
            txtpreciofinal.setText(bd.doubleValue() + "");
            txtstock.requestFocus();
        } else if (source == txtstock) {
            frmClientes.jbGuardar.doClick();
        }
    }

    public void personalizartipoletra() {
        nombre.setFont(fontboton);
        descripcion.setFont(fontboton);
        dosispro.setFont(fontboton);
        preciofinal.setFont(fontboton);
        precioventa.setFont(fontboton);
        igv.setFont(fontboton);
        stock.setFont(fontboton);
        idproducto.setFont(fontboton);

        txtnombre.setFont(fontboton);
        txtdescripcion.setFont(fontboton);
        txtdosis.setFont(fontboton);
        txtigv.setFont(fontboton);
        txtpreciofinal.setFont(fontboton);
        txtprecioventa.setFont(fontboton);
        txtstock.setFont(fontboton);
        txtidproducto.setFont(fontboton);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        Object source = ke.getSource();

        ke.getComponent().setBackground(Color.white);

        if (source == txtnombre || source == txtdosis || source == txtnombre || source == txtdescripcion) {
            char c = ke.getKeyChar();
            if (Character.isLowerCase(c)) {
                String cad = ("" + c).toUpperCase();
                c = cad.charAt(0);
                ke.setKeyChar(c);
            }
            if (((ke.getKeyChar() < 97 || ke.getKeyChar() > 122)) && (ke.getKeyChar() < 65 || ke.getKeyChar() > 90) && source != txtdosis && source != txtdescripcion) {
                ke.consume();
            }
        } else if (source == txtprecioventa|| source==txtstock) {
            if (ke.getKeyChar() < 48 || ke.getKeyChar() > 57 && ke.getKeyChar()!=46) {
                ke.consume();
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        Object source = ke.getSource();
         if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
             if (source == txtdescripcion) {
                txtprecioventa.requestFocus();
                return;
            }
            ke.getComponent().transferFocusBackward();
        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (source == txtstock) {
                txtnombre.requestFocus();
                return;
            }
             if (source == txtprecioventa) {
                txtdescripcion.requestFocus();
                return;
            }
            
            ke.getComponent().transferFocus();
        }
        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
            frmProducto.jbCancelar.doClick();

        }
        if (ke.getExtendedKeyCode() == KeyEvent.VK_CONTROL) {
            teclaunida = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_S && teclaunida) {
            frmProducto.jbGuardar.doClick();
            teclaunida = false;
        }
    }

    private void iniciar_componentes() {

        GridLayout g = new GridLayout(6, 1);
        g.setHgap(10);
        g.setVgap(10);
        pane = new JPanel(g);
        JPanel idpane = new JPanel(new GridLayout(1, 2));
        txtiddescuento = new JTextField(10);
        idpane.add(txtiddescuento);
        txtiddescuento.setVisible(false);
        //primero
        JPanel panecliente = new JPanel(new GridLayout(1, 2));
        idproducto = new JLabel("ID Producto");
        txtidproducto = new JTextField(10);
        txtidproducto.setEnabled(false);
        panecliente.add(idproducto, BorderLayout.WEST);
        panecliente.add(txtidproducto, BorderLayout.EAST);
        panecliente.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel panenombre = new JPanel(new GridLayout(1, 2));
        nombre = new JLabel("Nombre: ");
        txtnombre = new JTextField(10);
        panenombre.add(nombre, BorderLayout.WEST);
        panenombre.add(txtnombre, BorderLayout.EAST);
        panenombre.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        //segundo
        JPanel panedosis = new JPanel(new GridLayout(1, 2));
        dosispro = new JLabel("Dosis :");
        txtdosis = new JTextField(10);
        panedosis.add(dosispro, BorderLayout.WEST);
        panedosis.add(txtdosis, BorderLayout.EAST);
        panedosis.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel paneprecio = new JPanel(new GridLayout(1, 2));
        precioventa = new JLabel("Precio Venta:");
        txtprecioventa = new JTextField(10);
        paneprecio.add(precioventa, BorderLayout.WEST);
        paneprecio.add(txtprecioventa, BorderLayout.EAST);
        paneprecio.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        //tercero
        JPanel paneigv = new JPanel(new GridLayout(1, 2));
        igv = new JLabel("IGV :");
        txtigv = new JTextField(10);
        txtigv.setEditable(false);
        paneigv.add(igv, BorderLayout.WEST);
        paneigv.add(txtigv, BorderLayout.EAST);
        paneigv.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel panetotal = new JPanel(new GridLayout(1, 2));
        preciofinal = new JLabel("Precio final :");
        txtpreciofinal = new JTextField(10);
        txtpreciofinal.setEditable(false);
        panetotal.add(preciofinal, BorderLayout.WEST);
        panetotal.add(txtpreciofinal, BorderLayout.EAST);
        panetotal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        //cuarto
        JPanel panelscroll = new JPanel(new BorderLayout());

        descripcion = new JLabel("Descripcion: ");
        txtdescripcion = new JTextField(40);
        panelscroll.add(descripcion, BorderLayout.WEST);
        panelscroll.add(txtdescripcion, BorderLayout.EAST);
        panelscroll.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        //quinto 
        JPanel panestock = new JPanel(new GridLayout(1, 2));
        stock = new JLabel("Stock:");
        txtstock = new JTextField(10);
        panestock.add(stock);
        panestock.add(txtstock);
        panestock.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        //agrupamos entidades
        JPanel primero = new JPanel(new BorderLayout());
        primero.add(panecliente, BorderLayout.WEST);
        panecliente.setBackground(c);
        primero.add(panenombre, BorderLayout.EAST);
        panenombre.setBackground(c);
        primero.setBackground(c);

        JPanel segundo = new JPanel(new BorderLayout());
        segundo.add(panelscroll, BorderLayout.WEST);
        panelscroll.setBackground(c);
        segundo.setBackground(c);

        JPanel tercero = new JPanel(new BorderLayout());
        tercero.add(panedosis, BorderLayout.WEST);
        panedosis.setBackground(c);
        tercero.add(paneprecio, BorderLayout.EAST);
        paneprecio.setBackground(c);
        tercero.setBackground(c);

        JPanel cuarto = new JPanel(new BorderLayout());
        cuarto.add(paneigv, BorderLayout.WEST);
        paneigv.setBackground(c);
        cuarto.add(panetotal, BorderLayout.EAST);
        panetotal.setBackground(c);
        cuarto.setBackground(c);

        JPanel quinto = new JPanel(new BorderLayout());
        quinto.add(panestock, BorderLayout.WEST);
        panestock.setBackground(c);
        quinto.setBackground(c);

        pane.add(idpane);
        idpane.setBackground(c);
        pane.add(primero);
        pane.add(segundo);
        pane.add(tercero);
        pane.add(cuarto);
        pane.add(quinto);
        pane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createTitledBorder("Datos del Cliente")));
        pane.setBackground(c);
//        setLayout(new GridLayout(1,1));
        add(pane);

    }

}
