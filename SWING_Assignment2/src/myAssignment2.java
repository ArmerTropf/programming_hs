import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;




public class myAssignment2 
{
	
	
	
	
	
	public static void main(String[]args)
	{
		
		
		Image imgOriginal = Toolkit.getDefaultToolkit().getImage("d:/3.jpg");
		Image imgOriginalScaledImage = imgOriginal.getScaledInstance(800, 600, Image.SCALE_FAST);
		
		
		
		Histogram myHisto = new Histogram(imgOriginalScaledImage);
		view _view = new view(myHisto.myImage, myHisto);
		
		
	}
}

class view extends JFrame
{

	static Image imgSavedOriginalScaledImage;
	
	public view(Image go, Histogram _histo) 
	{
		
		setSize(810, 645);
		setLayout(new BorderLayout());
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		imgSavedOriginalScaledImage = go.getScaledInstance(800,	 600, Image.SCALE_FAST);
		//JPanel mit dem Bild
		picture bums = new picture(imgSavedOriginalScaledImage);
		
		bums.revalidate();
		
		
		JPanel jPanel1 = new JPanel();
		jPanel1.setSize(800, 500);
		
		JTextField stepText = new JTextField("sdsd",10);
		
		SpinnerNumberModel model = new SpinnerNumberModel(0.0,0.0 ,100.0,0.01);
		
		JSpinner jSpinner1 = new JSpinner(model);
		jSpinner1.addChangeListener(new ChangeListener() {
			
					
			@Override
			public void stateChanged(ChangeEvent e) 
			{
//				System.out.println("Source: " + jSpinner1.getValue());
				_histo.intPercentToReduceColors = (double)jSpinner1.getValue()/100;
//	    		System.out.println("Prozentwert: " + _histo.intPercentToReduceColors);
	    		_histo.loadImage(imgSavedOriginalScaledImage);
	    		stepText.setText(_histo.intAnzahlfarben + " Farben");
	    		bums.go = _histo.myImage;
	    		repaint();
			}
		});
		stepText.setText(_histo.intAnzahlfarben + " Farben");
	
		jPanel1.add(jSpinner1);
		jPanel1.add(stepText);
		
		add(bums, BorderLayout.CENTER);
		add(jPanel1, BorderLayout.NORTH);
		
		
		setVisible(true);
	}
	
	
	
}


class picture extends JPanel
{
	Image go;
	
	public picture(Image go) 
	{
		this.go = go;
	}

	@Override
	public void paintComponent(Graphics g)
	{
		g.drawImage(go, 0, 0, null);	
		System.out.println("gogoog");
	}
	
	
	
	
	
	
}
