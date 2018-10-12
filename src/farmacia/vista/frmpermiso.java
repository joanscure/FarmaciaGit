/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista;

import static farmacia.calculos.EncriptacionPass.cryptMD5;
import farmacia.calculos.Permisos;
import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.mysql.DAOManagerSQL;
import farmacia.jdbc.modelado.empleado;
import farmacia.vista.mantenimientoCliente.frmClientes;
import farmacia.vista.mantenimientoEmpleado.frmEmpleados;
import farmacia.vista.mantenimientoEmpresa.frmEmpresa;
import farmacia.vista.mantenimientoProductos.frmProducto;
import farmacia.vista.mantenimientoTipoUsuario.frmTipousuario;
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
public class frmpermiso extends JDialog implements ActionListener {

    JPanel panelprincipal;
    JButton cancelar, acceder;
    JLabel jluser, jlpass;
    JTextField user;
    JPasswordField password;
    Font font = new Font("Geneva", 1, 13);
//    Color c = new java.awt.Color(255, 255, 153);
    Color c = Color.WHITE;
    frmClientes frm1;
    frmEmpleados frm2;
    frmTipousuario frm4;
    frmProducto frm3;
    frmEmpresa frm5;

    int index;

    public frmpermiso(frmClientes frm) {
//        super(frm, "Conceder Permiso", true);
        setTitle("No tiene Acceso");
        this.frm1 = frm;
        index = 11;
        setModal(true);
        iniciarComponentes();
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        cambiarletras();

        acceder.addActionListener(this);
        cancelar.addActionListener(this);
    }

    public frmpermiso(frmEmpleados frm) {
//        super(frm, "Conceder Permiso", true);
        setTitle("No tiene Acceso");
        this.frm2 = frm;
        index = 12;
        setModal(true);
        iniciarComponentes();
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        cambiarletras();

        acceder.addActionListener(this);
        cancelar.addActionListener(this);
    }

    public frmpermiso(frmEmpresa frm) {
//        super(frm, "Conceder Permiso", true);
        setTitle("No tiene Acceso");
        this.frm5 = frm;
        index = 14;
        setModal(true);
        iniciarComponentes();
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        cambiarletras();

        acceder.addActionListener(this);
        cancelar.addActionListener(this);
    }

    public frmpermiso(frmProducto frm) {
//        super(frm, "Conceder Permiso", true);
        setTitle("No tiene Acceso");
        this.frm3 = frm;
        index = 10;
        setModal(true);
        iniciarComponentes();
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        cambiarletras();

        acceder.addActionListener(this);
        cancelar.addActionListener(this);
    }

    public frmpermiso(frmTipousuario frm) {
//        super(frm, "Conceder Permiso", true);
        setTitle("No tiene Acceso");
        this.frm4 = frm;
        index = 13;
        setModal(true);
        iniciarComponentes();
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        cambiarletras();

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
        Object source = e.getSource();
        if (source == cancelar) {
            dispose();
        } else if (source == acceder) {
            String usuario = (user.getText());
            String contraseña = (String.valueOf(password.getPassword()));
            String contraseñaEcriptada = cryptMD5(contraseña);
            
            if (usuario.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campo vacío", "Advertencia ", JOptionPane.ERROR_MESSAGE);
                user.requestFocus();
                return;
            }
            if (contraseña.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campo vacío", "Advertencia ", JOptionPane.ERROR_MESSAGE);
                password.requestFocus();
                return;
            }
            if (!validaruser(usuario)) {
                JOptionPane.showMessageDialog(null, "Los datos no coinciden", "Acceso denegado", JOptionPane.ERROR_MESSAGE);
                user.requestFocus();
                return;
            }
            if (!validarpass(usuario, contraseñaEcriptada)) {
                JOptionPane.showMessageDialog(null, "Los datos no coinciden", "Acceso denegado", JOptionPane.ERROR_MESSAGE);
                user.requestFocus();
                return;
            }
            
            DAOManagerSQL manager = null;
            try {
                manager = new DAOManagerSQL("localhost", "basefarmacia", "root", "");
                empleado emp;
                emp = new empleado();
                emp.setLogin(usuario);
                String ocupacion = manager.getEmpleadoDAO().obtenerOcupacion(emp);
                manager.cerrarConexion();
                if (Permisos.verificarEliminar(index, ocupacion)) {

                    switch (index) {
                        case 10:
                            frm3.eliminar();
                            break;
                        case 11:
                            frm1.eliminar();
                            break;
                        case 12:
                            frm2.eliminar();
                            break;
                        case 13:
                            frm4.eliminar();
                            break;
                        case 14:
                            frm5.eliminar();
                            break;
                        default:
                            System.out.println("ERORRRRR");
                    }
                }
                else 
                {
                     JOptionPane.showMessageDialog(null, "No tiene acceso", "imposible eliminar ", JOptionPane.ERROR_MESSAGE);
                }
                dispose();

            } catch (DAOException ex) {
                System.out.println(" errorr");
            }

        }
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
                BorderFactory.createTitledBorder("Proporcionar Acceso de un Administrador")));
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
      private boolean validaruser(String user) {
        for (int i = 0; i < frmusuariologin.tabla.getRowCount(); i++) {
            if (user.equals(frmusuariologin.tabla.getValueAt(i, 2))) {
                return true;
            }

        }
        return false;
    }

    private boolean validarpass(String user, String passw) {
        
        for (int i = 0; i < frmusuariologin.tabla.getRowCount(); i++) {
            if (passw.equals(frmusuariologin.tabla.getValueAt(i, 3))&& user.equals(frmusuariologin.tabla.getValueAt(i, 2))) {
                return true;
            }

        }
        return false;
    }

}
