import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class go 
{
	public static void main(String[] args)
	{
		new view();
	}
}

class view extends JFrame implements MouseListener
{
	Point p1 = new Point();
	Point p2 = new Point();
	
	view()
	{
		setSize(800,800);
		
		addMouseListener(this);
		
		
		setVisible(true);
	}
	@Override
	public void paint(Graphics g) 
	{
		if (p1.x != 0)
		{
			int radius = p2.x-p1.x;
			int y = 0;
			int x = radius;
			int r_2 = radius*radius;
//			double F = 0.25 - radius;
			int F = -radius;
			
			int dy = 1;
			int dyx = -2*radius+3;
			
			while( y <= x) 
			{
				
				x=(int) Math.sqrt(r_2-y*y);
				System.out.println(r_2 + " - " +  y + " * " + y +  " = " + x);
				

				g.drawLine(p1.x + x,p1.y + y,p1.x + x,p1.y + y); // p_0
				g.drawLine(p1.x - x,p1.y + y,p1.x - x,p1.y + y); // p_1
				g.drawLine(p1.x + x,p1.y - y,p1.x + x,p1.y - y); // p_2
				g.drawLine(p1.x - x,p1.y - y,p1.x - x,p1.y - y); // p_3
				g.drawLine(p1.x + y,p1.y + x,p1.x + y,p1.y + x); // p_4
				g.drawLine(p1.x - y,p1.y + x,p1.x - y,p1.y + x); // p_5
				g.drawLine(p1.x + y,p1.y - x,p1.x + y,p1.y - x); // p_6
				g.drawLine(p1.x - y,p1.y - x,p1.x - y,p1.y - x); // p_7
				
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
		p1 = arg0.getPoint();
		System.out.println(p1);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		p2 = arg0.getPoint();
		System.out.println(p2);
		repaint();
	}
	
}
