package jSudoku.control;

import jSudoku.model.*;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;


public class GridBoardController {
	
// ******************************************************************************
//
// Constructor
//
// ******************************************************************************
	/**
	 * private default constructor - prevent others to create instances of this class.
	 * No instance should be created.
	 * 
	 * @param  none
	 * @return N/A
	 */	
	public static final int NUM_OF_ROW = 9;
	public static final int NUM_OF_COLUMN = 9;
	public static DMCell typeA[][]  = new DMCell[NUM_OF_ROW][NUM_OF_COLUMN];
	public static DMCell typeB[][]  = new DMCell[NUM_OF_ROW][NUM_OF_COLUMN];
	static {
		for (int i = 0; i < NUM_OF_ROW; i++){
			for (int j = 0; j < NUM_OF_COLUMN; j++) {
				typeA[i][j] = new DMCell();
				typeB[i][j] = new DMCell();
			}
		}
			typeA[0][0].setValue(4);
			typeA[0][1].setValue(9);
			typeA[0][2].setValue(1);
			typeA[0][3].setValue(8);
			typeA[0][4].setValue(5);
			typeA[0][5].setValue(2);
			typeA[0][6].setValue(6);
			typeA[0][7].setValue(3);
			typeA[0][8].setValue(7);

			typeA[1][0].setValue(5);
			typeA[1][1].setValue(8);
			typeA[1][2].setValue(3);
			typeA[1][3].setValue(6);
			typeA[1][4].setValue(7);
			typeA[1][5].setValue(1);
			typeA[1][6].setValue(2);
			typeA[1][7].setValue(9);
			typeA[1][8].setValue(4);

			typeA[2][0].setValue(6);
			typeA[2][1].setValue(7);
			typeA[2][2].setValue(2);
			typeA[2][3].setValue(9);
			typeA[2][4].setValue(3);
			typeA[2][5].setValue(4);
			typeA[2][6].setValue(1);
			typeA[2][7].setValue(8);
			typeA[2][8].setValue(5);

			typeA[3][0].setValue(3);
			typeA[3][1].setValue(5);
			typeA[3][2].setValue(9);
			typeA[3][3].setValue(1);
			typeA[3][4].setValue(4);
			typeA[3][5].setValue(8);
			typeA[3][6].setValue(7);
			typeA[3][7].setValue(6);
			typeA[3][8].setValue(2);

			typeA[4][0].setValue(2);
			typeA[4][1].setValue(1);
			typeA[4][2].setValue(8);
			typeA[4][3].setValue(7);
			typeA[4][4].setValue(9);
			typeA[4][5].setValue(6);
			typeA[4][6].setValue(5);
			typeA[4][7].setValue(4);
			typeA[4][8].setValue(3);
			
			typeA[5][0].setValue(7);
			typeA[5][1].setValue(6);
			typeA[5][2].setValue(4);
			typeA[5][3].setValue(5);
			typeA[5][4].setValue(2);
			typeA[5][5].setValue(3);
			typeA[5][6].setValue(8);
			typeA[5][7].setValue(1);
			typeA[5][8].setValue(9);

			typeA[6][0].setValue(1);
			typeA[6][1].setValue(3);
			typeA[6][2].setValue(7);
			typeA[6][3].setValue(4);
			typeA[6][4].setValue(8);
			typeA[6][5].setValue(5);
			typeA[6][6].setValue(9);
			typeA[6][7].setValue(2);
			typeA[6][8].setValue(6);

			typeA[7][0].setValue(8);
			typeA[7][1].setValue(4);
			typeA[7][2].setValue(5);
			typeA[7][3].setValue(2);
			typeA[7][4].setValue(6);
			typeA[7][5].setValue(9);
			typeA[7][6].setValue(3);
			typeA[7][7].setValue(7);
			typeA[7][8].setValue(1);
			
			typeA[8][0].setValue(9);
			typeA[8][1].setValue(2);
			typeA[8][2].setValue(6);
			typeA[8][3].setValue(3);
			typeA[8][4].setValue(1);
			typeA[8][5].setValue(7);
			typeA[8][6].setValue(4);
			typeA[8][7].setValue(5);
			typeA[8][8].setValue(8);
			
			typeB[0][0].setValue(5);
			typeB[0][1].setValue(8);
			typeB[0][2].setValue(6);
			typeB[0][3].setValue(2);
			typeB[0][4].setValue(7);
			typeB[0][5].setValue(1);
			typeB[0][6].setValue(4);
			typeB[0][7].setValue(9);
			typeB[0][8].setValue(3);

			typeB[1][0].setValue(4);
			typeB[1][1].setValue(3);
			typeB[1][2].setValue(9);
			typeB[1][3].setValue(5);
			typeB[1][4].setValue(8);
			typeB[1][5].setValue(6);
			typeB[1][6].setValue(2);
			typeB[1][7].setValue(7);
			typeB[1][8].setValue(1);
			
			typeB[2][0].setValue(7);
			typeB[2][1].setValue(2);
			typeB[2][2].setValue(1);
			typeB[2][3].setValue(4);
			typeB[2][4].setValue(3);
			typeB[2][5].setValue(9);
			typeB[2][6].setValue(8);
			typeB[2][7].setValue(6);
			typeB[2][8].setValue(5);

			typeB[3][0].setValue(9);
			typeB[3][1].setValue(5);
			typeB[3][2].setValue(7);
			typeB[3][3].setValue(8);
			typeB[3][4].setValue(4);
			typeB[3][5].setValue(2);
			typeB[3][6].setValue(1);
			typeB[3][7].setValue(3);
			typeB[3][8].setValue(6);

			typeB[4][0].setValue(2);
			typeB[4][1].setValue(1);
			typeB[4][2].setValue(8);
			typeB[4][3].setValue(7);
			typeB[4][4].setValue(6);
			typeB[4][5].setValue(3);
			typeB[4][6].setValue(9);
			typeB[4][7].setValue(5);
			typeB[4][8].setValue(4);
			
			typeB[5][0].setValue(6);
			typeB[5][1].setValue(4);
			typeB[5][2].setValue(3);
			typeB[5][3].setValue(1);
			typeB[5][4].setValue(9);
			typeB[5][5].setValue(5);
			typeB[5][6].setValue(7);
			typeB[5][7].setValue(2);
			typeB[5][8].setValue(8);

			typeB[6][0].setValue(3);
			typeB[6][1].setValue(6);
			typeB[6][2].setValue(2);
			typeB[6][3].setValue(9);
			typeB[6][4].setValue(1);
			typeB[6][5].setValue(4);
			typeB[6][6].setValue(5);
			typeB[6][7].setValue(8);
			typeB[6][8].setValue(7);

			typeB[7][0].setValue(8);
			typeB[7][1].setValue(9);
			typeB[7][2].setValue(4);
			typeB[7][3].setValue(6);
			typeB[7][4].setValue(5);
			typeB[7][5].setValue(7);
			typeB[7][6].setValue(3);
			typeB[7][7].setValue(1);
			typeB[7][8].setValue(2);
			
			typeB[8][0].setValue(1);
			typeB[8][1].setValue(7);
			typeB[8][2].setValue(5);
			typeB[8][3].setValue(3);
			typeB[8][4].setValue(2);
			typeB[8][5].setValue(8);
			typeB[8][6].setValue(6);
			typeB[8][7].setValue(4);
			typeB[8][8].setValue(9);
	}
	
	
	private GridBoardController() {
		
	}

// ******************************************************************************
//
// puzzle data searching & manipulating methods
//
// ******************************************************************************	 
	 public static void clearSelectedCellTempValue(DMPuzzle puzzle) {
		 for (DMCell[] cellarray : puzzle.cells) {
			 for (DMCell cell : cellarray) {
				 if (cell.isSelected() && !cell.isFixedCell()) {
					 cell.deleteLastEntryFromNoteList();
					 return;
				 }
			 }
		 }
	 }
	 
