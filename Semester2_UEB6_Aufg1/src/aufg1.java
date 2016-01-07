/* 	UEBUNG 6 
 * 
 * 	Aufgabe 1:
 * 	Erweitern sie Ihr Programm, so dass
 * 	das MVC Muster eingehalten wird
 * 	das Polygon und der Hintergrund farbig dargestellt werden. Die Farben sollen als Argumente dem konstruktor übergeben werden können.
 * 	das aktuelle Polygon auf einen Drucker ausgedruckt werden kann.
 * 	Verwenden Sie hierfür die gleiche Methode (paint), die Sie auch für die Ausgabe auf dem Bildschirm verwenden.  
 * 
 *  MVC FEHLT 
 */
import java.awt.*;
import java.awt.event.*;


public class aufg1
{
	
	
	
	public static void main(String[]args)
	{
		//view_rect myRect = new view_rect();
		rect_controller myController = new rect_controller();
		myController.show_view();
		
	}
	
}

class rect_controller
{
	private rect_view _view;
//	private rect_model _model;
	
	rect_controller()
	{
		this._view = new rect_view("Mein Fenster", 800,800);
	}
	
	public void show_view()
	{
		_view.setVisible(true);
	}
	
	private void addListener()
	{
		this._view.setWindowClosingListener(new myWindowListener());
		
	}
	
	class myWindowListener implements WindowListener
	{

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
}

class rect_view extends Frame
{
	int anzahl  = 5000;
	int[] myX = new int[anzahl];
	int[] myY = new int[anzahl];
	boolean foundX = false;
	boolean foundY = false;
	
	
	rect_view(String myWindowName, int length, int hight)
	{
		
		
		super("myWindowName");
		setSize(length,hight);
		

//		addWindowListener(new WindowAdapter()
//		{
//			public void windowClosing(WindowEvent e)
//			{
//				dispose();
//			}
//		}
//		);
		
		for (int i = 0 ; i != myX.length-1 ; ++i)
		{
			while (foundX != true )
			{
					
				myX[i] = (int)(Math.random()*792);
				
				if (myX[i] > 8 )
				{
					foundX = true;
				}
				
			}
			while (foundY != true)
			{
				myY[i] = (int)(Math.random()*792);
				if (myY[i] > 31 )
				{
					foundY = true;
				}
			}
			foundX = false;
			foundY = false;
		}
	
	}
	
	public void setWindowClosingListener(WindowListener e)
	{
		this.addWindowListener(e);
	}
	
	public void paint(Graphics g) 
	{

		int R = 0;
		int G = 0;
		int B = 0;
		
		Insets INS = this.getInsets();
		
		System.out.println(INS.left + " " + INS.top + " " + INS.right + " " + INS.bottom );
		
		for (int i = 0 ; i != myX.length-1 ; ++i)
		{
			
			System.out.println((int)(Math.random()*255));
			
			R = (int)(Math.random()*255);
			G = (int)(Math.random()*255);
			B = (int)(Math.random()*255);
	
			g.setColor( new Color(R,G,B));
			
			try 
			{
				Thread.sleep(1000);				
				g.fillPolygon(myX, myY, i);
			
				
			
			} 
			catch (InterruptedException e) 
			{
				e.getStackTrace();
			}
			
			
		}
		
			   
		
	}
	
	
	
}


