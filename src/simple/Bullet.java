/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple;

import java.awt.Rectangle;
import java.awt.geom.Line2D;

/**
 *
 * @author naree1878
 */
public class Bullet {

    private double posx;
    private double posy;
    private double terx = 0;
    private double tery = 0;
    private double bv = 12;

    public Bullet(double x, double y, double tx, double ty) {
        posx = x;
        posy = y;
        //if (terx != 0 && tery != 0) {
        ter(tx, ty);
        //}
    }

    public void move(Rectangle[] r) {
        Line2D b = new Line2D.Double(posx + 6, posy + 6, posx + terx + terx + terx + 6, posy + tery + tery + tery + 6);
        for (int i = 0; i < r.length; i++) {
            if (b.intersects(r[i])) {
                posx = 12345;
                posy = 12345;
            } else {
                posx += terx;
                posy += tery;
            }
        }

    }

    public double getx() {
        return posx;
    }

    public double gety() {
        return posy;
    }

    public double gettx() {
        return terx;
    }

    public double getty() {
        return tery;
    }

    public void setv(double v) {
        bv = v;
    }

    public void ter(double mx, double my) {
        double hyp;

        if (((mx - posx) < 0 && (my - posy) > 0) || ((posx - mx) < 0 && (posy - my) > 0)) {
            hyp = Math.sqrt(Math.pow((mx - posx), 2) + Math.pow((posy - my), 2)); //gets the hypotenuse if its in top right or bottom left
        } else {
            hyp = Math.sqrt(Math.pow((posx - mx), 2) + Math.pow((posy - my), 2));
        }
        double vFactorX = (mx - posx) / (hyp); //gets the basic amount the bullet needs to move
        double vFactorY = (my - posy) / (hyp);
        terx = vFactorX * bv;
        tery = vFactorY * bv;
    }
}
