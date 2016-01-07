import javax.swing.*;

import java.awt.*;
import java.awt.event.*;



public class myAssignment 
{
	public static void main(String[]args)
	{
		View_assignment _view = new View_assignment();
	}
}

class View_assignment extends JFrame implements MouseListener,MouseMotionListener
{
	Dimension dimWindowSize = new Dimension(604,830);

	JPanel JPanel_upper = new JPanel(true);
	JPanel JPanel_lower = new JPanel(true);
	JPanel JPanel_adjustments = new JPanel(true);
	
	JSplitPane JSplitPane_mySplit1;
	
	MyPicture MyPicture_TmpPic4View;
	
	JLabel nb1 = new JLabel("X");
	JTextField jTextFieldX = new JTextField("4");
	JLabel nb2 = new JLabel("Y");
	JTextField jTextFieldY = new JTextField("4");
	JButton nb3 = new JButton("go");
	
	
	Dimension aBig1 = new Dimension(600,500);
	Dimension aPrev1 = new Dimension(200,200);
	
	//Für das Auswahlfeld um zu morphen
	Point pointSelectionFirst = new Point();
	Point pointSelectionSecond = new Point();
	Dimension dimSelection = new Dimension();
	
	MySelection mySelection;
	
	
	boolean boolSelectionSet = false;

	
	Color color_selectionRect = Color.YELLOW;
		
