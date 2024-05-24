package org.example;



/*
public class Main {
    public static void main(String[] args){

    }
}*/
// 1.04
// 2.4
import Game.Thing.*;
import Game.*;

import javafx.scene.control.Control;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.FontPosture;
import org.example.Controller;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;

public class Game extends Application {

    // Taille de la grille et des carreaux
    Controller controller = new Controller();
    private final int gridSize = 10;
    private final double canvasSize = 500;
    private final double tileSize = canvasSize / gridSize;
    private Text pointsDeViesText;
    private ListView<String> listView;
    Scene titleScene;

    @Override
    public void start(Stage primaryStage) {
        // Création de la scène de titre
        Text title = new Text("LOST CAT");
        title.setFill(Color.WHITE);
        title.setFont(new Font("Arial", 50));

        Button startButton = new Button("Démarrer");
        startButton.setOnAction(e -> primaryStage.setScene(createGameScene(primaryStage)));

        VBox titleLayout = new VBox(20, title, startButton);
        titleLayout.setStyle("-fx-background-color: black;");
        titleLayout.setPrefSize(700, 500);
        titleLayout.setAlignment(javafx.geometry.Pos.CENTER);

        titleScene = new Scene(titleLayout);

        // Configurer le stage pour afficher la scène de titre
        primaryStage.setTitle("Grille avec Texte Déplaçable et Barre Latérale");
        primaryStage.setScene(titleScene);
        primaryStage.show();
    }

    // Méthode pour créer la scène du jeu
    private Scene createGameScene(Stage primaryStage) {

        controller.build();

        // Création du Canvas pour la grille
        Canvas canvas = new Canvas(canvasSize, canvasSize);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Dessiner la grille et le texte initial
        drawGrid(gc);
        drawText(gc);

        // Création des éléments de la barre latérale
        pointsDeViesText = new Text("Points de vies:" + Controller.player.getLife());
        pointsDeViesText.setFill(Color.GREEN);
        pointsDeViesText.setFont(new Font("Arial", 20));

        Text inventaireText = new Text("Inventaire:");
        inventaireText.setFill(Color.WHITE);
        inventaireText.setFont(new Font("Arial", 20));

        listView = new ListView<>();
        listView.getItems().addAll(Controller.player.StrInventory());
        listView.setOnKeyPressed(event -> KeyPress(event, listView));

        // Création de la VBox pour la barre latérale
        VBox sidebar = new VBox(10, pointsDeViesText, inventaireText, listView);
        sidebar.setPrefWidth(200);

        // Création de la HBox pour contenir la grille et la barre latérale
        HBox layout = new HBox(10, canvas, sidebar);
        layout.setStyle("-fx-background-color: black;");

        // Création de la scène
        Scene gameScene = new Scene(layout, canvasSize + 200, canvasSize, Color.BLACK);

        // Gestionnaire d'événements pour les touches du clavier
        gameScene.setOnKeyPressed(event -> {
            int x =  Controller.player.getPosition().getX();
            int y =  Controller.player.getPosition().getY();
            switch (event.getCode()) {
                case Z:
                    y--;
                    Controller.player.getWorld().move(controller.player,new Coords(x,y));
                    break;
                case Q:
                    x--;
                    Controller.player.getWorld().move(controller.player,new Coords(x,y));
                    break;
                case S:
                    y++;
                    Controller.player.getWorld().move(controller.player,new Coords(x,y));
                    break;
                case D:
                    x++;
                    Controller.player.getWorld().move(controller.player,new Coords(x,y));
                    break;
                default:
                    break;
            }
            if (Controller.player.getWorld().getStatus()==1){
                // victoire
                primaryStage.setScene( createVictoryScene(primaryStage));
            } else if (Controller.player.getWorld().getStatus()==2) {
                // defeat
                primaryStage.setScene( createDefeatScene(primaryStage));
            }
            // Redessiner la grille et le texte après le déplacement
            drawGrid(gc);
            drawText(gc);
            updateSidebar();
         });

        return gameScene;
    }
    private Scene createDefeatScene(Stage primaryStage) {
        Text defeatText = new Text("Défaite");
        defeatText.setFill(Color.RED);
        defeatText.setFont(new Font("Arial", 50));

        Text subText = new Text(Controller.player.getWorld().getMessage());
        subText.setFill(Color.GRAY);
        subText.setFont(Font.font("Arial", FontPosture.ITALIC, 20));

        VBox defeatLayout = new VBox(10, defeatText, subText);
        defeatLayout.setStyle("-fx-background-color: black;");
        defeatLayout.setPrefSize(700, 500);
        defeatLayout.setAlignment(javafx.geometry.Pos.CENTER);

        Scene defeatScene = new Scene(defeatLayout);

        // Gestionnaire d'événements pour les touches du clavier
        defeatScene.setOnKeyPressed(event -> {
                primaryStage.setScene(titleScene);
        });

        return defeatScene;
    }
    private Scene createVictoryScene(Stage primaryStage) {
        Text defeatText = new Text("VICTOIRE");
        defeatText.setFill(Color.GREEN);
        defeatText.setFont(new Font("Arial", 50));

        Text subText = new Text(Controller.player.getWorld().getMessage());
        subText.setFill(Color.GRAY);
        subText.setFont(Font.font("Arial", FontPosture.ITALIC, 20));

        VBox defeatLayout = new VBox(10, defeatText, subText);
        defeatLayout.setStyle("-fx-background-color: black;");
        defeatLayout.setPrefSize(700, 500);
        defeatLayout.setAlignment(javafx.geometry.Pos.CENTER);

        Scene defeatScene = new Scene(defeatLayout);

        // Gestionnaire d'événements pour les touches du clavier
        defeatScene.setOnKeyPressed(event -> {
                primaryStage.setScene(titleScene);
        });

        return defeatScene;
    }

