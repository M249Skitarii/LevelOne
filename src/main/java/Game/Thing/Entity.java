package Game.Thing;

import Game.Attacks;
import Game.Item;
import Game.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


//inventory managing, item use, name?

public class Entity extends Thing{
    private String name;
    private HashMap<Item, Integer> inventory; //item and quantity


    public Entity(String name, HashMap<Item, Integer> inventory, int x, int y, World w) {
        super(x, y, w);
        this.name = name;
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Item, Integer> getInventory() {
        return this.inventory;
    }

    public void setInventory(HashMap<Item, Integer> inventory) {
        this.inventory = inventory;
    }


    public ArrayList<String> StrInventory(){
        ArrayList<String> res = new ArrayList<String>();

        for (Item key : getInventory().keySet()) {
            res.add(key.getName()+" x"+getInventory().get(key));
        }
        return res;
    }

    public Item getIt(String e){
        for (Item key : getInventory().keySet()){
            if (key.getName().equals(e))
                return key;

        }
        return new Item("getIT: do not exist");
    }


    public ArrayList<String> getCombatInvetory(){
        ArrayList<String> res = new ArrayList<String>();
        for (Item key : getInventory().keySet()){
            if (key.combat()){
                res.add(key.getName()+" x"+getInventory().get(key));
            }
        }
        return res;
    }




    //Add one Item to Inventory
    //if item exists in inventory add 1
    //if not, create it.
    public void itemAdd(Item item){
        Integer value;
        value= getInventory().get(item);
        if (value != null){
            getInventory().put(item, value+1);
        }
        else{
            getInventory().put(item, 1);
        }
    }
    //Add multiple item to inventory
    //if item exists in inventory add quantity
    //if not, create it.
    public void itemAdd(Item item, int i){
        Integer value;
        value= getInventory().get(item);
        if (value != null){
            getInventory().put(item, value+i);
        }
        else{
            getInventory().put(item, i);
        }
    }
    //delete one item from inventory, return false if no item.
    public boolean removeItem(Item item){
        Integer value;
        value = getInventory().get(item);
        if (value == 1){
            getInventory().remove(item);
        }
        else if(value>1){
            getInventory().put(item, value-1);
        }
        else{
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", inventory=" + inventory +
                +','+getPosition()+ '}';
    }
}
