/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aislerush;

import static aislerush.AisleRush.creds;
import static aislerush.AisleRush.fonts;
import static aislerush.AisleRush.high;
import static aislerush.AisleRush.hover;
import static aislerush.AisleRush.load;
import static aislerush.AisleRush.main;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author 0001046382
 */
public class MenuItem extends StackPane {
        public MenuItem(String name){
            
            Rectangle bg = new Rectangle(400, 48);
            LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[] {
                new Stop(0, Color.YELLOW),
                new Stop(0.4, Color.GOLD),
                new Stop(0.8, Color.GOLDENROD)
            });
            bg.setFill(gradient);
            bg.setOpacity(0.6);
            bg.setVisible(false);
            bg.setEffect(new DropShadow(5, 0, 5, Color.BLACK));
            
            Text text = new Text("      " + name);
            text.setFill(Color.BLACK);
            text.setFont(fonts);
            if (name.equals("Exit"))
            {
                setOnMouseClicked(event -> 
                {
                    System.exit(0);
                });
            }
            if (name.equals("Back"))
            {
                setOnMouseClicked(event -> 
                {
                    main.show();
                    load.hide();
                    high.hide();
                    creds.hide();
                });
            }
            if (name.equals("Load Game"))
            {    
                setOnMouseClicked(event -> 
                {
                    load.show();
                    main.hide();
                });
            }
            if (name.equals("New Game"))
            {    
                setOnMouseClicked(event -> 
                {
                    load.show();
                    main.hide();
                });
            }
            if (name.equals("Highscores"))
            {    
                setOnMouseClicked(event -> 
                {
                    high.show();
                    main.hide();
                });
            }
            if (name.equals("Credits"))
            {    
                setOnMouseClicked(event -> 
                {
                    creds.show();
                    main.hide();
                });
            }
            
            setAlignment(Pos.BOTTOM_LEFT);
            getChildren().addAll(bg, text);
            
            
            setOnMouseEntered(event -> {
                bg.setVisible(true);
                hover = true;
                text.setFill(Color.PURPLE);
            });
            
            setOnMouseExited(event -> {
                bg.setVisible(false);
                hover = false;
                text.setFill(Color.BLACK);
            });
            
            setOnMousePressed(event -> {
                bg.setFill(Color.WHITE);
                text.setFill(Color.BLUE);
            });
            
            setOnMouseReleased(event -> {
                bg.setFill(gradient);
                if(hover)
                text.setFill(Color.PURPLE);
                else
                text.setFill(Color.BLACK);    
            });
        }
    }
