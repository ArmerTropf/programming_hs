import javax.swing.*;
import javax.swing.event.ChangeListener;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.event.*;

import java.awt.event.*;


public class myComp 
{
	public static void main(String[]args)
	{
		view_component _view = new view_component();
		
		
	}

}

class view_component extends JFrame
{
	view_component()
	{
		super("FUCK YOU");
		
		GridLayout myGrid = new GridLayout();
		
		
		
		setSize(500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		JPanel P1 = new JPanel();
		JPanel P2 = new JPanel();
		JPanel P3 = new JPanel();
		JPanel P4 = new JPanel();
		
		JButton myButton = new JButton("bums");
		JSpinner mySpinner = new JSpinner();
		
		P1.add(myButton);
		P1.add(mySpinner);
		
		add(P1, BorderLayout.NORTH);
		
		
		JProgressBar myProgressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0,5000);
		myProgressBar.setStringPainted(true);
		myProgressBar.setValue(4600);
		
		P2.add(myProgressBar);
		
		add(P2,BorderLayout.SOUTH);
		
		
		JSlider mySlider = new JSlider();
		mySlider.setMaximum(5000);
		mySlider.setMinimum(0);
		mySlider.setMajorTickSpacing(1500);
		mySlider.setMinorTickSpacing(100);
		mySlider.setPaintTicks(true);
		mySlider.setPaintLabels(true);
		mySlider.setSnapToTicks(true);
		
		mySlider.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				System.out.println(mySlider.getValue());
				myProgressBar.setValue(mySlider.getValue());
			}
		});
		
		P2.add(mySlider);
		add(P2, BorderLayout.SOUTH);
		
		
		
		
		JButton go = new JButton("Funz");
		JButton go2 = new JButton("rer.nat");
		JButton go3 = new JButton("natr");
		
		go3.addActionListener(e->{System.out.println("fuckoff");});
		
		P4.add(go);
		P4.add(go2);
		
		
		P3.setLayout(new GridLayout(1,4));
		P3.add(P4);
		P3.add(go3);
		
		
		
		
		add(P3,BorderLayout.CENTER);
		
		
		
		
		setVisible(true);
		
		
		
		
		
		
	}
	
	
}
