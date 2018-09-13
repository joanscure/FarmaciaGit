
package farmacia.modelado;


public class facturadetalle {
    
    private Long idfacturadetalle = null;//PK
    private Long idfacturacabecera; //indice
    private Long idproducto; //indice
    private double cantidad; //5,2
    private double subtotal; //5,2
    private boolean status; 

    public facturadetalle(Long idfacturacabecera, Long idproducto, double cantidad, double subtotal) {
        this.idfacturacabecera = idfacturacabecera;
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
    
    

    
}
