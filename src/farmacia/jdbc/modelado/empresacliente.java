
package farmacia.jdbc.modelado;

import java.util.Date;
import java.util.Objects;

public class empresacliente {
    
    private Long idempresacliente = null;//PK
    private Long idempresa;//indice
    private Date fecharegistro;//NULO
    private boolean status;

    public empresacliente(Long idempresa, Date fecharegistro) {
        this.idempresa = idempresa;
        this.fecharegistro = fecharegistro;
        status = true;
    }

    public Long getIdempresacliente() {
        return idempresacliente;
    }

    public void setIdempresacliente(Long idempresacliente) {
        this.idempresacliente = idempresacliente;
    }

    public Long getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(Long idempresa) {
        this.idempresa = idempresa;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "empresacliente{" + "idempresacliente=" + idempresacliente + ", idempresa=" + idempresa + ", fecharegistro=" + fecharegistro + ", status=" + status + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.idempresacliente);
        hash = 29 * hash + Objects.hashCode(this.idempresa);
        hash = 29 * hash + Objects.hashCode(this.fecharegistro);
        hash = 29 * hash + (this.status ? 1 : 0);
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
        final empresacliente other = (empresacliente) obj;
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.idempresacliente, other.idempresacliente)) {
            return false;
        }
        if (!Objects.equals(this.idempresa, other.idempresa)) {
            return false;
        }
        if (!Objects.equals(this.fecharegistro, other.fecharegistro)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    

    
}
