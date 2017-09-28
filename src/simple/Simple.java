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

    int mX, mY, px = 200, py = 300;
    guys men[] = load1();

    bullets b1;    

    public Simple() {//program name
        timer = new Timer(16, this);
        timer.setInitialDelay(100);     //starts timer
        timer.start();
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //this will run every 2 seconds
            }
        });
        try {//READ
            FileReader fr = new FileReader("save.txt"); //reads from text file (located in "files"
            BufferedReader br = new BufferedReader(fr);
            //map[c] = Integer.parseInt(br.readLine());            
        } catch (IOException a) {
            System.out.println("Couldn't Load");//if it fails
        }
        addKeyListener(this);
        addMouseWheelListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);

    }

    public static void main(String[] args) {
        JApplet applet = new Simple();        //sets up the window
        f.getContentPane().add("Center", applet);
        applet.init();
        f.pack();
        f.setVisible(true); //makes it visible
        f.setResizable(false);//makes in unsizable
        f.setBounds(10, 10, 1300, 700);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //stops program if you x out the window    
    }

    public void paint(Graphics g) {
        dbImage = createImage(getWidth(), getHeight());      //creats and image the size of the screen
        dbg = dbImage.getGraphics();        //double buffers the panel
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }

    public void ter() {
        double hyp = Math.sqrt((px-mX)^2+(py-mY)^2);
        double vFactorX = (mX - px) / (hyp);
        double vFactorY = (mY - py) / (hyp);

        double v = 2;
        b1 = new bullets(px, py, (int) (vFactorX * v), (int) (vFactorY * v));
    }

    public void paintComponent(Graphics g) {
        myPic = (Graphics2D) g;
        for (int i = 0; i < 4; i++) {
            myPic.drawRect(men[i].getx(), men[i].gety(), 12, 12);
            men[i].move();
        }
        if (b1 == null) {
        } else {
            myPic.fillRect(b1.getx(), b1.gety(), 12, 12);
            b1.move();
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
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            //runs if escape is pressed
            try {
                FileWriter fw = new FileWriter("save.txt");//set place to write to in "Files"
                PrintWriter pw = new PrintWriter(fw); //starts writing
                //pw.println();
                System.out.println("Saved");//it worked
                pw.close(); //stop writing
            } catch (IOException a) {
                System.out.println("ERROR");//it didnt work
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

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
        mX = e.getX();
        mY = e.getY();        
        ter();
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
