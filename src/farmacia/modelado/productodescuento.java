
package farmacia.modelado;

public class productodescuento {
    
    private int idproducto;//indice
    private int iddescuento; //indice
    private boolean status;

    public productodescuento(int idproducto, int iddescuento) {
        this.idproducto = idproducto;
        this.iddescuento = iddescuento;
        status = true;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getIddescuento() {
        return iddescuento;
    }

    public void setIddescuento(int iddescuento) {
        this.iddescuento = iddescuento;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    
    
}
