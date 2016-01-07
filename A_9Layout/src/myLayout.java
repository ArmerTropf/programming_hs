import java.awt.*;


public class myLayout 
{
	
	public static void main(String[]args)
	{
		myLayout_go test = new myLayout_go();
	}
	

}


class myLayout_go extends Frame
{
	myLayout_go()
	{
		super("GOGOG");
		setSize(500,500);
		setLayout(new BorderLayout());
		
		
		Button test = new Button("ggig");
		Button test2 = new Button("ggig");
		Button test3 = new Button("ggig");
		Button test4 = new Button("ggig");
		Button test5 = new Button("ggig");
		
		add(test,BorderLayout.NORTH);
		add(test2, BorderLayout.SOUTH);
		add(test3, BorderLayout.WEST);
		add(test4, BorderLayout.EAST);
		add(test5, BorderLayout.CENTER);
		

		setVisible(true);
		
		
		
	}
}
