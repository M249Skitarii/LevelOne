package Game;

import Game.Thing.Killable;

public class TempEffect extends Effect{

    int time;

    public TempEffect(Killable target, int time) {
        this.time = time;
    }
    @Override
    public void allTurn(Killable k){
        //do
        //decrease time, if time hit 0 wore off effect.
        time--;
        if (time <= 0)
            wornOff(k);
    }
    public int getTime() {
        return time;
    }
}
