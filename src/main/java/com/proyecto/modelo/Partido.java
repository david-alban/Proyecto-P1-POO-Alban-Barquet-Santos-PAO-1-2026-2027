package com.proyecto.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

/**
 * Representa un partido disponible en el sistema. 
 */
public class Partido {

    /**
     * Variables de la clase partido.
     */
    private String codigo;
    private String local;
    private String visitante;
    private Date fecha;
    private String estadio;
    private String ciudad;
    private int capacidad;
    private int stockGeneral;
    private int stockPreferencial;
    private int stockVIP;
    private String fase;

    /**
     * Crea un nuevo  partido con todos los atributos detallados.
     * @param codigo
     * @param local
     * @param visitante
     * @param fecha
     * @param estadio
     * @param ciudad
     * @param capacidad
     * @param stockGeneral
     * @param StockPreferencial
     * @param StockVIP
     * @param fase
     */
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
        this.stockPreferencial = StockPreferencial;
        this.stockVIP = StockVIP;
        this.fase = fase;
    }

    /**
     * Calcula el precio de la entrada en la zona general dependiendo a la fase.
     * @return Precio de la entrada general
     */
    public double getPrecioGeneral() {
        if (fase.equalsIgnoreCase("Octavos de final"))
            return 60.0;
        if (fase.equalsIgnoreCase("Cuartos de final"))
            return 75.0;
        if (fase.equalsIgnoreCase("Semifinal"))
            return 90.0;
        if (fase.equalsIgnoreCase("Final"))
            return 120.0;
        return 45.0; // Precio base para otras fases
    }

    /**
     * Calcula el precio de la entrada en la zona preferencial dependiendo a la fase.
     * @return Precio de la entrada preferencial.
     */
    public double getPrecioPreferencial() {
        if (fase.equalsIgnoreCase("Octavos de final"))
            return 100.0;
        if (fase.equalsIgnoreCase("Cuartos de final"))
            return 120.0;
        if (fase.equalsIgnoreCase("Semifinal"))
            return 150.0;
        if (fase.equalsIgnoreCase("Final"))
            return 200.0;
        return 85.0; // Precio base para otras fases
    }

    /**
     * Calcula el precio de la entrada en la zona VIP dependiendo a la fase.
     * @return Precio de la entrada VIP.
     */
    public double getPrecioVIP() {
        if (fase.equalsIgnoreCase("Octavos de final"))
            return 180.0;
        if (fase.equalsIgnoreCase("Cuartos de final"))
            return 220.0;
        if (fase.equalsIgnoreCase("Semifinal"))
            return 280.0;
        if (fase.equalsIgnoreCase("Final"))
            return 350.0;
        return 45.0; // Precio base para otras fases
    }

    /**
     * Crea un nuevo partido a partir de la linea de texto
     * Carga información contenida en los archivos .txt.
     * @param linea Linea de Cadena de texto.
     * @throws ParseException Si ocurre un error convertir el formato de la fecha.
     */
    public Partido(String linea) throws ParseException {
        String[] datos = linea.split("\\|");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        this.codigo = datos[0];
        this.local = datos[1];
        this.visitante = datos[2];
        this.fecha = sdf.parse(datos[3]);
        this.estadio = datos[4];
        this.ciudad = datos[5];
        this.capacidad = Integer.valueOf(datos[6]);
        this.stockGeneral = Integer.valueOf(datos[7]);
        this.stockPreferencial = Integer.valueOf(datos[8]);
        this.stockVIP = Integer.valueOf(datos[9]);

        this.fase = datos[10];
    }

    /** 
     * Devuelve una cadena de texto con la información detallada.
     * @return Texto con las variables
    */

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return "Código: " + this.codigo + "\n" +
                "Partido: " + this.local + " vs " + this.visitante + "\n" +
                "Fecha: " + sdf.format(this.fecha) + "\n" +
                "Estadio: " + this.estadio + "\n" +
                "Ciudad: " + this.ciudad + "\n" +
                "Fase: " + this.fase + "\n" +
                "Stock:  General: " + this.stockGeneral + " | Preferencial: " + this.stockPreferencial + " | VIP: " + this.stockVIP + "\n" +
                "Precios:  General: " + getPrecioGeneral() + " | Preferencial: " + getPrecioPreferencial() + " | VIP: " + getPrecioVIP();
    }

    /**
     * Setters y Getters
     * @return
     */
    
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
        return stockPreferencial;
    }

    public void setStockPreferencial(int StockPreferencial) {
        this.stockPreferencial = StockPreferencial;
    }

    public int getStockVIP() {
        return stockVIP;
    }

    public void setStockVIP(int StockVIP) {
        this.stockVIP = StockVIP;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

}
