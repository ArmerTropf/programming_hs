import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.imageio.*;

import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Vector;

public class mypics 
{

	public static void main(String[]args)
	{
		view_pics _view = new view_pics();
	}
} 

class view_pics extends JFrame
{
	//Größe der eingestellten Windowsauflösung
	private Dimension dimCurrentScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
	//Vector für die MiniaturBilder
	private Vector<Label_myPreviewPic> vecPicLabel = new Vector<Label_myPreviewPic>(1,1);
	
	private JPanel panel_tree = new JPanel();
	private JPanel panel_bigPic = new JPanel();
	private JPanel panel_preview = new JPanel();
	
	//Anzahl der Bilder speichern
	private int intLabelCounter = 0;
	
	
	
	view_pics()
	{
		super("GOGOO");
		System.out.println(dimCurrentScreenSize.getHeight() + " " + dimCurrentScreenSize.getWidth());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(1600, 768);
		setLayout(new BorderLayout());
		setWindowToCenter();
		
		//<!--MENÜ
		JMenuBar myJMenu = new JMenuBar();
		JMenu JMenu_File = new JMenu("File");
		JMenu JMenu_View = new JMenu("View");
		JMenuItem JMItem_file = new JMenuItem("oeffnen");
		JMenuItem JMItem_View_WindowSize = new JMenuItem("set Window Size");
		
		JMItem_file.addActionListener(e -> 
		{
			System.out.println("Button angeklickt");
			openPicFromDisk();
		} 
		);
		
		myJMenu.add(JMenu_File);
		JMenu_File.add(JMItem_file);
		JMenu_View.add(JMItem_View_WindowSize);
		
		setJMenuBar(myJMenu);
		//MENÜ-->
		

		panel_tree.setLayout(new BorderLayout());
		panel_bigPic.setLayout(new BorderLayout());
		panel_preview.setLayout(new FlowLayout());
		
		
		MyTreeView myTree = new MyTreeView();

		
		panel_tree.add(myTree,BorderLayout.CENTER);

		
		JScrollPane myScrollBar1 = new JScrollPane(panel_tree,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		JScrollPane myScrollBar2 = new JScrollPane(panel_bigPic,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		JScrollPane myScrollBar3 = new JScrollPane(panel_preview,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	
		
		JSplitPane split1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,myScrollBar1,myScrollBar2);
		JSplitPane split2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,split1,myScrollBar3);
				
		split2.setOneTouchExpandable(true);
		split2.setDividerLocation((int)(this.getHeight() * 0.60));		
		
		
		add(split2,BorderLayout.CENTER);
		

		setVisible(true);
	}
	
	//Methode, um das angeklickte Miniaturbild in originalgröße anzuzeigen 
	public void show_BigPicture(String PicturePath)
	{
		panel_bigPic.removeAll();

		//Original Größe
		ImageIcon myImage = new ImageIcon(PicturePath);
		Icon picture = myImage;
		
		panel_bigPic.add(new JLabel(picture),BorderLayout.CENTER);
		revalidate(); 
	}
	
	//Fenster auf die Mitte setzen
	public void setWindowToCenter()
	{
		Dimension myCenterCoords = new Dimension();
		myCenterCoords.setSize((dimCurrentScreenSize.getWidth() - this.getWidth())/2, (dimCurrentScreenSize.getHeight() - this.getHeight())/2);
		setLocation((int)myCenterCoords.getWidth(), (int)myCenterCoords.getHeight());
	}
	
	//Bilder dem VorschauFenster hinzufügen
	//Möglichkeit von Auswahl eines Ordners oder einer Datei
	public void openPicFromDisk(String path)
	{
		openFromDisk_go(path);
	}
	public void openPicFromDisk()
	{
		openFromDisk_go("c:/");
	}
	
	
	private void openFromDisk_go(String path)
	{
		JFileChooser file = new JFileChooser("D:/Sicherung/Pictures/iCloud Photos/My Photo Stream");
//		JFileChooser file = new JFileChooser(path);
		
		//File- und Directory-Auswahl erlauben
		file.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		
		if ( file.showOpenDialog(null) != JFileChooser.CANCEL_OPTION)
		{
		
			if ( file.getSelectedFile().isDirectory() )
			{
				//File Objekt erzeugen
				File myFiles = new File(file.getSelectedFile().getAbsolutePath());
				//Array aller beinhalteten Files erzeugen
				File[] fileArray = myFiles.listFiles();
				
				int intTmplabelCounter = intLabelCounter; 
				
				for (int i = 0; i < fileArray.length; i++)
				{
					System.out.println(fileArray[i].getPath());
					System.out.println(fileArray.length);
	
					//Suffixfilter auf 
					if (fileArray[i].getPath().toLowerCase().endsWith("jpg") || fileArray[i].getPath().toLowerCase().endsWith("gif") || fileArray[i].getPath().toLowerCase().endsWith("jpeg") || fileArray[i].getPath().toLowerCase().endsWith("png"))
					{
						vecPicLabel.add(new Label_myPreviewPic(fileArray[i].getAbsoluteFile(),intLabelCounter,this));
						System.out.println(intLabelCounter);
						panel_preview.add(vecPicLabel.get(intLabelCounter));				
						intLabelCounter++;
						
					}
					
					
				}
				if ( intTmplabelCounter == intLabelCounter )
				{
					JOptionPane.showMessageDialog(null,"Keine Bilddatein gefunden!");
					openPicFromDisk(myFiles.getParent());
					
				}
	
			}
			else
			{
				vecPicLabel.add(new Label_myPreviewPic(file.getSelectedFile(),intLabelCounter,this));
				System.out.println(intLabelCounter);
				panel_preview.add(vecPicLabel.get(intLabelCounter));				
				intLabelCounter++;
			}
			revalidate();
		}
		
	}
	
	
}

class Label_myPreviewPic extends JLabel implements  MouseListener
{
	
