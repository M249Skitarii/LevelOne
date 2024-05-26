package Game;

//attack as a name
//attack is in a arraylist in killable entities.
//attacks can recieve the attacker and/or the target. Depends on the attack.
//Attacks can have an array of effects
import Game.Thing.Killable;

import java.util.ArrayList;

public class Attacks {
    private String name;
    private ArrayList<Effect> effect = new ArrayList<Effect>();

    public void execute(Killable caster, Killable enemy){
        //do what the attack do.
    }

    public String getName() {
        return name;
    }
    public void setName(String n){
        name = n;
    }
}
