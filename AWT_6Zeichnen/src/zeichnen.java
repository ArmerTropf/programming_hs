import java.awt.*;
import java.awt.event.*;
import java.util.Vector;



class zeichnen <E> extends Frame
{
	Vector<Point> myVec = new Vector<Point>(50,50);
		
	
	Point erster = new Point();
	Point zweiter = new Point();
	boolean boolStrich = false;
	boolean boolFreihand = false;
	Graphics go = this.getGraphics();
	
	zeichnen()
	{
		super("Fenster");
		setVisible(true);
		setSize(800,800);
		

		MenuBar myBar = new MenuBar();
		Menu myMenu = new Menu("Zeichnen");
		MenuItem myStrich = new MenuItem("Gerade Punkt");
		myStrich.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if 	( boolStrich == false )
				{
					boolStrich = true;
	

				}
				else
				{
					boolStrich = false;

				}
				
			}
		});
		
		MenuItem myGerade = new MenuItem("Gerade ziehen");
		MenuItem myWeg = new MenuItem("Freihand");
		myWeg.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if 	( boolFreihand == false )
				{
					boolFreihand = true;
					boolStrich = false;
				}
				else
				{
					boolFreihand = false;
				}
			}
			
		}
		
				
		);
		
		myMenu.add(myStrich);
		myMenu.add(myGerade);
		myMenu.add(myWeg);

		
		myBar.add(myMenu);
		
		setMenuBar(myBar);
		

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				dispose();
			}
		});
		
		addMouseListener(new MouseAdapter()
		{
			int ct = 0;
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (boolStrich)
				{	
					ct++;
					if (ct == 1)
					{
	
						erster.setLocation(e.getX(), e.getY() );
					}
					else
					{
						zweiter.setLocation(e.getX(), e.getY() );
						repaint();
						ct = 0;
	
					}
					System.out.println(e.getX() + " " + e.getY() );
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent t)
			{	
			}
		}
		);
		
		addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseDragged(MouseEvent e)
			{
				if (boolFreihand == true)
				{

					myVec.add(e.getPoint());
					
					//erster.setLocation(e.getX(), e.getY());
					//zweiter.setLocation(e.getX(), e.getY());
					repaint();
				}
			}	
		}
		
				
		);
	
	}
	
	

	
	@Override
	public void paint(Graphics g)
	{
		Insets myInsets = getInsets();
		
		if (boolStrich)
		{
			g.drawLine(erster.x, erster.y, zweiter.x, zweiter.y);
		}
		if (true)
		{
			System.out.println(myVec.size());
			for(int i = 1; i < myVec.size(); i++) {
				System.out.println(myVec.get(i).getX());
				g.drawLine(myVec.get(i-1).x, myVec.get(i-1).y, myVec.get(i).x, myVec.get(i).y);
			}
				
		}
//		g.drawLine(myInsets.left, myInsets.top, getWidth()-myInsets.right, getHeight()-myInsets.bottom);
//		g.drawRect(400, 400, 100, 100);
//		g.drawOval(402, 402, 50, 50);
		
	
		
		System.out.println(myInsets.left + " " + myInsets.top + " " +  myInsets.right + " " +  myInsets.bottom);
	}
	
	
	

	
	
	
	public static void main(String[]args)
	{
		zeichnen myWindow = new zeichnen();
		
		
//		Graphics myG = myWindow.getGraphics();
		
		
		
//		myG.drawLine(222, 123, 112, 231);
//		myG.drawLine(20, 20, 555, 555);
	
		
	}



}