	private view_pics _view;
	private BufferedImage originalImage;
	private String path;
	private int int_whichIndexinVectorArray;
	private Dimension dim_PicReducedSize;
	
	ActionListener actionListener;
	
	public String getPath() {
		return path;
	}

	Label_myPreviewPic(File myFile, int vectorIndex, view_pics myview)
	{
		
		try {
			//ViewObjekt speichern für das Pushen des BigPictures
			_view = myview;
			//Index des Vektors speichern indem das Bild gespeichert ist
			int_whichIndexinVectorArray = vectorIndex;
			//Lesen des Bildes
			originalImage = ImageIO.read(myFile);
			//Miniaturbildmaße berechnen lassen
			dim_PicReducedSize = getIconReducedSize((double)originalImage.getHeight(),(double)originalImage.getWidth(),(double)200.0);
			
			//Eine verkleinerte Instanz des Bildes generieren und in ein neues Image speichern
            Image smallImage = originalImage.getScaledInstance((int)dim_PicReducedSize.getWidth(),(int)dim_PicReducedSize.getHeight(),Image.SCALE_FAST);
            //Ein ImageIcon erstellen für das Icon als Bild
            ImageIcon test = new ImageIcon(smallImage);
            //Icon erstellen um es auf das Label zu bringen
    		Icon test2 = test;
    		//Label mit einem Icon/Bild ausstatten
    		setIcon(test2);
    		
    		path = myFile.getAbsolutePath();
    		System.out.println(path);
    		
    		addMouseListener(this);
    		
		
		} catch (IOException e) 
		{
			e.printStackTrace();
		}		
		
	}
	
	//Methode um die reduzierte Höhe und Breite vom Miniaturbild zu berechnen
	private Dimension getIconReducedSize(double height, double width , double PreviewPicHeight)
	{
		double double_smallerInPercent = 0.0;
		Dimension reduced_dimensions = new Dimension();	
		double_smallerInPercent = (PreviewPicHeight / height);
		reduced_dimensions.setSize(width*double_smallerInPercent, PreviewPicHeight);	
		return reduced_dimensions;			
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(getPath() + " " + int_whichIndexinVectorArray);
		_view.show_BigPicture(getPath());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	
	
}

class MyTreeView extends JPanel
{
	
	public MyTreeView() 
	{
		JTree tree_folder = new JTree();
		
		
		File homeDir = FileSystemView.getFileSystemView().getHomeDirectory();
		 
		//create the root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        //create the child nodes
        DefaultMutableTreeNode vegetableNode = new DefaultMutableTreeNode("Vegetables");
        DefaultMutableTreeNode fruitNode = new DefaultMutableTreeNode("Fruits");
 
        //add the child nodes to the root node
        root.add(vegetableNode);
        root.add(fruitNode);
         
        //create the tree by passing in the root node
        tree_folder = new JTree(root);
        add(tree_folder);
    
		
		repaint();
	}
	


	
}