package bouncingBall;

import javax.swing.JFrame;

public class BouncingBallsTest {

	   public static void main(String[] args) {

	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                JFrame f = new JFrame("Bouncing Balls");
	                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                f.setContentPane(new BouncingBalls(490, 490));
	                f.pack();
	                f.setVisible(true);
	                f.setResizable(false);
	            }
	        });
	   }
}
