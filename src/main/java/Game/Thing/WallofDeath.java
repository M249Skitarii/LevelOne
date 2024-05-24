package Game.Thing;

import Game.*;

public class WallofDeath extends Thing implements Encounters{

    public WallofDeath(int x, int y, World w) {
        super(x, y, w);
    }
    @Override
    public String Text(){
        return "WoD";
    }
    @Override
    public boolean encounters(World world) {
        Player target = world.getPlayer();
        if (target.getPosition().equals(getPosition())) {
            if (!target.getInventory().containsKey(target.getIt("ItemY"))) {
                target.kill();

            }
        }
        return false;
    }
}
