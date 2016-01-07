import java.awt.*;
import java.awt.event.*;


public class AWT_Button extends Frame{

	AWT_Button()
	{
		super("mein Fester");
		setSize(300, 400);
//Layout muss gesetzt werden
		BorderLayout myBorder = new BorderLayout();
		setLayout(myBorder);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				dispose();
			}
		}		
		);
		
		
		setVisible(true);
	}
	
	public static void main(String[]args)
	{

		
		
		Button myButton = new Button("CoolerButton");
		myButton.setSize(20,40);
		
		TextField myTextField = new TextField("MyTextfield");
		myTextField.setSize(20, 30);
		
		Checkbox myCheckbox = new Checkbox();
		myCheckbox.setEnabled(true);
		
		TextArea myTextArea = new TextArea();
		myTextArea.setSize(10,60);
		
		Choice myChoice = new Choice();
		myChoice.add("Erster");
		myChoice.add("Zweiter");
		
		List myList = new List(5);
		myList.add("gogo");
		myList.add("2GOGOGO");
		myList.add("3zu1",0);
		myList.setBackground(Color.RED);
		
		Scrollbar myScrollbar = new Scrollbar(Scrollbar.HORIZONTAL,50,10,0,100);
		
				
		AWT_Button myButtonWindow = new AWT_Button();
		
		Panel myPanelNorth = new Panel();
		Panel myPanelSouth = new Panel();
		Panel myPanelEast = new Panel();
		Panel myPanelWest = new Panel();
		
		myPanelNorth.add(myButton);
		myPanelSouth.add(myCheckbox);
		myPanelEast.add(myTextArea);
		myPanelWest.add(myList);
		
/*		myButtonWindow.add(myButton);
		myButtonWindow.add(myTextField);
		myButtonWindow.add(myCheckbox);
		myButtonWindow.add(myTextArea);
		myButtonWindow.add(myChoice);
		myButtonWindow.add(myList);
		myButtonWindow.add(myScrollbar);
*/
myButtonWindow.add(BorderLayout.NORTH, myPanelNorth);
myButtonWindow.add(BorderLayout.SOUTH, myPanelSouth);
myButtonWindow.add(BorderLayout.EAST, myPanelEast);
myButtonWindow.add(BorderLayout.WEST, myPanelWest);
		
//Passt die Fenstergröße für die Dialogelemente an. Sodass jedes hineinpasst
		myButtonWindow.pack();
	}
}
