/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slashandsummon;

import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aragorn
 */
public class TitleScreen extends Section{
    public Sprite logo;
    public Sprite start;
    public Sprite options;
    public Sprite selectSword;
    public TitleScreen(int screenWidth, int screenHeight, Game game) throws IOException {
        super(screenWidth, screenHeight, game);
        sprites= new Sprite[4];
        //public Sprite(int width, int height, int xPosition, int yPosition, String filename)
        Sprite logo = new Sprite(screenHeight/2,screenHeight/2,(screenWidth/2)-(screenHeight/4),0,"logo.png",this);
        Sprite start = new Sprite(screenHeight/4,screenHeight/4,(screenWidth/2)-(screenHeight/8),screenHeight/2,"start.png",this);
        Sprite options = new Sprite(screenHeight/4,screenHeight/4,(screenWidth/2)-(screenHeight/8),25*(screenHeight/36),"options.png",this);
        Sprite selectSword = new Sprite((int)((screenHeight/4)*(1.49)),(int)((screenHeight/4)*(1.49)),(screenWidth/2)-(int)((1.49)*(screenHeight/8)),(screenHeight/2)-(int)((.49)*(screenHeight/8)),"swordselect.png",this);
        sprites[0]=logo;
        sprites[1]=selectSword;
        sprites[2]=start;
        sprites[3]=options;

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

}
