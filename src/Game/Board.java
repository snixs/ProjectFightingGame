package Game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JPanel implements ActionListener {

	Fighter f;
	Enemy e;
	public Image img;
	Timer time;

	public Board() {
		f = new Fighter();
		e = new Enemy();
		addKeyListener(new AL());
		setFocusable(true);
		ImageIcon i = new ImageIcon("C:/Users/Vlad/Desktop/back1.jpg");
		img = i.getImage();
	}

	public void actionPerformed(ActionEvent e) {
		f.move();
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, 0, 0, null);
		g2d.drawImage(f.getStartImage(), f.getX()+20, f.getY()+20, null);
		g2d.drawImage(f.getImage(), f.getX(), f.getY(), null);

	}

	private class AL extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
			f.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			f.keyPressed(e);
		}
	}
}
