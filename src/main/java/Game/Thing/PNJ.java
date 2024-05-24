package Game.Thing;

import Game.*;

import java.util.HashMap;

public class PNJ extends Entity implements Encounters {

    public PNJ(String name, HashMap<Item, Integer> inventory, int x, int y, World w) {
        super(name, inventory, x, y, w);
    }
    @Override
    public String Text(){
        return "PNJ";
    }

    @Override
    public boolean encounters(World world) {
        Player target = world.getPlayer();
        if (target.getPosition().equals(getPosition())){
            Message m = new Message(getName(), "Je suis pas Gandhi");
            m.send();
            target.getWorld().setStatus(2);
            target.getWorld().setMessage("Vous avez été viré");
        }
        return false;
    }
}
