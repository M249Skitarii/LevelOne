package Game.Thing;

import Game.Item;
import Game.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class OnMapItem extends Thing implements Encounters{
    private Item item;

    public OnMapItem(Item item , int x, int y, World w) {
        super(x, y, w);
        this.item = item;
    }

    @Override
    public String toString() {
        return super.toString() +
                "item=" + item +
                '}';
    }
    @Override
    public String Text(){
        return "ITEM";
    }

    @Override
    public boolean encounters(World world) {
        //test for someone touching OnMapItem
        ArrayList<Thing> target = world.whatsHere(getPosition());
        if (target.isEmpty()){
            return false;
        }
        //test if there's an entity and give it to the first one.
        Iterator<Thing> thingIterator = target.iterator();
        Thing i;
        while (thingIterator.hasNext()){
            i = thingIterator.next();
            if (i instanceof Entity){
                ((Entity) i).itemAdd(this.item);
                this.getWorld().getContent().remove(this);
                return true;
            }
        }

        return false;
    }
}