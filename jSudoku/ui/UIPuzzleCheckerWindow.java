package jSudoku.ui;

import jSudoku.*;
import jSudoku.control.GridBoardController;
import jSudoku.model.*;
import jSudoku.util.screen2image;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;



public class UIPuzzleCheckerWindow extends JFrame implements UISelectionRectHolder {
	
	static final long serialVersionUID = 1;
	
	public  static Image PROGRAM_ICON_IMG = null;
	
// ******************************************************************************
//
// Constructor
//
// ******************************************************************************
	/**
	 * Constructor is made private so that an instance of this UI class can only be built by calling a class
	 * method (static method), <i><b>run</b></i>. <p> Since this class is an UI class, event-dispatching thread is used in <i>run</i> method.
	 * 
	 * @param  none
	 * @return N/A
	 */		
	private UIPuzzleCheckerWindow() {
		buildGUI();
	}
	
// ******************************************************************************
//
// GUI creating methods
//
// ******************************************************************************
	/**
	 * This class builds UI.
	 * 
	 * @param  none
	 * @return void
	 */	
	private void buildGUI() {		
		PROGRAM_ICON_IMG = buildImage(CONSTVALUE.PROGRAM_ICON);
		if (PROGRAM_ICON_IMG != null)
			setIconImage(PROGRAM_ICON_IMG);
		
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setResizable(false);
	    setTitle("JSUDOKU Puzzle Checker" );
	    
	    //	handles event when window closed by the user
	    addWindowListener(new WindowAdapter() {
	    			public void windowClosing(WindowEvent e) {
	    				closeProgram();
	    			}
	    });
	    
	    Container pane = getContentPane();        
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		buildMenuBar();
		buildTopAndBottomPanel();
		buildPuzzleArea();
		buildMsgLabel();
		addUIComponentsToMasterPane(pane);	

		pack();		
	}
	
	private void addUIComponentsToMasterPane(Container pane) {
		topPanel.add(puzzleArea);
		bottomPanel.add(msgArea);
		pane.add(topPanel);
		pane.add(bottomPanel);		
	}

	private void buildTopAndBottomPanel() {
		topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.PAGE_AXIS));
	}
	
	private void buildPuzzleArea() {
		puzzleArea = new JPanel();
		puzzleArea.setBorder(BorderFactory.createTitledBorder("Puzzle:"));
		puzzleArea.setPreferredSize(new Dimension(CONSTVALUE.PUZZLE_GRID_WIDTH, CONSTVALUE.PUZZLE_GRID_HEIGHT));
		gridArea = new UIPuzzleCheckerSelectionRect(this);
		gridArea.setPreferredSize(new Dimension(CONSTVALUE.PUZZLE_GRID_WIDTH-CONSTVALUE.PUZZLE_GRID_OFFSET, CONSTVALUE.PUZZLE_GRID_HEIGHT-CONSTVALUE.PUZZLE_GRID_OFFSET));
		puzzleArea.add(gridArea, BorderLayout.CENTER);
	}

	private void buildMsgLabel() {
		msgArea = new JLabel();
		msgArea.setPreferredSize(new Dimension(bottomPanel.getWidth(), CONSTVALUE.MSG_LABEL_HEIGHT));
		msgArea.setBorder(BorderFactory.createEmptyBorder(10,10,0,10));
		msgArea.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		msgArea.setAlignmentY(JLabel.CENTER_ALIGNMENT);
	}

	private void buildMenuBar() {
		jPuzzleMenuBar			= new JMenuBar();
		
		jMenuFile			= new JMenu();
		
		jMenuItemNew 		= new JMenuItem();
		jMenuItemOpenFile	= new JMenuItem();
		jMenuItemClose		= new JMenuItem();
		jMenuItemBoardCheck	= new JMenuItem();
		jMenuItemCapture	= new JMenuItem();
		jMenuItemExit		= new JMenuItem();
		
		jMenuFile.setText("Game");
		
		jMenuItemNew.setText("New...");	
		jMenuItemOpenFile.setText("Open File...");
		jMenuItemClose.setText("Close");
		jMenuItemBoardCheck.setText("Check Board");	
		jMenuItemCapture.setText("Capture");	
		jMenuItemExit.setText("Exit");

		jMenuItemNew.setIcon(buildIcon(CONSTVALUE.FILENEW_ICON));
		jMenuItemOpenFile.setIcon(buildIcon(CONSTVALUE.FILEOPEN_ICON));
		jMenuItemClose.setIcon(buildIcon(CONSTVALUE.FILECLOSE_ICON));
		
		
		jMenuItemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				performWindowClosing(evt);
			}
		});

		jMenuItemBoardCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				performCheckBoard(evt);
			}
		});

		jMenuItemCapture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				performCapture(evt);
			}
		});

		jMenuItemClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				performFileClose(evt);
			}
		});

		jMenuItemOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				performFileOpen(evt);
			}
		});

		jMenuItemNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				performFileNew(evt);
			}
		});

		jMenuFile.add(jMenuItemNew);
		jMenuFile.add(jMenuItemOpenFile);
		jMenuFile.add(new JSeparator());
		jMenuFile.add(jMenuItemClose);
		jMenuFile.add(new JSeparator());
		jMenuFile.add(jMenuItemBoardCheck);
		jMenuFile.add(jMenuItemCapture);
		jMenuFile.add(new JSeparator());
		jMenuFile.add(jMenuItemExit);
        
        jPuzzleMenuBar.add(jMenuFile);

        setJMenuBar(jPuzzleMenuBar);
	}	


	protected Image buildImage(String image_name) {
		Image image = null;
		URL url = getClass().getResource(CONSTVALUE.JAR_RESOURCES_PATH + image_name); 
		if (url != null)
			image = Toolkit.getDefaultToolkit().getImage(url);
		else
			image = Toolkit.getDefaultToolkit().getImage(CONSTVALUE.RESOURCES_PATH + image_name);
		
		return image;
	}
	
	protected ImageIcon buildIcon(String icon_name) {
		ImageIcon icon = null;

		Image image = buildImage(icon_name);

		if (image != null)
			icon = new ImageIcon(image);
	
		return icon;
	}
	
