import java.awt.*;

/**
 * 
 * Klasse beinhaltet Informationen über die Auswahl
 * 
 * @author ArmerTropf
 *
 */
public class Selection 
{
	View_assignment _view;
	
	public Point pointSelectionFirst = new Point();
	public Point pointSelectionSecond = new Point();	
	
	public int[] intArrCopiedArea = null;
//	private int copiedAreaScan = 0;

	public Dimension dimSelection = new Dimension(); 
	

	public boolean boolSelectionSet = false;
	public boolean boolSelectionCopied = false;
	
	//Aktuelle Position der Maus um bei einfügen Variabel zu sein
	Point pointPositionForPastingFirst;
	Point pointPositionForPastingSecond;
	
	
	public Selection(Point firstPoint , Point secondPoint) 
	{
		pointSelectionFirst = new Point(firstPoint);
		pointSelectionSecond = new Point(secondPoint);
		
		setDimensionForSelection();
	}
	
	public Selection(View_assignment view) 
	{
		this(new Point(0, 0), new Point(0, 0));
		_view = view;
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

	public void copySelection(int[] arrActualImage, int actualimageWidth, int actualimageHeight) {
		
		final int x1 = pointSelectionFirst.x;
		final int y1 = pointSelectionFirst.y;
		final int x2 = pointSelectionSecond.x;
		final int y2 = pointSelectionSecond.y;

		//Auswahl Breite und Höhe ermitteln
		final int intSelectionWidth  = (x2 - x1);
        final int intSelectionHeight = (y2 - y1);
        
        //Arraygröße für die Auswahl initialisieren
        intArrCopiedArea = new int[intSelectionWidth * intSelectionHeight];
        
        
        
        for (int y = 0 ; y < actualimageHeight; y++)
		{
			for (int x = 0 ; x < actualimageWidth ; x++)
			{
                //Liegt der Punkt in der Selection?
				if(x >= x1 && x < x2 && y >= y1 && y < y2) 
                {
                	//Aktueller Bildpunkt der Auswahl in neues Array schreiben
					intArrCopiedArea[(y - y1) * intSelectionWidth +(x - x1)] = arrActualImage[y * actualimageWidth + x];
                }
            }
        }
        boolSelectionCopied = true;

      pointPositionForPastingFirst = pointSelectionFirst;
      pointPositionForPastingSecond = pointSelectionSecond;

        
	}

	public int [] PasteSelection(int [] ActualIntImageArr ,int ActualIntImageArrWidth, int ActualIntImageArrHeight ) 
	{
		
		final int x1 = pointPositionForPastingFirst.x;
		final int y1 = pointPositionForPastingFirst.y;
		
		final int x2 = pointPositionForPastingSecond.x;
		final int y2 = pointPositionForPastingSecond.y;
		
		final Dimension dimSecetion = new Dimension(x2-x1,y2-y1); 
		
        int[] intArrNewImage = new int[ActualIntImageArr.length];
        
        
        for (int y = 0; y < ActualIntImageArrHeight; y++) 
        {
            for (int x = 0; x < ActualIntImageArrWidth; x++) 
            {
            	if (x >= 0 && x < ActualIntImageArrWidth && y >= 0 && y < ActualIntImageArrHeight  ) 
                {
            		if(x > x1 && x < x2 && y > y1 && y < y2) 
                	{

            			intArrNewImage[y * ActualIntImageArrWidth + x] = intArrCopiedArea[(y-y1) * dimSecetion.width + (x-x1)];

                    } 
            		else
            			intArrNewImage[y * ActualIntImageArrWidth + x] = ActualIntImageArr[y * ActualIntImageArrWidth + x];
                }
            }
        }
       
        return intArrNewImage;
	}
	

}
