
package farmacia.modelado;

import java.util.Date;


public class boletacabecera {
    
    private int idboletacabecera;
    private int correlativoboleta;//int 4
    private int numeroboleta; //int 8
    private Date fechaemisionboleta;
    private int idpersonacliente;//indice
    private int idempleado;//indice
    private boolean status;

    @Override
    public String toString() {
        return "boletacabecera{" + "correlativoboleta=" + correlativoboleta + ", numeroboleta=" + numeroboleta + ", fechaemision=" + fechaemisionboleta + ", idpersonacliente=" + idpersonacliente + ", idempleado=" + idempleado + '}';
    }

    public boletacabecera(int idboletacabecera, int correlativoboleta, int numeroboleta, Date fechaemision, int idpersonacliente, int idempleado) {
        this.idboletacabecera = idboletacabecera;
        this.correlativoboleta = correlativoboleta;
        this.numeroboleta = numeroboleta;
        this.fechaemisionboleta = fechaemision;
        this.idpersonacliente = idpersonacliente;
        this.idempleado = idempleado;
        status = true;
    }

    public int getIdboletacabecera() {
        return idboletacabecera;
    }

    public void setIdboletacabecera(int idboletacabecera) {
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

    public Date getFechaemision() {
        return fechaemisionboleta;
    }

    public void setFechaemision(Date fechaemision) {
        this.fechaemisionboleta = fechaemision;
    }

    public int getIdpersonacliente() {
        return idpersonacliente;
    }

    public void setIdpersonacliente(int idpersonacliente) {
        this.idpersonacliente = idpersonacliente;
    }

    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
