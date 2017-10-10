/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple;

import java.awt.Rectangle;

/**
 *
 * @author naree1878
 */
public class guys {

    private int hp;
    private double posx;
    private double posy;
    private double damage;

    public guys(int x, int y, int lvl) {
        posx = x;
        posy = y;
        hp = lvl * 3;
        damage = lvl * 1.5;
    }

    public double getx() {
        return posx;
    }

    public double gety() {
        return posy;
    }

    public void move(Rectangle[] obj) {
        Rectangle g = new Rectangle((int) posx + 6, (int) posy + 6, 12, 12);
        for (int i = 0; i < obj.length; i++) {
            if (g.intersects(obj[i])) {
                System.out.println("dd");
            } else {
                posx += 0.5;
                System.out.println("ee");
            }
        }
    }

    public void shoot() {

    }

}
