import java.awt.*;
import java.awt.event.*;


public class ueben1 extends Frame 
{
	ueben1()
	{
		super("gfdg");
		setSize(600,600);
		setVisible(true);
		
		addWindowListener(new WindowAdapter()
		{			
			public void windowClosing(WindowEvent e)
			{
				dispose();
			}
		}
		);
		
		Button myButton = new Button("-bums");
		myButton.setBounds(40, 40, 100, 100);
		myButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Button1 gedrückt");
				Button myButton2 = new Button("FickundFoxy");
				myButton2.setBounds(500, 500, 30, 30);
				add(myButton2);
				myButton2.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent t)
					{
						System.out.println("zweiter");
					}
				});
				
				
			}
		});
		
		add(myButton);
		
	}
	
	public static void main(String[]args)
	{
		ueben1 test = new ueben1();
		CheckboxGroup myCheckboxGroup1 = new CheckboxGroup();
		
		//Label
		Label myLabel = new Label("Test");
		myLabel.setBounds(300, 300, 20, 20);
		test.add(myLabel);
		
		//Checkbox mit Gruppe
		Checkbox myCheckBox = new Checkbox("test", false, myCheckboxGroup1);
		myCheckBox.setBounds(150, 150, 20, 20);
		test.add(myCheckBox);
		
		Checkbox myCheckBox2 = new Checkbox("TESTBOX", true, myCheckboxGroup1);
		myCheckBox2.setBounds(150, 170, 20, 20);
		test.add(myCheckBox2);
		
		TextField myTextField = new TextField();
		myTextField.setBounds(180, 180, 100, 40);
		myTextField.setText("Bums");
		test.add(myTextField);
		
//		TextArea myTextArea = new TextArea();
//		myTextArea.setRows(5);
//		myTextArea.setBounds(200, 300, 100, 40);
//		test.add(myTextArea);
	
		Choice myChoice1 = new Choice();
		myChoice1.setBounds(200, 300, 60, 60);
		myChoice1.add("Test1");
		myChoice1.add("Test2");
		myChoice1.add("Test3");
		myChoice1.add("Test4");
		
		myChoice1.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent e) 
			{
				System.out.println(e.getItem().toString());
			}
			
		}
		);
		
		
		test.add(myChoice1);		
		
		
		
		
		
		
		
	}
}
