package pruebas;


public class Paciente {
    private String nombre;
    private String dni;
    private String telefono;
    private char sexo;

    public Paciente() {
    }

    public Paciente(String nombre, String dni, String telefono, char sexo) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.sexo = sexo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
}
