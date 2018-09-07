/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slashandsummon;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.event.MouseInputListener;
/**
 *
 * @author owencon18
 */
public class MyJPanel extends JPanel implements MouseInputListener{
    int index;
    int x;
    int y;
    int xCenterDist;
    int yCenterDist;
    Game game;
    int screenHeight;
    int screenWidth;
    final BufferedImage image;
    KeyListener listener;
    public boolean[] keys;
    public boolean mousePressed;
    public int pressedX;
    public int pressedY;
    public int currentX;
    public int currentY;
    public int releasedX;
    public int releasedY;
    public MyJPanel(Game game, int screenWidth, int screenHeight) throws IOException  {
        this.screenHeight=screenHeight;
        this.screenWidth=screenWidth;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.game=game;
        
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        image = ImageIO.read(new File("background.png"));
        game.frame=this;
        listener = new MyKeyListener(game,this);
	addKeyListener(listener);
	setFocusable(true);
    }
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);       
        g.drawImage(image,0,0,screenWidth,screenHeight,null);
        game.render(g);
    }  
    @Override
    public void mouseClicked(MouseEvent e) {
        
        
    }
    @Override 
    public void mouseExited(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed=false;
        
        game.mouseInput("released "+e.getX()+" "+e.getY());
        
    }
    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed=true;
        
        game.mouseInput("pressed "+e.getX()+" "+e.getY());
            
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        
        game.mouseInput("dragged "+me.getX()+" "+me.getY());
        
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        
    }

    public void redraw() {
        this.repaint();
    }
}
