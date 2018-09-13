/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.modelado;

/**
 *
 * @author Cliente
 */
public class boletadetalle {
    
    private Long idboletadetalle = null;//PK
    private Long idboletacabecera; //indice
    private Long idproducto; //indice
    private double cantidad; //5,2
    private double subtotal; //5,2
    private boolean status; 

    public boletadetalle(Long idboletacabecera, Long idproducto, double cantidad, double subtotal) {
        this.idboletacabecera = idboletacabecera;
        this.idproducto = idproducto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        status = true;
    }

    public Long getIdboletadetalle() {
        return idboletadetalle;
    }

    public void setIdboletadetalle(Long idboletadetalle) {
        this.idboletadetalle = idboletadetalle;
    }

    public Long getIdboletacabecera() {
        return idboletacabecera;
    }

    public void setIdboletacabecera(Long idboletacabecera) {
        this.idboletacabecera = idboletacabecera;
    }

    public Long getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Long idproducto) {
        this.idproducto = idproducto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "boletadetalle{" + "idproducto=" + idproducto + ", cantidad=" + cantidad + ", subtotal=" + subtotal + '}';
    }
    
    

    
    

    
}
