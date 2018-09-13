
package farmacia.modelado;


public class persona {
    private int idPersona;
    private String nombre;//32
    private String appaterno;//32
      
    private String apmaterno;//32
    private char[] numerodni;//8
    private int personaedad;// NULO
    private String direccion;//32 NULO
    private String telefono;//16 NULO
    private boolean status;

    public persona(int idPersona, String nombre, String appaterno, String apmaterno, char[] numerodni, int personaedad, String direccion, String telefono, boolean status) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.appaterno = appaterno;
        this.apmaterno = apmaterno;
        this.numerodni = numerodni;
        this.personaedad = personaedad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.status = status;
    }

    public persona() {
    }

    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", appaterno=" + appaterno + ", apmaterno=" + apmaterno + ", numerodni=" + numerodni + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.idPersona;
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
        if (this.idPersona != other.idPersona) {
            return false;
        }
        return true;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
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
    
}
   