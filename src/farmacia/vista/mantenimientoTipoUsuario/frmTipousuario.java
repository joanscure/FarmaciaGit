/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista.mantenimientoTipoUsuario;

import farmacia.diseño.estrategias.EstrategiaIFrame;
import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.mysql.DAOManagerSQL;
import farmacia.jdbc.modelado.tipotrabajador;
import farmacia.vista.frmpermiso;
import farmacia.vista.frmprincipal;
import static farmacia.vista.mantenimientoTipoUsuario.ListadoTipousuario.tabla;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.RowFilter;

/**
 *
 * @author fecyp
 */
public class frmTipousuario extends EstrategiaIFrame implements ActionListener, KeyListener, MouseListener {

    public ListadoTipousuario pane1;
    public RegistrarTipoUsuario pane2;

    public frmTipousuario(String titulo) throws DAOException {
        super(titulo);
        pane1.actualizartabla();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == jbModificar) {
            habilitar();
            int fila = pane1.tabla.getSelectedRow();
            pane2.txtidtipouser.setText((Long) pane1.tabla.getValueAt(fila, 0) + "");
            pane2.txtdescripcion.setText((String) pane1.tabla.getValueAt(fila, 1));

            pane2.aventas.setSelected((boolean) pane1.tabla.getValueAt(fila, 2));
            pane2.aproductos.setSelected((boolean) pane1.tabla.getValueAt(fila, 3));
            pane2.aclientes.setSelected((boolean) pane1.tabla.getValueAt(fila, 4));
            pane2.aconsultas.setSelected((boolean) pane1.tabla.getValueAt(fila, 5));
            pane2.aempleados.setSelected((boolean) pane1.tabla.getValueAt(fila, 6));
            pane2.atiposusuario.setSelected((boolean) pane1.tabla.getValueAt(fila, 7));
            pane2.acambioclave.setSelected((boolean) pane1.tabla.getValueAt(fila, 8));
            pane2.aanularventas.setSelected((boolean) pane1.tabla.getValueAt(fila, 9));
            pane2.aeliminarproducto.setSelected((boolean) pane1.tabla.getValueAt(fila, 10));
            pane2.aelmininartipotrabajor.setSelected((boolean) pane1.tabla.getValueAt(fila, 11));
            pane2.aeliminarusuario.setSelected((boolean) pane1.tabla.getValueAt(fila, 12));
            pane2.aeliminarclientes.setSelected((boolean) pane1.tabla.getValueAt(fila, 13));

            action = "modificar";
            pestañas.setSelectedIndex(1);
            pane2.txtdescripcion.requestFocus();
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
            action = "nothing";
            jbModificar.setEnabled(false);
            jbEliminar.setEnabled(false);
            pane1.txtBuscar.requestFocus();

        } else if (source == jbGuardar) {
            if (action.equals("nuevo")) {
                int confirmacion = JOptionPane.showConfirmDialog(rootPane, "¿Estas seguro que quieres Guardar el tipo de Usuario?", "confirmar", 2);
                if (confirmacion == 0) {
                    guardar();
                }
            } else if (action.equals("modificar")) {
                int confirmacion = JOptionPane.showConfirmDialog(rootPane, "¿Estas seguro que quieres  Guardar la edicion del Tipo de Usuario?", "confirmar", 2);
                if (confirmacion == 0) {
                    modificar();
                }

            }
        } else if (source == jbEliminar) {
            int confirmacion = JOptionPane.showConfirmDialog(rootPane, "¿Estas seguro que quieres eliminar el Tipo de usuario?", "confirmar", 2);
            if (confirmacion == 0) {
                if (!permisoeliminar) {
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

                setVisible(false);
                frmprincipal.marchivo.requestFocus();
            }
        } else if (source == jbNuevo) {
            habilitar();
            action = "nuevo";
            pane1.control = true;
            pestañas.setSelectedIndex(1);
            pane2.txtdescripcion.requestFocus();
            pestañas.setEnabledAt(0, false);
            jbSalir.setEnabled(false);
            jbNuevo.setEnabled(false);
        }
    }

