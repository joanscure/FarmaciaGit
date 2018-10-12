/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.calculos;

import farmacia.vista.frmprincipal;
import farmacia.vista.mantenimientoTipoUsuario.ListadoTipousuario;

/**
 *
 * @author fecyp
 */
public class Permisos {

    public static boolean verificarEliminar( int index,String ocupacion) {
        //11 cliente
        //10 productos
        //12  empleados
        //13 tipousuario
        if(index==14)
        {
            index=11;
        }
        int indexRow = 0;
        boolean valid = false;
        for (int i = 0; i < ListadoTipousuario.tabla.getRowCount(); i++) {
            if (ocupacion.equals(ListadoTipousuario.tabla.getValueAt(i, 1))) {
                indexRow = i;
            }
        }
        if ((boolean)ListadoTipousuario.tabla.getValueAt(indexRow, index)==true) {
            valid = true;
        }
        return valid;
    }
    
   
}
