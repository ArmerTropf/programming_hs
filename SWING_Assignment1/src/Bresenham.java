import java.awt.Point;

/**
 * Bresenham Algo für das Erstellen von:
 * Linie, Kreis und gefüllter Kreis
 * 
 * Leider ist es mir nicht möglich gewesen einen Farverlauf
 * in den Bresenham-Kreis zu bekommen
 * 
 * @author Michel Günster
 *
 */
public class Bresenham 
{

	public static int[] bresenLine(Point pointFirst,Point pointSecond,int ImageWidth, int[] imgArray, int color) 
	{
		final int dx = Math.abs(pointFirst.x-pointSecond.x);
		final int dy = Math.abs(pointFirst.y-pointSecond.y);
		final int sgnDx = pointFirst.x < pointSecond.x ? 1 : -1;
		final int sgnDy = pointFirst.y < pointSecond.y ? 1 : -1;
		
		int shortD,longD,incXshort,incXlong,incYshort,incYlong;
		
		if (dx > dy) 
		{
			shortD = dy; 
			longD = dx; 
			incXlong = sgnDx; 
			incXshort = 0; 
			incYlong = 0; 
			incYshort = sgnDy;
		}
		else 
		{
			shortD = dx; 
			longD = dy; 
			incXlong = 0; 
			incXshort = sgnDx; 
			incYlong = sgnDy; 
			incYshort = 0;
		}
		int d = longD / 2; 
		int x = pointFirst.x; 
		int y = pointFirst.y;
		
		for(int i = 0; i <= longD ; ++i) 
		{

			if ( (y * ImageWidth + x) >= 0 && (y * ImageWidth + x) < imgArray.length ) 
			imgArray[y * ImageWidth + x] = color;
			
			x += incXlong;
			y += incYlong;
			d += shortD;
			
			if (d >= longD) 
			{
				d -= longD;
				x += incXshort;
				y += incYshort;
			}
			
		};
		return imgArray;
	}

	public static int[] bresenCircle(Point pointFirst, int radius,int imageWidth, int[] imgArray, int color) 
	{
		if (pointFirst.x != 0)
		{
			
			int y = 0;
			int x = radius;
			;
//			double F = 0.25 - radius;
			int F = -radius;
			
			int dy = 1;
			int dyx = -2*radius+3;
			
			while( y < x) 
			{
				
				if ( (pointFirst.y+y)*imageWidth+(pointFirst.x+x) >= 0 && (pointFirst.y+y)*imageWidth+(pointFirst.x+x) < imgArray.length)
					imgArray[(pointFirst.y+y)*imageWidth+(pointFirst.x+x)] = color;
				
				if ( (pointFirst.y+y)*imageWidth+(pointFirst.x-x) >= 0 && (pointFirst.y+y)*imageWidth+(pointFirst.x-x) < imgArray.length)
					imgArray[(pointFirst.y+y)*imageWidth+(pointFirst.x-x)] = color;
				
				if ( (pointFirst.y-y)*imageWidth+(pointFirst.x+x) >= 0&& (pointFirst.y-y)*imageWidth+(pointFirst.x+x) < imgArray.length)
					imgArray[(pointFirst.y-y)*imageWidth+(pointFirst.x+x)] = color;
				
				if ( (pointFirst.y-y)*imageWidth+(pointFirst.x-x) >= 0 && (pointFirst.y-y)*imageWidth+(pointFirst.x-x) < imgArray.length)
					imgArray[(pointFirst.y-y)*imageWidth+(pointFirst.x-x)] = color;
				
				if ( (pointFirst.y+x)*imageWidth+(pointFirst.x+y) >= 0 && (pointFirst.y+x)*imageWidth+(pointFirst.x+y) < imgArray.length)
					imgArray[(pointFirst.y+x)*imageWidth+(pointFirst.x+y)] = color;
				
				if ( (pointFirst.y+x)*imageWidth+(pointFirst.x-y) >= 0 && (pointFirst.y+x)*imageWidth+(pointFirst.x-y) < imgArray.length)
					imgArray[(pointFirst.y+x)*imageWidth+(pointFirst.x-y)] = color;
				
				if ( (pointFirst.y-x)*imageWidth+(pointFirst.x+y) >= 0 && (pointFirst.y-x)*imageWidth+(pointFirst.x+y) < imgArray.length)				
					imgArray[(pointFirst.y-x)*imageWidth+(pointFirst.x+y)] = color;
				
				if ( (pointFirst.y-x)*imageWidth+(pointFirst.x-y) >= 0&& (pointFirst.y+y)*imageWidth+(pointFirst.x+x) < imgArray.length)
					imgArray[(pointFirst.y-x)*imageWidth+(pointFirst.x-y)] = color;

				y++;
				
				
			
				dy += 2;
				dyx += 2;
				
				if (F > 0) 
				{
					F += 2*y-2*x+3;
					--x;
					dyx += 2;
				} else 
				{
					F += 2*y+1;
				}
			
			}
			
		}
		return imgArray;
	}
	
