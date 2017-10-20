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
        Rectangle g;
        for (int i = 0; i < 4; i++) {
            g = new Rectangle((int) (posx + 7), (int) posy, 12, 12);
            if (g.intersects(obj[i])) {
                break;
            } else if (Simple.px > posx) {
                posx += .5;
            }
            g = new Rectangle((int) (posx + 5), (int) posy + 6, 12, 12);
            if (g.intersects(obj[i])) {
                break;
            } else if (Simple.px < posx) {
                posx -= .5;
            }
            g = new Rectangle((int) (posx + 6), (int) posy + 7, 12, 12);
            if (g.intersects(obj[i])) {
                break;
            } else if (Simple.py > posy) {
                posy += .5;
            }
            g = new Rectangle((int) (posx + 6), (int) posy + 5, 12, 12);
            if (g.intersects(obj[i])) {
                break;
            } else if (Simple.py < posy) {
                posy -= .5;
            }
        }
    }

    public Bullet shoot() {
        Bullet b = new Bullet(posx, posx, Simple.px, Simple.py);
        return b;
    }
}
