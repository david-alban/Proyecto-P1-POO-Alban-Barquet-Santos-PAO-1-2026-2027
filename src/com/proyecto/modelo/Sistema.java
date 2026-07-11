package com.proyecto.modelo;
import com.proyecto.util.ManejoArchivos;
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

    public int buscarIndiceRespectivo(ArrayList<String> lista, String codigo){
        for (int i = 0; i<lista.size();i++){
            String l = lista.get(i);
            if ((l != null)&&(!l.trim().isEmpty())&&(l.startsWith(codigo))){
                return i;
            }
        }
        return -1;
    }

    //terminado metodo complementario para cargar usuarios
    public void cargarUsuario(){
        ArrayList<String> lineas = com.proyecto.util.ManejoArchivos.LeeFichero("usuarios.txt");
        ArrayList<String> lineasA = com.proyecto.util.ManejoArchivos.LeeFichero("aficionados.txt");
        ArrayList<String> lineasO = com.proyecto.util.ManejoArchivos.LeeFichero("organizadores.txt");


        for (int i = 0; i<lineas.size();i++){
            String linea = lineas.get(i);
            if ((linea != null)&&(!linea.trim().isEmpty())){
                String[] datos =  linea.split("\\|");
                String code = datos[0];
              

                if (linea.endsWith("A") || linea.endsWith("a")){
                    // Es aficionado
                    int index = buscarIndiceRespectivo(lineasA, code);
                    if (index != -1){
                        this.usuarios.add(new Aficionado(linea,lineasA.get(index)));
                    } else {System.out.println("error aficionado");}

                } else if (linea.endsWith("O") || linea.endsWith("o")){
                    // Es organizador
                    int index = buscarIndiceRespectivo(lineasO, code);
                    if (index != -1){
                        this.usuarios.add(new Organizador(linea,lineasO.get(index)));
                    } else {System.out.println("error organizador");}

                } else  {System.out.print("wtf");}
        
                
        
            }
        }

    }
    
    //terminado cargarUsuarios

    // me estaba botando el error Unhandled exception type ParseException
    // https://www.w3schools.com/java/java_try_catch.asp
    // https://stackoverflow.com/questions/11665195/unhandled-exception-type-parseexception
    public void cargarPartidos(){
        ArrayList<String> lineas = com.proyecto.util.ManejoArchivos.LeeFichero("partidos.txt");
        for (String linea : lineas){
            if ((linea != null)&&(!linea.trim().isEmpty())){
                try {
                    this.partidos.add(new Partido(linea));
                } catch (Exception e){
                    System.out.println("error al procesar fecha de partido");
                }
            }
        }
    }

    //terminado cargarPartidos
    public void cargarKits(){
        ArrayList<String> lineas = com.proyecto.util.ManejoArchivos.LeeFichero("kits.txt");
        for (String linea : lineas){
            if ((linea != null)&&(!linea.trim().isEmpty())){
                
                this.kits.add(new Kit(linea));
            }
        }
    }

    // public void cargarCompras(){
    //       ArrayList<String> lineas = com.proyecto.util.ManejoArchivos.LeeFichero("compras.txt");
    //     for (String linea : lineas){
    //         if ((linea != null)&&(!linea.trim().isEmpty())){
                
    //             this.compras.add(new Compra(linea));
    //         }
    //     }
    // }

    public void pruebaCargarDatos(){
        this.cargarUsuario();
        this.cargarPartidos();
        this.cargarKits();

        System.out.println("\n--- Usuarios Cargados (" + this.usuarios.size() + ") ---");
    for (Object u : this.usuarios) {
        System.out.println(u); 
    }

    System.out.println("\n--- Partidos Cargados (" + this.partidos.size() + ") ---");
    for (Partido p : this.partidos) {
        System.out.println(p);
    }

    System.out.println("\n--- Kits Cargados (" + this.kits.size() + ") ---");
    for (Kit k : this.kits) {
        System.out.println(k);
    }
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

}
