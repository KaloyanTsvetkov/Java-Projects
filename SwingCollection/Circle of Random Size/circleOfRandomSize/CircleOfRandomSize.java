package circleOfRandomSize;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class CircleOfRandomSize extends JPanel{
	
	private double radius;
	Random random = new Random();
	
	public CircleOfRandomSize(){
		super();
		int x = random.nextInt(100)+ 30;
		setRadius(x);
	}


	
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
		g.fillArc(20, 20, (int)getRadius(), (int)getRadius(),0 , 360);
		
	}
}
