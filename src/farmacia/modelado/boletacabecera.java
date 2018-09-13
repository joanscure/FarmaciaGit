package farmacia.modelado;

import java.util.Date;

public class boletacabecera {

    private Long idboletacabecera = null;//PK
    private int correlativoboleta;//int 4
    private int numeroboleta; //int 8
    private Date fechaemisionboleta;
    private Long idpersonacliente;//indice
    private Long idempleado;//indice
    private boolean status;

    public boletacabecera(int correlativoboleta, int numeroboleta, Date fechaemisionboleta, Long idpersonacliente, Long idempleado) {
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

    public int getCorrelativoboleta() {
        return correlativoboleta;
    }

    public void setCorrelativoboleta(int correlativoboleta) {
        this.correlativoboleta = correlativoboleta;
    }

    public int getNumeroboleta() {
        return numeroboleta;
    }

    public void setNumeroboleta(int numeroboleta) {
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
    
    

    
    
    

    
}
