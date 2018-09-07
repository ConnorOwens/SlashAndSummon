/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slashandsummon;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author owencon18
 */
public class ListNode {
    ListNode next;
    BufferedImage data;
    String filename;
    public ListNode() {
        next=null;
    }
    public ListNode(String filename) throws IOException {
        this.filename=filename;
        data = ImageIO.read(new File(filename));
        next=null;
    }
}
