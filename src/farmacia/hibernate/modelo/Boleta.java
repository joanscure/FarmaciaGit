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
public class Boleta {
    private Boletacabecera cabecera;
    private List<Boletadetalle> detalle;

    public Boleta(Boletacabecera cabecera, List<Boletadetalle> detalle) {
        this.cabecera = cabecera;
        this.detalle = detalle;
    }

    public List<Boletadetalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<Boletadetalle> detalle) {
        this.detalle = detalle;
    }

    public Boletacabecera getCabecera() {
        return cabecera;
    }

    public void setCabecera(Boletacabecera cabecera) {
        this.cabecera = cabecera;
    }

    
}
