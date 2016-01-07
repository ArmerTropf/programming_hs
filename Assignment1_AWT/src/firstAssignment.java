import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.*;


public class firstAssignment
{
	public static void main(String[]args)
	{
		controller_pics _controller = new controller_pics();
	}
}

class controller_pics
{
	public controller_pics() 
	{
		model_pics _model = new model_pics();
		view_pics _view = new view_pics(_model);
		
//		_view.showFrame(true);
	}
	
	
}


class view_pics extends Frame
{
	model_pics _model;
	
	Image myImage;
	JFileChooser JFileChooser_pics = new JFileChooser();

	tester [] arr_tester = new tester[30];
	int tester_arr_counter = 0;
	
	double double_picHeightScaler = 200.0;
	Dimension dim_scaled_pic = new Dimension(0,0);
	
	Panel test = new Panel();
	
	
	view_pics(model_pics myModel)
	{	
		super("Hauptfenster");
		_model = myModel;
		setVisible(true);
		setSize(800,600);
		setLayout(new FlowLayout());
		
		
		
		
		
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				dispose();
			}
			
		});

		// <MENU>
		MenuBar myMenubar = new MenuBar();
		Menu MenuFile = new Menu("Datei");
		MenuItem MenuItem_BilderOeffnen = new MenuItem("Öffnen");
		
		MenuItem_BilderOeffnen.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{

				
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Bilder", "gif", "png", "jpg");
				JFileChooser_pics.setCurrentDirectory(new File("D:/Sicherung/Pictures/iCloud Photos/My Photo Stream"));
				JFileChooser_pics.setFileFilter(filter);
	
				if (JFileChooser_pics.showOpenDialog(null) == JFileChooser.APPROVE_OPTION )
				{
					ImageIcon icon = new ImageIcon(JFileChooser_pics.getSelectedFile().getPath());
					Image img = icon.getImage();
					
					tester newObj = new tester(img,400);
					newObj.setImagePath(JFileChooser_pics.getSelectedFile().getPath());
					arr_tester[tester_arr_counter++] = newObj;
				}
				
				 
				System.out.println(JFileChooser_pics.getSelectedFile());
			
				show_mini_Pics();

				
				
			}
		});
	
		
		
		
		myMenubar.add(MenuFile);
		MenuFile.add(MenuItem_BilderOeffnen);
		setMenuBar(myMenubar);
		
		
//		test.setBounds(20, 20, 400, 400);
		test.setLayout(new FlowLayout());
add(test);
		// </MENU>
		
	
	}
	
	public void showFrame(boolean what)
	{
		this.setVisible(what);
		if (what == true)
		{

		}
	}
	
	public void show_mini_Pics()
	{
//		Image currentImage;
//		ImageIcon imageIcon_newIcon;
//		test.add(arr_tester[0]);
		
//		repaint();
		
		
		for (int i = 0 ; i < tester_arr_counter; i++)
		{
			test.add(arr_tester[i]);
			test.repaint();
		}
		
	}
	
}

class tester extends JLabel
{
	private Image image_myOriginalImage;
	private String string_myImage_Path;
	private Image image_myScaledImage;
	
	private Dimension dim_scaled_pic;
	
	tester(Image newPic, double scaledHeight)
	{
//		super(new Icon(new ImageIcon()));
		
		
		image_myOriginalImage = newPic;	
		scaleImage(scaledHeight);
		
	}

	public void setImage(Image bam)
	{
		image_myOriginalImage = bam;
	}
	
	public Image getImage()
	{
		return image_myOriginalImage;
	}
	
	public void setImagePath(String imagePath)
	{
		string_myImage_Path = imagePath;
	}
	
	public String getImagePath()
	{
		return string_myImage_Path;
	}
	
	
	private void scaleImage(double scaledHeight)
	{
		ImageIcon imageIcon_newIcon;
		imageIcon_newIcon = new ImageIcon(image_myOriginalImage);
		double pic_width = imageIcon_newIcon.getIconWidth()*(scaledHeight/imageIcon_newIcon.getIconHeight());
		double pic_height = scaledHeight;
		
		System.out.println( pic_width + " " + pic_height);
		
//		dim_scaled_pic.setSize(pic_width,pic_height);
//		image_myScaledImage = image_myOriginalImage.getScaledInstance((int)dim_scaled_pic.getHeight(), (int)dim_scaled_pic.getWidth(), Image.SCALE_SMOOTH);
	
	}
	
	@Override
	public void paint(Graphics g)
	{
		System.out.println("im Panel");
		
		if ( image_myOriginalImage != null )
	    {
	      g.drawImage( image_myOriginalImage, 0, 0, null );
	      
	      
	      setSize( image_myOriginalImage.getWidth(this), image_myOriginalImage.getHeight(this) );
	    }
	}

}


//class ImageComponent extends Component
//{
//  private static final long serialVersionUID = 8055865896136562197L;
//
//  private BufferedImage image;
//
//  public void setImage( BufferedImage image )
//  {
//    this.image = image;
//    setPreferredSize( new Dimension(image.getWidth(), image.getHeight()) );
//    repaint();
//    invalidate();
//  }
//
//  @Override
//  public void paint(Graphics g)
//  {
//    if ( image != null )
//      g.drawImage( image, 0, 0, this );
//  }
//}


class model_pics
{
	
	
	
	public model_pics() 
	{
	
	}
	
}
