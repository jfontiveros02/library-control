package pio.daw;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

public class Library implements Controlable {

    private Map<String, User> users;

    // Constructor privado: solo se usa desde fromFile()
    private Library() {
        this.users = new HashMap<>();
    }

    /**
     * Carga el archivo de texto y crea una biblioteca con los usuarios y sus
     * eventos (entradas y salidas).
     */
    public static Library fromFile(Path path) {
        Library library = new Library();

        try {
            // Leemos todas las líneas del archivo
            List<String> lines = Files.readAllLines(path);

            // Procesamos cada línea
            for (String line : lines) {
                if (line.isBlank()) {
                    continue; // ignorar líneas vacías
                }
                // Cada línea tiene formato: U001;ENTRADA
                String[] parts = line.split(";");
                String id = parts[0];
                String eventStr = parts[1];

                // Convertimos el texto a EventType
                EventType e = null;
                if (eventStr.equals("ENTRADA")) {
                    e = EventType.ENTRY;
                } else if (eventStr.equals("SALIDA")) {
                    e = EventType.EXIT;
                }

                // Registramos el evento en la biblioteca
                library.registerChange(id, e);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error leyendo el archivo: " + e.getMessage());
        }

        return library;
    }

    /**
     * Registra un nuevo evento (entrada o salida) para un usuario. Si el
     * usuario no existe todavía, se crea.
     */
    @Override
    public void registerChange(String id, EventType e) {
        User u = this.users.get(id);

        // Si el usuario no existe, lo creamos
        if (u == null) {
            u = new User(id);
        }

        // Actualizamos su estado (entrada/salida)
        u.registerNewEvent(e);

        // Guardamos el usuario actualizado en el mapa
        this.users.put(id, u);
    }

    /**
     * Devuelve una lista con los usuarios que están dentro actualmente.
     */
    @Override
    public List<User> getCurrentInside() {
        List<User> result = new ArrayList<>();
        for (User u : users.values()) {
            if (u.isInside()) {
                result.add(u);
            }
        }

        result.sort(Comparator.comparing(User::getId));
        return result;
    }

    /**
     * Devuelve la lista de usuarios que tienen el mayor número de entradas.
     * Puede haber más de uno si empatan.
     */
    @Override
    public List<User> getMaxEntryUsers() {
        int max = 0;
        for (User u : users.values()) {
            if (u.getNEntries() > max) {
                max = u.getNEntries();
            }
        }

        List<User> result = new ArrayList<>();
        for (User u : users.values()) {
            if (u.getNEntries() == max) {
                result.add(u);
            }
        }

        result.sort(Comparator.comparing(User::getId));
        return result;
    }

    /**
     * Devuelve la lista de todos los usuarios ordenados por su ID.
     */
    @Override
    public List<User> getUserList() {
        List<User> list = new ArrayList<>(users.values());
        // Orden con Comparator
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User a, User b) {
                return a.getId().compareTo(b.getId());
            }
        });
        return list;
    }

    /**
     * Imprime un resumen con: 1. Usuarios dentro 2. Número de entradas por
     * usuario 3. Usuario(s) con más entradas
     */
    @Override
    public void printResume() {
        System.out.println("Usuarios actualmente dentro de la biblioteca:");
        for (User u : getCurrentInside()) {
            System.out.println(u.getId());
        }

        System.out.println();

        System.out.println("Número de entradas por usuario:");
        for (User u : getUserList()) {
            System.out.println(u.getId() + " -> " + u.getNEntries());
        }

        System.out.println();

        System.out.println("Usuario(s) con más entradas:");
        for (User u : getMaxEntryUsers()) {
            System.out.println(u.getId());
        }
    }

}
