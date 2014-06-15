package jSudoku.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DMGame implements Serializable {
	
	/* serializable class version number */
	private static final long serialVersionUID = 01L;
	
	transient private static DMGame myself = null;
	private DMPuzzle puzzle = null;
	private String gameID = null;
	private String puzzleID = null;
	private String puzzleDesc = null;
	
	
// ******************************************************************************
//
// Constructor
//
// ******************************************************************************

	public DMGame () {
		puzzle = DMPuzzle.createNewPuzzle();
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
			System.err.println("@DMGame::writeToFile");
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
			myself = (DMGame) is.readObject();			
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
	public void setPuzzle(DMPuzzle puzzle) {
		this.puzzle = puzzle;
	}

	public DMPuzzle getPuzzle() {
		return puzzle;
	}

	public void setGameID(String gameID) {
		this.gameID = gameID;
	}

	public String getGameID() {
		return gameID;
	}

	public void setPuzzleID(String puzzleID) {
		this.puzzleID = puzzleID;
	}

	public String getPuzzleID() {
		return puzzleID;
	}

	public void setPuzzleDesc(String puzzleDesc) {
		this.puzzleDesc = puzzleDesc;
	}

	public String getPuzzleDesc() {
		return puzzleDesc;
	}
	
	

}
