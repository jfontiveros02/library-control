package pio.daw;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library implements Controlable {

    private Map<String, User> users;

    /**
     * Read the library register file (.txt) and create a library object with
     * the current status of the users.
     *
     * @param path Library registry file path.
     * @return Library object.
     */
    private Library() {
        this.users = new HashMap<>();
    }

    public static Library fromFile(Path path) {
        Library library = new Library();
        //TODO
        String line = null;
        String[] splittedLine = line.split(";");
        String id = splittedLine[0];
        EventType e = null;
        if (splittedLine[1].equals("ENTRADA")) {
            e = EventType.ENTRY;
        }
        library.registerChange(id, e);
        return library;
    }

    @Override
    public void registerChange(String id, EventType e) {
        User u = this.users.get(id);
        if (u == null) {
            u = new User(id);
        }
        u.registerNewEvent(e);
        this.users.put(id, u);

        /**
         * Return the list of all of the current Users
         *
         * @return current users.
         */
    }

    @Override
    public List<User> getCurrentInside() {
        return users.values().stream().filter(User::isInside).toList();
        /**
         * Get the user with the biggest amount of entries.
         *
         * @return user that enters more tiemes.
         */
    }

    @Override
    public List<User> getMaxEntryUsers() {
        int max = users.values().stream().mapToInt(User::getNEntries).max().orElse(0);
        return users.values().stream().filter(u -> u.getNEntries() == max).toList();

        /**
         * Get the list with all the users that has enter the place ordered by
         * User ID.
         *
         * @return
         */
    }

    @Override
    public List<User> getUserList() {
        return users.values().stream().sorted(Comparator.comparing(User::getId)).toList();
        /**
         * Print a resume of the current status: 1. Current users 2. Entries per
         * user 3. User with more entries
         */
    }

    @Override
    public void printResume() {
        System.out.println("=== RESUMEN DE LA BIBLIOTECA ===");

        System.out.println();
        System.out.println("Usuarios actualmente dentro:");
        for (User u : getCurrentInside()) {
            System.out.println(" - " + u.getId());
        }

        System.out.println();
        System.out.println("Entradas por usuario:");
        for (User u : getUserList()) {
            System.out.println(" - " + u.getId() + ": " + u.getNEntries() + " entradas");
        }

        System.out.println();
        System.out.println("Usuario(s) con más entradas:");
        for (User u : getMaxEntryUsers()) {
            System.out.println(" - " + u.getId() + " (" + u.getNEntries() + " entradas)");
        }
    }

}
//TODO lo de controlable

