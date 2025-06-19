package com.talento_tech.BolsaEmpleo.Services;

import java.io.*;

import com.talento_tech.BolsaEmpleo.Entities.SystemInfo;
/**
 * Clase para manejar operaciones relacionadas con archivos.
 * Esta clase puede crear, leer, escribir, sobreescribir y eliminar archivos según sea necesario.
 * @author Kevin Gómez
 * @version 1.0
 * @since 2025-06-18
 * Esta clase es parte del proyecto Bolsa de Empleo de Talento Tech, para crear un log que registre las acciones realizadas en la aplicación.
 */
public class Archivos {

    /**
     * Crea un nuevo archivo con el nombre especificado.
     *
     * @param nombreArchivo El nombre del archivo a crear.
     */
    public void crearArchivo(String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);
            if (archivo.createNewFile()) {
                System.out.println("Archivo creado: " + archivo.getName());
            } else {
                System.out.println("El archivo ya existe.");
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al crear el archivo: " + e.getMessage());
        }
    }

    /**
     * Lee el contenido de un archivo existente.
     *
     * @param nombreArchivo El nombre del archivo a leer.
     * @return El contenido del archivo.
     */
    public String leerArchivo(String nombreArchivo) {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
        return contenido.toString();
    }

    /**
     * Escribe contenido en un archivo existente.
     *
     * @param nombreArchivo El nombre del archivo a escribir.
     * @param contenido     El contenido a escribir en el archivo.
     */
    public void escribirArchivo(String nombreArchivo, String contenido) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            bw.write(contenido);
            System.out.println("Contenido escrito en el archivo: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el archivo: " + e.getMessage());
        }
    }

    /**
     * Sobreescribe el contenido de un archivo existente.
     *
     * @param nombreArchivo El nombre del archivo a sobreescribir.
     * @param contenido     El nuevo contenido a escribir en el archivo.
     */
    public void sobreescribirArchivo(String nombreArchivo, String contenido) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            bw.write(contenido);
            System.out.println("Contenido sobreescrito en el archivo: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Ocurrió un error al sobreescribir el archivo: " + e.getMessage());
        }
    }

    /**
     * Elimina un archivo existente.
     *
     * @param nombreArchivo El nombre del archivo a eliminar.
     */
    public void eliminarArchivo(String nombreArchivo) {
        try {
            File archivo = new File(nombreArchivo);
            if (archivo.delete()) {
                System.out.println("Archivo eliminado: " + archivo.getName());
            } else {
                System.out.println("No se pudo eliminar el archivo.");
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error al eliminar el archivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Archivos archivos = new Archivos();
        String nombreArchivo = "./src/main/java/com/talento_tech/BolsaEmpleo/logs/log.txt";
        SystemInfo systemInfo = new SystemInfo("INFO", "usando el servicio de archivos para guardar un log de las acciones realizadas en la aplicación");
        String contenido = systemInfo.getFecha() + " - " + systemInfo.getTipoInfo() + ": " + systemInfo.getAccion() + "\n";

        // Crear un archivo
        archivos.crearArchivo(nombreArchivo);

        // Escribir en el archivo
        archivos.escribirArchivo(nombreArchivo, contenido);

        
    }

}
