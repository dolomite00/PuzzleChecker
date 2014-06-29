package jSudoku.model;

import java.awt.Rectangle;
import java.io.Serializable;
import java.util.Vector;

public class DMCell implements Serializable {

	/* serializable class version number */
	private static final long serialVersionUID = 01L;
	
	private int cellID = 0;
	private Rectangle rect = null;
	private boolean fixedCell = false;
	private boolean selected = false;
	private boolean highlightSelected = false;
	private boolean isFilled = false;
	private int value = -1;
	private DMBox box = null;
	private Vector<DMNote> noteList = null;
	private int colorCode = -1;
	private boolean conflict = false;
	private boolean correct = false;
	
// ******************************************************************************
//
// Constructor
//
// ******************************************************************************
	public DMCell() {
		noteList = new Vector<DMNote>();
	}

// ******************************************************************************
//
// cell data related methods
//
// ******************************************************************************
	public void addNote(int val, int color) {
		if (isFixedCell()) return;
		if (noteListContains(val)) return;
		noteList.add(new DMNote(val,color));
	}
	
	public void removeNote(int val) {
		if (isFixedCell()) return;
		for (DMNote num : noteList) {
			if (num.getValue() == val) {
				noteList.removeElement(num);
				return;
			}
		}
	}

	public DMNote getNote(int val) {
		if (isFixedCell()) return null;
		for (DMNote num : noteList) {
			if (num.getValue() == val)
				return num;
		}
		return null;
	}
	
	public Vector<DMNote> getGuessList() {
		return noteList;
	}
	
	public void clearNoteList() {
		noteList.clear();
	}

	public void clearCellValue() {
		setValue(-1);
	}
	
	public void clearCell() {
		clearNoteList();
		clearCellValue();
	}
	
	public void deleteLastEntryFromNoteList() {
		if (noteList.size() > 0)
			noteList.removeElementAt(noteList.size()-1);
	}
	
	public boolean noteListContains(int val) {
		for (DMNote num : noteList) {
			if (num.getValue() == val)
				return true;
		}
		return false;
	}
// ******************************************************************************
//
// Setters & Getters
//
// ******************************************************************************
	
	public void setCellID(int cellID) {
		this.cellID = cellID;
	}

	public int getCellID() {
		return cellID;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	public void setRect(Rectangle rect) {
		this.rect = rect;
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setBox(DMBox box) {
		this.box = box;
	}


	public DMBox getBox() {
		return box;
	}


	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setHighlightSelected(boolean highlightSelected) {
		this.highlightSelected = highlightSelected;
	}

	public boolean isHighlightSelected() {
		return highlightSelected;
	}

	public void setFixedCell(boolean fixedCell) {
		this.fixedCell = fixedCell;
	}


	public boolean isFixedCell() {
		return fixedCell;
	}

	public void setColorCode(int colorCode) {
		this.colorCode = colorCode;
	}

	public int getColorCode() {
		return colorCode;
	}

	public void setFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}

	public boolean isFilled() {
		return isFilled;
	}

	public boolean isConflict() {
		return conflict;
	}

	public void setConflict(boolean conflict) {
		this.conflict = conflict;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
}