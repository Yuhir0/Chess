// Dani
package main;

import board.ChessBoard;
import input.Inputs;
import player.Player;
import java.util.regex.Pattern;
import java.io.IOException;
import java.util.regex.Matcher;

public class Match{
	private static Player[] players = new Player[2];
	private static int turn = 0;
	private static ChessBoard chessBoard;
	public static void main(String[] args) throws Exception {
		chessBoard = new ChessBoard();
		String input;
		String email;
		String name;
		
		name = validName("Player White, enter your name: ");
		email = validEmail("Player White, enter your email: ");
		players[0] = new Player(email,'w',name);
		
		name = validName("Player Black, enter your name: ");
		email = validEmail("Player Black, enter your email: ");
		players[1] = new Player(email,'b',name);
		
		while (chessBoard.match()) {
			do {
				System.out.println(chessBoard);
				input = Inputs.str_input("["+players[turn].getName()+" "+players[turn].getColor()+"]('h' to help): ");
			}while(!options(input,chessBoard));
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
				if (Pattern.compile("s\\s[a-h][1-8]").matcher(option).matches()) {
					try {
						chessBoard.selectChessmen(option.substring(2));
						validColor();
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
				}
				return false;
				
			case 'm':
				if (Pattern.compile("m\\s([a-h][1-8]\\s?){2}").matcher(option).matches()) {
					try {
						String[] split = option.split(" ");
						chessBoard.selectChessmen(split[1]);
						validColor();
						chessBoard.moveTo(split[2]);
						return true;
					}catch(Exception e) {
						System.out.println(e.getMessage());
						return false;
					}
				} else if (Pattern.compile("m\\s[a-h][1-8]").matcher(option).matches()) {
					try {
						chessBoard.moveTo(option.substring(2));
						return true;
					}catch(Exception e) {
						System.out.println(e.getMessage());
						return false;
					}
				} else {
					System.out.println("Error: The syntax of the command is incorrect\nFor more info type 'h'");
					return false;
				}
				
			case 'h':
				menu();
				return false;
				
			default:
				System.out.println("Invalid Option, try again.");
				return false;
			}
	}
	
	public static String validEmail(String text) throws IOException {
		String email;
		do {
			email = Inputs.str_input(text);
		}while(!Player.isValidEmail(email));
		return email;
	}
	
	public static String validName(String text) throws IOException {
		String name;
		do {
			name = Inputs.str_input(text);
		}while(!Player.isValidString(name));
		return name;
	}	
	
	public static void validColor() {
		if (chessBoard.chessmenSelectedColor() != players[turn].getColor()) {
			chessBoard.desselectChessmen();
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
