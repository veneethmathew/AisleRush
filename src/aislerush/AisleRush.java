/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aislerush;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.input.KeyCode;
import javafx.scene.effect.DropShadow;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 *
 * @author Manoj
 */
public class AisleRush extends Application {
    
    public static Font font;
    public static Font fonts;
    public static MenuBox main, load, high, creds;
    public static Pane root = new Pane();
    public static boolean hover = false;
    
    public Parent createContent(){
        Pane root = new Pane();
        root.setPrefSize(1200, 600);
        
        try (InputStream is = Files.newInputStream(Paths.get("res/images/bg-1.gif"));
                InputStream fontStream = Files.newInputStream(Paths.get("res/fonts/SketchRockwell-Bold.ttf"));
                InputStream fontsStream = Files.newInputStream(Paths.get("res/fonts/SketchRockwell-Bold.ttf"))) {
            ImageView img = new ImageView(new Image(is));
            img.setFitWidth(1200);
            img.setFitHeight(600);
            
            root.getChildren().add(img);
            
            font = Font.loadFont(fontStream, 30);
            fonts = Font.loadFont(fontsStream, 40);
        }
        catch (IOException e) {
            System.out.println("Couldn't load image or font");
        }
        
        main = new MenuBox("MAIN MENU",
                new MenuItem("New Game"),
                new MenuItem("Load Game"),
                new MenuItem("Highscores"),
                new MenuItem("Credits"),
                new MenuItem("Exit"));
        
        load = new MenuBox("LOAD MENU",
                new MenuItem("Slot 1"),
                new MenuItem("Slot 2"),
                new MenuItem("Slot 3"),
                new MenuItem("Slot 4"),
                new MenuItem("Back"));
        
        high = new MenuBox("HIGHSCORES",
                new MenuItem("Back"));
        
        creds = new MenuBox("CREDITS",
                new MenuItem("Contracted By"),
                new MenuItem("COGNITIVE"),
                new MenuItem("THOUGHT"),
                new MenuItem("MEDIA"),
                new MenuItem(""),
                new MenuItem("Created By"),
                new MenuItem("AI STUDIOS"), 
                new MenuItem(""),
                new MenuItem("Back"));
        root.getChildren().add(creds);
        root.getChildren().add(high);
        root.getChildren().add(load);
        root.getChildren().add(main);
        
        return root;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        scene.setOnKeyPressed(event -> {
           if (event.getCode() == KeyCode.SPACE) {
               if (main.isVisible()){
                   main.hide();
                   load.hide();
                   high.hide();
                   creds.hide();
               }
               else {
                   main.show();
                   load.hide();
                   high.hide();
                   creds.hide();
               }
           } 
        });
        load.hide();
        high.hide();
        creds.hide();
        primaryStage.setTitle("Aisle Rush");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static class MenuBox extends StackPane{
        public MenuBox(String title, MenuItem... items){
            Rectangle bg = new Rectangle(400,600);
            
        try (InputStream is = Files.newInputStream(Paths.get("res/images/note_pad.png"));) {
            ImagePattern img = new ImagePattern(new Image(is));
            bg.setFill(img);
        }
        catch (IOException e) {
            System.out.println("Couldn't load image or font");
        }
            bg.setOpacity(1.0);
            
            DropShadow shadow = new DropShadow(7, 5, 0, Color.BLACK);
            shadow.setSpread(0.8);
            
            bg.setEffect(shadow);
            
            Text text = new Text("        " + title);
            text.setFont(font);
            text.setFill(Color.BLACK);

            
            VBox vbox = new VBox();
            vbox.setAlignment(Pos.TOP_LEFT);
            vbox.setPadding(new Insets(60, 0, 0, 0));
            vbox.getChildren().addAll(text);
            vbox.getChildren().addAll(items);
            
            setAlignment(Pos.TOP_RIGHT);
            getChildren().addAll(bg, vbox);
        }
        
        public void show(){
            setVisible(true);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), this);
            tt.setToX(0);
            tt.play();
        }
        
        public void hide(){
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), this);
            tt.setToX(-400);
            tt.setOnFinished(event -> setVisible(false));
            tt.play();
        }
        
    }
    
    public static void main(String[] args) {
        try 
        {
            Thread.sleep(2000);
        } 
        catch (Exception e) 
        {
        }
        launch(args);        
    }
    
}
