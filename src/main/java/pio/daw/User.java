package pio.daw;

public class User implements Localizable {
    private String id;
    private Integer nEntries = 0;
    private Boolean inside = false;

    public User(String id){
        this.id = id;
    }

    public static Integer compare (User u1, User u2){
        return u1.getId().compareTo(u2.getId());// para comparar usuarios en getUserList()
    }

    public String getId(){
        return this.id;
    }

    public void registerNewEvent(EventType e){
        if(e == EventType.ENTRY && !this.inside){
            this.inside = true;
            this.nEntries++;
        }
        else if(e == EventType.EXIT && this.inside){
            this.inside = false;
        }
    }

    public Integer getNEntries(){
        return this.nEntries;
    }

     public Boolean isInside(){
        return this.inside;
     }
   
}
