/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slashandsummon;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import javax.imageio.ImageIO;

/**
 *
 * @author Aragorn
 */
public class Sprite {
    public int width;
    public int height;
    public String filename;
    public String[] filenames;
    public int xPosition;
    public int yPosition;
    public int currentFrame=0;
    private BufferedImage image;
    public int originalY;
    public Section start;
    public boolean selected;
    private BufferedImage[] images;
    public boolean facingRight=true;
    public boolean cast;
    public boolean cardGrabbed=false;
    public int grabbedX;
    public int grabbedY;
    public int castX;
    public int fireballY;
    boolean attacking=false;
    boolean slowdown=false;
    boolean jumping =false;
    public int yVelocity;
    public boolean jumpButtonPressed=false;
    public int graveY;
    public boolean spawnSkulls=false;
    public LinkedImageList[] animations;
    public boolean skullBroken =false;
    public int skullCount=0;
    public boolean returnGrave;
    public int waitFrames=0;
    public Sprite(int width, int height, int xPosition, int yPosition, String filename, Section start) throws IOException {
        this.start=start;
        this.width=width;
        this.height=height;
        this.xPosition=xPosition;
        this.yPosition=yPosition;
        image = ImageIO.read(new File(filename));
        this.filename=filename;
        originalY=yPosition;
    }
    public Sprite(int width, int height, int xPosition, int yPosition, String[] filename, Section start, boolean selected) throws IOException {
        this.start=start;
        this.width=width;
        this.height=height;
        this.xPosition=xPosition;
        this.yPosition=yPosition;
        images = new BufferedImage[filename.length];
        filenames = new String[filename.length];
        for (int i=0; i<filename.length;i++) {
            images[i] = ImageIO.read(new File(filename[i]));
            filenames[i]=filename[i];
        }
        originalY=yPosition;
        this.selected=selected;
        if (filenames[0].equals("fireball_0.txt")) {
            fireballY=-height;
            cast=false;
        }
        
    }
    public Sprite(int width, int height, int xPosition, int yPosition, LinkedImageList[] animations, Section start, boolean selected) throws IOException {
        this.start=start;
        this.width=width;
        this.height=height;
        this.xPosition=xPosition;
        this.yPosition=yPosition;
        originalY=yPosition;
        this.selected=selected;
        this.animations=animations;
        if (animations[0].front.filename.equals("fireball_0.txt")) {
            fireballY=-height;
            cast=false;
        }
        
    }
    public void render(Graphics g) {
        if (images == null && image != null) {
            if (filename.equals("grave.png")) {
                if (start.sprites[5].cast) {
                    if (!returnGrave && yPosition>start.sprites[6].yPosition-(height)) {
                        yPosition-=(int)(.5*((double)start.screenWidth/16.0));
                    } else if (start.sprites[5].spawnSkulls==false) {
                        if (waitFrames<30 && start.sprites[5].skullCount==0) {
                            waitFrames++;
                        } else {
                            waitFrames=0;
                            if (!returnGrave) {
                                start.sprites[5].spawnSkulls=true;
                            }
                            start.sprites[5].xPosition=xPosition+(width/2)-(start.sprites[5].width/2);
                            start.sprites[5].yPosition=start.sprites[1].height+start.sprites[1].yPosition;
                        }
                    }
                    if (returnGrave) {
                        if (yPosition<start.sprites[6].yPosition) { 
                            yPosition+=(int)(.5*((double)start.screenWidth/16.0));
                        } else {
                            returnGrave=false;
                            start.sprites[5].cast=false;
                            start.sprites[5].spawnSkulls=false;
                        }
                    }
                    g.drawImage(image, xPosition, yPosition, width, height, null);
                }
            } else if (image!=null) {
                g.drawImage(image,xPosition,yPosition,width,height,null);
            }
        } else if (Options.class.isInstance(start)){
            if (selected==true) {
                g.drawImage(images[1],xPosition,yPosition,width,height,null);
            } else {
                g.drawImage(images[0],xPosition,yPosition,width,height,null);
            }
        } else if (animations[0].front.filename.equals("skull_00.png")) {
                if (skullCount == 3) {
                    skullCount=0;
                    spawnSkulls=false;
                    start.sprites[3].returnGrave=true;
                }
                if (spawnSkulls) {
                    
                    if (yPosition>start.sprites[6].yPosition-(2*height)) {
                        yPosition-=(int)(.1*((double)start.screenWidth/16.0));
                    } else if (!skullBroken){
                        if (xPosition>start.sprites[1].xPosition) {
                            xPosition-=(int)(.1*((double)start.screenWidth/16.0));
                        } else {
                            xPosition+=(int)(.1*((double)start.screenWidth/16.0));
                        }
                    }
                    
                }
                if (skullBroken) {
                    g.drawImage(animations[1].tempo.data, xPosition, yPosition, width, height, null);
                    animations[1].tempo=animations[1].tempo.next;
                } else  {
                    g.drawImage(animations[0].tempo.data, xPosition, yPosition, width, height, null);
                    animations[0].tempo=animations[0].tempo.next;
                }
                if (skullBroken && animations[1].tempo==animations[1].front) {
                    spawnSkulls=false;
                    skullCount++;
                    skullBroken=false;
                }
        } else if (animations[0].front.filename.equals("standing_sprite.png")){
            if (jumping == true) {
                
                if (facingRight) {
                    g.drawImage(animations[2].front.data, xPosition, yPosition,width, height, null);
                } else {
                    g.drawImage(animations[2].front.data, xPosition, yPosition, xPosition+width, yPosition+height, 580, 0, 0, 1120,null);
                }
                
            }else if (attacking==true) {
                /*if (slowdown ==true) {
                    currentFrame++;
                    slowdown=false;
                } else {
                    slowdown=true;
                }*/
                
                if (facingRight) {
                    g.drawImage(animations[1].tempo.data, xPosition, yPosition,(int)((double)(width)*(1100.0/580.0)), height, null);
                } else {
                    g.drawImage(animations[1].tempo.data, xPosition-((int)((double)(width)*(1100.0/580.0))-width), yPosition, xPosition-((int)((double)(width)*(1100.0/580.0))-width)+(int)((double)(width)*(1100.0/580.0)), yPosition+height, 1100, 0, 0, 1120,null);
                }
                animations[1].tempo=animations[1].tempo.next;
                if (animations[1].tempo==animations[1].front) {
                    attacking=false;
                }
            } else if (start.game.frame.keys[KeyEvent.VK_D]==true || start.game.frame.keys[KeyEvent.VK_A]==true) {
                
                if (facingRight) {
                    g.drawImage(animations[0].tempo.data, xPosition, yPosition,width, height, null);
                } else {
                    g.drawImage(animations[0].tempo.data, xPosition, yPosition, xPosition+width, yPosition+height, 580, 0, 0, 1120,null);
                }
                animations[0].tempo=animations[0].tempo.next;
                
            } else {
                currentFrame=0;
                if (facingRight) {
                    g.drawImage(animations[0].front.data, xPosition, yPosition,width, height, null);
                } else {
                    g.drawImage(animations[0].front.data, xPosition, yPosition, xPosition+width, yPosition+height, 580, 0, 0, 1120,null);
                }
            }  
        } else if (animations[0].front.filename.equals("fireball_0.png")) {
            if (cast) {
                fireballY+=(start.screenHeight/16.0)/4;
                yPosition=fireballY;
                g.drawImage(animations[0].tempo.data, castX, fireballY, width, height, null);
                animations[0].tempo=animations[0].tempo.next;
                if (fireballY>start.screenHeight) {
                    fireballY=-height;
                    yPosition=fireballY;
                    cast=false;
                }
                
            }
        } 
        
    
    }
    public void input(String input) throws FileNotFoundException {
        if (start.game.currentState.equals("Title Screen")) {
            if (input.equals("W") || input.equals("S")) {
                if (yPosition == originalY) {
                    yPosition=yPosition+(height/2); 
                } else {
                    yPosition = originalY;
                }
            } else if (input.equals("Enter")) {
                if (yPosition == originalY) {
                    start.game.currentState="Gameplay";

                } else {
                    start.game.currentState="Options";
                }
            }
        } else if (start.game.currentState.equals("Options")) {
            int[] checkers = {(int)((1.49)*-start.screenHeight/6), -start.screenHeight/16 , (height/6), start.screenHeight/3};
            if (input.equals("W")) {
                for (int i=1; i<checkers.length+1 ;i++) {
                    if (yPosition==checkers[i-1]) {
                        if (i==1) {
                            yPosition=checkers[3];
                        } else {
                            yPosition=checkers[i-2];
                        }
                        break;
                    }
                }
            } else if (input.equals("S")) {
                for (int i=1; i<checkers.length+1 ;i++) {
                    if (yPosition==checkers[i-1]) {
                        if (i==4) {
                            yPosition=checkers[0];
                        } else {
                            yPosition=checkers[i];
                        }
                        break;
                    }
                }
            } else if (input.equals("Enter")) {
                if (yPosition == checkers[0]) {
                    start.sprites[1].selected=!start.sprites[1].selected;
                    start.sprites[2].selected=false;
                } else if (yPosition == checkers[1]) {
                    start.sprites[2].selected=!start.sprites[2].selected;
                    start.sprites[1].selected=false;
                } else if (yPosition == checkers[2]) {
                    start.sprites[3].selected=!start.sprites[3].selected;
                } else {
                    String resolution= start.screenWidth+" "+start.screenHeight;
                    if (start.sprites[1].selected==true) {
                        resolution="1280 720";
                    } else if (start.sprites[2].selected==true) {
                        resolution="1920 1080";
                    }
                    PrintStream output = new PrintStream(new File(SlashAndSummon.longTing()+"settings.txt"));
                    output.println(resolution+" "+start.sprites[3].selected);
                    System.exit(0);
                }
                
            }
        } else if (start.game.currentState.equals("Game over")) {
            if (input.equals("W") || input.equals("S")) {
                if (yPosition == originalY) {
                    yPosition=yPosition+(int)(2.25*(double)height/8); 
                } else {
                    yPosition = originalY;
                }
            } else if (input.equals("Enter")) {
                if (yPosition == originalY) {
                    start.game.battle.resetGame();
                    start.game.currentState="Gameplay";

                } else {
                    System.exit(0);
                }
            }
        }
    }

    public void update() {
        if (!attacking) {
            if (jumping) {
                if (jumpButtonPressed) {
                    if (start.game.frame.keys[KeyEvent.VK_SPACE]==false) {
                        jumpButtonPressed=false;
                        if (yVelocity>10){
                            yVelocity=10;
                        }
                    }
                }
                yPosition-=yVelocity;
                yVelocity-=2;
                if (yPosition>=originalY) {
                    jumping=false;
                    yPosition=originalY;
                }
            }
        
            if (start.game.frame.keys[KeyEvent.VK_D]==true) {
                facingRight=true;
                xPosition+=(start.screenHeight/16.0)/4;
                if (xPosition<0) {
                    xPosition=0;
                } else if (start.screenWidth-width<xPosition) {
                    xPosition=start.screenWidth-width;
                }
            } else if (start.game.frame.keys[KeyEvent.VK_A]==true) {
                facingRight=false;
                xPosition-=(start.screenHeight/16.0)/4;
                if (xPosition<0) {
                    xPosition=0;
                } else if (start.screenWidth-width<xPosition) {
                    xPosition=start.screenWidth-width;
                }
            }
        }
    }
}
