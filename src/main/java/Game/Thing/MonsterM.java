package Game.Thing;

import Game.Attacks;
import Game.Item;
import Game.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MonsterM extends Monster{

    public MonsterM(String name, int life, HashMap<Item, Integer> inventory, int x, int y, World w, ArrayList<Attacks> a) {
        super(name, life, inventory, x, y, w, a);
    }
    @Override
    public void kill(){
        super.kill();
        int count = 0;
        ArrayList<Thing> e = getWorld().getContent();
        Iterator<Thing> thingIterator = getWorld().getContent().iterator();
        while(thingIterator.hasNext()){
            if (thingIterator.next() instanceof MonsterM)
                count++;
        }
        if (count <= 0) //spawn itemZ monsterM's Position
            getWorld().getContent().add(new OnMapItem(new Item("itemZ"), getPosition().getX(), getPosition().getY(), getWorld()));
    }
}
