import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Vector;


/**
 * 
 * @author Michael G�nster
 *
 */
public class myAssignment 
{
	public static void main(String[]args)
	{
		View_assignment _view = new View_assignment();
	}
}
/**
 * 
 * @author Michael G�nster
 *
 */
class View_assignment extends JFrame 
{
	View_assignment _view = this;
	Dimension dimWindowSize = new Dimension(604,830);

	JPanel JPanel_upper = new JPanel(true);
	JPanel JPanel_lower = new JPanel(true);
	JPanel JPanel_adjustments = new JPanel(true);
	JPanel JPanel_NORTH = new JPanel(true);
	
	JSplitPane JSplitPane_mySplit1;
	
	PresentationImage presentationLayer_ActualPresentationImage;
	PreviewImage previewImage_ActualPresentationImage = new PreviewImage();
	
	//Standardgr��en festlegen
	Dimension aBig1 = new Dimension(600,500);
	Dimension aPrev1 = new Dimension(200,200);

	
	Vector <Image> vecFadingImages = new Vector<Image>(1,1);
	//--
		
	//Vector f�r alle eingelesenen Bilder
	Vector<Picture> vecMyPictures = new Vector<Picture>(1,1);
	
	
	Selection selection = new Selection(this);
	ImageMorph myImageMorph = new ImageMorph();
	
	
	JMenu JMenu_myMenu_Edit;
	JMenu JMenu_myMenu_DiaShow;
	JMenu JMenu_myMenu_Morphing;
	JMenu JMenu_myMenu_Histogram;
	JMenu JMenu_myMenu_AccepttoCallerImage;
	
    
	//Welche ChkBox wurde ausge�hlt
    JCheckBoxMenuItem checkBoxMenuItem_MorphSelection = new JCheckBoxMenuItem();
	
    OptionPanel optionPanel = new OptionPanel();
    /*
     * Konstruktor f�r die View 
     */
	View_assignment() 
	{
		super("Assignment 2 - Farbersetzung");
		setSize((int)dimWindowSize.getWidth(),(int)dimWindowSize.getHeight());
		BorderLayout myBorder = new BorderLayout();
		myBorder.getLayoutAlignmentX(this);
		myBorder.getLayoutAlignmentY(this);
		setLayout(myBorder);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		/*
		 * MenuBar
		 */
		JMenuBar JMenuBar_myMenubar = new JMenuBar();
		
		/*
		 * Erstellung des Men�s DATEI
		 */
		JMenu JMenu_myMenu_File = new JMenu("Datei");
		
		JMenuItem JMenuItem_OpenFile = new JMenuItem("Open File");
		JMenuItem_OpenFile.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				/*
				 * Auswahldialog f�r Bilder 
				 */
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setCurrentDirectory(new File("d:/"));
				jFileChooser.setDialogTitle("Bitte Bild/Bilder ausw�hlen");
				jFileChooser.setMultiSelectionEnabled(true);
				jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				/*
				 * Filter f�r die Bilder w�hlen
				 */
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Nur jpg/jpeg/gif", "jpg", "gif", "jpeg");
				jFileChooser.setFileFilter(filter);
				
				if ( jFileChooser.showOpenDialog(_view) == 0 )
				{
					for (File myFiles : jFileChooser.getSelectedFiles()) 
					{
						vecMyPictures.add(new Picture(myFiles.getPath(),aBig1.width, aBig1.height, aPrev1.width, aPrev1.height, _view));
					
					}

					for (int i = 0 ; i < vecMyPictures.size() ; i++)
					{
						new PreviewImage(vecMyPictures.get(i).imgShowPrev, _view, vecMyPictures.get(i));
					}
					vecMyPictures.removeAllElements();
					_view.revalidate();
					disableEnableMenu(new JMenu [] {JMenu_myMenu_DiaShow}, true);
				}
				else 
					JOptionPane.showMessageDialog(_view,"Keine Bilder ausgew�hlt");
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
		/*
		 * ENDE MEN� DATEI
		 */
		
		JSeparator JSep_mySep1 = new JSeparator(JSeparator.HORIZONTAL);
		
		
		
		/*
		 * Erstellung des Men�s BEARBEITEN
		 */
		JMenu_myMenu_Edit = new JMenu("Bearbeiten");
		JMenuItem JMenuItem_copy = new JMenuItem("kopieren");
		
		/*
		 * Setzten des Hotkey auf  Strg-c
		 */
		JMenuItem_copy.setAccelerator(KeyStroke.getKeyStroke('C', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		/*
		 * Die jeweiligen ActionListener f�r die 
		 * einzelnen Men�eintr�ge unter dem Men� BEARBEITEN  
		 */
		JMenuItem_copy.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				selection.copySelection(presentationLayer_ActualPresentationImage.intArrImgShowThisImage, 600, 500);
			}
		});
		JMenuItem JMenuItem_paste = new JMenuItem("einf�gen");
		/*
		 * Setzten des Hotkey auf  Strg-V
		 */
		JMenuItem_paste.setAccelerator(KeyStroke.getKeyStroke('V', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		/*
		 * Die jeweiligen ActionListener f�r die 
		 * einzelnen Men�eintr�ge unter dem Men� BEARBEITEN  
		 */
		JMenuItem_paste.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{				
				/*
				 * Ver�ndertes Bild in das Aktuelle BildpunktArray speichern 
				 */
				presentationLayer_ActualPresentationImage.intArrImgShowThisImage = selection.PasteSelection(presentationLayer_ActualPresentationImage.intArrImgShowThisImage, 600, 500);
				/*
				 * Ver�ndertes Image erzeugen und im aktuell gezeigeten Bild speichern
				 */
				presentationLayer_ActualPresentationImage.imgShowThisImage = ImageActions.getImageFromArray(presentationLayer_ActualPresentationImage.intArrImgShowThisImage,600,500);
				repaint();
			}
		});
		/*
		 * ENDE MEN� BEARBEITEN
		 */
		
