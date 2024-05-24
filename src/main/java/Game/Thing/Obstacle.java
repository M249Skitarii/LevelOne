package Game.Thing;
import Game.*;
public class Obstacle extends Thing implements Blocker{

    public Obstacle(int x, int y, World w) {
        super(x, y, w);
    }

    @Override
    public String Text(){
        return "OBS";
    }
    @Override
    public boolean blocker(Thing thing) {
        return false;
    }
}
