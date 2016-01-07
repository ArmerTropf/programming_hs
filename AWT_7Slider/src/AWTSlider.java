import java.awt.*;
import java.awt.event.*;


public class AWTSlider
{
	public static void main(String[]args)
	{
		Control mySlider = new Control();
//		Thread t = new Thread(mySlider);
//		
//		t.start();
//		Control mySlider1 = new Control();
//		Thread t1 = new Thread(mySlider1);
//		
//		t1.start();

	}
	

}


class Control extends Frame implements Runnable
{	
	static int iRed;
	static int iGreen;
	static int iBlue;
	
	Thread t = new Thread(this);
	
	public boolean boolColorRandom = false;
	
	Scrollbar myScrollbarRed = new Scrollbar(Scrollbar.HORIZONTAL,1,1,0,255);
	Scrollbar myScrollbarGreen = new Scrollbar(Scrollbar.HORIZONTAL,1,1,0,255);
	Scrollbar myScrollbarBlue = new Scrollbar(Scrollbar.HORIZONTAL,1,1,0,255);
	
	Button myButton = new Button("Random");
		
	Control()
	{
		super("Test");
		setSize(600,600);
		setBackground(Color.red);
		setVisible(true);
//		setLayout(new FlowLayout(FlowLayout.CENTER));
		setLayout(new GridLayout(20, 2));
		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				dispose();
			}
		}
		);
		
		myScrollbarRed.addAdjustmentListener(new AdjustmentListener() 
		{
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) 
			{
				System.out.println(e.getValue());
				iRed = e.getValue();
				repaint();
			}
		});
		myScrollbarGreen.addAdjustmentListener(new AdjustmentListener() 
		{
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) 
			{
				System.out.println(e.getValue());
				iGreen = e.getValue();
				repaint();
			}
		});
		myScrollbarBlue.addAdjustmentListener(new AdjustmentListener() 
		{
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) 
			{
				System.out.println(e.getValue());
				iBlue = e.getValue();
				repaint();
			}
		});
		
		myButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("GO RUN" + " " +boolColorRandom);
				
				
				if (!boolColorRandom)
				{
					System.out.println("GO RUN");
					boolColorRandom = true;
					t.start();					
				}
				else
				{
					boolColorRandom = false; 
				}
					
				
			}
		});
		
		add(myScrollbarRed);
		add(myScrollbarGreen);
		add(myScrollbarBlue);
		add(myButton);

		
	}
	
	public void run()
	{
		myRandom zahl = new myRandom();
		
		while(boolColorRandom)
		{
			try
			{
				System.out.println("In schleife Run");
				myScrollbarRed.setValue(zahl.getMyRandom(250));
				myScrollbarGreen.setValue(zahl.getMyRandom(250));
				myScrollbarBlue.setValue(zahl.getMyRandom(250));
				iRed = zahl.getMyRandom(100);
				iGreen = zahl.getMyRandom(100);
				iBlue = zahl.getMyRandom(100);
				setBackground(new Color(iRed,iGreen,iBlue));
				repaint();
				Thread.sleep(1000);
			}
			catch (Exception e)
			{
				
			}
			
		}
		t.interrupt();
		System.out.println(t.interrupted());
	}
	
	public void paint(Graphics g)
	{
		setBackground(new Color(iRed,iGreen,iBlue));
	}
}

class myRandom
{
	public int getMyRandom(int multi)
	{
		int myInt = (int)(Math.random() * multi);
		return myInt;
	}
}