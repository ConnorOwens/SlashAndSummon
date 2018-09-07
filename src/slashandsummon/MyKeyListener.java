/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slashandsummon;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aragorn
 */
public class MyKeyListener implements KeyListener {
    public Game game;
    public MyJPanel panel;

    public MyKeyListener(Game game, MyJPanel panel) {
        this.game=game;
        this.panel=panel;
        panel.keys = new boolean[200];
        for (int i=0; i<panel.keys.length;i++) {
            panel.keys[i]=false;
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
        

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //if (game.currentState == "Title Screen" || game.currentState == "Options" || game.currentState == "Game over") {
            System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
            try {
                game.input(KeyEvent.getKeyText(e.getKeyCode()));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MyKeyListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        //panel.repaint();
        //} else {
            panel.keys[e.getKeyCode()] = true;
        //}
    }

    @Override
    public void keyReleased(KeyEvent e) {
        panel.keys[e.getKeyCode()] = false;
        System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
    }
    
}
