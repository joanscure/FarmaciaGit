/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista.mantenimientoEmpleado;

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
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author fecyp
 */
public class Registrar extends JPanel implements ActionListener, KeyListener {

    frmEmpleados regis;

    JPanel pane;
    JLabel nombre, apellidop, apellidom, telefono, documento, direccion, tipodocumento, idempleado, jlfecha,jlusr,jlpass,jlocupacion;
    public JTextField txtnombre, txtapellidop, txtapellidom, txtidpersona, txtidempleado, txttelefono, txtdocumento,txtuser,txtidtipodepersona;
    JTextField txtdireccion;
    JPasswordField txtpassw;
    JComboBox cbxtipodocumento,cbxtipodeempleado;
    JDateChooser fecharegistro;
    configuracionImagenes iamgeConfig = new configuracionImagenes();
    Color c = new java.awt.Color(255, 255, 153);
    Font fontboton = new Font("Geneva", 1, 13);
    boolean teclaunida=false;

    public Registrar(frmEmpleados regis) {
        this.regis = regis;
        iniciar_componentes();
        setBackground(c);
        personalizartipoletra();
        
        txtapellidop.addActionListener(this);
        txtnombre.addActionListener(this);
        txtdocumento.addActionListener(this);
        txttelefono.addActionListener(this);
        txtapellidom.addActionListener(this);
        txtdireccion.addActionListener(this);
        txtuser.addActionListener(this);
        txtpassw.addActionListener(this);
        
        txtapellidop.addKeyListener(this);
        txtapellidom.addKeyListener(this);
        txtnombre.addKeyListener(this);
        txtdocumento.addKeyListener(this);
        txttelefono.addKeyListener(this);
        txtdireccion.addKeyListener(this);
        cbxtipodocumento.addKeyListener(this);
        cbxtipodeempleado.addKeyListener(this);
        txtuser.addActionListener(this);
        txtpassw.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == txtnombre) {
            txtnombre.transferFocus();

        } else if (source == txtapellidop) {
            txtapellidop.transferFocus();

        } else if (source == txtapellidom) {
            cbxtipodocumento.setPopupVisible(true);
            txtapellidom.transferFocus();
        } else if (source == txtdocumento) {
            txtdocumento.transferFocus();
        } else if (source == txtdireccion) {
            txttelefono.requestFocus();
           
        } else if (source == txttelefono) {
            txttelefono.transferFocus();
        }
        else if(source==txtuser)
        {
            txtuser.transferFocus();
        }
        else if(source==txtpassw)
        {
            txtpassw.transferFocus();
            cbxtipodeempleado.setPopupVisible(true);
        }
    }

    public void personalizartipoletra() {
        nombre.setFont(fontboton);
        apellidop.setFont(fontboton);
        apellidom.setFont(fontboton);
        telefono.setFont(fontboton);
        documento.setFont(fontboton);
        direccion.setFont(fontboton);
        tipodocumento.setFont(fontboton);
        idempleado.setFont(fontboton);
        jlfecha.setFont(fontboton);
        jlusr.setFont(fontboton);
        txtuser.setFont(fontboton);
        jlpass.setFont(fontboton);
        txtpassw.setFont(fontboton);
        jlocupacion.setFont(fontboton);
        

        txtnombre.setFont(fontboton);
        txtapellidop.setFont(fontboton);
        txtapellidom.setFont(fontboton);
        txtidpersona.setFont(fontboton);
        txtidempleado.setFont(fontboton);
        txttelefono.setFont(fontboton);
        txtdocumento.setFont(fontboton);
        txtdireccion.setFont(fontboton);
        cbxtipodocumento.setFont(fontboton);
        fecharegistro.setFont(fontboton);
         cbxtipodeempleado.setFont(fontboton);
        
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        Object source = ke.getSource();
        if(source!= cbxtipodocumento && source!=cbxtipodeempleado)
            ke.getComponent().setBackground(Color.white);
        
        if ((source == txtapellidom || source == txtapellidop || source == txtnombre || source == txtdireccion)&&source!=txtpassw&& source!=txtuser&&source!=fecharegistro) {
            char c = ke.getKeyChar();
            if (Character.isLowerCase(c)) {
                String cad = ("" + c).toUpperCase();
                c = cad.charAt(0);
                ke.setKeyChar(c);
            }
            if (((ke.getKeyChar() < 97 || ke.getKeyChar() > 122)) && (ke.getKeyChar() < 65 || ke.getKeyChar() > 90) && source != txtdireccion) {
                ke.consume();
            }
        } else if (source == txttelefono || source == txtdocumento&&source!=txtpassw&& source!=txtuser) {
            if (ke.getKeyChar() < 48 || ke.getKeyChar() > 57) {
                ke.consume();
            }
        }
       
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        Object source = ke.getSource();
        if (source == cbxtipodocumento) {
            if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                cbxtipodocumento.setPopupVisible(false);
                cbxtipodocumento.transferFocus();
            }
        }
        else if (source == cbxtipodeempleado) {
            if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                cbxtipodeempleado.setPopupVisible(false);
                 frmEmpleados.jbGuardar.doClick();
            }
        }
        
         if(ke.getKeyCode()==KeyEvent.VK_ESCAPE)
            {
                frmEmpleados.jbCancelar.doClick();
                
            }
         if (ke.getExtendedKeyCode()== KeyEvent.VK_CONTROL ) {
                  teclaunida=true;
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_S &&teclaunida) {
                    frmEmpleados.jbGuardar.doClick();
                    teclaunida=false;
                }
    }

    private void iniciar_componentes() {

        GridLayout g = new GridLayout(8, 1);
        g.setHgap(10);
        g.setVgap(10);
        pane = new JPanel(g);
        JPanel idpane = new JPanel(new GridLayout(1, 2));
        txtidpersona = new JTextField(10);
        txtidtipodepersona=new JTextField(10);
        
        idpane.add(txtidpersona);
        idpane.add(txtidtipodepersona);
        txtidtipodepersona.setVisible(false);
        txtidpersona.setVisible(false);
        //primero
        JPanel panecliente = new JPanel(new GridLayout(1, 2));
        idempleado = new JLabel("ID Empleado");
        txtidempleado = new JTextField(10);
        txtidempleado.setEnabled(false);
        panecliente.add(idempleado, BorderLayout.WEST);
        panecliente.add(txtidempleado, BorderLayout.EAST);
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
        JPanel tipodoc = new JPanel(new GridLayout(1, 2));;
        tipodocumento = new JLabel("Tipo de Documento");
        String[] tipos = {"DNI"};
        cbxtipodocumento = new JComboBox(tipos);
        cbxtipodocumento.setPreferredSize(new Dimension(100, 5));
        tipodoc.add(tipodocumento, BorderLayout.WEST);
        tipodoc.add(cbxtipodocumento, BorderLayout.EAST);
        tipodoc.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel panedoc = new JPanel(new GridLayout(1, 2));
        documento = new JLabel("Numero Documento :");
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
        jlfecha = new JLabel("Fecha  Alta:");
        fecharegistro = new JDateChooser();
        panefecha.add(jlfecha);
        panefecha.add(fecharegistro);
        panefecha.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel panetelefono = new JPanel(new GridLayout(1, 2));
        telefono = new JLabel("Telefono:");
        txttelefono = new JTextField(10);
        panetelefono.add(telefono, BorderLayout.WEST);
        panetelefono.add(txttelefono, BorderLayout.EAST);
        panetelefono.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        //sexto
        
        JPanel paneuser=new JPanel(new GridLayout(1,2));
        jlusr=new JLabel("Nombre de Usuario:");
        txtuser=new JTextField(10);
        paneuser.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        paneuser.add(jlusr);
        paneuser.add(txtuser);
        
        JPanel panepass=new JPanel(new GridLayout(1,2));
        jlpass=new JLabel("Contraseña:");
        txtpassw=new JPasswordField(10);
        panepass.add(jlpass);
        panepass.add(txtpassw);
        panepass.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        // setimo
        JPanel paneocupacion=new JPanel(new GridLayout(1,2));
        jlocupacion=new JLabel("Tipo de Empleado:");
        String[] tiposE = {"Administrador","Vendedor"};
        cbxtipodeempleado=new JComboBox(tiposE);
        cbxtipodeempleado.setPreferredSize(new Dimension(100, 5));
        paneocupacion.add(jlocupacion);
        paneocupacion.add(cbxtipodeempleado);
        paneocupacion.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        //agrupamos paneles
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
        tercero.add(tipodoc, BorderLayout.WEST);
        tipodoc.setBackground(c);
        tercero.add(panedoc, BorderLayout.EAST);
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
        
        JPanel sexto=new JPanel(new BorderLayout());
        sexto.add(paneuser,BorderLayout.WEST);
        paneuser.setBackground(c);
        sexto.add(panepass,BorderLayout.EAST);
        panepass.setBackground(c);
        sexto.setBackground(c);
        
        JPanel setimo=new JPanel(new BorderLayout());
        setimo.add(paneocupacion,BorderLayout.WEST);
        paneocupacion.setBackground(c);
        setimo.setBackground(c);

        pane.add(idpane);
        idpane.setBackground(c);
        pane.add(primero);
        pane.add(segundo);
        pane.add(tercero);
        pane.add(cuarto);
        pane.add(quinto);
        pane.add(sexto);
        pane.add(setimo);
        pane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createTitledBorder("Datos del Empleado")));
        pane.setBackground(c);
//        setLayout(new GridLayout(1,1));
        add(pane);

    }

}
