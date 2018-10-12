/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.diseño.estrategias;

import farmacia.calculos.configuracionImagenes;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;

/**
 *
 * @author fecyp
 */
public abstract class EstrategiaPanelRegistrar extends JPanel {

    public JPanel pane;
    public configuracionImagenes iamgeConfig = new configuracionImagenes();
    public Color c = new java.awt.Color(255, 255, 153);
    public Font fontboton = new Font("Geneva", 1, 13);
    public boolean teclaunida = false;

    public EstrategiaPanelRegistrar(String titulo) {
        iniciar_componentes(titulo);
        setBackground(c);
        personalizartipoletra();
    }

    public abstract void personalizartipoletra();

    public abstract void iniciar_componentes(String titulo);
}
