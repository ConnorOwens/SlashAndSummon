/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slashandsummon;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Aragorn
 */
public class Battle extends Section{
    Sprite character;
    Sprite fireball;
    Sprite fireballCard;
    Sprite skull;
    Sprite skullCard;
    Sprite grave;
    Sprite ground;
    double blockSize;
    public Battle(int screenWidth, int screenHeight, Game game) throws IOException {
        super(screenWidth, screenHeight, game);
        blockSize =(double)((double)screenWidth)/16.0;
        sprites=new Sprite[7];
        //public Sprite(int width, int height, int xPosition, int yPosition, String filename, this)
        String[] walkingAnimation = {"standing_sprite.png", "Walking00.png", "Walking01.png", "Walking02.png", "Walking03.png", "Walking04.png", "Walking05.png", "Walking06.png", "Walking07.png", "Walking08.png", "Walking09.png"};
        String[] attackAnimation = {"attack_0.png", "attack_1.png","attack_2.png","attack_3.png","attack_4.png","attack_5.png","attack_6.png"};
        String[] jumpAnimation = {"jump.png"};
        LinkedImageList walking = new LinkedImageList(walkingAnimation);
        LinkedImageList attack = new LinkedImageList(attackAnimation);
        LinkedImageList jump = new LinkedImageList(jumpAnimation);
        LinkedImageList[] stickAnimations = {walking, attack, jump};
        character=new Sprite((int)((580.0/1120.0)*(3.0*blockSize))+1, (int)(3.0*blockSize), 0, screenHeight-(int)(4.0*blockSize), stickAnimations, this, false);
        String[] fireballAnimation = {"fireball_0.png", "fireball_1.png", "fireball_2.png"};
        LinkedImageList fire = new LinkedImageList(fireballAnimation);
        LinkedImageList[] fireAnimations = {fire};
        fireball= new Sprite((int)(3.0*blockSize),(int)(3.0*blockSize), 0, 0, fireAnimations,this,false);
        fireballCard=new Sprite((int)((418.0/572.0)*3.0*blockSize),(int)(3.0*blockSize), (int)((double)screenWidth/2.0)-(int)(((418.0/572.0)*3.0*blockSize)), 0, "fireballcard.png",this);
        String[] skullAnime = {"skull_00.png", "skull_01.png", "skull_02.png", "skull_03.png", "skull_04.png", "skull_05.png","skull_06.png","skull_07.png","skull_08.png","skull_09.png","skull_10.png", "skull_11.png", "skull_12.png","skull_13.png","skull_14.png","skull_15.png","skull_16.png","skull_17.png","skull_18.png","skull_19.png","skull_20.png"};
        String[] skullAnime2 = {"skullbreak_1.png","skullbreak_2.png","skullbreak_3.png","skullbreak_4.png","skullbreak_5.png","skullbreak_6.png","skullbreak_7.png"};
        LinkedImageList skullYeezy = new LinkedImageList(skullAnime);
        LinkedImageList skullYeezy2 =new LinkedImageList(skullAnime2);
        LinkedImageList[] skullAnimations = {skullYeezy, skullYeezy2};
        skull= new Sprite((int)(blockSize),(int)(blockSize), -(int)(blockSize), -(int)(blockSize), skullAnimations, this, false);
        skullCard=new Sprite((int)((418.0/572.0)*3.0*blockSize),(int)(3.0*blockSize), (int)((double)screenWidth/2.0), 0, "gravecard.png",this);
        grave=new Sprite((int)((462.0/407.0)*2.0*blockSize),(int)(2.0*blockSize), (int)((double)screenWidth/2.0), character.height+character.yPosition, "grave.png",this);
        ground=new Sprite(screenWidth,screenWidth, 0, character.yPosition+character.height, "ground.png",this);
        sprites[0] = fireballCard;
        sprites[2] = fireball;
        sprites[1] = character;
        sprites[5] = skull;
        sprites[4] = skullCard;
        sprites[3] = grave;
        sprites[6] = ground;
    }
    @Override
    public void render(Graphics g) {
        for (Sprite sprite : sprites) {
            sprite.render(g);
        }
    }
    public void update() {
        sprites[1].update();
        if (sprites[2].cast) {
            //(x,y,width,height)
            HitBox rect1= new HitBox(sprites[1].xPosition+(sprites[1].width/4),sprites[1].yPosition+(int)((double)sprites[1].width/2.0),sprites[1].width-(sprites[1].width/4),(int)(4*((double)sprites[1].height/5)));
            HitBox rect2= new HitBox(sprites[2].xPosition+(int)((double)sprites[2].width*.15),sprites[2].yPosition+sprites[2].height/2,(int)((double)sprites[2].width*.7),sprites[2].height/2);
        /*
            if (sprites[1].xPosition < sprites[2].xPosition + sprites[2].width &&
                    sprites[1].xPosition + sprites[1].width > sprites[2].xPosition && sprites[1].yPosition < sprites[2].yPosition + sprites[2].height && sprites[1].height + sprites[1].yPosition > sprites[2].yPosition) {
            */
            
            if (rect1.collidesWith(rect2)) {
                game.currentState="Game over";
            }
        }
        if (sprites[5].spawnSkulls) {
            HitBox rect1= new HitBox(sprites[5].xPosition,sprites[5].yPosition,sprites[5].width,sprites[5].height);
            HitBox rect2= new HitBox(sprites[1].xPosition+(sprites[1].width/4),sprites[1].yPosition+(int)((double)sprites[1].width/2.0),sprites[1].width-(sprites[1].width/4),(int)(4*((double)sprites[1].height/5)));
            if (rect1.collidesWith(rect2)) {
                game.currentState="Game over";
            }
        }
        if (sprites[1].attacking && !sprites[5].skullBroken) {
            if (sprites[1].facingRight) {
                HitBox rect1= new HitBox(sprites[5].xPosition,sprites[5].yPosition,sprites[5].width,sprites[5].height);
                HitBox rect2= new HitBox(sprites[1].xPosition+sprites[1].width-(sprites[5].width/2),sprites[1].yPosition+(int)((double)sprites[1].width/5.0),sprites[1].width,(int)(4*((double)sprites[1].height/5)));
                if (rect1.collidesWith(rect2)) {
                    sprites[5].skullBroken=true;
                }
            } else {
                HitBox rect1= new HitBox(sprites[5].xPosition,sprites[5].yPosition,sprites[5].width,sprites[5].height);
                HitBox rect2= new HitBox(sprites[1].xPosition-sprites[1].width+(sprites[5].width/2),sprites[1].yPosition+(int)((double)sprites[1].width/5.0),sprites[1].width,(int)(4*((double)sprites[1].height/5)));
                if (rect1.collidesWith(rect2)) {
                sprites[5].skullBroken=true;
            }
            }
            
        }
    }
    @Override
    public void input(String input) {
        if (input.equals("J") && !sprites[1].attacking && !sprites[1].jumping) {
            sprites[1].attacking=true;
            sprites[1].currentFrame= 11;
        }
        if (input.equals("Space") && !sprites[1].jumping && !sprites[1].attacking) {
            sprites[1].jumping=true;
            sprites[1].jumpButtonPressed=true;
            sprites[1].yVelocity=30;
        }
    }

