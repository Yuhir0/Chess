package chessmen;

import java.util.ArrayList;
import java.util.Arrays;

import board.ChessBoard;

public class King extends Chessmen {
	
	public King(String position, char color) throws Exception {
		super(position, color);
		name = "K" + color;
	}
	
	public void moveTo(String position) {
		
	}
	
	public ArrayList<String> previewMovement() {
		int[][] posible = {{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1}};
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
