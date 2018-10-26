/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.hibernate.modelo;

import java.util.List;

/**
 *
 * @author fecyp
 */
public class Factura {

    private Facturacabecera cabecera;
    private List<Facturadetalle> detalle;

    public Factura(Facturacabecera cabecera, List<Facturadetalle> detalle) {
        this.cabecera = cabecera;
        this.detalle = detalle;
    }

    public List<Facturadetalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<Facturadetalle> detalle) {
        this.detalle = detalle;
    }

    public Facturacabecera getCabecera() {
        return cabecera;
    }

    public void setCabecera(Facturacabecera cabecera) {
        this.cabecera = cabecera;
    }

}
