import java.util.Scanner;


public class GameBoard {
	private int whiteX;
	private int whiteY;
	private int blackX;
	private int blackY;
	private boolean emptyBoard;
	private char[][] gameBoard;
	private static Scanner cin = new Scanner(System.in);
	
	public GameBoard() {
		emptyBoard = true;
		this.gameBoard = new char[8][8];
	}
	//since we are guaranteed to have a king of each color if the board is not
	//empty, I have toggled the board to not empty when a white king is found.
	public void initBoard() {
		//Scanner cin = new Scanner(System.in);
		char tempChar;
		
		for(int i = 0; i < 8; i++) {
			String line = cin.nextLine();
			for(int j = 0; j < 8; j++) {
				tempChar = line.charAt(j);
				
				if(tempChar == 'K') {
					this.whiteX = j;
					this.whiteY = i;
					emptyBoard = false;
				} else if(tempChar == 'k') {
					this.blackX = j;
					this.blackY = i;
				}
				this.gameBoard[i][j] = tempChar;
			}
		}
		if(!emptyBoard) {
			cin.nextLine();
		}
	}
	
	public boolean getEmptyBoard() {
		return this.emptyBoard;
	}

	public String getCheck() {
		if(whiteCheck()) {
			return "white king is in check.";
		}
		if(blackCheck()) {
			return "black king is in check.";
		}
		
		return "no king is in check.";
	}
	
	private boolean blackCheck() {
		if(bDiagCheck()) {
			return true;
		}
		if(bLineCheck()) {
			return true;
		}
		if(bHorseCheck()) {
			return true;
		}
		if(bPawnCheck()) {
			return true;
		}
		return false;
	}
	
	private boolean bHorseCheck() {
		if(blackX + 2 < 8 && blackY - 1 >= 0 && gameBoard[blackY - 1][blackX + 2] == 'N') {
			return true;
		}
		if(blackX + 2 < 8 && blackY + 1 < 8 && gameBoard[blackY + 1][blackX + 2] == 'N') {
			return true;
		}
		if(blackX + 1 < 8 && blackY + 2 < 8 && gameBoard[blackY + 2][blackX + 1] == 'N') {
			return true;
		}
		if(blackX - 1 >= 0 && blackY + 2 < 8 && gameBoard[blackY + 2][blackX - 1] == 'N') {
			return true;
		}
		if(blackX - 2 >= 0 && blackY + 1 < 8 && gameBoard[blackY + 1][blackX - 2] == 'N') {
			return true;
		}
		if(blackX - 2 >= 0 && blackY - 1 >= 0 && gameBoard[blackY - 1][blackX - 2] == 'N') {
			return true;
		}
		if(blackX - 1 >= 0 && blackY - 2 >= 0 && gameBoard[blackY - 2][blackX - 1] == 'N') {
			return true;
		}
		if(blackX + 1 < 8 && blackY - 2 >= 0 && gameBoard[blackY - 2][blackX + 1] == 'N') {
			return true;
		}
		return false;
	}
	
	//check for check from queens or rooks
	private boolean bLineCheck() {
		if(bRightCheck(blackY, blackX)) {
			return true;
		}
		if(bDownCheck(blackY, blackX)) {
			return true;
		}
		if(bLeftCheck(blackY, blackX)) {
			return true;
		}
		if(bUpCheck(blackY, blackX)) {
			return true;
		}
		return false;
	}
	
	//recursive helpers for bLineCheck
	private boolean bRightCheck(int curY, int curX) {
		if(gameBoard[curY][curX] == 'Q' || gameBoard[curY][curX] == 'R') {
			return true;
		}
		if(gameBoard[curY][curX] != '.' && gameBoard[curY][curX] != 'k') {
			return false;
		}
		if(curX + 1 < 8) {
			return bRightCheck(curY, curX + 1);
		}
		return false;
	}
	private boolean bDownCheck(int curY, int curX) {
		if(gameBoard[curY][curX] == 'Q' || gameBoard[curY][curX] == 'R') {
			return true;
		}
		if(gameBoard[curY][curX] != '.' && gameBoard[curY][curX] != 'k') {
			return false;
		}
		if(curY + 1 < 8) {
			return bDownCheck(curY + 1, curX);
		}
		return false;
	}
	private boolean bLeftCheck(int curY, int curX) {
		if(gameBoard[curY][curX] == 'Q' || gameBoard[curY][curX] == 'R') {
			return true;
		}
		if(gameBoard[curY][curX] != '.' && gameBoard[curY][curX] != 'k') {
			return false;
		}
		if(curX - 1 >= 0) {
			return bLeftCheck(curY, curX - 1);
		}
		return false;
	}
	private boolean bUpCheck(int curY, int curX) {
		if(gameBoard[curY][curX] == 'Q' || gameBoard[curY][curX] == 'R') {
			return true;
		}
		if(gameBoard[curY][curX] != '.' && gameBoard[curY][curX] != 'k') {
			return false;
		}
		if(curY - 1 >= 0) {
			return bUpCheck(curY - 1, curX);
		}
		return false;
	}
	
