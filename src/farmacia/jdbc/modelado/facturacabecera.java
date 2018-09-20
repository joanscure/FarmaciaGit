
package farmacia.jdbc.modelado;

import java.util.Date;
import java.util.Objects;


public class facturacabecera {
    
    private Long idfacturacabecera = null;//PK
    private Long idempresacliente;//indice
    private Long idempleado;//indice
    private int correlativofactura;//int 4
    private int numerofactura; //int 8
    private Date fechaemisionfactura;
    private boolean status;

    public facturacabecera(Long idempresacliente, Long idempleado, int correlativofactura, int numerofactura, Date fechaemisionfactura) {
        this.idempresacliente = idempresacliente;
        this.idempleado = idempleado;
        this.correlativofactura = correlativofactura;
        this.numerofactura = numerofactura;
        this.fechaemisionfactura = fechaemisionfactura;
        status = true;
    }

    public Long getIdfacturacabecera() {
        return idfacturacabecera;
    }

    public void setIdfacturacabecera(Long idfacturacabecera) {
        this.idfacturacabecera = idfacturacabecera;
    }

    public Long getIdempresacliente() {
        return idempresacliente;
    }

    public void setIdempresacliente(Long idempresacliente) {
        this.idempresacliente = idempresacliente;
    }

    public Long getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Long idempleado) {
        this.idempleado = idempleado;
    }

    public int getCorrelativofactura() {
        return correlativofactura;
    }

    public void setCorrelativofactura(int correlativofactura) {
        this.correlativofactura = correlativofactura;
    }

    public int getNumerofactura() {
        return numerofactura;
    }

    public void setNumerofactura(int numerofactura) {
        this.numerofactura = numerofactura;
    }

    public Date getFechaemisionfactura() {
        return fechaemisionfactura;
    }

    public void setFechaemisionfactura(Date fechaemisionfactura) {
        this.fechaemisionfactura = fechaemisionfactura;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "facturacabecera{" + "idfacturacabecera=" + idfacturacabecera + ", idempresacliente=" + idempresacliente + ", idempleado=" + idempleado + ", correlativofactura=" + correlativofactura + ", numerofactura=" + numerofactura + ", fechaemisionfactura=" + fechaemisionfactura + ", status=" + status + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.idfacturacabecera);
        hash = 23 * hash + Objects.hashCode(this.idempresacliente);
        hash = 23 * hash + Objects.hashCode(this.idempleado);
        hash = 23 * hash + this.correlativofactura;
        hash = 23 * hash + this.numerofactura;
        hash = 23 * hash + Objects.hashCode(this.fechaemisionfactura);
        hash = 23 * hash + (this.status ? 1 : 0);
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
        final facturacabecera other = (facturacabecera) obj;
        if (this.correlativofactura != other.correlativofactura) {
            return false;
        }
        if (this.numerofactura != other.numerofactura) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.idfacturacabecera, other.idfacturacabecera)) {
            return false;
        }
        if (!Objects.equals(this.idempresacliente, other.idempresacliente)) {
            return false;
        }
        if (!Objects.equals(this.idempleado, other.idempleado)) {
            return false;
        }
        if (!Objects.equals(this.fechaemisionfactura, other.fechaemisionfactura)) {
            return false;
        }
        return true;
    }
    
}