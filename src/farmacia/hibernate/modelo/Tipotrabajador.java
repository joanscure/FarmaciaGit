package farmacia.hibernate.modelo;
// Generated Oct 25, 2018 3:35:34 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

/**
 * Tipotrabajador generated by hbm2java
 */
public class Tipotrabajador implements java.io.Serializable {

    private Integer idtipotrabajador;
    private String nombretipotrabajador;
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
    private Set empleados = new HashSet(0);

    public Tipotrabajador() {
    }

    public Tipotrabajador(String nombretipotrabajador, boolean accederventas, boolean accederproductos, boolean accederclientes, boolean accederconsultas, boolean accederempleados, boolean accedertipousuario, boolean accedercambioclave, boolean accederanulaciones, boolean accedereliminarproducto, boolean accedereliminarcliente, boolean accedereliminarempleado, boolean accedereliminartipoempleado, boolean status) {
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

    public Tipotrabajador(boolean accederventas, boolean accederproductos, boolean accederclientes, boolean accederconsultas, boolean accederempleados, boolean accedertipousuario, boolean accedercambioclave, boolean accederanulaciones, boolean accedereliminarproducto, boolean accedereliminarcliente, boolean accedereliminarempleado, boolean accedereliminartipoempleado, boolean status) {
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

    public Tipotrabajador(String nombretipotrabajador, boolean accederventas, boolean accederproductos, boolean accederclientes, boolean accederconsultas, boolean accederempleados, boolean accedertipousuario, boolean accedercambioclave, boolean accederanulaciones, boolean accedereliminarproducto, boolean accedereliminarcliente, boolean accedereliminarempleado, boolean accedereliminartipoempleado, boolean status, Set empleados) {
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
        this.empleados = empleados;
    }

    public Integer getIdtipotrabajador() {
        return this.idtipotrabajador;
    }

    public void setIdtipotrabajador(Integer idtipotrabajador) {
        this.idtipotrabajador = idtipotrabajador;
    }

    public String getNombretipotrabajador() {
        return this.nombretipotrabajador;
    }

    public void setNombretipotrabajador(String nombretipotrabajador) {
        this.nombretipotrabajador = nombretipotrabajador;
    }

    public boolean isAccederventas() {
        return this.accederventas;
    }

    public void setAccederventas(boolean accederventas) {
        this.accederventas = accederventas;
    }

    public boolean isAccederproductos() {
        return this.accederproductos;
    }

    public void setAccederproductos(boolean accederproductos) {
        this.accederproductos = accederproductos;
    }

    public boolean isAccederclientes() {
        return this.accederclientes;
    }

    public void setAccederclientes(boolean accederclientes) {
        this.accederclientes = accederclientes;
    }

    public boolean isAccederconsultas() {
        return this.accederconsultas;
    }

    public void setAccederconsultas(boolean accederconsultas) {
        this.accederconsultas = accederconsultas;
    }

    public boolean isAccederempleados() {
        return this.accederempleados;
    }

    public void setAccederempleados(boolean accederempleados) {
        this.accederempleados = accederempleados;
    }

    public boolean isAccedertipousuario() {
        return this.accedertipousuario;
    }

    public void setAccedertipousuario(boolean accedertipousuario) {
        this.accedertipousuario = accedertipousuario;
    }

    public boolean isAccedercambioclave() {
        return this.accedercambioclave;
    }

    public void setAccedercambioclave(boolean accedercambioclave) {
        this.accedercambioclave = accedercambioclave;
    }

    public boolean isAccederanulaciones() {
        return this.accederanulaciones;
    }

    public void setAccederanulaciones(boolean accederanulaciones) {
        this.accederanulaciones = accederanulaciones;
    }

    public boolean isAccedereliminarproducto() {
        return this.accedereliminarproducto;
    }

    public void setAccedereliminarproducto(boolean accedereliminarproducto) {
        this.accedereliminarproducto = accedereliminarproducto;
    }

    public boolean isAccedereliminarcliente() {
        return this.accedereliminarcliente;
    }

    public void setAccedereliminarcliente(boolean accedereliminarcliente) {
        this.accedereliminarcliente = accedereliminarcliente;
    }

    public boolean isAccedereliminarempleado() {
        return this.accedereliminarempleado;
    }

    public void setAccedereliminarempleado(boolean accedereliminarempleado) {
        this.accedereliminarempleado = accedereliminarempleado;
    }

    public boolean isAccedereliminartipoempleado() {
        return this.accedereliminartipoempleado;
    }

    public void setAccedereliminartipoempleado(boolean accedereliminartipoempleado) {
        this.accedereliminartipoempleado = accedereliminartipoempleado;
    }

    public boolean isStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set getEmpleados() {
        return this.empleados;
    }

    public void setEmpleados(Set empleados) {
        this.empleados = empleados;
    }

}