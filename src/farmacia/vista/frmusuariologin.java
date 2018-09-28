/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista;

import farmacia.calculos.EncriptacionPass;
import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.mysql.DAOManagerSQL;
import farmacia.jdbc.modelado.empleado;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

/**
 *
 * @author fecyp
 */
public class frmusuariologin extends JFrame implements ActionListener, KeyListener {

    JPanel panenorth, panecenter, panemain;
    JLabel jlinformacion, jlusuario, jlpassword, jlimagen;
    JButton bnentrar, bncancelar;
    JTextField txtusuario;
    JPasswordField txtpassword;
    JTable tabla;
    DefaultTableModel modelo;
    int index;

    public frmusuariologin() throws DAOException {
        iniciar_componentes();
        llenartabla();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setTitle("Login");
        setType(Type.POPUP);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        bnentrar.addActionListener(this);
        bncancelar.addActionListener(this);
        txtusuario.addActionListener(this);
        txtpassword.addActionListener(this);
        txtusuario.addKeyListener(this);
        txtpassword.addKeyListener(this);

//         try{
//     Image img= ImageIO.read(new File("/Files/login.ico"));
//         setIconImage(img);
//     }catch(Exception ex)
//     {
//         System.out.println(ex.getMessage());
//     } 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == bnentrar) {
            //permitir acceso
            if (!validaruser()) {
                JOptionPane.showMessageDialog(null, "el nombre de usuario es invalido", "Acceso denegado", JOptionPane.ERROR_MESSAGE);
                txtusuario.requestFocus();

                return;
            }
            if (!validarpass()) {
                JOptionPane.showMessageDialog(null, "La contraseña es invalida", "Acceso denegado", JOptionPane.ERROR_MESSAGE);
                txtpassword.requestFocus();
                index = -1;
                return;
            }

            this.setVisible(false);
            frmprincipal sistem;
            try {
                sistem = new frmprincipal(this);
                 sistem.setVisible(true);
                 
            sistem.jlidempleado = new Long((Long) tabla.getValueAt(index, 0)+"");

            sistem.jlidpersona = new Long((Long) tabla.getValueAt(index, 1)+"") ;
            sistem.jlocupacion = new Long((Long) tabla.getValueAt(index, 5)+"") ;
            sistem.permisos();
            } catch (DAOException ex) {
                Logger.getLogger(frmusuariologin.class.getName()).log(Level.SEVERE, null, ex);
            }
           


        } else if (source == bncancelar) {
            System.exit(0);
        } else if (source == txtusuario) {
            if (txtpassword.getText().isEmpty()) {
                txtusuario.transferFocus();
            } else {
                if (!txtusuario.getText().isEmpty()) {
                    bnentrar.doClick();
                }
            }
        } else if (source == txtpassword) {
            if (txtusuario.getText().isEmpty()) {
                txtusuario.requestFocus();
            } else {
                if (!txtpassword.getText().isEmpty()) {
                    bnentrar.doClick();
                }
            }
        }
    }

    private void iniciar_componentes() {
        panenorth = new JPanel();
        panenorth.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panenorth.setBackground(Color.WHITE);
        panecenter = new JPanel(new BorderLayout());
        panecenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panecenter.setBackground(Color.WHITE);

        jlinformacion = new JLabel("Ingrese usuario y contraseña para tener Acceso");
        jlinformacion.setFont(new Font("Geneva", 1, 15));

        panenorth.add(jlinformacion);

        JPanel paneusuario = new JPanel(new GridLayout(1, 2));
        jlusuario = new JLabel("Usuario:");
        jlusuario.setFont(new Font("Geneva", 1, 16));
        txtusuario = new JTextField(10);
        txtusuario.setFont(new Font("Geneva", 1, 16));
        paneusuario.add(jlusuario);
        paneusuario.add(txtusuario);
        paneusuario.setBackground(Color.WHITE);

        JPanel panepassword = new JPanel(new GridLayout(1, 2));
        jlpassword = new JLabel("Contraseña:");
        jlpassword.setFont(new Font("Geneva", 1, 16));
        txtpassword = new JPasswordField(10);
        txtpassword.setFont(new Font("Geneva", 1, 16));
        panepassword.add(jlpassword);
        panepassword.add(txtpassword);
        panepassword.setBackground(Color.WHITE);

        JPanel panebotones = new JPanel(new FlowLayout());
        bnentrar = new JButton("Entrar", new ImageIcon(getClass().getResource("/Files/entrarlog.png")));
        bnentrar.setFont(new Font("Geneva", 1, 16));
        bnentrar.setBackground(Color.GRAY);
        bnentrar.setForeground(Color.white);
        bncancelar = new JButton("Cancelar", new ImageIcon(getClass().getResource("/Files/salirlog.png")));
        bncancelar.setForeground(Color.white);
        bncancelar.setBackground(Color.red);
        bncancelar.setFont(new Font("Geneva", 1, 16));

        panebotones.setBackground(Color.white);
        panebotones.add(bnentrar);
        panebotones.add(bncancelar);
        JPanel paneinteraccion = new JPanel(new GridLayout(3, 1));
        paneinteraccion.setPreferredSize(new Dimension(500, 150));
        paneinteraccion.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createTitledBorder("")));
        paneinteraccion.add(paneusuario);
        paneinteraccion.add(panepassword);
        paneinteraccion.add(panebotones);
        paneinteraccion.setBackground(Color.WHITE);

        JPanel paneimagen = new JPanel();
        jlimagen = new JLabel(new ImageIcon(getClass().getResource("/Files/userlog.png")));
        paneimagen.add(jlimagen);
        paneimagen.setBackground(Color.WHITE);
        panecenter.add(paneimagen, BorderLayout.WEST);
        panecenter.add(paneinteraccion, BorderLayout.EAST);
        paneimagen.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panemain = new JPanel(new BorderLayout());
        panemain.setBackground(Color.WHITE);
        panemain.add(panenorth, BorderLayout.NORTH);
        panemain.add(panecenter, BorderLayout.CENTER);
        panemain.add(tablaUsuarios(), BorderLayout.SOUTH);
        setContentPane(panemain);
    }

    public JScrollPane tablaUsuarios() {
        tabla = new JTable();
        Object[][] data = new Object[0][0];
        String[] lista = {"idempleado", "idpersona", "login", "password", "fecha Alta", "ocupacion", "estado"};
        modelo = new DefaultTableModel(data, lista) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JScrollPane pane = new JScrollPane(tabla);
        pane.setBackground(Color.WHITE);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setModel(modelo);
        pane.setVisible(false);
        return pane;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            bncancelar.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void llenartabla() throws DAOException {
        DAOManagerSQL manager = null;
        try {
            manager = new DAOManagerSQL("localhost", "basefarmacia", "root", "");
            List<empleado> lista = manager.getEmpleadoDAO().obtenertodos();
            for (int i = 0; i < lista.size(); i++) {
                Object obj[] = {lista.get(i).getIdempleado(), lista.get(i).getIdpersona(), lista.get(i).getLogin(), lista.get(i).getPassword(),lista.get(i).getFechaalta(), lista.get(i).getIdtipotrabajador(), lista.get(i).isStatus()};

                modelo.addRow(obj);

            }

            manager.cerrarConexion();
        } catch (DAOException ex) {
            throw new DAOException("error al buscar" + ex.getMessage());
        }
    }

    private boolean validaruser() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (txtusuario.getText().equals(tabla.getValueAt(i, 2))) {
                return true;
            }

        }
        return false;
    }

    private boolean validarpass() {
        String passw = EncriptacionPass.cryptMD5(txtpassword.getText());
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (passw.equals(tabla.getValueAt(i, 3))) {
                index = i;
                return true;
            }

        }
        return false;
    }

}