	//check for checks from queens or bishops
	private boolean bDiagCheck() {
		if(bRightDCheck(blackY, blackX)) {
			return true;
		}
		if(bLeftDCheck(blackY, blackX)) {
			return true;
		}
		if(bRightUCheck(blackY, blackX)) {
			return true;
		}
		if(bLeftUCheck(blackY, blackX)) {
			return true;
		}
		return false;
	}
	
	//recursive helpers for bDiagCheck
	private boolean bRightDCheck(int curY, int curX) {
		if(gameBoard[curY][curX] == 'Q' || gameBoard[curY][curX] == 'B') {
			return true;
		}
		if(gameBoard[curY][curX] != '.' && gameBoard[curY][curX] != 'k') {
			return false;
		}
		if(curY + 1 < 8 && curX + 1 < 8) {
			return bRightDCheck(curY + 1, curX + 1);
		}
		return false;
	}
	private boolean bLeftDCheck(int curY, int curX) {
		if(gameBoard[curY][curX] == 'Q' || gameBoard[curY][curX] == 'B') {
			return true;
		}
		if(gameBoard[curY][curX] != '.' && gameBoard[curY][curX] != 'k') {
			return false;
		}
		if(curY + 1 < 8 && curX - 1 >= 0) {
			return bLeftDCheck(curY + 1, curX - 1);
		}
		return false;
	}
	private boolean bRightUCheck(int curY, int curX) {
		if(gameBoard[curY][curX] == 'Q' || gameBoard[curY][curX] == 'B') {
			return true;
		}
		if(gameBoard[curY][curX] != '.' && gameBoard[curY][curX] != 'k') {
			return false;
		}
		if(curY - 1 >= 0 && curX + 1 < 8) {
			return bRightUCheck(curY - 1, curX + 1);
		}
		return false;
	}
	private boolean bLeftUCheck(int curY, int curX) {
		if(gameBoard[curY][curX] == 'Q' || gameBoard[curY][curX] == 'B') {
			return true;
		}
		if(gameBoard[curY][curX] != '.' && gameBoard[curY][curX] != 'k') {
			return false;
		}
		if(curY - 1 >= 0 && curX - 1 >= 0) {
			return bLeftUCheck(curY - 1, curX - 1);
		}
		return false;
	}
	
	private boolean bPawnCheck() {
		if(this.blackY < this.gameBoard.length - 1) { //pawn check
			if(blackX > 0 && gameBoard[blackY + 1][blackX - 1] == 'P') {
				return true;
			}
			if(blackX < gameBoard.length - 1 && 
					gameBoard[blackY + 1][blackX + 1] == 'P') {
				return true;
			}
		}
		return false;
	}
	
	private boolean whiteCheck() {
		if(wDiagCheck()) {
			return true;
		}
		if(wLineCheck()) {
			return true;
		}
		if(wHorseCheck()) {
			return true;
		}
		if(wPawnCheck()) {
			return true;
		}
		return false;
	}
	private boolean wHorseCheck() {
		if(whiteX + 2 < 8 && whiteY - 1 >= 0 && gameBoard[whiteY - 1][whiteX + 2] == 'n') {
			return true;
		}
		if(whiteX + 2 < 8 && whiteY + 1 < 8 && gameBoard[whiteY + 1][whiteX + 2] == 'n') {
			return true;
		}
		if(whiteX + 1 < 8 && whiteY + 2 < 8 && gameBoard[whiteY + 2][whiteX + 1] == 'n') {
			return true;
		}
		if(whiteX - 1 >= 0 && whiteY + 2 < 8 && gameBoard[whiteY + 2][whiteX - 1] == 'n') {
			return true;
		}
		if(whiteX - 2 >= 0 && whiteY + 1 < 8 && gameBoard[whiteY + 1][whiteX - 2] == 'n') {
			return true;
		}
		if(whiteX - 2 >= 0 && whiteY - 1 >= 0 && gameBoard[whiteY - 1][whiteX - 2] == 'n') {
			return true;
		}
		if(whiteX - 1 >= 0 && whiteY - 2 >= 0 && gameBoard[whiteY - 2][whiteX - 1] == 'n') {
			return true;
		}
		if(whiteX + 1 < 8 && whiteY - 2 >= 0 && gameBoard[whiteY - 2][whiteX + 1] == 'n') {
			return true;
		}
		return false;
	}
	private boolean wLineCheck() {
		if(wRightCheck(whiteY, whiteX)) {
			return true;
		}
		if(wDownCheck(whiteY, whiteX)) {
			return true;
		}
		if(wLeftCheck(whiteY, whiteX)) {
			return true;
		}
		if(wUpCheck(whiteY, whiteX)) {
			return true;
		}
		return false;
	}
	
