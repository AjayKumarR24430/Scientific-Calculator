import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Quadratic extends JFrame
{
	private JTextField CoA, CoB, CoC; 
	// roots are readonly fields so labels
	private JLabel Root1, Root2;
	private JLabel CoAx, CoBx, CoCx, Root1x, Root2x;
	private JButton calculateB, exitB;
	private CalculateButtonHandler cbHandler;
	private ExitButtonHandler ebHandler;

	public Quadratic()
	{
		setTitle("Quadratic Equation");        //Title of the window

		//Create the five labels
		CoAx = new JLabel("a", SwingConstants.CENTER);
		CoBx = new JLabel("b", SwingConstants.CENTER);
		CoCx = new JLabel("c", SwingConstants.CENTER);
		Root1x = new JLabel("Root1", SwingConstants.CENTER);
		Root2x = new JLabel("Root2", SwingConstants.CENTER);
		//Create the five text fields
		CoA = new JTextField(10);
		CoB = new JTextField(10);
		CoC = new JTextField(10);
		Root1 = new JLabel("", SwingConstants.RIGHT);
		Root2 = new JLabel("", SwingConstants.RIGHT);             
		//Create Calculate Button
		calculateB = new JButton("Calculate");
		cbHandler = new CalculateButtonHandler();
		calculateB.addActionListener(cbHandler);
		//Create Exit Button
		exitB = new JButton("Exit");
		ebHandler = new ExitButtonHandler();
		exitB.addActionListener(ebHandler);

		Container pane = getContentPane();//Get the container
		pane.setLayout(new GridLayout(6, 2));//Set the Layout
		//Place the components in the pane
		pane.add(CoAx);
		pane.add(CoA);
		pane.add(CoBx);
		pane.add(CoB);
		pane.add(CoCx);
		pane.add(CoC);
		pane.add(Root1x);
		pane.add(Root1);
		pane.add(Root2x);
		pane.add(Root2);
		pane.add(calculateB);
		pane.add(exitB);

		setSize(400,300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private class CalculateButtonHandler implements ActionListener//Creates a class on top of the interface
	{
		public void actionPerformed(ActionEvent e)
		{
			double a = Double.parseDouble(CoA.getText());
			double b = Double.parseDouble(CoB.getText());
			double c = Double.parseDouble(CoC.getText());
			double det = (4 * a * c);
			b = b * b;

			double R1 = Math.sqrt(b + det);
			double R2 = Math.sqrt(b - det);
 
			Root1.setText("" + R1);
			Root2.setText("" + R2);

		}
	}

	private class ExitButtonHandler implements ActionListener//Creates a class on top of the interface
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}
	public static void main ( String[] args) 
	{
		Quadratic solve = new Quadratic();
	}

}
