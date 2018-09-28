

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
public class tipotrabajador {
    private Long idtipotrabajador = null;
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

    public tipotrabajador(String nombretipotrabajador, boolean accederventas, boolean accederproductos, boolean accederclientes, boolean accederconsultas, boolean accederempleados, boolean accedertipousuario, boolean accedercambioclave, boolean accederanulaciones, boolean accedereliminarproducto, boolean accedereliminarcliente, boolean accedereliminarempleado, boolean accedereliminartipoempleado) {
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
        status = true;
    }

    public tipotrabajador() {
        
    }

    public Long getIdtipotrabajador() {
        return idtipotrabajador;
    }

    public void setIdtipotrabajador(Long idtipotrabajador) {
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "tipotrabajador{" + "idtipotrabajador=" + idtipotrabajador + ", nombretipotrabajador=" + nombretipotrabajador + ", accederventas=" + accederventas + ", accederproductos=" + accederproductos + ", accederclientes=" + accederclientes + ", accederconsultas=" + accederconsultas + ", accederempleados=" + accederempleados + ", accedertipousuario=" + accedertipousuario + ", accedercambioclave=" + accedercambioclave + ", accederanulaciones=" + accederanulaciones + ", accedereliminarproducto=" + accedereliminarproducto + ", accedereliminarcliente=" + accedereliminarcliente + ", accedereliminarempleado=" + accedereliminarempleado + ", accedereliminartipoempleado=" + accedereliminartipoempleado + ", status=" + status + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.idtipotrabajador);
        hash = 83 * hash + Objects.hashCode(this.nombretipotrabajador);
        hash = 83 * hash + (this.accederventas ? 1 : 0);
        hash = 83 * hash + (this.accederproductos ? 1 : 0);
        hash = 83 * hash + (this.accederclientes ? 1 : 0);
        hash = 83 * hash + (this.accederconsultas ? 1 : 0);
        hash = 83 * hash + (this.accederempleados ? 1 : 0);
        hash = 83 * hash + (this.accedertipousuario ? 1 : 0);
        hash = 83 * hash + (this.accedercambioclave ? 1 : 0);
        hash = 83 * hash + (this.accederanulaciones ? 1 : 0);
        hash = 83 * hash + (this.accedereliminarproducto ? 1 : 0);
        hash = 83 * hash + (this.accedereliminarcliente ? 1 : 0);
        hash = 83 * hash + (this.accedereliminarempleado ? 1 : 0);
        hash = 83 * hash + (this.accedereliminartipoempleado ? 1 : 0);
        hash = 83 * hash + (this.status ? 1 : 0);
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
        final tipotrabajador other = (tipotrabajador) obj;
        if (this.accederventas != other.accederventas) {
            return false;
        }
        if (this.accederproductos != other.accederproductos) {
            return false;
        }
        if (this.accederclientes != other.accederclientes) {
            return false;
        }
        if (this.accederconsultas != other.accederconsultas) {
            return false;
        }
        if (this.accederempleados != other.accederempleados) {
            return false;
        }
        if (this.accedertipousuario != other.accedertipousuario) {
            return false;
        }
        if (this.accedercambioclave != other.accedercambioclave) {
            return false;
        }
        if (this.accederanulaciones != other.accederanulaciones) {
            return false;
        }
        if (this.accedereliminarproducto != other.accedereliminarproducto) {
            return false;
        }
        if (this.accedereliminarcliente != other.accedereliminarcliente) {
            return false;
        }
        if (this.accedereliminarempleado != other.accedereliminarempleado) {
            return false;
        }
        if (this.accedereliminartipoempleado != other.accedereliminartipoempleado) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.nombretipotrabajador, other.nombretipotrabajador)) {
            return false;
        }
        if (!Objects.equals(this.idtipotrabajador, other.idtipotrabajador)) {
            return false;
        }
        return true;
    }
    
    
    

    
}



