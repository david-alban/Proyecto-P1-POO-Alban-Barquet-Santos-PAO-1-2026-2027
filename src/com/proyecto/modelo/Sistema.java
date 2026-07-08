package com.proyecto.modelo;

import java.util.ArrayList;

public class Sistema {

    private ArrayList<Usuario> usuarios;
    private ArrayList<Partido> partidos;
    private ArrayList<Kit> kits;
    private ArrayList<Compra> compras;

    // Constructor del método Sistema

    public Sistema(){
        this.usuarios = new ArrayList<>();
        this.compras = new ArrayList<>();
        this.kits = new ArrayList<>();
        this.partidos = new ArrayList<>();
    }

    public void cargarUsuario(){

    }
    
    public void cargarPartidos(){
        
    }

    public void cargarKits(){
        
    }

    public void cargarCompras(){
        
    }

    public void mostrarMenu(){

    }

    public void iniciarSesion(){

    }


    public void notificar(Aficionado aficionado, Compra compraRealizada){

    }

    public void notificar(Aficionado aficionado, Compra compraRealizada, Kit kitAdquirido){

    }

    public void notificar(Organizador organizador, String datosReporte){

    }



    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(ArrayList<Partido> partidos) {
        this.partidos = partidos;
    }

    public ArrayList<Kit> getKits() {
        return kits;
    }

    public void setKits(ArrayList<Kit> kits) {
        this.kits = kits;
    }

    public ArrayList<Compra> getCompras() {
        return compras;
    }

    public void setCompras(ArrayList<Compra> compras) {
        this.compras = compras;
    }

    // Método consultar partidos
    public void consultarPartidos() {
        if (partidos.isEmpty()) {
            System.out.println("No hay partidos registrados en el sistema.");
            return;
        }

        System.out.println("=========================================");
        System.out.println("          PARTIDOS ENCONTRADOS           ");
        System.out.println("=========================================\n");

        int i = 1;
        for (Partido partido : partidos) {
            if (partido != null) {
                System.out.print(i + ". ");
                System.out.println(partido);
                System.out.println("\n-----------------------------------------\n");
                i++;
            }
        }
    }

}
