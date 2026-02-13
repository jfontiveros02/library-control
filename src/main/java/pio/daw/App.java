package pio.daw;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {

    /**
     * Este método recibe los argumentos del programa (args)
     * y devuelve la ruta del archivo si es válida.
     * 
     * - Si no hay argumentos → error.
     * - Si la ruta no existe → error.
     */
    public static Path getPathFromArgs(String[] args) {
        // Comprobamos que haya al menos un argumento
        if (args == null || args.length < 1) {
            throw new IllegalArgumentException(">>> Escriba el directorio del archivo: ");
        }

        // Convertimos el texto del argumento en un Path
        Path p = Paths.get(args[0]);

        // Comprobamos que el archivo exista
        if (!Files.exists(p)) {
            throw new IllegalArgumentException(">>> El archivo no existe: " + p);
        }

        return p;
    }

    /**
     * Método principal del programa.
     * 
     * 1. Pide la ruta del archivo (viene en args).
     * 2. Carga la biblioteca desde ese archivo.
     * 3. Imprime el resumen final.
     */
    public static void main(String[] args) {
        System.out.println("/n>>Escriba un directorio<<");

        // Obtenemos la ruta del archivo desde los argumentos
        Path p = getPathFromArgs(args);

        // Cargamos la biblioteca leyendo el archivo
        Controlable controler = Library.fromFile(p);

        // Mostramos el resumen de usuarios
        controler.printResume();
    }
}
