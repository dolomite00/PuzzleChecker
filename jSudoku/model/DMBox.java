package jSudoku.model;

import java.awt.Rectangle;
import java.io.Serializable;

public class DMBox implements Serializable {

	/* serializable class version number */
	private static final long serialVersionUID = 01L;
	
	private int boxID = 0;
	private Rectangle rect = null;
	private boolean selected = false;
//	private Vector<DMCell> celllist;
	
	
// ******************************************************************************
//
// Constructor
//
// ******************************************************************************
	public DMBox(int id) {
		this.setBoxID(id);
	}
	
// ******************************************************************************
//
// Setters & Getters
//
// ******************************************************************************
	public void setRect(Rectangle rect) {
		this.rect = rect;
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isSelected() {
		return selected;
	}
	
	private void setBoxID(int boxID) {
		this.boxID = boxID;
	}

	public int getBoxID() {
		return boxID;
	}
}