
package farmacia.modelado;

import java.util.Date;


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
    
    

    
}
