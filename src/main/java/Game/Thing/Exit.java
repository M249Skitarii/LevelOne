package Game.Thing;

import Game.Coords;
import Game.World;

import java.util.ArrayList;
import java.util.Iterator;

public class Exit extends Thing implements Encounters{

    private World out; //world where exit takes the player
    private Coords entry; // Coords where exit takes the player

    public Exit(World out, Coords entry, int x, int y, World w) {
        super(x, y, w);
        this.out = out;
        this.entry = entry;
    }
    @Override
    public String Text(){
        return "EXIT";
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
        boolean teleport = false;
        while (thingIterator.hasNext()){
            i = thingIterator.next();
            if (i instanceof Entity){
                teleport = true;
                world.leaveWorld(i);
                this.out.enterWorld(i);
                i.setPosition(this.entry);
            }
        }

        return teleport;
    }
}
