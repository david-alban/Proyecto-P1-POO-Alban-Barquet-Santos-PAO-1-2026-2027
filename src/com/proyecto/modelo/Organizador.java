package com.proyecto.modelo;

import java.util.ArrayList;
import java.util.Locale;

public class Organizador extends Usuario {

    /**
     * Nombre de la empresa a la que pertenece el usuario tipo Organizador.
     */
    private String empresa;

    /**
     * Cargo del organizador.
     */
    private String cargo;

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
     * @param empresa Empresa a la que pertenece el organizador.
     * @param cargo Cargo del organizador.
     */
    public Organizador(String codigoUnico, String cedula, String nombres, String apellidos, String usuario,
            String contraseña, String correo, Rol rol, String empresa, String cargo) {
        super(codigoUnico, cedula, nombres, apellidos, usuario, contraseña, correo, rol);
        this.empresa = empresa;
        this.cargo = cargo;
    }

    /**
     * Sobrescritura del método abstracto consultar entradas. Este método muestra todos las compras guardadas en el sistema.
     */
    @Override
    public void consultarEntradas(ArrayList<Compra> compras) {
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

    /**
     * Constructor  ue recibe dos Strings extraídos de los archivos usuarios.txt y organizadores.txt
     * @param lineaUsuario Linea extraída con datos separados por '|' con información de atributos de usuario.
     * @param lineaOrganizador Linea extraída con datos separados por '|' con información de atributos de organizador.
     */
    public Organizador(String lineaUsuario, String lineaOrganizador) {
        // Linea para extraer los datos de la clase padre
        super(lineaUsuario);

        // Extraemos solos los datos que se solicitan
        String[] partesOrg = lineaOrganizador.split("\\|");
        this.empresa = partesOrg[4];
        this.cargo = partesOrg[5];
    }

    /**
     * Sobrescritura del método toString.
     */
    @Override
    public String toString() {
        return codigoUnico + "|" + cedula + "|" + nombre + "|" + apellido + "|" + empresa + "|" + cargo;
    }

    /**
     * Generar reporte de ventas registradas en sistema.
     * @param compras Lista de objetos {@link Compra} cargados en el sistema.
     */
    public void generarReporte(ArrayList<Compra> compras) {
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