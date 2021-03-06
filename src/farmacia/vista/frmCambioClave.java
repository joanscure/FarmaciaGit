/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista;

import static farmacia.calculos.EncriptacionPass.cryptMD5;
import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.mysql.DAOManagerSQL;
import farmacia.jdbc.modelado.empleado;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 *
 * @author fecyp
 */
public class frmCambioClave extends JDialog implements ActionListener,KeyListener {

    JPanel panelprincipal;
    JButton cancelar, acceder;
    JLabel jluser, jlpassnueva, jlpassactual;
    JTextField user;
    JPasswordField txtpassnueva, txtpasswordconfir;
    Font font = new Font("Geneva", 1, 13);
//    Color c = new java.awt.Color(255, 255, 153);
    Color c = Color.WHITE;

    public frmCambioClave(frmprincipal frm) {
        super(frm, "Cambio de Clave", true);
        iniciarComponentes();
        //pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        cambiarletras();

//        setPreferredSize(300,200);
        acceder.addActionListener(this);
        user.addActionListener(this);
        txtpassnueva.addActionListener(this);
        txtpasswordconfir.addActionListener(this);
        cancelar.addActionListener(this);

    }

    public void cambiarletras() {
        jluser.setFont(font);
        jlpassnueva.setFont(font);
        jlpassactual.setFont(font);
        user.setFont(font);
        txtpasswordconfir.setFont(font);
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
        Object source = e.getSource();
        if (source == acceder) {
            String usuario = (user.getText());
            String contraseña = (String.valueOf(txtpasswordconfir.getPassword()));
            String contraseñaEcriptada = cryptMD5(contraseña);
            String contraseñaConfirmar = (String.valueOf(txtpassnueva.getPassword()));
            if (usuario.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campo vacío", "Advertencia ", JOptionPane.ERROR_MESSAGE);
                user.requestFocus();
                return;
            } 
            if (contraseña.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campo vacío", "Advertencia ", JOptionPane.ERROR_MESSAGE);
                txtpasswordconfir.requestFocus();
                return;
            }
            if (contraseñaConfirmar.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campo vacío", "Advertencia ", JOptionPane.ERROR_MESSAGE);
                txtpassnueva.requestFocus();
                return;
            }
            if (!contraseña.equals(contraseñaConfirmar)) {
                JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden", "Advertencia ", JOptionPane.ERROR_MESSAGE);
                return;
            }
            DAOManagerSQL manager = null;
                try {
                    manager = new DAOManagerSQL("localhost", "basefarmacia", "root", "");
                    empleado emp= new empleado();
                    emp.setLogin(usuario);
                    emp.setPassword(contraseñaEcriptada);
                    manager.getEmpleadoDAO().actualizarpassword(emp);
                    manager.cerrarConexion();
                    JOptionPane.showMessageDialog(null, "Clave actualizada correctamente", "Buen Trabajo ", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }catch (DAOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(),"Error SQL", JOptionPane.ERROR_MESSAGE);
                }
                
        } else if (source == cancelar) {
            dispose();
        }else if(source== user)
        {
            user.transferFocus();
        }
        else if(source==txtpasswordconfir)
        {
            txtpasswordconfir.transferFocus();
        }
        else if(source== txtpassnueva)
        {
            acceder.doClick();
        }
    }

    private void iniciarComponentes() {
        panelprincipal = new JPanel(new BorderLayout());
        panelprincipal.setBackground(c);
        JPanel paneuser = new JPanel(new GridLayout(1, 2));
        jluser = new JLabel("Usuario:");
        jluser.setFont(new Font("Geneva", 1, 16));
        user = new JTextField(10);
        user.setFont(new Font("Geneva", 1, 16));
        paneuser.add(jluser);
        paneuser.add(user);
        paneuser.setBackground(c);

        JPanel panelpassA = new JPanel(new GridLayout(1, 2));
        jlpassactual = new JLabel("Contraseña Nueva:");
        jlpassactual.setFont(new Font("Geneva", 1, 16));
        txtpasswordconfir = new JPasswordField(10);
        txtpasswordconfir.setFont(new Font("Geneva", 1, 16));
        panelpassA.add(jlpassactual);
        panelpassA.add(txtpasswordconfir);
        panelpassA.setBackground(c);

        JPanel panelpassN = new JPanel(new GridLayout(1, 2));
        jlpassnueva = new JLabel("Confirmar Contraseña:");
        jlpassnueva.setFont(new Font("Geneva", 1, 16));
        txtpassnueva = new JPasswordField(10);
        txtpassnueva.setFont(new Font("Geneva", 1, 16));
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
        cancelar.setFont(new Font("Geneva", 1, 16));

        acceder = new JButton("Acceder");
        acceder.setFont(new Font("Geneva", 1, 16));

        botones.add(acceder);
        botones.add(cancelar);
        botones.setBackground(c);

        panelprincipal.add(todo, BorderLayout.NORTH);
        panelprincipal.add(botones, BorderLayout.SOUTH);
        add(panelprincipal);
        setSize(400, 215);
        setResizable(false);
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
