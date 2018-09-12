/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.calculos;

import farmacia.vista.frmprincipal;
import farmacia.vista.mantenimientoTipoUsuario.ListadoTipousuario;
import farmacia.vista.mantenimientoTipoUsuario.frmTipousuario;

/**
 *
 * @author fecyp
 */
public class Permisos {

    public boolean verificarClienteEliminar() {
        int indexRow = 0;
        boolean valid = false;
        for (int i = 0; i < ListadoTipousuario.tabla.getRowCount(); i++) {
            if (frmprincipal.jlocupacion.equals(ListadoTipousuario.tabla.getValueAt(i, 1))) {
                indexRow = i;
            }
        }
       
            if (ListadoTipousuario.tabla.getValueAt(indexRow, 11).equals(1)) {
                valid=true;
            }
      
        return valid;
    }
}
