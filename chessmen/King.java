//Oscar Garcia
package chessmen;

import java.util.ArrayList;
import java.util.Arrays;
import board.ChessBoard;

public class King extends Chessmen {
	
	private boolean castling;
	
	public King(String position, char color) throws Exception {
		super(position, color);
		this.name = "K" + color;
		this.castling = true;
	}
	
	public void moveTo(String new_position) throws Exception {
		ArrayList<String> posible = this.previewMovement();
		
		if (posible.indexOf(new_position) >= 0) {
			this.position = new_position;
			if (castling) {
				castling = false;
			}
		} else {
			throw InvalidPosition;
		}
	}
	
	public ArrayList<String> previewMovement() {
		int[][] posible = {{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1},{2,0},{-2,0}};
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
	
	public boolean castling() {
		return this.castling;
	}
}
