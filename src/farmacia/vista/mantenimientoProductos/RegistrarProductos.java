/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista.mantenimientoProductos;

import farmacia.calculos.configuracionImagenes;
import farmacia.diseño.estrategias.EstrategiaPanelRegistrar;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextField;

/**
 *
 * @author fecyp
 */
public class RegistrarProductos extends EstrategiaPanelRegistrar implements ActionListener, KeyListener {

    JLabel nombre, descripcion, dosispro, precioventa, igv, preciofinal, stock, idproducto;
    public JTextField txtnombre, txtdescripcion, txtdosis, txtprecioventa, txtidproducto, txtigv, txtiddescuento, txtpreciofinal, txtstock;
    boolean controlpunto = false;
    boolean controlpuntoigv = false;

    public RegistrarProductos(String titulo) {
        super(titulo);

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

    public boolean ExistenVacios() {
        if (txtnombre.getText().isEmpty()
                || txtdescripcion.getText().isEmpty()
                || txtdosis.getText().isEmpty()
                || txtprecioventa.getText().isEmpty()
                || txtidproducto.getText().isEmpty()
                || txtigv.getText().isEmpty()
                || txtiddescuento.getText().isEmpty()
                || txtpreciofinal.getText().isEmpty()
                || txtstock.getText().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (!ExistenVacios()) {
            frmProducto.jbGuardar.doClick();
        }
        if (source == txtnombre) {
            txtnombre.transferFocus();

        } else if (source == txtdescripcion) {
            txtdescripcion.transferFocus();

        } else if (source == txtdosis) {
            txtdosis.transferFocus();
        } else if (source == txtprecioventa) {
            txtstock.requestFocus();
        } else if (source == txtstock) {
            frmProducto.jbGuardar.doClick();
        } else if (source == txtigv) {
            txtigv.transferFocus();
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
        } else if (source == txtprecioventa) {
            if ((ke.getKeyChar() < 48 || ke.getKeyChar() > 57) && ((ke.getKeyChar() != 46) || controlpunto)) {

                ke.consume();

            }
        } else if (source == txtstock) {
            if ((ke.getKeyChar() < 48 || ke.getKeyChar() > 57)) {

                ke.consume();

            }
        }
        if (source == txtigv) {
            if ((ke.getKeyChar() < 48 || ke.getKeyChar() > 57) && ((ke.getKeyChar() != 46) || controlpuntoigv)) {

                ke.consume();

            }
        }

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        Object source = ke.getSource();
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            if (source == txtstock) {
                txtprecioventa.requestFocus();
                return;
            }
            if (source == txtnombre) {
                txtstock.requestFocus();
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
                txtstock.requestFocus();
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
        Object source = e.getSource();
        if (e.getKeyCode() == KeyEvent.VK_S && teclaunida) {
            frmProducto.jbGuardar.doClick();
            teclaunida = false;
        }
        if (source == txtprecioventa || source == txtigv) {
            int validacion = txtigv.getText().indexOf(".");
            if (validacion == -1) {
                controlpuntoigv = false;//deja poner el punto ya que dice que no encuentra punto
            } else {
                controlpuntoigv = true;//no lo deja poner el punto ya que dice que ya se encuentra un punto

            }
            validacion = txtprecioventa.getText().indexOf(".");
            if (validacion == -1) {
                controlpunto = false;//deja poner el punto ya que dice que no encuentra punto
            } else {
                controlpunto = true;//no lo deja poner el punto ya que dice que ya se encuentra un punto

            }
            //para que el igv no este vacio
            if (txtigv.getText().isEmpty()) {
                return;
            } else if (txtigv.getText().charAt(txtigv.getText().length() - 1) == '.') //para que el igv no quede en un punto
            {
                return;
            }

            if (txtprecioventa.getText().isEmpty()) {
                txtpreciofinal.setText("");
                return;
            } else if (txtprecioventa.getText().charAt(txtprecioventa.getText().length() - 1) == '.') {
                txtpreciofinal.setText("");
                return;
            }
            //validamos que solo halla un punto

            //
            if (txtprecioventa.getText().charAt(txtprecioventa.getText().length() - 1) == '.') {
                if (txtprecioventa.getText().length() == 1) {
                    return;
                }
            }

            double igv = (Double.parseDouble(txtigv.getText()) / 100) + 1;

            double preciototal = Double.parseDouble(txtprecioventa.getText()) * igv;
            BigDecimal bd = new BigDecimal(preciototal);
            bd = bd.setScale(2, RoundingMode.HALF_UP);

            txtpreciofinal.setText(bd.doubleValue() + "");
        }

    }

    public void iniciar_componentes(String titulo) {

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
        igv = new JLabel("IGV %:");
        txtigv = new JTextField(10);
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
        tercero.add(paneigv, BorderLayout.EAST);
        paneigv.setBackground(c);
        tercero.setBackground(c);

        JPanel cuarto = new JPanel(new BorderLayout());
        cuarto.add(paneprecio, BorderLayout.WEST);
        paneprecio.setBackground(c);
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
                BorderFactory.createTitledBorder("Datos del " + titulo)));
        pane.setBackground(c);
//        setLayout(new GridLayout(1,1));
        add(pane);

    }

}
