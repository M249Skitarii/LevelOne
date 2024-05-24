package Game.Thing;

import Game.Attacks;
import Game.Fight;
import Game.Item;
import Game.World;

import java.util.ArrayList;
import java.util.HashMap;

public class Monster extends Killable implements Encounters{

    public Monster(String name, int life, HashMap<Item, Integer> inventory, int x, int y, World w, ArrayList<Attacks> a) {
        super(name, life, inventory, x, y, w, a);
    }

    // choose attack method
    public void action(Killable k){
        //do something
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
