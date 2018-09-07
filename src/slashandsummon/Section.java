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
public class Section {
    public Sprite[] sprites;
    int screenWidth;
    int screenHeight;
    Game game;
    public Section(int screenWidth, int screenHeight, Game game) throws IOException {
        this.game=game;
        this.screenHeight=screenHeight;
        this.screenWidth=screenWidth;
        
        //public Sprite(int width, int height, int xPosition, int yPosition, String filename

    }
    public void render(Graphics g) {
        for (Sprite sprite : sprites) {
            sprite.render(g);
        }
    }
    public void input(String input) throws FileNotFoundException {
        sprites[1].input(input);

    }
}