    public void mouseInput(String input) {
        Scanner search=new Scanner(input);
        String mouseAction=search.next();
        int x = search.nextInt();
        int y = search.nextInt();
        if (mouseAction.equals("pressed")) {
            if (x<(screenWidth/2) && x>(screenWidth/2)-(sprites[0].width))
                if (y<sprites[0].height && y>0) {
                    sprites[0].cardGrabbed=true;
                    sprites[0].grabbedX=x-sprites[0].xPosition;
                    sprites[0].grabbedY=y-sprites[0].yPosition;
                }
            if (x>(screenWidth/2) && x<(screenWidth/2)+(sprites[0].width))
                if (y<sprites[0].height && y>0) {
                    sprites[4].cardGrabbed=true;
                    sprites[4].grabbedX=x-sprites[4].xPosition;
                    sprites[4].grabbedY=y-sprites[4].yPosition;
                }
        }
        if (mouseAction.equals("dragged")) {
            if (sprites[0].cardGrabbed==true) {
                sprites[0].xPosition=x-(sprites[0].grabbedX);
                sprites[0].yPosition=y-(sprites[0].grabbedY);
            }
            if (sprites[4].cardGrabbed==true) {
                sprites[4].xPosition=x-(sprites[4].grabbedX);
                sprites[4].yPosition=y-(sprites[4].grabbedY);
            }
        }
        if (mouseAction.equals("released")) {
            if (sprites[0].cardGrabbed==true) {
                if (sprites[2].cast==false) {
                    sprites[2].cast=true;
                    sprites[2].castX=sprites[0].xPosition+sprites[0].width/2-sprites[2].width/2;
                    sprites[2].xPosition=sprites[2].castX;
                }
                
                sprites[0].cardGrabbed=false;
                sprites[0].xPosition=(int)((double)screenWidth/2.0)-(int)(((418.0/572.0)*3.0*blockSize));
                sprites[0].yPosition=0;
            }
            if (sprites[4].cardGrabbed==true) {
                if (sprites[5].cast==false) {
                    sprites[3].xPosition=x-(sprites[4].grabbedX);
                    sprites[3].yPosition=sprites[6].yPosition;
                    sprites[5].cast=true;
                    sprites[5].xPosition=x-(sprites[4].grabbedX);
                    sprites[5].castX=sprites[4].xPosition+sprites[4].width/2-sprites[3].width/2;
                    sprites[5].xPosition=sprites[2].castX;
                }
                
                sprites[4].cardGrabbed=false;
                sprites[4].xPosition=(int)((double)screenWidth/2.0);
                sprites[4].yPosition=0;
            }
        }
    }

    public void resetGame() {
        sprites[0].yPosition=0;
        sprites[0].xPosition=(screenWidth/2)-((int)((418.0/572.0)*(3.0*blockSize)));
        sprites[0].cardGrabbed=false;
        sprites[4].cardGrabbed=false;
        sprites[4].yPosition=0;
        sprites[4].xPosition=(screenWidth/2);
        sprites[2].cast=false;
        sprites[2].yPosition=-sprites[2].height;
        sprites[2].fireballY=-sprites[2].height;
        sprites[0].currentFrame=0;
        sprites[2].currentFrame=0;
        sprites[1].facingRight=true;
        sprites[1].yPosition=screenHeight-(int)(4.0*blockSize);
        sprites[1].xPosition=0;
        sprites[1].jumping=false;
        sprites[1].attacking=false;
        sprites[0].grabbedX=0;
        sprites[0].grabbedY=0;
        sprites[4].currentFrame=0;
        sprites[4].grabbedX=0;
        sprites[4].grabbedY=0;
        sprites[3].yPosition=sprites[6].yPosition;
        sprites[5].spawnSkulls=false;
        sprites[3].cast=false;
        sprites[5].cast=false;
        sprites[5].yPosition=sprites[6].yPosition;
        sprites[5].skullCount=0;
        sprites[3].returnGrave=false;
    }
}
