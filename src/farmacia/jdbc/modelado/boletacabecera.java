package farmacia.jdbc.modelado;

import java.util.Date;
import java.util.Objects;

public class boletacabecera {

    private Long idboletacabecera = null;//PK
    private String correlativoboleta;//int 4
    private String numeroboleta; //int 8
    private Date fechaemisionboleta;
    private Long idpersonacliente;//indice
    private Long idempleado;//indice
    private boolean status;

    public boletacabecera(String correlativoboleta, String numeroboleta, Date fechaemisionboleta, Long idpersonacliente, Long idempleado) {
        this.correlativoboleta = correlativoboleta;
        this.numeroboleta = numeroboleta;
        this.fechaemisionboleta = fechaemisionboleta;
        this.idpersonacliente = idpersonacliente;
        this.idempleado = idempleado;
        status = true;
    }

    public Long getIdboletacabecera() {
        return idboletacabecera;
    }

    public void setIdboletacabecera(Long idboletacabecera) {
        this.idboletacabecera = idboletacabecera;
    }

    public String getCorrelativoboleta() {
        return correlativoboleta;
    }

    public void setCorrelativoboleta(String correlativoboleta) {
        this.correlativoboleta = correlativoboleta;
    }

    public String getNumeroboleta() {
        return numeroboleta;
    }

    public void setNumeroboleta(String numeroboleta) {
        this.numeroboleta = numeroboleta;
    }

    public Date getFechaemisionboleta() {
        return fechaemisionboleta;
    }

    public void setFechaemisionboleta(Date fechaemisionboleta) {
        this.fechaemisionboleta = fechaemisionboleta;
    }

    public Long getIdpersonacliente() {
        return idpersonacliente;
    }

    public void setIdpersonacliente(Long idpersonacliente) {
        this.idpersonacliente = idpersonacliente;
    }

    public Long getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Long idempleado) {
        this.idempleado = idempleado;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "boletacabecera{" + "idboletacabecera=" + idboletacabecera + ", correlativoboleta=" + correlativoboleta + ", numeroboleta=" + numeroboleta + ", fechaemisionboleta=" + fechaemisionboleta + ", idpersonacliente=" + idpersonacliente + ", idempleado=" + idempleado + ", status=" + status + '}';
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
        final boletacabecera other = (boletacabecera) obj;
        if (this.correlativoboleta != other.correlativoboleta) {
            return false;
        }
        if (this.numeroboleta != other.numeroboleta) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.idboletacabecera, other.idboletacabecera)) {
            return false;
        }
        if (!Objects.equals(this.fechaemisionboleta, other.fechaemisionboleta)) {
            return false;
        }
        if (!Objects.equals(this.idpersonacliente, other.idpersonacliente)) {
            return false;
        }
        if (!Objects.equals(this.idempleado, other.idempleado)) {
            return false;
        }
        return true;
    }
    
    

    
    
    

    
}
