/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista.mantenimientoCliente;

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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author fecyp
 */
public class Registrar extends JPanel implements ActionListener, KeyListener {

    frmClientes regis;

    JPanel pane;
    JLabel nombre, apellidop, apellidom, telefono, documento, direccion, idcliente, jledad;
    public JTextField txtnombre, txtapellidop, txtapellidom, txtidpersona, txtidcliente, txttelefono, txtdocumento, txtedad;
    JTextField txtdireccion;
//    JComboBox cbxtipodocumento;
//    JLabel tipodocumento;
    
    configuracionImagenes iamgeConfig = new configuracionImagenes();
    Color c = new java.awt.Color(255, 255, 153);
    Font fontboton = new Font("Geneva", 1, 13);
    boolean teclaunida = false;

    public Registrar(frmClientes regis) {
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
        txtedad.addActionListener(this);

        txtapellidop.addKeyListener(this);
        txtapellidom.addKeyListener(this);
        txtnombre.addKeyListener(this);
        txtdocumento.addKeyListener(this);
        txttelefono.addKeyListener(this);
        txtdireccion.addKeyListener(this);
        txtedad.addKeyListener(this);
//        cbxtipodocumento.getText().isEmpty()||

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(!ExistenVacios())
        {
             frmClientes.jbGuardar.doClick();
        }
        if (source == txtnombre) {
            txtnombre.transferFocus();

        } else if (source == txtapellidop) {
            txtapellidop.transferFocus();

        } else if (source == txtapellidom) {
//            cbxtipodocumento.setPopupVisible(true);
            txtapellidom.transferFocus();
        } else if (source == txtdocumento) {
            txtdocumento.transferFocus();
        } else if (source == txtdireccion) {
            txttelefono.requestFocus();
        } else if (source == txttelefono) {
            frmClientes.jbGuardar.doClick();
        } else if (source == txtedad) {
            txtedad.transferFocus();
        }
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

    @Override
    public void keyTyped(KeyEvent ke) {
        Object source = ke.getSource();
//        if (source != cbxtipodocumento) {
        ke.getComponent().setBackground(Color.white);
//        }

        if (source == txtapellidom || source == txtapellidop || source == txtnombre || source == txtdireccion) {
            char c = ke.getKeyChar();
            if (Character.isLowerCase(c)) {
                String cad = ("" + c).toUpperCase();
                c = cad.charAt(0);
                ke.setKeyChar(c);
            }
            if (((ke.getKeyChar() < 97 || ke.getKeyChar() > 122)) && (ke.getKeyChar() < 65 || ke.getKeyChar() > 90) && source != txtdireccion) {
                ke.consume();
            }
        } else if (source == txttelefono || source == txtdocumento) {
            if (ke.getKeyChar() < 48 || ke.getKeyChar() > 57) {
                ke.consume();
            }
            if (source == txtdocumento) {
                if (txtdocumento.getText().length() >= 8) {
                    ke.consume();
                }
            }
            if (source == txttelefono) {
                if (txttelefono.getText().length() >= 9) {
                    ke.consume();
                }
            }
        } else if (source == txtedad) {
            if (ke.getKeyChar() < 48 || ke.getKeyChar() > 57) {

                ke.consume();
            }
            if (txtedad.getText().length() >= 2) {
                ke.consume();
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        Object source = ke.getSource();
//        if (source == cbxtipodocumento) {
//            if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
//                cbxtipodocumento.setPopupVisible(false);
//                cbxtipodocumento.transferFocus();
//            }
//        }
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            if (source == txttelefono) {
                txtdireccion.requestFocus();
                return;
            }
            ke.getComponent().transferFocusBackward();
        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (source == txttelefono) {
                txtnombre.requestFocus();
                return;
            }
            if (source == txtdireccion) {
                txttelefono.requestFocus();
                return;
            }
            ke.getComponent().transferFocus();
        }
        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
            frmClientes.jbCancelar.doClick();

        }
        if (ke.getExtendedKeyCode() == KeyEvent.VK_CONTROL) {
            teclaunida = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_S && teclaunida) {
            frmClientes.jbGuardar.doClick();
            teclaunida = false;
        }
    }

    private void iniciar_componentes() {

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
                BorderFactory.createTitledBorder("Datos del Cliente")));
        pane.setBackground(c);
//        setLayout(new GridLayout(1,1));
        add(pane);

    }

}
