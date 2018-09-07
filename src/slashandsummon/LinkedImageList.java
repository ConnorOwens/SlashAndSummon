/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slashandsummon;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author owencon18
 */
public class LinkedImageList {
    ListNode front;
    ListNode tempo;
    public LinkedImageList(String[] filenames) throws IOException {
        front=new ListNode();
        ListNode temp = front;
        for (String name : filenames){
            temp.data=ImageIO.read(new File(name));
            temp.filename=name;
            temp.next = new ListNode();
            temp=temp.next;
        }
        temp.next=front;
        ListNode temp2 = front;
        while (temp.next.filename!=null) {
            temp=temp.next;
        }
        temp.next=temp.next.next;
        tempo=front;
    }
    public LinkedImageList(String filenames) throws IOException {
        front=new ListNode(filenames);
        
    }
    public void print() {
        ListNode temp = front;
        for (int i=0; i<9; i++) {
            System.out.println(temp.filename);
            temp=temp.next;
        }
    }
}
