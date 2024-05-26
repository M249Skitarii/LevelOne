package Game.Thing;

import Game.*;

import java.util.ArrayList;
import java.util.HashMap;

public class MonsterB extends Monster{
    public MonsterB(String name, int life, HashMap<Item, Integer> inventory, int x, int y, World w, ArrayList<Attacks> a) {
        super(name, life, inventory, x, y, w, a);
    }
    //drop item when dead
    @Override
    public void kill(){
        super.kill();
        Message m = new Message(getName(),"Good job, you killed me");
        m.send();

    }
}
