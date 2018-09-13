
package farmacia.modelado;

import java.util.Date;

public class empresaclietne {
    
    private int idempresacliente;
    private int idempresa;//indice
    private Date fecharegistro;//NULO
    private boolean status;

    public empresaclietne(int idempresacliente, int idempresa, Date fecharegistro) {
        this.idempresacliente = idempresacliente;
        this.idempresa = idempresa;
        this.fecharegistro = fecharegistro;
        status = true;
    }

    public int getIdempresacliente() {
        return idempresacliente;
    }

    public void setIdempresacliente(int idempresacliente) {
        this.idempresacliente = idempresacliente;
    }

    public int getIdempresa() {
        return idempresa;
    }

    public void setIdempresa(int idempresa) {
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
}
