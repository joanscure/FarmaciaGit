
package farmacia.modelado;

public class productodescuento {
    
    private Long idproductodescuento = null;//PK
    private Long idproducto;//indice
    private Long iddescuento; //indice
    private boolean status;

    public productodescuento(Long idproducto, Long iddescuento) {
        this.idproducto = idproducto;
        this.iddescuento = iddescuento;
        status = true;
    }

    public Long getIdproductodescuento() {
        return idproductodescuento;
    }

    public void setIdproductodescuento(Long idproductodescuento) {
        this.idproductodescuento = idproductodescuento;
    }

    public Long getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Long idproducto) {
        this.idproducto = idproducto;
    }

    public Long getIddescuento() {
        return iddescuento;
    }

    public void setIddescuento(Long iddescuento) {
        this.iddescuento = iddescuento;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    

    
}
