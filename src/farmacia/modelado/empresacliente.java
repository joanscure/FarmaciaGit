
package farmacia.modelado;

import java.util.Date;

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
    
    
    
    

    
}
