package chessmen;

import java.util.ArrayList;
import java.util.Arrays;

import board.ChessBoard;

public class Pawn extends Chessmen{
	public Pawn (String position, char color) throws Exception {
		super(position, color);
		name = "P" + color;
	}
	
	public ArrayList<String> previewMovement() {
		int[][] posible = {{0,2},{0,-2},{0,1},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
	
		ArrayList<String> positions = new ArrayList<String>();
		char[] numRow = ChessBoard.getNumRow();
		char[] alphaRow = ChessBoard.getAlphaRow();
		int[] actualPosition = {Arrays.binarySearch(numRow, this.position.charAt(1)),
				Arrays.binarySearch(alphaRow, this.position.charAt(0))};
		for (int i = 0; i < posible.length; i++) {
			try {
				positions.add(alphaRow[actualPosition[1]+posible[i][0]] + "" + numRow[actualPosition[0]+posible[i][1]]);
			} catch (Exception e) {}
		}
		for (int i = 0; i < positions.size(); i++) {
			System.out.println(positions.get(i));
		}
		return positions;
	}
}
