import java.awt.*;
import java.awt.event.*;



public class AWT_Threads extends Frame 
{
	Image myImage;
	Image myImageTemp;
	int m_iHeight = 0; volatile int m_iMaxHeight = 0;
	
	AWT_Threads() throws Exception
	{
		super("Mein Fenster");	
		setSize(800,800);
		setLayout(new BorderLayout());
	
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				dispose();
			}
		}
		);
				
		myImage = getToolkit().createImage("ball.gif");
		
		MediaTracker myMT = new MediaTracker(this);
		myMT.addImage(myImage,1);
		myMT.waitForAll();
		
		//setBounds(50,50,myImage.getWidth(this),myImage.getHeight(this));
		setVisible(true);
	}
	
	
	//DoubleBuffer!!!!
	@Override
	public void update(Graphics g)
	{
		if (myImageTemp == null) 
		{
			myImageTemp = createImage(getWidth(),getHeight());
		}
			Graphics bufferGraphics = myImageTemp.getGraphics();
			bufferGraphics.clearRect(0,0,getWidth(),getHeight());
			paint(bufferGraphics);
			g.drawImage(myImageTemp,0,0,this);
	}
	@Override
	public void paint(Graphics g)
	{
		final Insets INS = getInsets();
		if (m_iMaxHeight == 0)
		m_iMaxHeight = getHeight()-INS.top-INS.bottom-myImage.getHeight(this);
		
		
		g.drawImage(myImage,INS.left,INS.top+m_iHeight,this);
		g.drawImage(myImage,INS.left+myImage.getWidth(this),
		INS.top+m_iMaxHeight-m_iHeight,this);
		
		
	}
	
	public void anime() throws Exception 
	{
		while (true) 
		{
			for(m_iHeight = m_iMaxHeight;m_iHeight > 0 ;--m_iHeight) 
			{
				Thread.sleep(2);
				repaint();
			}
			for(m_iHeight = 0;m_iHeight < m_iMaxHeight;++m_iHeight) 
			{
				Thread.sleep(2);
				repaint();
			}
		}
	}
	
	
	public static void main(String[]args) throws Exception
	{
		AWT_Threads myThread = new AWT_Threads();
		Panel myPanelEast = new Panel();
		final Button myButton = new Button("Knopf");
		myPanelEast.add(myButton);
		
		
		
		
		myButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				
				for (int i = 0 ; i != 400 ; ++i)
				{
					System.out.println(myButton.getAlignmentX());
			
				}
			}
		}
		);
		
		myThread.add(BorderLayout.EAST,myPanelEast);
		myThread.anime();
		
	}
	
	
}