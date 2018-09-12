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
public class frmpermiso extends JFrame implements ActionListener {

    JPanel panelprincipal;
    JButton cancelar, acceder;
    JLabel jluser, jlpass;
    JTextField user;
    JPasswordField password;
    Font font = new Font("Geneva", 1, 13);
//    Color c = new java.awt.Color(255, 255, 153);
    Color c=Color.WHITE;

    public frmpermiso() {
        iniciarComponentes();
        
        setTitle("Sesion");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        cambiarletras();
        pack();
         acceder.addActionListener(this);
        cancelar.addActionListener(this);
    }

    public void cambiarletras() {
        jluser.setFont(font);
        jlpass.setFont(font);
        user.setFont(font);
        password.setFont(font);
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

        JPanel panelpass = new JPanel(new GridLayout(1, 2));
        jlpass = new JLabel("Contraseña:");
        password = new JPasswordField(10);
        panelpass.add(jlpass);
        panelpass.add(password);
        panelpass.setBackground(c);

        JPanel todo = new JPanel(new BorderLayout());
        todo.add(paneuser, BorderLayout.NORTH);
        todo.add(panelpass, BorderLayout.SOUTH);
        todo.setBackground(c);
        todo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createTitledBorder("Proporcionar Acceso")));
        JPanel botones=new JPanel(new GridLayout(1,2));
        cancelar=new JButton("Cancelar");
        
        acceder=new JButton("Acceder");
        
        botones.add(acceder);
        botones.add(cancelar);
        botones.setBackground(c);
         
        panelprincipal.add(todo,BorderLayout.NORTH);
        panelprincipal.add(botones,BorderLayout.SOUTH);
        add(panelprincipal);
    }

    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        new frmpermiso().setVisible(true);
    }
}
