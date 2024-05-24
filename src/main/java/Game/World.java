package Game;

import Game.Thing.*;

import java.util.*;

// handle coords and moving. :  done
// worlds will be squares of predefined size :done

// TODO
//error handling on moving
// interactions: done
// //Create Class or Enum Game status as class attribute: OK 0 VICTORY 1 DEFEAT 2. or just int
//
public class World {


    private Player player;
    private String message;
    private int status;
    private ArrayList<Thing> content;
    private int size = 10;

    public World(List<Thing> content) {
        this.content = new ArrayList<Thing>();
        Thing element;
        Iterator<Thing> thingIterator = content.iterator();
        while(thingIterator.hasNext()){
            element = thingIterator.next();
            this.content.add(element);
        }

    }
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
        }

    public void setStatus(int status) {
        this.status = status;
    }
    public ArrayList<Thing> getContent() {
        return this.content;
    }
    public void leaveWorld(Thing t){
        getContent().remove(t);
    }
    public void enterWorld(Thing t){
        t.setWorld(this);
        getContent().add(t);
    }
    public void setContent(ArrayList<Thing> content) {
        this.content = content;
    }

    //spawn Thing on a position on world.
    // if spawns on the entry or exit, then spawn denied and return false.
    /*public boolean spawn(Thing thing, Coords coords){
        //if spawn is occupied, denied.
        if (getContent().containsKey(coords)){
            return false;
        }
        getContent().put(coords, thing);
        thing.setPosition(coords);
        return true;
    }*/

    // generate world
    public void ticker(){
        Iterator<Thing> thingIterator = getContent().iterator();
        ArrayList<Thing> enc = new ArrayList<Thing>();
        Thing i;

        while(thingIterator.hasNext()){
            i = thingIterator.next();
            if (i instanceof Encounters){
                enc.add(i);
            }
        }
        thingIterator = enc.iterator();

        while(thingIterator.hasNext()){
            i = thingIterator.next();
            ((Encounters) i).encounters(i.getWorld());
        }
    }


    //returns an arraylist of entity at coords.
    public ArrayList<Thing> whatsHere(Coords c){
        ArrayList<Thing> world = getContent();
        ArrayList<Thing> res = new ArrayList<Thing>();

        Iterator<Thing> thingIterator = world.iterator();
        Thing element;
        while(thingIterator.hasNext()){
            element = thingIterator.next();
             if (element.getPosition().equals(c))
                res.add(element);
        }
        return res;
    }
    //return an arraylist of entity in a zone.
    public ArrayList<Thing> whatsHere(ArrayList<Coords> zone){
        ArrayList<Thing> res = new ArrayList<>();
        Iterator<Coords> thingIterator = zone.iterator();

        while(thingIterator.hasNext()){
           res.addAll(whatsHere(thingIterator.next()));
        }
        return res;
    }

    @Override
    public String toString() {
        return "World{" +
                "content=" + content +
                '}';
    }

    //move: get coords of thing, get it in content, change coords in thing AND content.
    // Make move return Game_status
    public boolean move(Thing thing, Coords new_pos){
        System.out.println(new_pos + " "+status);
        Coords position;
        boolean pass = false;
        position = thing.getPosition();

        //test if destination is valid
        if ( new_pos.getX() >size-1 || new_pos.getY() >size-1 || new_pos.getY() <0 || new_pos.getX() <0){
            //error
            return pass;
        }

        //test if thing exists in world.
        if (getContent().contains(thing)){
            //test if destination coords can be accessed;
            // get all thing at position new_pos
            Iterator<Thing> thingIterator = whatsHere(new_pos).iterator();
            Thing i;
            pass = true;
            while(thingIterator.hasNext() && pass==true){
                i = thingIterator.next();
                //test if thing blocks passage
                if (i instanceof Blocker){
                    pass = ((Blocker) i).blocker(thing);
                }
            }
            //move if not blocked
            if (pass == true) {
                thing.setPosition(new_pos);
                }
            //activate all interaction
            ticker();
        }
        else {
            //error handling
            return false;
        }
        return pass;

    }
}
