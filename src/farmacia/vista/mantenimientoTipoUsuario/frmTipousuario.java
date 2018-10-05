/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista.mantenimientoTipoUsuario;

import farmacia.vista.mantenimientoProductos.*;
import farmacia.vista.mantenimientoCliente.*;
import farmacia.calculos.configuracionImagenes;
import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.mysql.DAOManagerSQL;
import farmacia.jdbc.modelado.tipotrabajador;
import static farmacia.vista.mantenimientoCliente.frmClientes.jbEliminar;
import static farmacia.vista.mantenimientoCliente.frmClientes.jbModificar;
import static farmacia.vista.mantenimientoCliente.frmClientes.jbNuevo;
import static farmacia.vista.mantenimientoCliente.frmClientes.jbSalir;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JInternalFrame;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author fecyp
 */
public class frmTipousuario extends JInternalFrame implements ActionListener, KeyListener {

    public JTabbedPane pestañas;
    public ListadoTipousuario pane1;
    public Registrar pane2;
    public static JButton jbNuevo, jbEliminar, jbGuardar, jbModificar, jbSalir, jbCancelar;
    JToolBar toolBar;
    private Color c = Color.white;
    Font fontboton = new Font("Geneva", 1, 14);
    configuracionImagenes config = new configuracionImagenes();
    public static String action = "nothing";

    public frmTipousuario() throws DAOException {
        super("Formulario Tipo de Usuario", false, true, false, true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        Iniciar_componentes();
        pestañas.setSelectedIndex(0);
        perzonalizacionfondocolor();
        deshabilitar();
        perzonalizartipoletra();
        personalizarboton();
        pack();
        pane1.actualizartabla();
        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                if (!action.equals("nothing")) {
                    JOptionPane.showMessageDialog(null, "Se esta ejecutando una Accion\n para cerrar la ventana debe cancelar o terminar con dicha acción", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    jbSalir.doClick();
                }
            }

        });

    }

    private JPanel getToolBar() {
//        JToolBar barraBotones = new JToolBar();
//        barraBotones.setBackground(c);
//        barraBotones.setFloatable(false);// deja estatica el JToolBar
//        barraBotones.addSeparator();// agrega lines divisoras entere los botones
        JPanel botones_principal = new JPanel(new BorderLayout());
        botones_principal.setBackground(c);
        JPanel botones = new JPanel(new GridLayout(6, 1));
        jbNuevo = new JButton("Nuevo", config.obtenerIcono("nuevo.png"));

        jbGuardar = new JButton("Guardar", config.obtenerIcono("guardar.png"));
        jbEliminar = new JButton("Eliminar", config.obtenerIcono("eliminar.png"));
        jbModificar = new JButton("Modificar", config.obtenerIcono("modificar.png"));
        jbCancelar = new JButton("Cancelar", config.obtenerIcono("cancelar.png"));
        botones.setBackground(c);
        jbSalir = new JButton("Salir", config.obtenerIcono("salir.png"));
        botones.add(jbNuevo);
        botones.add(jbGuardar);
        botones.add(jbEliminar);
        botones.add(jbModificar);
        botones.add(jbCancelar);
        botones.add(jbSalir);
        botones_principal.add(botones, BorderLayout.WEST);
        botones_principal.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createTitledBorder("Mantenimiento")));

//        barraBotones.add(botones_principal);
//        barraBotones.setOrientation(JToolBar.HORIZONTAL);
        return botones_principal;
    }

    public void perzonalizacionfondocolor() {
        jbNuevo.setBackground(c);
        jbGuardar.setBackground(c);
        jbCancelar.setBackground(c);
        jbEliminar.setBackground(c);
        jbSalir.setBackground(c);
        jbModificar.setBackground(c);
        pestañas.setBackground(c);
        pestañas.setBackgroundAt(0, c);
        pestañas.setBackgroundAt(1, c);
        this.setBackground(c);
    }

    public void perzonalizartipoletra() {
        jbNuevo.setFont(fontboton);
        jbGuardar.setFont(fontboton);
        jbCancelar.setFont(fontboton);
        jbEliminar.setFont(fontboton);
        jbSalir.setFont(fontboton);
        jbModificar.setFont(fontboton);
        setFont(fontboton);
        pestañas.setFont(fontboton);
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
                } catch (DAOException ex) {
                    System.out.println(" error" + ex.getMessage());

                }
                //mensaje de exito
                JOptionPane.showMessageDialog(null, "Se Registro el Tipo de trabajador satisfactoriamente", "Buen Trabajo ", JOptionPane.INFORMATION_MESSAGE);
                deshabilitar();
                pestañas.setEnabledAt(0, true);
                pestañas.setSelectedIndex(0);
            } else if (action.equals("modificar")) {
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
                } catch (DAOException ex) {
                    System.out.println(" error" + ex.getMessage());

                }

            }
            deshabilitar();
            pane1.control = true;
            pane1.txtBuscar.requestFocus();
            action = "nothing";

        } else if (source == jbEliminar) {
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
            } catch (DAOException ex) {
                System.out.println(" error" + ex.getMessage());

            }

            pane1.tabla.clearSelection();
            jbEliminar.setEnabled(false);
            jbModificar.setEnabled(false);
            action = "nothing";
            pane1.txtBuscar.requestFocus();

        } else if (source == jbSalir) {
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

    private void Iniciar_componentes() {
        pestañas = new JTabbedPane();
        pane1 = new ListadoTipousuario(this);
        pane2 = new Registrar(this);
        pestañas.add("Buscar", pane1);

        pestañas.add("Registrar", pane2);
        pestañas.setIconAt(0, config.obtenerIcono("buscar.png", 15));
        pestañas.setIconAt(1, config.obtenerIcono("nuevo.png", 15));
        pestañas.setSelectedIndex(1);
        JPanel principal = new JPanel();
        principal.setBackground(c);
        principal.add(pestañas, BorderLayout.CENTER);
        add(principal, BorderLayout.WEST);
        add(getToolBar(), BorderLayout.EAST);

        jbNuevo.setEnabled(true);
        jbGuardar.setEnabled(false);
        jbEliminar.setEnabled(false);
        jbSalir.setEnabled(true);
        jbModificar.setEnabled(false);
        jbCancelar.setEnabled(false);

        jbNuevo.addActionListener(this);
        jbGuardar.addActionListener(this);
        jbGuardar.addKeyListener(this);
        jbEliminar.addActionListener(this);
        jbSalir.addActionListener(this);
        jbModificar.addActionListener(this);

        pestañas.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {

            }
        });

        jbModificar.addActionListener(this);
        jbCancelar.addActionListener(this);
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

    public void personalizarboton() {

        jbNuevo.setHorizontalTextPosition(SwingConstants.CENTER);
        jbNuevo.setVerticalTextPosition(SwingConstants.BOTTOM);

        jbEliminar.setHorizontalTextPosition(SwingConstants.CENTER);
        jbEliminar.setVerticalTextPosition(SwingConstants.BOTTOM);

        jbGuardar.setHorizontalTextPosition(SwingConstants.CENTER);
        jbGuardar.setVerticalTextPosition(SwingConstants.BOTTOM);

        jbModificar.setHorizontalTextPosition(SwingConstants.CENTER);
        jbModificar.setVerticalTextPosition(SwingConstants.BOTTOM);

        jbSalir.setHorizontalTextPosition(SwingConstants.CENTER);
        jbSalir.setVerticalTextPosition(SwingConstants.BOTTOM);
        jbCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
        jbCancelar.setVerticalTextPosition(SwingConstants.BOTTOM);
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }

}
