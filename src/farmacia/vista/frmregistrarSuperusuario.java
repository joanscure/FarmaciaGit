/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista;

import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.mysql.DAOManagerSQL;
import farmacia.jdbc.modelado.tipotrabajador;
import farmacia.vista.mantenimientoEmpleado.Registrar;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author fecyp
 */
public class frmregistrarSuperusuario extends JFrame implements ActionListener{
    Registrar panelregistro;
    JPanel principal;
    public static JButton salir,registrar;
    //FRM PARA REGISTRAR AL MASTER ADMIN 
    public frmregistrarSuperusuario()
    {
        iniciarcomponents();
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source=e.getSource();
        if(source==salir)
        {
            System.exit(0);
        }
        else if(source==registrar)
        {
            //se registra el tipo de trabajador
               tipotrabajador tipo = new tipotrabajador("ADMINISTRADOR", true, true, true, true, true, true, true, true, true, true, true, true);
        DAOManagerSQL manager=null;
            try {
                manager = new DAOManagerSQL("localhost", "basefarmacia", "root", "");
                manager.getTipoTrabajadorDAO().insertar(tipo);
            } catch (DAOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        
          //se registra el empleado
        }
    }

    private void iniciarcomponents() {
        principal=new JPanel(new BorderLayout());
        panelregistro=new Registrar();
        JPanel botones=new JPanel(new FlowLayout());
        
        salir=new JButton("SALIR", new ImageIcon(getClass().getResource("/Files/salir.gif")));
        registrar=new JButton("REGISTREAR",new ImageIcon(getClass().getResource("/Files/guardar.png")));
        botones.add(registrar);
        botones.add(salir);
        add(principal);
        principal.add(panelregistro,BorderLayout.CENTER);
        principal.add(botones,BorderLayout.SOUTH);
        
        
    }
    public static void main(String[] args) {
        new frmregistrarSuperusuario().setVisible(true);
    }
}
