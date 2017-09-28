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
public class guys {

    private int hp;
    private int posx;
    private int posy;
    private double damage;

    public guys(int x, int y, int lvl) {
        posx = x;
        posy = y;
        hp = lvl * 3;
        damage = lvl * 1.5;
    }
    public int getx(){
        return posx;
    }
    public int gety(){
        return posy;
    }
    public void move(){
        posx+=1;
        posy-=1;
    }
    public void shoot(){
        
    }
          
}
