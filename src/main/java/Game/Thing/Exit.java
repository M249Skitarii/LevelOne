package Game.Thing;

import Game.*;

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

        //test for some Entity touches Exit
        ArrayList<Thing> target = world.whatsHere(getPosition());
        if (target.isEmpty()){
            return false;
        }
        //test if there's an entity and take it to the world.
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
