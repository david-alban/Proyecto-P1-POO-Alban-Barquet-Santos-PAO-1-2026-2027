package com.proyecto.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Compra {
    private int codigoCompra;
    private String tipo;
    private String codigoReferencial;
    private Date fechaCompra;
    private int cantidad;
    private double valorPagado;
    private String codigoAficionado;

    public static int nextCodigo = 1;

    // Constructor Compra que recibe un string
    public Compra(String linea) throws ParseException {
        String[] datos = linea.split("\\|");
        this.codigoCompra = Integer.parseInt(datos[0]);
        this.tipo = datos[1];
        this.codigoReferencial = datos[2];
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        this.fechaCompra = sdf.parse(datos[3]);
        this.cantidad = Integer.valueOf(datos[4]);
        this.valorPagado = Double.valueOf(datos[5]);
        this.codigoAficionado = datos[6];
    }

    // Constructor del método Compra
    public Compra(String tipo, String codigoReferencial, int cantidad, double valorPagado, String codigoAficionado) {
        this.tipo = tipo;
        this.codigoReferencial = codigoReferencial;
        this.cantidad = cantidad;
        this.valorPagado = valorPagado;
        this.codigoAficionado = codigoAficionado;
        this.codigoCompra = nextCodigo;
        this.fechaCompra = new Date();
        nextCodigo++;
    }

    // Getters/Setters
    public int getCodigoCompra() {
        return codigoCompra;
    }

    public void setCodigoCompra(int codigoCompra) {
        this.codigoCompra = codigoCompra;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCodigoReferencial() {
        return codigoReferencial;
    }

    public void setCodigoReferencial(String codigoReferencial) {
        this.codigoReferencial = codigoReferencial;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(double valorPagado) {
        this.valorPagado = valorPagado;
    }

    public String getCodigoAficionado() {
        return codigoAficionado;
    }

    public void setCodigoAficionado(String codigoAficionado) {
        this.codigoAficionado = codigoAficionado;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return "Código de Compra: " + this.codigoCompra + "\n" +
                "Tipo:             " + this.tipo + "\n" +
                "Ref. Producto:    " + this.codigoReferencial + "\n" +
                "Fecha de Compra:  " + sdf.format(this.fechaCompra) + "\n" +
                "Cantidad:         " + this.cantidad + "\n" +
                "Total Pagado:     $" + String.format("%.2f", this.valorPagado) + "\n" +
                "Aficionado:       " + this.codigoAficionado + "\n" +
                "-----------------------------------------";
    }
}
