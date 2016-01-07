import java.awt.*;
import java.awt.event.*;

//Ausfürliche Methode mit einer Klasse die genau eine
//Funktion implemetiert
//nicht zu empfehlen da dies genau in der Erstellung
//des Elements passieren kann

//class reagierer implements ActionListener
//{
//	public void actionPerformed(ActionEvent e)
//	{
//		System.out.println("gogoog");
//		
//	}
//}

public class awtEvent extends Frame 
{
	awtEvent()
	{
		super("MyNewFenstre");
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.out.println("Fesnter wird geschlossen...");
				dispose();
			}
		}
		);
		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				System.out.print("X="+e.getX()+ " Y="+e.getY());
				System.out.println();
			}
		}
		);
		addMouseMotionListener( new MouseMotionAdapter()
		{
			@Override
			public void mouseMoved(MouseEvent e)
			{
				System.out.println("Postition X: "+ e.getX()+ " Position Y:" +e.getY());
				
			}
			
		}
		);
		
		MenuBar myBar = new MenuBar();
		Menu myMenu = new Menu("Datei");
		MenuItem myItem = new MenuItem("Öffnen");
		myItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Menü Datei geclickt");
			}
			
		}
		);
		
		
		
		myMenu.add(myItem);
		myBar.add(myMenu);
				
		setMenuBar(myBar);
		setSize(400,400);
		setLayout(new FlowLayout());
		setVisible(true);
	}
	
	public static void main(String[]args)
	{
		awtEvent myEvent = new awtEvent();
		
		Button myButton = new Button("Mein Knopf");
		myButton.setSize(20,40);
		Button myButton2 = new Button("Zweiter Knopf");
		myButton2.setSize(40,40);
		
//Erstellung des KlickEvents mit Ausgabe
//In die Konsole
		myButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Anonyme Klaasssee");
				new awtEvent();
			}			
		}
		);
		
		myButton2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Zweiter JButton");
				
			}
			
		}
		);
			
		
		myEvent.add(myButton);
		myEvent.add(myButton2);
		myButton.setLabel("GogOGO");
		
		
		Graphics test =  myEvent.getGraphics();
		test.drawOval(50, 100, 200, 20);
		myEvent.repaint();
		
	}

}