// ******************************************************************************
//
// GUI manipulating methods
//
// ******************************************************************************
	public void updateLabel(String txt) {
		msgArea.setText(txt);
	}
		
// ******************************************************************************
//
// UISelectionRectHolder Interface Methods
//
// ******************************************************************************
    	
// ******************************************************************************
//
// events driven action methods (perform*** methods)
//
// ******************************************************************************
	private void performWindowClosing(ActionEvent evt) {  
    	this.processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }       

    private void performCheckBoard(ActionEvent evt) {  
    	DMPuzzle puzzle = DMPuzzle.getPuzzle();
    	
    	
    	if (puzzle == null) {
    		updateLabel("There is no puzzle opened for saving...");
    		return;
    	}
    	
    	puzzle.clearConflicts();
    	
    	String countConflict = "";
        if (GridBoardController.checkConflicts(puzzle))
        	countConflict = "Conflict(yes) ";
        else
        	countConflict = "Conflict(no) ";  
        
        String resultCorrectness = GridBoardController.checkCorrectness(puzzle);
        updateLabel(countConflict + resultCorrectness);
        
        gridArea.repaint();
    }       


    private void performCapture(ActionEvent evt) {   
        try {
            Thread.sleep(1000 * 2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        
        screen2image.capture();
    }       
    
    private void performFileClose(ActionEvent evt) {  
    	DMPuzzle.clearPuzzle(); 
    	gridArea.repaint();
    	updateLabel("Puzzle Closed");
    }           

    private void performFileOpen(ActionEvent evt) {  
    	performFileClose(evt);
    	
        FileDialog fd = new FileDialog(this, "Open Puzzle File", FileDialog.LOAD);
        fd.setFile("*.puz");
        fd.setVisible(true);
        fd.setModal(true);
        String filename = fd.getDirectory() + fd.getFile();
        if (filename != null && DMPuzzle.readFromFile(filename)) {
        	DMPuzzle puzzle = DMPuzzle.getPuzzle();
        	puzzle.lockPuzzle();
        	//puzzle.unlockPuzzle();
        	puzzle.setPuzzle_filename(filename);
        	updateLabel("File " + filename + " opened.");        	
        } else
        	updateLabel("File not opened");    
        gridArea.repaint();
    }       

    private void performFileNew(ActionEvent evt) {  
    	DMPuzzle.setPuzzle(DMPuzzle.createNewPuzzle());
    	String str = "Created " + "a new puzzle.";
    	updateLabel(str);
    	gridArea.updateUI();
    }   

// ******************************************************************************
//
// Auxiliary Action Methods... 
//
// ******************************************************************************    
    private void closeProgram() {
    	
    }

// ******************************************************************************
//
// Run
//
// ******************************************************************************    
	public static void run () {
	    //Schedule a job for the event-dispatching thread:
	    //creating and showing this application's GUI.
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	new UIPuzzleCheckerWindow().setVisible(true);
	        }
	    });
	}
	
// ******************************************************************************
//
// GUI Variables declaration
//
// ******************************************************************************	
	private JMenuBar 	jPuzzleMenuBar = null;
	private JMenu 		jMenuFile = null;
	private JMenuItem 	jMenuItemNew = null;
	private JMenuItem 	jMenuItemOpenFile = null;
	private JMenuItem 	jMenuItemClose = null;
	private JMenuItem 	jMenuItemBoardCheck = null;
	private JMenuItem 	jMenuItemCapture = null;
	private JMenuItem 	jMenuItemExit = null;
	
	private JPanel topPanel = null;
	private JPanel bottomPanel = null;
	
	private JPanel puzzleArea = null;
	private JLabel msgArea = null;
	private UISelectionRect gridArea = null;
}
