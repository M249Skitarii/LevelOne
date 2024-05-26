package Game.Thing;

import Game.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Monster extends Killable implements Encounters{

    public Monster(String name, int life, HashMap<Item, Integer> inventory, int x, int y, World w, ArrayList<Attacks> a) {
        super(name, life, inventory, x, y, w, a);
    }

    // Monster's behavior in combat
    public void action(Killable k){
        System.out.println("monster attacks!");
    }
    @Override
    public String Text(){
        return "MTR";
    }
    @Override
    public void kill(){
        setStatus(0);
        getWorld().getContent().remove(this);
        System.out.println("oh no, im dead");
        Iterator<Item> it = getInventory().keySet().iterator();
        Item i;
        while (it.hasNext()){
            i = it.next();
            getWorld().enterWorld(new OnMapItem(i, getPosition().getX(), getPosition().getY(),getWorld()));
        }

    }

    @Override
    public boolean encounters(World world) {
        Player target = world.getPlayer();
        if (target.getPosition().equals(getPosition())){
            Fight f = new Fight(target, this);
            f.combat();
        }
        return false;
    }

    //encounter combat trigger.

}
