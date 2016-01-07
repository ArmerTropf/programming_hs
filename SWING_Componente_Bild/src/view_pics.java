import java.awt.*;

import javax.swing.*;




public class view_pics extends JFrame
{
	int klein = 200;
	int gross = 600;
	
	myPictures test1 = new myPictures("d:/ball.gif",klein,gross,false);
	myPictures test0 = new myPictures("d:/1.jpg",klein,gross,false);
	myPictures test2 = new myPictures("d:/ball.gif",klein,gross,false);
	myPictures test3 = new myPictures("d:/ball.gif",klein,gross,false);
	myPictures test4 = new myPictures("d:/ball.gif",klein,gross,false);
	myPictures test5 = new myPictures("d:/ball.gif",klein,gross,false);
	
	JScrollPane myScrollPane1;
	JScrollPane myScrollPane2;
	JPanel myPanelTop = new JPanel(new FlowLayout());
	JPanel myPanelBottom = new JPanel(new FlowLayout());
	
	view_pics()
	{
		super("ogogo");
		setSize(1024, 768);
		setLayout(new BorderLayout());
		
		//<!--MENÜ
		JMenuBar myJMenu = new JMenuBar();
		JMenu JMenu_File = new JMenu("File");
		JMenu JMenu_View = new JMenu("View");
		JMenuItem JMItem_file = new JMenuItem("oeffnen");
		JMenuItem JMItem_View_WindowSize = new JMenuItem("set Window Size");
		
		JMItem_file.addActionListener(e -> 
		{
			System.out.println("Button angeklickt");
//			openPicFromDisk();
		} 
		);
		
		myJMenu.add(JMenu_File);
		JMenu_File.add(JMItem_file);
		JMenu_View.add(JMItem_View_WindowSize);
		
		setJMenuBar(myJMenu);
		//MENÜ-->

		
		
		myScrollPane1 = new JScrollPane(myPanelTop, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		myScrollPane2 = new JScrollPane(myPanelBottom, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		JSplitPane splt_pane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, myScrollPane1, myScrollPane2);
		splt_pane.setDividerLocation(500);
		add(splt_pane, BorderLayout.CENTER);
		
		
		
//		add(test0);
//		add(test1);
//		add(test2);
//		add(test3);
//		add(test4);
//		add(test5);
		myPanelBottom.add(test0);
		myPanelBottom.add(test1);
		myPanelBottom.add(test2);
		myPanelBottom.add(test3);
		myPanelBottom.add(test4);
		setVisible(true);
		
		
	}

}
