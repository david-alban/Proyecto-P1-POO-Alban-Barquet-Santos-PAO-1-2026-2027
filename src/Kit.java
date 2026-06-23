import java.util.ArrayList;

public class Kit {

    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private int disponibles;
    private ArrayList<Partido> partidos;

    public Kit(String codigo, String nombre, String descripcion, double precio, int disponibles,
            ArrayList<Partido> partidos) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.disponibles = disponibles;
        this.nombre = nombre;
        this.partidos = partidos;
        this.precio = precio;
    }

    public void agregarPartido(Partido p) {
        this.partidos.add(p);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public int getDisponibles() {
        return disponibles;
    }

    public ArrayList<Partido> getPartidos() {
        return partidos;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }

    public void setPartidos(ArrayList<Partido> partidos) {
        this.partidos = partidos;
    }

}
