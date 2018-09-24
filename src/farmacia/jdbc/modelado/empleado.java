


package farmacia.jdbc.modelado;

import java.util.Date;
import java.util.Objects;


public class empleado {
    
    //la instancia empleado no puede existir si no hay una persona con su identificador
    
    private Long idempleado = null;//PK
    private Long idpersona; //indice
    private Long idtipotrabajador;//indice
    private String login;//32
    private String password;//32
    private Date fechaalta;//nulo
    private boolean status;

    public empleado(Long idpersona, Long idtipotrabajador, String login, String password, Date fechaalta) {
        this.idpersona = idpersona;
        this.idtipotrabajador = idtipotrabajador;
        this.login = login;
        this.password = password;
        this.fechaalta = fechaalta;
        status = true;
    }

    public Long getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Long idempleado) {
        this.idempleado = idempleado;
    }

    public Long getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Long idpersona) {
        this.idpersona = idpersona;
    }

    public Long getIdtipotrabajador() {
        return idtipotrabajador;
    }

    public void setIdtipotrabajador(Long idtipotrabajador) {
        this.idtipotrabajador = idtipotrabajador;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaalta() {
        return fechaalta;
    }

    public void setFechaalta(Date fechaalta) {
        this.fechaalta = fechaalta;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "empleado{" + "idempleado=" + idempleado + ", idpersona=" + idpersona + ", tipotrabajador=" + idtipotrabajador + ", fechaalta=" + fechaalta + ", status=" + status + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.idempleado);
        hash = 79 * hash + Objects.hashCode(this.idpersona);
        hash = 79 * hash + Objects.hashCode(this.idtipotrabajador);
        hash = 79 * hash + Objects.hashCode(this.login);
        hash = 79 * hash + Objects.hashCode(this.password);
        hash = 79 * hash + Objects.hashCode(this.fechaalta);
        hash = 79 * hash + (this.status ? 1 : 0);
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
        final empleado other = (empleado) obj;
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.idempleado, other.idempleado)) {
            return false;
        }
        if (!Objects.equals(this.idpersona, other.idpersona)) {
            return false;
        }
        if (!Objects.equals(this.idtipotrabajador, other.idtipotrabajador)) {
            return false;
        }
        if (!Objects.equals(this.fechaalta, other.fechaalta)) {
            return false;
        }
        return true;
    }
    
    
    

    
    
}
