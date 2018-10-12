/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista.mantenimientoCliente;

import farmacia.diseño.estrategias.EstrategiaPanelRegistrar;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author fecyp
 */
public class RegistrarCliente extends EstrategiaPanelRegistrar {

    JLabel nombre, apellidop, apellidom, telefono, documento, direccion, idcliente, jledad;
    public JTextField txtnombre, txtapellidop, txtapellidom, txtidpersona, txtidcliente, txttelefono, txtdocumento, txtedad;
    JTextField txtdireccion;

    public RegistrarCliente(String titulo) {
        super(titulo);

        

       
//        cbxtipodocumento.getText().isEmpty()||

    }

   

    public void personalizartipoletra() {
        nombre.setFont(fontboton);
        apellidop.setFont(fontboton);
        apellidom.setFont(fontboton);
        telefono.setFont(fontboton);
        documento.setFont(fontboton);
        direccion.setFont(fontboton);
//        tipodocumento.setFont(fontboton);
        idcliente.setFont(fontboton);
        jledad.setFont(fontboton);

        txtnombre.setFont(fontboton);
        txtapellidop.setFont(fontboton);
        txtapellidom.setFont(fontboton);
        txtidpersona.setFont(fontboton);
        txtidcliente.setFont(fontboton);
        txttelefono.setFont(fontboton);
        txtdocumento.setFont(fontboton);
        txtdireccion.setFont(fontboton);
//        cbxtipodocumento.setFont(fontboton);
        txtedad.setFont(fontboton);
    }

    public boolean ExistenVacios() {
        if (txtapellidop.getText().isEmpty()
                || txtapellidom.getText().isEmpty()
                || txtnombre.getText().isEmpty()
                || txtdocumento.getText().isEmpty()
                || txttelefono.getText().isEmpty()
                || txtdireccion.getText().isEmpty()
                || txtedad.getText().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void iniciar_componentes(String titulo) {

        GridLayout g = new GridLayout(6, 1);
        g.setHgap(10);
        g.setVgap(10);
        pane = new JPanel(g);
        JPanel idpane = new JPanel(new GridLayout(1, 2));
        txtidpersona = new JTextField(10);
        idpane.add(txtidpersona);
        txtidpersona.setVisible(false);
        //primero
        JPanel panecliente = new JPanel(new GridLayout(1, 2));
        idcliente = new JLabel("ID Cliente");
        txtidcliente = new JTextField(10);
        txtidcliente.setEnabled(false);
        panecliente.add(idcliente, BorderLayout.WEST);
        panecliente.add(txtidcliente, BorderLayout.EAST);
        panecliente.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel panenombre = new JPanel(new GridLayout(1, 2));
        nombre = new JLabel("Nombre: ");
        txtnombre = new JTextField(10);
        panenombre.add(nombre, BorderLayout.WEST);
        panenombre.add(txtnombre, BorderLayout.EAST);
        panenombre.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        //segundo
        JPanel paneapellidop = new JPanel(new GridLayout(1, 2));
        apellidop = new JLabel("Apellido Paterno:");
        txtapellidop = new JTextField(10);
        paneapellidop.add(apellidop, BorderLayout.WEST);
        paneapellidop.add(txtapellidop, BorderLayout.EAST);
        paneapellidop.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel paneapellidom = new JPanel(new GridLayout(1, 2));
        apellidom = new JLabel("Apellido Materno:");
        txtapellidom = new JTextField(10);
        paneapellidom.add(apellidom, BorderLayout.WEST);
        paneapellidom.add(txtapellidom, BorderLayout.EAST);
        paneapellidom.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        //tercero
//        JPanel tipodoc = new JPanel(new GridLayout(1, 2));;
//        tipodocumento = new JLabel("Tipo de Documento");
//        String[] tipos = {"DNI"};
//        cbxtipodocumento = new JComboBox(tipos);
//        cbxtipodocumento.setPreferredSize(new Dimension(100, 5));
//        tipodoc.add(tipodocumento, BorderLayout.WEST);
//        tipodoc.add(cbxtipodocumento, BorderLayout.EAST);
//        tipodoc.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel panedoc = new JPanel(new GridLayout(1, 2));
        documento = new JLabel("Numero de DNI :");
        txtdocumento = new JTextField(10);
        panedoc.add(documento, BorderLayout.WEST);
        panedoc.add(txtdocumento, BorderLayout.EAST);
        panedoc.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel paneedad = new JPanel(new GridLayout(1, 2));
        jledad = new JLabel("Edad: ");
        txtedad = new JTextField(10);
        paneedad.add(jledad, BorderLayout.WEST);
        paneedad.add(txtedad, BorderLayout.EAST);
        paneedad.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        //cuarto
        JPanel panelscroll = new JPanel(new BorderLayout());

        direccion = new JLabel("Direccion: ");
        txtdireccion = new JTextField(40);
        panelscroll.add(direccion, BorderLayout.WEST);
        panelscroll.add(txtdireccion, BorderLayout.EAST);
        panelscroll.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        //quinto 
        JPanel panetelefono = new JPanel(new GridLayout(1, 2));
        telefono = new JLabel("Telefono:");
        txttelefono = new JTextField(10);
        panetelefono.add(telefono, BorderLayout.WEST);
        panetelefono.add(txttelefono, BorderLayout.EAST);
        panetelefono.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        //agrupamos entidades
        JPanel primero = new JPanel(new BorderLayout());
        primero.add(panecliente, BorderLayout.WEST);
        panecliente.setBackground(c);
        primero.add(panenombre, BorderLayout.EAST);
        panenombre.setBackground(c);
        primero.setBackground(c);

        JPanel segundo = new JPanel(new BorderLayout());
        segundo.add(paneapellidop, BorderLayout.WEST);
        paneapellidom.setBackground(c);
        segundo.add(paneapellidom, BorderLayout.EAST);
        paneapellidop.setBackground(c);
        segundo.setBackground(c);

        JPanel tercero = new JPanel(new BorderLayout());
//        tercero.add(tipodoc, BorderLayout.WEST);
//        tipodoc.setBackground(c);
        tercero.add(panedoc, BorderLayout.WEST);
        tercero.add(paneedad, BorderLayout.EAST);
        paneedad.setBackground(c);
        panedoc.setBackground(c);
        tercero.setBackground(c);

        JPanel cuarto = new JPanel(new BorderLayout());
        cuarto.add(panelscroll, BorderLayout.WEST);
        panelscroll.setBackground(c);
        cuarto.setBackground(c);

        JPanel quinto = new JPanel(new BorderLayout());

        quinto.add(panetelefono, BorderLayout.WEST);
        panetelefono.setBackground(c);
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
