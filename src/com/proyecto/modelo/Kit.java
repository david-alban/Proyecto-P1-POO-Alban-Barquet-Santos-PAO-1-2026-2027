package com.proyecto.modelo;

import java.util.ArrayList;

public class Kit {

    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private int disponibles;
    private ArrayList<Partido> partidos;

    //Constructor del método Kit
    public Kit(String codigo, String nombre, String descripcion, double precio, int disponibles,
            ArrayList<Partido> partidos) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.disponibles = disponibles;
        this.partidos = partidos;
    }

    // Método para agregar un partido al kit
    public void agregarPartido(Partido p) {
        this.partidos.add(p);
    }
    
    // Constructor del método Kit a partir de una línea de texto
    public Kit(String linea) {
        String[] datos =  linea.split("\\|");
        this.codigo = datos[0];
        this.nombre = datos[1];
        this.descripcion = datos[2];
        this.partidos = new ArrayList<>();
        this.precio = Double.valueOf(datos[4]);
        this.disponibles = Integer.valueOf(datos[5]);
    }

    public Kit(String linea, ArrayList<Partido> partidos){
        this(linea);
        String[] datos =  linea.split("\\|");
        String[] codigos = datos[3].split(",");
        for(String codigo:codigos){
            for(Partido partido:partidos){
                if(codigo.equals(partido.getCodigo())){
                    this.partidos.add(partido);
                }     
            }
        }

    }

    // Método toString para mostrar la información del Kit
    @Override   
    public String toString() {
        String p = "";
        for(Partido partido:partidos){
            p = p + "\n- " + partido.getLocal() + " vs " + partido.getVisitante();  

        }
        return nombre + '\n' + "Incluye:\n" + p + "\n\nPrecio: " + precio + "\nDisponibles: " + disponibles ;

    }

    public String getCodigo() {return codigo;}
    public void setCodigo(String codigo) {this.codigo = codigo;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public double getPrecio() {return precio;}
    public void setPrecio(double precio) {this.precio = precio;}

    public int getDisponibles() {return disponibles;}
    public void setDisponibles(int disponibles) {this.disponibles = disponibles;}

    public ArrayList<Partido> getPartidos() {return partidos;}
    public void setPartidos(ArrayList<Partido> partidos) {this.partidos = partidos;}

}
