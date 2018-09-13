
package farmacia.modelado;


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
    
    

    
}
   