/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slashandsummon;

import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Aragorn
 */
public class Game {
    String currentState;
    TitleScreen start;
    Options options;
    Battle battle;
    GameOver gameOver;
    int screenWidth;
    int screenHeight;
    public MyJPanel frame;
    public Game(int screenWidth, int screenHeight) throws IOException {
        this.screenHeight=screenHeight;
        this.screenWidth=screenWidth;
        currentState = "Title Screen";
        start =new TitleScreen(screenWidth, screenHeight, this);
        options=new Options(screenWidth, screenHeight, this);
        battle = new Battle(screenWidth, screenHeight, this);
        gameOver = new GameOver(screenWidth, screenHeight, this);
        
    }
    public void render(Graphics g) {
        if (currentState.equals("Title Screen")) {
            start.render(g);
        } else if (currentState.equals("Gameplay")) {
            battle.render(g);
        } else if (currentState.equals("Options")) {
            options.render(g);
        } else if (currentState.equals("Game over")) {
            gameOver.render(g);
        }
    }
    public void input(String input) throws FileNotFoundException {
        if (currentState.equals("Title Screen")) {
            start.input(input);
        } else if (currentState.equals("Gameplay")) {
            battle.input(input);
        } else if (currentState.equals("Options")) {
            options.input(input);
        } else if (currentState.equals("Game over")) {
            gameOver.input(input);
        }
    }

    public void update() {
        battle.update();
    }

    public void mouseInput(String input) {
        if (currentState.equals("Title Screen")) {
            
        } else if (currentState.equals("Gameplay")) {
            battle.mouseInput(input);
        } else if (currentState.equals("Options")) {
            
        } else if (currentState.equals("Game over")) {
            gameOver.mouseInput(input);
        }
    }
}
