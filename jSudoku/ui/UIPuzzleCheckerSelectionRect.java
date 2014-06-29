package jSudoku.ui;

import jSudoku.control.GridBoardController;
import jSudoku.model.DMBox;
import jSudoku.model.DMCell;
import jSudoku.model.DMPuzzle;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;


public class UIPuzzleCheckerSelectionRect extends UISelectionRect{
	
	protected static final long serialVersionUID = 1;

	private final static Color RED = new Color(185,0,0);
	
// ******************************************************************************
//
// constructor
//
// ******************************************************************************
	public UIPuzzleCheckerSelectionRect(UISelectionRectHolder controller) {
		super(controller);

		MyKeyListener myKeyListener = new MyKeyListener();
		addKeyListener(myKeyListener);
		
		MyMouseListener myListener = new MyMouseListener();
		addMouseListener(myListener);
		addMouseMotionListener(myListener);			
	}
	
// ******************************************************************************
//
// KeyListener inner class
//
// ******************************************************************************
	protected class MyKeyListener  implements KeyListener {
		public void keyPressed(KeyEvent evt) {
			if (DMPuzzle.getPuzzle() == null) return;
			
			int key = evt.getKeyCode();
			if (key == KeyEvent.VK_ESCAPE) {
				GridBoardController.clearSelectedCellValue(DMPuzzle.getPuzzle());				
			}

			if (key >= KeyEvent.VK_1 && key <= KeyEvent.VK_9 ) {
				int color = -1;
				if (controller.getUserColor() == Color.RED)
					color = 1;
				else if (controller.getUserColor() == Color.CYAN)
					color = 2;
				else if (controller.getUserColor() == Color.GREEN)
					color = 3;
				GridBoardController.setSelectedCellValue(DMPuzzle.getPuzzle(), key, color);
			}
			controller.CheckBoard();			
			repaint();
		}
		
		public void keyReleased(KeyEvent evt) {

		}
		
		public void keyTyped(KeyEvent evt) {

		}
	}

// ******************************************************************************
//
// abstract UISelectionRect methods
//
// ******************************************************************************
	
	protected void processMousePointClick(Point point) {
		if (DMPuzzle.getPuzzle() != null)
			GridBoardController.locateCellAtMousePoint(DMPuzzle.getPuzzle(), point);
	}
	
	
// ******************************************************************************
//
// grid painting methods
//
// ******************************************************************************
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintGridBackgrounds(g);
		paintSelectedCell(g);
		paintFilledCell(g);
	}

	private void paintFilledCell(Graphics g) {
		DMPuzzle puzzle = DMPuzzle.getPuzzle();
		if (puzzle == null) return;
		
		for (DMCell[] row : puzzle.cells) {
			for (DMCell cell : row) {
				Rectangle rect = cell.getRect();
					drawWord(g,cell,rect);
			} //end of inner for loop			
		} // end of outer for loop
	}

	private void paintSelectedCell(Graphics g) {
		DMPuzzle puzzle = DMPuzzle.getPuzzle();
		if (puzzle == null) return;
		
		for (DMCell[] row : puzzle.cells) {
			for (DMCell cell : row) {
				Rectangle rect = cell.getRect();
				if (cell.isSelected()) {
					g.setColor(filledCellColor);
					g.fillRect(rect.x, rect.y, rect.width, rect.height);
					Graphics2D g2=(Graphics2D)g;
					g2.setStroke(new BasicStroke(2)); 
					g2.setColor(Color.black);
					g2.drawRect(rect.x, rect.y, rect.width, rect.height);
				}
			} //end of inner for loop			
		} // end of outer for loop
	}
	
	/**
	 * Paints Sudoku Puzzle Grids
	 * 
	 * @param Graphics g
	 * @return void
	 */
	private void paintGridBackgrounds(Graphics g) {

		DMPuzzle puzzle = DMPuzzle.getPuzzle();
		if (puzzle == null) return;

		for (DMCell[] row : puzzle.cells) {
			for (DMCell cell : row) {
				Rectangle rect = cell.getRect();
				if (cell.isConflict() && !cell.isFixedCell()) 
					g.setColor(RED);
				else if (!cell.isFixedCell() && cell.isFilled() && cell.isCorrect())
					g.setColor(Color.white);
				else if (!cell.isFixedCell() && cell.isFilled() && !cell.isCorrect())
					g.setColor(Color.pink);
				else
					g.setColor(Color.white);
				
				g.fillRect(rect.x, rect.y, rect.width, rect.height);

				Graphics2D g2=(Graphics2D)g;
				g2.setStroke(new BasicStroke(2)); 
				g2.setColor(Color.black);
				g2.drawRect(rect.x, rect.y, rect.width, rect.height);
			}
		}
		
		for (DMBox box : puzzle.boxes) {
			Rectangle rect = box.getRect();
			Graphics2D g2=(Graphics2D)g;
			g2.setStroke(new BasicStroke(4)); 
			g2.setColor(Color.black);
			g2.drawRect(rect.x, rect.y, rect.width, rect.height);			
		}
	}

	protected void drawWord(Graphics g, DMCell cell, Rectangle rect) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		
		if (cell.isFixedCell()) {
			g2.setColor(Color.BLACK);
			g2.setFont(fixedCellFont);
		} else {
			
			if (cell.getColorCode() == 1)
				g2.setColor(Color.MAGENTA);
			else if (cell.getColorCode() == 2)
				g2.setColor(Color.BLUE);
			else if (cell.getColorCode() == 3)
				g2.setColor(Color.GREEN);
			else
				g2.setColor(Color.GRAY);
			g2.setFont(fixedEntryFont);
		}
		
		 //... Find the size of this text so we can center it.
		FontMetrics fm = g2.getFontMetrics(fixedEntryFont);  // metrics for this object
		Integer val = cell.getValue();
		if (val >= 1 && val <= 9) {
			Rectangle2D rect2d = fm.getStringBounds(val.toString(), g2); // size of string
			int textHeight = (int)(rect2d.getHeight());
			int textWidth  = (int)(rect2d.getWidth());
			
			//... Center text horizontally and vertically
			int x = (rect.width - textWidth) / 2;
			int y = (rect.height - textHeight) / 2  + fm.getAscent();						
			g2.drawString(val.toString(), rect.x+x, rect.y+y);		
		}
	}
}