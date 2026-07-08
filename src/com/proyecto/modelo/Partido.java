package com.proyecto.modelo;

import java.util.Date;

public class Partido {

    private String codigo;
    private String local;
    private String visitante;
    private Date fecha;
    private String estadio;
    private String ciudad;
    private int capacidad;
    private int stockGeneral;
    private int StockPreferencial;
    private int StockVIP;
    private String fase;
    private static final double precioBaseGeneral = 45.00;
    private static final double precioBasePreferencial = 85.00;
    private static final double precioBaseVIP = 150.00;

    // Constructor del método Partido
    public Partido(String codigo, String local, String visitante, Date fecha, String estadio, String ciudad,
            int capacidad, int stockGeneral, int StockPreferencial, int StockVIP, String fase) {
        this.codigo = codigo;
        this.local = local;
        this.visitante = visitante;
        this.fecha = fecha;
        this.estadio = estadio;
        this.ciudad = ciudad;
        this.capacidad = capacidad;
        this.stockGeneral = stockGeneral;
        this.StockPreferencial = StockPreferencial;
        this.StockVIP = StockVIP;
        this.fase = fase;
    }

    // Getters/Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getVisitante() {
        return visitante;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getStockGeneral() {
        return stockGeneral;
    }

    public void setStockGeneral(int stockGeneral) {
        this.stockGeneral = stockGeneral;
    }

    public int getStockPreferencial() {
        return StockPreferencial;
    }

    public void setStockPreferencial(int StockPreferencial) {
        this.StockPreferencial = StockPreferencial;
    }

    public int getStockVIP() {
        return StockVIP;
    }

    public void setStockVIP(int StockVIP) {
        this.StockVIP = StockVIP;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    // Método obtenerPrecio
    public double obtenerPrecio(double precioBase, String fase) {
        switch (fase) {
            case "Fase de grupos":
                return precioBase;

            case "Octavos de final":
                return precioBase + 15;

            case "Cuartos de final":
                return precioBase + 30;

            case "Semifinal":
                return precioBase + 60;

            case "Tercer lugar":
                return precioBase + 75;

            case "Final":
                return precioBase + 90;

            default:
                return precioBase;
        }
    }

    // Sobreescritura del método toString
    @Override
    public String toString() {
        String formato1 = "Código: " + codigo + "\n" + "Partido: " + local + " vs " + visitante + "\n";
        String formato2 = "Fecha: " + fecha + "\n" + "Estadio: " + estadio + "\n" + "Ciudad: " + ciudad + "\n"
                + "Fase: " + fase + "\n";
        String formato3 = "\n" + "Zonas disponibles: \n";
        String formato4 = String.format("- %-13s | Disponibles: %-6d | Precio: $%.2f\n",
                "GENERAL", stockGeneral, obtenerPrecio(precioBaseGeneral, fase));

        String formato5 = String.format("- %-13s | Disponibles: %-6d | Precio: $%.2f\n",
                "PREFERENCIAL", StockPreferencial, obtenerPrecio(precioBasePreferencial, fase));

        String formato6 = String.format("- %-13s | Disponibles: %-6d | Precio: $%.2f\n",
                "VIP", StockVIP, obtenerPrecio(precioBaseVIP, fase));

        return formato1 + formato2 + formato3 + formato4 + formato5 + formato6;
    }
}
