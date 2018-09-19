
package farmacia.jdbc.modelado;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "productodescuento{" + "idproductodescuento=" + idproductodescuento + ", idproducto=" + idproducto + ", iddescuento=" + iddescuento + ", status=" + status + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.idproductodescuento);
        hash = 79 * hash + Objects.hashCode(this.idproducto);
        hash = 79 * hash + Objects.hashCode(this.iddescuento);
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
        final productodescuento other = (productodescuento) obj;
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.idproductodescuento, other.idproductodescuento)) {
            return false;
        }
        if (!Objects.equals(this.idproducto, other.idproducto)) {
            return false;
        }
        if (!Objects.equals(this.iddescuento, other.iddescuento)) {
            return false;
        }
        return true;
    }
    
    

    
}