    public void Iniciar_componentes(String titulo) {
        pestañas = new JTabbedPane();
        pane1 = new ListadoTipousuario(titulo);
        pane2 = new RegistrarTipoUsuario(titulo);
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
        funcionregistrar();
        funcionlistado();
    }

    public void habilitar() {
        pane2.txtdescripcion.setEnabled(true);
        pane2.aventas.setEnabled(true);
        pane2.aproductos.setEnabled(true);
        pane2.aclientes.setEnabled(true);
        pane2.aconsultas.setEnabled(true);
        pane2.aempleados.setEnabled(true);
        pane2.atiposusuario.setEnabled(true);
        pane2.acambioclave.setEnabled(true);
        pane2.aanularventas.setEnabled(true);
        pane2.aeliminarproducto.setEnabled(false);
        pane2.aelmininartipotrabajor.setEnabled(false);
        pane2.aeliminarusuario.setEnabled(false);
        pane2.aeliminarclientes.setEnabled(false);
        jbNuevo.setEnabled(true);
        jbEliminar.setEnabled(false);
        jbModificar.setEnabled(false);
        jbGuardar.setEnabled(true);
        jbSalir.setEnabled(true);
        jbCancelar.setEnabled(true);

        pane2.txtdescripcion.setText("");
        pane2.txtidtipouser.setText("");
        pane2.aventas.setSelected(false);
        pane2.aproductos.setSelected(false);
        pane2.aclientes.setSelected(false);
        pane2.aconsultas.setSelected(false);
        pane2.aempleados.setSelected(false);
        pane2.atiposusuario.setSelected(false);
        pane2.acambioclave.setSelected(false);
        pane2.aanularventas.setSelected(false);
        pane2.aeliminarproducto.setSelected(false);
        pane2.aelmininartipotrabajor.setSelected(false);
        pane2.aeliminarusuario.setSelected(false);
        pane2.aeliminarclientes.setSelected(false);

    }

    public void deshabilitar() {
        pane2.txtdescripcion.setEnabled(false);
        pane2.aventas.setEnabled(false);
        pane2.aproductos.setEnabled(false);
        pane2.aclientes.setEnabled(false);
        pane2.aconsultas.setEnabled(false);
        pane2.aempleados.setEnabled(false);
        pane2.atiposusuario.setEnabled(false);
        pane2.acambioclave.setEnabled(false);
        pane2.aanularventas.setEnabled(false);
        pane2.aeliminarproducto.setEnabled(false);
        pane2.aelmininartipotrabajor.setEnabled(false);
        pane2.aeliminarusuario.setEnabled(false);
        pane2.aeliminarclientes.setEnabled(false);

        jbNuevo.setEnabled(true);
        jbGuardar.setEnabled(false);
        jbSalir.setEnabled(true);
        jbCancelar.setEnabled(false);

        pane2.txtdescripcion.setText("");
        pane2.txtidtipouser.setText("");
        pane2.aventas.setSelected(false);
        pane2.aproductos.setSelected(false);
        pane2.aclientes.setSelected(false);
        pane2.aconsultas.setSelected(false);
        pane2.aempleados.setSelected(false);
        pane2.atiposusuario.setSelected(false);
        pane2.acambioclave.setSelected(false);
        pane2.aanularventas.setSelected(false);
        pane2.aeliminarproducto.setSelected(false);
        pane2.aelmininartipotrabajor.setSelected(false);
        pane2.aeliminarusuario.setSelected(false);
        pane2.aeliminarclientes.setSelected(false);

    }

