package chessmen;

import java.util.ArrayList;
import java.util.Arrays;

import board.ChessBoard;

public class Queen extends Chessmen {

	public Queen(String position, char color) throws Exception {
		super(position, color);
		name = "Q" + color;
	}

	public ArrayList<String> previewMovement() {
		int[][] posible = new int[64][2];
		int plus = 8;
		for (int i = -8; i <= 8; i++) {
			if (i != 0) {
			posible [i + plus][0] = i;
			posible [i + plus][1] = 0;
			} else {
				plus--;
			}
		}
		plus += 17;
		for (int i = -8; i <= 8; i++) {
			if (i != 0) {
				posible [i + plus][0] = 0;
				posible [i + plus][1] = i;	
			} else {
				plus--;
			}
		}
		plus += 17;
		for (int i = -8; i <= 8; i++) {
			if (i != 0) {
			posible [i + plus][0] = i;
			posible [i + plus][1] = i;
			} else {
				plus--;
			}
		}
		plus += 17;
		for (int i = -8; i <= 8; i++) {
			if (i != 0) {
				posible [i + plus][0] = -i;
				posible [i + plus][1] = i;	
			} else {
				plus--;
			}
		}
		
		ArrayList<String> positions = new ArrayList<String>();
		char[] numRow = ChessBoard.getNumRow();
		char[] alphaRow = ChessBoard.getAlphaRow();
		int[] actualPosition = {Arrays.binarySearch(numRow, this.position.charAt(1)),
				Arrays.binarySearch(alphaRow, this.position.charAt(0))};
		for (int i = 0; i < posible.length; i++) {
			try {
				positions.add(alphaRow[actualPosition[1]+posible[i][1]] + "" + numRow[actualPosition[0]+posible[i][0]]);
			} catch (Exception e) {}
		}
		
		return positions;
	}
}
