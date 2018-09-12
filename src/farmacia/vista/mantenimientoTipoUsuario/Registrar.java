/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista.mantenimientoTipoUsuario;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import farmacia.calculos.configuracionImagenes;
import farmacia.vista.mantenimientoCliente.frmClientes;
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
import javax.swing.JCheckBox;
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

    frmTipousuario regis;

    JPanel pane;
    JLabel descripcion, tipouser;
    public JTextField txtdescripcion, txtidtipouser;
    JCheckBox aventas, aproductos, aclientes, aconsultas, aempleados, atiposusuario, acambioclave, aanularventas, aeliminarproducto, aelmininartipotrabajor, aeliminarusuario, aeliminarclientes;
    configuracionImagenes iamgeConfig = new configuracionImagenes();
    Color c = new java.awt.Color(255, 255, 153);
    Font fontboton = new Font("Geneva", 1, 13);
    boolean teclaunida = false;

    public Registrar(frmTipousuario regis) {
        this.regis = regis;
        iniciar_componentes();
        setBackground(c);
        personalizartipoletra();
        txtdescripcion.addActionListener(this);
        txtdescripcion.addKeyListener(this);
        aventas.addKeyListener(this);
        aproductos.addKeyListener(this);
        aclientes.addKeyListener(this);
        aconsultas.addKeyListener(this);
        aempleados.addKeyListener(this);
        atiposusuario.addKeyListener(this);
        acambioclave.addKeyListener(this);
        aanularventas.addKeyListener(this);
        aeliminarproducto.addKeyListener(this);
        aelmininartipotrabajor.addKeyListener(this);
        aeliminarusuario.addKeyListener(this);
        aeliminarclientes.addKeyListener(this);
        

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == txtdescripcion) {
            txtdescripcion.transferFocus();
        }

    }

    public void personalizartipoletra() {
        txtdescripcion.setFont(fontboton);
        txtidtipouser.setFont(fontboton);
        tipouser.setFont(fontboton);
        descripcion.setFont(fontboton);
        aventas.setFont(fontboton);
        aproductos.setFont(fontboton);
        aclientes.setFont(fontboton);
        aconsultas.setFont(fontboton);
        aempleados.setFont(fontboton);
        atiposusuario.setFont(fontboton);
        acambioclave.setFont(fontboton);
        aanularventas.setFont(fontboton);
        aeliminarproducto.setFont(fontboton);
        aelmininartipotrabajor.setFont(fontboton);
        aeliminarusuario.setFont(fontboton);
        aeliminarclientes.setFont(fontboton);
        aeliminarclientes.setBackground(Color.WHITE);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        Object source=ke.getSource();
    
         if (source == txtdescripcion) {
            char c = ke.getKeyChar();
            if (Character.isLowerCase(c)) {
                String cad = ("" + c).toUpperCase();
                c = cad.charAt(0);
                ke.setKeyChar(c);
            }
            if (((ke.getKeyChar() < 97 || ke.getKeyChar() > 122)) && (ke.getKeyChar() < 65 || ke.getKeyChar() > 90)) {
                ke.consume();
            }
}

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        Object source = ke.getSource();
        if (source != txtdescripcion) {
            if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
                ke.getComponent().transferFocusBackward();
            }
            if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                ke.getComponent().transferFocus();
            }
            if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                JCheckBox cheaux = (JCheckBox) ke.getComponent();
                cheaux.setSelected(!cheaux.isSelected());
            }
        }
        else
        {
            txtdescripcion.setBackground(Color.white);
        }
        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
            frmClientes.jbCancelar.doClick();

        }

        if (ke.getExtendedKeyCode() == KeyEvent.VK_CONTROL) {
            teclaunida = true;
        }
        System.out.println(ke.getExtendedKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_S && teclaunida) {
            frmClientes.jbGuardar.doClick();
            teclaunida = false;
        }
    }

    private void iniciar_componentes() {

        pane = new JPanel(new BorderLayout());

        //primero
        JPanel paneid = new JPanel(new FlowLayout());
        tipouser = new JLabel("ID: ");
        txtidtipouser = new JTextField(10);
        txtidtipouser.setEnabled(false);
        paneid.add(tipouser, BorderLayout.WEST);
        paneid.add(txtidtipouser, BorderLayout.EAST);
        paneid.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel panenombre = new JPanel(new FlowLayout());
        descripcion = new JLabel("Descripcion: ");
        txtdescripcion = new JTextField(10);
        panenombre.add(descripcion, BorderLayout.WEST);
        panenombre.add(txtdescripcion, BorderLayout.EAST);
        panenombre.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        //segundo

        //agrupamos entidades
        JPanel primero = new JPanel(new BorderLayout());
        primero.add(paneid, BorderLayout.WEST);
        paneid.setBackground(c);
        primero.add(panenombre, BorderLayout.EAST);
        panenombre.setBackground(c);
        primero.setBackground(c);
        GridLayout g = new GridLayout(4, 3);
        g.setHgap(20);
        g.setVgap(20);
        JPanel segundo = new JPanel(g);

        aventas = new JCheckBox("Acceder Ventas");
        aproductos = new JCheckBox("Acceder Productos");
        aclientes = new JCheckBox("Acceder Clientes");
        aconsultas = new JCheckBox("Acceder Consultas");
        aempleados = new JCheckBox("Acceder Empleados");
        atiposusuario = new JCheckBox("Acceder Tipo usuario");
        acambioclave = new JCheckBox("Acceder Cambio de Clave");
        aanularventas = new JCheckBox("Acceder Anular Ventas");
        aeliminarproducto = new JCheckBox("Acceder Eliminar Productos");
        aelmininartipotrabajor = new JCheckBox("Acceder Eliminar Tipo Trabajador");
        aeliminarusuario = new JCheckBox("Acceder Eliminar Usuario");
        aeliminarclientes = new JCheckBox("Acceder Eliminar Clientes");

        segundo.add(aventas);
        segundo.add(aproductos);
        segundo.add(aclientes);
        segundo.add(aconsultas);
        segundo.add(aempleados);
        segundo.add(atiposusuario);
        segundo.add(acambioclave);
        segundo.add(aanularventas);
        segundo.add(aeliminarproducto);
        segundo.add(aelmininartipotrabajor);
        segundo.add(aeliminarusuario);
        segundo.add(aeliminarclientes);

        segundo.setBackground(c);

        pane.add(primero, BorderLayout.NORTH);
        pane.add(segundo, BorderLayout.SOUTH);

        segundo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createTitledBorder("Permisos")));
        pane.setBackground(c);
//        setLayout(new GridLayout(1,1));
        add(pane);

    }

    public boolean verificarselected() {
        boolean resp = false;
        boolean valid[] = {
            aventas.isSelected(),
            aproductos.isSelected(),
            aclientes.isSelected(),
            aconsultas.isSelected(),
            aempleados.isSelected(),
            atiposusuario.isSelected(),
            acambioclave.isSelected(),
            aanularventas.isSelected(),
            aeliminarproducto.isSelected(),
            aelmininartipotrabajor.isSelected(),
            aeliminarusuario.isSelected(),
            aeliminarclientes.isSelected()};
        for (int i = 0; i < valid.length; i++) {
            if (valid[i]) {
                resp = true;
            }

        }
        return resp;
    }
}
