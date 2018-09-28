/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista.mantenimientoEmpresa;

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
import java.util.Date;
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

    frmEmpresa regis;

    JPanel pane;
    JLabel nombre, telefono, documento, direccion, idcliente, jlfecha;
    public JTextField txtnombre, txtidclienteempresa, txtidempresa, txttelefono, txtdocumento;
    JTextField txtdireccion;
//    JComboBox cbxtipodocumento;
//    JLabel tipodocumento;
    JDateChooser fecharegistro;
    configuracionImagenes iamgeConfig = new configuracionImagenes();
    Color c = new java.awt.Color(255, 255, 153);
    Font fontboton = new Font("Geneva", 1, 13);
    boolean teclaunida = false;

    public Registrar(frmEmpresa regis) {
        this.regis = regis;
        iniciar_componentes();
        setBackground(c);
        personalizartipoletra();

        txtnombre.addActionListener(this);
        txtdocumento.addActionListener(this);
        txttelefono.addActionListener(this);
        txtdireccion.addActionListener(this);

        txtnombre.addKeyListener(this);
        txtdocumento.addKeyListener(this);
        txttelefono.addKeyListener(this);
        txtdireccion.addKeyListener(this);
//        cbxtipodocumento.addKeyListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == txtnombre) {
            txtnombre.transferFocus();

        } else if (source == txtdocumento) {
            txtdocumento.transferFocus();
        } else if (source == txtdireccion) {
            txttelefono.requestFocus();
        } else if (source == txttelefono) {
            frmEmpresa.jbGuardar.doClick();
        }
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

    @Override
    public void keyTyped(KeyEvent ke) {
        Object source = ke.getSource();
//        if (source != cbxtipodocumento) {
        ke.getComponent().setBackground(Color.white);
//        }

        if (source == txtnombre || source == txtdireccion) {
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
                if (txtdocumento.getText().length() >= 11) {
                    ke.consume();
                }
            }
            if (source == txttelefono) {
                if (txttelefono.getText().length() >= 9) {
                    ke.consume();
                }
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
            frmEmpresa.jbCancelar.doClick();

        }
        if (ke.getExtendedKeyCode() == KeyEvent.VK_CONTROL) {
            teclaunida = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_S && teclaunida) {
            frmEmpresa.jbGuardar.doClick();
            teclaunida = false;
        }
    }

    private void iniciar_componentes() {

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
                BorderFactory.createTitledBorder("Datos del Empresa")));
        pane.setBackground(c);
//        setLayout(new GridLayout(1,1));
        add(pane);

    }

}
