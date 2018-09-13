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
    
    private int idboletacabecera; //indice
    private int idproducto; //indice
    private double cantidad; //5,2
    private double subtotal; //5,2
    private boolean status; 

    public boletadetalle(int idboletacabecera, int idproducto, double cantidad, double subtotal) {
        this.idboletacabecera = idboletacabecera;
        this.idproducto = idproducto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        status = true;
    }

    public int getIdboletacabecera() {
        return idboletacabecera;
    }

    public void setIdboletacabecera(int idboletacabecera) {
        this.idboletacabecera = idboletacabecera;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
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
}
