/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.calculos;

import farmacia.vista.mantenimientoTipoUsuario.ListadoTipousuario;

/**
 *
 * @author fecyp
 */
public class calculosTipousuario {
    public static Long obtenerIdtipousuario(String nombre)
    {
        Long id=null;
        for (int i = 0; i < ListadoTipousuario.tabla.getRowCount(); i++) {
           if(nombre.equals(ListadoTipousuario.tabla.getValueAt(i, 1)))
           {
               id=new Long((Long) ListadoTipousuario.tabla.getValueAt(i, 0)+"");
               
           }
        }
        return id;
    }
}
