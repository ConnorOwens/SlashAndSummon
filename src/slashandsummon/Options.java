/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slashandsummon;

import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aragorn
 */
public class Options extends Section{
    public Sprite seventwenty;
    public Sprite teneighty;
    public Sprite fullscreen;
    public Sprite selectSword;
    public Sprite close;
    public Options(int screenWidth, int screenHeight, Game game) throws IOException {
        super(screenWidth, screenHeight, game);
        String[] seventwentyarray={"720.png", "select 720.png"};
        String[] teneightyarray={"1080.png", "select 1080.png"};
        String[] fullscreenarray={"fullscreen.png", "select fullscreen.png"};
        boolean seventwozero;
        boolean onezeroeightzero;
        if (screenHeight==720 && screenWidth==1280) {
            seventwozero=true;
            onezeroeightzero=false;
        } else if (screenHeight==1080) {
            onezeroeightzero=true;
            seventwozero=false;
        } else {
            onezeroeightzero=false;
            seventwozero=false;
        }
        
        Scanner input = new Scanner(new File(SlashAndSummon.longTing()+"settings.txt"));
        boolean fullscreenQuestionMark=false;
        if (input.hasNext()) {
            input.nextInt();
            input.nextInt();
            fullscreenQuestionMark = input.nextBoolean();
        }
        seventwenty= new Sprite(screenHeight/2,screenHeight/2,(screenWidth/2)-(screenHeight/4),-(screenHeight/8),seventwentyarray,this,seventwozero);
        teneighty= new Sprite(screenHeight/2,screenHeight/2,(screenWidth/2)-(screenHeight/4),(screenHeight)/5-(screenHeight/8),teneightyarray,this,onezeroeightzero);
        fullscreen= new Sprite(screenHeight/2,screenHeight/2,(screenWidth/2)-(screenHeight/4),(2*screenHeight)/5-(screenHeight/8),fullscreenarray,this,fullscreenQuestionMark);
        selectSword = new Sprite((int)((screenHeight/2)*(1.49)),(int)((screenHeight/2)*(1.49)),(screenWidth/2)-(int)((1.49)*(screenHeight/4)),(int)((1.49)*-screenHeight/6),"swordselect.png",this);
        close = new Sprite(screenHeight/2,screenHeight/2,(screenWidth/2)-(screenHeight/4),(3*screenHeight)/5-(screenHeight/8),"close_apply.png",this);
        sprites= new Sprite[5];
        sprites[0] =selectSword;
        sprites[1] = seventwenty;
        sprites[2]= teneighty;
        sprites[3]= fullscreen;
        sprites[4]= close;
    }
    @Override
    public void render(Graphics g) {
        for (Sprite sprite : sprites) {
            sprite.render(g);
        }

    }
    @Override
    public void input(String input) throws FileNotFoundException {
        sprites[0].input(input);

    }
}
