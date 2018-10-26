
package farmacia.jdbc.modelado;

import java.util.Objects;

public class producto {
    
    private Long idproducto = null;//PK
    private String nombreproducto;//32
    private String descripcionproducto;//128
    private String dosisproducto; // 32
    private double precioventa;//5,2
    private double igv;//2,2
    private double preciofinal;//5,2
    private int stock;
    private boolean status;

    public producto(String nombreproducto, String descripcionproducto, String dosisproducto, double precioventa, double igv, double preciofinal, int stock) {
        this.nombreproducto = nombreproducto;
        this.descripcionproducto = descripcionproducto;
        this.dosisproducto = dosisproducto;
        this.precioventa = precioventa;
        this.igv = igv;
        this.preciofinal = preciofinal;
        this.stock = stock;
        status = true;
    }

    public producto() {
    }


    public Long getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Long idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public String getDescripcionproducto() {
        return descripcionproducto;
    }

    public void setDescripcionproducto(String descripcionproducto) {
        this.descripcionproducto = descripcionproducto;
    }

    public String getDosisproducto() {
        return dosisproducto;
    }

    public void setDosisproducto(String dosisproducto) {
        this.dosisproducto = dosisproducto;
    }

    public double getPrecioventa() {
        return precioventa;
    }

    public void setPrecioventa(double precioventa) {
        this.precioventa = precioventa;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getPreciofinal() {
        return preciofinal;
    }

    public void setPreciofinal(double preciofinal) {
        this.preciofinal = preciofinal;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "producto{" + "nombreproducto=" + nombreproducto + ", descripcionproducto=" + descripcionproducto + ", dosisproducto=" + dosisproducto + ", preciofinal=" + preciofinal + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.idproducto);
        hash = 71 * hash + Objects.hashCode(this.nombreproducto);
        hash = 71 * hash + Objects.hashCode(this.descripcionproducto);
        hash = 71 * hash + Objects.hashCode(this.dosisproducto);
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.precioventa) ^ (Double.doubleToLongBits(this.precioventa) >>> 32));
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.igv) ^ (Double.doubleToLongBits(this.igv) >>> 32));
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.preciofinal) ^ (Double.doubleToLongBits(this.preciofinal) >>> 32));
        hash = 71 * hash + this.stock;
        hash = 71 * hash + (this.status ? 1 : 0);
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
        final producto other = (producto) obj;
        if (Double.doubleToLongBits(this.precioventa) != Double.doubleToLongBits(other.precioventa)) {
            return false;
        }
        if (Double.doubleToLongBits(this.igv) != Double.doubleToLongBits(other.igv)) {
            return false;
        }
        if (Double.doubleToLongBits(this.preciofinal) != Double.doubleToLongBits(other.preciofinal)) {
            return false;
        }
        if (this.stock != other.stock) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.nombreproducto, other.nombreproducto)) {
            return false;
        }
        if (!Objects.equals(this.descripcionproducto, other.descripcionproducto)) {
            return false;
        }
        if (!Objects.equals(this.dosisproducto, other.dosisproducto)) {
            return false;
        }
        if (!Objects.equals(this.idproducto, other.idproducto)) {
            return false;
        }
        return true;
    }
    
    

    
}


