package Game;

import Game.Thing.Entity;
import Game.Thing.MonsterB;

public class ItemB extends Item{

    public ItemB(String n) {
        super(n);
    }
    @Override
    public void use(Entity caster, Entity enemy){
        if( enemy instanceof MonsterB){
            ((MonsterB) enemy).kill();
        }
        else{
            Message m = new Message("Game","Can't use that on that monster");
            m.send();
        }
    }
}
