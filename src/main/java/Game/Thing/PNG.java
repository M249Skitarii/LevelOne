package Game.Thing;

import Game.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class PNG extends Entity implements Encounters{
    public PNG(String name, HashMap<Item, Integer> inventory, int x, int y, World w) {
        super(name, inventory, x, y, w);
    }
    @Override
    public String Text(){
        return "PNG";
    }

    @Override
    public boolean encounters(World world) {
        Player target = world.getPlayer();
        if (target.getPosition().equals(getPosition())) {
            if (target.getInventory().isEmpty()){
                Message m = new Message(getName(), "Hello you little cat");
                m.send();
            }
            else {
                Random random = new Random();
                List<Item> keys = new ArrayList<Item>(target.getInventory().keySet());
                Item i = keys.get(random.nextInt(keys.size()));
                itemAdd(i);
                target.removeItem(i);
            }
        }
        return false;
    }
}
