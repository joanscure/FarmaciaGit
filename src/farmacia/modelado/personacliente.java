
package farmacia.modelado;

public class personacliente {
    
    private Long idpersonacliente = null;//PK
    private Long idpersona;//indice
    private boolean status;

    public personacliente(Long idpersona) {
        this.idpersona = idpersona;
        status = true;
    }

    public Long getIdpersonacliente() {
        return idpersonacliente;
    }

    public void setIdpersonacliente(Long idpersonacliente) {
        this.idpersonacliente = idpersonacliente;
    }

    public Long getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Long idpersona) {
        this.idpersona = idpersona;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    

    
}