	public static int[] bresenFilledCircle(Point pointFirst, int radius,int imageWidth, int[] imgArray, int color) 
	{
		if (pointFirst.x != 0)
		{
			
			int y = 0;
			int x = radius;
			int r_2 = radius*radius;
//			double F = 0.25 - radius;
			int F = -radius;
			
			int dy = 1;
			int dyx = -2*radius+3;
			
			while( y <= x) 
			{
				bresenLine(pointFirst, new Point(pointFirst.x+x,pointFirst.y+y), imageWidth, imgArray, color);
				bresenLine(pointFirst, new Point(pointFirst.x+x+1, pointFirst.y+y), imageWidth, imgArray,color);
	
				bresenLine(pointFirst, new Point(pointFirst.x-x, pointFirst.y+y), imageWidth, imgArray,color);
				bresenLine(pointFirst, new Point(pointFirst.x-x+1, pointFirst.y+y), imageWidth, imgArray,color);
	
				bresenLine(pointFirst, new Point(pointFirst.x+x, pointFirst.y-y), imageWidth, imgArray,color);
				bresenLine(pointFirst, new Point(pointFirst.x+x+1, pointFirst.y-y), imageWidth, imgArray,color);
	
				bresenLine(pointFirst, new Point(pointFirst.x-x, pointFirst.y-y), imageWidth, imgArray,color);
				bresenLine(pointFirst, new Point(pointFirst.x-x+1, pointFirst.y-y), imageWidth, imgArray,color);
	
	
				bresenLine(pointFirst, new Point(pointFirst.x+y, pointFirst.y+x), imageWidth, imgArray,color);
				bresenLine(pointFirst, new Point(pointFirst.x+y, pointFirst.y+x+1), imageWidth, imgArray,color);
	
				bresenLine(pointFirst, new Point(pointFirst.x-y, pointFirst.y+x), imageWidth, imgArray,color);
				bresenLine(pointFirst, new Point(pointFirst.x-y, pointFirst.y+x+1), imageWidth, imgArray,color);
	
				bresenLine(pointFirst, new Point(pointFirst.x+y, pointFirst.y-x), imageWidth, imgArray,color);
				bresenLine(pointFirst, new Point(pointFirst.x+y, pointFirst.y-x+1), imageWidth, imgArray,color);
	
				bresenLine(pointFirst, new Point(pointFirst.x-y, pointFirst.y-x), imageWidth, imgArray,color);
				bresenLine(pointFirst, new Point(pointFirst.x-y, pointFirst.y-x+1), imageWidth, imgArray,color);

				y++;
			
				dy += 2;
				dyx += 2;
				
				if (F > 0) 
				{
					F += 2*y-2*x+3;
					--x;
					dyx += 2;
				} else 
				{
					F += 2*y+1;
				}
			
			}
			
		}

		return imgArray;
	}

}
	

