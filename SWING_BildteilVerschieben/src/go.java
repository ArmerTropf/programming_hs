import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;


public class go 
{
	
	public static void main(String[]args)
	{
		view_pic _view = new view_pic();
	}

	
	
	

}


class view_pic extends JFrame
{
	
	
	
	public view_pic() 
	{
		
		pic myPic = new pic();
//		setSize(myPic.picSize.width,myPic.picSize.height);
		setSize(1024,768);
		
		setLayout(new BorderLayout());
		
		JButton myButton = new JButton("OG GO");
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		
		p1.setLayout(new FlowLayout());
		p2.setLayout(new FlowLayout());
		
		
		
		p1.add(myButton);
		p2.add(myPic);
		
		add(p1, BorderLayout.NORTH);
		add(p2, BorderLayout.CENTER);
		
		
		setVisible(true);
		
		
	}
}




class pic extends JComponent implements MouseListener, MouseMotionListener
{
	public Dimension picSize =  new Dimension();
	public Image img1;
	public Image img1_Scaled;
	public Image img_SRC;
	public Image img_SRC2;
	
	Point click1 = new Point();
	Point click2 = new Point();
	
	
	PixelGrabber grab2;
	MemoryImageSource mediaSource_ImgSrc;
	MemoryImageSource mediaSource_ImgSrc2;
	
	
	pic() 
	{
//		img1 = Toolkit.getDefaultToolkit().getImage("c:/foto.jpg");
		img1 = Toolkit.getDefaultToolkit().getImage("d:/1.jpg");
		img1_Scaled = img1.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		
		ImageIcon myImageIcon = new ImageIcon(img1_Scaled);
//		picSize.setSize(myImageIcon.getIconWidth(), myImageIcon.getIconHeight());
		picSize.setSize(100, 100);

		
		
		int arr_img1_Scaled [] = new int [myImageIcon.getIconWidth()*myImageIcon.getIconHeight()];  
		
		setSize(myImageIcon.getIconWidth(), myImageIcon.getIconHeight());
		
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(img1_Scaled,0);
		mt.addImage(img1,0);
		
		
		try {
			mt.waitForAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PixelGrabber grab1 = new PixelGrabber(img1_Scaled,0,0,myImageIcon.getIconWidth(),myImageIcon.getIconHeight(),arr_img1_Scaled,0,myImageIcon.getIconWidth());
//		
//		
		try {
			grab1.grabPixels();
//			grab2.grabPixels();
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int  [] arr_test = new int[100*100];
		class_myMatrix myMatrix = new class_myMatrix(
				1, 0, 0, 
				0, 1, 0, 
				0, 0, 1
		);
		class_myVector myVector = new class_myVector();
		class_myVector myNewVector = new class_myVector();
		
		myMatrix.matrix_Mult(myMatrix);
		
		mediaSource_ImgSrc2 = new MemoryImageSource(100,100,arr_test,0,100);
		mediaSource_ImgSrc2.setAnimated(true);
		img_SRC2 = createImage(mediaSource_ImgSrc2);
		
		for (int y = 0 ; y < myImageIcon.getIconHeight() ; y++ )
		{
			for (int x = 0 ; x < myImageIcon.getIconWidth() ; x++)
			{

				myVector.arr_dblVector[0] = x;
				myVector.arr_dblVector[1] = y;
				myVector.arr_dblVector[2] = 1;

				myNewVector = myMatrix.matrix_Vector_Mult(myVector);
				
//				System.out.println(myNewVector.getX() + " >=  0 && + " + myNewVector.getY() + " >= 0 ");
				
//				System.out.println( (int)myNewVector.getY() +  " * " + myImageIcon.getIconWidth() + " + "  + myNewVector.getX());
//				System.out.println( (int)myNewVector.getY() *  myImageIcon.getIconWidth() + myNewVector.getX());
//				System.out.println(y * myImageIcon.getIconWidth() + x);
				
				
				
				if ( myNewVector.getX() >= 0 && myNewVector.getX() < 100 && myNewVector.getY() >= 0 && myNewVector.getY() < 100 )
				{
					
					int t1_neu = y * myImageIcon.getIconWidth() + x; 
					int t1_alt = (int)myNewVector.getY() * myImageIcon.getIconWidth() + (int)myNewVector.getX();
					
					System.out.println(t1_neu + "  " + t1_alt);
					arr_test[t1_neu] = arr_img1_Scaled[t1_alt];
//					System.out.println(arr_test[(int)myNewVector.getY() *  myImageIcon.getIconWidth() + (int)myNewVector.getX()]);
				}
				
				
				
				//arr_test[(y*myImageIcon.getIconWidth())+x] = arr_img1_Scaled[(y*myImageIcon.getIconWidth())+x]; 
				
				
			}
		}
		
		
		mediaSource_ImgSrc2.newPixels();
		
		
		
		
		mediaSource_ImgSrc = new MemoryImageSource(100,100,arr_img1_Scaled,0,100);
		mediaSource_ImgSrc.setAnimated(true);
		
		img_SRC = createImage(mediaSource_ImgSrc);
		
		
		
		
		
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		
		
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.drawImage(img_SRC2, 0, 0, null);
		g.drawRect(click1.x, click1.y, (click2.x - click1.x), (click2.y - click1.y));
	}
	
	@Override
	public Dimension getPreferredSize()
	{
		return picSize;
	}
	
	@Override
	public Dimension getMinimumSize()
	{
		return picSize;
	}
	
	
	
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {

		click1.setLocation(arg0.getPoint());
		System.out.println(click1.x + " " + click1.y);
	
			
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
		
		click2.setLocation(arg0.getPoint());
		System.out.println( click1.x + " " +click1.y + " -  "  + click2.x + " " + click2.y  );  
					
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		click2.setLocation(arg0.getPoint());
		System.out.println((click2.x - click1.x) + "  "  + (click2.y - click1.y) ); 
		repaint();
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	
		repaint();
	}
	
}
