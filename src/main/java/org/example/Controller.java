package org.example;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.example.Game;
import Game.*;
import Game.Thing.*;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import java.util.concurrent.atomic.AtomicReference;
import java.util.*;

import javafx.scene.control.Alert.AlertType;


//add interface event
//Effet dans la durée
//Access to world ->Encounters or Thing

//Combat system
//ticks
//Triggered by encounters.

//PNJ

// item
// item get combat interaction and onMapInteraction interfaces

//Victory and defeat/
//World has status. break loop if the player's world get a defeat or victory status.

// add damage types.

// effect and capacity
/*
Strength
Life
Immunity

time of effect, add tick interface.
that will decrease time in effect.
add attribute for entity (Array of Effects)
Effect class
    Effect
Temporary Effect
    int Time
    Effect.

*/

//Test idea
// item fruits = give you health or add capacity
// item gun = kill ennemy if you have ammo, combat only.
// item ammo = get to use gun.
// item that replace things.
//  item that awaken the trees and become monsters.
//  Item that make all obstacle traversable.

// obstacle Arbre = chance to get fruits if you r near
// obstacle you can pass with an item
// obstacle you delete with an item
// do a PNJ


// pnj that can nuke the world of all monsters.

// enemy can steal player
// gun attack one shot everyone

//create WereWolf pnj
//create night and day cycle
//if its day just pnj if night Werewolf monster.
// one of the pnj give sylver gun;
//Sylver gun only thing that can kill WereWolves;

// do a pnj that can fight
// when you beat him you can buy him a portal gun
// using the portal gun creates an exit to another world


// le génie de la lampe:
//item lampe
//invoque génie
//donne un voeux.

// une attaque qui utilisée 3 fois d'affilé, tue l'adversaire.

public class Controller {

    //current world
    public Controller(){
    }
    static Player player;

    //inventaire
    //joueur
    //return selected option in listView

    //attach player datas.
    //get things a text to display
    //move handling
    //Menus Backend

    private static void generateWorld(ArrayList<Thing> e){
        Iterator<Thing> thingIterator = e.iterator();
        Thing i;
        while (thingIterator.hasNext()){
            i = thingIterator.next();
            i.getWorld().enterWorld(i);
        }
    }
    public static void build() {
        //create attack
        PowerWordKill pwk = new PowerWordKill();
        ArrayList<Attacks> arsenal = new ArrayList<Attacks>();
        BasicAttack taper = new BasicAttack("taper", 1);
        arsenal.add(pwk);
        arsenal.add(taper);

        //Create World
        World world = new World(new ArrayList<Thing>());
        World world2 = new World(new ArrayList<Thing>());


        ArrayList<Thing> allThing = new ArrayList<Thing>();
        //create Player
        Player p = new Player("eddy", 4, new HashMap<Item, Integer>(), 1, 2, world, arsenal);
        p.setStrength(2);
        allThing.add(p);
                //create things
        allThing.add(new Monster("ydde", 4, new HashMap<Item, Integer>(), 1, 2, world2, new ArrayList<Attacks>()));
        allThing.add(new OnMapItem(new Item("pop"), 3, 3, world));
        allThing.add(new OnMapItem(new ItemB("itemB"), 4,4, world2));
        allThing.add(new OnMapItem(new Item("ItemY"),5,5,world2));
        allThing.add(new Exit(world2, new Coords(1, 1), 3, 2, world));
        allThing.add(new Obstacle(2, 3, world));
        allThing.add(new PNJ("Le Directeur", new HashMap<>(), 4,7, world2));
        //public MonsterB(String name, int life, HashMap<Item, Integer> inventory, int x, int y, World w, ArrayList<Attacks> a)
        allThing.add(new WallofDeath(8,8, world2));
        allThing.add(new WallofDeath(8,9, world2));
        allThing.add(new WallofDeath(9,8, world2));
        allThing.add(new MonsterM("Arlo", 1, new HashMap<Item,Integer>(), 7,7, world,new ArrayList<Attacks>()));
        allThing.add(new MonsterM("Arlo", 1, new HashMap<Item,Integer>(), 8,7, world,new ArrayList<Attacks>()));
        allThing.add(new MonsterM("Arlo", 1, new HashMap<Item,Integer>(), 9,7, world,new ArrayList<Attacks>()));
        //public PNG(String name, HashMap<Item, Integer> inventory, int x, int y, World w)
        allThing.add(new PNG("ElTarba", new HashMap<Item,Integer>(), 1, 9, world2));
        allThing.add(new MonsterB("Monster B", 7, new HashMap<Item, Integer>(), 9,9, world2, new ArrayList<Attacks>()));
        //add things to world

        generateWorld(allThing);
        player = p;

    }

    public static String talker(Talk t) {
        // Use AtomicReference for thread-safe mutable reference
        AtomicReference<String> res = new AtomicReference<>(null);

        TextField textField = new TextField();
        textField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                res.set(textField.getText());
                // Close the alert
                ((Stage) textField.getScene().getWindow()).close();
            }
        });

        Alert alert = new Alert(AlertType.NONE);
        alert.setTitle(t.getQuestion());
        alert.getDialogPane().setContent(textField);
        alert.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        // Wait for the alert to close
        alert.showAndWait();

        return res.get();
    }

    public static void messager(Message m){
        // Display a simple alert dialog
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(m.getAuthor());
        alert.setHeaderText(null);
        alert.setContentText(m.getMessage());

        // Wait for the alert to close
        alert.showAndWait();

    }

    public static String Menu(Menue M) {
        AtomicReference<String> res = new AtomicReference<>(null);

        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(M.getAll());
        listView.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String selectedItem = listView.getSelectionModel().getSelectedItem();
                res.set(selectedItem);
                // Close the stage after selecting an item
                ((Stage) listView.getScene().getWindow()).close();
            }
        });
        StackPane root = new StackPane();
        root.getChildren().add(listView);
        Scene scene = new Scene(root, 400, 300);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setOnCloseRequest((WindowEvent event) -> {
            res.set("Window closed by user");
            System.out.println(res.get());
        });
        stage.showAndWait();

        return res.get();
    }
    private static String handleKeyPress(KeyEvent event, ListView<String> listView) {

        if (event.getCode() == KeyCode.ENTER) {
            String selectedItem = listView.getSelectionModel().getSelectedItem();
            return selectedItem;
        }
        return "";
    }


}




