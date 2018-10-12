/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista.mantenimientoEmpleado;

import static farmacia.calculos.EncriptacionPass.cryptMD5;
import farmacia.calculos.calculosTipousuario;
import farmacia.diseño.estrategias.EstrategiaIFrame;
import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.mysql.DAOManagerSQL;
import farmacia.jdbc.modelado.empleado;
import farmacia.jdbc.modelado.persona;
import farmacia.vista.frmpermiso;
import farmacia.vista.frmprincipal;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.RowFilter;

/**
 *
 * @author fecyp
 */
public class frmEmpleados extends EstrategiaIFrame implements ActionListener {

    public ListadoEmpleado pane1;
    public RegistrarEmpleado pane2;

    public frmEmpleados(String titulo) throws DAOException {
        super(titulo);
        pane1.actualizartabla();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == jbModificar) {
            habilitar();
            int fila = pane1.tabla.getSelectedRow();
            pane2.txtidempleado.setText((Long) pane1.tabla.getValueAt(fila, 0) + "");
            pane2.txtidpersona.setText((Long) pane1.tabla.getValueAt(fila, 1) + "");
            pane2.txtnombre.setText((String) pane1.tabla.getValueAt(fila, 2));
            pane2.txtapellidop.setText((String) pane1.tabla.getValueAt(fila, 3));
            pane2.txtapellidom.setText((String) pane1.tabla.getValueAt(fila, 4));
            pane2.txtdocumento.setText((String) pane1.tabla.getValueAt(fila, 5));
            pane2.txtedad.setText((int) pane1.tabla.getValueAt(fila, 6) + "");
            pane2.txtdireccion.setText((String) pane1.tabla.getValueAt(fila, 7) + "");
            pane2.txttelefono.setText((String) pane1.tabla.getValueAt(fila, 8) + "");
            pane2.txtuser.setEnabled(false);
            pane2.txtpassw.setEnabled(false);
            pane2.cbxtipodeempleado.setEnabled(false);
            action = "modificar";
            pestañas.setSelectedIndex(1);
            pane2.txtnombre.requestFocus();
            pestañas.setEnabledAt(0, false);

            jbEliminar.setEnabled(false);
            jbSalir.setEnabled(false);
            jbNuevo.setEnabled(false);

        } else if (source == jbCancelar) {
            pane1.tabla.clearSelection();
            deshabilitar();
            pestañas.setEnabledAt(0, true);
            pestañas.setSelectedIndex(0);
            pane1.control = true;
            jbModificar.setEnabled(false);
            jbEliminar.setEnabled(false);
            action = "nothing";
            pane1.txtBuscar.requestFocus();

        } else if (source == jbGuardar) {
            if (action.equals("nuevo")) {
                int confirmacion = JOptionPane.showConfirmDialog(rootPane, "¿Estas seguro que quieres Guardar al Empleado?", "confirmar", 2);
                if (confirmacion == 0) {
                    guardar();
                }
            } else if (action.equals("modificar")) {
                int confirmacion = JOptionPane.showConfirmDialog(rootPane, "¿Estas seguro que quieres  Guardar la edicion del empleado ?", "confirmar", 2);
                if (confirmacion == 0) {
                    modificar();

                }

            }
        } else if (source == jbEliminar) {
            int confirmacion = JOptionPane.showConfirmDialog(rootPane, "¿Estas seguro que quieres eliminar el Empleado?", "confirmar", 2);
            if (confirmacion == 0) {
                 if (permisoeliminar) {
                    frmpermiso permiso = new frmpermiso(this);
                    return;
                }
                eliminar();
            }

        } else if (source == jbSalir) {
            int confirmacion = JOptionPane.showConfirmDialog(rootPane, "¿Estas seguro que quieres Salir?", "confirmar", 2);
            if (confirmacion == 0) {
                deshabilitar();
                pane1.tabla.clearSelection();

                pane1.txtBuscar.setText("");
                pane1.elQueOrdena.setRowFilter(RowFilter.regexFilter("", 0));
                jbModificar.setEnabled(false);
                jbCancelar.setEnabled(false);
                jbEliminar.setEnabled(false);
                jbGuardar.setEnabled(false);
                pane1.control = true;

                dispose();
                frmprincipal.visibleempleados = false;
                frmprincipal.marchivo.requestFocus();
            }
        } else if (source == jbNuevo) {
            habilitar();
            action = "nuevo";
            pane1.control = true;
            pestañas.setSelectedIndex(1);
            pane2.txtnombre.requestFocus();
            pestañas.setEnabledAt(0, false);
            jbSalir.setEnabled(false);
            jbNuevo.setEnabled(false);

        }
    }

    public void Iniciar_componentes(String titulo) {
        pestañas = new JTabbedPane();
        pane1 = new ListadoEmpleado(titulo);
        pane2 = new RegistrarEmpleado(titulo);
        pestañas.add("Buscar", pane1);

        pestañas.add("Registrar", pane2);
        pestañas.setIconAt(0, config.obtenerIcono("buscar.png", 15));
        pestañas.setIconAt(1, config.obtenerIcono("nuevo.png", 15));
        pestañas.setSelectedIndex(1);
        JPanel principal = new JPanel();
        principal.setBackground(c);
        principal.add(pestañas, BorderLayout.CENTER);
        add(principal, BorderLayout.WEST);
        add(getBotones(), BorderLayout.EAST);

        jbNuevo.setEnabled(true);
        jbGuardar.setEnabled(false);
        jbEliminar.setEnabled(false);
        jbSalir.setEnabled(true);
        jbModificar.setEnabled(false);
        jbCancelar.setEnabled(false);

        jbNuevo.addActionListener(this);
        jbGuardar.addActionListener(this);
        jbEliminar.addActionListener(this);
        jbSalir.addActionListener(this);
        jbModificar.addActionListener(this);

        jbModificar.addActionListener(this);
        jbCancelar.addActionListener(this);
    }

    public void habilitar() {
        pane2.txtnombre.setEnabled(true);
        pane2.txtapellidop.setEnabled(true);
        pane2.txtapellidom.setEnabled(true);
        pane2.txtdocumento.setEnabled(true);
        pane2.txtdireccion.setEnabled(true);
        pane2.fecharegistro.setEnabled(true);
        pane2.txttelefono.setEnabled(true);
        pane2.txtedad.setEnabled(true);
//        pane2.cbxtipodocumento.setEnabled(true);
        pane2.txtuser.setEnabled(true);
        pane2.txtpassw.setEnabled(true);
        pane2.cbxtipodeempleado.setEnabled(true);

        jbNuevo.setEnabled(true);
        jbEliminar.setEnabled(false);
        jbModificar.setEnabled(false);
        jbGuardar.setEnabled(true);
        jbSalir.setEnabled(true);
        jbCancelar.setEnabled(true);

        pane2.txtidempleado.setText("");
        pane2.fecharegistro.cleanup();
        pane2.txtidtipodepersona.setText("");
        pane2.txtidpersona.setText("");
        pane2.txtnombre.setText("");
        pane2.txtedad.setText("");
        pane2.txtapellidop.setText("");
        pane2.txtapellidom.setText("");
        pane2.txtdocumento.setText("");
        pane2.txtdireccion.setText("");
        pane2.txttelefono.setText("");
        pane2.txtuser.setText("");
        pane2.txtpassw.setText("");
//        pane2.cbxtipodocumento.setSelectedIndex(0);
        pane2.cbxtipodeempleado.setSelectedIndex(0);

    }

    public void deshabilitar() {
        pane2.txtnombre.setEnabled(false);
        pane2.txtapellidop.setEnabled(false);
        pane2.txtapellidom.setEnabled(false);
        pane2.txtdocumento.setEnabled(false);
        pane2.txtdireccion.setEnabled(false);
        pane2.fecharegistro.setEnabled(false);
        pane2.txtedad.setEnabled(false);
        pane2.txttelefono.setEnabled(false);
//        pane2.cbxtipodocumento.setEnabled(false);
        pane2.txtuser.setEnabled(false);
        pane2.txtpassw.setEnabled(false);
        pane2.cbxtipodeempleado.setEnabled(false);

        jbNuevo.setEnabled(true);
        jbGuardar.setEnabled(false);
        jbSalir.setEnabled(true);
        jbCancelar.setEnabled(false);

        pane2.txtidempleado.setText("");
        pane2.txtidtipodepersona.setText("");
        pane2.txtidpersona.setText("");
        pane2.txtnombre.setText("");
        pane2.txtapellidop.setText("");
        pane2.txtedad.setText("");
        pane2.txtapellidom.setText("");
        pane2.txtdocumento.setText("");
        pane2.txtdireccion.setText("");
        pane2.txttelefono.setText("");
        pane2.txtuser.setText("");
        pane2.txtpassw.setText("");
//        pane2.cbxtipodocumento.setSelectedIndex(0);
        pane2.cbxtipodeempleado.setSelectedIndex(0);

    }

    public void actualizaritem(String[] lista) {
        pane2.cbxtipodeempleado.removeAllItems();
        for (int i = 0; i < lista.length; i++) {
            pane2.cbxtipodeempleado.addItem(lista[i]);

        }
    }

    public boolean validarUsuario(String usuario) {
        boolean validar = false;
        for (int i = 0; i < pane1.tabla.getRowCount(); i++) {
            if (usuario.equals(pane1.tabla.getValueAt(i, 9))) {
                validar = true;
            }
        }
        return validar;
    }

    @Override
    public void guardar() {
        if (pane2.txtnombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un Nombre para el Empleado", "Campo en blanco", JOptionPane.ERROR_MESSAGE);
            pane2.txtnombre.requestFocus();
            pane2.txtnombre.setBackground(Color.yellow);
            return;
        }
        if (pane2.txtapellidop.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un Apellido paterno para el Empleado", "Campo en blanco", JOptionPane.ERROR_MESSAGE);
            pane2.txtapellidop.requestFocus();
            pane2.txtapellidop.setBackground(Color.yellow);
            return;
        }
        if (pane2.txtapellidom.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un Apellido materno para el Empleado", "Campo en blanco", JOptionPane.ERROR_MESSAGE);
            pane2.txtapellidom.requestFocus();
            pane2.txtapellidom.setBackground(Color.yellow);
            return;
        }
        if (pane2.txtdocumento.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un Numero de DNI para el Empleado", "Campo en blanco", JOptionPane.ERROR_MESSAGE);
            pane2.txtdocumento.requestFocus();
            pane2.txtdocumento.setBackground(Color.yellow);
            return;
        }

        //verificar dni
        if (pane2.txtdocumento.getText().length() != 8) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un Numero de DNI Valido", "Campo en Invalido", JOptionPane.ERROR_MESSAGE);
            pane2.txtdocumento.requestFocus();
            pane2.txtdocumento.setBackground(Color.yellow);
            return;
        }
        if (pane2.txtuser.getText().length() <= 4) {
            JOptionPane.showMessageDialog(null, "El nombre de usuario debe tener minimo 4 caracteres", "Campo en Invalido", JOptionPane.ERROR_MESSAGE);
            pane2.txtuser.requestFocus();
            pane2.txtuser.setBackground(Color.yellow);
            return;
        }
        if (validarUsuario(pane2.txtuser.getText())) {
            JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe", "Error", JOptionPane.ERROR_MESSAGE);
            pane2.txtuser.requestFocus();
            pane2.txtuser.setBackground(Color.yellow);
            return;
        }
        if (pane2.txtpassw.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una contraseña  para el Poder registrarse", "Campo en blanco", JOptionPane.ERROR_MESSAGE);
            pane2.txtpassw.requestFocus();
            pane2.txtpassw.setBackground(Color.yellow);
            return;
        }
        DAOManagerSQL manager = null;
        try {
            manager = new DAOManagerSQL("localhost", "basefarmacia", "root", "");
            persona p;
            String nombre = pane2.txtnombre.getText();
            String appaterno = pane2.txtapellidop.getText();
            String apmaterno = pane2.txtapellidom.getText();
            String dni = pane2.txtdocumento.getText();
            char[] numerodni = dni.toCharArray();
            int personaedad = Integer.parseInt(pane2.txtedad.getText());
            String direccion = pane2.txtdireccion.getText();
            String telefono = pane2.txttelefono.getText();
            p = new persona(nombre, appaterno, apmaterno, numerodni, personaedad, direccion, telefono);

            empleado emp;
            Long tipousuario = new Long(calculosTipousuario.obtenerIdtipousuario((String) pane2.cbxtipodeempleado.getSelectedItem()));
            Date time = (Date) pane2.fecharegistro.getDate();
            String usuario = pane2.txtuser.getText();
            String contraseña_encrip = cryptMD5(pane2.txtpassw.getText());
            if (tipousuario == null) {
                JOptionPane.showMessageDialog(null, "algo ocurrio mal");

            }
            emp = new empleado(0L, tipousuario, usuario, contraseña_encrip, time);

            manager.getEmpleadoDAO().insertarNuevo(p, emp);
            manager.cerrarConexion();
            pane1.actualizartabla();
            //mensaje de exito
            JOptionPane.showMessageDialog(null, "Se Registro el Empleado satisfactoriamente", "Buen Trabajo ", JOptionPane.INFORMATION_MESSAGE);
            deshabilitar();
            pestañas.setEnabledAt(0, true);
            pestañas.setSelectedIndex(0);
        } catch (DAOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    @Override
    public void modificar() {
        DAOManagerSQL manager = null;
        try {
            manager = new DAOManagerSQL("localhost", "basefarmacia", "root", "");
            persona p;
            Long idp = new Long(pane2.txtidpersona.getText());
            Long ide = new Long(pane2.txtidempleado.getText());
            String nombre = pane2.txtnombre.getText();
            String appaterno = pane2.txtapellidop.getText();
            String apmaterno = pane2.txtapellidom.getText();
            String dni = pane2.txtdocumento.getText();
            char[] numerodni = dni.toCharArray();
            int personaedad = Integer.parseInt(pane2.txtedad.getText());
            String direccion = pane2.txtdireccion.getText();
            String telefono = pane2.txttelefono.getText();
            p = new persona(nombre, appaterno, apmaterno, numerodni, personaedad, direccion, telefono);
            p.setIdPersona(idp);

            empleado emp;
            Long tipousuario = calculosTipousuario.obtenerIdtipousuario((String) pane2.cbxtipodeempleado.getSelectedItem());
            Date time = (Date) pane2.fecharegistro.getDate();
            String usuario = pane2.txtuser.getText();
            String contraseña_encrip = cryptMD5(pane2.txtpassw.getText());
            if (tipousuario == null) {
                JOptionPane.showMessageDialog(null, "algo ocurrio mal");

            }
            emp = new empleado(0L, tipousuario, usuario, contraseña_encrip, time);
            emp.setIdpersona(idp);
            emp.setIdempleado(ide);
            manager.getEmpleadoDAO().modificar(emp);
            manager.getPersonaDAO().modificar(p);
            manager.cerrarConexion();
            pane1.actualizartabla();
            JOptionPane.showMessageDialog(null, "Se Editó el Empleado satisfactoriamente", "Buen Trabajo ", JOptionPane.INFORMATION_MESSAGE);
            deshabilitar();
            pestañas.setEnabledAt(0, true);
            pestañas.setSelectedIndex(0);
            pane1.control = true;
            pane1.txtBuscar.requestFocus();
            action = "nothing";
        } catch (DAOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    @Override
    public void eliminar() {
        DAOManagerSQL manager = null;
        try {
            manager = new DAOManagerSQL("localhost", "basefarmacia", "root", "");
            persona p;
            int fila = pane1.tabla.getSelectedRow();
            Long idp = new Long((long) pane1.tabla.getValueAt(fila, 1));
            Long ide = new Long((long) pane1.tabla.getValueAt(fila, 0));

            p = new persona();
            p.setIdPersona(idp);
            empleado emp;

            emp = new empleado();
            emp.setIdpersona(idp);
            emp.setIdempleado(ide);

            manager.getEmpleadoDAO().eliminar(emp);
            manager.getPersonaDAO().eliminar(p);
            manager.cerrarConexion();
            pane1.actualizartabla();
            pane1.tabla.clearSelection();
            jbEliminar.setEnabled(false);
            jbModificar.setEnabled(false);
            action = "nothing";
            pane1.txtBuscar.requestFocus();
        } catch (DAOException ex) {
            System.out.println(" errorr");

        }

    }
}
