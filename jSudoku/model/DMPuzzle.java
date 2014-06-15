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

import jSudoku.CONSTVALUE;

import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;


public class DMPuzzle implements Serializable {

	/* serializable class version number */
	private static final long serialVersionUID = 01L;
	
	/* transient variables */
	transient private static DMPuzzle puzzle = null;
	transient private String puzzle_filename = null;
	transient public static final int NUM_OF_ROW = 9;
	transient public static final int NUM_OF_COLUMN = 9;
	transient public static final int NUM_OF_BOX = 9;

	transient public final static String CELL_TYPE = "CELL";
	transient public final static String BOX_TYPE = "BOX";
	transient public final static String ROW_TYPE = "ROW";
	transient public final static String COL_TYPE = "COL";
	
	/* puzzle entry related transient constants */
	transient public static final int NO_CELL_SELECTED = 0;
	transient public static final int SINGLE_CELL_SELECTED = 1;
	transient public static final int BOX_SELECTED = 2;
	transient public static final int ROW_SELECTED = 3;
	transient public static final int COLUMN_SELECTED = 4;
	
	transient private static int selectionType = NO_CELL_SELECTED;
		
	public DMCell cells[][] = null;
	public DMBox boxes[] = null;
	public DMBox rows[] = null;
	public DMBox columns[] = null;
	public Vector<DMPoint> pointList = new Vector<DMPoint>();
	public Vector<DMHighlight> highlightList = new Vector<DMHighlight>();
	private DMPoint mousePoint = null;

// ******************************************************************************
//
// Constructor
//
// ******************************************************************************
	private DMPuzzle() {		
		cells = new DMCell[NUM_OF_ROW][NUM_OF_COLUMN];
		boxes = new DMBox[NUM_OF_BOX];
		rows = new DMBox[NUM_OF_BOX];
		columns = new DMBox[NUM_OF_BOX];
		int x = 0;
		int y = 0;
		int width = (CONSTVALUE.PUZZLE_GRID_WIDTH - CONSTVALUE.PUZZLE_GRID_OFFSET)/ NUM_OF_ROW;
		int height = (CONSTVALUE.PUZZLE_GRID_HEIGHT - CONSTVALUE.PUZZLE_GRID_OFFSET)/ NUM_OF_COLUMN;
//		int numofcells = 0;
		int cellID = 0;
		
		for (int i = 0; i < NUM_OF_ROW; i++){
			for (int j = 0; j < NUM_OF_COLUMN; j++) {
//				numofcells++;
				cells[i][j] = new DMCell();
				cells[i][j].setRect(new Rectangle(x,y,width,height)); 
				cells[i][j].setCellID(++cellID);
				cells[i][j].setConflict(false);
				x += width;
			}
			y += height;
			x = 0;
		}
		
		for (int i = 0; i < 9; i++) {
			rows[i] = new DMBox(i);
			columns[i] = new DMBox(i);
			boxes[i] = new DMBox(i);
		}
		
		Vector<DMCell> celllist = new Vector<DMCell>();

		for (int offset_i = 0; offset_i < 3; offset_i++)
			for (int offset_j = 0; offset_j < 3; offset_j++) {
				celllist.clear();
				Rectangle boxRect = new Rectangle();
				for (int i = 3*offset_i + 0; i < 3*offset_i + 3; i++) {
					for (int j = 3*offset_j + 0; j < 3*offset_j + 3; j++) {
					celllist.add(cells[i][j]);
					cells[i][j].setBox(boxes[offset_i*3 + offset_j]);
					if (boxRect.isEmpty()) {
						Rectangle initRect = cells[i][j].getRect();
						boxRect.setRect(initRect.x, initRect.y, initRect.width, initRect.height);
					} else 
						boxRect.add(cells[i][j].getRect());
					}
				}			
				boxes[offset_i*3 + offset_j].setRect(boxRect);
			}
		
		
		for (int i = 0; i < NUM_OF_BOX; i++) {
			Rectangle boxRect = new Rectangle();
			celllist.clear();
			for (int j= 0; j < NUM_OF_COLUMN; j++) {
				if (boxRect.isEmpty()) {
					Rectangle initRect = cells[i][j].getRect();
					boxRect.setRect(initRect.x, initRect.y, initRect.width, initRect.height);
				} else 
					boxRect.add(cells[i][j].getRect());
				celllist.add(cells[i][j]);
				if (CONSTVALUE._DEBUG_FLAG_LEVEL_4_)
					System.out.println("cell is cell[" + i + " , "+j +"]");
			}
			
			if (CONSTVALUE._DEBUG_FLAG_LEVEL_4_)
				System.out.println("added to rows[" + i + "]");
			rows[i].setRect(boxRect);
		}
		
		for (int i = 0; i < NUM_OF_BOX; i++) {
			Rectangle boxRect = new Rectangle();
			celllist.clear();
			for (int j= 0; j < NUM_OF_ROW; j++) {
				if (boxRect.isEmpty()) {
					Rectangle initRect = cells[j][i].getRect();
					boxRect.setRect(initRect.x, initRect.y, initRect.width, initRect.height);
				} else 
					boxRect.add(cells[j][i].getRect());

				celllist.add(cells[j][i]);
			}
			columns[i].setRect(boxRect);
		}

		setPuzzle(this);
	}
	
// ******************************************************************************
//
// program logic methods
//
// ******************************************************************************
	public static DMPuzzle createNewPuzzle() {
		return new DMPuzzle();
	}
		
