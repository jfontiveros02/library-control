package pio.daw;



public class User implements Localizable {
    private String id;
    private Integer nEntries = 0;
    private Boolean inside = false;

    public User(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void registerNewEvent(EventType e){
        if (e == EventType.ENTRY && !this.inside){
            this.inside = true;
        }
        else if(e == EventType.EXIT && this.inside){
            this.inside = true;
        }
    }
   public Boolean isInside(){
    
   }
}
