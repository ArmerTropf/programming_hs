import java.awt.*;
import java.awt.event.*;


public class myImage {

	
	myImage()
	{
		 
		
	}
	
	public static void main(String[]args)
	{
		myImage_view _view = new myImage_view();
		
		
	}
	
}

class myImage_view extends Frame
{
	Image myImage;
	
	MediaTracker mt = new MediaTracker(this);
	
	
	
	

	myImage_view() throws Exception
	{
		super("GOOOG");
		Node test = new Node();
		
		setVisible(true);
		setSize(800, 800);
		
		Graphics g = getGraphics();
		
		final int myID = 0;
		
		myImage = getToolkit().createImage("d:/ball.gif");
		mt.addImage(myImage, 1);
		myImage = getToolkit().createImage("aü3_2.jpg");
		mt.addImage(myImage, 2);
		
		try {
			mt.waitForAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Button myButton = new Button("GO");
		
		myButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (myID == 0)
				{
					try {
						mt.waitForID(1);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		
		
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(myImage, 10, 10, 200, 200, null);
		
	}

}
