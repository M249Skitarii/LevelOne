package Game;


//change decor
//change entity
//activate in one shot
//change World
//Invoke victory
//DEATH

import Game.Thing.Entity;

public class Item {
    private String name;
    //private Event[] effect;
    public Item(String n){
        this.name = n;
    }

    //adding this method so Item can modify activate when he get into inventory.
    public void give(Entity e){
        e.itemAdd(this);
    }
    public String getName(){
        return name;
    }
    public boolean combat(){
        return true;
    }

    public void use(Entity e){
        System.out.println(e.getName()+ "use" +getName());
        e.removeItem(this);
        // do things
        //surcharge same function in combat;
    }
    public void use(Entity caster, Entity ennemy){
        System.out.println("used");
        caster.removeItem(this);
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                '}';
    }
}
