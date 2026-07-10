package com.proyecto.modelo;

public abstract class Usuario {

    protected String codigoUnico;
    protected String cedula;
    protected String nombre;
    protected String apellido;
    protected String usuario;
    protected String contraseña;
    protected String correo;
    protected Rol rol;

    // Constructor del método Usuario 
    public Usuario(String codigoUnico, String cedula, String nombres, String apellidos, String usuario,
            String contraseña, String correo, Rol rol) {
        this.codigoUnico = codigoUnico;
        this.cedula = cedula;
        this.nombre = nombres;
        this.apellido = apellidos;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.correo = correo;
        this.rol = rol;
    }

    public Usuario(String lineaString) {
        String[] datos = lineaString.split(",");
        this.codigoUnico = datos[0];
        this.cedula = datos[1];
        this.nombre = datos[2];
        this.apellido = datos[3];
        this.usuario = datos[4];
        this.contraseña = datos[5];
        this.correo = datos[6];
        this.rol = Rol.valueOf(datos[7]);
    }

    // Método toString para mostrar la información del partido
    public String toString() {
        return "Usuario [codigoUnico=" + codigoUnico + 
        ", cedula=" + cedula + ", nombre=" + nombre + 
        ", apellido=" + apellido + ", usuario=" + usuario + 
        ", contraseña=" + contraseña + ", correo=" + correo + 
        ", rol=" + rol + "]";
    }

    // Getters/Setters
    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombre;
    }

    public void setNombres(String nombres) {
        this.nombre = nombres;
    }

    public String getApellidos() {
        return apellido;
    }

    public void setApellidos(String apellidos) {
        this.apellido = apellidos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    // Clase abstracta consultarEntradas
    protected abstract void consultarEntradas();
}
