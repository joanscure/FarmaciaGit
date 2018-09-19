
package farmacia.jdbc.modelado;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "personacliente{" + "idpersonacliente=" + idpersonacliente + ", idpersona=" + idpersona + ", status=" + status + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.idpersonacliente);
        hash = 37 * hash + Objects.hashCode(this.idpersona);
        hash = 37 * hash + (this.status ? 1 : 0);
        return hash;
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
        final personacliente other = (personacliente) obj;
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.idpersonacliente, other.idpersonacliente)) {
            return false;
        }
        if (!Objects.equals(this.idpersona, other.idpersona)) {
            return false;
        }
        return true;
    }
    
    
}
