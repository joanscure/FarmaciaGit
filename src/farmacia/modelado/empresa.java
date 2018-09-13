
package farmacia.modelado;


public class empresa {
    
    private int idempresa;  
    private char[] rucempresa;//11
    private String razonsocial; //64
    private String telefono;//16
    private String direccion;//32
    private boolean status;

    @Override
    public String toString() {
        return "Empresa{" + "rucempresa=" + rucempresa + ", razonsocial=" + razonsocial + ", telefono=" + telefono + ", direccion=" + direccion + '}';
    }

    public empresa(int idempresa, char[] rucempresa, String razonsocial, String telefono, String direccion, boolean status) {
        this.idempresa = idempresa;
        this.rucempresa = rucempresa;
        this.razonsocial = razonsocial;
        this.telefono = telefono;
        this.direccion = direccion;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
        this.idempresa = idempresa;
    }

    public char[] getRucempresa() {
        return rucempresa;
    }

    public void setRucempresa(char[] rucempresa) {
        this.rucempresa = rucempresa;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }



}
