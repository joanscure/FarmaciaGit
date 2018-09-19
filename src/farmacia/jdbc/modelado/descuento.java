/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.jdbc.modelado;

import java.util.Objects;

/**
 *
 * @author Cliente
 */
public class descuento {
    
    private Long iddescuento = null;//PK
    private String nombredescuento; //32
    private String condicion;//32
    private double porcentaje; //2,2
    private String descripciondescuento; // 64 NULO
    private boolean status;

    public descuento(String nombredescuento, String condicion, double porcentaje, String descripciondescuento) {
        this.nombredescuento = nombredescuento;
        this.condicion = condicion;
        this.porcentaje = porcentaje;
        this.descripciondescuento = descripciondescuento;
        status = true;
    }

    public Long getIddescuento() {
        return iddescuento;
    }

    public void setIddescuento(Long iddescuento) {
        this.iddescuento = iddescuento;
    }

    public String getNombredescuento() {
        return nombredescuento;
    }

    public void setNombredescuento(String nombredescuento) {
        this.nombredescuento = nombredescuento;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getDescripciondescuento() {
        return descripciondescuento;
    }

    public void setDescripciondescuento(String descripciondescuento) {
        this.descripciondescuento = descripciondescuento;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "descuento{" + "iddescuento=" + iddescuento + ", nombredescuento=" + nombredescuento + ", condicion=" + condicion + ", porcentaje=" + porcentaje + ", descripciondescuento=" + descripciondescuento + ", status=" + status + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.iddescuento);
        hash = 17 * hash + Objects.hashCode(this.nombredescuento);
        hash = 17 * hash + Objects.hashCode(this.condicion);
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.porcentaje) ^ (Double.doubleToLongBits(this.porcentaje) >>> 32));
        hash = 17 * hash + Objects.hashCode(this.descripciondescuento);
        hash = 17 * hash + (this.status ? 1 : 0);
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
        final descuento other = (descuento) obj;
        if (Double.doubleToLongBits(this.porcentaje) != Double.doubleToLongBits(other.porcentaje)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.nombredescuento, other.nombredescuento)) {
            return false;
        }
        if (!Objects.equals(this.condicion, other.condicion)) {
            return false;
        }
        if (!Objects.equals(this.descripciondescuento, other.descripciondescuento)) {
            return false;
        }
        if (!Objects.equals(this.iddescuento, other.iddescuento)) {
            return false;
        }
        return true;
    }
    
    

   
    
}