    @Override
    public void guardar() {
        if (pane2.txtdescripcion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una descripcion para el tipo de usuario", "Campo en blanco", JOptionPane.ERROR_MESSAGE);
            pane2.txtdescripcion.requestFocus();
            pane2.txtdescripcion.setBackground(Color.yellow);
            return;
        }
        if (!pane2.verificarselected()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar al menos un permiso para el tipo de trabajador", "Campo en blanco", JOptionPane.ERROR_MESSAGE);
            pane2.aventas.requestFocus();

            return;
        }

        DAOManagerSQL manager = null;
        try {
            manager = new DAOManagerSQL("localhost", "basefarmacia", "root", "");
            String descripcion = pane2.txtdescripcion.getText();
            boolean aventas = pane2.aventas.isSelected();
            boolean aproductos = pane2.aproductos.isSelected();
            boolean aclientes = pane2.aclientes.isSelected();
            boolean aconsultas = pane2.aconsultas.isSelected();
            boolean ampleados = pane2.aempleados.isSelected();
            boolean atiposusuario = pane2.atiposusuario.isSelected();
            boolean acambioclave = pane2.acambioclave.isSelected();
            boolean aanularventas = pane2.aanularventas.isSelected();
            boolean aeliminarproducto = pane2.aeliminarproducto.isSelected();
            boolean aelmininartipotrabajor = pane2.aelmininartipotrabajor.isSelected();
            boolean aeliminarusuario = pane2.aeliminarusuario.isSelected();
            boolean aeliminarclientes = pane2.aeliminarclientes.isSelected();

            tipotrabajador t = new tipotrabajador(descripcion, aventas, aproductos, aclientes, aconsultas, ampleados, atiposusuario, acambioclave, aanularventas, aeliminarproducto, aeliminarclientes, aeliminarusuario, aelmininartipotrabajor);
            manager.getTipoTrabajadorDAO().insertar(t);
            manager.cerrarConexion();
            pane1.actualizartabla();
            JOptionPane.showMessageDialog(null, "Se Registro el Tipo de trabajador satisfactoriamente", "Buen Trabajo ", JOptionPane.INFORMATION_MESSAGE);
            deshabilitar();
            jbEliminar.setEnabled(false);
            jbModificar.setEnabled(false);
            tabla.clearSelection();
             action = "nothing";
            pestañas.setEnabledAt(0, true);
            pestañas.setSelectedIndex(0);
        } catch (DAOException ex) {
            System.out.println(" error" + ex.getMessage());

        }
    }

    @Override
    public void modificar() {
        DAOManagerSQL manager = null;
        try {
            manager = new DAOManagerSQL("localhost", "basefarmacia", "root", "");
            String descripcion = pane2.txtdescripcion.getText();
            boolean aventas = pane2.aventas.isSelected();
            boolean aproductos = pane2.aproductos.isSelected();
            boolean aclientes = pane2.aclientes.isSelected();
            boolean aconsultas = pane2.aconsultas.isSelected();
            boolean ampleados = pane2.aempleados.isSelected();
            boolean atiposusuario = pane2.atiposusuario.isSelected();
            boolean acambioclave = pane2.acambioclave.isSelected();
            boolean aanularventas = pane2.aanularventas.isSelected();
            boolean aeliminarproducto = pane2.aeliminarproducto.isSelected();
            boolean aelmininartipotrabajor = pane2.aelmininartipotrabajor.isSelected();
            boolean aeliminarusuario = pane2.aeliminarusuario.isSelected();
            boolean aeliminarclientes = pane2.aeliminarclientes.isSelected();

            tipotrabajador t = new tipotrabajador(descripcion, aventas, aproductos, aclientes, aconsultas, ampleados, atiposusuario, acambioclave, aanularventas, aeliminarproducto, aeliminarclientes, aeliminarusuario, aelmininartipotrabajor);
            t.setIdtipotrabajador(new Long(pane2.txtidtipouser.getText()));
            manager.getTipoTrabajadorDAO().modificar(t);

            manager.cerrarConexion();
            pane1.actualizartabla();
            JOptionPane.showMessageDialog(null, "Se Editó el Tipo de trabajador satisfactoriamente", "Buen Trabajo ", JOptionPane.INFORMATION_MESSAGE);
            deshabilitar();
            pestañas.setEnabledAt(0, true);
            pestañas.setSelectedIndex(0);
            pane1.control = true;
            pane1.txtBuscar.requestFocus();
            action = "nothing";
            jbEliminar.setEnabled(false);
            jbModificar.setEnabled(false);
        } catch (DAOException ex) {
            System.out.println(" error" + ex.getMessage());

        }
    }

