import java.awt.*;
import java.awt.event.*;

public class myElement 
{
	public static void main(String[]args)
	{
		view_myButton _view = new view_myButton();
	
	}
}

class view_myButton extends Frame
{
	view_myButton()
	{
		super("gogogo");
		setSize(500,500);
	
		setLayout(null);
		
		Panel myPanel = new Panel(new FlowLayout());
		CompBar myBar = new CompBar(20, 60);
		
		
		Dialog myDialog = new Dialog(this);
		myDialog.setSize(200, 200);
		myDialog.setLayout(new FlowLayout());
		myDialog.setModal(true);
		
		Button myButton = new Button("schliessen");
		myButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				myDialog.dispose();
			}
		}
		);
		myDialog.add(myButton);
		
		myBar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				myDialog.setVisible(true);
				
				
			}
		}
		);
		
		addMouseMotionListener(new MouseAdapter()
		{
			@Override
			public void mouseMoved(MouseEvent e)
			{
				
				System.out.println(e.getX() + " " + myPanel.getY() + " " + myBar.getWidth() + " " +myBar.getHeight());
				myPanel.setBounds(e.getX(), myPanel.getY(),myBar.getWidth(),myBar.getHeight());
			}		
		}
		);
		
		myPanel.add(myBar);
		myPanel.setBounds(400, 250, 60, 20);
		add(myPanel);
		setVisible(true);	
		
		
		class myBallAnimate
		{
			
			myBallAnimate()
			{
				
			}
		}
	}
	
	public void paint(Graphics g)
	{
		
	}
}


class CompBar extends Component
{
	Dimension myDim;
	ActionListener actionListener; 
	
	CompBar(int height, int width)
	{
		myDim = new Dimension(width, height);
		
		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				myPressedMouse();
			}
		});
	}
		
	
	
	@Override
	public void paint(Graphics g)
	{
		g.fillRect(0, 0, myDim.width, myDim.height);
	}	
	@Override
	public Dimension getMinimumSize()
	{
		return myDim;
	}
	@Override
	public Dimension getPreferredSize()
	{
		return myDim;
	}
	
	
	
	public void addActionListener(ActionListener e)
	{
		actionListener = AWTEventMulticaster.add(actionListener, e);
	}
	
	public void myPressedMouse()
	{
		actionListener.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"goog"));
	}
	


}