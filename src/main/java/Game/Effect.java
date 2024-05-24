package Game;

import Game.Thing.Entity;
import Game.Thing.Killable;


// Adding effect method

// effect subclass: Temporary effect, continuous effect (do something every turn)
public class Effect {

    public void getEffect(Killable k){
        // get what the effect does
        k.getEffects().add(this);
    }

    public void allTurn(Killable k){
        //do something to k every turn
    }

    public void wornOff(Killable k){
        if (k.getEffects().remove(this)){
            //undo effect
        }
    }
}