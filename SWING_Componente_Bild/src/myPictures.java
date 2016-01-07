import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;


public class myPictures extends JComponent implements MouseListener 
{
	private String str_absolutPath;
	private Image img_myOriginalImage;
	private Image img_myScaledBigImage;
	private Image img_myScaledPreviewImage;
	
	private Dimension dim_myOriginalImageResolution = new Dimension();
	private double dbl_myOriginalImageResolutionScaleFactor;
	
	private Dimension dim_myScaledBigImageResolution  = new Dimension();
	private double dbl_myScaledBigImageResolutionScaleFactor;
	
	private Dimension dim_myScaledPreviewImageResolution  = new Dimension();
	private double dbl_myScaledPreviewImageScaleFactor;
	
	
	private Dimension dim_size;
	private boolean bool_showBigImage;
	int int_maxPreviewHeight, int_maxBigHeight;
	
	
	myPictures(String imagePath, int MaxPreviewHeight, int MaxPreviewWidth, int MaxBigHeight, int MaxBigWidth, boolean showBigImage)
	{
		dim_size = new Dimension();
		bool_showBigImage = showBigImage;
		int_maxPreviewHeight = MaxPreviewHeight;
		int_maxBigHeight = MaxBigHeight;
		str_absolutPath = imagePath;
		
		img_myOriginalImage = Toolkit.getDefaultToolkit().getImage(imagePath);
		
		ImageIcon imgIcon_img_myOriginalImage = new ImageIcon(img_myOriginalImage);
		dim_myOriginalImageResolution.setSize(imgIcon_img_myOriginalImage.getIconWidth(),imgIcon_img_myOriginalImage.getIconHeight());
		
		
		if (showBigImage)
		{
			getReducedSize(true);	
			img_myScaledBigImage = img_myOriginalImage.getScaledInstance(dim_myScaledBigImageResolution.width, dim_myScaledBigImageResolution.height, Image.SCALE_SMOOTH);
			ImageIcon imgIcon_img_myScaledBigImage = new ImageIcon(img_myScaledBigImage);
			dim_myScaledBigImageResolution.setSize(imgIcon_img_myScaledBigImage.getIconWidth(), int_maxBigHeight);
			setMySize(bool_showBigImage);
		}
		else
		{
			getReducedSize(false);
			img_myScaledPreviewImage = img_myOriginalImage.getScaledInstance(dim_myScaledPreviewImageResolution.width, dim_myScaledPreviewImageResolution.height, Image.SCALE_SMOOTH);
			ImageIcon imgIcon_img_myScaledPreviewImage = new ImageIcon(img_myScaledPreviewImage);
			dim_myScaledPreviewImageResolution.setSize(imgIcon_img_myScaledPreviewImage.getIconWidth(), int_maxPreviewHeight);
			setMySize(bool_showBigImage);
		}
		
	
		System.out.println("ORG width: " + dim_myOriginalImageResolution.getWidth() + " " + "height: " + dim_myOriginalImageResolution.getHeight());
		System.out.println("SCALED width: " + dim_myScaledBigImageResolution.getWidth() + " " + "height: " + + dim_myScaledBigImageResolution.getHeight());
		System.out.println("PREVIEW width: " + dim_myScaledPreviewImageResolution.getWidth() + " " + "height: " + + dim_myScaledPreviewImageResolution.getHeight());
		
		setMySize(bool_showBigImage);
		
		revalidate();
		this.addMouseListener(this);

	}
	
	public String getStr_absolutPath() {
		return str_absolutPath;
	}
		
	
	//Beide Funktionen müssen noch schön Programmiert werden
	public void setMySize(boolean bool_BigImage)
	{
		if (bool_showBigImage == true)
		{
			dim_size.setSize(dim_myScaledBigImageResolution.getWidth(), dim_myScaledBigImageResolution.getHeight());
		}
		else
		{
			dim_size.setSize(dim_myScaledPreviewImageResolution.getWidth(), dim_myScaledPreviewImageResolution.getHeight());
		}
		
		
	}

	private void getReducedSize(boolean bool_bigImage)
	{
		double dbl_smallerInPercent = 0.0;
		Dimension reduced_dimensions = new Dimension();	
		
		
		if ( bool_bigImage == true ) 
		{
			dbl_smallerInPercent = (int_maxBigHeight / dim_myOriginalImageResolution.getHeight());
			reduced_dimensions.setSize(dim_myOriginalImageResolution.getWidth()*dbl_smallerInPercent, dim_myOriginalImageResolution.getHeight());
			dim_myScaledBigImageResolution = reduced_dimensions;
		}
		else
		{
			dbl_smallerInPercent = (int_maxPreviewHeight / dim_myOriginalImageResolution.getHeight());
			reduced_dimensions.setSize(dim_myOriginalImageResolution.getWidth()*dbl_smallerInPercent, dim_myOriginalImageResolution.getHeight());
			dim_myScaledPreviewImageResolution = reduced_dimensions;
		}
			
		
	}
	
	@Override
	public Dimension getPreferredSize()
	{
		return dim_size;	
	}
	
	@Override
	public Dimension getMinimumSize()
	{
		return dim_size;
		
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		if (bool_showBigImage)
		{
			g.drawImage(img_myScaledBigImage, 0, 0, (int)dim_size.getWidth(), (int)dim_size.getHeight(), this);
		}
		else
			g.drawImage(img_myScaledPreviewImage, 0, 0, (int)dim_size.getWidth(), (int)dim_size.getHeight(), this);
	}
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("go");
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
