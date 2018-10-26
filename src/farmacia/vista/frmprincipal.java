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
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 * iI
 *
 * @author fecyp
 */
public class frmprincipal extends JFrame implements ActionListener, KeyListener {

    public JMenu malmacen, mconsultas, mherramientas, mayuda, mventas, mmantenimiento, manulaciones;
    public static JMenu marchivo;
    private JMenuItem iempresa, iproductos, isalir, icerrarsesion, iventas, iusuarios_accesos, itipousuario, icambiarPass, ianularventas, iclientes, iacercade, iayuda, iconsultaventas;
    public JDesktopPane desktopPane;
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
    frmConsultas frmconsulta;
    configuracionImagenes imageconfig = new configuracionImagenes();
    frmusuariologin login;
    boolean[] permiso = new boolean[12];
    public boolean controlpriincipal = true;
    public static boolean visibleclientes = false, visibleproductos = false, visibleempleados = false, visibletipo = false, visibleempresa = false, visibleventas = false,visibleconsulta;

    public frmprincipal(frmusuariologin login) throws DAOException {

        this.login = login;
//        this.setResizable(false);
        inciar_componentes();
        frmtipousuario = new frmTipousuario("Tipo de Usuario");
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
        iconsultaventas.addActionListener(this);
        setMinimumSize(new Dimension(600, 600));
        setVisible(true);
        perzonalizartipoletra();
        marchivo.addKeyListener(this);

    }

    public void permisos() {

        for (int i = 0; i < frmtipousuario.pane1.tabla.getRowCount(); i++) {
            if (Long.compare(jlocupacion, (Long) frmtipousuario.pane1.tabla.getValueAt(i, 0)) == 0) {
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
//        eproducto=(permiso[8]);
//        ecliente=(permiso[9]);
//        eempleado=(permiso[10]);
//        etipousuario=(permiso[11]);

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
        iconsultaventas.setFont(fontitem);
    }

    public void inciar_componentes() {

        desktopPane = new JDesktopPane();

        barra = new JMenuBar();
//        micono = new JMenu("");
//        micono.setIcon(imageconfig.obtenerIcono("caduceo.png", 32));
//        micono.setSelected(false);
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

        isalir = new JMenuItem("Salir");
        isalir.setIcon(imageconfig.obtenerIcono("salir.png", 30));

        iproductos = new JMenuItem("Productos");
        iproductos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        iproductos.setIcon(new ImageIcon(getClass().getResource("/Files/productos.png")));

        iventas = new JMenuItem("Ventas");
        iventas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
        iventas.setIcon(new ImageIcon(getClass().getResource("/Files/ventas.png")));

        iclientes = new JMenuItem("Clientes");
        iclientes.setIcon(new ImageIcon(getClass().getResource("/Files/clientes.png")));
        iclientes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));

        iempresa = new JMenuItem("Empresas");
        iempresa.setIcon(imageconfig.obtenerIcono("empresa.png", 30));
        iempresa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));

        iconsultaventas = new JMenuItem("Consultar ventas");
        iconsultaventas.setIcon(imageconfig.obtenerIcono("consulta.png", 40));
        iconsultaventas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));


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

        mconsultas.add(iconsultaventas);

        mmantenimiento.add(iusuarios_accesos);
        mmantenimiento.add(itipousuario);

        manulaciones.add(ianularventas);

        mherramientas.add(icambiarPass);

        mayuda.add(iacercade);
        mayuda.add(iayuda);
//        barra.add(micono);
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
            if (!visibleclientes) {
                try {
                    frmclientes = new frmClientes("Cliente");
                    desktopPane.add(frmclientes);

                    frmclientes.toFront();
                    frmclientes.setVisible(true);
                    frmclientes.permisoeliminar = permiso[9];
                    visibleclientes = true;
                    frmclientes.pane1.txtBuscar.requestFocus();
                } catch (DAOException ex) {
                    System.out.println("error" + ex.getMessage());
                }

            }

        } else if (source == icerrarsesion) {
// si esque cierra sesion, se cierran todas las ventanas si esque se han abierto
// y se abre el login guardando el txtusuario
            login.setVisible(true);
            login.txtpassword.setText("");
            login.txtpassword.requestFocus();
            try {
                login.llenartabla();
            } catch (DAOException ex) {
                System.out.println("error" + ex.getMessage());
            }

            dispose();

        } else if (source == isalir) {
            System.exit(0);

        } else if (source == iproductos) {
            if (!visibleproductos) {
                try {
                    frmproducto = new frmProducto("Producto");
                    desktopPane.add(frmproducto);
                    frmproducto.toFront();
                    frmproducto.setVisible(true);
                    frmproducto.permisoeliminar = permiso[8];
                    visibleproductos = true;
                    frmproducto.pane1.txtBuscar.requestFocus();
                } catch (DAOException ex) {
                    System.out.println("error" + ex.getMessage());
                }

            }

        } else if (source == itipousuario) {

            frmtipousuario.toFront();
            frmtipousuario.setVisible(true);
            frmtipousuario.pane1.txtBuscar.requestFocus();
            frmtipousuario.permisoeliminar = permiso[11];

        } else if (source == iusuarios_accesos) {
            if (!visibleempleados) {
                try {
                    frmempleados = new frmEmpleados("Empleado");
                    desktopPane.add(frmempleados);
                    frmempleados.toFront();
                    frmempleados.setVisible(true);
                    visibleempleados = true;
                    frmempleados.permisoeliminar = permiso[10];
                    frmempleados.pane1.txtBuscar.requestFocus();

                    actualizaritem();
                } catch (DAOException ex) {
                    System.out.println("error" + ex.getMessage());
                }

            }

        } else if (source == iventas) {
            if (!visibleventas) {
                frmventas = new frmVentas();
                desktopPane.add(frmventas);
                frmventas.toFront();
                frmventas.setVisible(true);
                visibleventas=true;
                frmventas.bnnuevo.requestFocus();
            }

        } else if (source == iempresa) {

            if (!visibleempresa) {
                try {
                    frmempresa = new frmEmpresa("Empresa");
                    desktopPane.add(frmempresa);
                    frmempresa.toFront();
                    frmempresa.setVisible(true);
                    visibleempresa=true;
                    frmempresa.permisoeliminar = permiso[9];
                    frmempresa.pane1.txtBuscar.requestFocus();

                } catch (DAOException ex) {
                    System.out.println("error" + ex.getMessage());
                }

            }

        } else if (source == icambiarPass) {
            frmCambioClave frm = new frmCambioClave(this);
            frm.setVisible(true);
            frm.toFront();
        }
      else if(source==iconsultaventas)
        {
             if (!visibleconsulta) {
                
                    frmconsulta = new frmConsultas();
                    desktopPane.add(frmconsulta);
                    frmconsulta.toFront();
                    frmconsulta.setVisible(true);
                    visibleconsulta=true;

               

            }
        }
    }

    private void actualizaritem() {
        String list[] = new String[frmtipousuario.pane1.tabla.getRowCount()];
        for (int i = 0; i < list.length; i++) {
            list[i] = (String) frmtipousuario.pane1.tabla.getValueAt(i, 1).toString();
        }
        frmempleados.actualizaritem(list);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (controlpriincipal) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                marchivo.doClick();
//                controlpriincipal=false;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
