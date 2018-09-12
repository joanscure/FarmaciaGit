/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.calculos;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author fecyp
 */
public class configuracionImagenes {
    public Icon obtenerIcono(String imagen, int index) {
       ImageIcon img = new ImageIcon(getClass().getResource("/Files/imagenes/"+imagen));
        Icon imagenes = new ImageIcon(img.getImage().getScaledInstance(index, index, Image.SCALE_DEFAULT));
      return imagenes;
    }
      public Icon obtenerIcono(String imagen) {
        ImageIcon img = new ImageIcon(getClass().getResource("/Files/imagenes/" + imagen));
        Icon imagenes = new ImageIcon(img.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        return imagenes;
    }

}
