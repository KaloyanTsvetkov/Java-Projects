package grafics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class EightCirclesFill extends JPanel{

	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Random random = new Random();
		int height = 400;
		int width = 400;
		int x = 20;
		int y = 20;
		for(int i=0; i<20; i++){
		g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
		g.fillArc(x, y, width, height, 0, 360);
		width = width - 20;
		height = height - 20;
		x = x + 10;
		y = y + 10;
		}
	}
}
