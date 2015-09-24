package randomLines;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.util.Random;

import javax.swing.JPanel;

public class RandomLines extends JPanel implements MouseListener{

	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Random random = new Random();
		Graphics2D g2d = (Graphics2D) g;

		for(int i = 0; i<20; i++){
			g2d.setPaint(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
			g2d.setStroke(new BasicStroke(random.nextFloat()+random.nextInt(15)));
			g2d.draw(new Line2D.Double(random.nextInt(getWidth()),random.nextInt(getHeight()),
					random.nextInt(getWidth()), random.nextInt(getHeight())));
		}
		addMouseListener(this);
	}
		

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
