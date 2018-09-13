
package farmacia.modelado;


public class facturadetalle {
    
    private int idfacturacabecera; //indice
    private int idproducto; //indice
    private double cantidad; //5,2
    private double subtotal; //5,2
    private boolean status; 

    public facturadetalle(int idfacturacabecera, int idproducto, double cantidad, double subtotal) {
        this.idfacturacabecera = idfacturacabecera;
        this.idproducto = idproducto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        status = true;
    }

    public int getIdfacturacabecera() {
        return idfacturacabecera;
    }

    public void setIdfacturacabecera(int idfacturacabecera) {
        this.idfacturacabecera = idfacturacabecera;
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
