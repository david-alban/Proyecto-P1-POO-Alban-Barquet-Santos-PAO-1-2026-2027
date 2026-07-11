package com.proyecto.modelo;

public class Main {
    public static void main(String[] args) {
        
        Sistema sistema = new Sistema();
        sistema.pruebaCargarDatos(); 
        sistema.cargarCompras();
        System.out.println("\n\n");
        sistema.consultarKits();
    }
}