	 public static void clearSelectedCellTempValue(DMCell cell) {
		 if (cell.isSelected() && !cell.isFixedCell()) 
			 cell.deleteLastEntryFromNoteList();
	 }
	 
	 public static void setSelectedCellTempValue(DMPuzzle puzzle, int ascii) {
		 int num = ascii - KeyEvent.VK_0;
		 
		 for (DMCell[] cellarray : puzzle.cells) {
			 for (DMCell cell : cellarray) {
				 if (cell.isSelected() && !cell.isFixedCell()) {
					 cell.addNote(num, -1);
					 return;
				 }
			 }
		 }
	 }

	 public static void setSelectedCellTempValue(DMCell cell, int ascii) {
		 int num = ascii - KeyEvent.VK_0;
		 
		 if (cell.isSelected() && !cell.isFixedCell()) {
			 cell.addNote(num,-1);
		 }
	 }

	 public static void clearSelectedCellValue(DMPuzzle puzzle) {
		 for (DMCell[] cellarray : puzzle.cells) {
			 for (DMCell cell : cellarray) {
				 if (cell.isSelected() && !cell.isFixedCell()) {
					 cell.setValue(-1);
					 cell.setSelected(false);
					 cell.setFilled(false);
					 cell.setConflict(false);
					 return;
				 }
			 }
		 }
	 }

