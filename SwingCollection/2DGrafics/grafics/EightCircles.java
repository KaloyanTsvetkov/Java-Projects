package grafics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class EightCircles extends JPanel{

	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Random random = new Random();
		int height = 40;
		int width = 40;
		int x = 200;
		int y = 200;
		for(int i=0; i<20; i++){
		g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
		g.drawArc(x, y, width, height, 0, 360);
		width = width + 20;
		height = height + 20;
		x = x - 10;
		y = y - 10;
		}
	}
}
