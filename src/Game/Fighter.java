package Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Fighter implements Runnable {
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
	ImageIcon jR = new ImageIcon("src/SSJ3Gotenks-jumpR.gif");
	ImageIcon jT = new ImageIcon("src/SSJ3Gotenks-transformR.gif");
	
	
	
	public Fighter()
	{
		sp = jT.getImage();
		x = 100;
		y = 100;
		jumpd=150;
		pressed=false;
		
		sp = onR.getImage();
		
		
	}
	public void move() {
	if((x+dx) < 980 && (x+dx)>-5)
		x+=dx;	
		y+=dy;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		if(sp == wL.getImage() || sp==wR.getImage())
		{
			y=315;
		}
		if(sp == onL.getImage() || sp==onR.getImage())
		{
			y=340;
		}
		return y;
	}
	public Image getImage() {
		return sp;
	}
	public Image getStartImage() {
		return jT.getImage();
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
			dy=-1;
		if (key == KeyEvent.VK_DOWN)
			dy=+1;
		if (key == KeyEvent.VK_SPACE && pressed == false)
		{
			sp = jR.getImage();
			jump();
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
	}
	private void jump (){
            animetion = new Thread(this);
            animetion.start();
    }
	

	public void run() {
		h = false;
		JD = jumpd;
		done = false;
		double sleep;
			while (done == false) {

				done= cycle ();
				sleep = 3;
				try {Thread.sleep((long) sleep);} 
				catch (InterruptedException e) {
					System.out.println("interrupted");
				}
			}
			JD=0;
			sp = onR.getImage();
			pressed=false;
	}
	public boolean cycle() {
		
		if (h==false){
			y--;	
		JD--;}
		if(JD == 0)
			h= true;
		if (h==true && JD<jumpd)
			{
			  y++;
			  JD++;
			}
		if(JD==jumpd)
			return true;
		else 
			return false;
		}
	}

