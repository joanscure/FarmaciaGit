/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.calculos;

import javax.swing.JTable;

/**
 *
 * @author fecyp
 */
public class configuracionesTabla {

    public void ocultarColumnas(JTable tabla, int[] lista) {

        int a = 0;
        for (int i = 0; i < tabla.getModel().getColumnCount(); i++) {
            if (lista.length>a&&i == lista[a]) {
                tabla.getColumnModel().getColumn(i).setMaxWidth(0);
                tabla.getColumnModel().getColumn(i).setMinWidth(0);
                tabla.getColumnModel().getColumn(i).setPreferredWidth(0);
                a++;
            }

        }

    }

    public void fijarTamaño(JTable tabla,int[] tamaño) {

        for (int i = 0; i < tabla.getModel().getColumnCount(); i++) {
                tabla.getColumnModel().getColumn(i).setMaxWidth(tamaño[i]);
                tabla.getColumnModel().getColumn(i).setMinWidth(tamaño[i]);
                tabla.getColumnModel().getColumn(i).setPreferredWidth(tamaño[i]);

        }
    }
}
