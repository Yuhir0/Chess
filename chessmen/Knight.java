//Marc Fors
package chessmen;

import java.util.ArrayList;
import java.util.Arrays;

import board.ChessBoard;

public class Knight extends Chessmen {
	
	//Constructor
	public Knight(String position, char color) throws Exception {
		super(position, color);
		name = "N" + color;
	}
	
	//Methods
	public ArrayList<String> previewMovement() {
		int[][] posible = {{2,-1},{2,1},{-2,-1},{-2,1},{1,2},{-1,2},{1,-2},{-1,-2}};
		
		ArrayList<String> positions = new ArrayList<String>();
		char[] numRow = ChessBoard.getNumRow();
		char[] alphaRow = ChessBoard.getAlphaRow();
		int[] actualPosition = {Arrays.binarySearch(numRow, this.position.charAt(1)),Arrays.binarySearch(alphaRow, this.position.charAt(0))};
		
		for (int i = 0; i < posible.length; i++) {
			try {
				positions.add(alphaRow[actualPosition[1]+posible[i][0]] + "" + numRow[actualPosition[0]+posible[i][1]]);
			} catch (Exception e) {}
		}
		return positions;
	}
	
	/*
	public static void main(String[] args) throws Exception {
		Knight knight= new Knight("f3",'w');
		ArrayList<String> positions = knight.previewMovement();
		for (int i = 0; i < positions.size(); i++) {
			System.out.println(positions.get(i));
		}
	}
	*/
}
