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
import farmacia.jdbc.modelado.persona;
import farmacia.jdbc.modelado.tipotrabajador;
import farmacia.vista.mantenimientoEmpleado.Registrar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author fecyp
 */
public class frmregistrarSuperusuario extends JFrame implements ActionListener {

    Registrar panelregistro;
    JPanel principal;
    public static JButton salir, registrar;
Color c = new java.awt.Color(255, 255, 153);
    //FRM PARA REGISTRAR AL MASTER ADMIN 
    public frmregistrarSuperusuario() {
        iniciarcomponents();
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        salir.addActionListener(this);
        registrar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == salir) {
            System.exit(0);
        } else if (source == registrar) {
             if (panelregistro.txtnombre.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un Nombre para el Empleado", "Campo en blanco", JOptionPane.ERROR_MESSAGE);
                    panelregistro.txtnombre.requestFocus();
                    panelregistro.txtnombre.setBackground(Color.yellow);
                    return;
                }
                if (panelregistro.txtapellidop.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un Apellido paterno para el Empleado", "Campo en blanco", JOptionPane.ERROR_MESSAGE);
                    panelregistro.txtapellidop.requestFocus();
                    panelregistro.txtapellidop.setBackground(Color.yellow);
                    return;
                }
                if (panelregistro.txtapellidom.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un Apellido materno para el Empleado", "Campo en blanco", JOptionPane.ERROR_MESSAGE);
                    panelregistro.txtapellidom.requestFocus();
                    panelregistro.txtapellidom.setBackground(Color.yellow);
                    return;
                }
                if (panelregistro.txtdocumento.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un Numero de DNI para el Empleado", "Campo en blanco", JOptionPane.ERROR_MESSAGE);
                    panelregistro.txtdocumento.requestFocus();
                    panelregistro.txtdocumento.setBackground(Color.yellow);
                    return;
                }
               
               
               //verificar dni
                if(panelregistro.txtdocumento.getText().length()!=8)
                {
                   JOptionPane.showMessageDialog(null, "Debe ingresar un Numero de DNI Valido", "Campo en Invalido", JOptionPane.ERROR_MESSAGE);
                    panelregistro.txtdocumento.requestFocus();
                    panelregistro.txtdocumento.setBackground(Color.yellow);
                    return;
                }
                 if(panelregistro.txtuser.getText().length()<=4)
                {
                   JOptionPane.showMessageDialog(null, "El nombre de usuario debe tener minimo 4 caracteres", "Campo en Invalido", JOptionPane.ERROR_MESSAGE);
                    panelregistro.txtuser.requestFocus();
                    panelregistro.txtuser.setBackground(Color.yellow);
                    return;
                }
                  if (panelregistro.txtpassw.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar una contraseña  para el Poder registrarse", "Campo en blanco", JOptionPane.ERROR_MESSAGE);
                    panelregistro.txtpassw.requestFocus();
                    panelregistro.txtpassw.setBackground(Color.yellow);
                    return;
                }
            //se registra el tipo de trabajador
            tipotrabajador tipo = new tipotrabajador("ADMINISTRADOR", true, true, true, true, true, true, true, true, true, true, true, true);
            DAOManagerSQL manager = null;
            try {
                manager = new DAOManagerSQL("localhost", "basefarmacia", "root", "");
                manager.getTipoTrabajadorDAO().insertar(tipo);
          
            
                persona p;
                String nombre = panelregistro.txtnombre.getText();
                String appaterno = panelregistro.txtapellidop.getText();
                String apmaterno = panelregistro.txtapellidom.getText();
                String dni = panelregistro.txtdocumento.getText();
                char[] numerodni = dni.toCharArray();
                int personaedad = Integer.parseInt(panelregistro.txtedad.getText());
                String direccion = panelregistro.txtdireccion.getText();
                String telefono = panelregistro.txttelefono.getText();
                p = new persona(nombre, appaterno, apmaterno, numerodni, personaedad, direccion, telefono);

                empleado emp;
                Long tipousuario = 1L;
                Date time = panelregistro.fecharegistro.getDate();
                String usuario = panelregistro.txtuser.getText();
                String contraseña_encrip = cryptMD5(panelregistro.txtpassw.getText());
                if (tipousuario == null) {
                    JOptionPane.showMessageDialog(null, "algo ocurrio mal");

                }
                emp = new empleado(0L, tipousuario, usuario, contraseña_encrip,time);

                manager.getEmpleadoDAO().insertarNuevo(p, emp);
                manager.cerrarConexion();
                 new frmusuariologin().setVisible(true);
             dispose();
            } catch (DAOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
            
            

        }
    }

    private void iniciarcomponents() {
        principal = new JPanel(new BorderLayout());
        panelregistro = new Registrar();
        JPanel botones = new JPanel(new FlowLayout());

        salir = new JButton("SALIR", new ImageIcon(getClass().getResource("/Files/salir.gif")));
        registrar = new JButton("REGISTRAR", new ImageIcon(getClass().getResource("/Files/guardar.png")));
        botones.setBackground(c);
        botones.add(registrar);
        botones.add(salir);
        add(principal);
        principal.add(panelregistro, BorderLayout.CENTER);
        principal.add(botones, BorderLayout.SOUTH);

    }

    
}
