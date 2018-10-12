/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista.mantenimientoEmpresa;

import com.toedter.calendar.JDateChooser;
import farmacia.diseño.estrategias.EstrategiaPanelRegistrar;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author fecyp
 */
public class RegistrarEmpresa extends EstrategiaPanelRegistrar  {

    JLabel nombre, telefono, documento, direccion, idcliente, jlfecha;
    public JTextField txtnombre, txtidclienteempresa, txtidempresa, txttelefono, txtdocumento;
    JTextField txtdireccion;
//    JComboBox cbxtipodocumento;
//    JLabel tipodocumento;
    JDateChooser fecharegistro;

    public RegistrarEmpresa(String titulo) {
        super(titulo);

        
//        cbxtipodocumento.addKeyListener(this);

    }

    
    public void personalizartipoletra() {
        nombre.setFont(fontboton);

        telefono.setFont(fontboton);
        documento.setFont(fontboton);
        direccion.setFont(fontboton);
//        tipodocumento.setFont(fontboton);
        idcliente.setFont(fontboton);
        jlfecha.setFont(fontboton);

        txtnombre.setFont(fontboton);

        txttelefono.setFont(fontboton);
        txtdocumento.setFont(fontboton);
        txtdireccion.setFont(fontboton);
//        cbxtipodocumento.setFont(fontboton);
        fecharegistro.setFont(fontboton);
    }


    public void iniciar_componentes(String titulo) {

        GridLayout g = new GridLayout(5, 1);
        g.setHgap(10);
        g.setVgap(10);
        pane = new JPanel(g);
        JPanel idpane = new JPanel(new GridLayout(1, 2));
        txtidclienteempresa = new JTextField(10);
        idpane.add(txtidclienteempresa);
        txtidclienteempresa.setVisible(false);
        //primero
        JPanel panecliente = new JPanel(new GridLayout(1, 2));
        idcliente = new JLabel("ID Empresa");
        txtidempresa = new JTextField(10);
        txtidempresa.setEnabled(false);
        panecliente.add(idcliente, BorderLayout.WEST);
        panecliente.add(txtidempresa, BorderLayout.EAST);
        panecliente.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel panenombre = new JPanel(new GridLayout(1, 2));
        nombre = new JLabel("Razon Social: ");
        txtnombre = new JTextField(10);
        panenombre.add(nombre, BorderLayout.WEST);
        panenombre.add(txtnombre, BorderLayout.EAST);
        panenombre.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        //segundo

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
        documento = new JLabel("Numero de RUC :");
        txtdocumento = new JTextField(10);
        panedoc.add(documento, BorderLayout.WEST);
        panedoc.add(txtdocumento, BorderLayout.EAST);
        panedoc.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        //cuarto
        JPanel panelscroll = new JPanel(new BorderLayout());

        direccion = new JLabel("Direccion: ");
        txtdireccion = new JTextField(40);
        panelscroll.add(direccion, BorderLayout.WEST);
        panelscroll.add(txtdireccion, BorderLayout.EAST);
        panelscroll.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        //quinto 
        JPanel panefecha = new JPanel(new GridLayout(1, 2));
        jlfecha = new JLabel("Fecha de Registro:");
        fecharegistro = new JDateChooser(new Date());
        panefecha.add(jlfecha);
        panefecha.add(fecharegistro);
        panefecha.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

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

        JPanel tercero = new JPanel(new BorderLayout());
//        tercero.add(tipodoc, BorderLayout.WEST);
//        tipodoc.setBackground(c);
        tercero.add(panedoc, BorderLayout.WEST);
        panedoc.setBackground(c);
        tercero.setBackground(c);

        JPanel cuarto = new JPanel(new BorderLayout());
        cuarto.add(panelscroll, BorderLayout.WEST);
        panelscroll.setBackground(c);
        cuarto.setBackground(c);

        JPanel quinto = new JPanel(new BorderLayout());
        quinto.add(panefecha, BorderLayout.WEST);
        panefecha.setBackground(c);
        quinto.add(panetelefono, BorderLayout.EAST);
        panetelefono.setBackground(c);
        quinto.setBackground(c);

        pane.add(idpane);
        idpane.setBackground(c);
        pane.add(primero);
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
