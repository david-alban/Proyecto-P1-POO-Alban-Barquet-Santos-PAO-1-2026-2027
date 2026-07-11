package com.proyecto.modelo;
import java.util.ArrayList;

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

    // Constructor del método Aficionado a partir de una línea de texto
    public Aficionado(String lineaUsuario, String lineaAficionado) {
        //Linea para extraer los datos de la clase padre
        super(lineaUsuario);

        //Extraemos solos los datos que se solicitan
        String[] partesAfi = lineaAficionado.split("\\|");
        this.celular = partesAfi[4];
        this.paisFavorito = partesAfi[5];
    }
    
    // Método toString para mostrar la información del Aficionado
    @Override
    public String toString() {
        return codigoUnico + "|" + cedula + "|" + nombre + 
        "|" + apellido + "|" + celular + "|" + paisFavorito;
    }

    // Sobreescritura del método consultarEntradas
    @Override
    public void consultarEntradas(Sistema sis) {
        ArrayList<Compra> compras = sis.getCompras();
        ArrayList<Compra> comprasEncontradas = new ArrayList<>();
        for(Compra compra:compras){
            if(codigoUnico.equals(compra.getCodigoAficionado())){
                comprasEncontradas.add(compra);
            }
        }
        if(comprasEncontradas.size() == 0){
            System.out.println("No tienes compras registradas.");
        }else{
            System.out.println("Compras registradas: \n\n");
            for(Compra c:comprasEncontradas){
                System.out.println(c);
                System.out.println();
            }
        }
    }

    // ====== Métodos comprar =======

    // Comprar entrada 
    public Compra comprar(Partido p, Zona zona, int cantidad){
        Double precio = 0.0;
        switch (zona) {
            case Zona.VIP:
                precio = p.getPrecioVIP() * cantidad;
                break;
            case Zona.Preferencial:
                precio = p.getPrecioPreferencial() * cantidad;
                break;
            default:
                precio = p.getPrecioGeneral() * cantidad;
                break;
        }
        return new Compra("Entrada", p.getCodigo(), cantidad, precio, this.codigoUnico);
    }

    // Comprar kit

    public Compra comprar(Kit k, int cantidad){
        Double precio = k.getPrecio() * cantidad;
        return new Compra("Kit", k.getCodigo(), cantidad, precio, this.codigoUnico);
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