		/*
		 * Erstellung des Men�s DIASHOW
		 */
		JMenu_myMenu_DiaShow = new JMenu("DiaShow");
		JMenuItem JMenuItem_start = new JMenuItem("start DiaShow");
		/*
		 * Die jeweiligen ActionListener f�r die 
		 * einzelnen Men�eintr�ge unter dem Men� DIASHOW  
		 */
		JMenuItem_start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("DiaShow");
				if (vecFadingImages.size() != 0)
				{
					new ImageFade(vecFadingImages, _view );	
				}
				else
					JOptionPane.showMessageDialog(_view, "Keine Bilder f�r die SlideShow ausgew�hlt.");
	
			}
		});
		/*
		 * ENDE MEN� DIASHOW
		 */
		
		
		/*
		 * Erstellung des Men�s MORPH
		 */
		JMenu_myMenu_Morphing = new JMenu("Morph");		
		
		/*
		 * Alle Men�eintr�ge f�r das Men� MORPH
		 */
		JCheckBoxMenuItem JCheckBoxMenuItem_Move = new JCheckBoxMenuItem("Verschieben");
		JCheckBoxMenuItem JCheckBoxMenuItem_BigSmall  = new JCheckBoxMenuItem("Vergroessern/Verkleinern");
		JCheckBoxMenuItem JCheckBoxMenuItem_Rotate  = new JCheckBoxMenuItem("Rotieren");
		JCheckBoxMenuItem JCheckBoxMenuItem_Shear  = new JCheckBoxMenuItem("Scheren");
		JCheckBoxMenuItem JCheckBoxMenuItem_BresenhamLine  = new JCheckBoxMenuItem("Linie");
		JCheckBoxMenuItem JCheckBoxMenuItem_BresenhamCircle  = new JCheckBoxMenuItem("Kreis");
		JCheckBoxMenuItem JCheckBoxMenuItem_BresenhamFilledCircle  = new JCheckBoxMenuItem("gefuellter Kreis");	
		JCheckBoxMenuItem JCheckBoxMenuItem_ReplaceColors  = new JCheckBoxMenuItem("Farben ersetzen");
		
		
		/*
		 * Die jeweiligen ActionListener f�r die 
		 * einzelnen Men�eintr�ge unter dem Men� MORPH  
		 */
		JCheckBoxMenuItem_Move.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JCheckBoxMenuItem_Shear.setSelected(false);
				JCheckBoxMenuItem_BigSmall.setSelected(false);
				JCheckBoxMenuItem_Rotate.setSelected(false);
				JCheckBoxMenuItem_BresenhamLine.setSelected(false);
				JCheckBoxMenuItem_BresenhamCircle.setSelected(false);
				JCheckBoxMenuItem_BresenhamFilledCircle.setSelected(false);
				JCheckBoxMenuItem_ReplaceColors.setSelected(false);
				checkBoxMenuItem_MorphSelection = JCheckBoxMenuItem_Move;
				optionPanel.getModul("verschieben");
				revalidate();

			}
		});
		
		JCheckBoxMenuItem_BigSmall.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JCheckBoxMenuItem_Move.setSelected(false);
				JCheckBoxMenuItem_Rotate.setSelected(false);
				JCheckBoxMenuItem_Shear.setSelected(false);
				JCheckBoxMenuItem_BresenhamLine.setSelected(false);
				JCheckBoxMenuItem_BresenhamCircle.setSelected(false);
				JCheckBoxMenuItem_BresenhamFilledCircle.setSelected(false);
				JCheckBoxMenuItem_ReplaceColors.setSelected(false);
				checkBoxMenuItem_MorphSelection = JCheckBoxMenuItem_BigSmall;
				optionPanel.getModul("vergroessern/verkleinern");
				revalidate();
			}
		});
		
		JCheckBoxMenuItem_Rotate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JCheckBoxMenuItem_Move.setSelected(false);
				JCheckBoxMenuItem_BigSmall.setSelected(false);
				JCheckBoxMenuItem_Shear.setSelected(false);
				JCheckBoxMenuItem_BresenhamLine.setSelected(false);
				JCheckBoxMenuItem_BresenhamCircle.setSelected(false);
				JCheckBoxMenuItem_BresenhamFilledCircle.setSelected(false);
				JCheckBoxMenuItem_ReplaceColors.setSelected(false);
				checkBoxMenuItem_MorphSelection = JCheckBoxMenuItem_Rotate;
				optionPanel.getModul("Rotation");
				revalidate();
			}
		});
		
		JCheckBoxMenuItem_Shear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JCheckBoxMenuItem_Move.setSelected(false);
				JCheckBoxMenuItem_BigSmall.setSelected(false);
				JCheckBoxMenuItem_Rotate.setSelected(false);
				JCheckBoxMenuItem_BresenhamLine.setSelected(false);
				JCheckBoxMenuItem_BresenhamCircle.setSelected(false);
				JCheckBoxMenuItem_BresenhamFilledCircle.setSelected(false);
				JCheckBoxMenuItem_ReplaceColors.setSelected(false);
				checkBoxMenuItem_MorphSelection = JCheckBoxMenuItem_Shear;
				optionPanel.getModul("verzerren");
				revalidate();
			}
		});
		
		JCheckBoxMenuItem_BresenhamLine.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JCheckBoxMenuItem_Move.setSelected(false);
				JCheckBoxMenuItem_BigSmall.setSelected(false);
				JCheckBoxMenuItem_Rotate.setSelected(false);
				JCheckBoxMenuItem_Shear.setSelected(false);
				JCheckBoxMenuItem_BresenhamCircle.setSelected(false);
				JCheckBoxMenuItem_BresenhamFilledCircle.setSelected(false);
				JCheckBoxMenuItem_ReplaceColors.setSelected(false);
				checkBoxMenuItem_MorphSelection = JCheckBoxMenuItem_BresenhamLine;
		
			}
		});
		
		JCheckBoxMenuItem_BresenhamCircle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JCheckBoxMenuItem_Move.setSelected(false);
				JCheckBoxMenuItem_BigSmall.setSelected(false);
				JCheckBoxMenuItem_Rotate.setSelected(false);
				JCheckBoxMenuItem_Shear.setSelected(false);
				JCheckBoxMenuItem_BresenhamLine.setSelected(false);
				JCheckBoxMenuItem_BresenhamFilledCircle.setSelected(false);
				JCheckBoxMenuItem_ReplaceColors.setSelected(false);
				
				checkBoxMenuItem_MorphSelection = JCheckBoxMenuItem_BresenhamCircle;
		
			}
		});
		
		JCheckBoxMenuItem_BresenhamFilledCircle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JCheckBoxMenuItem_Move.setSelected(false);
				JCheckBoxMenuItem_BigSmall.setSelected(false);
				JCheckBoxMenuItem_Rotate.setSelected(false);
				JCheckBoxMenuItem_Shear.setSelected(false);
				JCheckBoxMenuItem_BresenhamLine.setSelected(false);
				JCheckBoxMenuItem_BresenhamCircle.setSelected(false);
				JCheckBoxMenuItem_ReplaceColors.setSelected(false);
				checkBoxMenuItem_MorphSelection = JCheckBoxMenuItem_BresenhamFilledCircle;
		
			}
		});
		
		JCheckBoxMenuItem_ReplaceColors.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JCheckBoxMenuItem_Move.setSelected(false);
				JCheckBoxMenuItem_BigSmall.setSelected(false);
				JCheckBoxMenuItem_Rotate.setSelected(false);
				JCheckBoxMenuItem_Shear.setSelected(false);
				JCheckBoxMenuItem_BresenhamLine.setSelected(false);
				JCheckBoxMenuItem_BresenhamCircle.setSelected(false);
				checkBoxMenuItem_MorphSelection = JCheckBoxMenuItem_ReplaceColors;
				optionPanel.getModul("Farben ersetzen");
				revalidate();
				
			}
		});
		/*
		 * ENDE MEN� MORPH
		 */
		
		
		/*
		 * Alle Eintr�ge f�r das Men� HISTOGRAMM
		 */
		JMenu_myMenu_Histogram = new JMenu("Histotgramm");
		JMenuItem JMenuItem_histogramShow = new JMenuItem("zeige Histogramm");
		
		/*
		 * Die jeweiligen ActionListener f�r die 
		 * einzelnen Men�eintr�ge unter dem Men� HISTOGRAMM 
		 */
		JMenuItem_histogramShow.addActionListener(new ActionListener() 
		{
			/*
			 * Anzeigen des Histogramms vom aktuellen Bild.
			 * Ver�nderung nach einer Farbersetzung sichtbar.
			 * (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) 
			{	
				new Histogram(presentationLayer_ActualPresentationImage);	
			}
		});
		/*
		 * ENDE MEN� HISTOGRAMM
		 */
		
		/*
		 * Alle Eintr�ge f�r das Men� �BERNEHMEN
		 */
		JMenu_myMenu_AccepttoCallerImage = new JMenu("�bernehmen");
		JMenuItem JMenuItem_accept = new JMenuItem("Bild�nderung �bernehmen");
		/*
		 * Die jeweiligen ActionListener f�r die 
		 * einzelnen Men�eintr�ge unter dem Men� �BERNEHMEN 
		 */
		JMenuItem_accept.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				/*
				 * �bernahme des Bildeigenschaften 
				 */
				presentationLayer_ActualPresentationImage.setEditedImageToCallerImage();
			}
		});
		/*
		 * ENDE MEN� �BERNEHMEN
		 */
		
		/*
		 * Alle Eintr�ge f�r das Men� HILFE
		 */
		JMenu JMenu_myMenu_Help= new JMenu("Hilfe");
		JMenuItem JMenuItem_myMenu_Help= new JMenuItem("Hilfe");
		/*
		 * Die jeweiligen ActionListener f�r die 
		 * einzelnen Men�eintr�ge unter dem Men� HILFE 
		 */
		JMenuItem_myMenu_Help.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog (null,
												"- Alle Morphing/Bild-Aktionen m�ssen einzeln durch Klick im  Men�punkt '�bernehmen' �bernommen werden.\n"
											+ 	"- Durch klicken und ziehen mit der Maus kann ein Bildauschnitt ausgew�hlt werden\n"
											+ 	"- Durch Shortcuts Strg-c & Strg-v oder unter Bearbeiten kann kopiert und eingef�gt werden.\n"
											+ 	"- Durch Shift-Klick auf die Vorschaubilder k�nnen diese f�r die SlideShow ausgew�hlt werden.", "Kurze Programmerkl�rung", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		/*
		 * ENDE MEN� HILFE
		 */
		
		
		
		/*
		 * Zusammenbau der Men�bar
		 */
		JMenu_myMenu_File.add(JMenuItem_OpenFile);
		JMenu_myMenu_File.add(JSep_mySep1);
		JMenu_myMenu_File.add(JMenuItem_CloseProgram);
		
		JMenu_myMenu_Edit.add(JMenuItem_copy);
		JMenu_myMenu_Edit.add(JMenuItem_paste);
		
		JMenu_myMenu_DiaShow.add(JMenu_myMenu_DiaShow);
		JMenu_myMenu_DiaShow.add(JMenuItem_start);
		
		JMenu_myMenu_Morphing.add(JCheckBoxMenuItem_BigSmall);
		JMenu_myMenu_Morphing.add(JCheckBoxMenuItem_Move);
		JMenu_myMenu_Morphing.add(JCheckBoxMenuItem_Rotate);
		JMenu_myMenu_Morphing.add(JCheckBoxMenuItem_Shear);
		JMenu_myMenu_Morphing.addSeparator();
		JMenu_myMenu_Morphing.add(JCheckBoxMenuItem_BresenhamLine);
		JMenu_myMenu_Morphing.add(JCheckBoxMenuItem_BresenhamCircle);
		JMenu_myMenu_Morphing.add(JCheckBoxMenuItem_BresenhamFilledCircle);
		JMenu_myMenu_Morphing.addSeparator();
		JMenu_myMenu_Morphing.add(JCheckBoxMenuItem_ReplaceColors);
		
		JMenu_myMenu_Histogram.add(JMenuItem_histogramShow);
		
		JMenu_myMenu_AccepttoCallerImage.add(JMenuItem_accept);
		
		JMenu_myMenu_Help.add(JMenuItem_myMenu_Help);
		
		
		JMenuBar_myMenubar.add(JMenu_myMenu_File);
		JMenuBar_myMenubar.add(JMenu_myMenu_Edit);
		JMenuBar_myMenubar.add(JMenu_myMenu_DiaShow);
		JMenuBar_myMenubar.add(JMenu_myMenu_Morphing);
		JMenuBar_myMenubar.add(JMenu_myMenu_Histogram);
		JMenuBar_myMenubar.add(JMenu_myMenu_AccepttoCallerImage);
		JMenuBar_myMenubar.add(JMenu_myMenu_Help);
		setJMenuBar(JMenuBar_myMenubar);
		/*
		 * ENDE ZUSAMMENBAU MEN�BAR
		 */
		
		// --> JPanel f�r ober und untere Splitpane 
		JPanel_upper.setLayout(new BorderLayout());
		JPanel_lower.setLayout(new FlowLayout());
		
		
		
		/*
		 * ActionListener f�r den Button, welcher bei der 
		 * Auswahl von Morphing im NORTH-BORDER auftaucht
		 */
		optionPanel.button.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (JCheckBoxMenuItem_Move.isSelected() == true )
				{
					System.out.println("Bewegen");
					int [] intArrAktuell =  presentationLayer_ActualPresentationImage.myPicture_caller.intArrImgShowBig;
					int intAktuellBreite = presentationLayer_ActualPresentationImage.dimImgShowThisImage.width;
					int intAktuellHoehe = presentationLayer_ActualPresentationImage.dimImgShowThisImage.height;
					
					intArrAktuell = myImageMorph.getMovedImageArr(intArrAktuell, intAktuellBreite, intAktuellHoehe, Double.parseDouble(optionPanel.spinnerX.getValue().toString()), Double.parseDouble(optionPanel.spinnerY.getValue().toString()),selection);
					presentationLayer_ActualPresentationImage.imgShowThisImage = ImageActions.getImageFromArray(intArrAktuell,intAktuellBreite, intAktuellHoehe);
	
				}
				else if (JCheckBoxMenuItem_BigSmall.isSelected() == true )
				{
					System.out.println("Vergr��ern/Verkleinern");
					
					int [] intArrAktuell =  presentationLayer_ActualPresentationImage.myPicture_caller.intArrImgShowBig;
					int intAktuellBreite = presentationLayer_ActualPresentationImage.dimImgShowThisImage.width;
					int intAktuellHoehe = presentationLayer_ActualPresentationImage.dimImgShowThisImage.height;
					
					intArrAktuell = myImageMorph.getScaledImageArr(intArrAktuell, intAktuellBreite, intAktuellHoehe, Double.parseDouble(optionPanel.spinnerX.getValue().toString()), Double.parseDouble(optionPanel.spinnerX.getValue().toString()) ,selection);
					presentationLayer_ActualPresentationImage.imgShowThisImage = ImageActions.getImageFromArray(intArrAktuell, intAktuellBreite, intAktuellHoehe);
				
					
				}
				else if (JCheckBoxMenuItem_Rotate.isSelected() == true )
				{
					System.out.println("Rotieren");
					int [] intArrAktuell =  presentationLayer_ActualPresentationImage.myPicture_caller.intArrImgShowBig;
					int intAktuellBreite = presentationLayer_ActualPresentationImage.dimImgShowThisImage.width;
					int intAktuellHoehe = presentationLayer_ActualPresentationImage.dimImgShowThisImage.height;
					
					intArrAktuell = myImageMorph.getRotatedImageArr(intArrAktuell, intAktuellBreite, intAktuellHoehe, Double.parseDouble(optionPanel.spinnerX.getValue().toString()), 0,selection, presentationLayer_ActualPresentationImage);
					presentationLayer_ActualPresentationImage.imgShowThisImage = ImageActions.getImageFromArray(intArrAktuell,intAktuellBreite, intAktuellHoehe);

					
				}
				else if (JCheckBoxMenuItem_Shear.isSelected() == true )
				{
					System.out.println("Scheren");
					int [] intArrAktuell =  presentationLayer_ActualPresentationImage.myPicture_caller.intArrImgShowBig;
					int intAktuellBreite = presentationLayer_ActualPresentationImage.dimImgShowThisImage.width;
					int intAktuellHoehe = presentationLayer_ActualPresentationImage.dimImgShowThisImage.height;
					
					intArrAktuell = myImageMorph.getShearedImageArr(presentationLayer_ActualPresentationImage.intArrImgShowThisImage, presentationLayer_ActualPresentationImage.dimImgShowThisImage.width, presentationLayer_ActualPresentationImage.dimImgShowThisImage.height, Double.parseDouble(optionPanel.spinnerX.getValue().toString()), Double.parseDouble(optionPanel.spinnerY.getValue().toString()), selection);
					
					presentationLayer_ActualPresentationImage.imgShowThisImage = ImageActions.getImageFromArray(intArrAktuell, intAktuellBreite, intAktuellHoehe);
				}
				else if (JCheckBoxMenuItem_ReplaceColors.isSelected() == true )
				{
					
					Image imtImageFromPresenationArr = ImageActions.getImageFromArray(presentationLayer_ActualPresentationImage.intArrImgShowThisImage, 500, 600);
					ReplaceColors _histo = new ReplaceColors(
							imtImageFromPresenationArr,
							presentationLayer_ActualPresentationImage.getWidth(),
							presentationLayer_ActualPresentationImage.getHeight()
							);

					_histo.intPercentToReduceColors = (double)optionPanel.spinnerX.getValue()/100;
		    		_histo.loadImage(imtImageFromPresenationArr);
		    	
		    		
		    	

		    		optionPanel.getjTextField1_HISTO().setText(""+_histo.getIntCountColorsToKeep());
		    		presentationLayer_ActualPresentationImage.imgShowThisImage = _histo.myImage;
		    		repaint();
					
				}
				
				repaint();
			}
		});
		

		JScrollPane jScrollPane_Lower = new  JScrollPane(JPanel_lower);
		
		/*
		 *  JSplitPane: 
		 */
		JSplitPane_mySplit1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, JPanel_upper, jScrollPane_Lower);
		JSplitPane_mySplit1.setSize(dimWindowSize);
		JSplitPane_mySplit1.setDividerLocation(490);
		/*
		 *  JSplitPane <--
		 */
		
				
		JPanel_NORTH.add(optionPanel);
		add(JPanel_NORTH,BorderLayout.NORTH);
		add(JSplitPane_mySplit1, BorderLayout.CENTER);
		

		setVisible(true);

		
		//Men�punkte deaktivieren
		disableEnableMenu(new JMenu [] {JMenu_myMenu_Edit,JMenu_myMenu_DiaShow,JMenu_myMenu_Morphing,JMenu_myMenu_AccepttoCallerImage,JMenu_myMenu_Histogram}, false);
	}
	
	
	
	/*
	 * Deaktivieren der Men�s.
	 */
	public void disableEnableMenu(JMenu [] allMenu, boolean on)
	{
		for (JMenu jMenu : allMenu) {
			if (on == true)
				jMenu.setEnabled(true);
			else
				jMenu.setEnabled(false);
		}
	}

	
	
}
