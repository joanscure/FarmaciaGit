/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista.mantenimientoProductos;

import farmacia.diseño.estrategias.EstrategiaIFrame;
import farmacia.jdbc.dao.DAOException;
import farmacia.jdbc.dao.mysql.DAOManagerSQL;
import farmacia.jdbc.modelado.producto;
import farmacia.vista.frmpermiso;
import farmacia.vista.frmprincipal;
import static farmacia.vista.frmprincipal.visibleproductos;
import static farmacia.vista.mantenimientoProductos.ListadoProductos.tabla;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.RowFilter;

/**
 *
 * @author fecyp
 */
public class frmProducto extends EstrategiaIFrame implements ActionListener, KeyListener {

    public ListadoProductos pane1;
    public RegistrarProductos pane2;

    public frmProducto(String titulo) throws DAOException {
        super(titulo);
        pane1.actualizartabla();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == jbModificar) {
            habilitar();
            int fila = pane1.tabla.getSelectedRow();
            pane2.txtidproducto.setText((Long) pane1.tabla.getValueAt(fila, 0) + "");
            pane2.txtnombre.setText((String) pane1.tabla.getValueAt(fila, 1));
            pane2.txtdescripcion.setText((String) pane1.tabla.getValueAt(fila, 2));
            pane2.txtdosis.setText((String) pane1.tabla.getValueAt(fila, 3));
            pane2.txtprecioventa.setText((Double) pane1.tabla.getValueAt(fila, 4) + "");
            pane2.txtigv.setText((Double) pane1.tabla.getValueAt(fila, 5) + "");
            pane2.txtpreciofinal.setText((Double) pane1.tabla.getValueAt(fila, 6) + "");
            pane2.txtstock.setText((int) pane1.tabla.getValueAt(fila, 7) + "");
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
            action = "nothing";
            jbModificar.setEnabled(false);
            jbEliminar.setEnabled(false);
            pane1.txtBuscar.requestFocus();

        } else if (source == jbGuardar) {
            if (action.equals("nuevo")) {
                int confirmacion = JOptionPane.showConfirmDialog(rootPane, "¿Estas seguro que quieres Guardar el Producto?", "confirmar", 2);
                if (confirmacion == 0) {
                    guardar();
                }
            } else if (action.equals("modificar")) {
                int confirmacion = JOptionPane.showConfirmDialog(rootPane, "¿Estas seguro que quieres  Guardar la edicion del Producto?", "confirmar", 2);
                if (confirmacion == 0) {
                    modificar();

                }
            }
        } else if (source == jbEliminar) {
            int confirmacion = JOptionPane.showConfirmDialog(rootPane, "¿Estas seguro que quieres eliminar el Producto?", "confirmar", 2);
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
                dispose();
                visibleproductos = false;
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

        } else if (source == pane2.txtdescripcion) {
            pane2.txtdescripcion.transferFocus();

        } else if (source == pane2.txtdosis) {
            pane2.txtdosis.transferFocus();
        } else if (source == pane2.txtprecioventa) {
            pane2.txtstock.requestFocus();
        } else if (source == pane2.txtstock) {
            jbGuardar.doClick();
        } else if (source == pane2.txtigv) {
            pane2.txtigv.transferFocus();
        }
    }

    public void Iniciar_componentes(String titulo) {
        pestañas = new JTabbedPane();
        pane1 = new ListadoProductos(titulo);
        pane2 = new RegistrarProductos(titulo);
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
        pane2.txtnombre.setEnabled(true);
        pane2.txtdescripcion.setEnabled(true);
        pane2.txtdosis.setEnabled(true);
        pane2.txtigv.setEnabled(true);
        pane2.txtpreciofinal.setEnabled(true);
        pane2.txtprecioventa.setEnabled(true);
        pane2.txtstock.setEnabled(true);

        jbNuevo.setEnabled(true);
        jbEliminar.setEnabled(false);
        jbModificar.setEnabled(false);
        jbGuardar.setEnabled(true);
        jbSalir.setEnabled(true);
        jbCancelar.setEnabled(true);

        pane2.txtiddescuento.setText("");
        pane2.txtidproducto.setText("");
        pane2.txtnombre.setText("");
        pane2.txtdescripcion.setText("");
        pane2.txtdosis.setText("");
        pane2.txtigv.setText("");
        pane2.txtpreciofinal.setText("");
        pane2.txtprecioventa.setText("");
        pane2.txtstock.setText("");

    }

    public void deshabilitar() {
        pane2.txtnombre.setEnabled(false);
        pane2.txtdescripcion.setEnabled(false);
        pane2.txtdosis.setEnabled(false);
        pane2.txtigv.setEnabled(false);
        pane2.txtpreciofinal.setEnabled(false);
        pane2.txtprecioventa.setEnabled(false);
        pane2.txtstock.setEnabled(false);

        jbNuevo.setEnabled(true);
        jbGuardar.setEnabled(false);
        jbSalir.setEnabled(true);
        jbCancelar.setEnabled(false);
        pane2.txtiddescuento.setText("");
        pane2.txtidproducto.setText("");
        pane2.txtnombre.setText("");
        pane2.txtdescripcion.setText("");
        pane2.txtdosis.setText("");
        pane2.txtigv.setText("");
        pane2.txtpreciofinal.setText("");
        pane2.txtprecioventa.setText("");
        pane2.txtstock.setText("");

    }

    @Override
    public void guardar() {
        if (pane2.txtnombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un Nombre Del Producto", "Campo en blanco", JOptionPane.ERROR_MESSAGE);
            pane2.txtnombre.requestFocus();
            pane2.txtnombre.setBackground(Color.yellow);
            return;
        }
        if (pane2.txtdosis.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar La Dosis del producto", "Campo en blanco", JOptionPane.ERROR_MESSAGE);
            pane2.txtdosis.requestFocus();
            pane2.txtdosis.setBackground(Color.yellow);
            return;
        }
        if (pane2.txtpreciofinal.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar El Precio de venta del Producto", "Campo en blanco", JOptionPane.ERROR_MESSAGE);
            pane2.txtprecioventa.requestFocus();
            pane2.txtprecioventa.setBackground(Color.yellow);
            return;
        }
        if (pane2.txtstock.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar El Stock del Producto", "Campo en blanco", JOptionPane.ERROR_MESSAGE);
            pane2.txtstock.requestFocus();
            pane2.txtstock.setBackground(Color.yellow);
            return;
        }

        DAOManagerSQL manager = null;
        try {
            manager = new DAOManagerSQL("localhost", "basefarmacia", "root", "");
            String nombre = pane2.txtnombre.getText();
            String descripcion = pane2.txtdescripcion.getText();
            String dosis = pane2.txtdosis.getText();
            double precioventa = Double.parseDouble(pane2.txtprecioventa.getText());
            double igv = Double.parseDouble(pane2.txtigv.getText());
            double preciofinal = Double.parseDouble(pane2.txtpreciofinal.getText());
            int stock = Integer.parseInt(pane2.txtstock.getText());

            producto p = new producto(nombre, descripcion, dosis, precioventa, igv, preciofinal, stock);
            manager.getProductoDAO().insertar(p);
            manager.cerrarConexion();
            pane1.actualizartabla();
            JOptionPane.showMessageDialog(null, "Se Registro el Producto satisfactoriamente", "Buen Trabajo ", JOptionPane.INFORMATION_MESSAGE);
            deshabilitar();
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
            Long idproducto = new Long(pane2.txtidproducto.getText());
            String nombre = pane2.txtnombre.getText();
            String descripcion = pane2.txtdescripcion.getText();
            String dosis = pane2.txtdosis.getText();
            double precioventa = Double.parseDouble(pane2.txtprecioventa.getText());
            double igv = Double.parseDouble(pane2.txtigv.getText());
            double preciofinal = Double.parseDouble(pane2.txtpreciofinal.getText());
            int stock = Integer.parseInt(pane2.txtstock.getText());

            producto p = new producto(nombre, descripcion, dosis, precioventa, igv, preciofinal, stock);
            p.setIdproducto(idproducto);
            manager.getProductoDAO().modificar(p);
            manager.cerrarConexion();
            pane1.actualizartabla();
            JOptionPane.showMessageDialog(null, "Se Editó el Producto satisfactoriamente", "Buen Trabajo ", JOptionPane.INFORMATION_MESSAGE);
            pestañas.setEnabledAt(0, true);
            pestañas.setSelectedIndex(0);
            deshabilitar();
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
            int fila = pane1.tabla.getSelectedRow();
            Long idproducto = new Long((long) pane1.tabla.getValueAt(fila, 0));
            producto p = new producto();
            p.setIdproducto(idproducto);
            manager.getProductoDAO().eliminar(p);
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

        ke.getComponent().setBackground(Color.white);

        if (source == pane2.txtnombre || source == pane2.txtdosis || source == pane2.txtnombre || source == pane2.txtdescripcion) {
            char c = ke.getKeyChar();
            if (Character.isLowerCase(c)) {
                String cad = ("" + c).toUpperCase();
                c = cad.charAt(0);
                ke.setKeyChar(c);
            }
            if (((ke.getKeyChar() < 97 || ke.getKeyChar() > 122)) && (ke.getKeyChar() < 65 || ke.getKeyChar() > 90) && source != pane2.txtdosis && source != pane2.txtdescripcion) {
                ke.consume();
            }
        } else if (source == pane2.txtprecioventa) {
            if ((ke.getKeyChar() < 48 || ke.getKeyChar() > 57) && ((ke.getKeyChar() != 46) || pane2.controlpunto)) {

                ke.consume();

            }
        } else if (source == pane2.txtstock) {
            if ((ke.getKeyChar() < 48 || ke.getKeyChar() > 57)) {

                ke.consume();

            }
        }
        if (source == pane2.txtigv) {
            if ((ke.getKeyChar() < 48 || ke.getKeyChar() > 57) && ((ke.getKeyChar() != 46) || pane2.controlpuntoigv)) {

                ke.consume();

            }
        }

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        Object source = ke.getSource();
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            if (source == pane2.txtstock) {
                pane2.txtprecioventa.requestFocus();
                return;
            }
            if (source == pane2.txtnombre) {
                pane2.txtstock.requestFocus();
                return;
            }
            ke.getComponent().transferFocusBackward();
        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (source == pane2.txtstock) {
                pane2.txtnombre.requestFocus();
                return;
            }
            if (source == pane2.txtprecioventa) {
                pane2.txtstock.requestFocus();
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
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Object source = e.getSource();
        if (e.getKeyCode() == KeyEvent.VK_S && pane2.teclaunida) {
            jbGuardar.doClick();
            pane2.teclaunida = false;
        }
        if (source == pane2.txtprecioventa || source == pane2.txtigv) {
            int validacion = pane2.txtigv.getText().indexOf(".");
            if (validacion == -1) {
                pane2.controlpuntoigv = false;//deja poner el punto ya que dice que no encuentra punto
            } else {
                pane2.controlpuntoigv = true;//no lo deja poner el punto ya que dice que ya se encuentra un punto

            }
            validacion = pane2.txtprecioventa.getText().indexOf(".");
            if (validacion == -1) {
                pane2.controlpunto = false;//deja poner el punto ya que dice que no encuentra punto
            } else {
                pane2.controlpunto = true;//no lo deja poner el punto ya que dice que ya se encuentra un punto

            }
            //para que el igv no este vacio
            if (pane2.txtigv.getText().isEmpty()) {
                return;
            } else if (pane2.txtigv.getText().charAt(pane2.txtigv.getText().length() - 1) == '.') //para que el igv no quede en un punto
            {
                return;
            }

            if (pane2.txtprecioventa.getText().isEmpty()) {
                pane2.txtpreciofinal.setText("");
                return;
            } else if (pane2.txtprecioventa.getText().charAt(pane2.txtprecioventa.getText().length() - 1) == '.') {
                pane2.txtpreciofinal.setText("");
                return;
            }
            //validamos que solo halla un punto

            //
            if (pane2.txtprecioventa.getText().charAt(pane2.txtprecioventa.getText().length() - 1) == '.') {
                if (pane2.txtprecioventa.getText().length() == 1) {
                    return;
                }
            }

            double igv = (Double.parseDouble(pane2.txtigv.getText()) / 100) + 1;

            double preciototal = Double.parseDouble(pane2.txtprecioventa.getText()) * igv;
            BigDecimal bd = new BigDecimal(preciototal);
            bd = bd.setScale(2, RoundingMode.HALF_UP);

            pane2.txtpreciofinal.setText(bd.doubleValue() + "");
        }
        if (e.getKeyCode() == KeyEvent.VK_N && pane1.teclamas) {
           jbNuevo.doClick();
           pane1.teclamas = false;
        }

    }

    private void funcionregistrar() {
        pane2.txtnombre.addActionListener(this);
        pane2.txtdescripcion.addActionListener(this);
        pane2.txtdosis.addActionListener(this);
        pane2.txtprecioventa.addActionListener(this);
        pane2.txtidproducto.addActionListener(this);
        pane2.txtigv.addActionListener(this);
        pane2.txtiddescuento.addActionListener(this);
        pane2.txtpreciofinal.addActionListener(this);
        pane2.txtstock.addActionListener(this);

        pane2.txtnombre.addKeyListener(this);
        pane2.txtdescripcion.addKeyListener(this);
        pane2.txtdosis.addKeyListener(this);
        pane2.txtprecioventa.addKeyListener(this);
        pane2.txtidproducto.addKeyListener(this);
        pane2.txtigv.addKeyListener(this);
        pane2.txtiddescuento.addKeyListener(this);
        pane2.txtpreciofinal.addKeyListener(this);
        pane2.txtstock.addKeyListener(this);
    }

    private void funcionlistado() {
        tabla.addKeyListener(this);
        pane1.txtBuscar.addKeyListener(this);
        pane1.buscarPor.addKeyListener(this);
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if ( pane1.control) {
                jbEliminar.setEnabled(true);
                jbModificar.setEnabled(true);
            }
        }
        );
    }

}