	public View_assignment() 
	{
		super("Assignment 1");
		
		
		setSize((int)dimWindowSize.getWidth(),(int)dimWindowSize.getHeight());
		BorderLayout myBorder = new BorderLayout();
		myBorder.getLayoutAlignmentX(this);
		myBorder.getLayoutAlignmentY(this);
		setLayout(myBorder);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//--> Menu
		JMenuBar JMenuBar_myMenubar = new JMenuBar();
		JMenu JMenu_myMenu_File = new JMenu("File");
		
		JMenuItem JMenuItem_OpenFile = new JMenuItem("Open File");
		JMenuItem_OpenFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(JSplitPane_mySplit1.getSize(getSize()));
				
			}
		});
		JMenuItem JMenuItem_CloseProgram = new JMenuItem("Close Program");
		JMenuItem_CloseProgram.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		JSeparator JSep_mySep1 = new JSeparator(JSeparator.HORIZONTAL);
		JMenu JMenu_myMenu_DiaShow = new JMenu("DiaShow");
		JMenuItem JMenuItem_start = new JMenuItem("start DiaShow");
		
		JMenu JMenu_myMenu_Morphing = new JMenu("Morph");		
		
		JCheckBoxMenuItem JCheckBoxMenuItem_Move = new JCheckBoxMenuItem("Verschieben");
		JCheckBoxMenuItem JCheckBoxMenuItem_BigSmall  = new JCheckBoxMenuItem("Vergroessern/Verkleinern");
		JCheckBoxMenuItem JCheckBoxMenuItem_Rotate  = new JCheckBoxMenuItem("Rotieren");
		JCheckBoxMenuItem JCheckBoxMenuItem_Shear  = new JCheckBoxMenuItem("Scheren");
		
		
		
		JCheckBoxMenuItem_Move.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JCheckBoxMenuItem_Shear.setSelected(false);
				JCheckBoxMenuItem_BigSmall.setSelected(false);
				JCheckBoxMenuItem_Rotate.setSelected(false);
				
			}
		});
		
		JCheckBoxMenuItem_BigSmall.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JCheckBoxMenuItem_Move.setSelected(false);
				JCheckBoxMenuItem_Rotate.setSelected(false);
				JCheckBoxMenuItem_Shear.setSelected(false);
				
			}
		});
		
		JCheckBoxMenuItem_Rotate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JCheckBoxMenuItem_Move.setSelected(false);
				JCheckBoxMenuItem_BigSmall.setSelected(false);
				JCheckBoxMenuItem_Shear.setSelected(false);
				
			}
		});
		
		JCheckBoxMenuItem_Shear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JCheckBoxMenuItem_Move.setSelected(false);
				JCheckBoxMenuItem_BigSmall.setSelected(false);
				JCheckBoxMenuItem_Rotate.setSelected(false);
				
			}
		});
		
		JMenu JMenu_myMenu_Histogram = new JMenu("Histotgramm");
		JMenuItem JMenuItem_histogramShow = new JMenuItem("zeige Histogramm");
		JMenuItem_histogramShow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				MyHistogram myHistogram = new MyHistogram(MyPicture_TmpPic4View);
			}
		});
		
		JMenu JMenu_myMenu_AccepttoCallerImage = new JMenu("Übernehmen");
		JMenuItem JMenuItem_accept = new JMenuItem("Auf Bild Übernehmen");
		JMenuItem_accept.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				MyPicture_TmpPic4View.setEditedImageToCallerImage();
			}
		});
		
		
		
		JMenu_myMenu_File.add(JMenuItem_OpenFile);
		JMenu_myMenu_File.add(JSep_mySep1);
		JMenu_myMenu_File.add(JMenuItem_CloseProgram);
		
		JMenu_myMenu_DiaShow.add(JMenu_myMenu_DiaShow);
		JMenu_myMenu_DiaShow.add(JMenuItem_start);
		
		JMenu_myMenu_Morphing.add(JCheckBoxMenuItem_BigSmall);
		JMenu_myMenu_Morphing.add(JCheckBoxMenuItem_Move);
		JMenu_myMenu_Morphing.add(JCheckBoxMenuItem_Rotate);
		JMenu_myMenu_Morphing.add(JCheckBoxMenuItem_Shear);
		
		JMenu_myMenu_Histogram.add(JMenuItem_histogramShow);
		
		JMenu_myMenu_AccepttoCallerImage.add(JMenuItem_accept);
		
		JMenuBar_myMenubar.add(JMenu_myMenu_File);
		JMenuBar_myMenubar.add(JMenu_myMenu_DiaShow);
		JMenuBar_myMenubar.add(JMenu_myMenu_Morphing);
		JMenuBar_myMenubar.add(JMenu_myMenu_Histogram);
		JMenuBar_myMenubar.add(JMenu_myMenu_AccepttoCallerImage);
		setJMenuBar(JMenuBar_myMenubar);
		// MENU <--
		
		// --> JPanel für ober und untere Splitpane 
		JPanel_upper.setLayout(new BorderLayout());
		JPanel_lower.setLayout(new FlowLayout());
		
		
		
		
		nb3.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//ClickEvent des AusgangsObjekt deaktivieren
				MyPicture_TmpPic4View.boolMouseListenerClicktoSetBigImage = false;
				
				if (JCheckBoxMenuItem_Move.isSelected() == true )
				{
					
					
					System.out.println("Bewegen");
					MyImageMorph myImageMorph = new MyImageMorph();
					MyPicture_TmpPic4View.intArrImgShowThisImage = myImageMorph.getMovedImageArr(MyPicture_TmpPic4View.intArrImgShowThisImage, MyPicture_TmpPic4View.dimImgShowBig.width, MyPicture_TmpPic4View.dimImgShowBig.height, Double.parseDouble(jTextFieldX.getText()), Double.parseDouble(jTextFieldY.getText()), mySelection);
					MyPicture_TmpPic4View.imgShowThisImage = MyPicture_TmpPic4View.getImageFromArray(MyPicture_TmpPic4View.intArrImgShowThisImage, MyPicture_TmpPic4View.dimImgShowBig.width, MyPicture_TmpPic4View.dimImgShowBig.height);
					
//					MyPicture_TmpPic4View.intArrImgShowThisImage = myImageMorph.getMovedImageArr(MyPicture_TmpPic4View.intArrImgShowBig, MyPicture_TmpPic4View.dimImgShowBig.width, MyPicture_TmpPic4View.dimImgShowBig.height, Double.parseDouble(jTextFieldX.getText()), Double.parseDouble(jTextFieldY.getText()), mySelection);
//					MyPicture_TmpPic4View.imgShowThisImage = MyPicture_TmpPic4View.getImageFromArray(MyPicture_TmpPic4View.intArrImgShowThisImage, MyPicture_TmpPic4View.dimImgShowBig.width, MyPicture_TmpPic4View.dimImgShowBig.height);
				}
				else if (JCheckBoxMenuItem_BigSmall.isSelected() == true )
				{
					System.out.println("Vergrößern/Verkleinern");
					MyImageMorph myImageMorph = new MyImageMorph();
					MyPicture_TmpPic4View.intArrImgShowThisImage = myImageMorph.getScaledImageArr(MyPicture_TmpPic4View.intArrImgShowThisImage, MyPicture_TmpPic4View.dimImgShowBig.width, MyPicture_TmpPic4View.dimImgShowBig.height, Double.parseDouble(jTextFieldX.getText()), Double.parseDouble(jTextFieldY.getText()), mySelection);
					MyPicture_TmpPic4View.imgShowThisImage = MyPicture_TmpPic4View.getImageFromArray(MyPicture_TmpPic4View.intArrImgShowThisImage, MyPicture_TmpPic4View.dimImgShowBig.width, MyPicture_TmpPic4View.dimImgShowBig.height);
					repaint();
					
				}
				else if (JCheckBoxMenuItem_Rotate.isSelected() == true )
				{
					System.out.println("Rotieren");
					MyImageMorph myImageMorph = new MyImageMorph();
					MyPicture_TmpPic4View.intArrImgShowThisImage = myImageMorph.getRotatedImageArr(MyPicture_TmpPic4View.intArrImgShowThisImage, MyPicture_TmpPic4View.dimImgShowBig.width, MyPicture_TmpPic4View.dimImgShowBig.height, Double.parseDouble(jTextFieldX.getText()), Double.parseDouble(jTextFieldY.getText()), mySelection);
					MyPicture_TmpPic4View.imgShowThisImage = MyPicture_TmpPic4View.getImageFromArray(MyPicture_TmpPic4View.intArrImgShowThisImage, MyPicture_TmpPic4View.dimImgShowBig.width, MyPicture_TmpPic4View.dimImgShowBig.height);
					repaint();
					
				}
				else if (JCheckBoxMenuItem_Shear.isSelected() == true )
				{
					System.out.println("Scheren");
					MyImageMorph myImageMorph = new MyImageMorph();
					MyPicture_TmpPic4View.intArrImgShowThisImage = myImageMorph.getShearedImageArr(MyPicture_TmpPic4View.intArrImgShowThisImage, MyPicture_TmpPic4View.dimImgShowBig.width, MyPicture_TmpPic4View.dimImgShowBig.height, Double.parseDouble(jTextFieldX.getText()), Double.parseDouble(jTextFieldY.getText()), mySelection);
					MyPicture_TmpPic4View.imgShowThisImage = MyPicture_TmpPic4View.getImageFromArray(MyPicture_TmpPic4View.intArrImgShowThisImage, MyPicture_TmpPic4View.dimImgShowBig.width, MyPicture_TmpPic4View.dimImgShowBig.height);
					
					repaint();
					
				}
				
				


				
				
				repaint();
			}
		});
		
		
		
		JPanel_adjustments.setLayout(new GridLayout(1,5));
		JPanel_adjustments.setBounds(0, 0, 600, 800);;
		JPanel_adjustments.add(nb1);
		JPanel_adjustments.add(jTextFieldX);
		JPanel_adjustments.add(nb2);
		JPanel_adjustments.add(jTextFieldY);
		JPanel_adjustments.add(nb3);
		// JPanel <--
		
		
		
		JScrollPane jScrollPane_Upper = new  JScrollPane(JPanel_upper);
		JScrollPane jScrollPane_Lower = new  JScrollPane(JPanel_lower);
		
		// --> JSplitPane: 
		JSplitPane_mySplit1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, JPanel_upper, jScrollPane_Lower);
		JSplitPane_mySplit1.setSize(dimWindowSize);
		JSplitPane_mySplit1.setDividerLocation(490);
		// JSplitPane <--
		
		
		
		
