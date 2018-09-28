
package farmacia.jdbc.modelado;

import java.util.Objects;


public class facturadetalle {
    
    private Long idfacturadetalle = null;//PK
    private Long idfacturacabecera; //indice
    private Long idproducto; //indice
    private double cantidad; //5,2
    private double subtotal; //5,2
    private boolean status; 

    public facturadetalle( Long idproducto, double cantidad, double subtotal) {
        this.idproducto = idproducto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        status = true;
    }

    public Long getIdfacturadetalle() {
        return idfacturadetalle;
    }

    public void setIdfacturadetalle(Long idfacturadetalle) {
        this.idfacturadetalle = idfacturadetalle;
    }

    public Long getIdfacturacabecera() {
        return idfacturacabecera;
    }

    public void setIdfacturacabecera(Long idfacturacabecera) {
        this.idfacturacabecera = idfacturacabecera;
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
        return "facturadetalle{" + "idfacturadetalle=" + idfacturadetalle + ", idfacturacabecera=" + idfacturacabecera + ", idproducto=" + idproducto + ", cantidad=" + cantidad + ", subtotal=" + subtotal + ", status=" + status + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.idfacturadetalle);
        hash = 79 * hash + Objects.hashCode(this.idfacturacabecera);
        hash = 79 * hash + Objects.hashCode(this.idproducto);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.cantidad) ^ (Double.doubleToLongBits(this.cantidad) >>> 32));
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.subtotal) ^ (Double.doubleToLongBits(this.subtotal) >>> 32));
        hash = 79 * hash + (this.status ? 1 : 0);
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
        final facturadetalle other = (facturadetalle) obj;
        if (Double.doubleToLongBits(this.cantidad) != Double.doubleToLongBits(other.cantidad)) {
            return false;
        }
        if (Double.doubleToLongBits(this.subtotal) != Double.doubleToLongBits(other.subtotal)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.idfacturadetalle, other.idfacturadetalle)) {
            return false;
        }
        if (!Objects.equals(this.idfacturacabecera, other.idfacturacabecera)) {
            return false;
        }
        if (!Objects.equals(this.idproducto, other.idproducto)) {
            return false;
        }
        return true;
    }
    
    

    
}
