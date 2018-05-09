package main;

import board.ChessBoard;
import input.Inputs;
import player.Player;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Match {
	
	public static void main(String[] args) throws Exception {
		boolean partida = true; 
		ChessBoard chessBoard = new ChessBoard();
		int turn = 0;
		Player[] players = new Player[2];
		String input;
		String name = Inputs.str_input("Player 1, enter your name: ");
		String email = Inputs.str_input("Player 1, enter your email: ");
		players[0] = new Player(email,'w',name);
		name = Inputs.str_input("Player 1, enter your name: ");
		email = Inputs.str_input("Player 1, enter your email: ");
		players[1] = new Player(email,'b',name);
		while (chessBoard.match()) {
			System.out.println(chessBoard);
			do {
				input = Inputs.str_input("["+players[turn].getName()+"]('h' to help): ");
			}while(options(input,chessBoard));
			if (turn == 0) {
				turn++;
			}else {
				turn--;
			}
		}
	}
	
	public static boolean options(String option, ChessBoard chessBoard) throws Exception {
		switch (option.toLowerCase().charAt(0)) {
			case 's':
				if (Pattern.compile("s\\s+[a-h][1-8]").matcher(option).matches()) {
					try {
						chessBoard.selectChessmen(option.substring(2));
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
				}
				return false;
			case 'm':
				if (Pattern.compile("m\\s+([a-h][1-8]\\s?){2}").matcher(option).matches()) {
					try {
						chessBoard.selectChessmen(option.substring(2));
						chessBoard.moveTo(option.substring(2));
						return true;
					}catch(Exception e) {
						System.out.println(e.getMessage());
						return false;
					}
				} else if (Pattern.compile("m\\s+[a-h][1-8]").matcher(option).matches()) {
					try {
						chessBoard.moveTo(option.substring(2));
						return true;
					}catch(Exception e) {
						System.out.println(e.getMessage());
						return false;
					}
				}
			case 'h':
				menu();
				return true;
			default:
				
				return false;
			}
	}
	public static void menu() {
		System.out.println("+------------------------------------------+");
		System.out.println("|Type 'm' and position to move the Chessmen|");
		System.out.println("|if you would type a single position, you  |");
		System.out.println("|must do it after typing the 's (position)'|");
		System.out.println("|ex. 'm a1 a4' or'm a4'                    |");
		System.out.println("+------------------------------------------+");
		System.out.println("|Type 's' and position to select a Chessmen|");
		System.out.println("|That option will show the you the         |");
		System.out.println("|possibilities to move the Chessmen        |");
		System.out.println("|ex. 's a1'                                |");
		System.out.println("+------------------------------------------+");
	}
}
