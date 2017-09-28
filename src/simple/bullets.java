/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple;

/**
 *
 * @author naree1878
 */
public class bullets {

    private int posx;
    private int posy;
    private int terx;
    private int tery;

    public bullets(int x, int y, int tx, int ty) {
        posx = x;
        posy = y;
        terx = tx;
        tery = ty;
        System.out.println(tx+" "+ty);
    }

    public void move() {
        posx += terx;
        posy += tery;
    }

    public int getx() {
        return posx;
    }

    public int gety() {
        return posy;
    }
}
