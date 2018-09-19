
package farmacia.jdbc.modelado;

import java.util.Arrays;
import java.util.Objects;


public class persona {
    
    private Long idPersona = null;//PK
    private String nombre;//32
    private String appaterno;//32
    private String apmaterno;//32
    private char[] numerodni;//8
    private int personaedad;// NULO
    private String direccion;//32 NULO
    private String telefono;//16 NULO
    private boolean status;

    public persona(String nombre, String appaterno, String apmaterno, char[] numerodni, int personaedad, String direccion, String telefono) {
        this.nombre = nombre;
        this.appaterno = appaterno;
        this.apmaterno = apmaterno;
        this.numerodni = numerodni;
        this.personaedad = personaedad;
        this.direccion = direccion;
        this.telefono = telefono;
        status = true;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAppaterno() {
        return appaterno;
    }

    public void setAppaterno(String appaterno) {
        this.appaterno = appaterno;
    }

    public String getApmaterno() {
        return apmaterno;
    }

    public void setApmaterno(String apmaterno) {
        this.apmaterno = apmaterno;
    }

    public char[] getNumerodni() {
        return numerodni;
    }

    public void setNumerodni(char[] numerodni) {
        this.numerodni = numerodni;
    }

    public int getPersonaedad() {
        return personaedad;
    }

    public void setPersonaedad(int personaedad) {
        this.personaedad = personaedad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "persona{" + "idPersona=" + idPersona + ", nombre=" + nombre + ", appaterno=" + appaterno + ", apmaterno=" + apmaterno + ", numerodni=" + String.valueOf(numerodni) + ", personaedad=" + personaedad + ", direccion=" + direccion + ", telefono=" + telefono + ", status=" + status + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.idPersona);
        hash = 17 * hash + Objects.hashCode(this.nombre);
        hash = 17 * hash + Objects.hashCode(this.appaterno);
        hash = 17 * hash + Objects.hashCode(this.apmaterno);
        hash = 17 * hash + Arrays.hashCode(this.numerodni);
        hash = 17 * hash + this.personaedad;
        hash = 17 * hash + Objects.hashCode(this.direccion);
        hash = 17 * hash + Objects.hashCode(this.telefono);
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
        final persona other = (persona) obj;
        if (this.personaedad != other.personaedad) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.appaterno, other.appaterno)) {
            return false;
        }
        if (!Objects.equals(this.apmaterno, other.apmaterno)) {
            return false;
        }
        if (!Objects.equals(this.direccion, other.direccion)) {
            return false;
        }
        if (!Objects.equals(this.telefono, other.telefono)) {
            return false;
        }
        if (!Objects.equals(this.idPersona, other.idPersona)) {
            return false;
        }
        if (!Arrays.equals(this.numerodni, other.numerodni)) {
            return false;
        }
        return true;
    }
    
}
   