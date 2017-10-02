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
public class bullets {

    private double posx;
    private double posy;
    private double terx;
    private double tery;

    public bullets(double x, double y, double tx, double ty) {
        posx = x;
        posy = y;
        terx = tx;
        tery = ty;
    }

    public void move(Rectangle[] r) {
        Line2D b = new Line2D.Double(posx + 6, posy + 6, posx + terx+24 + 6, posy + tery+24 + 6);
        for (int i = 0; i < r.length; i++) {
            if (b.intersects(r[i])) {
                posx = 12345;
                posy = 12345;
            } else {
                posx += terx;
                posy += tery;
                System.out.println(terx+" "+tery);
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
}
