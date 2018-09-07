/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slashandsummon;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Aragorn
 */
public class SlashAndSummon {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        int screenWidth = 1280;
        int screenHeight = 720;
        boolean fullscreen = false;
        try {
        Scanner input = new Scanner(new File(longTing()+"settings.txt"));
        if (!input.hasNextInt()) {
            PrintStream output = new PrintStream(new File(SlashAndSummon.longTing()+"settings.txt"));
            output.println("1280 720 false");
        } else if (input.hasNext()) {
            screenWidth = input.nextInt();
            screenHeight = input.nextInt();
            fullscreen = input.nextBoolean();
        } 
        } catch(IOException e) {
            PrintStream output = new PrintStream(new File(SlashAndSummon.longTing()+"settings.txt"));
            output.println("1280 720 false");
        }  
        
        Game game = new Game(screenWidth, screenHeight);
        JFrame frame = buildFrame(game, screenHeight, screenWidth, fullscreen);
        double frame_cap = 1.0/60.0;
        
        double frame_time =0;
        int frames=0;
        
        double time = Timer.getTime();
        double unprocessed=0;
        boolean running=true;
        while(running) {
            boolean canRender = false;
            double time_2=Timer.getTime();
            double passed = time_2-time;
            unprocessed+=passed;
            frame_time += passed;
            
            time = time_2;
            
            while(unprocessed>=frame_cap) {
                unprocessed-=frame_cap;
                canRender=true;
                
                //if (win.getInput().isKeyPressed(GLFW_KEY_ESCAPE)) {
                  //  glfwSetWindowShouldClose(win.getWindow(), true);
                //}
                if (game.currentState.equals("Gameplay"))
                game.update();
                
                if (frame_time >= 1.0) {
                    frame_time =0;
                    System.out.println("FPS: "+frames);
                    frames =0;
                }
            }
            if (canRender) {
                game.frame.redraw();
                frames++;
            }
        } 
        
    }

    private static JFrame buildFrame(Game game, int screenHeight, int screenWidth, boolean fullscreen) throws IOException {
        JFrame frame = new JFrame("Slash and Summon");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(screenWidth+17, screenHeight+40);
        MyJPanel pane = new MyJPanel(game, screenWidth, screenHeight);

        frame.add(pane);
        if (fullscreen==true) {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
            frame.setUndecorated(true);   
        }
        frame.setVisible(true);
        return frame;
    }
    public static String longTing() {
        return "";//"C:\\Users\\owencon18\\Documents\\NetBeansProjects\\slash&summon3\\";
    }
    

}