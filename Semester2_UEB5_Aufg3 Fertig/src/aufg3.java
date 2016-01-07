/* UEBUNG 5
 * Aufgabe 3:
 * Schreiben Sie ein Programm, das ein Frame öffnet und ein Polygon zeichnet.
 * Nach einer kurzen Zeit soll das Polygon immer wieder zufällig erweitert und neu gezeichnet werden.
 * Dabei soll das Polygon immer im Fenster liegen.
 * 
 * 
 * ZUSATZ: Menü und Threads: Die Scheife(Zeichenen) kann mittels Menü unterbrochen werden
 */

import java.awt.*;
import java.awt.event.*;


public class aufg3
{
	public static void main(String[]args)
	{
		view_rect myRect = new view_rect();


		
		Thread test = new Thread(myRect);
		test.start();
	}
	
}

class view_rect extends Frame implements Runnable
{
	int anzahl  = 5000;
	int[] myX = new int[anzahl];
	int[] myY = new int[anzahl];
	boolean foundX = false;
	boolean foundY = false;
	int myPolyCount = 0;
	public boolean myInterrupt = true;
	Insets INS;
	


	view_rect()
	{		
		super("Fenster");
		setSize(800,800);
		setVisible(true);
			
		MenuBar myMenuBar = new MenuBar();
		Menu myMenu = new Menu ("BUMS");
		MenuItem myMenuItem= new MenuItem("Aus");
		
		myMenuBar.add(myMenu);
		myMenu.add(myMenuItem);
		
		myMenuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e )
			{
				myInterrupt = false;
				System.out.println(myInterrupt);
			}
			
		});
		setMenuBar(myMenuBar);
		
		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				myInterrupt = false;
				dispose();
			}
		}
		);
		
		INS = this.getInsets();
		
		System.out.println(INS.left + " " + INS.top + " " + INS.right + " " + INS.bottom);
		
		for (int i = 0 ; i != myX.length-1 ; ++i)
		{
			while (foundX != true )
			{
					
				myX[i] = (int)(Math.random()*792);
				
				if (myX[i] > INS.left )
				{
					foundX = true;
				}
				
			}
			while (foundY != true)
			{
				myY[i] = (int)(Math.random()*792);
				if (myY[i] > INS.top )
				{
					foundY = true;
				}
			}
			foundX = false;
			foundY = false;
		}
		
		
		
	}
	
	public void run()
	{
		System.out.println("BIN DA");
					
			for (int i = 0 ; i != myX.length-1 && myInterrupt == true ; ++i)
			{		
				try 
				{
//					System.out.println(myX[i] + " " +myInterrupt);
					Thread.sleep(1000);
					myPolyCount++;
					repaint();
			
				} 
				catch (InterruptedException e) 
				{
					e.getStackTrace();
				}
			}
		System.out.println("BIN RAUS");
	}
	
	public void paint(Graphics g)
	{
		g.drawPolygon(myX, myY, myPolyCount);
	}
	
}


