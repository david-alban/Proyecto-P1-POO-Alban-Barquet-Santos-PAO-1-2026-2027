package com.proyecto.modelo;

public class Organizador extends Usuario {

    private String empresa;
    private String cargo;

    //Constructor del método Organizador 
    public Organizador(String codigoUnico, String cedula, String nombres, String apellidos, String usuario,
            String contraseña, String correo, Rol rol, String empresa, String cargo) {
        super(codigoUnico, cedula, nombres, apellidos, usuario, contraseña, correo, rol);
        this.empresa = empresa;
        this.cargo = cargo;
    }

    //Sobreescritura del método consultarEntradas
    @Override
    public void consultarEntradas() {

    }

    // Constructor del método Organizador a partir de una línea de texto
    public Organizador(String lineaUsuario, String lineaOrganizador) {
        //Linea para extraer los datos de la clase padre
        super(lineaUsuario);

        //Extrameos solos los datos que se solicitan
        String[] partesOrg = lineaOrganizador.split("\\|");
        this.empresa = partesOrg[4];
        this.cargo = partesOrg[5];
    }

    // Método toString para mostrar la información del Organizador
    @Override
    public String toString() {
        return codigoUnico + "|" + cedula + "|" + nombre + "|" + apellido + "|" + empresa + "|" + cargo;
    }


    //Getters/Setters
    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}