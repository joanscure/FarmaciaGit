/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.calculos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JTable;

/**
 *
 * @author fecyp
 */
public class calculosTotales {
    public static double sumaigv(JTable tabla,int index)
    {
        double suma=0.0;
        for (int i = 0; i <tabla.getRowCount(); i++) {
            suma+=Double.parseDouble((String) tabla.getValueAt(i, index));
            
        }
        BigDecimal bd = new BigDecimal(suma);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
     public static double sumasubtotal(JTable tabla,int index)
    {
        double suma=0.0;
        for (int i = 0; i <tabla.getRowCount(); i++) {
            suma+=Double.parseDouble((String) tabla.getValueAt(i, index));
            
        }
        BigDecimal bd = new BigDecimal(suma);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
     
       public static double sumatotal(double igv,double desc,double subtotal)
    {
        double suma=igv+desc+subtotal;
        BigDecimal bd = new BigDecimal(suma);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
        
        return bd.doubleValue();
    }
}
