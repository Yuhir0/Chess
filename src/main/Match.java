package main;

import board.ChessBoard;
import input.Inputs;

public class Match {
	
	public static void main(String[] args) throws Exception {
		boolean partida = true; 
		ChessBoard chessBoard = new ChessBoard();
		char[] player = {'w','b'};
		int turn = 0;
		while (partida) {
			/*System.out.println(chessBoard);
			try {
			String input = Inputs.str_input("m: ");
			chessBoard.selectChessmen(input);
			}
			catch (Exception e){
				System.out.println(e.getMessage());
			}
			try {
				String input = Inputs.str_input("1. Move to \n2. Select chessmen ");
			}
			
			if (chessBoard.chessmenSelectedColor() == player[turn]) {
				chessBoard.moveTo(position);
			} else {
				
			}*/
			
			String input = Inputs.str_input("Player " + player[turn] + ": ");
			
			if (options(input, chessBoard)) {
				if (turn == 0) turn = 1;
				else turn = 0;
			}
		}
	}
	
	public static boolean options(String option, ChessBoard chessBoard) throws Exception {
		switch (option.toLowerCase().charAt(0)) {
			case 's':
				chessBoard.selectChessmen(option.substring(2));
				return true;
			case 'm':
				chessBoard.moveTo(option.substring(2));
				return true;
			default:
				System.out.println("Error: Input a valid option.");
				return false;
			}
	}

}