	//recursive helpers for wLineCheck
	private boolean wRightCheck(int curY, int curX) {
		if(gameBoard[curY][curX] == 'q' || gameBoard[curY][curX] == 'r') {
			return true;
		}
		if(gameBoard[curY][curX] != '.' && gameBoard[curY][curX] != 'K') {
			return false;
		}
		if(curX + 1 < 8) {
			return wRightCheck(curY, curX + 1);
		}
		return false;
	}
	private boolean wDownCheck(int curY, int curX) {
		if(gameBoard[curY][curX] == 'q' || gameBoard[curY][curX] == 'r') {
			return true;
		}
		if(gameBoard[curY][curX] != '.' && gameBoard[curY][curX] != 'K') {
			return false;
		}
		if(curY + 1 < 8) {
			return wDownCheck(curY + 1, curX);
		}
		return false;
	}
	private boolean wLeftCheck(int curY, int curX) {
		if(gameBoard[curY][curX] == 'q' || gameBoard[curY][curX] == 'r') {
			return true;
		}
		if(gameBoard[curY][curX] != '.' && gameBoard[curY][curX] != 'K') {
			return false;
		}
		if(curX > 0) {
			return wLeftCheck(curY, curX - 1);
		}
		return false;
	}
	private boolean wUpCheck(int curY, int curX) {
		if(gameBoard[curY][curX] == 'q' || gameBoard[curY][curX] == 'r') {
			return true;
		}
		if(gameBoard[curY][curX] != '.' && gameBoard[curY][curX] != 'K') {
			return false;
		}
		if(curY > 0) {
			return wUpCheck(curY - 1, curX);
		}
		return false;
	}
	
	
	private boolean wDiagCheck() {
		if(wRightDCheck(whiteY, whiteX)) {
			return true;
		}
		if(wLeftDCheck(whiteY, whiteX)) {
			return true;
		}
		if(wRightUCheck(whiteY, whiteX)) {
			return true;
		}
		if(wLeftUCheck(whiteY, whiteX)) {
			return true;
		}
		return false;
	}
	
	//recursive helpers for wDiagCheck
	private boolean wRightDCheck(int curY, int curX) {
		if(gameBoard[curY][curX] == 'q' || gameBoard[curY][curX] == 'b') {
			return true;
		}
		if(gameBoard[curY][curX] != '.' && gameBoard[curY][curX] != 'K') {
			return false;
		}
		if(curY + 1 < 8 && curX + 1 < 8) {
			return wRightDCheck(curY + 1, curX + 1);
		}
		return false;
	}
	private boolean wLeftDCheck(int curY, int curX) {
		if(gameBoard[curY][curX] == 'q' || gameBoard[curY][curX] == 'b') {
			return true;
		}
		if(gameBoard[curY][curX] != '.' && gameBoard[curY][curX] != 'K') {
			return false;
		}
		if(curY + 1 < 8 && curX - 1 >= 0) {
			return wLeftDCheck(curY + 1, curX - 1);
		}
		return false;
	}
	private boolean wRightUCheck(int curY, int curX) {
		if(gameBoard[curY][curX] == 'q' || gameBoard[curY][curX] == 'b') {
			return true;
		}
		if(gameBoard[curY][curX] != '.' && gameBoard[curY][curX] != 'K') {
			return false;
		}
		if(curY - 1 >= 0 && curX + 1 < 8) {
			return wRightUCheck(curY - 1, curX + 1);
		}
		return false;
	}
	private boolean wLeftUCheck(int curY, int curX) {
		if(gameBoard[curY][curX] == 'q' || gameBoard[curY][curX] == 'b') {
			return true;
		}
		if(gameBoard[curY][curX] != '.' && gameBoard[curY][curX] != 'K') {
			return false;
		}
		if(curY - 1 >= 0 && curX - 1 >= 0) {
			return wLeftUCheck(curY - 1, curX - 1);
		}
		return false;
	}
	
	private boolean wPawnCheck() {
		if(whiteY > 0) { //pawn check
			if(whiteX > 0 && gameBoard[whiteY - 1][whiteX - 1] == 'p') {
				return true;
			}
			if(whiteX < gameBoard.length - 1 && 
					gameBoard[whiteY - 1][whiteX + 1] == 'p') {
				return true;
			}
		}
		return false;
	}
}