    @Override
    public void eliminar() {
        DAOManagerSQL manager = null;
        try {
            manager = new DAOManagerSQL("localhost", "basefarmacia", "root", "");
            int fila = pane1.tabla.getSelectedRow();
            Long idtipo = new Long((Long) pane1.tabla.getValueAt(fila, 0));
            tipotrabajador t = new tipotrabajador();
            t.setIdtipotrabajador(idtipo);
            manager.getTipoTrabajadorDAO().eliminar(t);

            manager.cerrarConexion();
            pane1.actualizartabla();
            pane1.tabla.clearSelection();
            jbEliminar.setEnabled(false);
            jbModificar.setEnabled(false);
            action = "nothing";
            pane1.txtBuscar.requestFocus();
        } catch (DAOException ex) {
            System.out.println(" error" + ex.getMessage());

        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        Object source = ke.getSource();

        if (source == pane2.txtdescripcion) {
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
        if (source != pane2.txtdescripcion) {
            if (ke.getKeyCode() == KeyEvent.VK_LEFT) {

                ke.getComponent().transferFocusBackward();
            }
            if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                if (source == pane2.aelmininartipotrabajor) {
                    pane2.aventas.requestFocus();
                    return;
                }
                ke.getComponent().transferFocus();
            }
            if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                JCheckBox cheaux = (JCheckBox) ke.getComponent();
                cheaux.setSelected(!cheaux.isSelected());

            }
        } else {
            pane2.txtdescripcion.setBackground(Color.white);
        }
        if (source == pane2.aclientes) {
            if (pane2.aclientes.isSelected()) {
                pane2.aeliminarclientes.setEnabled(true);

            } else {
                pane2.aeliminarclientes.setEnabled(false);
                pane2.aeliminarclientes.setSelected(false);
                return;

            }
        } else if (source == pane2.aproductos) {
            if (pane2.aproductos.isSelected()) {
                pane2.aeliminarproducto.setEnabled(true);

            } else {
                pane2.aeliminarproducto.setEnabled(false);
                pane2.aeliminarproducto.setSelected(false);
            }
        } else if (source == pane2.aempleados) {
            if (pane2.aempleados.isSelected()) {
                pane2.aeliminarusuario.setEnabled(true);
            } else {
                pane2.aeliminarusuario.setEnabled(false);
                pane2.aeliminarusuario.setSelected(false);
            }
        } else if (source == pane2.atiposusuario) {
            if (pane2.atiposusuario.isSelected()) {
                pane2.aelmininartipotrabajor.setEnabled(true);
            } else {
                pane2.aelmininartipotrabajor.setEnabled(false);
                pane2.aelmininartipotrabajor.setSelected(false);
            }
        }
        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
            jbCancelar.doClick();
            jbSalir.doClick();

        }

