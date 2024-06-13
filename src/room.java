import java.util.ArrayList;

public class room {
    public String name;
    public String desc;
    ArrayList<room> connects;

    public void initializeRoom(String n, String d){
        name = n;
        desc = d;
    }

    public void connectTo(room c){
        connects.add(c);
    }

    public void disconnect(room c){
        connects.remove(c);
    }

}
