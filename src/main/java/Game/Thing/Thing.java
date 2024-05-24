package Game.Thing;

import Game.*;
//TODO
//Get World to check if coordinates exists

public class Thing {

    private Coords position;
    private World world;

    public Thing(int x, int y, World w) {
        this.position = new Coords(x,y);
        this.world = w;
    }

    public String Text(){
        return "THG";
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public String toString() {
        return "{"+position+"}";
    }
    public Coords getPosition() {
        return position;
    }

    public void setPosition(Coords position) {
        this.position = position;
    }
}
