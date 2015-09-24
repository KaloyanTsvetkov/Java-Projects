package grafics;

import javax.swing.JFrame;

public class EightCirclesTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame frame = new JFrame("Drawing Arcs");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		EightCircles arcsJPanel = new EightCircles();
		frame.add(arcsJPanel);
		frame.setSize(500, 500);
		frame.setVisible(true);
	}

}
