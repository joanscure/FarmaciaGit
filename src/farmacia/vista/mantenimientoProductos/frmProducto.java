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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;

/**
 *
 * @author fecyp
 */
public class frmProducto extends EstrategiaIFrame implements ActionListener{

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
           pane2.txtidproducto.setText((Long) pane1.tabla.getValueAt(fila, 0)+"");
            pane2.txtnombre.setText((String) pane1.tabla.getValueAt(fila, 1));
            pane2.txtdescripcion.setText((String) pane1.tabla.getValueAt(fila, 2));
            pane2.txtdosis.setText((String) pane1.tabla.getValueAt(fila, 3));
            pane2.txtprecioventa.setText((Double) pane1.tabla.getValueAt(fila, 4)+"");
            pane2.txtigv.setText((Double) pane1.tabla.getValueAt(fila, 5)+"");
            pane2.txtpreciofinal.setText((Double) pane1.tabla.getValueAt(fila, 6)+"");
            pane2.txtstock.setText((int) pane1.tabla.getValueAt(fila, 7)+"");
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
                    
                    producto p=new producto(nombre, descripcion, dosis, precioventa, igv, preciofinal, stock);
                    manager.getProductoDAO().insertar(p);
                    manager.cerrarConexion();
                     pane1.actualizartabla();
                } catch (DAOException ex) {
                    System.out.println(" error"+ ex.getMessage());
                   
                }

                //mensaje de exito
                JOptionPane.showMessageDialog(null, "Se Registro el Producto satisfactoriamente", "Buen Trabajo ", JOptionPane.INFORMATION_MESSAGE);
                
                pestañas.setEnabledAt(0, true);
                pestañas.setSelectedIndex(0);
            } else if (action.equals("modificar")) {
                DAOManagerSQL manager = null;
                try {
                    manager = new DAOManagerSQL("localhost", "basefarmacia", "root", "");
                    Long idproducto= new Long(pane2.txtidproducto.getText());
                    String nombre = pane2.txtnombre.getText();
                    String descripcion = pane2.txtdescripcion.getText();
                    String dosis = pane2.txtdosis.getText();
                    double precioventa = Double.parseDouble(pane2.txtprecioventa.getText());
                    double igv = Double.parseDouble(pane2.txtigv.getText());
                    double preciofinal = Double.parseDouble(pane2.txtpreciofinal.getText());
                    int stock = Integer.parseInt(pane2.txtstock.getText());
                    
                    producto p=new producto(nombre, descripcion, dosis, precioventa, igv, preciofinal, stock);
                    p.setIdproducto(idproducto);
                    manager.getProductoDAO().modificar(p);
                    manager.cerrarConexion();
                     pane1.actualizartabla();
                } catch (DAOException ex) {
                        System.out.println(" errorr");
                   
                }
                JOptionPane.showMessageDialog(null, "Se Editó el Producto satisfactoriamente", "Buen Trabajo ", JOptionPane.INFORMATION_MESSAGE);
                pestañas.setEnabledAt(0, true);
                pestañas.setSelectedIndex(0);
            }
            //actualizar taba
           deshabilitar();
            pane1.control = true;
            pane1.txtBuscar.requestFocus();
            action = "nothing";

        } else if (source == jbEliminar) {
             DAOManagerSQL manager = null;
                try {
                    manager = new DAOManagerSQL("localhost", "basefarmacia", "root", "");
                    int fila = pane1.tabla.getSelectedRow();
                    Long idproducto= new Long((long) pane1.tabla.getValueAt(fila, 0));
                    producto p=new producto();
                    p.setIdproducto(idproducto);
                    manager.getProductoDAO().eliminar(p);
                    manager.cerrarConexion();
                     pane1.actualizartabla();
                } catch (DAOException ex) {
                    try {
                        throw new DAOException("error al insertar producto");
                    } catch (DAOException ex1) {
                        System.out.println(" errorr");
                    }
                   
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
            pane2.txtnombre.requestFocus();
            pestañas.setEnabledAt(0, false);
            jbSalir.setEnabled(false);
            jbNuevo.setEnabled(false);
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


}
