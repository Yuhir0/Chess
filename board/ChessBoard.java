package board;

import chessmen.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ChessBoard {
	
	private static char[] numRow   = {'1','2','3','4','5','6','7','8'};
	private static char[] alphaRow = {'a','b','c','d','e','f','g','h'};
	
	Chessmen[][] cells = new Chessmen[8][8];
	
	private boolean match;
	
	//private ArrayList<Chessmen> chessmen = new ArrayList<Chessmen>();
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
			//chessmen.add(new Pawn(alphaRow[j]+"2",'w'));
		}
		
		/*for (int j = 0; j < 8; j++) {
			cells[6][j] = new Pawn(alphaRow[j]+"2",'w');
			chessmen.add(new Pawn(alphaRow[j]+"7",'b'));
		}*/
		
		cells[0][0] = new Rook("a1",'w'); cells[0][7] = new Rook("h1",'w');
		cells[0][1] = new Knight("b1",'w'); cells[0][6] = new Knight("g1",'w');
		cells[0][2] = new Bishop("c1",'w'); cells[0][5] = new Bishop("f1",'w');
		cells[0][3] = new Queen("d1",'w'); cells[0][4] = new King("e1",'w');
		
		cells[7][0] = new Rook("a8",'b'); cells[7][7] = new Rook("h8",'b');
		cells[7][1] = new Knight("b8",'b'); cells[7][6] = new Knight("g8",'b');
		cells[7][2] = new Bishop("c8",'b'); cells[7][5] = new Bishop("f8",'b');
		cells[7][3] = new Queen("d8",'b'); cells[7][4] = new King("e8",'b');
		
		selectChessmen("d7");
		/*chessmen.add(new Rook("a1",'w')); chessmen.add(new Rook("h1",'w'));
		chessmen.add(new Knight("b1",'w')); chessmen.add(new Knight("g1",'w'));
		chessmen.add(new Bishop("c1",'w')); chessmen.add(new Bishop("f1",'w'));
		chessmen.add(new Queen("d1",'w')); chessmen.add(new King("e1",'w'));
		
		chessmen.add(new Rook("a8",'b')); chessmen.add(new Rook("h8",'b'));
		chessmen.add(new Knight("b8",'b')); chessmen.add(new Knight("g8",'b'));
		chessmen.add(new Bishop("c8",'b')); chessmen.add(new Bishop("f8",'b'));
		chessmen.add(new Queen("d8",'b')); chessmen.add(new King("e8",'b'));*/
	}
	
	public void selectChessmen (String position) throws Exception {
		if (validPosition(position)) {
			chessmenSelected = cells[index(numRow,position.charAt(1))][index(alphaRow,position.charAt(0))];
			posibleMovements = chessmenSelected.previewMovement();
		} else {
			throw InvalidPosition;
		}
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
				chessmenSelected.moveTo(position);
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
	
	/*private Chessmen cheesmenOn(String position) {
		for (Chessmen c: chessmen) {
			if (c.getPosition().equals(position)) {
				return c;
			}
		}
		return null;
	}*/
	
	/*public String toString() {
		String concat = "  +---+---+---+---+---+---+---+---+\n";
		boolean done = false;
		ArrayList<String> preview = new ArrayList<String>();
		posibleMovements = new ArrayList<String>();
		try {
			preview = chessmenSelected.previewMovement();
		} catch (Exception e) {
			preview = null;
		}
		for (int n = 7; n > -1; n--) {
			concat += numRow[n] + " |";
			for (int a = 0; a < 8; a++) {
				for (int c = 0; c < chessmen.size() ; c++) {
					if (chessmen.get(c).getPosition().equals(alphaRow[a]+""+numRow[n])) {
						concat += chessmen.get(c).getName() + " |";
						done = true;
					}
					
					if (preview != null && preview.indexOf(alphaRow[a]+""+numRow[n]) != -1) {
						if (done && chessmenSelected != null && chessmenSelected.getColor() != chessmen.get(c).getColor()) {
							concat = concat.substring(0,concat.length()-2) + "]|";
							posibleMovements.add(alphaRow[a]+""+numRow[n]);
						} else if(c + 1 == chessmen.size()){
							concat += "[ ]|";
							posibleMovements.add(alphaRow[a]+""+numRow[n]);
							done = true;
						}
					}
					if (done) {break;}
				}
				if (!done) {
					concat += "   |";
				}
				done = false;
			}
			concat += "\n  +---+---+---+---+---+---+---+---+\n";
		}
		concat += "    a   b   c   d   e   f   g   h";
		return concat;
	}*/
	
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
				if (posibleMovements.indexOf(alphaRow[j]+""+numRow[i]) >= 0) {
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
	
	private int index (char[] arr, char c) {
		return Arrays.binarySearch(arr, c);
	}
	
	public static void main (String[] args) throws Exception {
		ChessBoard board = new ChessBoard();
		System.out.println(board);
	}
}