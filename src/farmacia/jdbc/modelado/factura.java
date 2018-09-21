/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.jdbc.modelado;

import java.util.List;

/**
 *
 * @author fecyp
 */
public class factura {
      facturacabecera cabecera;
    List<facturadetalle> detalle;

    public factura(facturacabecera cabecera, List<facturadetalle> detalle) {
        this.cabecera = cabecera;
        this.detalle = detalle;
    }

    public facturacabecera getCabecera() {
        return cabecera;
    }

    public void setCabecera(facturacabecera cabecera) {
        this.cabecera = cabecera;
    }

    public List<facturadetalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<facturadetalle> detalle) {
        this.detalle = detalle;
    }
    
}
