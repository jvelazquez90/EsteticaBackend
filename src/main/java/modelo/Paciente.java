package modelo;

import java.util.Date;

public class Paciente extends Persona{
    private int idPaciente;
    private String telefono;
    private Date fechaDeAlta;
    private Date fechaReal;
    private double totalGastos;
    private double saldo;
    private boolean activo;

    public Paciente(int idPersona, String nombre, String apellido, String dni, int sexo, 
            int idPaciente, String telefono, Date fechaDeAlta, Date fechaReal, 
            double totalGastos, double saldo, boolean activo) {
        super(idPersona,nombre,apellido,dni,sexo);
        
        this.idPaciente = idPaciente;
        this.telefono = telefono;
        this.fechaDeAlta = fechaDeAlta;
        this.fechaReal = fechaReal;
        this.totalGastos = totalGastos;
        this.saldo = saldo;
        this.activo = activo;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaDeAlta() {
        return fechaDeAlta;
    }

    public void setFechaDeAlta(Date fechaDeAlta) {
        this.fechaDeAlta = fechaDeAlta;
    }

    public Date getFechaReal() {
        return fechaReal;
    }

    public void setFechaReal(Date fechaReal) {
        this.fechaReal = fechaReal;
    }

    public double getTotalGastos() {
        return totalGastos;
    }

    public void setTotalGastos(double totalGastos) {
        this.totalGastos = totalGastos;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    
}
