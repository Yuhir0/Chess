package board;

import chessmen.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ChessBoard {
	
	private static char[] numRow   = {'1','2','3','4','5','6','7','8'};
	private static char[] alphaRow = {'a','b','c','d','e','f','g','h'};
	
	private ArrayList<Chessmen> chessmen = new ArrayList<Chessmen>();
	private Chessmen chessmenSelected = null;
	
	Exception InvalidPosition;
	Exception NoneChessmenSelected;
	
	public ChessBoard () throws Exception {
		for (int j = 0; j < 8; j++) {
			chessmen.add(new Pawn(alphaRow[j]+"2",'b'));
		}
		
		for (int j = 0; j < 8; j++) {
			chessmen.add(new Pawn(alphaRow[j]+"7",'w'));
		}
		
		chessmen.add(new Rook("a1",'w')); chessmen.add(new Rook("h1",'w'));
		chessmen.add(new Knight("b1",'w')); chessmen.add(new Knight("g1",'w'));
		chessmen.add(new Bishop("c1",'w')); chessmen.add(new Bishop("f1",'w'));
		chessmen.add(new Queen("d1",'w')); chessmen.add(new King("e1",'w'));
		
		chessmen.add(new Rook("a8",'b')); chessmen.add(new Rook("h8",'b'));
		chessmen.add(new Knight("b8",'b')); chessmen.add(new Knight("g8",'b'));
		chessmen.add(new Bishop("c8",'b')); chessmen.add(new Bishop("f8",'b'));
		chessmen.add(new Queen("d8",'b')); chessmen.add(new King("e8",'b'));
	}
	
	public void selectChessmen (String position) throws Exception {
		if (validPosition(position)) {
			chessmenSelected = cheesmenOn(position);
		} else {
			throw InvalidPosition;
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
	
	private Chessmen cheesmenOn(String position) {
		for (Chessmen c: chessmen) {
			if (c.getPosition().equals(position)) {
				return c;
			}
		}
		return null;
	}
	
	
	public String toString() {
		String concat = "  +---+---+---+---+---+---+---+---+\n";
		boolean check = false;
		/*ArrayList<String> preview = new ArrayList<String>();
		try {
			preview = chessmenSelected.previewMovement();
		} catch (Exception e) {
			preview = null;
		}*/
		for (int n = 7; n > -1; n--) {
			concat += numRow[n] + " |";
			for (int a = 0; a < 8; a++) {
				for (int c = 0; c < chessmen.size() ; c++) {
					if (chessmen.get(c).getPosition().equals(alphaRow[a]+""+numRow[n])) {
						concat += chessmen.get(c).getName() + " |";
						check = true;
						break;
					}
					//if (check) {break;}
				}
				if (!check) {
					concat += "   |";
				}
				check = false;
			}
			concat += "\n  +---+---+---+---+---+---+---+---+\n";
		}
		concat += "    a   b   c   d   e   f   g   h";
		return concat;
	}
}