//		this.remove(((BorderLayout)getLayout()).getLayoutComponent(BorderLayout.NORTH));
		add(JPanel_adjustments, BorderLayout.NORTH);
		
		add(JSplitPane_mySplit1, BorderLayout.CENTER);
		
		
		MyPicture myPicture1 = new MyPicture("d:/1.jpg",aBig1.width, aBig1.height, aPrev1.width, aPrev1.height, MyPicture.SHOW_PREVIEW_IMAGE, this, null);
		MyPicture myPicture2 = new MyPicture("d:/2.jpg",aBig1.width, aBig1.height, aPrev1.width, aPrev1.height, MyPicture.SHOW_PREVIEW_IMAGE, this, null);
		MyPicture myPicture3 = new MyPicture("d:/1.jpg",aBig1.width, aBig1.height, aPrev1.width, aPrev1.height, MyPicture.SHOW_PREVIEW_IMAGE, this, null);
		MyPicture myPicture4 = new MyPicture("d:/2.jpg",aBig1.width, aBig1.height, aPrev1.width, aPrev1.height, MyPicture.SHOW_PREVIEW_IMAGE, this, null);
		MyPicture myPicture5 = new MyPicture("d:/1.jpg",aBig1.width, aBig1.height, aPrev1.width, aPrev1.height, MyPicture.SHOW_PREVIEW_IMAGE, this, null);
		MyPicture myPicture6 = new MyPicture("d:/2.jpg",aBig1.width, aBig1.height, aPrev1.width, aPrev1.height, MyPicture.SHOW_PREVIEW_IMAGE, this, null);
		MyPicture myPicture7 = new MyPicture("d:/1.jpg",aBig1.width, aBig1.height, aPrev1.width, aPrev1.height, MyPicture.SHOW_PREVIEW_IMAGE, this, null);
		MyPicture myPicture8 = new MyPicture("d:/2.jpg",aBig1.width, aBig1.height, aPrev1.width, aPrev1.height, MyPicture.SHOW_PREVIEW_IMAGE, this, null);
		
