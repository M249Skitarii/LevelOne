package Game;

import Game.Thing.Killable;

public class PowerWordKill extends Attacks{

    public static String name = "power-word: kill";


    public void execute(Killable caster, Killable enemy){
        enemy.kill();
        System.out.println("POWERWORD: kill");
    }
    @Override
    public String getName() {
        return name;
    }
}
