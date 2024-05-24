package Game;

import java.util.ArrayList;
import com.example.lostcat.Controller;
public class Menue {

    ArrayList<String> all;

    public Menue(){
    }

    public String call(ArrayList<String> e){
        this.all = e;
        return Controller.Menu(this);
    }
    public String choice() {
        return "";
    }

    public ArrayList<String> getAll() {
        return all;
    }

}
