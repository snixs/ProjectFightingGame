package Game;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Enemy {

	int x, dx, dy, y;
	Image sp;
	Thread animetion;
	boolean done;
	boolean h;
	int jumpd, JD;
	boolean pressed;
	ImageIcon onR = new ImageIcon("src/SSJ3GotenksR.gif");
	ImageIcon onL = new ImageIcon("src/SSJ3GotenksL.gif");
	ImageIcon wL = new ImageIcon("src/SSJ3Gotenks-walkingL.gif");
	ImageIcon wR = new ImageIcon("src/SSJ3Gotenks-walkingR.gif");
	

	public Enemy() {
		sp = onL.getImage();
		x = 633;
		y = 170;
		jumpd=150;
		pressed=false;
	}

	public void move() {
	if((x+dx) < 634 && (x+dx)>-1)
		x+=dx;	
	if((dy+y)>160 && (dy+y)<256)
		y+=dy;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImage() {
		return sp;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT)
		{
			dx=-2;
			sp = wL.getImage();
		}

		if (key == KeyEvent.VK_RIGHT)
		{
			dx=+2;
			sp = wR.getImage();
		}
		
		if (key == KeyEvent.VK_UP)
			dy=-2;
		if (key == KeyEvent.VK_DOWN)
			dy=+2;
		if (key == KeyEvent.VK_SPACE && pressed == false)
		{
			pressed=true;
		}
		
		System.out.print("y="+y+"\n");
		System.out.print("x="+x+"\n");
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_LEFT)
		{
			dx = 0;
			sp = onL.getImage();
		}
		if (key == KeyEvent.VK_RIGHT)
		{
			dx = 0;
			sp = onR.getImage();
		}
		if (key == KeyEvent.VK_UP)
			dy = 0;
		if (key == KeyEvent.VK_DOWN)
			dy = 0;
	}
}
