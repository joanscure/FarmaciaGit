
package farmacia.modelado;

public class personacliente {
    
    private int idpersonacliente;
    private int idpersona;//indice
    private boolean status;

    public personacliente(int idpersonacliente, int idpersona) {
        this.idpersonacliente = idpersonacliente;
        this.idpersona = idpersona;
        status = true;
    }

    public int getIdpersonacliente() {
        return idpersonacliente;
    }

    public void setIdpersonacliente(int idpersonacliente) {
        this.idpersonacliente = idpersonacliente;
    }

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    
    
    
}
