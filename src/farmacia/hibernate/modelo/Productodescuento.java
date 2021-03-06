package farmacia.hibernate.modelo;
// Generated Oct 25, 2018 3:35:34 PM by Hibernate Tools 4.3.1



/**
 * Productodescuento generated by hbm2java
 */
public class Productodescuento  implements java.io.Serializable {


     private Integer idproductodescuent;
     private Descuento descuento;
     private Producto producto;
     private boolean status;

    public Productodescuento() {
    }

    public Productodescuento(Descuento descuento, Producto producto, boolean status) {
       this.descuento = descuento;
       this.producto = producto;
       this.status = status;
    }
   
    public Integer getIdproductodescuent() {
        return this.idproductodescuent;
    }
    
    public void setIdproductodescuent(Integer idproductodescuent) {
        this.idproductodescuent = idproductodescuent;
    }
    public Descuento getDescuento() {
        return this.descuento;
    }
    
    public void setDescuento(Descuento descuento) {
        this.descuento = descuento;
    }
    public Producto getProducto() {
        return this.producto;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public boolean isStatus() {
        return this.status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }




}


