


package farmacia.modelado;

import java.util.Date;


public class empleado {
    
    //la instancia empleado no puede existir si no hay una persona con su identificador
    
    private Long idempleado = null;//PK
    private Long idpersona; //indice
    private Long tipotrabajador;//indice
    private String login;//32
    private String password;//32
    private Date fechaalta;//nulo
    private boolean status;

    public empleado(Long idpersona, Long tipotrabajador, String login, String password, Date fechaalta) {
        this.idpersona = idpersona;
        this.tipotrabajador = tipotrabajador;
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

    public Long getTipotrabajador() {
        return tipotrabajador;
    }

    public void setTipotrabajador(Long tipotrabajador) {
        this.tipotrabajador = tipotrabajador;
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
    
    
    

    
    
}
