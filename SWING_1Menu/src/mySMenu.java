import java.awt.*;
import javax.swing.*;
import java.awt.event.*;



public class mySMenu  
{
	
	public static void main(String[]args)
	{
		View myMenu = new View("Meine kleines Fenster");
//		Graphics g = myMenu.getGraphics();
		myMenu.myLineX.setLocation(200, 100);
		myMenu.myLineY.setLocation(700, 500);
		//myMenu.repaint();
		
		
	}

}

class View extends JFrame
{
	public Point myLineX = new Point();
	public Point myLineY = new Point();
	
	View(String myFramename) 
	{
				
		super(myFramename);
		
		setSize(800,800);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		JMenuBar myJMenuBar = new JMenuBar();
		JMenu myJMenu = new JMenu("Test");
		JMenuItem myJMenuItem = new JMenuItem("Fuc");
		
		myJMenuBar.add(myJMenu);
		myJMenu.add(myJMenuItem);
		setJMenuBar(myJMenuBar);
		
		JPanel myJPanel = new JPanel();
		
		JSlider myJSlider = new JSlider(JSlider.HORIZONTAL, 0,255,0);
		myJSlider.setBounds(100, 100, 150, 100);
		myJSlider.setPaintTicks(true);
		myJSlider.setPaintLabels(true);
		myJSlider.setMajorTickSpacing(50);
		myJSlider.setMinorTickSpacing(25);
		
//		myJSlider.addChangeListener(new ChangeListener()
//		{
//			
//		});
		
		add(myJSlider);
		setVisible(true);
		
		
		JOptionPane.showMessageDialog(this, "fffpf");
	}
	
//	public void paint(Graphics g)
//	{
//
////		g.drawLine((int)myLineX.getX(),(int)myLineX.getY(),(int)myLineY.getX(),(int)myLineY.getY());
//	}
	
}

