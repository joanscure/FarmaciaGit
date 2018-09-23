/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista;

import com.toedter.calendar.JDateChooser;
import farmacia.calculos.EstiloTablaHeader;
import farmacia.calculos.EstiloTablaRenderer;
import farmacia.calculos.configuracionImagenes;
import farmacia.calculos.configuracionesTabla;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author fecyp
 */
public class frmVentas extends JInternalFrame implements ActionListener, KeyListener, MouseListener {

    //paneles
    JPanel paneprincipal, paneventa, panebotones;
    //datos venta
    JLabel jlcliente, jbdtipocomprobante, jlfecha;
    public static JTextField txtidcliente, txtnombrecliente;
    JButton bnagregarCliente;
    JComboBox cbxtipocomprobante;
    JDateChooser fechaventa;

    //pane botones
    JButton bnguardar, bnnuevo, bnrecibo, bncancelar, bnsalir;
    //datos producto
    JLabel jlidproducto, jlstock, jlprecio, jlnombreproducto;
    public static JTextField txtnombreProducto, txtcodigo, txtstock, txtprecio;
    JButton bnagregproducto;

    //agrega
    JLabel jlcantidad, jltotal;
    JTextField txtcantidad, txttotal;
    JButton bnagregar, bnquitar;

    //imagen
    JLabel jlcorrelativo;
    JTextField txtcorrelativo;
    JLabel jlimagen;
    configuracionesTabla configtabla = new configuracionesTabla();
    //total y subtotal
    JLabel jltotalPago, jlsubtotal, jldescuento, jligv;
    JTextField txttotalPago, txtsubtotal, txtdescuento, txtigv;

//configuraciones
    configuracionImagenes configImage = new configuracionImagenes();
    Font fontbutton = new Font("Geneva", 1, 14);
    Font fonttexto = new Font("Geneva", 1, 13);
    Color colorButton = Color.WHITE;
    Color paneClaro = new java.awt.Color(255, 255, 153);
//    Color paneClaro = Color.white;
    Color paneOscuro = new Color(255, 204, 102);
    Color azulclaro = new Color(191, 198, 251);
    Color amarilloclaro = new Color(253, 255, 189);
    boolean teclaunida = false;
    //tabla
    JTable tabla;
    DefaultTableModel modelo;
    public static String action = "nothing";

    frmvistalistadoproductos frmvistaproducto = new frmvistalistadoproductos();

