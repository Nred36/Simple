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
        Rectangle b = new Rectangle((int) (posx), (int) (posy), 12 + (int) terx, 12 + (int) tery);
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
}
