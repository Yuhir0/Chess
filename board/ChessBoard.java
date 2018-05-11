//Oscar Garcia
package board;

import chessmen.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ChessBoard {
	
	private static char[] numRow   = {'1','2','3','4','5','6','7','8'};
	private static char[] alphaRow = {'a','b','c','d','e','f','g','h'};
	
	Chessmen[][] cells = new Chessmen[8][8];
	
	private boolean match;
	
	private Chessmen chessmenSelected = null;
	private ArrayList<String> posibleMovements = new ArrayList<String>();
	
	Exception InvalidPosition;
	Exception NoneChessmenSelected;
	
	public ChessBoard () throws Exception {
		this.match = true;
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				cells[i][j] = null;
			}
		}
		
		for (int j = 0; j < 8; j++) {
			cells[1][j] = new Pawn(alphaRow[j]+"2",'w');
			cells[6][j] = new Pawn(alphaRow[j]+"7",'b');
		}
		
		cells[0][0] = new Rook("a1",'w'); cells[0][7] = new Rook("h1",'w');
		cells[0][1] = new Knight("b1",'w'); cells[0][6] = new Knight("g1",'w');
		cells[0][2] = new Bishop("c1",'w'); cells[0][5] = new Bishop("f1",'w');
		cells[0][3] = new Queen("d1",'w'); cells[0][4] = new King("e1",'w');
		
		cells[7][0] = new Rook("a8",'b'); cells[7][7] = new Rook("h8",'b');
		cells[7][1] = new Knight("b8",'b'); cells[7][6] = new Knight("g8",'b');
		cells[7][2] = new Bishop("c8",'b'); cells[7][5] = new Bishop("f8",'b');
		cells[7][3] = new Queen("d8",'b'); cells[7][4] = new King("e8",'b');
	}
	
	public void selectChessmen (String position) throws Exception {
		if (validPosition(position)) {
			chessmenSelected = cells[index(numRow,position.charAt(1))][index(alphaRow,position.charAt(0))];
			posibleMovements = chessmenSelected.previewMovement();
			for (int i = 0; i < cells.length; i++) {
				for (int j = 0; j < cells[i].length; j++) {
					if (posibleMovements.indexOf(alphaRow[j]+""+numRow[i]) >= 0) {
						if (!(cells[i][j] == null || (cells[i][j] != null && cells[i][j].getColor() != chessmenSelected.getColor()))) {
							posibleMovements.remove(posibleMovements.indexOf(alphaRow[j]+""+numRow[i]));
						}
					}
				}
			}

		} else {
			throw InvalidPosition;
		}
	}
	
	public void desselectChessmen() {
		chessmenSelected = null;
		posibleMovements = new ArrayList<String>();
	}
	
	public char chessmenSelectedColor() {
		try {
			return chessmenSelected.getColor();
		} catch (Exception e) {
			return 0;
		}
	}
	
	public void moveTo(String position) throws Exception {
		if (chessmenSelected != null) {
			ArrayList<String> preview = chessmenSelected.previewMovement();
			if (preview.indexOf(position) >= 0) {
				int num = index(numRow,chessmenSelected.getPosition().charAt(1));
				int alpha = index(alphaRow,chessmenSelected.getPosition().charAt(0));
				int destNum = index(numRow,position.charAt(1));
				int destAlpha = index(alphaRow,position.charAt(0));
				
				cells[destNum][destAlpha] = cells[num][alpha];
				cells[num][alpha] = null;
				chessmenSelected.moveTo(position);
				chessmenSelected = null;
				posibleMovements = new ArrayList<String>();
			} else {
				throw InvalidPosition;
			}
		} else {
			throw NoneChessmenSelected;
		}
	}
	
	public static char[] getNumRow() {
		return numRow;
	}

	public static char[] getAlphaRow() {
		return alphaRow;
	}

	public static boolean validPosition (String position) {
		try {
			char alpha = position.charAt(0);
			char num = position.charAt(1);
			if (Arrays.binarySearch(alphaRow, alpha) >= 0 && Arrays.binarySearch(numRow,num) >= 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	public String toString() {
		boolean check = false;
		String concat = "  +---+---+---+---+---+---+---+---+\n";
		for (int i = 0; i < cells.length; i++) {
			concat += (i + 1) + " ";
			for (int j = 0; j < cells[i].length; j++) {
				if (cells[i][j] != null) {
					concat += "|" + cells[i][j] + " ";
					check = true;
				}
				if (posibleMovements.size() > 0 && posibleMovements.indexOf(alphaRow[j]+""+numRow[i]) >= 0) {
					if (!check) {
						concat += "|[ ]";
					} else if (check && cells[i][j].getColor() != chessmenSelected.getColor()){
						concat = concat.substring(0,concat.length()-1) + "]";
					}
				} else if (!check){
					concat += "|   ";
				}
				check = false;
			}
			concat += "|\n  +---+---+---+---+---+---+---+---+\n";
		}
		concat += "    a   b   c   d   e   f   g   h";
		return concat;
	}
	
	public boolean match() {
		return this.match;
	}
	
	private int cell (String pos, char color) {
		if (cells[index(numRow,pos.charAt(1))][index(alphaRow,pos.charAt(0))] != null && cells[index(numRow,pos.charAt(1))][index(alphaRow,pos.charAt(0))].getColor() == color) {
			return 1;
		} else if (cells[index(numRow,pos.charAt(1))][index(alphaRow,pos.charAt(0))] == null) {
			return 0;
		}
		return -1;
	}
	
	public static int index (char[] arr, char c) {
		return Arrays.binarySearch(arr, c);
	}
	
	public static void main (String[] args) throws Exception {
		ChessBoard board = new ChessBoard();
		board.selectChessmen("c7");
		System.out.println(board);
		board.moveTo("c5");
		System.out.println(board);
		board.selectChessmen("c2");
		System.out.println(board);
		board.moveTo("c4");
		System.out.println(board);
		board.selectChessmen("c4");
		System.out.println(board);
		board.moveTo("c5");
		System.out.println(board);
	}
}