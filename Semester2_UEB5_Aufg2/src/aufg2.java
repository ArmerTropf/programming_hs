
/*
 * Aufgabe 2:
 * Schreiben Sie ein Programm, das in einem Frame eine Funktion zeichnet. 
 * Die Funktion soll als Interface dem Konstruktor Ihrer Klasse mitgegeben werden. 
 * Zusätzlich werdern zwei float Werte mitgegeben, die den Wertebereich der zu zeichnenden Funktion beschreiben. 
 * Testen Sie Ihr Programm mit den Funktionen f(x)=x² und f(x)=x³ jeweils in dem Wertebereich -0.1 ... 0.1 und 10.0 ... -10.0
*/

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import java.math.*;

public class aufg2 
{
	public static void main(String[]args)
	{
		myWindow<Point> test = new myWindow<Point>();
		test.parabel();
		
		
	}
}

class myWindow <T> extends Frame
{
	int [] myX = new int[21];
	int [] myY = new int[21];
	
	int myOffset = 400;
	int myTeiler = 2;
	
	myWindow()
	{
		super("Mein Fenster");
		setSize(800,800);
		setVisible(true);
		
		
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				dispose();
			}
			
		});
	}
	
	public void parabel()
	{
		
		
		int x = 10;
		int y;
		
		for (int i = 0  ; i != 21; i++)
		{
			myX[i] = i-x;
			myY[i] = (i-x)*(i-x);
	
		}
		
		
		for (int i = 0 ; i != 21; ++i)
		{
			System.out.println(myX[i] + " " + myY[i]);
		}		
	}
	
	public void paint(Graphics g)
	{
		
		g.drawLine(0,0,200,100);
	}
	
}

interface Funktionen
{
	void parabel();
	
}
