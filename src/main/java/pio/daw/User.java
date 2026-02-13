package pio.daw;

public class User implements Localizable {
    private String id;
    private Integer nEntries = 0;   // número total de entradas del usuario
    private Boolean inside = false; // indica si está dentro o fuera

    /**
     * Constructor: crea un usuario con su ID.
     */
    public User(String id){
        this.id = id;
    }

    /**
     * Compara dos usuarios por su ID (para ordenar).
     */
    public static Integer compare (User u1, User u2){
        return u1.getId().compareTo(u2.getId());
    }

    /**
     * Devuelve el ID del usuario.
     */
    public String getId(){
        return this.id;
    }

    /**
     * Registra un nuevo evento (entrada o salida).
     * - Si entra y estaba fuera → entra y suma 1 entrada.
     * - Si entra y ya estaba dentro → no pasa nada.
     * - Si sale y estaba dentro → sale.
     * - Si sale y ya estaba fuera → no pasa nada.
     */
    public void registerNewEvent(EventType e){
        if(e == EventType.ENTRY && !this.inside){
            this.inside = true;
            this.nEntries++; // solo sumamos cuando realmente entra
        }
        else if(e == EventType.EXIT && this.inside){
            this.inside = false;
        }
    }

    /**
     * Devuelve cuántas veces ha entrado el usuario.
     */
    public Integer getNEntries(){
        return this.nEntries;
    }

    /**
     * Indica si el usuario está dentro actualmente.
     */
    @Override
    public Boolean isInside(){
        return this.inside;
    }
}
