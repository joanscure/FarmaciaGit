
package farmacia.jdbc.modelado;

import java.util.Arrays;
import java.util.Objects;


public class empresa {
    
    private Long idempresa = null;//PK
    private char[] rucempresa;//11
    private String razonsocial; //64
    private String telefono;//16
    private String direccion;//32
    private boolean status;

    public empresa(char[] rucempresa, String razonsocial, String telefono, String direccion) {
        this.rucempresa = rucempresa;
        this.razonsocial = razonsocial;
        this.telefono = telefono;
        this.direccion = direccion;
        status = true;
    }

    public empresa() {
    }

    public Long getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Long idempresa) {
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "empresa{" + "idempresa=" + idempresa + ", rucempresa=" + rucempresa + ", razonsocial=" + razonsocial + ", telefono=" + telefono + ", direccion=" + direccion + ", status=" + status + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.idempresa);
        hash = 89 * hash + Arrays.hashCode(this.rucempresa);
        hash = 89 * hash + Objects.hashCode(this.razonsocial);
        hash = 89 * hash + Objects.hashCode(this.telefono);
        hash = 89 * hash + Objects.hashCode(this.direccion);
        hash = 89 * hash + (this.status ? 1 : 0);
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
        final empresa other = (empresa) obj;
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.razonsocial, other.razonsocial)) {
            return false;
        }
        if (!Objects.equals(this.telefono, other.telefono)) {
            return false;
        }
        if (!Objects.equals(this.direccion, other.direccion)) {
            return false;
        }
        if (!Objects.equals(this.idempresa, other.idempresa)) {
            return false;
        }
        if (!Arrays.equals(this.rucempresa, other.rucempresa)) {
            return false;
        }
        return true;
    }
    
    
    

    
}
