package com.proyecto.modelo;

public class Aficionado extends Usuario {

    private String celular;
    private String paisFavorito;

    // Constructor del método Aficionado 
    public Aficionado(String codigoUnico, String cedula, String nombres, String apellidos, String usuario,
            String contraseña, String correo, Rol rol, String celular, String paisFavorito) {
        super(codigoUnico, cedula, nombres, apellidos, usuario, contraseña, correo, rol);
        this.celular = celular;
        this.paisFavorito = paisFavorito;
    }

    // Sobreescritura del método consultarEntradas
    @Override
    public void consultarEntradas() {

    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getPaisFavorito() {
        return paisFavorito;
    }

    public void setPaisFavorito(String paisFavorito) {
        this.paisFavorito = paisFavorito;
    }
}