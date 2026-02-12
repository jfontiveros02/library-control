package pio.daw;

import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library implements Controlable {
    private Map<String,User> users;

    /**
     * Read the library register file (.txt) and create a library object
     * with the current status of the users.PREGUNTAAAAR JUANCARLOS!!!!!!!!!!!!!!!!!!!
     * @param path Library registry file path.
     * @return Library object.
     */

     private Library(){
        this.users = new HashMap<>();
    }

    public static Library fromFile(Path path){// lo que separa se lo pasas a register change
        Library library = new Library();
        //TODO
        return library;
    }

     public void registerChange(String id, EventType e){
       User u = this.users.get(id);
       if(u == null){
        u = new User(id);

       }
        u.registerNewEvent();



    /**
     * Return the list of all of the current Users
     *
     * @return current users.
     */
     }
       
   

    //TODO lo de controlable
}
