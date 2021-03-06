/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista.mantenimientoEmpresa;

import farmacia.diseño.estrategias.EstrategiaIFrame;
import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.mysql.DAOManagerSQL;
import farmacia.jdbc.modelado.empresa;
import farmacia.jdbc.modelado.empresacliente;
import farmacia.vista.frmpermiso;
import farmacia.vista.frmprincipal;
import static farmacia.vista.mantenimientoEmpresa.ListadoEmpresa.tabla;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.RowFilter;

/**
 *
 * @author fecyp
 */
public class frmEmpresa extends EstrategiaIFrame implements ActionListener, KeyListener {

    public ListadoEmpresa pane1;
    public RegistrarEmpresa pane2;

    public frmEmpresa(String titulo) throws DAOException {
        super(titulo);
        pane1.actualizartabla();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == jbModificar) {
            habilitar();
            int fila = pane1.tabla.getSelectedRow();
            pane2.txtidclienteempresa.setText((Long) pane1.tabla.getValueAt(fila, 0) + "");
            pane2.txtidempresa.setText((Long) pane1.tabla.getValueAt(fila, 1) + "");
            pane2.txtnombre.setText((String) pane1.tabla.getValueAt(fila, 2));
            pane2.txtdocumento.setText((String) pane1.tabla.getValueAt(fila, 3));
            pane2.txtdireccion.setText((String) pane1.tabla.getValueAt(fila, 4));
            pane2.txttelefono.setText((String) pane1.tabla.getValueAt(fila, 5));

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
                int confirmacion = JOptionPane.showConfirmDialog(rootPane, "¿Estas seguro que quieres Guardar a la Empresa?", "confirmar", 2);
                if (confirmacion == 0) {
                    guardar();
                }
            } else if (action.equals("modificar")) {
                int confirmacion = JOptionPane.showConfirmDialog(rootPane, "¿Estas seguro que quieres  Guardar la edicion de la Empresa?", "confirmar", 2);
                if (confirmacion == 0) {
                    modificar();
                }

            }
        } else if (source == jbEliminar) {
            int confirmacion = JOptionPane.showConfirmDialog(rootPane, "¿Estas seguro que quieres eliminar la Empresa?", "confirmar", 2);
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
                frmprincipal.visibleempresa=false;

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
        if (source == pane2.txtnombre) {
            pane2.txtnombre.transferFocus();

        } else if (source == pane2.txtdocumento) {
            pane2.txtdocumento.transferFocus();
        } else if (source == pane2.txtdireccion) {
            pane2.txttelefono.requestFocus();
        } else if (source == pane2.txttelefono) {
            jbGuardar.doClick();
        }
        else if(source==pane1.bnreport)
        {
            pane1.generarReporte();
        }
    }

    public void Iniciar_componentes(String titulo) {
        pestañas = new JTabbedPane();
        pane1 = new ListadoEmpresa(titulo);
        pane2 = new RegistrarEmpresa(titulo);
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
        pane1.bnreport.addActionListener(this);

        jbModificar.addActionListener(this);
        jbCancelar.addActionListener(this);
        funcionregistrar();
        funcionlistado();
    }

    public void habilitar() {
        pane2.txtnombre.setEnabled(true);
        pane2.txtdocumento.setEnabled(true);
        pane2.txtdireccion.setEnabled(true);
        pane2.fecharegistro.setEnabled(true);
        pane2.txttelefono.setEnabled(true);
//        pane2.cbxtipodocumento.setEnabled(true);

        jbNuevo.setEnabled(true);
        jbEliminar.setEnabled(false);
        jbModificar.setEnabled(false);
        jbGuardar.setEnabled(true);
        jbSalir.setEnabled(true);
        jbCancelar.setEnabled(true);

        pane2.txtidempresa.setText("");
        pane2.txtidclienteempresa.setText("");
        pane2.txtnombre.setText("");
        pane2.txtdocumento.setText("");
        pane2.txtdireccion.setText("");
        pane2.txttelefono.setText("");
//        pane2.cbxtipodocumento.setSelectedIndex(0);

    }

    public void deshabilitar() {
        pane2.txtnombre.setEnabled(false);
        pane2.txtdocumento.setEnabled(false);
        pane2.txtdireccion.setEnabled(false);
        pane2.fecharegistro.setEnabled(false);
        pane2.txttelefono.setEnabled(false);
//        pane2.cbxtipodocumento.setEnabled(false);

        jbNuevo.setEnabled(true);
        jbGuardar.setEnabled(false);
        jbSalir.setEnabled(true);
        jbCancelar.setEnabled(false);

        pane2.txtidempresa.setText("");
        pane2.txtidclienteempresa.setText("");
        pane2.txtnombre.setText("");
        pane2.txtdocumento.setText("");
        pane2.txtdireccion.setText("");
        pane2.txttelefono.setText("");
//        pane2.cbxtipodocumento.setSelectedIndex(0);

    }

    @Override
    public void guardar() {
        if (pane2.txtnombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una Razon Social para la Empresa", "Campo en blanco", JOptionPane.ERROR_MESSAGE);
            pane2.txtnombre.requestFocus();
            pane2.txtnombre.setBackground(Color.yellow);
            return;
        }

        if (pane2.txtdocumento.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un Numero de RUC para la Empresa", "Campo en blanco", JOptionPane.ERROR_MESSAGE);
            pane2.txtdocumento.requestFocus();
            pane2.txtdocumento.setBackground(Color.yellow);
            return;
        }
        //verificar dni
        if (pane2.txtdocumento.getText().length() != 11) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un Numero de RUC Valido", "Campo en blanco", JOptionPane.ERROR_MESSAGE);
            pane2.txtdocumento.requestFocus();
            pane2.txtdocumento.setBackground(Color.yellow);
            return;
        }
        DAOManagerSQL manager = null;
        try {
            manager = new DAOManagerSQL("localhost", "basefarmacia", "root", "");
            empresa emp;
            String nombre = pane2.txtnombre.getText();
            String documento = pane2.txtdocumento.getText();
            String direccion = pane2.txtdireccion.getText();
            String telefono = pane2.txttelefono.getText();
            char[] numeroruc = documento.toCharArray();
            Date time = pane2.fecharegistro.getDate();

            emp = new empresa(numeroruc, nombre, telefono, direccion);

            empresacliente cliente;

            cliente = new empresacliente(0L, time);

            manager.getEmpresaClienteDAO().insertarNuevo(cliente, emp);
            manager.cerrarConexion();
            pane1.actualizartabla();
            JOptionPane.showMessageDialog(null, "Se Registro el Cliente satisfactoriamente", "Buen Trabajo ", JOptionPane.INFORMATION_MESSAGE);
            deshabilitar();
            pestañas.setEnabledAt(0, true);
            pestañas.setSelectedIndex(0);
            action="nothing";
        } catch (DAOException ex) {
            System.out.println(" errorr" + ex.getMessage());

        }
    }

    @Override
    public void modificar() {
        DAOManagerSQL manager = null;
        try {
            manager = new DAOManagerSQL("localhost", "basefarmacia", "root", "");
            empresa emp;
            Long ide = new Long(pane2.txtidempresa.getText());
            Long idc = new Long(pane2.txtidclienteempresa.getText());
            String nombre = pane2.txtnombre.getText();
            String documento = pane2.txtdocumento.getText();
            String direccion = pane2.txtdireccion.getText();
            String telefono = pane2.txttelefono.getText();
            char[] numeroruc = documento.toCharArray();
            Date time = (Date) pane2.fecharegistro.getDate();

            emp = new empresa(numeroruc, nombre, telefono, direccion);
            emp.setIdempresa(ide);
            empresacliente cliente;

            cliente = new empresacliente(0L, time);
            cliente.setIdempresa(ide);
            cliente.setIdempresacliente(idc);
            manager.getEmpresaClienteDAO().modificar(cliente);
            manager.getEmpresaDAO().modificar(emp);
            manager.cerrarConexion();
            pane1.actualizartabla();
            JOptionPane.showMessageDialog(null, "Se Editó el Cliente satisfactoriamente", "Buen Trabajo ", JOptionPane.INFORMATION_MESSAGE);
            deshabilitar();
            jbEliminar.setEnabled(false);
            jbModificar.setEnabled(false);
            tabla.clearSelection();
            pestañas.setEnabledAt(0, true);
            pestañas.setSelectedIndex(0);
            pane1.control = true;
            pane1.txtBuscar.requestFocus();
            action = "nothing";
        } catch (DAOException ex) {
            System.out.println(" errorr");

        }
    }

    @Override
    public void eliminar() {
        DAOManagerSQL manager = null;
        try {
            manager = new DAOManagerSQL("localhost", "basefarmacia", "root", "");
            empresa emp;
            int fila = pane1.tabla.getSelectedRow();
            Long ide = new Long((long) pane1.tabla.getValueAt(fila, 1));
            Long idc = new Long((long) pane1.tabla.getValueAt(fila, 0));

            emp = new empresa();
            emp.setIdempresa(ide);
            empresacliente cliente;

            cliente = new empresacliente();
            cliente.setIdempresa(ide);
            cliente.setIdempresacliente(idc);

            manager.getEmpresaClienteDAO().eliminar(cliente);
            manager.getEmpresaDAO().eliminar(emp);
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

    @Override
    public void keyTyped(KeyEvent ke) {
        Object source = ke.getSource();
//        if (source != cbxtipodocumento) {
        ke.getComponent().setBackground(Color.white);
//        }

        if (source == pane2.txtnombre || source == pane2.txtdireccion) {
            char c = ke.getKeyChar();
            if (Character.isLowerCase(c)) {
                String cad = ("" + c).toUpperCase();
                c = cad.charAt(0);
                ke.setKeyChar(c);
            }
            if (((ke.getKeyChar() < 97 || ke.getKeyChar() > 122)) && (ke.getKeyChar() < 65 || ke.getKeyChar() > 90) && source != pane2.txtdireccion) {
                ke.consume();
            }
        } else if (source == pane2.txttelefono || source == pane2.txtdocumento) {
            if (ke.getKeyChar() < 48 || ke.getKeyChar() > 57) {
                ke.consume();
            }
            if (source == pane2.txtdocumento) {
                if (pane2.txtdocumento.getText().length() >= 11) {
                    ke.consume();
                }
            }
            if (source == pane2.txttelefono) {
                if (pane2.txttelefono.getText().length() >= 9) {
                    ke.consume();
                }
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        Object source = ke.getSource();
//        if (source == cbxtipodocumento) {
//            if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
//                cbxtipodocumento.setPopupVisible(false);
//                cbxtipodocumento.transferFocus();
//            }
//        }
        if (source == tabla) {
            if (tabla.getSelectedRow() == -1) {
                return;
            }
            if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
//                
                jbModificar.doClick();

            } else if (ke.getKeyCode() == KeyEvent.VK_DELETE) {
                jbEliminar.doClick();
            }
        } else if (source == pane1.txtBuscar) {
            if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
                pane1.buscarPor.setPopupVisible(true);
                pane1.buscarPor.requestFocus();
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
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            if (source == pane2.txttelefono) {
                pane2.txtdireccion.requestFocus();
                return;
            }
            ke.getComponent().transferFocusBackward();
        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (source == pane2.txttelefono) {
                pane2.txtnombre.requestFocus();
                return;
            }
            if (source == pane2.txtdireccion) {
                pane2.txttelefono.requestFocus();
                return;
            }
            ke.getComponent().transferFocus();
        }
        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
            jbCancelar.doClick();
            jbSalir.doClick();
        }
        if (ke.getExtendedKeyCode() == KeyEvent.VK_CONTROL) {
            pane2.teclaunida = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_S && pane2.teclaunida) {
            jbGuardar.doClick();
            pane2.teclaunida = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_N && pane1.teclamas) {
            jbNuevo.doClick();
            pane1.teclamas = false;
        }
    }

    private void funcionregistrar() {
        pane2.txtnombre.addActionListener(this);
        pane2.txtdocumento.addActionListener(this);
        pane2.txttelefono.addActionListener(this);
        pane2.txtdireccion.addActionListener(this);

        pane2.txtnombre.addKeyListener(this);
        pane2.txtdocumento.addKeyListener(this);
        pane2.txttelefono.addKeyListener(this);
        pane2.txtdireccion.addKeyListener(this);
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
