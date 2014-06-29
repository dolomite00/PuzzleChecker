package jSudoku.model;

/**
 * Copyright 2008, POET Lab (Dr. Deborah Tatar) @ Virginia Tech, Blacksburg, VA
 *
 * Permission to use and modify this software and its documentation for
 * any purpose other than its incorporation into a commercial product is
 * hereby granted without fee.  Permission to copy and distribute this
 * software and its documentation only for non-commercial use is also
 * granted without fee, provided, however, that the above copyright notice
 * appear in all copies, that both that copyright notice and this permission
 * notice appear in supporting documentation, that the name of POET Lab 
 * (Dr. Deborah Tatar) not be used in advertising or publicity pertaining to
 * distribution of the software without specific, written prior permission,
 * and that the person doing the distribution notify POET Lab (Dr. Deborah Tatar) of
 * such distributions outside of his or her organization. POET Lab (Dr. Deborah Tatar)
 * makes no representations about the suitability of this software for
 * any purpose.  It is provided "as is" without express or implied warranty.
 * POET Lab (Dr. Deborah Tatar) requests notification of any modifications to this
 * software or its documentation.
 *
 * Send the following redistribution information:
 *
 *      Name:
 *      Organization:
 *      Address (postal and/or electronic):
 *
 * To:
 *      Dr. Deborah Tatar
 *      Computer Science Department
 *      Virginia Polytechnic Institute and State University
 *      Blacksburg, VA 24061
 *
 *      Joon Suk Lee
 *      Computer Science Department
 *      Virginia Polytechnic Institute and State University
 *      Blacksburg, VA 24061
 *
 *              or
 *
 *		tatar@cs.vt.edu
 *      joonlee@vt.edu
 *
 * We will acknowledge all electronic notifications.
 */

import java.awt.Color;
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