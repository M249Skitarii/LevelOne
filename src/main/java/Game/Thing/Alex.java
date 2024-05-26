package Game.Thing;

import Game.Item;
import Game.Message;
import Game.PowerWordKill;
import Game.World;

import java.util.HashMap;

public class Alex extends Entity implements Encounters{
    public Alex(String name, HashMap<Item, Integer> inventory, int x, int y, World w) {
        super(name, inventory, x, y, w);
    }
    @Override
    public String Text(){
        return "ALEX";
    }

    @Override
    public boolean encounters(World world) {
        Player target = world.getPlayer();
        if (target.getPosition().equals(getPosition())) {
            if(!(target.StrAttacks().contains("power-word: kill"))) {
                target.getAttacks().add(new PowerWordKill());
                new Message("game", "Get PowerWordKill").send();
            }
        }
        return false;
    }
}
