package com.proyecto.modelo;

import java.util.ArrayList;

public abstract class Usuario {

    /**
     * Código único del usuario.
     */
    protected String codigoUnico;

    /**
     * Numero de cédula del usuario.
     */
    protected String cedula;

    /**
     * Nombre del usuario.
     */
    protected String nombre;

    /**
     * Apellido del usuario.
     */
    protected String apellido;

    /**
     * Nombre de usuario.
     */
    protected String usuario;

    /**
     * Contraseña de la cuenta del usuario.
     */
    protected String contraseña;

    /**
     * Correo vinculado a la cuenta del usuario.
     */
    protected String correo;

    /**
     * Identificador del Rol de usuario.
     */
    protected Rol rol;

    /**
     * Constructor de la clase abstracta usuario que recibe parametros.
     * @param codigoUnico Código identificador del usuario.
     * @param cedula Numero de cédula del usuario 
     * @param nombres Nombre del usuario.
     * @param apellidos Apellido del usuario.
     * @param usuario Nombre de usuario de la cuenta.
     * @param contraseña Contraseña de la cuenta.
     * @param correo Correo vinculada a la cuenta.
     * @param rol Rol asignado (usando el enum {@link Rol})
     */
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

    /**
     * Constructor que recibe un string con el formato de una linea extraída de usuarios.txt.
     * @param lineaString Linea extraída con datos separados por '|'.
     */
    public Usuario(String lineaString) {
        String[] datos = lineaString.split("\\|");
        this.codigoUnico = datos[0];
        this.cedula = datos[1];
        this.nombre = datos[2];
        this.apellido = datos[3];
        this.usuario = datos[4];
        this.contraseña = datos[5];
        this.correo = datos[6];
        this.rol = Rol.valueOf(datos[7]);
    }

    /**
     * Sobrescritura del método toString.
     */
    @Override
    public String toString() {
        return "Nombre: " + nombre + " " + apellido + " | Cédula: " + cedula +
                " | Correo: " + correo + " | Rol: " + rol;
    }

    /**
     * Método que recibe una lista de partidos para mostrárselos al usuario.
     * @param partidos Lista de objetos {@link Partido} cargados en el sistema.
     */
    public void consultarPartidos(ArrayList<Partido> partidos) {
        if (partidos.isEmpty()) {
            System.out.println("No hay partidos registrados en el sistema.");
            return;
        }

        System.out.println("Partidos encontrados: \n");

        int i = 1;
        for (Partido partido : partidos) {
            if (partido != null) {
                System.out.print(i + ". ");
                System.out.println(partido);
                System.out.println("\n-----------------------------------------\n\n");
                i++;
            }
        }
    }

    /**
     * Método que recibe una lista de kits para mostrárselos al usuario.
     * @param kits Lista de objetos {@link Kit} cargados en el sistema.
     */
    public void consultarKits(ArrayList<Kit> kits) {
        if (kits.isEmpty()) {
            System.out.println("No hay kits cargados en el sistema.");
            return;
        }

        System.out.println("Kits encontrados: \n");

        int i = 1;
        for (Kit kit : kits) {
            if (kit != null) {
                System.out.print(i + ". ");
                System.out.println(kit);
                System.out.println("\n-----------------------------------------\n\n");
                i++;
            }
        }
    }

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

    /**
     * Método abstracto que muestra al usuario las entradas que ha adquirido.
     * @param compras Lista de objetos {@link Compra} cargados en el sistema.
     */
    protected abstract void consultarEntradas(ArrayList<Compra> compras);
}
