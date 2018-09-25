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
public class boleta {
    boletacabecera cabecera;
    List<boletadetalle> detalle;

    public boleta(boletacabecera cabecera, List<boletadetalle> detalle) {
        this.cabecera = cabecera;
        this.detalle = detalle;
    }

    public boletacabecera getCabecera() {
        return cabecera;
    }

    public void setCabecera(boletacabecera cabecera) {
        this.cabecera = cabecera;
    }

    public List<boletadetalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<boletadetalle> detalle) {
        this.detalle = detalle;
    }
    
    public boletadetalle getOneDetalle(int index)
    {
        return detalle.get(index);
        
    }
}
