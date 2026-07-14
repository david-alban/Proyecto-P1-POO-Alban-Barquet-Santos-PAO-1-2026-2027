package com.proyecto.modelo;
import java.util.ArrayList;

public class Aficionado extends Usuario {

    /**
     * Numero de celular del aficionado.
     */
    private String celular;

    /**
     * País favorito del aficionado.
     */
    private String paisFavorito;

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
     * @param celular Numero de celular del aficionado.
     * @param paisFavorito País favorito del aficionado.
     */
    public Aficionado(String codigoUnico, String cedula, String nombres, String apellidos, String usuario,
            String contraseña, String correo, Rol rol, String celular, String paisFavorito) {
        super(codigoUnico, cedula, nombres, apellidos, usuario, contraseña, correo, rol);
        this.celular = celular;
        this.paisFavorito = paisFavorito;
    }

    /**
     * Constructor  ue recibe dos Strings extraídos de los archivos usuarios.txt y organizadores.txt
     * @param lineaUsuario Linea extraída con datos separados por '|' con información de atributos de usuario.
     * @param lineaAficionado Linea extraída con datos separados por '|' con información de atributos de aficionado.
     */
    public Aficionado(String lineaUsuario, String lineaAficionado) {
        //Linea para extraer los datos de la clase padre
        super(lineaUsuario);

        //Extraemos solos los datos que se solicitan
        String[] partesAfi = lineaAficionado.split("\\|");
        this.celular = partesAfi[4];
        this.paisFavorito = partesAfi[5];
    }
    
    /**
     * Sobrescritura del método toString.
     */
    @Override
    public String toString() {
        return codigoUnico + "|" + cedula + "|" + nombre + 
        "|" + apellido + "|" + celular + "|" + paisFavorito;
    }

    /**
     * Sobrescritura del método abstracto consultar entradas. Este método muestra todos las compras hechas por el usuario.
     */
    @Override
    public void consultarEntradas(ArrayList<Compra> compras) {
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

    /**
     * Método que devuelve un objeto {@link Compra} y calcula el valor total a pagar por entradas para un partido.
     * @param p Partido elegido por el usuario.
     * @param zona Zona elegida por el usuario.
     * @param cantidad Cantidad de entradas elegidas por el usuario.
     * @return Objeto {@link Compra}
     */
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

    /**
     * Sobrecarga del método comprar. Retorna un objeto {@link Compra} y calcula el valor total a pagar por un numero de kits determinados por el usuario.
     * @param k Kit elegido por el usuario;
     * @param cantidad Cantidad de kits elegidos por el usuario.
     * @return Objeto {@link Compra}
     */
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