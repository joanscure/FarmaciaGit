/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista;

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
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fecyp
 */
public class frmusuariologin extends JFrame implements ActionListener ,KeyListener{

    JPanel panenorth, panecenter, panemain;
    JLabel jlinformacion, jlusuario, jlpassword, jlimagen;
    JButton bnentrar, bncancelar;
    JTextField txtusuario;
    JPasswordField txtpassword;
    JTable tabla;
    DefaultTableModel modelo;

    public frmusuariologin() {
        iniciar_componentes();
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
            this.setVisible(false);
            frmprincipal sistem = new frmprincipal(this);
            sistem.setVisible(true);
        } else if (source == bncancelar) {
            System.exit(0);
        } else if (source == txtusuario) {
           if(txtpassword.getText().isEmpty())
           {
              txtusuario.transferFocus();
           }
           else
           {
                if(!txtusuario.getText().isEmpty())
               {
                   bnentrar.doClick();
               }
           }
        }else if (source == txtpassword) {
              if(txtusuario.getText().isEmpty())
           {
              txtusuario.requestFocus();
           }
           else
           {
                if(!txtpassword.getText().isEmpty())
               {
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
        String[] lista = {"idempleado", "idpersona", "Nombre", "Apellido Paterno", "Apellido Materno", "Tipo de Documento", "Numero de Documento", "Direccion", "Telefono", "login", "password", "fecha Alta", "ocupacion", "estado"};
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

    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        new frmusuariologin().setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
        {
           bncancelar.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
