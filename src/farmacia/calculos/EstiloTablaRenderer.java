/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.calculos;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Rojeru San CL
 */
public class EstiloTablaRenderer extends DefaultTableCellRenderer{
    private Component componenete;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        componenete = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.       
        this.setHorizontalAlignment(0);
        this.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(0, 0, 0)));

        if(row%2 == 0){
            componenete.setForeground(Color.BLACK);
            componenete.setBackground(new Color(232, 232, 232));//cambio de color de cada colummnas
        }else{
            componenete.setForeground(Color.BLACK);// cambio de color de la segunda columna
            componenete.setBackground(Color.WHITE);
        }
        if(isSelected){
            componenete.setForeground(Color.WHITE);//color de letras seleccionadas
            componenete.setBackground(new Color(28, 134, 238));//color de seleccion
        }
        return componenete;
        
    }
    
    
}
