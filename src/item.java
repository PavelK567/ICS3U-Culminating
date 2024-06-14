import java.util.ArrayList;

public class item {
    // Variables for item information
    public String name;
    public String desc;
    public Boolean pickable;
    public ArrayList<item> useableOn = new ArrayList<item>();
    public String usedText;

    // Changes the name, description and pickupability of an item
    public void setInfo(String n, String d, boolean p, String u){
        name = n;
        desc = d;
        pickable = p;
        usedText = u;
    }

    // Adds a useable on Item
    public void addUseable(item i){
        useableOn.add(i);
    }



}
