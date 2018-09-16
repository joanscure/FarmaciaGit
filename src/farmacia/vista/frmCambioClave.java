/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author fecyp
 */
public class frmCambioClave extends JDialog implements ActionListener {

    JPanel panelprincipal;
    JButton cancelar, acceder;
    JLabel jluser, jlpassnueva, jlpassactual;
    JTextField user;
    JPasswordField txtpassnueva, txtpassactual;
    Font font = new Font("Geneva", 1, 13);
//    Color c = new java.awt.Color(255, 255, 153);
    Color c = Color.WHITE;

    public frmCambioClave(frmprincipal frm) {
        super(frm, "Cambio de Clave", true);
        iniciarComponentes();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        cambiarletras();
        pack();
//        setPreferredSize(300,200);
        acceder.addActionListener(this);
        cancelar.addActionListener(this);

    }

    public void cambiarletras() {
        jluser.setFont(font);
        jlpassnueva.setFont(font);
        jlpassactual.setFont(font);
        user.setFont(font);
        txtpassactual.setFont(font);
        txtpassnueva.setFont(font);
        cancelar.setFont(font);
        acceder.setFont(font);
        acceder.setBackground(Color.GRAY);
        acceder.setForeground(Color.white);
        cancelar.setForeground(Color.white);
        cancelar.setBackground(Color.red);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();

    }

    private void iniciarComponentes() {
        panelprincipal = new JPanel(new BorderLayout());
        panelprincipal.setBackground(c);
        JPanel paneuser = new JPanel(new GridLayout(1, 2));
        jluser = new JLabel("Usuario:");
        user = new JTextField(10);
        paneuser.add(jluser);
        paneuser.add(user);
        paneuser.setBackground(c);

        JPanel panelpassA = new JPanel(new GridLayout(1, 2));
        jlpassactual = new JLabel("Contraseña Actual:");
        txtpassactual = new JPasswordField(10);
        panelpassA.add(jlpassactual);
        panelpassA.add(txtpassactual);
        panelpassA.setBackground(c);

        JPanel panelpassN = new JPanel(new GridLayout(1, 2));
        jlpassnueva = new JLabel("Contraseña Nueva:");
        txtpassnueva = new JPasswordField(10);
        panelpassN.add(jlpassnueva);
        panelpassN.add(txtpassnueva);
        panelpassN.setBackground(c);

        JPanel todo = new JPanel(new BorderLayout());
        todo.add(paneuser, BorderLayout.NORTH);
        todo.add(panelpassA, BorderLayout.CENTER);
        todo.add(panelpassN, BorderLayout.SOUTH);
        todo.setBackground(c);
        todo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createTitledBorder("Proporcionar Acceso")));
        JPanel botones = new JPanel(new GridLayout(1, 2));
        cancelar = new JButton("Cancelar");

        acceder = new JButton("Acceder");

        botones.add(acceder);
        botones.add(cancelar);
        botones.setBackground(c);

        panelprincipal.add(todo, BorderLayout.NORTH);
        panelprincipal.add(botones, BorderLayout.SOUTH);
        add(panelprincipal);
    }

}
