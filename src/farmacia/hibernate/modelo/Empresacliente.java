package farmacia.hibernate.modelo;
// Generated Oct 25, 2018 3:35:34 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Empresacliente generated by hbm2java
 */
public class Empresacliente  implements java.io.Serializable {


     private Integer idempresacliente;
     private Empresa empresa;
     private Date fecharegistro;
     private boolean status;
     private Set facturacabeceras = new HashSet(0);

    public Empresacliente() {
    }

	
    public Empresacliente(Empresa empresa, boolean status) {
        this.empresa = empresa;
        this.status = status;
    }
    public Empresacliente(Empresa empresa, Date fecharegistro, boolean status, Set facturacabeceras) {
       this.empresa = empresa;
       this.fecharegistro = fecharegistro;
       this.status = status;
       this.facturacabeceras = facturacabeceras;
    }
   
    public Integer getIdempresacliente() {
        return this.idempresacliente;
    }
    
    public void setIdempresacliente(Integer idempresacliente) {
        this.idempresacliente = idempresacliente;
    }
    public Empresa getEmpresa() {
        return this.empresa;
    }
    
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    public Date getFecharegistro() {
        return this.fecharegistro;
    }
    
    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }
    public boolean isStatus() {
        return this.status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }
    public Set getFacturacabeceras() {
        return this.facturacabeceras;
    }
    
    public void setFacturacabeceras(Set facturacabeceras) {
        this.facturacabeceras = facturacabeceras;
    }




}


