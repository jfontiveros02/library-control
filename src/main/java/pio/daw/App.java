package pio.daw;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {

    /**
     * Parse the arguments of the program to get the library registry file path.
     * Exits the program if the args are not correct or the file does not
     * exists.
     *
     * @param args program args.
     * @return Path to file if exists.
     */
    public static Path getPathFromArgs(String[] args) {// este args no es el mismo del main
        //Scanner sc = new Scanner(System.in);
        if (args == null || args.length < 1) {
            throw new IllegalArgumentException(">>> Escriba el directorio del archivo: ");
        }
        Path p = Paths.get(args[0]);
        if (!Files.exists(p)) {// se lee: si el archivo no existe buscado en la ruta "p"...
            throw new IllegalArgumentException(">>> El archivo no existe: " + p);
        }
        return p;
    }
// si es un array porqu
    public static void main(String[] args) {
        System.out.println("/n>>Escriba un directorio<<");
        Path p = getPathFromArgs(args);
        Controlable controler = Library.fromFile(p);
        controler.printResume();
    }
}
