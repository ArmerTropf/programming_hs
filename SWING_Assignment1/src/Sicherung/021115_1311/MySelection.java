import java.awt.Dimension;
import java.awt.Point;



public class MySelection 
{
	private Point pointSelectionFirst;
	private Point pointSelectionSecond;	

	private Dimension dimSelection = new Dimension(); 
	

	public boolean boolSelectionSet = false;
	
	public MySelection(Point firstPoint , Point secondPoint) 
	{
		pointSelectionFirst = new Point(firstPoint);
		pointSelectionSecond = new Point(secondPoint);
		setDimensionForSelection();
	}
	
	public MySelection() 
	{
		
	}
	
	public Point getPointSelectionFirst() {
		return pointSelectionFirst;
	}

	public void setPointSelectionFirst(Point pointSelectionFirst) {
		this.pointSelectionFirst = pointSelectionFirst;
	}

	public Point getPointSelectionSecond() {
		return pointSelectionSecond;
	}

	public void setPointSelectionSecond(Point pointSelectionSecond) {
		this.pointSelectionSecond = pointSelectionSecond;
	}
	
	public Dimension getDimSelection() {
		return dimSelection;
	}

	public void setDimSelection(Dimension dimSelection) {
		this.dimSelection = dimSelection;
	}
		
	public boolean isPointInSelection(Point Click)
	{

		if  (Click.getX() >= pointSelectionFirst.x && Click.getX() <= pointSelectionSecond.x  && Click.getY() >= pointSelectionFirst.y && Click.getY() <= pointSelectionSecond.y)
			return true;
		else 
			return false;
	}
	
	public void setDimensionForSelection()
	{
		
		dimSelection.setSize(pointSelectionSecond.x-pointSelectionFirst.x, pointSelectionSecond.y-pointSelectionFirst.y);	 
		boolSelectionSet = true;
	}

}
