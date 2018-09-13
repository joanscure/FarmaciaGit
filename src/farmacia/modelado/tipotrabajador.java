

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
public class tipotrabajador {
    private int idtipotrabajador;
    private String nombretipotrabajador;//32 NULO
    private boolean accederventas;
    private boolean accederproductos;
    private boolean accederclientes;
    private boolean accederconsultas;
    private boolean accederempleados;
    private boolean accedertipousuario;
    private boolean accedercambioclave;
    private boolean accederanulaciones;
    private boolean accedereliminarproducto;
    private boolean accedereliminarcliente;
    private boolean accedereliminarempleado;
    private boolean accedereliminartipoempleado;
    private boolean status;

    public tipotrabajador(int idtipotrabajador, String nombretipotrabajador, boolean accederventas, boolean accederproductos, boolean accederclientes, boolean accederconsultas, boolean accederempleados, boolean accedertipousuario, boolean accedercambioclave, boolean accederanulaciones, boolean accedereliminarproducto, boolean accedereliminarcliente, boolean accedereliminarempleado, boolean accedereliminartipoempleado, boolean status) {
        this.idtipotrabajador = idtipotrabajador;
        this.nombretipotrabajador = nombretipotrabajador;
        this.accederventas = accederventas;
        this.accederproductos = accederproductos;
        this.accederclientes = accederclientes;
        this.accederconsultas = accederconsultas;
        this.accederempleados = accederempleados;
        this.accedertipousuario = accedertipousuario;
        this.accedercambioclave = accedercambioclave;
        this.accederanulaciones = accederanulaciones;
        this.accedereliminarproducto = accedereliminarproducto;
        this.accedereliminarcliente = accedereliminarcliente;
        this.accedereliminarempleado = accedereliminarempleado;
        this.accedereliminartipoempleado = accedereliminartipoempleado;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getIdtipotrabajador() {
        return idtipotrabajador;
    }

    public void setIdtipotrabajador(int idtipotrabajador) {
        this.idtipotrabajador = idtipotrabajador;
    }

    public String getNombretipotrabajador() {
        return nombretipotrabajador;
    }

    public void setNombretipotrabajador(String nombretipotrabajador) {
        this.nombretipotrabajador = nombretipotrabajador;
    }

    public boolean isAccederventas() {
        return accederventas;
    }

    public void setAccederventas(boolean accederventas) {
        this.accederventas = accederventas;
    }

    public boolean isAccederproductos() {
        return accederproductos;
    }

    public void setAccederproductos(boolean accederproductos) {
        this.accederproductos = accederproductos;
    }

    public boolean isAccederclientes() {
        return accederclientes;
    }

    public void setAccederclientes(boolean accederclientes) {
        this.accederclientes = accederclientes;
    }

    public boolean isAccederconsultas() {
        return accederconsultas;
    }

    public void setAccederconsultas(boolean accederconsultas) {
        this.accederconsultas = accederconsultas;
    }

    public boolean isAccederempleados() {
        return accederempleados;
    }

    public void setAccederempleados(boolean accederempleados) {
        this.accederempleados = accederempleados;
    }

    public boolean isAccedertipousuario() {
        return accedertipousuario;
    }

    public void setAccedertipousuario(boolean accedertipousuario) {
        this.accedertipousuario = accedertipousuario;
    }

    public boolean isAccedercambioclave() {
        return accedercambioclave;
    }

    public void setAccedercambioclave(boolean accedercambioclave) {
        this.accedercambioclave = accedercambioclave;
    }

    public boolean isAccederanulaciones() {
        return accederanulaciones;
    }

    public void setAccederanulaciones(boolean accederanulaciones) {
        this.accederanulaciones = accederanulaciones;
    }

    public boolean isAccedereliminarproducto() {
        return accedereliminarproducto;
    }

    public void setAccedereliminarproducto(boolean accedereliminarproducto) {
        this.accedereliminarproducto = accedereliminarproducto;
    }

    public boolean isAccedereliminarcliente() {
        return accedereliminarcliente;
    }

    public void setAccedereliminarcliente(boolean accedereliminarcliente) {
        this.accedereliminarcliente = accedereliminarcliente;
    }

    public boolean isAccedereliminarempleado() {
        return accedereliminarempleado;
    }

    public void setAccedereliminarempleado(boolean accedereliminarempleado) {
        this.accedereliminarempleado = accedereliminarempleado;
    }

    public boolean isAccedereliminartipoempleado() {
        return accedereliminartipoempleado;
    }

    public void setAccedereliminartipoempleado(boolean accedereliminartipoempleado) {
        this.accedereliminartipoempleado = accedereliminartipoempleado;
    }
}



