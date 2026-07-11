package com.proyecto.modelo;

import java.util.ArrayList;
import java.util.Locale;

public class Organizador extends Usuario {

    private String empresa;
    private String cargo;

    // Constructor del método Organizador
    public Organizador(String codigoUnico, String cedula, String nombres, String apellidos, String usuario,
            String contraseña, String correo, Rol rol, String empresa, String cargo) {
        super(codigoUnico, cedula, nombres, apellidos, usuario, contraseña, correo, rol);
        this.empresa = empresa;
        this.cargo = cargo;
    }

    // Sobreescritura del método consultarEntradas
    @Override
    public void consultarEntradas(Sistema sis) {
        ArrayList<Compra> compras = sis.getCompras();
        if (compras.size() == 0) {
            System.out.println("No hay compras registradas en el sistema.");
        } else {
            System.out.println("Compras registradas en el sistema: \n\n");
            for (Compra c : compras) {
                System.out.println(c);
                System.out.println();
            }
        }
    }

    // Constructor del método Organizador a partir de una línea de texto
    public Organizador(String lineaUsuario, String lineaOrganizador) {
        // Linea para extraer los datos de la clase padre
        super(lineaUsuario);

        // Extraemos solos los datos que se solicitan
        String[] partesOrg = lineaOrganizador.split("\\|");
        this.empresa = partesOrg[4];
        this.cargo = partesOrg[5];
    }

    // Método toString para mostrar la información del Organizador
    @Override
    public String toString() {
        return codigoUnico + "|" + cedula + "|" + nombre + "|" + apellido + "|" + empresa + "|" + cargo;
    }

    // Método generar reporte
    public void generarReporte(Sistema sis) {
        ArrayList<Compra> compras = sis.getCompras();
        int totalCompras = 0;
        int comprasKit = 0;
        int comprasEntrada = 0;
        double montoTotal = 0;
        for (Compra compra : compras) {
            totalCompras = totalCompras + compra.getCantidad();
            montoTotal = montoTotal + compra.getValorPagado();
            if (compra.getTipo().equals("Kit")) {
                comprasKit = comprasKit + compra.getCantidad();
            } else {
                comprasEntrada = comprasEntrada + compra.getCantidad();
            }
        }
        System.out.println("======== GENERAR REPORTE DE VENTAS ========\n");
        System.out.println("Resumen de ventas registradas: " + totalCompras + "\n");
        System.out.println("Compras por tipo:\n");
        System.out.println("Entradas: " + comprasEntrada);
        System.out.println("Kits: " + comprasKit + "\n");
        // Formateando montoTotal con separador decimal y de millares
        String montoFormateado = String.format(Locale.US, "%,.2f", montoTotal);
        System.out.println("Monto total recaudado: \n" + "$" + montoFormateado);
    }

    // Getters/Setters
    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}