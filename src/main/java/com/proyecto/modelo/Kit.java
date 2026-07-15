package com.proyecto.modelo;

import java.util.ArrayList;

/**
 * Clase que representa un Kit de partidos deportivos.
 */

public class Kit {
    /**
     * Variables del kit generado.
     */
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private int disponibles;
    private ArrayList<Partido> partidos;

    /**
     * Constructor de la clase Kit (Crea un nuevo kit con todos sus atributos).
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param precio
     * @param disponibles
     * @param partidos
     */

    public Kit(String codigo, String nombre, String descripcion, double precio, int disponibles,
            ArrayList<Partido> partidos) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.disponibles = disponibles;
        this.partidos = partidos;
    }

    /**
     * Método para agregar un partido al kit.
     * @param p Partido a agregar.
     */

    public void agregarPartido(Partido p) {
        this.partidos.add(p);
    }

    /**
     * Constructor de la clase Kit (Crea un nuevo kit a partir de una línea de texto).
     * @param linea Linea de texto con los datos del kit.
     */    

    public Kit(String linea) {
        String[] datos =  linea.split("\\|");
        this.codigo = datos[0];
        this.nombre = datos[1];
        this.descripcion = datos[2];
        this.partidos = new ArrayList<>();
        this.precio = Double.valueOf(datos[4]);
        this.disponibles = Integer.valueOf(datos[5]);
    }
    /**
     * Constructor de la clase Kit (Asocia los objetos Partido correspondiente).
     * @param linea Linea de texto con los datos del kit.
     * @param partidos Lista general de partidos disponibles.
     */

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

    /**
     * Devuelve una representación en texto con la información del kit.
     * @return Cadena de texto con codigo.
     */

    @Override   
    public String toString() {
        String p = "";
        for(Partido partido:partidos){
            p = p + "\n- " + partido.getLocal() + " vs " + partido.getVisitante();  

        }
        return "Código: " + codigo + '\n' + nombre + '\n' + "Incluye:\n" + p + "\n\nPrecio: " + precio + "\nDisponibles: " + disponibles ;

    }
    
    /**
     * Setters y Getters
     * @return
     */
    
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
