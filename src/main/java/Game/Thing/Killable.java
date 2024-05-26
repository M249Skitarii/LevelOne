package Game.Thing;

import Game.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

// add a tick method

public class Killable extends Entity{

    private int strength = 0; // attack modifier.
    private int resistance = 0; // damage taken modifier.


    // use boolean to lock methods so multiple effect can lock them
    // without adding a test for all effect in killable methods.
    private boolean invicible = false; //lock loosing life
    private boolean oneShot = false; //can be killed in one attack
    private boolean raaaah = false; //can kill in one attack
    private boolean stupid = false; //cannot use item

    private int life;
    private int status = 1; // 0: dead 1: alive
    ArrayList<Effect> effects = new ArrayList<Effect>(); //list of effect in place.

    ArrayList<Attacks> attacks = new ArrayList<Attacks>(); // list of attacks killable is capable of
    public Killable(String name, int life, HashMap<Item, Integer> inventory, int x, int y, World w, ArrayList<Attacks> a) {
        super(name, inventory, x, y, w);
        this.life = life;
        this.attacks = a;
    }

    public ArrayList<Attacks> getAttacks() {
        return attacks;
    }
    //return string list for Menue Function
    public ArrayList<String> StrAttacks(){
        ArrayList<String> res = new ArrayList<String>();
        Iterator<Attacks> iterator = getAttacks().iterator();
        while (iterator.hasNext()){
            res.add(iterator.next().getName());
        }
        return res;
    }
    //find Attack by name in this Killable's Attack list
    public Attacks getAtt(String e){
        Iterator<Attacks> iterator = getAttacks().iterator();
        Attacks i = getAttacks().get(0);
        while (iterator.hasNext()){
            i = iterator.next();
            if (e.equals(i.getName()))
                return i;

        }
        return i;
    }

    public void setAttacks(ArrayList<Attacks> attacks) {
        this.attacks = attacks;
    }

    public ArrayList<Effect> getEffects() {
        return effects;
    }

    public void setEffects(ArrayList<Effect> effects) {
        this.effects = effects;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getStatus() {
        return status;
    }
    public boolean isDead(){
        return (getStatus() == 0);
    }

    public void setInvicible(boolean invicible) {
        this.invicible = invicible;
    }

    public void setOneShot(boolean oneShot) {
        this.oneShot = oneShot;
    }

    public void setRaaaah(boolean raaaah) {
        this.raaaah = raaaah;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public int getStrength() {
        return strength;
    }

    public int getResistance() {
        return resistance;
    }

    public void setStupid(boolean stupid) {
        this.stupid = stupid;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public boolean useItem(Item o){
        if ( getInventory().get(o) != null && !stupid){
            new Message("game", "sorry can't use that now");
            return true;
        }
        return false;
    }
    public void loosePV(int x){
        //if killable is not invicible or attacks heals
        if (x<0 || !invicible){
            setLife(getLife()-x+resistance);
        }
        if (oneShot){
            setLife(0);
        }
        if (life<=0){
            kill();
        }

    }

    public void kill(){
        setStatus(0);
        getWorld().getContent().remove(this);
    }

    public void ticker(){
        Iterator<Effect> effectIterator = getEffects().iterator();
        Effect i;
        while (effectIterator.hasNext()){
            i = effectIterator.next();
            i.allTurn(this);
        }
    }
}
