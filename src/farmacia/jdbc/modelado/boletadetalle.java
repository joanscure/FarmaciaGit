/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.jdbc.modelado;

import java.util.Objects;

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
        return "boletadetalle{" + "idboletadetalle=" + idboletadetalle + ", idboletacabecera=" + idboletacabecera + ", idproducto=" + idproducto + ", cantidad=" + cantidad + ", subtotal=" + subtotal + ", status=" + status + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.idboletadetalle);
        hash = 43 * hash + Objects.hashCode(this.idboletacabecera);
        hash = 43 * hash + Objects.hashCode(this.idproducto);
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.cantidad) ^ (Double.doubleToLongBits(this.cantidad) >>> 32));
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.subtotal) ^ (Double.doubleToLongBits(this.subtotal) >>> 32));
        hash = 43 * hash + (this.status ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final boletadetalle other = (boletadetalle) obj;
        if (Double.doubleToLongBits(this.cantidad) != Double.doubleToLongBits(other.cantidad)) {
            return false;
        }
        if (Double.doubleToLongBits(this.subtotal) != Double.doubleToLongBits(other.subtotal)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.idboletadetalle, other.idboletadetalle)) {
            return false;
        }
        if (!Objects.equals(this.idboletacabecera, other.idboletacabecera)) {
            return false;
        }
        if (!Objects.equals(this.idproducto, other.idproducto)) {
            return false;
        }
        return true;
    }

    
    

    
    

    
}
