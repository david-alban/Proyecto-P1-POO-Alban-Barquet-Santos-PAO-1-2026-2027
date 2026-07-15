package com.proyecto.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Clase utilitaria para el manejo y persistencia de datos en archivos de texto.
 * Proporciona métodos estáticos para leer, escribir y verificar la existencia de archivos,
 * aplicando el principio de abstracción en el sistema.
 */

public class ManejoArchivos {
    /**
     * Lee un archivo de texto y devuelve su contenido línea por línea.
     * Este método omite automáticamente la primera línea del archivo, asumiendo que es el encabezado.
     *
     * @param nombrearchivo Nombre o ruta del archivo de texto a leer.
     * @return Una lista de tipo ArrayList con las líneas leídas del archivo, excluyendo el encabezado.
     */

    public static ArrayList<String> LeeFichero(String nombrearchivo) {
        ArrayList<String> lineas = new ArrayList<>();
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archivo = new File(nombrearchivo);
            fr = new FileReader(archivo, StandardCharsets.UTF_8);
            br = new BufferedReader(fr);

            String linea;
            boolean esEncabezado = true; 
            while ((linea = br.readLine()) != null) {
                if (esEncabezado) {
                    esEncabezado = false; 
                    continue;
                }
                lineas.add(linea); 
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return lineas;

    }

    /**
     * Escribe o añade una nueva línea de texto al final de un archivo especificado.
     * Si el archivo no existe, lo crea. Si ya existe, añade la información sin borrar el contenido anterior.
     *
     * @param nombreArchivo Nombre o ruta del archivo de texto donde se va a escribir.
     * @param linea Cadena de texto que se agregará al archivo.
     */

    public static void EscribirArchivo(String nombreArchivo, String linea) {

        FileWriter fichero = null;
        BufferedWriter bw = null;

        try {
            fichero = new FileWriter(nombreArchivo, true);
            bw = new BufferedWriter(fichero);
            bw.write(linea + "\n");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero) {
                    // fichero.close();
                    bw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * Verifica si un archivo de texto existe en la ruta especificada.
     *
     * @param nombreArchivo Nombre o ruta del archivo a verificar.
     * @return true si el archivo existe; false en caso contrario.
     */

    public static boolean existeArchivo(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        if(archivo.exists()){
            return true;
        }
        return false;
    }

}
