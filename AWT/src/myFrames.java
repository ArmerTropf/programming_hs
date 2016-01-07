import java.awt.*;

class myFrames extends Frame 
{
	public myFrames()
	{
		super("Mein Tolles Fenster");
		setSize(300,400);
		setVisible(true);
	}
	
	public void paint(Graphics g)
	{

	}
	
	public static void main(String[]args)
	{
		myFrames frame = new  myFrames();
//		frame.dispose();
	}
}