	public static void clearPuzzle() {
		puzzle = null;
		selectionType = NO_CELL_SELECTED;		
	}

	public void lockPuzzle() {
		for (DMCell[] celllist : puzzle.cells) 
			for (DMCell cell: celllist){
				if (cell.isFilled())
					cell.setFixedCell(true);
		}
	}

	public void unlockPuzzle() {
		for (DMCell[] celllist : puzzle.cells) 
			for (DMCell cell: celllist){
				if (cell.isFixedCell())
					cell.setFixedCell(false);
		}
	}
	
	public void clearHighlightSelection() {
		for (DMCell[] celllist : puzzle.cells) 
			for (DMCell cell: celllist){
				cell.setHighlightSelected(false);
		}		
	}

	public void clearConflicts() {
		for (DMCell[] celllist : puzzle.cells) 
			for (DMCell cell: celllist){
				cell.setConflict(false);
		}		
	}

	public void addHighlight(String owner, String type, int index, int colorCode) {
		DMHighlight highlight = null;
		if (type.equals(CELL_TYPE)) {
			int x = (index - 1) / DMPuzzle.NUM_OF_ROW;
			int y = (index - 1) % DMPuzzle.NUM_OF_ROW;
			Rectangle rect = cells[x][y].getRect();
			highlight = new DMHighlight(owner, rect, colorCode);			
		} else if (type.equals(ROW_TYPE)) {
			Rectangle rect = rows[index].getRect();
			highlight = new DMHighlight(owner, rect, colorCode);
		} else if (type.equals(COL_TYPE)) {
			Rectangle rect = columns[index].getRect();
			highlight = new DMHighlight(owner, rect, colorCode);
		} else if (type.equals(BOX_TYPE)) {
			Rectangle rect = boxes[index].getRect();
			highlight = new DMHighlight(owner, rect, colorCode);
		}		
		
		if (highlight != null)
			highlightList.add(highlight);
	}
	
	public void removeHighlight(String owner) {
		int index = -1;
		
		for (DMHighlight target : highlightList) {
			if (owner.equals(target.getOwner())) {
				index = highlightList.indexOf(target);
			}
		}
		
		if (index >= 0)
			highlightList.remove(index);
	}

	public void addPoint(String owner, int x, int y, int colorCode) {
		for (DMPoint point : pointList) {
			if (owner.equals(point.getOwner())) {
				point.setPoint(x, y);
				point.setColorCode(colorCode);
				return;
			}
		}
		
		DMPoint new_point = new DMPoint(owner, x, y, colorCode); 
		pointList.add(new_point);
	}
	
	public void removePoint(String owner, int x, int y, int colorCode) {
		int index = -1;
		
		for (DMPoint point : pointList) {
			if (owner.equals(point.getOwner()) && x == point.getXPosition() 
					&& y == point.getYPosition() && colorCode == point.getColorCode()) {
				index = pointList.indexOf(point);
			}
		}
		
		if (index >= 0)
			pointList.remove(index);
	}

	public void removePoint(String owner) {
		int index = -1;
		
		for (DMPoint point : pointList) {
			if (owner.equals(point.getOwner())) {
				index = pointList.indexOf(point);
			}
		}
		
		if (index != 0)
			pointList.remove(index);
	}
	
	public DMPoint getMousePoint() {
		return mousePoint;
	}
	
	public void setMousePoint(String owner, int x, int y, int colorCode) {
		if (mousePoint == null) {
			mousePoint = new DMPoint(owner, x, y, colorCode);
		} else {
			mousePoint.setOwner(owner);
			mousePoint.setPoint(x, y);
			mousePoint.setColorCode(colorCode);
		}
	}
	
	public void removeMousePoint() {
		mousePoint = null;
	}
	
// ******************************************************************************
//
// serialization methods
//
// ******************************************************************************
	public boolean writeToFile (String filename) {
		try {
			FileOutputStream fs = new FileOutputStream(filename);
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(this);
			os.close();
		}
		catch (Exception e) {
			System.err.println("@DMPuzzle::writeToFile");
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
			return false;
		}
		return true;
	}
	
	public static boolean readFromFile (String filename) {
		try {
			FileInputStream fileStream = new FileInputStream(filename);
			ObjectInputStream is = new ObjectInputStream(fileStream);
			DMPuzzle.setPuzzle((DMPuzzle) is.readObject());
			is.close();
		}
		catch (Exception e) {
			System.err.println("@DMPuzzle::readFromFile");
			System.err.println(e.getMessage());
			System.err.println(e.getStackTrace());
			return false;
		}
		return true;
	}	
	
	
// ******************************************************************************
//
// Setters & Getters
//
// ******************************************************************************
	public static DMPuzzle getPuzzle() {
		return puzzle;
	}
	
	public static void setPuzzle(DMPuzzle puzzle) {
		DMPuzzle.puzzle = puzzle;
	}

	public static int getSelectionType() {
		return selectionType;
	}

	public void setPuzzle_filename(String puzzle_filename) {
		this.puzzle_filename = puzzle_filename;
	}

	public String getPuzzle_filename() {
		return puzzle_filename;
	}
}