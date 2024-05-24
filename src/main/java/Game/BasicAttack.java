package Game;

import Game.Thing.Killable;

public class BasicAttack extends Attacks{

    int damage;
    public BasicAttack(String Name, int d){
        setName(Name);
        damage = d;
    }

    @Override
    public void execute(Killable caster, Killable k) {
        k.loosePV(damage+ caster.getStrength());
    }
}
