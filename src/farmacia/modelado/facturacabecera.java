
package farmacia.modelado;

import java.util.Date;


public class facturacabecera {
    
    private int idfacturacabecera;
    private int correlativofactura;//int 4
    private int numerofactura; //int 8
    private Date fechaemisionfactura;
    private int idempresacliente;//indice
    private int idempleado;//indice
    private boolean status;

    public facturacabecera(int idfacturacabecera, int correlativofactura, int numerofactura, Date fechaemisionfactura, int idempresacliente, int idempleado) {
        this.idfacturacabecera = idfacturacabecera;
        this.correlativofactura = correlativofactura;
        this.numerofactura = numerofactura;
        this.fechaemisionfactura = fechaemisionfactura;
        this.idempresacliente = idempresacliente;
        this.idempleado = idempleado;
        status = true;
    }

    public int getIdfacturacabecera() {
        return idfacturacabecera;
    }

    public void setIdfacturacabecera(int idfacturacabecera) {
        this.idfacturacabecera = idfacturacabecera;
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

    public int getIdempresacliente() {
        return idempresacliente;
    }

    public void setIdempresacliente(int idempresacliente) {
        this.idempresacliente = idempresacliente;
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
