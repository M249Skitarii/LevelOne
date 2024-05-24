package Game;

import Game.Thing.Killable;
import Game.Thing.Monster;
import Game.Thing.Player;

import java.util.ArrayList;

public class Fight {

    int time = 0; //time of fight, useful for cooldowns
    public Player player;
    public Monster enemy;

    public Fight(Player p, Monster m) {
        this.player = p;
        this.enemy = m;
    }

    public boolean combat() {
        boolean f = true;
        boolean p = true;//test for player turn to pass
        Menue menue = new Menue();
        ArrayList<String> choice = new ArrayList<String>();
        choice.add("inventaire");
        choice.add("attacks");
        Item i;
        while (f && !player.isDead() && !enemy.isDead()) {
            // add fight in action paramter so actions can affect in the fight.
            String c;
            p = true;
            while (p) {
                c = menue.call(choice);

                if (c.equals("attacks")) {
                    player.action(this, player.getAtt(menue.call(player.StrAttacks()))); //menu(getAttacks)
                    p = false;
                } else if (c.equals("inventaire")) {
                    if (!player.getInventory().isEmpty()) {
                        i = player.getIt(menue.call(player.StrInventory()).split(" ")[0]);
                        if (i.combat()) {
                            i.use(player, enemy);
                            p = false; //end player turn
                        } else {
                            Message m = new Message("Game", "Cannot be used in combat");
                            m.send();
                        }
                    } else {
                        Message m = new Message("Game", "No more Combat items");
                        m.send();
                    }

                } else if (c.equals("Window closed by user")) {
                    p = false;//end player turn
                    f = false;//end combat
                } else {
                    Message m = new Message("Game", "unknown action");
                }
            }


            if (!enemy.isDead() && f)
                enemy.action(player);

            if (player.isDead() || enemy.isDead()) {
                f = false;
            } else {
                player.ticker();
                enemy.ticker();
                System.out.println("Cat:" + player.getLife() + " | " + enemy.getName() + ":" + enemy.getLife());
            }
        }

            if (player.isDead()) {
                player.getWorld().setStatus(2);
                return false;
            }
            //create a function in monster who choose their attacks
            //create a ticker function in killable
            //player choose attack
            //if player dies, change current world message and status return false
            //
            return true;
    }
}
