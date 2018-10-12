/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista.mantenimientoProductos;

import farmacia.diseño.estrategias.EstrategiaPanelRegistrar;
import java.awt.BorderLayout;
import java.awt.Color;

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
public class RegistrarProductos extends EstrategiaPanelRegistrar  {

    JLabel nombre, descripcion, dosispro, precioventa, igv, preciofinal, stock, idproducto;
    public JTextField txtnombre, txtdescripcion, txtdosis, txtprecioventa, txtidproducto, txtigv, txtiddescuento, txtpreciofinal, txtstock;
    boolean controlpunto = false;
    boolean controlpuntoigv = false;

    public RegistrarProductos(String titulo) {
        super(titulo);

       

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