//		MyPicture myPicture1 = new MyPicture("c:/1.jpg",aBig1.width, aBig1.height, aPrev1.width, aPrev1.height, MyPicture.SHOW_PREVIEW_IMAGE, this);
//		MyPicture myPicture2 = new MyPicture("c:/2.jpg",aBig1.width, aBig1.height, aPrev1.width, aPrev1.height, MyPicture.SHOW_PREVIEW_IMAGE, this);
//		MyPicture myPicture3 = new MyPicture("c:/1.jpg",aBig1.width, aBig1.height, aPrev1.width, aPrev1.height, MyPicture.SHOW_PREVIEW_IMAGE, this);
//		MyPicture myPicture4 = new MyPicture("c:/2.jpg",aBig1.width, aBig1.height, aPrev1.width, aPrev1.height, MyPicture.SHOW_PREVIEW_IMAGE, this);
//		MyPicture myPicture5 = new MyPicture("c:/1.jpg",aBig1.width, aBig1.height, aPrev1.width, aPrev1.height, MyPicture.SHOW_PREVIEW_IMAGE, this);
//		MyPicture myPicture6 = new MyPicture("c:/2.jpg",aBig1.width, aBig1.height, aPrev1.width, aPrev1.height, MyPicture.SHOW_PREVIEW_IMAGE, this);
//		MyPicture myPicture7 = new MyPicture("c:/1.jpg",aBig1.width, aBig1.height, aPrev1.width, aPrev1.height, MyPicture.SHOW_PREVIEW_IMAGE, this);
//		MyPicture myPicture8 = new MyPicture("c:/2.jpg",aBig1.width, aBig1.height, aPrev1.width, aPrev1.height, MyPicture.SHOW_PREVIEW_IMAGE, this);
		
		
		JPanel_lower.add(myPicture1);
		JPanel_lower.add(myPicture2);
		JPanel_lower.add(myPicture3);
		JPanel_lower.add(myPicture4);
		JPanel_lower.add(myPicture5);
		JPanel_lower.add(myPicture6);
		JPanel_lower.add(myPicture7);
		JPanel_lower.add(myPicture8);
		
		setBigImage(myPicture1);
		
		setVisible(true);
		
		mySelection = new MySelection();
		
	}
	
	public void setBigImage(MyPicture MyPicture2Show)
	{
		JPanel_upper.removeAll();
		MyPicture_TmpPic4View = null;
		MyPicture_TmpPic4View = new MyPicture(MyPicture2Show.getImagePath(), MyPicture2Show.dimImgShowBig.width, MyPicture2Show.dimImgShowBig.height, MyPicture2Show.dimImgPreview.width, MyPicture2Show.dimImgPreview.height, MyPicture.SHOW_BIG_IMAGE, this, MyPicture2Show);
		JPanel_upper.add(MyPicture_TmpPic4View,BorderLayout.CENTER);
		
		
		
		MyPicture_TmpPic4View.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if (boolSelectionSet == false)
				{
					mySelection.setPointSelectionSecond(e.getPoint());
					boolSelectionSet = true;
					
				}
				
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if (boolSelectionSet == false)
				{
					mySelection.setPointSelectionFirst(e.getPoint());
				}
				
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
		
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {

				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				System.out.println("clicked in neu");
				if ( isPointInSelection(e.getPoint()) == false )
				{
					boolSelectionSet = false;
					mySelection.setPointSelectionFirst(new Point(0,0));
					mySelection.setPointSelectionSecond(new Point(0,0));
					mySelection.setDimensionForSelection();
					repaint();
				}				
			
			}
		});
		MyPicture_TmpPic4View.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) 
			{
				if ( isPointInSelection(e.getPoint()) == false )
				{
		
				}	
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				
				if (boolSelectionSet == false)
				{
					mySelection.setPointSelectionSecond(e.getPoint());
					mySelection.setDimensionForSelection();
					repaint();
				}
		
			}
		});
		JPanel_upper.repaint();
		
	}
	
	@Override
	public void paint(Graphics g) 
	{
        super.paint(g);
        //Grafics von der Componente und nicht vom JFrame nutzen
        //Insets des JFrames müssen sonst einberechnet werden.
        if (MyPicture_TmpPic4View != null)
        	g = MyPicture_TmpPic4View.getGraphics();
        
 		g.setColor(color_selectionRect);
 		
 		
 		if ( mySelection.boolSelectionSet == true)
 			g.drawRect(mySelection.getPointSelectionFirst().x, mySelection.getPointSelectionFirst().y , mySelection.getDimSelection().width,mySelection.getDimSelection().height);
 		

		
		
    }
	
	public boolean isPointInSelection(Point Click)
	{
//		System.out.println(Click.getX() + " >= " + pointSelectionFirst.x + " && " +  Click.getX() + " <= " +  pointSelectionSecond.x  + " && " + Click.getY() + " >= " + pointSelectionFirst.y + " && " + Click.getY() + " <= " + pointSelectionSecond.y);
//		System.out.println(Click.getX() >= pointSelectionFirst.x && Click.getX() <= pointSelectionSecond.x  && Click.getY() >= pointSelectionFirst.y && Click.getY() <= pointSelectionSecond.y);
		if  (Click.getX() >= pointSelectionFirst.x && Click.getX() <= pointSelectionSecond.x  && Click.getY() >= pointSelectionFirst.y && Click.getY() <= pointSelectionSecond.y)
			return true;
		else 
			return false;
	}
	
	public Dimension getDimensionSelection(Point X, Point Y)
	{
		Dimension dimNew = new Dimension();
		
		dimNew.setSize(Y.x-X.x, Y.y-X.y);
		
	
		
		
		return dimNew; 
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("dododooddfdf");
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		
		
		
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}
	
}
