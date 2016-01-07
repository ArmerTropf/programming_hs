import java.awt.*;
import java.awt.event.*;


public class aufg3 
{
	public static void main(String[]args)
	{
		kreis_controller myController = new kreis_controller();
		myController.show_view();
	}
}


class kreis_model
{
	public double getMyRadius(Point CenterPoint, Point RadiusPoint)
	{
		double radius;
		radius = Math.sqrt
		( 
				( (RadiusPoint.getX() - CenterPoint.getX()) * (RadiusPoint.getX() - CenterPoint.getX()) ) +
				( (RadiusPoint.getY() - CenterPoint.getY()) * (RadiusPoint.getY() - CenterPoint.getY()) )
		
		);
		return radius;
	}
}

class kreis_controller
{
	//Deklaration von View und Model
	private kreis_view _view ;
	private kreis_model _model;
	
	//Zählvariable für das jeweilige Clicken
	int CoordClickCounter = 0;
	//Variablen für die beiden Koordinatenpaare
	Point myCoord1 = new Point();
	Point myCoord2 = new Point();
			
	//Abbruchvariable für die Nebenläufigkeit
	private boolean neben = false;
	Thread hierIstMeinThread;
	
	//Konstruktor  für den Controller
	kreis_controller()
	{
		//Instanzieren von View und Model
		_view = new kreis_view();
		_model = new kreis_model();
		//Separate Listener hinzufügen
		addListener();
	}
	
	//Funktion zum anzeigen des Fensters
	public void show_view()
	{
		_view.setVisible(true);
	}
	
	//Verschiedenste Elemente mit Listener ausstatten
	//BEACHTE: Listener werden in der View-Klasse mit einer 
	//Funktion ausgestattet, um die Funktionalität durch Hinzufügen
	//von 
	private void addListener()
	{
		_view.setItemListener_RED(new  myItemListener_GREEN());
		_view.setItemListener_GREEN(new  myItemListener_RED());
		_view.setItemListener_OnOff(new myItemListenerOnOFF());
		_view.setMouseListener(new myMouseListenermyWindow());
		_view.setWindowListener(new myWindowListener());
	}
	
	//ActionListener-Funktionalität für das MenüItem erstellen
	class myItemListener_RED implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			_view.myColor = Color.red;
			_view.repaint();
		}
	}
	
	//ActionListener-Funktionalität für das MenüItem erstellen
	class myItemListener_GREEN implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			_view.myColor = Color.green;
			_view.repaint();
		}
	}
	
	//ActionListener-Funktionalität für das MenüItem erstellen
	class myItemListenerOnOFF implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			Thread myThread = new Thread(_view);
			hierIstMeinThread = myThread;
			
			if (_view.neben == false)
				{
					_view.neben = true;
					myThread.start();
				}
				else if (_view.neben == true)
				{
					_view.neben = false;					
				}
		}
	}
	
	//MouseAdapter-Funktionalität für das Fenster erstellen
	class myMouseListenermyWindow extends MouseAdapter
	{
		@Override
		public void mouseClicked (MouseEvent e)
		{
			int x,y,width,height;
			double radius;
			if (CoordClickCounter == 0 )
			{
				myCoord1.setLocation(e.getX(), e.getY());
				CoordClickCounter++;
			}
			else
			{
				myCoord2.setLocation(e.getX(), e.getY());
				CoordClickCounter = 0;
				radius = _model.getMyRadius(myCoord1, myCoord2);
				
				_view.x = (int)(myCoord1.getX()-radius);
				_view.y = (int)(myCoord1.getY()-radius);
				_view.width = (int)(radius*2);
				_view.height = (int)(radius*2);		
				_view.repaint();
			}
		}
	}
	
	//WindowAdapter-Funktionalität für das Fenster erstellen
	class myWindowListener extends WindowAdapter
	{
		@Override
		public void windowClosing(WindowEvent e)
		{
			_view.neben = false;
			_view.dispose();
		}
	}
	
}




class kreis_view extends Frame implements Runnable
{
	Thread test = null;
	Color myColor = Color.BLACK;

	public boolean neben = false;
	
	public int x = 0;
	public int y = 0;
	public int width = 0;
	public int height = 0;
	
	
	//Menü
	MenuBar myMenubar = new MenuBar();
	Menu myMenu = new Menu("Einstellungen");
	MenuItem myItem_red = new MenuItem("rot");
	MenuItem myItem_green = new MenuItem("grün");
	MenuItem myItem_OnOff = new MenuItem("AN/AUS");
	
	

	
	
	kreis_view ()
	{
		super("Fenster");
		setSize(800,800);
		
		myMenu.add(myItem_red);
		myMenu.add(myItem_green);
		myMenu.add(myItem_OnOff);
		myMenubar.add(myMenu);
		
		setMenuBar(myMenubar);
		
	}
	
	public void setItemListener_RED(ActionListener l)
	{
		this.myItem_red.addActionListener(l);
	}
	
	public void setItemListener_GREEN(ActionListener l)
	{
		this.myItem_green.addActionListener(l);
	}
	
	public void setItemListener_OnOff(ActionListener l)
	{
		this.myItem_OnOff.addActionListener(l);
	}
	
	public void setMouseListener(MouseAdapter l)
	{
		this.addMouseListener(l);
	}
	
	public void setWindowListener(WindowAdapter l)
	{
		this.addWindowListener(l);
	}
	public void paint(Graphics g)
	{
		if (myColor.getGreen() == 255 && myColor != null)
		{
			myColor = Color.red;
			g.setColor(myColor);
		}
		else if (myColor.getRed() == 255)
		{
			myColor = Color.green;
			g.setColor(myColor);
		}
		else
			g.setColor(myColor);
		
		
		
		g.drawOval(x, y, width, height);

	}
	
	public void run()
	{
		int i = 1;
		while (neben)
		{	
			System.out.println(i++);
			try
			{
				repaint();
				Thread.sleep(200);
			}
			catch ( Exception e)
			{
				
			}
		}
		
	}
	
	
}
