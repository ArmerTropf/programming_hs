import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


public class go {

	public static void main(String[]args)
	{
		myView go = new myView();
	}
	
}


class myView extends JFrame implements KeyListener
{
	public myView() 
	{
		setSize(600, 600);
		setVisible(true);

		addKeyListener(this);
	}
	
	

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		if (arg0.isControlDown() == true)
			System.out.println(arg0.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	}