	 public static void clearSelectedCellValue(DMCell cell) {
		 if (cell.isSelected() && !cell.isFixedCell()) {
			 cell.setValue(-1);
			 cell.setSelected(false);
			 cell.setFilled(false);
		 }
	 }
	 
	 public static void setSelectedCellValue(DMPuzzle puzzle, int ascii) {
		 int num = ascii - KeyEvent.VK_0;
		 
		 for (DMCell[] cellarray : puzzle.cells) {
			 for (DMCell cell : cellarray) {
				 if (cell.isSelected() && !cell.isFixedCell()) {
					 cell.setValue(num);
					 cell.setSelected(false);
					 cell.setFilled(true);
					 return;
				 }
			 }
		 }
	 }

	 public static void setSelectedCellValue(DMCell cell, int ascii) {
		 int num = ascii - KeyEvent.VK_0;
		 if (cell.isSelected() && !cell.isFixedCell()) {
			 cell.setValue(num);
			 cell.setSelected(false);
			 cell.setFilled(true);
		 }
	 }

	 public static DMCell locateCellAtMousePoint(DMPuzzle puzzle, Point point) {
		 unselectAllCells(puzzle);
		 
		 for (DMCell[] cellarray : puzzle.cells) {
			 for (DMCell cell : cellarray) {
				 if (cell.getRect().contains(point)) {
					 cell.setSelected(true);
					 return cell;
				 } else {
					 cell.setSelected(false);
				 }
			 }
		 }
		 return null;
	 }

	public static void unselectAllCells(DMPuzzle puzzle) {
		for (DMCell[] cellarray : puzzle.cells) {
			 for (DMCell cell : cellarray)
				 cell.setSelected(false);
		 }
	}

	 public static DMCell locatePenSelectedCellPoint(DMPuzzle puzzle) {
		 for (DMCell[] cellarray : puzzle.cells) {
			 for (DMCell cell : cellarray)
				 if (cell.isSelected())
					 return cell;
		 }
		 
		 return null;
	 }

	 public static String checkCorrectness(DMPuzzle puzzle) {

		 boolean isTypeA = false;
		 int countIncorrect = 0;
		 int countCorrect = 0;
		 int countEmpty = 0;
		 int countFilled = 0;
		 
		 if (puzzle.cells[0][1].getValue() == 9 ) 
			 isTypeA = true;
		 
		 for (int i = 0; i < DMPuzzle.NUM_OF_ROW; i ++) {
			 for (int j = 0; j < DMPuzzle.NUM_OF_COLUMN; j++) {
				 if (isTypeA) {
					 if (puzzle.cells[i][j].isFilled() && !puzzle.cells[i][j].isFixedCell() && puzzle.cells[i][j].getValue() == GridBoardController.typeA[i][j].getValue()) {
						 puzzle.cells[i][j].setCorrect(true);
						 countCorrect++;
					 }
					 else if (puzzle.cells[i][j].isFilled() && !puzzle.cells[i][j].isFixedCell()) {
						 puzzle.cells[i][j].setCorrect(false);
						 countIncorrect ++;
					 }
				 } else {
					 if (puzzle.cells[i][j].isFilled() && !puzzle.cells[i][j].isFixedCell() && puzzle.cells[i][j].getValue() == GridBoardController.typeB[i][j].getValue()) {
						 puzzle.cells[i][j].setCorrect(true);
						 countCorrect++;
					 }
					 else if (puzzle.cells[i][j].isFilled() && !puzzle.cells[i][j].isFixedCell()) {
						 puzzle.cells[i][j].setCorrect(false);
						 countIncorrect++;
					 }
				 }
				 if (!puzzle.cells[i][j].isFixedCell() && puzzle.cells[i][j].isFilled())
					 countFilled++;
				 else if (!puzzle.cells[i][j].isFixedCell() && !puzzle.cells[i][j].isFilled())
					 countEmpty++;					 
			 }
		 }
		 
		 return "Inc(" + countIncorrect + ") " + "Corr(" + countCorrect + ") " + "Fill(" + countFilled + ") " + "Empty(" + countEmpty + ") "; 
	 }
	 
