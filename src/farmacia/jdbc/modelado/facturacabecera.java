
package farmacia.jdbc.modelado;

import java.util.Date;
import java.util.Objects;


public class facturacabecera {
    
    private Long idfacturacabecera = null;//PK
    private Long idempresacliente;//indice
    private Long idempleado;//indice
    private String correlativofactura;//int 4
    private String numerofactura; //int 8
    private Date fechaemisionfactura;
    private boolean status;

    public facturacabecera(Long idempresacliente, Long idempleado, String correlativofactura, String numerofactura, Date fechaemisionfactura) {
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

    public String getCorrelativofactura() {
        return correlativofactura;
    }

    public void setCorrelativofactura(String correlativofactura) {
        this.correlativofactura = correlativofactura;
    }

    public String getNumerofactura() {
        return numerofactura;
    }

    public void setNumerofactura(String numerofactura) {
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