        if (ke.getExtendedKeyCode() == KeyEvent.VK_CONTROL) {
            pane2.teclaunida = true;
        }
         if (source == tabla) {
            if (tabla.getSelectedRow() == -1) {
                return;
            }
            if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
//                int index = tabla.getSelectedRow();
//                if (index == 0) {
//                    index = tabla.getRowCount();
//                }
//                index--;
//                control = false;
//                tabla.changeSelection(index, 0, false, false);
//                //se pasa el index como parametro o se usa el selected
////            control = true;
                jbModificar.doClick();

            } else if (ke.getKeyCode() == KeyEvent.VK_DELETE) {
                jbEliminar.doClick();
            }
        } else if (source == pane1.txtBuscar) {
            if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
               pane1. buscarPor.setPopupVisible(true);
               pane1. buscarPor.requestFocus();
            }

        } else if (source == pane1.buscarPor) {
            if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                pane1.buscarPor.setPopupVisible(false);
                pane1.buscarPor.transferFocus();
            }
        }
        

        if (ke.getExtendedKeyCode() == KeyEvent.VK_CONTROL) {
            pane1.teclamas = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_S && pane2.teclaunida) {
            jbGuardar.doClick();
            pane2.teclaunida = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_N &&pane1. teclamas) {
            jbNuevo.doClick();
           pane1. teclamas = false;
        }
    }

    private void funcionregistrar() {
        pane2.txtdescripcion.addKeyListener(this);
        pane2.aventas.addKeyListener(this);
        pane2.aproductos.addKeyListener(this);
        pane2.aclientes.addKeyListener(this);
        pane2.aconsultas.addKeyListener(this);
        pane2.aempleados.addKeyListener(this);
        pane2.atiposusuario.addKeyListener(this);
        pane2.acambioclave.addKeyListener(this);
        pane2.aanularventas.addKeyListener(this);
        pane2.aeliminarproducto.addKeyListener(this);
        pane2.aelmininartipotrabajor.addKeyListener(this);
        pane2.aeliminarusuario.addKeyListener(this);
        pane2.aeliminarclientes.addKeyListener(this);

        pane2.aventas.addMouseListener(this);
        pane2.aproductos.addMouseListener(this);
        pane2.aclientes.addMouseListener(this);
        pane2.aconsultas.addMouseListener(this);
        pane2.aempleados.addMouseListener(this);
        pane2.atiposusuario.addMouseListener(this);
        pane2.acambioclave.addMouseListener(this);
        pane2.aanularventas.addMouseListener(this);
        pane2.aeliminarproducto.addMouseListener(this);
        pane2.aelmininartipotrabajor.addMouseListener(this);
        pane2.aeliminarusuario.addMouseListener(this);
        pane2.aeliminarclientes.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();

        if (source == pane2.aclientes) {
            if (pane2.aclientes.isSelected()) {
                pane2.aeliminarclientes.setEnabled(true);

            } else {
                pane2.aeliminarclientes.setEnabled(false);
                pane2.aeliminarclientes.setSelected(false);
                return;

            }
        } else if (source == pane2.aproductos) {
            if (pane2.aproductos.isSelected()) {
                pane2.aeliminarproducto.setEnabled(true);

            } else {
                pane2.aeliminarproducto.setEnabled(false);
                pane2.aeliminarproducto.setSelected(false);
            }
        } else if (source == pane2.aempleados) {
            if (pane2.aempleados.isSelected()) {
                pane2.aeliminarusuario.setEnabled(true);
            } else {
                pane2.aeliminarusuario.setEnabled(false);
                pane2.aeliminarusuario.setSelected(false);
            }
        } else if (source == pane2.atiposusuario) {
            if (pane2.atiposusuario.isSelected()) {
                pane2.aelmininartipotrabajor.setEnabled(true);
            } else {
                pane2.aelmininartipotrabajor.setEnabled(false);
                pane2.aelmininartipotrabajor.setSelected(false);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private void funcionlistado() {
          tabla.addKeyListener(this);
        pane1.txtBuscar.addKeyListener(this);
        pane1.buscarPor.addKeyListener(this);
         tabla.getSelectionModel().addListSelectionListener(e -> {
            if (pane1.control) {
                jbEliminar.setEnabled(true);
                jbModificar.setEnabled(true);
            }
        }
        );
    }
}
