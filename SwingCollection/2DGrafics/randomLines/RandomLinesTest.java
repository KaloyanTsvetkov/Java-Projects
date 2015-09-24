package randomLines;

import javax.swing.JFrame;

public class RandomLinesTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Drawing Arcs");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RandomLines linesJPanel = new RandomLines();
		frame.add(linesJPanel);
		frame.setSize(400, 400);
		frame.setVisible(true);

	}

}
