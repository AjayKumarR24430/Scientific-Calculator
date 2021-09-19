// abstract window toolkit - Contains all of the classes for 
// creating user interfaces and for painting graphics and images
import java.awt.*;
//mvc framework for java - provide a more sophisticated set of 
// GUI components than the earlier Abstract Window Toolkit (AWT). 
import javax.swing.*; // GUI Widget Toolkit
import java.awt.event.*;
import javax.swing.event.*;

// JFrame works like the main window where components like labels, buttons, textfields are added to create a GUI.

public class ScientificCalculator extends JFrame implements ActionListener {
	JTextField tfield;
	double temp, temp1, result, a;
	static double m1, m2;
	int k = 1, x = 0, y = 0, z = 0;
	char ch;
    // various button for the calculator
	JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, zero, clr, pow2, pow3, exp,
			fac, plus, min, div, log, rec, mul, eq, addSub, dot, mr, mc, mp,
			mm, sqrt, sin, cos, tan;
	Container cont;
    // JPanel, a part of Java Swing package, is a container that can store a group of 
    //components. The main task of JPanel is to organize components, various layouts 
    // can be set in JPanel which provide better organisation of components, 
	JPanel textPanel, buttonpanel;

	ScientificCalculator() {
        //In Java Swing, the layer that is used to hold objects is called the content pane.
        // The getContentPane() method retrieves the content pane layer so that you can add an object to it.
		cont = getContentPane();
        // set the layout of the calculator
		cont.setLayout(new BorderLayout());
		JPanel textpanel = new JPanel();
        // textfield with 25 columns
		tfield = new JTextField(25);
        // specifies the alignment of the number on the text field
		tfield.setHorizontalAlignment(SwingConstants.LEFT);
        // keylistener is notified when you change the state of the text field
		tfield.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent keyevent) {
				char c = keyevent.getKeyChar();
                // Consume function is responsible for not processing 
                // the KeyListeners code during some specific kind of events happen.
                // For example : Here i want to make a textfield in java such that it 
                // will only respond when digits are pressed, then I can use the consume 
                // method to consume 
				if (c >= '0' && c <= '9') {
				} else {
                    //Not process the keyevents which were not caused due to the pressing of digits
					keyevent.consume();
				}
			}
		});
		textpanel.add(tfield);
		buttonpanel = new JPanel();
        // layout with 8 rows, 4 columns, horizontal gap and vertical gap as 2
		buttonpanel.setLayout(new GridLayout(8, 4, 2, 2));
		boolean t = true;
		mr = new JButton("MR");
		buttonpanel.add(mr);
        // ActionListener is notified whenever you click on the button
        // or menu item. It is notified against ActionEvent.
		mr.addActionListener(this);
		mc = new JButton("MC");
		buttonpanel.add(mc);
		mc.addActionListener(this);

		mp = new JButton("M+");
		buttonpanel.add(mp);
		mp.addActionListener(this);

		mm = new JButton("M-");
		buttonpanel.add(mm);
		mm.addActionListener(this);

		b1 = new JButton("1");
		buttonpanel.add(b1);
		b1.addActionListener(this);
		b2 = new JButton("2");
		buttonpanel.add(b2);
		b2.addActionListener(this);
		b3 = new JButton("3");
		buttonpanel.add(b3);
		b3.addActionListener(this);

		plus = new JButton("+");
		buttonpanel.add(plus);
		plus.addActionListener(this);

		b4 = new JButton("4");
		buttonpanel.add(b4);
		b4.addActionListener(this);
		b5 = new JButton("5");
		buttonpanel.add(b5);
		b5.addActionListener(this);
		b6 = new JButton("6");
		buttonpanel.add(b6);
		b6.addActionListener(this);

		min = new JButton("-");
		buttonpanel.add(min);
		min.addActionListener(this);

		b7 = new JButton("7");
		buttonpanel.add(b7);
		b7.addActionListener(this);
		b8 = new JButton("8");
		buttonpanel.add(b8);
		b8.addActionListener(this);
		b9 = new JButton("9");
		buttonpanel.add(b9);
		b9.addActionListener(this);

		mul = new JButton("*");
		buttonpanel.add(mul);
		mul.addActionListener(this);

		eq = new JButton("=");
		buttonpanel.add(eq);
		eq.addActionListener(this);

		zero = new JButton("0");
		buttonpanel.add(zero);
		zero.addActionListener(this);

		addSub = new JButton("+/-");
		buttonpanel.add(addSub);
		addSub.addActionListener(this);

		div = new JButton("/");
		div.addActionListener(this);
		buttonpanel.add(div);

		dot = new JButton(".");
		buttonpanel.add(dot);
		dot.addActionListener(this);

		sin = new JButton("SIN");
		buttonpanel.add(sin);
		sin.addActionListener(this);

		cos = new JButton("COS");
		buttonpanel.add(cos);
		cos.addActionListener(this);

		tan = new JButton("TAN");
		buttonpanel.add(tan);
		tan.addActionListener(this);

		rec = new JButton("1/x");
		buttonpanel.add(rec);
		rec.addActionListener(this);

		sqrt = new JButton("Sqrt");
		buttonpanel.add(sqrt);
		sqrt.addActionListener(this);

		log = new JButton("log");
		buttonpanel.add(log);
		log.addActionListener(this);

		fac = new JButton("n!");
		fac.addActionListener(this);
		buttonpanel.add(fac);

		pow2 = new JButton("x^2");
		buttonpanel.add(pow2);
		pow2.addActionListener(this);

		pow3 = new JButton("x^3");
		buttonpanel.add(pow3);
		pow3.addActionListener(this);

		exp = new JButton("Exp");
		exp.addActionListener(this);
		buttonpanel.add(exp);

		clr = new JButton("AC");
		buttonpanel.add(clr);
		clr.addActionListener(this);
        // positions of button and text panels
		cont.add("Center", buttonpanel);
		cont.add("North", textpanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

    // function to calculate the square root of a number

    public static double squareRoot(double num)   
    {  
        //temporary variable  
        double t;  
        double sqrtroot=num/2;  
        do{  
            t=sqrtroot;  
            sqrtroot=(t+(num/t))/2;  
        }while((t-sqrtroot)!= 0);  
        return sqrtroot;  
    }  

    //function to calculate the power 

    public static double power(double base, double exp)  
    {  
        double power = 1;  
        // if exponent power is negative
        if(exp<0){
            for (int i = 1; i <= (-exp); i++)   
                //calculates power  
                power = power * (1/base); 
        }
        else{
            //increment the value of i after each iteration until the condition becomes false  
            for (int i = 1; i <= exp; i++)   
            //calculates power  
            power = power * base;  
        }
        //returns power  
        return power;  
    }  

    //calculate sine value
    public static double calculateSine(double radian){
        double sine = 0;
        double i,j;
        // Maclaurin expansion of sine series:
        // sin(x)= x - x^3/3! + x^5/5!-....
        for(i=0,j=0;i<20;i=i+2,j++)
        {
            sine +=power(-1,j)*power(radian,i+1)/fact(i+1);
        }
        return sine;
    }

    // calculate cosine value
    public static double calculateCosine(double radian){
        double cosine = 0;
        double i,j;
        // Maclaurin expansion of cosine series:
        // cos(x)= 1 - x^2/2! + x^4/4!- x^6/6! + ....
        for(i=0,j=0;i<20;i=i+2,j++)
        {
            cosine +=power(-1,j)*power(radian,i)/fact(i);
        }
        return cosine;
    }

	// log10(x) = ln(x)/ln10

	public static double log10( double x ) {
		double LN10 = 2.3025850929940456840179914546844;
    	return ln(x) / LN10;    
	}

	public static double ln(double x)
	{
		double old_sum = 0.0;
		double xmlxpl = (x - 1) / (x + 1);
		double xmlxpl_2 = xmlxpl * xmlxpl;
		double denom = 1.0;
		double frac = xmlxpl;
		double term = frac;                 // denom start from 1.0
		double sum = term;

		while ( sum != old_sum )
		{
			old_sum = sum;
			denom += 2.0;
			frac *= xmlxpl_2;
			sum += frac / denom;
		}
		return 2.0 * sum;
	}

	public void actionPerformed(ActionEvent e) {
        // getActionCommand() gives you a String representing the action command
		String s = e.getActionCommand();
        // appending the input numbers and operations to the textfield
		if (s.equals("1")) {
			if (z == 0) {
				tfield.setText(tfield.getText() + s);
			} else {
				tfield.setText("");
				tfield.setText(tfield.getText() + s);
				z = 0;
			}
		}
		if (s.equals("2")) {
			if (z == 0) {
				tfield.setText(tfield.getText() + "2");
			} else {
				tfield.setText("");
				tfield.setText(tfield.getText() + "2");
				z = 0;
			}
		}
		if (s.equals("3")) {
			if (z == 0) {
				tfield.setText(tfield.getText() + "3");
			} else {
				tfield.setText("");
				tfield.setText(tfield.getText() + "3");
				z = 0;
			}
		}
		if (s.equals("4")) {
			if (z == 0) {
				tfield.setText(tfield.getText() + "4");
			} else {
				tfield.setText("");
				tfield.setText(tfield.getText() + "4");
				z = 0;
			}
		}
		if (s.equals("5")) {
			if (z == 0) {
				tfield.setText(tfield.getText() + "5");
			} else {
				tfield.setText("");
				tfield.setText(tfield.getText() + "5");
				z = 0;
			}
		}
		if (s.equals("6")) {
			if (z == 0) {
				tfield.setText(tfield.getText() + "6");
			} else {
				tfield.setText("");
				tfield.setText(tfield.getText() + "6");
				z = 0;
			}
		}
		if (s.equals("7")) {
			if (z == 0) {
				tfield.setText(tfield.getText() + "7");
			} else {
				tfield.setText("");
				tfield.setText(tfield.getText() + "7");
				z = 0;
			}
		}
		if (s.equals("8")) {
			if (z == 0) {
				tfield.setText(tfield.getText() + "8");
			} else {
				tfield.setText("");
				tfield.setText(tfield.getText() + "8");
				z = 0;
			}
		}
		if (s.equals("9")) {
			if (z == 0) {
				tfield.setText(tfield.getText() + "9");
			} else {
				tfield.setText("");
				tfield.setText(tfield.getText() + "9");
				z = 0;
			}
		}
		if (s.equals("0")) {
			if (z == 0) {
				tfield.setText(tfield.getText() + "0");
			} else {
				tfield.setText("");
				tfield.setText(tfield.getText() + "0");
				z = 0;
			}
		}
        // AC - All Clear
		if (s.equals("AC")) {
			tfield.setText("");
			x = 0;
			y = 0;
			z = 0;
		}
		if (s.equals("log")) {
			if (tfield.getText().equals("")) {
				tfield.setText("");
			} else {
				a = log10(Double.parseDouble(tfield.getText()));
				tfield.setText("");
				tfield.setText(tfield.getText() + a);
            }
		}
		if (s.equals("1/x")) {
			if (tfield.getText().equals("")) {
				tfield.setText("");
			} else {
				a = 1 / Double.parseDouble(tfield.getText());
				tfield.setText("");
				tfield.setText(tfield.getText() + a);
			}
		}
		if (s.equals("Exp")) {
			if (tfield.getText().equals("")) {
				tfield.setText("");
			} else {
                // value of Euler Number
                double euler = 2.71828d;
				a = power(euler, Double.parseDouble(tfield.getText()));
				tfield.setText("");
				tfield.setText(tfield.getText() + a);
			}
		}
		if (s.equals("x^2")) {
			if (tfield.getText().equals("")) {
				tfield.setText("");
			} else {
				a = power(Double.parseDouble(tfield.getText()),2);
				tfield.setText("");
				tfield.setText(tfield.getText() + a);
			}
		}
		if (s.equals("x^3")) {
			if (tfield.getText().equals("")) {
				tfield.setText("");
			} else {
				a = power(Double.parseDouble(tfield.getText()), 3);
				tfield.setText("");
				tfield.setText(tfield.getText() + a);
			}
		}
		if (s.equals("+/-")) {
			if (x == 0) {
				tfield.setText("-" + tfield.getText());
				x = 1;
			} else {
				tfield.setText(tfield.getText());
			}
		}
        //decimal
		if (s.equals(".")) {
			if (y == 0) {
				tfield.setText(tfield.getText() + ".");
				y = 1;
			} else {
				tfield.setText(tfield.getText());
			}
		}
		if (s.equals("+")) {
			if (tfield.getText().equals("")) {
				tfield.setText("");
				temp = 0;
				ch = '+';
			} else {
                // storing the operands in temporary variables
				temp = Double.parseDouble(tfield.getText());
				tfield.setText("");
				ch = '+';
				y = 0;
				x = 0;
			}
			tfield.requestFocus();
		}
		if (s.equals("-")) {
			if (tfield.getText().equals("")) {
				tfield.setText("");
				temp = 0;
				ch = '-';
			} else {
				x = 0;
				y = 0;
                // storing the operands in temporary variables
				temp = Double.parseDouble(tfield.getText());
				tfield.setText("");
				ch = '-';
			}
			tfield.requestFocus();
		}
		if (s.equals("/")) {
			if (tfield.getText().equals("")) {
				tfield.setText("");
				temp = 1;
				ch = '/';
			} else {
				x = 0;
				y = 0;
                // storing the operands in temporary variables
				temp = Double.parseDouble(tfield.getText());
				ch = '/';
				tfield.setText("");
			}
			tfield.requestFocus();
		}
		if (s.equals("*")) {
			if (tfield.getText().equals("")) {
				tfield.setText("");
				temp = 1;
				ch = '*';
			} else {
				x = 0;
				y = 0;
                // storing the operands in temporary variables
				temp = Double.parseDouble(tfield.getText());
				ch = '*';
				tfield.setText("");
			}
			tfield.requestFocus();
		}
		if (s.equals("MC")) {
			m1 = 0;
			tfield.setText("");
		}
		if (s.equals("MR")) {
			tfield.setText("");
			tfield.setText(tfield.getText() + m1);
		}
		if (s.equals("M+")) {
			if (k == 1) {
				m1 = Double.parseDouble(tfield.getText());
				k++;
			} else {
				m1 += Double.parseDouble(tfield.getText());
				tfield.setText("" + m1);
			}
		}
		if (s.equals("M-")) {
			if (k == 1) {
				m1 = Double.parseDouble(tfield.getText());
				k++;
			} else {
				m1 -= Double.parseDouble(tfield.getText());
				tfield.setText("" + m1);
			}
		}
		if (s.equals("Sqrt")) {
			if (tfield.getText().equals("")) {
				tfield.setText("");
			} else {
				a = squareRoot(Double.parseDouble(tfield.getText()));
				tfield.setText("");
				tfield.setText(tfield.getText() + a);
			}
		}
		if (s.equals("SIN")) {
			if (tfield.getText().equals("")) {
				tfield.setText("");
			} else {
                double val = Double.parseDouble(tfield.getText());
                if(val>360 && val%360!=0)
                    val = val % 360;
                if(val>360 && val%360==0)
                    val = 360;
                double degree=Math.toRadians(val);
                a=(calculateSine(degree)); 
				tfield.setText("");
				tfield.setText(tfield.getText() + a);
			}
		}
		if (s.equals("COS")) {
			if (tfield.getText().equals("")) {
				tfield.setText("");
			} else {
                double val = Double.parseDouble(tfield.getText());
                if(val>360 && val%360!=0)
                    val = val % 360;
                if(val>360 && val%360==0)
                    val = 360;
				double degree=Math.toRadians(val);
                a=(calculateCosine(degree)); 
				tfield.setText("");
				tfield.setText(tfield.getText() + a);
			}
		}
		if (s.equals("TAN")) {
			if (tfield.getText().equals("")) {
				tfield.setText("");
			} else {
				double degree=Math.toRadians(Double.parseDouble(tfield.getText()));
                a=(calculateSine(degree)/calculateCosine(degree)); 
				tfield.setText("");
				tfield.setText(tfield.getText() + a);
			}
		}
		if (s.equals("=")) {
			if (tfield.getText().equals("")) {
				tfield.setText("");
			} else {
                // after equal button is pressed, do the operations based on the operators
                // using the temp stored variables
                // storing the operands in temporary variables
				temp1 = Double.parseDouble(tfield.getText());
				switch (ch) {
				case '+':
					result = temp + temp1;
					break;
				case '-':
					result = temp - temp1;
					break;
				case '/':
					result = temp / temp1;
					break;
				case '*':
					result = temp * temp1;
					break;
				}
				tfield.setText("");
				tfield.setText(tfield.getText() + result);
				z = 1;
			}
		}
		if (s.equals("n!")) {
			if (tfield.getText().equals("")) {
				tfield.setText("");
			} else {
				a = fact(Double.parseDouble(tfield.getText()));
				tfield.setText("");
				tfield.setText(tfield.getText() + a);
			}
		}
		tfield.requestFocus();
	}

    // function to compute the factorial of a number

	public static double fact(double x) {
		int er = 0;
		if (x < 0) {
			er = 20;
			return 0;
		}
		double i, s = 1;
		for (i = 2; i <= x; i += 1.0)
			s *= i;
		return s;
	}

	public static void main(String args[]) {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
		}
		ScientificCalculator f = new ScientificCalculator();
		f.setTitle("Scientific Calculator");
        // pack(): sizes the frame so that all its contents are at or above their preferred sizes
		f.pack();
		f.setVisible(true);
	}
}