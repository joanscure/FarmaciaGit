
package farmacia.modelado;

public class producto {
    
    private int idproducto;
    private String nombreproducto;//32
    private String descripcionproducto;//128
    private String dosisproducto; // 32
    private double precioventa;//5,2
    private double igv;//2,2
    private double preciofinal;//5,2
    private int stock;
    private boolean status;

    @Override
    public String toString() {
        return "Producto{" + "nombreproducto=" + nombreproducto + ", descripcionproducto=" + descripcionproducto + ", dosisproducto=" + dosisproducto + ", preciofinal=" + preciofinal + ", stock=" + stock + '}';
    }

    public producto(int idproducto, String nombreproducto, String descripcionproducto, String dosisproducto, double precioventa, double igv, double preciofinal, int stock) {
        this.idproducto = idproducto;
        this.nombreproducto = nombreproducto;
        this.descripcionproducto = descripcionproducto;
        this.dosisproducto = dosisproducto;
        this.precioventa = precioventa;
        this.igv = igv;
        this.preciofinal = preciofinal;
        this.stock = stock;
        status = true;
    }

  

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
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
}