	 public static boolean checkConflicts(DMPuzzle puzzle) {
		 Set <Integer> intSet = new HashSet <Integer> ();		 
		 
		 int val = -1;
		 
		 for (int i = 0; i < DMPuzzle.NUM_OF_ROW; i ++) {
			 intSet.clear();	 
			 for (int j = 0; j < DMPuzzle.NUM_OF_COLUMN; j++) {
				 if (puzzle.cells[i][j].isFixedCell())
					 intSet.add(puzzle.cells[i][j].getValue());
			 }
			 for (int j = 0; j < DMPuzzle.NUM_OF_COLUMN; j++) {
				 val = -1;
				 if  (!puzzle.cells[i][j].isFixedCell()) {
					 val = puzzle.cells[i][j].getValue();
					 if (val > 0) {
						 if (intSet.contains(val)) {
							 if (puzzle.cells[i][j].isFilled()) {
							 puzzle.cells[i][j].setConflict(true);
							 }
						 }
						 else
							 intSet.add(val);
					 }
				 }
			 }
		 }
		 
		 for (int i = 0; i < DMPuzzle.NUM_OF_COLUMN; i ++) {
			 intSet.clear();
			 for (int j = 0; j < DMPuzzle.NUM_OF_ROW; j++) {
				 if (puzzle.cells[j][i].isFixedCell())
					 intSet.add(puzzle.cells[j][i].getValue());
			 }
			 for (int j = 0; j < DMPuzzle.NUM_OF_ROW; j++) {
				 val = -1;
				 if (!puzzle.cells[j][i].isFixedCell()) {
					 val = puzzle.cells[j][i].getValue();
					 if (val > 0) {
						 if (intSet.contains(val)) {
							 if (puzzle.cells[j][i].isFilled()) {
							 puzzle.cells[j][i].setConflict(true);
							 }
						 }
						 else
							 intSet.add(val);
					 }
				 }
			 }
		 }
		 
		for (int offset_i = 0; offset_i < 3; offset_i++)
			for (int offset_j = 0; offset_j < 3; offset_j++) {
				
				intSet.clear();
				
				for (int i = 3*offset_i + 0; i < 3*offset_i + 3; i++) {
					for (int j = 3*offset_j + 0; j < 3*offset_j + 3; j++) {
						if (puzzle.cells[i][j].isFixedCell())
							intSet.add(puzzle.cells[i][j].getValue());
					}
				}
				
//				System.out.println("------------");
//				for (int num : intSet)
//					System.out.println("num is " + num);
				
				for (int i = 3*offset_i + 0; i < 3*offset_i + 3; i++) {
					for (int j = 3*offset_j + 0; j < 3*offset_j + 3; j++) {
						val = -1;
						if (!puzzle.cells[i][j].isFixedCell() && puzzle.cells[i][j].isFilled())
							val = puzzle.cells[i][j].getValue();
//						System.out.println("val is " + val);
//						System.out.println("i is " + i);
//						System.out.println("j is " + j);
						if (val > 0) {
							 if (intSet.contains(val)) {
								 if (puzzle.cells[i][j].isFilled() && !puzzle.cells[i][j].isFixedCell()) {
//									 System.out.println("CONFLICT FOUND------------ " + val);
//										System.out.println("intSet holds");
//										for (int num : intSet)
//											System.out.println("num is " + num);
									 puzzle.cells[i][j].setConflict(true);
								 }
							 }
							 else
								 intSet.add(val);
						}
					}
				}
		}
		
		 for (int i = 0; i < DMPuzzle.NUM_OF_COLUMN; i ++)
			 for (int j = 0; j < DMPuzzle.NUM_OF_ROW; j++)
				 if (puzzle.cells[i][j].isConflict() && puzzle.cells[i][j].isFilled() && !puzzle.cells[i][j].isFixedCell())
					 return true;
		
		return false;
	 }
}
