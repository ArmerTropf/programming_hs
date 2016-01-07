import java.awt.*;
import javax.swing.*;

/**
 * 
 * OptionPanel wird eingeblendet, wenn Morphing
 * aktionen stattfinden bzw. der Menüpunkt
 * ausgewählt wird
 * 
 * @author ArmerTropf
 *
 */
public class OptionPanel extends JPanel 
{
	String strActualPanel;
	JButton  button = new JButton();
	
	JSpinner spinnerX = new JSpinner();
	JSpinner spinnerY = new JSpinner();
	
	JTextField jTextField1_HISTO;
	
	public OptionPanel() 
	{
		setLayout(new FlowLayout());
		 
	}
	
	public void getModul(String WhatPanel)
	{
		this.removeAll();

		
		
		if (WhatPanel == "Rotation")
		{
			button.setText(WhatPanel);
			strActualPanel = WhatPanel;
			SpinnerNumberModel numberModel =   new SpinnerNumberModel(0.0, -360.0, 360.0,1);
			JLabel jLabelSpinnerX = new JLabel("Gradbereich:");
			spinnerX = new JSpinner(numberModel);
					
			add(jLabelSpinnerX);
			add(spinnerX);
	
		}
		else if(WhatPanel == "vergroessern/verkleinern")
		{
			button.setText(WhatPanel);
			strActualPanel = WhatPanel;
			SpinnerNumberModel numberModel1 =   new SpinnerNumberModel(0.0, 0, 10.0,0.1);

			JLabel jLabelSpinnerX = new JLabel("Multiplikator");
			spinnerX = new JSpinner(numberModel1);
						
			add(jLabelSpinnerX);
			add(spinnerX);

		}
		else if(WhatPanel == "verschieben")
		{
			button.setText(WhatPanel);
			strActualPanel = WhatPanel;
			SpinnerNumberModel numberModel1 =   new SpinnerNumberModel(0.0, -999.0, 999.0,1);
			SpinnerNumberModel numberModel2 =   new SpinnerNumberModel(0.0, -999.0, 999.0,1);
			JLabel jLabelSpinnerX = new JLabel("X-Bereich");
			
			spinnerX = new JSpinner(numberModel1);
			
			JLabel jLabelSpinnerY = new JLabel("Y-Bereich");
			spinnerY = new JSpinner(numberModel2);
						
			add(jLabelSpinnerX);
			add(spinnerX);
			add(jLabelSpinnerY);
			add(spinnerY);
		}
		else if(WhatPanel == "verzerren")
		{
			button.setText(WhatPanel);
			strActualPanel = WhatPanel;
			SpinnerNumberModel numberModel1 =   new SpinnerNumberModel(0.0, -2000.0, 2000.0,1);
			SpinnerNumberModel numberModel2 =   new SpinnerNumberModel(0.0, -2000.0, 2000.0,1);
			JLabel jLabelSpinnerX = new JLabel("X-Bereich");
			
			spinnerX = new JSpinner(numberModel1);
			
			JLabel jLabelSpinnerY = new JLabel("Y-Bereich");
			spinnerY = new JSpinner(numberModel2);
						
			add(jLabelSpinnerX);
			add(spinnerX);
			add(jLabelSpinnerY);
			add(spinnerY);
			
			System.out.println(button.getActionListeners());
//			button.removeActionListener(button.getActionListeners()[0]);
		}
		else if(WhatPanel == "Farben ersetzen")
		{			
			button.setText(WhatPanel);
			strActualPanel = WhatPanel;
			SpinnerNumberModel numberModel1 =   new SpinnerNumberModel(100.0, 0.0, 100.0,1);
			JLabel jLabelSpinnerX = new JLabel("Prozentwert: ");
			spinnerX = new JSpinner(numberModel1);
			
			JLabel jLabeljTextField1 = new JLabel("Farben: ");
			jTextField1_HISTO = new JTextField("Anzahl der Farben");
						
			add(jLabelSpinnerX);
			add(spinnerX);
			add(jLabeljTextField1);
			add(jTextField1_HISTO);
			
		}
		add(button);
	}

	public JTextField getjTextField1_HISTO() {
		return jTextField1_HISTO;
	}


}
