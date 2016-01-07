import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class mein 
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


class view_pics extends JFrame
{
	model_pics _model;
	
	Image myImage;
	JFileChooser JFileChooser_pics = new JFileChooser();
	
	view_pics(model_pics myModel)
	{	
		super("Hauptfenster");
		_model = myModel;
		
		setVisible(true);
		
		setSize(800,600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// <MENU>
		JMenuBar myJMenubar = new JMenuBar();
		JMenu JMenuFile = new JMenu("Datei");
		JMenuItem JMenuItem_BilderOeffnen = new JMenuItem("Öffnen");
		
		JMenuItem_BilderOeffnen.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
				
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Bilder", "gif", "png", "jpg");
				JFileChooser_pics.setCurrentDirectory(new File("D:/Sicherung/Pictures/iCloud Photos/My Photo Stream"));
				JFileChooser_pics.setFileFilter(filter);
				
				JFileChooser_pics.showOpenDialog(null);
				
				 
				System.out.println(JFileChooser_pics.getSelectedFile());
				
				
				Image test = Toolkit.getDefaultToolkit().getImage(JFileChooser_pics.getSelectedFile().getPath());
				
				myImage = test;
		
				
			}
		});
	
		
		
		
		myJMenubar.add(JMenuFile);
		JMenuFile.add(JMenuItem_BilderOeffnen);
		setJMenuBar(myJMenubar);

		// </MENU>
		
		add(new test(myImage));	
	}
	
	public void showFrame(boolean what)
	{
		this.setVisible(what);
		if (what == true)
		{

		}
	}
	
	
	
}

class test extends JComponent
{
	Image myImage;
	
	test(Image test)
	{
		myImage = test;
	}
	
	public void paintComponent(Graphics g)
	{

		System.out.println("bin drin");
		
		if ( myImage != null )
	    {
	      g.drawImage( myImage, 0, 0, null );
	      setSize( myImage.getWidth(this), myImage.getHeight(this) );
	    }
	}
	
}


class model_pics
{
	public model_pics() 
	{
	
	}
	
}