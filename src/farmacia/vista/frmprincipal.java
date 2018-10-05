/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.vista;

import farmacia.calculos.configuracionImagenes;
import farmacia.jdbc.dao.DAOException;
import farmacia.vista.mantenimientoCliente.frmClientes;
import farmacia.vista.mantenimientoEmpleado.frmEmpleados;
import farmacia.vista.mantenimientoEmpresa.frmEmpresa;
import farmacia.vista.mantenimientoProductos.frmProducto;
import farmacia.vista.mantenimientoTipoUsuario.frmTipousuario;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author fecyp
 */
public class frmprincipal extends JFrame implements ActionListener, MouseListener {

    private JMenu malmacen, mconsultas, mherramientas, mayuda, marchivo, mventas, mmantenimiento, manulaciones;
    private JMenuItem iempresa, iproductos, isalir, icerrarsesion, iventas, iusuarios_accesos, itipousuario, icambiarPass, ianularventas, iclientes, iacercade, iayuda;
    public  JDesktopPane desktopPane;
    private JMenuBar barra;
    public static Long jlidpersona, jlidempleado;
    public static Long jlocupacion;
    Font fontgeneral = new Font("Geneva", 1, 15);
    Font fontitem = new Font("Geneva", 1, 13);
    frmClientes frmclientes;
    frmProducto frmproducto;
    frmTipousuario frmtipousuario;
    frmEmpleados frmempleados;
    frmVentas frmventas;
    frmEmpresa frmempresa;
    configuracionImagenes imageconfig = new configuracionImagenes();
    frmusuariologin login;
    public static boolean eproducto, ecliente, eempleado, etipousuario;

    public frmprincipal(frmusuariologin login) throws DAOException {

        this.login = login;
//        this.setResizable(false);
        inciar_componentes();
        frmtipousuario = new frmTipousuario();
        desktopPane.add(frmtipousuario);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setTitle("SISTEMA DE VENTAS FARMACIA");
        iproductos.addActionListener(this);
        iusuarios_accesos.addActionListener(this);
        iclientes.addActionListener(this);
        icerrarsesion.addActionListener(this);
        itipousuario.addActionListener(this);
        iventas.addActionListener(this);
        iempresa.addActionListener(this);
        icambiarPass.addActionListener(this);
        isalir.addActionListener(this);
        setMinimumSize(new Dimension(500, 500));
        setVisible(true);
        perzonalizartipoletra();

    }

    public void permisos() {
        int posicion;
        boolean[] permiso = new boolean[12];
        for (int i = 0; i < frmtipousuario.pane1.tabla.getRowCount(); i++) {
            if (Long.compare(jlocupacion, (Long) frmtipousuario.pane1.tabla.getValueAt(i, 0)) == 0) {
                posicion = i;
                permiso[0] = (boolean) frmtipousuario.pane1.tabla.getValueAt(i, 2);//ventas

                permiso[1] = (boolean) frmtipousuario.pane1.tabla.getValueAt(i, 3);//productos
                permiso[2] = (boolean) frmtipousuario.pane1.tabla.getValueAt(i, 4);//clietes
                permiso[3] = (boolean) frmtipousuario.pane1.tabla.getValueAt(i, 5);//consultas
                permiso[4] = (boolean) frmtipousuario.pane1.tabla.getValueAt(i, 6);//empleados
                permiso[5] = (boolean) frmtipousuario.pane1.tabla.getValueAt(i, 7);//tipousuario
                permiso[6] = (boolean) frmtipousuario.pane1.tabla.getValueAt(i, 8);//cambiarpass

                permiso[7] = (boolean) frmtipousuario.pane1.tabla.getValueAt(i, 9);//anularventas
                permiso[8] = (boolean) frmtipousuario.pane1.tabla.getValueAt(i, 10);//eliminarproductos
                permiso[9] = (boolean) frmtipousuario.pane1.tabla.getValueAt(i, 11);//elinmmnar clientes
                permiso[10] = (boolean) frmtipousuario.pane1.tabla.getValueAt(i, 12);//eliminar empleados
                permiso[11] = (boolean) frmtipousuario.pane1.tabla.getValueAt(i, 13);//tipo empleado

            }
        }
        iventas.setVisible(permiso[0]);
        iproductos.setVisible(permiso[1]);

        iusuarios_accesos.setVisible(permiso[4]);
        itipousuario.setVisible(permiso[5]);
        icambiarPass.setVisible(permiso[6]);

        ianularventas.setVisible(permiso[7]);

        iclientes.setVisible(permiso[2]);
        mconsultas.setVisible(permiso[3]);
        iempresa.setVisible(permiso[2]);
        eproducto=(permiso[8]);
        ecliente=(permiso[9]);
        eempleado=(permiso[10]);
        etipousuario=(permiso[11]);

    }

