/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple;//package name

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.Timer;

import static simple.Levels.load1;

public class Simple extends JApplet implements ActionListener, KeyListener, MouseMotionListener, MouseWheelListener, MouseListener {

    static JFrame f = new JFrame("");
    Graphics2D myPic;
    Image dbImage, master;
    private Graphics dbg;
    Timer timer;

    double mx, my; //mouse positions
    double px = 200, py = 300; //sets player positon
    guys men[] = load1(); //gets the emeny list for level one
    boolean[] pres = new boolean[4]; //buttons being pressed

    bullets[] b = new bullets[25]; //array of bullets to be shot
    double bv = 12, pv = 4; //bullet speed and player speed

    public Simple() {
        timer = new Timer(16, this);
        timer.setInitialDelay(100);
        timer.start();
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //this will run every 2 seconds
            }
        });
        try {
            FileReader fr = new FileReader("save.txt");
            BufferedReader br = new BufferedReader(fr);
        } catch (IOException a) {
            System.out.println("Couldn't Load");
        }
        addKeyListener(this);
        addMouseWheelListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);

    }

    public static void main(String[] args) {
        JApplet applet = new Simple();
        f.getContentPane().add("Center", applet);
        applet.init();
        f.pack();
        f.setVisible(true);
        f.setResizable(false);
        f.setBounds(10, 10, 1300, 700);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void paint(Graphics g) {
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }

    public void ter() {
        double hyp;

        if (((mx - px) < 0 && (my - py) > 0) || ((px - mx) < 0 && (py - my) > 0)) {
            hyp = Math.sqrt(Math.pow((mx - px), 2) + Math.pow((py - my), 2)); //gets the hypotenuse if its in top right or bottom left
        } else {
            hyp = Math.sqrt(Math.pow((px - mx), 2) + Math.pow((py - my), 2));
        }
        double vFactorX = (mx - px) / (hyp); //gets the basic amount the bullet needs to move
        double vFactorY = (my - py) / (hyp);

        for (int i = 0; i < 25; i++) {
            if (b[i] == null || b[i].getx() < 0 || b[i].getx() > 2500 || b[i].getx() < 0 || b[i].getx() > 1500) { //find the first unused bullet and starts it moving
                b[i] = new bullets(px, py, (vFactorX * bv), (vFactorY * bv)); //YOU SHOULD ADD RELATIVE BULLET SPEED
                i = 50;
            }

        }
    }

    public void paintComponent(Graphics g) {
        myPic = (Graphics2D) g;
        for (int i = 0; i < 4; i++) {
            myPic.drawRect(men[i].getx(), men[i].gety(), 12, 12); //draws the enemys
            men[i].move(); //moves them
        }

        myPic.fillRect((int) px - 10, (int) py - 10, 20, 20); //draws the player

        for (int i = 0; i < 25; i++) {
            if (b[i] == null) {
            } else {
                myPic.fillRect((int) b[i].getx(), (int) b[i].gety(), 12, 12); //draws the bullets
                b[i].move(); //moves them
            }
        }
        move();
    }

    public void move() {
        if (pres[0] == true && pres[2] == true) { //moves the player depending on which set of keys are being pressed
            px -= (pv * (1 / Math.sqrt(pv)));
            py -= (pv * (1 / Math.sqrt(pv)));
        } else if (pres[1] == true && pres[2] == true) {
            px -= (pv * (1 / Math.sqrt(pv)));
            py += (pv * (1 / Math.sqrt(pv)));
        } else if (pres[0] == true && pres[3] == true) {
            px += (pv * (1 / Math.sqrt(pv)));
            py -= (pv * (1 / Math.sqrt(pv)));
        } else if (pres[1] == true && pres[3] == true) {
            px += (pv * (1 / Math.sqrt(pv)));
            py += (pv * (1 / Math.sqrt(pv)));
        } else {
            if (pres[0] == true) {
                py -= pv;
            }
            if (pres[1] == true) {
                py += pv;
            }
            if (pres[2] == true) {
                px -= pv;
            }
            if (pres[3] == true) {
                px += pv;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        requestFocus();
        setFocusTraversalKeysEnabled(false);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //key presses
        if (e.getKeyCode() == KeyEvent.VK_W) {
            pres[0] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            pres[1] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            pres[2] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            pres[3] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            try {
                FileWriter fw = new FileWriter("save.txt");
                PrintWriter pw = new PrintWriter(fw);
                System.out.println("Saved");
                pw.close();
            } catch (IOException a) {
                System.out.println("ERROR");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            pres[0] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            pres[1] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            pres[2] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            pres[3] = false;
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        /* if (zoom < 3 && e.getPreciseWheelRotation() < 0) {
         zoom++;
         } else if (zoom > 1 && e.getPreciseWheelRotation() > 0) {
         zoom--;
         }*/
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mx = e.getX();
        my = e.getY(); //sets the mouses position
        ter(); //spawns the bullets
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
