package Game;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class Frame {

	public Frame(){
		JFrame frame = new JFrame();
		frame.add(new Board());
		frame.setTitle("2-D Test Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1040,600);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	
	public static void main(String[] args)
	{
		new Frame();
		// Init the window
				JFrame frame = new JFrame("ImageAnimation");
				frame.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent event) {
						System.exit(0);
					}
				});
				frame.setSize(200, 200);
				frame.setVisible(true);

	}
}

