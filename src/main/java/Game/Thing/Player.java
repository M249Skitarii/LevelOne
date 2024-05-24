package Game.Thing;

import Game.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Player extends Killable{
    public Player(String name, int life, HashMap<Item, Integer> inventory, int x, int y, World w, ArrayList<Attacks> a) {
        super(name, life, inventory, x, y, w, a);
        this.getWorld().setPlayer(this);
    }
    @Override
    public String Text(){
        return "CAT";
    }
    @Override
    public void setWorld(World w){
        super.setWorld(w);
        super.getWorld().setPlayer(this);
    }
    public void action(Fight f, Attacks a){
        a.execute(f.player, f.enemy);
        System.out.println("player attacks!");
    }
    @Override
    public void kill(){
        super.kill();
        getWorld().setStatus(2);
        getWorld().setMessage("You Died");
    }
    @Override
    public String toString(){
        return super.toString()+getAttacks();
    }
}
