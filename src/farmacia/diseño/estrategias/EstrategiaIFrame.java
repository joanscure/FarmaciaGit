/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.diseño.estrategias;

import farmacia.calculos.Permisos;
import farmacia.calculos.configuracionImagenes;
import farmacia.jdbc.dao.DAOException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author fecyp
 */
public abstract class EstrategiaIFrame extends JInternalFrame {

    public JTabbedPane pestañas;

    public static JButton jbNuevo, jbEliminar, jbGuardar, jbModificar, jbSalir, jbCancelar;

    public String nombreAlm, telefonoAlm, direccionAlm;
    public Color c = Color.white;
    public Font fontboton = new Font("Geneva", 1, 14);
    public configuracionImagenes config = new configuracionImagenes();
    public String action = "nothing";
    public Permisos acceso = new Permisos();
    public boolean  permisoeliminar;

    public EstrategiaIFrame(String titulo) throws DAOException {
        super("Formulario " + titulo, false, true, false, true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        Iniciar_componentes(titulo);
        pestañas.setSelectedIndex(0);
        personalizacionfondocolor();
        deshabilitar();
        perzonalizartipoletra();
        personalizarboton();
        pack();

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

    public JPanel getBotones() {
        JPanel botones_principal = new JPanel(new BorderLayout());
        botones_principal.setBackground(c);
        JPanel botones = new JPanel(new GridLayout(6, 1));
        jbNuevo = new JButton("Nuevo(CTRL+N)", config.obtenerIcono("nuevo.png"));

        jbGuardar = new JButton("Guardar(CTRL+S)", config.obtenerIcono("guardar.png"));
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
        return botones_principal;
    }

    public void personalizacionfondocolor() {
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
    public abstract void habilitar();

    public abstract void deshabilitar();

    public abstract void Iniciar_componentes(String titulo);
     public abstract void guardar();
    public abstract void modificar();
     public abstract void eliminar();
}
