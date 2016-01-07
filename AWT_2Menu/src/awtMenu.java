import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class awtMenu extends Frame
{
	awtMenu()
	{
		super("GOGOGO");
//Zum Schliessen des Fensters		
		addWindowListener(new WindowAdapter () 
		{
			  public void windowClosing (final WindowEvent e) 
		      {
		        dispose();
		      }
		});		
		setMenuBar(getMenu());
		setSize(300,555);
		setVisible(true);
	}
	
	public static void main(String[]args)
	{
		awtMenu test = new awtMenu();
	}
	
	@Override
	public void paint(Graphics g)
	{
		final Insets INS = getInsets();
		g.drawLine(INS.left,INS.top,54,60);
	}
	
	MenuBar getMenu()
	{
		MenuBar myMenuBar = new MenuBar();
//Eigene Menüspalte Datei erstellen
		Menu myMenuDatei = new Menu("Datei");
		MenuItem mItemBeenden = new MenuItem("ITEM Beenden");

		myMenuDatei.add(mItemBeenden);
//Eigene Menüspalte Bearbeiten erstellen
		Menu myMenuBearbeiten = new Menu("Bearbeiten");
		Menu uM1_Bearbeiten = new Menu("weitere Einstellungen");
		 MenuItem mItemEinstellungen = new MenuItem("Einstellungen");
		 uM1_Bearbeiten.add(mItemEinstellungen);
		 MenuItem Item_Um1_Bearbeiten_groesse = new MenuItem("Größe");
		 uM1_Bearbeiten.add(Item_Um1_Bearbeiten_groesse);
		Menu uM2_Bearbeiten = new Menu("Farbe ändern");
		 MenuItem uM2_Bearbeiten_Ansicht = new MenuItem("Ansicht");
		uM2_Bearbeiten.add(uM2_Bearbeiten_Ansicht);
		myMenuBearbeiten.add(uM1_Bearbeiten);
		myMenuBearbeiten.add(uM2_Bearbeiten);
//Menüs der Leiste hinzufügen
		myMenuBar.add(myMenuDatei);
		myMenuBar.add(myMenuBearbeiten);
		return myMenuBar;
	}
	
}
