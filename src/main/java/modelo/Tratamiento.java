package modelo;

public class Tratamiento {
    private int idTratamiento;
    private String nombre;
    private int cantidadSesiones;
    private String tiempo;
    private String tiempoSuperpuesto;
    private int precio;

    public Tratamiento() {
    }

    public Tratamiento(int idTratamiento, String nombre, int cantidadSesiones, String tiempo, String tiempoSuperpuesto, int precio) {
        this.idTratamiento = idTratamiento;
        this.nombre = nombre;
        this.cantidadSesiones = cantidadSesiones;
        this.tiempo = tiempo;
        this.tiempoSuperpuesto = tiempoSuperpuesto;
        this.precio = precio;
    }

    public int getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(int idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadSesiones() {
        return cantidadSesiones;
    }

    public void setCantidadSesiones(int cantidadSesiones) {
        this.cantidadSesiones = cantidadSesiones;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getTiempoSuperpuesto() {
        return tiempoSuperpuesto;
    }

    public void setTiempoSuperpuesto(String tiempoSuperpuesto) {
        this.tiempoSuperpuesto = tiempoSuperpuesto;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    public String toString(){
        return "NOMBRE: " + this.nombre + ",PRECIO: " + this.precio;
    }
}
