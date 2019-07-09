package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

public class InBar {

	private Rectangle hp;
	private Rectangle mp;
	private Dimension d;
	private Point p;
	private Color red = new Color(200,0,0);
	private Color blue = new Color(0,0,200);
	private  AttributedString hpName;
	private  AttributedString mpName;
	private int xh;
	private int yh;
	private int xm;
	private int ym;
	
	public InBar()
	{
		p = new Point (0,0);
		d = new Dimension(100, 10);
		hpName = new AttributedString("Hp");
		mpName = new AttributedString("Mp");
		
		hp = new Rectangle(p,d);
		mp = new Rectangle(p,d);
		 
		Font font1 = new Font("Helvetica", Font.PLAIN, 20);
		 
		hpName.addAttribute(TextAttribute.FONT, font1);
		hpName.addAttribute(TextAttribute.FOREGROUND, Color.red);

		mpName.addAttribute(TextAttribute.FONT, font1);
		mpName.addAttribute(TextAttribute.FOREGROUND, Color.blue);
		

	
		setSize();
	}
	public void draw(Graphics2D g)
	{
		g.drawString(hpName.getIterator(), xh,yh);
		g.setPaint(red);
	    g.fill(hp);
		g.draw(hp);
		
		g.drawString(mpName.getIterator(), xm,ym);
		g.setPaint(blue);
	    g.fill(mp);
		g.draw(mp);
	}
	public void setLoc(int x, int y)
	{

		this.xh = x-30;
		this.yh = y+11;
		p.setLocation(x, y);
		hp.setLocation(p);

		int xnew = x+11;
		int ynew = y+20;
		this.xm = xnew-30;
		this.ym = ynew+11;
		p.setLocation(xnew,ynew);
		mp.setLocation(p);
	}
	
	private void setSize()
	{
		hp.setSize(d);
		mp.setSize(d);
	}
	
	private void increase()
	{
		d.width++;
	}
	
	private void  decrease()
	{
		d.width--;
	}

}
