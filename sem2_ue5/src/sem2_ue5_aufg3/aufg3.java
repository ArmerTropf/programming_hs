package sem2_ue5_aufg3;


import java.awt.*;

public class aufg3 {
	
	
	public static void main(String[]args) throws InterruptedException
	{
		model_polygon myPolygon  = new model_polygon(600,600,2000);
		view myView = new view(myPolygon);
		
		
		
		for (int i = 1; i != myPolygon.getPolyCount() ;++i)
		{
			//Neue Koordinaten in Array schreiben
			myPolygon.NewKoor();
			Thread.sleep(1000);
			//Neu zeichnen
			myView.repaint();
		}
	}
}

class view extends Frame
{
	private model_polygon myPoly;
	
	public view(model_polygon myModel)
	{		
		super("Mein erstes Fenster");
		setSize(myModel.getHight(),myModel.getWidth());
		setVisible(true);
		myPoly = myModel;	
	}
	
	@Override
	public void paint(Graphics g)
	{
	    g.drawPolygon(myPoly.xpoints, myPoly.ypoints, myPoly.getPolygonCounter());		
	}
}

class model_polygon
{	
	public int xpoints[];
	public int ypoints[]; 
	
	private int hight = 800;
	private int width = 800;
	
	private int int_polygonCounter = 0;
	private int polyCount = 0;
	
	public int getHight()
	{
		return hight;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getPolygonCounter()
	{
		return int_polygonCounter;
	}
	
//	Anzahl der Koordinaten
	public int getPolyCount()
	{
		return polyCount;
	}
	
	model_polygon(int width, int hight, int polyCount)
	{
		this.width = width;
		this.hight = hight;
		
		this.polyCount = polyCount;
		xpoints = new int [polyCount] ;
		ypoints = new int [polyCount] ;
		
		//Erstinitialisierung des Arrays
		xpoints[0] = (int)(Math.random()*width);
		ypoints[0] = (int)(Math.random()*hight);
		++int_polygonCounter;

	}
	
	public void NewKoor() 
	{
		
		if (int_polygonCounter != xpoints.length)
		{
			xpoints[int_polygonCounter] = (int)(Math.random()*width);
			ypoints[int_polygonCounter] = (int)(Math.random()*hight);
			++int_polygonCounter;
		}
	}



}