    public frmVentas() {
        super("Formulario Ventas", false, true, false, true);

        iniciar_componentes();
        changeFont();
        changeColor();
        personalizarboton();
        inicializacionVariables();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                if (!action.equals("nothing")) {
                    JOptionPane.showMessageDialog(null, "Se esta ejecutando una Accion\n para cerrar la ventana debe cancelar o terminar con dicha acción", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    bnsalir.doClick();
                }
            }

        });
        deshabilitar();
        txtprecio.setText("10");
        bnsalir.addActionListener(this);
        bnagregar.addActionListener(this);
        bnnuevo.addActionListener(this);
        bncancelar.addActionListener(this);
        bnagregarCliente.addActionListener(this);
        bnagregproducto.addActionListener(this);
        txtcodigo.addActionListener(this);

        bnagregarCliente.addKeyListener(this);
        cbxtipocomprobante.addKeyListener(this);
        txtcantidad.addKeyListener(this);
        txtcodigo.addKeyListener(this);
        bnagregproducto.addKeyListener(this);
        bnnuevo.addKeyListener(this);
        bnsalir.addKeyListener(this);
        cbxtipocomprobante.addMouseListener(this);

    }

    public void habilitar() {

        bnagregarCliente.requestFocus();
        bnagregarCliente.setEnabled(true);
        cbxtipocomprobante.setEnabled(true);
        fechaventa.setEnabled(true);
        txtcodigo.setEnabled(true);
        bnagregproducto.setEnabled(true);
        txtnombreProducto.setEnabled(false);
        txtcantidad.setEnabled(true);
        tabla.setEnabled(true);

        bnnuevo.setEnabled(false);
        bnguardar.setEnabled(true);
        bncancelar.setEnabled(true);
        bnsalir.setEnabled(false);

        txtidcliente.setText("");
        txtnombrecliente.setText("Cliente Genérico");
        cbxtipocomprobante.setSelectedIndex(0);
        Date date = new Date();
        fechaventa.setDate(date);
        txtcodigo.setText("");
        txtnombreProducto.setText("");
        txtstock.setText("");
        txtprecio.setText("");
        txtcantidad.setText("");
        txttotal.setText("");
        txtsubtotal.setText("0.00");
        txttotalPago.setText("0.00");
        txtdescuento.setText("0.00");
        txtigv.setText("0.00");

    }

    public void deshabilitar() {

        bnnuevo.requestFocus();
        bnagregarCliente.setEnabled(false);
        cbxtipocomprobante.setEnabled(false);
        fechaventa.setEnabled(false);
        txtcodigo.setEnabled(false);
        bnagregproducto.setEnabled(false);
        txtnombreProducto.setEnabled(false);
        txtcantidad.setEnabled(false);
             tabla.setEnabled(false);

        bnnuevo.setEnabled(true);
        bnguardar.setEnabled(false);
        bncancelar.setEnabled(false);
        bnsalir.setEnabled(true);

        txtidcliente.setText("");
        txtnombrecliente.setText("");
        cbxtipocomprobante.setSelectedIndex(0);
        Date date = new Date();
        fechaventa.setDate(date);
        txtcodigo.setText("");
        txtnombreProducto.setText("");
        txtstock.setText("");
        txtprecio.setText("");
        txtcantidad.setText("");
        txttotal.setText("");
        txtsubtotal.setText("0.00");
        txttotalPago.setText("0.00");
        txtdescuento.setText("0.00");
        txtigv.setText("0.00");

    }

    public void changeColor() {
        bnguardar.setBackground(colorButton);
        bnnuevo.setBackground(colorButton);
        bnrecibo.setBackground(colorButton);
        bncancelar.setBackground(colorButton);
        bnsalir.setBackground(colorButton);
        bnagregarCliente.setBackground(colorButton);
        txttotal.setBackground(azulclaro);
        txttotalPago.setBackground(Color.black);
        txtsubtotal.setBackground(amarilloclaro);
        txtdescuento.setBackground(azulclaro);
        txtigv.setBackground(amarilloclaro);
        txttotalPago.setForeground(Color.green);
        bnagregproducto.setBackground(colorButton);

    }

    public void changeFont() {
        bnguardar.setFont(fontbutton);
        bnnuevo.setFont(fontbutton);
        bnrecibo.setFont(fontbutton);
        bncancelar.setFont(fontbutton);
        bnsalir.setFont(fontbutton);

        jlcliente.setFont(fonttexto);
        txtidcliente.setFont(fonttexto);
        txtnombrecliente.setFont(fonttexto);
        jlfecha.setFont(fonttexto);
        fechaventa.setFont(fonttexto);
        jbdtipocomprobante.setFont(fonttexto);
        cbxtipocomprobante.setFont(fonttexto);
        jlnombreproducto.setFont(fonttexto);
        txtnombreProducto.setFont(fonttexto);
        jlstock.setFont(fonttexto);
        txtstock.setFont(fonttexto);
        jlprecio.setFont(fonttexto);
        txtprecio.setFont(fonttexto);
        jlidproducto.setFont(fonttexto);
        txtcodigo.setFont(fonttexto);
        jlcantidad.setFont(fonttexto);
        txtcantidad.setFont(fonttexto);
        jltotal.setFont(fonttexto);
        txttotal.setFont(fonttexto);
        jlcorrelativo.setFont(fonttexto);
        txtcorrelativo.setFont(fonttexto);
        tabla.getTableHeader().setFont(fontbutton);
        jltotalPago.setFont(fonttexto);
        jlsubtotal.setFont(fonttexto);
        jldescuento.setFont(fonttexto);
        jligv.setFont(fonttexto);
        txttotalPago.setFont(fonttexto);
        txtsubtotal.setFont(fonttexto);
        txtdescuento.setFont(fonttexto);
        txtigv.setFont(fonttexto);

    }

    public void inicializacionVariables() {
        txtnombreProducto.setEditable(false);
        txtnombrecliente.setEnabled(false);
        txtsubtotal.setEditable(false);
        txttotal.setEditable(false);
        txtigv.setEditable(false);
        txtdescuento.setEditable(false);
        txttotalPago.setEditable(false);
        txtcorrelativo.setEnabled(false);
        bnrecibo.setEnabled(false);
        bncancelar.setEnabled(false);
        bnguardar.setEnabled(false);
        bnagregar.setEnabled(false);
        bnquitar.setEnabled(false);
        Date fecha = new Date();
        fechaventa.setDate(fecha);
    }

    public void personalizarboton() {
        bnnuevo.setHorizontalTextPosition(SwingConstants.CENTER);
        bnnuevo.setVerticalTextPosition(SwingConstants.BOTTOM);

        bnguardar.setHorizontalTextPosition(SwingConstants.CENTER);
        bnguardar.setVerticalTextPosition(SwingConstants.BOTTOM);

        bnrecibo.setHorizontalTextPosition(SwingConstants.CENTER);
        bnrecibo.setVerticalTextPosition(SwingConstants.BOTTOM);

        bncancelar.setHorizontalTextPosition(SwingConstants.CENTER);
        bncancelar.setVerticalTextPosition(SwingConstants.BOTTOM);

        bnsalir.setHorizontalTextPosition(SwingConstants.CENTER);
        bnsalir.setVerticalTextPosition(SwingConstants.BOTTOM);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == bnsalir) {
            setVisible(false);
        } else if (source == bnagregar) {
            bnagregar.setEnabled(false);
        } else if (source == bnnuevo) {
            action = "nuevo";
            habilitar();
        } else if (source == bncancelar) {
            deshabilitar();
            action = "nothing";
        } else if (source == bnguardar) {
            deshabilitar();
            action = "nothing";

        } else if (source == bnrecibo) {
            deshabilitar();
            action = "nothing";
        } else if (source == bnagregarCliente) {
            txtcodigo.requestFocus();
            if (cbxtipocomprobante.getSelectedIndex() == 0) {
                frmvistalistadocliente frmvistacliente = new frmvistalistadocliente();

                frmvistacliente.setVisible(true);
                frmvistacliente.toFront();

            } else {
                frmvistalistadoEmpresa frmvistaempresa = new frmvistalistadoEmpresa();
                frmvistaempresa.setVisible(true);
                frmvistaempresa.toFront();
            }

        } else if (source == bnagregproducto) {
            txtcantidad.requestFocus();

            frmvistaproducto.setVisible(true);
            frmvistaproducto.toFront();

        } else if (source == txtcodigo) {
            if (txtnombreProducto.getText().isEmpty()) {
                if (txtcodigo.getText().isEmpty()) {
                    bnagregproducto.doClick();
                } else {
                    JOptionPane.showMessageDialog(null, "El Producto no se encuentra", "Error al buscar", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                txtcantidad.requestFocus();
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent ke) {
        Object source = ke.getSource();
        if (source == txtcantidad) {
            if (ke.getKeyChar() < 48 || ke.getKeyChar() > 57) {
                ke.consume();
            }
            if (txtcantidad.getText().isEmpty()) {
                if (ke.getKeyChar() == 48) {
                    ke.consume();
                }
            }
            if (txtcantidad.getText().length() >= 3) {
                ke.consume();
            }
        } else if (source == txtcodigo) {
            if (ke.getKeyChar() < 48 || ke.getKeyChar() > 57) {
                ke.consume();
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        Object source = ke.getSource();
        //izquierda
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            if (source == bnagregarCliente) {
                txtcantidad.requestFocus();
                return;
            }
            if (source == cbxtipocomprobante) {
                cbxtipocomprobante.setPopupVisible(false);
                bnagregarCliente.requestFocus();
                return;
            }
            if (source == txtcodigo) {
                cbxtipocomprobante.setPopupVisible(true);
                cbxtipocomprobante.requestFocus();
                return;
            }
            if (source == bnnuevo) {
                bnsalir.requestFocus();
                return;
            }
            ke.getComponent().transferFocusBackward();
        }
        //derecha
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (source == bnagregarCliente) {
                cbxtipocomprobante.setPopupVisible(true);
                cbxtipocomprobante.requestFocus();
                return;
            }
            if (source == cbxtipocomprobante) {
                cbxtipocomprobante.setPopupVisible(false);
                txtcodigo.requestFocus();
                return;
            }
            if (source == bnsalir) {
                bnnuevo.requestFocus();
                return;
            }
            if (source == txtcantidad) {
                bnagregarCliente.requestFocus();
                return;
            }
            ke.getComponent().transferFocus();
        }
        //si se hace enter en un combo box
        if (source == cbxtipocomprobante) {
            if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                cbxtipocomprobante.setPopupVisible(false);
                if (cbxtipocomprobante.getSelectedIndex() == 0) {
                    jlcliente.setText("Cliente:");
                    txtnombrecliente.setText("");
                    txtidcliente.setText("");
                } else {
                    jlcliente.setText("Empresa:");
                    txtnombrecliente.setText("");
                    txtidcliente.setText("");
                }
            }
        } else if (source == bnnuevo) {
            if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                bnnuevo.doClick();
            }
        } else if (source == bnsalir) {
            if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                bnsalir.doClick();
            }
        } else if (source == bnagregarCliente) {
            if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                bnagregarCliente.doClick();
            }
        } else if (source == bnagregproducto) {
            if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                bnagregproducto.doClick();
            }
        }
        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (bncancelar.isEnabled()) {
                bncancelar.doClick();
            } else if (bnsalir.isEnabled()) {
                bnsalir.doClick();
            }

        }
        if (ke.getExtendedKeyCode() == KeyEvent.VK_CONTROL) {
            if (bnguardar.isEnabled()) {
                teclaunida = true;
            }
        }
        //
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        Object source = ke.getSource();
        if (source == txtcantidad) {
            if (txtprecio.getText().isEmpty()) {
                return;

            }

            if (!txtcantidad.getText().isEmpty()) {
                int stockmax = Integer.parseInt(txtstock.getText());
                int cantidadmax = Integer.parseInt(txtcantidad.getText());
                if (cantidadmax > stockmax) {
                    txtcantidad.setForeground(Color.RED);
                    txttotal.setText("");
                    bnagregar.setEnabled(false);
                    return;
                } else {
                    txtcantidad.setForeground(Color.BLACK);
                }
                int cant = Integer.parseInt(txtcantidad.getText());
                double total = cant * Double.parseDouble(txtprecio.getText());
                BigDecimal bd = new BigDecimal(total);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                txttotal.setText(bd.doubleValue() + "");
                bnagregar.setEnabled(true);
            } else {
                txttotal.setText("");
                bnagregar.setEnabled(false);
            }

        } else if (source == txtcodigo) {
            if (txtcodigo.getText().isEmpty()) {
                txtnombreProducto.setText("");
                txtprecio.setText("");
                txtstock.setText("");

            }
            for (int i = 0; i < frmvistaproducto.tabla.getRowCount(); i++) {
                if (txtcodigo.getText().equals(frmvistaproducto.tabla.getValueAt(i, 0))) {
                    txtnombreProducto.setText((String) frmvistaproducto.tabla.getValueAt(i, 1));
                    txtprecio.setText((String) frmvistaproducto.tabla.getValueAt(i, 6));
                    txtstock.setText((String) frmvistaproducto.tabla.getValueAt(i, 7));
                } else {
                    txtnombreProducto.setText("");
                    txtprecio.setText("");
                    txtstock.setText("");
                }
            }
        }
        if (ke.getKeyCode() == KeyEvent.VK_S && teclaunida) {

            bnguardar.doClick();
            teclaunida = false;
        }
    }

    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        new frmVentas().setVisible(true);

    }

    @Override
    public void mouseClicked(MouseEvent ke) {
        Object source = ke.getSource();
        if (source == cbxtipocomprobante) {

            if (cbxtipocomprobante.getSelectedIndex() == 0) {
                jlcliente.setText("Cliente:");
                txtnombrecliente.setText("");
                txtidcliente.setText("");
            } else {
                jlcliente.setText("Empresa:");
                txtnombrecliente.setText("");
                txtidcliente.setText("");
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

    //crear ventana y componentes
    public void iniciar_componentes() {
        paneprincipal = new JPanel(new BorderLayout());
        crear_paneventa();
        paneventa.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(1, 1, 1, 1),
                BorderFactory.createTitledBorder("")));
        crear_panebotones();
        panebotones.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(1, 1, 1, 1),
                BorderFactory.createTitledBorder("")));
        paneprincipal.add(paneventa, BorderLayout.WEST);
        paneprincipal.add(panebotones, BorderLayout.EAST);
        paneprincipal.setBackground(colorButton);
        add(paneprincipal);
        //config ventana
        pack();

    }

    public void crear_paneventa() {
        paneventa = new JPanel(new BorderLayout());
        paneventa.setBackground(paneClaro);

        JPanel panecabecera = new JPanel(new BorderLayout());
        panecabecera.setBackground(paneClaro);
        //pane iquierda o la cabecera izquierda

        JPanel paneizquierda = new JPanel(new BorderLayout());
        paneizquierda.setBackground(paneClaro);

        JPanel panedatos = new JPanel(new BorderLayout());
        panedatos.setBackground(paneClaro);

        JPanel panedatosventa = new JPanel();
        panedatosventa.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(1, 1, 1, 1),
                BorderFactory.createTitledBorder("Datos de la venta")));
        panedatosventa.setBackground(paneClaro);
        panedatosventa.add(crearDatosVenta());

        //datos de producto
        JPanel panedatosproducto = new JPanel(new BorderLayout());
        panedatosproducto.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(1, 1, 1, 1),
                BorderFactory.createTitledBorder("Datos del Producto")));
        panedatosproducto.setBackground(paneClaro);

        panedatosproducto.add(crearDatosProducto(), BorderLayout.WEST);
        //

        panedatos.add(panedatosventa, BorderLayout.NORTH);
        panedatos.add(panedatosproducto, BorderLayout.SOUTH);
        //pane confi producto
        JPanel paneExtra = new JPanel(new BorderLayout());
        paneExtra.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createTitledBorder("")));
        paneExtra.setBackground(paneClaro);
        paneExtra.add(crear_paneinformacionproducto());

        paneizquierda.add(panedatos, BorderLayout.NORTH);
        paneizquierda.add(paneExtra, BorderLayout.SOUTH);

        //pane derech o la imagen
        JPanel panederecha = new JPanel(new BorderLayout());
        panederecha.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createTitledBorder("")));
        panederecha.setBackground(paneClaro);
        panederecha.add(crearcorrelativo());

        panecabecera.add(paneizquierda, BorderLayout.WEST);
        panecabecera.add(panederecha, BorderLayout.EAST);

        //pane datos de total:
        JPanel paneDatospago = new JPanel(new FlowLayout());
        paneDatospago.setBackground(paneClaro);
        paneDatospago.add(crearDatosPagos());

        paneventa.add(panecabecera, BorderLayout.NORTH);
        paneventa.add(venta_tabla(), BorderLayout.CENTER);
        paneventa.add(paneDatospago, BorderLayout.SOUTH);

    }

    public JPanel crearDatosVenta() {
        JPanel panedatosventa = new JPanel(new BorderLayout());
        panedatosventa.setBackground(paneClaro);
        JPanel paneidcliente = new JPanel(new BorderLayout());
        paneidcliente.setBackground(paneClaro);
        jlcliente = new JLabel("Cliente:");
        txtidcliente = new JTextField(5);
        txtidcliente.setVisible(false);
        paneidcliente.add(jlcliente, BorderLayout.WEST);
        paneidcliente.add(txtidcliente, BorderLayout.EAST);

        JPanel panenombrecliente = new JPanel(new BorderLayout());
        panenombrecliente.setBackground(paneClaro);
        txtnombrecliente = new JTextField(10);
        bnagregarCliente = new JButton("", new ImageIcon(getClass().getResource("/Files/buscar.png")));
        panenombrecliente.add(bnagregarCliente, BorderLayout.EAST);
        panenombrecliente.add(txtnombrecliente, BorderLayout.WEST);

        JPanel cliente = new JPanel(new BorderLayout());
        cliente.setBackground(paneClaro);
        cliente.add(panenombrecliente, BorderLayout.SOUTH);
        cliente.add(paneidcliente, BorderLayout.NORTH);
        ///
        JPanel datosComprobante = new JPanel(new BorderLayout());

        JPanel tipocomprobante = new JPanel(new BorderLayout());
        jbdtipocomprobante = new JLabel("Comprobante: ");
        String[] itemcomprobante = {"Boleta", "Factura"};
        cbxtipocomprobante = new JComboBox(itemcomprobante);
        cbxtipocomprobante.setPreferredSize(new Dimension(150, 30));
        tipocomprobante.add(jbdtipocomprobante, BorderLayout.NORTH);
        tipocomprobante.add(cbxtipocomprobante, BorderLayout.SOUTH);
        tipocomprobante.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        tipocomprobante.setBackground(paneClaro);

        JPanel fechacomprobantes = new JPanel(new BorderLayout());
        jlfecha = new JLabel("Fecha :");
        fechaventa = new JDateChooser();
        fechaventa.setPreferredSize(new Dimension(150, 30));
        fechacomprobantes.add(jlfecha, BorderLayout.NORTH);
        fechacomprobantes.add(fechaventa, BorderLayout.SOUTH);
        fechacomprobantes.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        fechacomprobantes.setBackground(paneClaro);

        datosComprobante.add(tipocomprobante, BorderLayout.WEST);
        datosComprobante.add(fechacomprobantes, BorderLayout.EAST);

        panedatosventa.add(cliente, BorderLayout.WEST);
        panedatosventa.add(datosComprobante, BorderLayout.EAST);
        return panedatosventa;
    }

    public JPanel crearDatosProducto() {
        JPanel panedatosproducto = new JPanel(new BorderLayout());
        panedatosproducto.setBackground(paneClaro);

        JPanel westpaneproducto = new JPanel(new BorderLayout());
        westpaneproducto.setBackground(paneClaro);
        JPanel panelidproducto = new JPanel(new BorderLayout());
        jlidproducto = new JLabel("Codigo de Producto:");
        JPanel panetextoboton = new JPanel(new BorderLayout());
        bnagregproducto = new JButton("", new ImageIcon(getClass().getResource("/Files/buscar.png")));
        txtcodigo = new JTextField(10);
        panetextoboton.add(txtcodigo, BorderLayout.WEST);
        panetextoboton.add(bnagregproducto, BorderLayout.EAST);
        panetextoboton.setBackground(paneClaro);
        panelidproducto.setBackground(paneClaro);
        panelidproducto.add(jlidproducto, BorderLayout.NORTH);
        panelidproducto.add(panetextoboton, BorderLayout.SOUTH);

        JPanel nombrepane = new JPanel(new BorderLayout());
        txtnombreProducto = new JTextField(10);
        jlnombreproducto = new JLabel("Nombre ");
        nombrepane.add(txtnombreProducto, BorderLayout.SOUTH);
        nombrepane.add(jlnombreproducto, BorderLayout.NORTH);
        nombrepane.setBackground(paneClaro);
        westpaneproducto.add(panelidproducto, BorderLayout.NORTH);
        westpaneproducto.add(nombrepane, BorderLayout.SOUTH);

        JPanel eastpaneproducto = new JPanel(new FlowLayout());
        eastpaneproducto.setBackground(paneClaro);
        eastpaneproducto.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JPanel todoeast = new JPanel(new BorderLayout());
        todoeast.setBackground(paneClaro);

        JPanel panestock = new JPanel(new BorderLayout());
        jlstock = new JLabel("STOCK: ");
        txtstock = new JTextField(10);
        txtstock.setEnabled(false);
        panestock.add(jlstock, BorderLayout.NORTH);
        panestock.add(txtstock, BorderLayout.SOUTH);
        panestock.setBackground(paneClaro);
        JPanel panecantidad = new JPanel(new BorderLayout());
        jlprecio = new JLabel("PRECIO: ");
        txtprecio = new JTextField(10);
        txtprecio.setEnabled(false);
        panecantidad.add(jlprecio, BorderLayout.NORTH);
        panecantidad.add(txtprecio, BorderLayout.SOUTH);
        panecantidad.setBackground(paneClaro);

        todoeast.add(panestock, BorderLayout.WEST);
        todoeast.add(panecantidad, BorderLayout.EAST);
        eastpaneproducto.add(todoeast);

        panedatosproducto.add(westpaneproducto, BorderLayout.WEST);
        panedatosproducto.add(eastpaneproducto, BorderLayout.EAST);
        return panedatosproducto;
    }

    public void crear_panebotones() {
        panebotones = new JPanel(new GridLayout(5, 1));
        bnguardar = new JButton("Realizar Venta", configImage.obtenerIcono("generarventas.png", 42));
        bnnuevo = new JButton("Nueva Venta", configImage.obtenerIcono("nuevo.png", 42));
        bnrecibo = new JButton("Ver Recibo", configImage.obtenerIcono("verrecibo.png", 42));
        bncancelar = new JButton("Cancelar Venta", configImage.obtenerIcono("cancelarventa.png", 42));
        bnsalir = new JButton("Salir", configImage.obtenerIcono("salirventa.png", 42));

        panebotones.add(bnnuevo);
        panebotones.add(bnguardar);
        panebotones.add(bnrecibo);
        panebotones.add(bncancelar);
        panebotones.add(bnsalir);
        panebotones.setBackground(colorButton);
    }

    public JPanel crear_paneinformacionproducto() {
        JPanel paneExtra = new JPanel(new FlowLayout());
        paneExtra.setBackground(paneClaro);

        JPanel panecantidad = new JPanel(new BorderLayout());
        jlcantidad = new JLabel("Cantidad: ");
        txtcantidad = new JTextField(5);
        panecantidad.add(jlcantidad, BorderLayout.WEST);
        panecantidad.add(txtcantidad, BorderLayout.EAST);
        panecantidad.setBackground(paneClaro);

        JPanel paneltotal = new JPanel(new BorderLayout());
        jltotal = new JLabel("Total: ");
        txttotal = new JTextField(5);
        paneltotal.add(jltotal, BorderLayout.WEST);
        paneltotal.add(txttotal, BorderLayout.EAST);
        paneltotal.setBackground(paneClaro);

        JPanel panebotonesconfig = new JPanel(new BorderLayout());
        bnagregar = new JButton("", configImage.obtenerIcono("agregar.png", 30));
        bnquitar = new JButton("", configImage.obtenerIcono("eliminar.png", 30));
        panebotonesconfig.setBackground(paneClaro);
        panebotonesconfig.add(bnagregar, BorderLayout.WEST);
        panebotonesconfig.add(bnquitar, BorderLayout.EAST);
        paneExtra.add(panecantidad);
        paneExtra.add(paneltotal);
        paneExtra.add(panebotonesconfig);
        return paneExtra;
    }

    public JPanel crearcorrelativo() {
        JPanel panederecha = new JPanel(new BorderLayout());
        panederecha.setBackground(paneClaro);
        JPanel paneimagen = new JPanel(new BorderLayout());
        jlimagen = new JLabel("");
        jlimagen.setIcon(configImage.obtenerIcono("farmacia.png", 180));
        paneimagen.add(jlimagen, BorderLayout.CENTER);
        paneimagen.setBackground(paneClaro);

        JPanel panecorrelativo = new JPanel(new BorderLayout());
        jlcorrelativo = new JLabel("Numero de Correlativo:");
        txtcorrelativo = new JTextField(15);
        panecorrelativo.setBackground(paneClaro);
        panecorrelativo.add(jlcorrelativo, BorderLayout.NORTH);
        panecorrelativo.add(txtcorrelativo, BorderLayout.SOUTH);
        panederecha.add(paneimagen, BorderLayout.CENTER);
        panederecha.add(panecorrelativo, BorderLayout.SOUTH);
        return panederecha;
    }

    public JScrollPane venta_tabla() {
        tabla = new JTable();
        Object[][] data = new Object[0][0];
        String[] lista = {"Codigo", "Producto", "Descripcion", "Cant.", "Precio", "Total"};
        modelo = new DefaultTableModel(data, lista) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla.setModel(modelo);
        JScrollPane pane = new JScrollPane(tabla);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.getTableHeader().setReorderingAllowed(false);
        pane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //aciones al seleccionar una fila de la tabla
        tabla.getSelectionModel().addListSelectionListener(e -> {
//            if (control) {
            bnquitar.setEnabled(true);
//            }
        }
        );
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pane.setBackground(colorButton);
        int[] tamaño = {100, 200, 200, 80, 90, 90};
        configtabla.fijarTamaño(tabla, tamaño);
        pane.setPreferredSize(new Dimension(760, 200));
        tabla.getTableHeader().setDefaultRenderer(new EstiloTablaHeader());
        tabla.setDefaultRenderer(Object.class, new EstiloTablaRenderer());
        return pane;
    }

    public JPanel crearDatosPagos() {
        JPanel paneDatospago = new JPanel(new FlowLayout());
        paneDatospago.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createTitledBorder("")));
        paneDatospago.setBackground(paneClaro);
        JPanel panesubtotal = new JPanel(new BorderLayout());
        panesubtotal.setBackground(paneClaro);
        jlsubtotal = new JLabel("SUB TOTAL");
        txtsubtotal = new JTextField("0.00", 10);
        panesubtotal.add(jlsubtotal, BorderLayout.NORTH);
        panesubtotal.add(txtsubtotal, BorderLayout.SOUTH);

        JPanel paneigv = new JPanel(new BorderLayout());
        paneigv.setBackground(paneClaro);
        jligv = new JLabel("IGV ");
        txtigv = new JTextField("0.00", 10);
        paneigv.add(jligv, BorderLayout.NORTH);
        paneigv.add(txtigv, BorderLayout.SOUTH);

        JPanel panedescuentos = new JPanel(new BorderLayout());
        panedescuentos.setBackground(paneClaro);
        jldescuento = new JLabel("DESUENTO");
        txtdescuento = new JTextField("0.00", 10);
        panedescuentos.add(jldescuento, BorderLayout.NORTH);
        panedescuentos.add(txtdescuento, BorderLayout.SOUTH);

        JPanel panetotalPago = new JPanel(new BorderLayout());
        panetotalPago.setBackground(paneClaro);
        jltotalPago = new JLabel("TOTAL");
        txttotalPago = new JTextField("0.00", 10);
        panetotalPago.add(jltotalPago, BorderLayout.NORTH);
        panetotalPago.add(txttotalPago, BorderLayout.SOUTH);
        paneDatospago.add(panesubtotal);
        paneDatospago.add(paneigv);
        paneDatospago.add(panedescuentos);
        paneDatospago.add(panetotalPago);
        return paneDatospago;
    }

}
