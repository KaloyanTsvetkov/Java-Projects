package grafics;

import javax.swing.JFrame;

public class EightCirclesFillTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame frame = new JFrame("Drawing Arcs");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		EightCirclesFill arcsJPanel = new EightCirclesFill();
		frame.add(arcsJPanel);
		frame.setSize(500, 500);
		frame.setVisible(true);
	}

}