    // Méthode pour dessiner la grille
    private void drawGrid(GraphicsContext gc) {
        // Couleur de fond du Canvas
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvasSize, canvasSize);

        // Dessiner la grille
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(1);

        for (int i = 0; i <= gridSize; i++) {
            double pos = i * tileSize;
            gc.strokeLine(pos, 0, pos, canvasSize);  // Lignes verticales
            gc.strokeLine(0, pos, canvasSize, pos);  // Lignes horizontales
        }
    }

    // Méthode pour dessiner le texte
    private void drawText(GraphicsContext gc) {
        Iterator<Thing> thingIterator = Controller.player.getWorld().getContent().iterator();
        Thing i;
        while (thingIterator.hasNext()) {
            i = thingIterator.next();
            String text = i.Text();
            gc.setFill(Color.WHITE);
            gc.setFont(new Font("Arial", 20));

            // Calculer la position du texte pour le centrer dans le carreau
            double textX = i.getPosition().getX() * tileSize + (tileSize / 1.04) - tileSize;
            double textY = i.getPosition().getY() * tileSize + (tileSize / 2.4);
            gc.fillText(text, textX, textY);
        }
    }


    private void updateSidebar(){
            // Mettre à jour les points de vies (pour l'exemple, on garde la même valeur)
            pointsDeViesText.setText("Points de vies:" + controller.player.getLife());

            // Mettre à jour l'inventaire si nécessaire (pour l'exemple, on garde les mêmes éléments)
            listView.getItems().setAll(controller.player.StrInventory()); // Si vous avez des changements dynamiques
        }
    private void KeyPress(KeyEvent event, ListView<String> listView) {
        if (event.getCode() == KeyCode.ENTER) {
            String selectedItem = listView.getSelectionModel().getSelectedItem();
            controller.player.getIt(selectedItem.split(" ")[0]).use(Controller.player);
            updateSidebar();
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}