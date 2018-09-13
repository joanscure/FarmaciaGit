/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmacia.modelado;

/**
 *
 * @author Cliente
 */
public class descuento {
    
    private int iddescuento;
    private String nombredescuento; //32
    private String condicion;//32
    private double porcentaje; //2,2
    private String descripciondescuento; // 64 NULO
    private boolean status;

    public descuento(int iddescuento, String nombredescuento, String condicion, double porcentaje, String descripciondescuento) {
        this.iddescuento = iddescuento;
        this.nombredescuento = nombredescuento;
        this.condicion = condicion;
        this.porcentaje = porcentaje;
        this.descripciondescuento = descripciondescuento;
        status = true;
    }

    public int getIddescuento() {
        return iddescuento;
    }

    public void setIddescuento(int iddescuento) {
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
    
    
    
}
