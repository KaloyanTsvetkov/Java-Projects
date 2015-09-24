package circleOfRandomSize;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JFrameCalculator extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextArea textAreaCalculater;
	private CircleOfRandomSize circle;
	private final double pi = Math.PI;
	private double area;
	private double radius;
	private double diameter;
	private double circumference;
	private JSlider diameterJSlider;
	
	
	public JFrameCalculator(){
		super();
		setLayout(new BorderLayout(60, 20));
		
		circle = new CircleOfRandomSize();
		add(circle, BorderLayout.CENTER);
		
		textAreaCalculater = new JTextArea("", 10, 15);
		textAreaCalculater.setEditable(false);
		add(textAreaCalculater, BorderLayout.SOUTH);
		calculatesValues();
		
		diameterJSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 200, 10);
		diameterJSlider.setMajorTickSpacing(10);
		diameterJSlider.setPaintTicks(true);
		add(diameterJSlider, BorderLayout.NORTH);
		diameterJSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
			circle.setRadius(diameterJSlider.getValue());
			repaint();
			calculatesValues();
			}
			});
		
	}
	
	public void calculatesValues(){
		
		setRadius(circle.getRadius());
		setDiameter(2*getRadius());
		setArea(getPi()*(getRadius()*getRadius()));
		setCircumference(2*getPi()*getRadius());
			
		String total = String.format("%s %.2f %s %.2f %s %.2f %s %.2f","The radius is: ", getRadius(), "\nThe diameter is: ",
				getDiameter(),"\nThe area is: ", getArea(), "\nThe circumference is: ", getCircumference());
		
		textAreaCalculater.setText(total);
	}
	
	
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public double getDiameter() {
		return diameter;
	}
	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}
	public double getCircumference() {
		return circumference;
	}
	public void setCircumference(double circumference) {
		this.circumference = circumference;
	}

	public double getPi() {
		return pi;
	}
	
}