    public void perzonalizartipoletra() {
        malmacen.setFont(fontgeneral);
        mconsultas.setFont(fontgeneral);
        mherramientas.setFont(fontgeneral);
        mayuda.setFont(fontgeneral);
        marchivo.setFont(fontgeneral);
        mventas.setFont(fontgeneral);
        mmantenimiento.setFont(fontgeneral);
        manulaciones.setFont(fontgeneral);

        iproductos.setFont(fontitem);
        isalir.setFont(fontitem);
        icerrarsesion.setFont(fontitem);
        iventas.setFont(fontitem);
        iusuarios_accesos.setFont(fontitem);
        itipousuario.setFont(fontitem);
        icambiarPass.setFont(fontitem);
        ianularventas.setFont(fontitem);
        iclientes.setFont(fontitem);
        iacercade.setFont(fontitem);
        iayuda.setFont(fontitem);
        iempresa.setFont(fontitem);
    }

    public void inciar_componentes() {

        desktopPane = new JDesktopPane();

        barra = new JMenuBar();

        mventas = new JMenu("Ventas");
        mventas.setIcon(imageconfig.obtenerIcono("mventa.png", 32));

        malmacen = new JMenu("Almacen");
        malmacen.setIcon(imageconfig.obtenerIcono("almacen.png", 50));

        marchivo = new JMenu("Archivo");
        marchivo.setIcon(new ImageIcon(getClass().getResource("/Files/Archivo.png")));

        mconsultas = new JMenu("Consultas");
        mconsultas.setIcon(new ImageIcon(getClass().getResource("/Files/Consultas.png")));

        mmantenimiento = new JMenu("Configuraciones");
        mmantenimiento.setIcon(new ImageIcon(getClass().getResource("/Files/Configuraciones.png")));

        mherramientas = new JMenu("Herramientas");
        mherramientas.setIcon(new ImageIcon(getClass().getResource("/Files/Herramientas.png")));

        manulaciones = new JMenu("Anulaciones");
        manulaciones.setIcon(imageconfig.obtenerIcono("anulaciones.png", 32));

        mayuda = new JMenu("Ayuda");
        mayuda.setIcon(new ImageIcon(getClass().getResource("/Files/Ayuda.png")));

        icerrarsesion = new JMenuItem("Cerrar Sesion");
        icerrarsesion.setIcon(imageconfig.obtenerIcono("desconectar.png", 30));
        icerrarsesion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));

        isalir = new JMenuItem("Salir");
        isalir.setIcon(imageconfig.obtenerIcono("salir.png", 30));
        isalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));

        iproductos = new JMenuItem("Productos");
        iproductos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
        iproductos.setIcon(new ImageIcon(getClass().getResource("/Files/productos.png")));

        iventas = new JMenuItem("Ventas");
        iventas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
        iventas.setIcon(new ImageIcon(getClass().getResource("/Files/ventas.png")));

        iclientes = new JMenuItem("Clientes");
        iclientes.setIcon(new ImageIcon(getClass().getResource("/Files/clientes.png")));
        iclientes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));

        iempresa = new JMenuItem("Empresas");
        iempresa.setIcon(imageconfig.obtenerIcono("empresa.png", 30));
        iempresa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0));

        iusuarios_accesos = new JMenuItem("Usuarios y Accesos");
        iusuarios_accesos.setIcon(new ImageIcon(getClass().getResource("/Files/trabajadores.png")));
        iusuarios_accesos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0));

        itipousuario = new JMenuItem("Tipo de Usuario");
        itipousuario.setIcon(imageconfig.obtenerIcono("tipousuario.png", 30));
        itipousuario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0));

        icambiarPass = new JMenuItem("Cambiar Contraseña");
        icambiarPass.setIcon(imageconfig.obtenerIcono("cambioclave.png", 30));
        icambiarPass.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0));

        ianularventas = new JMenuItem("Anular Ventas");
        ianularventas.setIcon(imageconfig.obtenerIcono("anularventas.png", 30));
        ianularventas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0));

        iacercade = new JMenuItem("Acerca De");
        iacercade.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0));

        iayuda = new JMenuItem("Ayuda");
        iayuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0));

        marchivo.add(icerrarsesion);
        marchivo.add(isalir);

        malmacen.add(iproductos);

        mventas.add(iventas);
        mventas.add(iclientes);
        mventas.add(iempresa);

        mmantenimiento.add(iusuarios_accesos);
        mmantenimiento.add(itipousuario);

        manulaciones.add(ianularventas);

        mherramientas.add(icambiarPass);

        mayuda.add(iacercade);
        mayuda.add(iayuda);

        barra.add(marchivo);
        barra.add(malmacen);
        barra.add(mventas);
        barra.add(mconsultas);
        barra.add(mmantenimiento);
        barra.add(mherramientas);
        barra.add(manulaciones);
        barra.add(mayuda);
        this.setJMenuBar(barra);

        add(desktopPane);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == iclientes) {
            if (frmclientes == null) {
                try {
                    frmclientes = new frmClientes();
                } catch (DAOException ex) {
                    System.out.println("error" + ex.getMessage());
                }
                desktopPane.add(frmclientes);
            }

            frmclientes.toFront();
            frmclientes.setVisible(true);
            frmclientes.pane1.txtBuscar.requestFocus();
        } else if (source == icerrarsesion) {
// si esque cierra sesion, se cierran todas las ventanas si esque se han abierto
// y se abre el login guardando el txtusuario
            login.setVisible(true);
            login.txtpassword.setText("");
            login.requestFocus();
            try {
                login.llenartabla();
            } catch (DAOException ex) {
                Logger.getLogger(frmprincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (frmproducto != null) {
                frmproducto.dispose();
            }
            if (frmclientes != null) {
                frmclientes.dispose();
            }
            if (frmtipousuario != null) {
                frmtipousuario.dispose();
            }
            if (frmempleados != null) {
                frmempleados.dispose();
            }
            if (frmventas != null) {
                if (frmventas.frmvistaproducto != null) {
                    frmventas.frmvistaproducto.dispose();
                }
                frmventas.dispose();
            }
            if (frmempresa != null) {
                frmempresa.dispose();
            }
            dispose();

        } else if (source == isalir) {
            System.exit(0);

        } else if (source == iproductos) {
            if (frmproducto == null) {
                try {
                    frmproducto = new frmProducto();
                } catch (DAOException ex) {
                    System.out.println("error" + ex.getMessage());
                }
                desktopPane.add(frmproducto);
            }

            frmproducto.toFront();
            frmproducto.setVisible(true);
            frmproducto.pane1.txtBuscar.requestFocus();

        } else if (source == itipousuario) {

            frmtipousuario.toFront();
            frmtipousuario.setVisible(true);
            frmtipousuario.pane1.txtBuscar.requestFocus();

        } else if (source == iusuarios_accesos) {
            if (frmempleados == null) {
                try {
                    frmempleados = new frmEmpleados();
                    desktopPane.add(frmempleados);
                } catch (DAOException ex) {
                    System.out.println("error" + ex.getMessage());
                }

            }

            frmempleados.toFront();
            frmempleados.setVisible(true);
            frmempleados.pane1.txtBuscar.requestFocus();

            actualizaritem();

        } else if (source == iventas) {
            if (frmventas == null) {
                frmventas = new frmVentas();
                desktopPane.add(frmventas);
            }

            frmventas.toFront();
            frmventas.setVisible(true);
            frmventas.bnnuevo.requestFocus();
        } else if (source == iempresa) {

            if (frmempresa == null) {
                try {
                    frmempresa = new frmEmpresa();
                    desktopPane.add(frmempresa);
                } catch (DAOException ex) {
                    System.out.println("error" + ex.getMessage());
                }

            }

            frmempresa.toFront();
            frmempresa.setVisible(true);
            frmempresa.pane1.txtBuscar.requestFocus();
        } else if (source == icambiarPass) {
            frmCambioClave frm = new frmCambioClave(this);
            frm.setVisible(true);
            frm.toFront();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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

    private void actualizaritem() {
        String list[] = new String[frmtipousuario.pane1.tabla.getRowCount()];
        for (int i = 0; i < list.length; i++) {
            list[i] = (String) frmtipousuario.pane1.tabla.getValueAt(i, 1);
        }
        frmempleados.actualizaritem(list);
    }

}
