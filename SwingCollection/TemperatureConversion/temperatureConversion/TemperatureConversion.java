package temperatureConversion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class TemperatureConversion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JRadioButton radioButtonCelsius;
	private JRadioButton radioButtonKelvin;
	private ButtonGroup radioGroup;
	private JTextField textFieldEnter;
	private JTextField textFieldCalculate;
	private JLabel label1;
	private JLabel label2;
	private JPanel buttonJPanel1;
	private JPanel buttonJPanel2;
	private JPanel buttonJPanel3;

	
	public TemperatureConversion(){
		super ("Temperature Conversion");
		setLayout(new BorderLayout(10,10));
		buttonJPanel1 = new JPanel();
		label1 = new JLabel("Enter the Fahrenheit temperature: ");
		buttonJPanel1.add(label1);
		textFieldEnter = new JTextField(10);
		buttonJPanel1.add(textFieldEnter);
		add(buttonJPanel1, BorderLayout.NORTH);
		
		buttonJPanel2 = new JPanel();
		radioGroup = new ButtonGroup();
		radioButtonCelsius = new JRadioButton("Celsius", true);
		radioButtonKelvin = new JRadioButton("Kelvin");
		buttonJPanel2.add(radioButtonCelsius);
		buttonJPanel2.add(radioButtonKelvin);
		radioGroup.add(radioButtonCelsius);
		radioGroup.add(radioButtonKelvin);
		add(buttonJPanel2, BorderLayout.CENTER);
		
		buttonJPanel3 = new JPanel();
		label2 = new JLabel("The converted temperature: ");
		buttonJPanel3.add(label2);
		textFieldCalculate = new JTextField(10);
		textFieldCalculate.setEditable(false);
		buttonJPanel3.add(textFieldCalculate);
		add(buttonJPanel3, BorderLayout.SOUTH);	
		
		TextFieldHandler handler = new TextFieldHandler();
		RadioButtonHandler listener = new RadioButtonHandler();
		textFieldEnter.addActionListener(handler);
		radioButtonCelsius.addItemListener(listener);
		radioButtonKelvin.addItemListener(listener);
	}
	
    private class TextFieldHandler implements ActionListener {
    	double x;
        double celsius;
        double kelvin;
        double fahrenheit;
        double exit;

        public void actionPerformed(ActionEvent e) {
            x = Double.parseDouble(textFieldEnter.getText());

	        fahrenheit = x;

	        if (radioButtonCelsius.isSelected()){
	        	celsius = 5/9.0 * ( fahrenheit - 32 );
	        	exit=celsius;
	        }
	        if (radioButtonKelvin.isSelected()){
	            kelvin = (5/9.0 * ( fahrenheit - 32 )) + 273.15;
	            exit=kelvin;
	        }
	        String tot= String.format("%.2f", exit);
	        textFieldCalculate.setText(tot);
        }
    }
    
    private class RadioButtonHandler implements ItemListener{
    	double x;
        double celsius;
        double kelvin;
        double fahrenheit;
        double exit;
		public void itemStateChanged(ItemEvent event) {

	        x = Double.parseDouble(textFieldEnter.getText());
	        fahrenheit = x;
	        
	        if (radioButtonCelsius.isSelected()){
	        	celsius = 5/9.0 * ( fahrenheit - 32 );
	        	exit = celsius;
	        }
	        if (radioButtonKelvin.isSelected()){
	            kelvin = (5/9.0 * ( fahrenheit - 32 )) + 273.15;
	            exit = kelvin;
	        }
	        String tot= String.format("%.2f", exit);
	        textFieldCalculate.setText(tot);
		}
    	
    }
    
}
    

        
		
 
	
