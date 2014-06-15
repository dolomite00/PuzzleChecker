package jSudoku.ui;

import jSudoku.CONSTVALUE;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;


public abstract class UISelectionRect extends JPanel {
	
	protected static final long serialVersionUID = 1;
	
	protected Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
	protected Cursor normalCursor  = new Cursor(Cursor.DEFAULT_CURSOR);
	protected Font fixedEntryFont = new Font("Helvetica", Font.PLAIN,  22);
	protected Font tempEntryFont = new Font("Helvetica", Font.ITALIC,  10);
	protected Font fixedCellFont = new Font("Helvetica", Font.BOLD,  22);
	protected Color filledCellColor = new Color (255,255,170);
	protected Color filledBorderColor = new Color (255,127,0);	
	protected Color selectedCellColor = new Color(85,127,255);
	protected Color selectedLightCellColor = new Color(162,184,252);
	protected Color selectedBorderColor = new Color(85,0,255);
	protected Color disabledBorderColor = new Color(50,50,50);
	protected Color disabledCellColor = new Color(70,70,70);
	
	protected Color conflictCellColor = new Color(200,10,10);
	protected Color conflictLightCellColor = new Color(200,126,126);
	protected Color conflictBorderColor = new Color(110,0,20);
	protected Color enteredCellColor = new Color(240,244,251);
	protected Color enteredBorderColor = new Color(2,27,78);
	protected Color disabledNselectedCellColor = new Color(195,195,195);
	protected Color disabledNselectedBorderColor = Color.YELLOW;
	protected Color inputSelectedCellColor = filledCellColor;
	protected Color inputSelectedBorderColor = filledBorderColor;
	protected Color inputCellColor = disabledNselectedCellColor;
	protected Color inputBorderColor = disabledBorderColor;
	
	
	protected Rectangle currentRect = null;
	protected Rectangle rectToDraw = null;
	protected Rectangle previousRectDrawn = new Rectangle();
	protected UISelectionRectHolder controller;

	protected Point mousePoint = null;
	protected boolean enabled = true;
	
// ******************************************************************************
//
// constructor
//
// ******************************************************************************
	/**
	 * This constructor takes a pointer to its controller and stores it in the instance.
	 * This pointer is used to call back its holder when a user selects a region in the grid 
	 * or fails to select any region in the grid.
	 * 
	 * @param  UISelectionRectHolder controller
	 * @return N/A
	 */		
	public UISelectionRect(UISelectionRectHolder controller) {
		this.controller = controller;
		setOpaque(true);
	}

// ******************************************************************************
//
// MouseInputAdapter inner class
//
// ******************************************************************************
	protected class MyMouseListener extends MouseInputAdapter {
		
		/**
		 * This method is invoked when a user clicks a mouse button.
		 * It initializes currentRect as a rectangle with zero length width and 
		 * zero length height.
		 * It calls updateDrawableRect method.
		 *    
 		 * @param  MouseEvent evt
		 * @return void
		 */		
		public void mousePressed(MouseEvent evt) {
			int x = evt.getX();
			int y = evt.getY();
			currentRect = new Rectangle(x,y,0,0);
			updateDrawableRect(getWidth(), getHeight());
			repaint();			
		}
		
		/**
		 * This method is invoked when a user drags a mouse while pressing down mouse button.
		 * It calls updateSize method.
		 *    
 		 * @param  MouseEvent evt
		 * @return void
		 */		
		public void mouseDragged(MouseEvent evt) {
			updateSize(evt);			
		}

		/**
		 * This method updates currentRect.
		 *    
 		 * @param  MouseEvent evt
		 * @return void
		 */		
		protected void updateSize(MouseEvent evt) {
			if (currentRect == null) return;
			
			int x = evt.getX();
			int y = evt.getY();			
			currentRect.setSize(x - currentRect.x, y - currentRect.y);
			if (CONSTVALUE._DEBUG_FLAG_LEVEL_5_) 
				System.out.println("selectionRect - updateSize x: " + (x - currentRect.x) + " y: " + (y - currentRect.y));
			updateDrawableRect(getWidth(), getHeight());
			repaint();		
		}
		
		/**
		 * This method is invoked when a user clicks a mouse.
		 *    
 		 * @param  MouseEvent evt
		 * @return void
		 */		
		public void mouseClicked(MouseEvent evt){
			requestFocus();
			mousePoint = evt.getPoint();
			processMousePointClick(mousePoint);
		}

		/**
		 * This method is invoked when a user releases a mouse button. 
		 * This method verifies if the user selected region is valid. 
		 * If so, it selects the puzzle region. If not, it clears the grid.
		 * Users can select puzzle region in two ways. First is to click a point in the grid and
		 * select corresponding region. Second is to draw a selection rect to select 
		 * a valid puzzle region within the selection rect. 
		 *    
 		 * @param  MouseEvent evt
		 * @return void
		 */		
		public void mouseReleased(MouseEvent evt) {

			if (rectToDraw == null) {
				System.err.println("null rect to draw");
				return;
			}
			
			if (evt.getX() != currentRect.x && evt.getY() != currentRect.y && 
					Math.abs(evt.getX() - currentRect.x) < 10 && Math.abs(evt.getY() - currentRect.y) < 10) {
				// Check for a point
				rectToDraw.height = 0;
				rectToDraw.width = 0;

				int x = evt.getX();
				int y = evt.getY();
				Point point = new Point(x,y);
				processMousePointClick(point);
			} else {
				// Check for a region
				updateSize(evt);
				rectToDraw.height = 0;
				rectToDraw.width = 0;
				
				processHighlighter();
			}
			repaint();
		}
	} 

// ******************************************************************************
//
// Abstract methods
//
// ******************************************************************************
	protected void processHighlighter() {
	}

	protected abstract void processMousePointClick(Point point);

// ******************************************************************************
//
// Setter and Getter
//
// ******************************************************************************

	public boolean getEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean setval) {
		enabled = setval;
	}
	
// ******************************************************************************
//
// updateDrawableRect
//
// ******************************************************************************
	protected void updateDrawableRect(int compWidth, int compHeight) {
		int x = currentRect.x;
		int y = currentRect.y;
		int width = currentRect.width;
		int height = currentRect.height;
		
		if (CONSTVALUE._DEBUG_FLAG_LEVEL_5_) {
			System.out.println("x =>" + x);
			System.out.println("y =>" + y);
			System.out.println("width =>" + width);
			System.out.println("height =>" + height);
		}

		if (width < 0) {
			width = 0 - width;
			x = x - width + 1;
			if (x < 0) {
				width += x;
				x = 0;
			}
		}
		if (height < 0) {
			height = 0 - height;
			y = y - height + 1;
			if (y < 0) {
				height += y;
				y = 0;
			}
		}
		
		if ((x+width) > compWidth) {
			width = compWidth - x;
		}
		if ((y+height) > compHeight) {
			height = compHeight - y;			
		}
		
		if (rectToDraw != null) {
			previousRectDrawn.setBounds(rectToDraw.x, rectToDraw.y, rectToDraw.width, rectToDraw.height);
			rectToDraw.setBounds(x,y,width,height);
		} else {
			rectToDraw = new Rectangle(x,y,width, height);
		}
	}
}