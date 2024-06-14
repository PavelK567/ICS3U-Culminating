import java.util.ArrayList;

public class room {
    // Variables for room information
    public String name;
    public String desc;
    ArrayList<room> connects = new ArrayList<room>();
    ArrayList<item> contains = new ArrayList<item>();

    // Changes the name and description of a room
    public void setInfo(String n, String d){
        name = n;
        desc = d;
    }

    // Adds a connected Room
    public void addRoom(room c){
        connects.add(c);
    }

    // Removes a connected Room
    public void removeRoom(room c){
        connects.remove(c);
    }

    // Adds a contained Item
    public void addItem(item i){
        contains.add(i);
    }

    // Removes a contained Item
    public void removeItem(item i){
        contains.remove(i);
    }

}
