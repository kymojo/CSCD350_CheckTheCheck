/* Team: Twenty Hats
 * Nathan Graham
 * CSCD 350
 * Assignment 2 (Check the Check)
 * 
 * Your task is to write a program that reads a chess board configuration 
 * and answers if there's a king under attack (i.e. "in check"). A king is 
 * in check if it's in a square which is attacked by an opponent's piece 
 * (i.e. it's in square which can be taken by an opponent's piece in his next 
 * move).  White pieces will be represented by uppercase letters whereas 
 * black pieces will be represented by lowercase letters. White side will 
 * always be on the bottom of the board and black side will always be on 
 * the top of the board.
 * 
 * Input: There will be an arbitrary number of board configurations on the 
 * input. Each board will consist of 8 lines of 8 characters each. A '.' 
 * character will represent an empty square. Upper and lower case letters 
 * (as defined above) will represent the pieces.  There will be no invalid 
 * characters (i.e. pieces) and there won't be a configuration where both 
 * kings are in check. You must read until you find an empty board (i.e. a 
 * board that is formed only of '.' characters) which should not be processed. 
 * There will be an empty line between each pair of board configurations. 
 * In all boards (except the last one which is empty) will appear both the 
 * white king and the black king (one, and only one of each).
 * 
 * Output: For each board configuration read you must output one of the following 
 * answers:
 * 
 * 		Game #d: white king is in check.
 * 		Game #d: black king is in check.
 *		Game #d: no king is in check.
 * 
 * Where d stands for the game number (starting from 1).
 * 
 */

public class CheckTheCheck {
	
	public static void main(String[] args) {
		GameBoard curBoard = new GameBoard();
		int gameNum = 1;
		
		curBoard.initBoard();
		while(!curBoard.getEmptyBoard()) {
			System.out.println("Game #" + gameNum + ": " + curBoard.getCheck());
			
			curBoard = new GameBoard();
			curBoard.initBoard();
			gameNum++;
		}
	}
	
	
}