


package farmacia.modelado;

import java.util.Date;


public class empleado extends persona {
    
    //la instancia empleado no puede existir si no hay una persona con su identificador
    
    private int idempleado;
    private int idpersona;
    private String login;//32
    private String password;//32
    private Date fechaalta;//nulo
    private int tipotrabajador;
    private boolean status;

    @Override
    public String toString() {
        return "Empleado{" + "login=" + login + ", tipotrabajador=" + tipotrabajador + '}';
    }

        
    public empleado(int idempleado, int idpersona, String login, String password, Date fechaalta, int tipotrabajador) {
        this.idempleado = idempleado;
        this.idpersona = idpersona;
        this.login = login;
        this.password = password;
        this.fechaalta = fechaalta;
        this.tipotrabajador = tipotrabajador;
        status = true;
    }
   
    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
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

    public int getTipotrabajador() {
        return tipotrabajador;
    }

    public void setTipotrabajador(int tipotrabajador) {
        this.tipotrabajador = tipotrabajador;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
