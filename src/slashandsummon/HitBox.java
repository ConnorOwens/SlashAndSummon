/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slashandsummon;

/**
 *
 * @author owencon18
 */
public class HitBox {
    public int x;
    public int y;
    public int width;
    public int height;
    
    public HitBox(int x,int y,int width, int height) {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }
    public boolean collidesWith(HitBox rect2) {
        if (x < rect2.x + rect2.width &&
                x + width > rect2.x &&
                y < rect2.y + rect2.height &&
                height + y > rect2.y) {
                return true;
            } else {
            return false;
        }
    }
}
