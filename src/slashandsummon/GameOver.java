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
public class GameOver extends Section {
    public Sprite quit;
    public Sprite playAgain;
    public Sprite gameOver;
    public Sprite selectSword;
    public GameOver(int screenWidth, int screenHeight, Game game) throws IOException {
        super(screenWidth, screenHeight, game);
        sprites = new Sprite[4];
        //public Sprite(int width, int height, int xPosition, int yPosition, String filename,this)
        gameOver=new Sprite(screenWidth, screenHeight, 0,0, "gameover.png", this);
        selectSword=new Sprite(screenHeight/2, screenHeight/2, (screenWidth/2)-(screenHeight/4), screenHeight/2-screenHeight/36, "invertedsword.png", this);
        quit=new Sprite(screenHeight/4, screenHeight/4, (screenWidth/2)-(screenHeight/8), 3*screenHeight/4, "quit.png", this);
        playAgain=new Sprite(screenHeight/4, screenHeight/4, (screenWidth/2)-(screenHeight/8), screenHeight/2+screenWidth/16, "play again.png", this);
        sprites[0]=gameOver;
        sprites[1]=selectSword;
        sprites[2]=quit;
        sprites[3]=playAgain;
    }
    @Override
    public void render(Graphics g) {
        for (Sprite sprite : sprites) {
            sprite.render(g);
        }

    }
    
    @Override
    public void input(String input) throws FileNotFoundException {
        sprites[1].input(input);
        
    }
    public void mouseInput(String input) {
        
    }
    
}
