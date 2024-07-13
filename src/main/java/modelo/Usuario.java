package modelo;

import java.util.Date;

public class Usuario {
    private int idUsuario;
    private String user;
    private String password;
    private String email;
    private Date ultimoAcceso;
    private boolean activo;

    public Usuario(int idUsuario, String user, String password, String email, Date ultimoAcceso) {
        this.idUsuario = idUsuario;
        this.user = user;
        this.password = password;
        this.email = email;
        this.ultimoAcceso = ultimoAcceso;
    }
    
    public Usuario(int idUsuario, String user, String password, String email, Date ultimoAcceso, boolean activo) {
        this.idUsuario = idUsuario;
        this.user = user;
        this.password = password;
        this.email = email;
        this.ultimoAcceso = ultimoAcceso;
        this.activo = activo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(Date ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public String toString(){
        return "USER: " + this.user + "\n" + "PASSWORD: " + this.password + "\n" + "EMAIL: " + this.email;
    }
    
}
