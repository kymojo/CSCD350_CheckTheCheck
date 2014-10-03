/* Twenty Hats
 * Eric Laib, Kyle Johnson, Nathan Graham, Daniel Moore
 * CSCD 350
 *
 * Writen by Eric Laib, this program reads in 8 rows of 8 characters
 * intended to be a chess game, and then prints out if a next move 
 * could result in a check.
 */

public class Check {

   //Fills a 2D array with the input brought in from the console
   //======================================================================================================
   public static char[][] fillGameBoards(String board){
		char[][] gameBoard = new char[8][8];
		int row, col, i = 0;
      
      for(row = 0; row < 8; row++){
		   for(col = 0; col < 8; col++){
            gameBoard[row][col] = board.charAt(i);
            i++;
         }
      }
      return gameBoard;
	}
   
   //Gets the coordiantes for the black and white kings
   //
   //These for loops are going to return the 2D array cordinates in 1 based format, I took care of 
   //that by subtracting the cordinates by 1 before passing them into methods.
   //======================================================================================================
   public static String checkForChecks(char[][] board){
   
      int blackKingRow = 0, blackKingCol = 0;
      int whiteKingRow = 0, whiteKingCol = 0;
      boolean bkFound = false, wkFound = false;
      String output = "";
      
      for(blackKingRow = 0; blackKingRow < 8 && bkFound == false; blackKingRow++){
		   for(blackKingCol = 0; blackKingCol < 8 && bkFound == false; blackKingCol++){
            if(board[blackKingRow][blackKingCol] == 'k')
               bkFound = true;           
         }
      }
      
      for(whiteKingRow = 0; whiteKingRow < 8 && wkFound == false; whiteKingRow++){
		   for(whiteKingCol = 0; whiteKingCol < 8 && wkFound == false; whiteKingCol++){
            if(board[whiteKingRow][whiteKingCol] == 'K')
               wkFound = true;           
         }
      }     
               
         output = pawnCheck(blackKingRow - 1, blackKingCol - 1, whiteKingRow - 1, whiteKingCol - 1, board);
            if(output.compareTo("") != 0)
               return output;
         output = rookCheck(blackKingRow - 1, blackKingCol - 1, whiteKingRow - 1, whiteKingCol - 1, board);
            if(output.compareTo("") != 0)
               return output;
         output = bishopCheck(blackKingRow - 1, blackKingCol - 1, whiteKingRow - 1, whiteKingCol - 1, board);
            if(output.compareTo("") != 0)
               return output;
         output = knightCheck(blackKingRow - 1, blackKingCol - 1, whiteKingRow - 1, whiteKingCol - 1, board);
            if(output.compareTo("") != 0)
               return output;
         output = queenCheck(blackKingRow - 1, blackKingCol - 1, whiteKingRow - 1, whiteKingCol - 1, board);
            if(output.compareTo("") != 0)
               return output;
         
      
      
      return output;
      
   }
   
   //Looks to see if either the white or the black kings can be checked by a pawn
   //======================================================================================================
   public static String pawnCheck(int bkRow, int bkCol, int wkRow, int wkCol, char[][] board){
      try{
         if((bkRow+1 <= 7 && bkCol+1 <= 7) && board[bkRow + 1][bkCol + 1] == 'P'){
            return "Black King is in check.";
         }else if((bkRow+1 <= 7 && bkCol-1 >= 0) && board[bkRow + 1][bkCol - 1] == 'P'){
            return "Black King is in check.";
         }else if(wkRow-1 >= 0 && wkCol-1 >= 0 && board[wkRow - 1][wkCol - 1] == 'p'){
            return "White King is in check.";
         }else if((wkRow-1 >= 0 && wkCol+1 <= 7) && board[wkRow - 1][wkCol + 1] == 'p'){
            return "White King is in check.";
         }else{
            return "";
         }
      }catch(Exception e){
         System.out.println("Error in Pawn Check");
         return "";
      }
   }
   
   //Looks to see if either the white or the black kings can be checked by a rook
   //======================================================================================================
   public static String rookCheck(int bkRow, int bkCol, int wkRow, int wkCol, char[][] board){
      try{      
         int i;
         for(i = bkRow; i <= 7 && (board[i][bkCol] == '.' || board[i][bkCol] == 'R' || board[i][bkCol] == 'k'); i++){
            if(board[i][bkCol] == 'R')
               return "Black King is in check.";
         }     
         for(i = bkRow; i >= 0 && (board[i][bkCol] == '.' || board[i][bkCol] == 'R' || board[i][bkCol] == 'k'); i--){
            if(board[i][bkCol] == 'R')
               return "Black King is in check.";
         }         
         for(i = bkCol; i <= 7 && (board[bkRow][i] == '.' || board[bkRow][i] == 'R' || board[bkRow][i] == 'k'); i++){
            if(board[bkRow][i] == 'R')
               return "Black King is in check."; 
         }
         for(i = bkCol; i >= 0 && (board[bkRow][i] == '.' || board[bkRow][i] == 'R' || board[bkRow][i] == 'k'); i--){
            if(board[bkRow][i] == 'R')
               return "Black King is in check."; 
         }
         
         for(i = wkRow; i <= 7 && (board[i][wkCol] == '.' || board[i][wkCol] == 'r' || board[i][wkCol] == 'K'); i++){
            if(board[i][wkCol] == 'r')
               return "White King is in check.";
         }
         for(i = wkRow; i >= 0 && (board[i][wkCol] == '.' || board[i][wkCol] == 'r' || board[i][wkCol] == 'K'); i--){
            if(board[i][wkCol] == 'r')
               return "White King is in check.";
         }
         for(i = wkCol; i <= 7 && (board[wkRow][i] == '.' || board[wkRow][i] == 'r' || board[wkRow][i] == 'K'); i++){
            if(board[wkRow][i] == 'r')
               return "White King is in check."; 
         }
         for(i = wkCol; i >= 0 && (board[wkRow][i] == '.' || board[wkRow][i] == 'r' || board[wkRow][i] == 'K'); i--){
            if(board[wkRow][i] == 'r')
               return "White King is in check."; 
         }
         
         return "";
      }catch(Exception e){
         System.out.println("Error in Rook Check");
         return "";
      }
   }
   
   //Looks to see if the bishop can check either king.
   //======================================================================================================
   public static String bishopCheck(int bkRow, int bkCol, int wkRow, int wkCol, char[][] board){
      int i, j;
      try{
         for(i = bkRow, j = bkCol; i <= 7 && j <= 7 && (board[i][j] == '.' || board[i][j] == 'B' || board[i][j] == 'k'); i++, j++){
            if(board[i][j] == 'B')
               return "Black King is in check.";
         }
         for(i = bkRow, j = bkCol; i >= 0 && j >= 0 && (board[i][j] == '.' || board[i][j] == 'B' || board[i][j] == 'k'); i--, j--){
            if(board[i][j] == 'B')
               return "Black King is in check.";
         }
         for(i = bkRow, j = bkCol; i >= 0 && j <= 7 && (board[i][j] == '.' || board[i][j] == 'B' || board[i][j] == 'k'); i--, j++){
            if(board[i][j] == 'B')
               return "Black King is in check.";
         }
         for(i = bkRow, j = bkCol; i <= 7 && j >= 0 && (board[i][j] == '.' || board[i][j] == 'B' || board[i][j] == 'k'); i++, j--){
            if(board[i][j] == 'B')
               return "Black King is in check.";
         }
         
         for(i = wkRow, j = wkCol; i <= 7 && j <= 7 && (board[i][j] == '.' || board[i][j] == 'b' || board[i][j] == 'K'); i++, j++){
            if(board[i][j] == 'b')
               return "White King is in check.";
         }
         for(i = wkRow, j = wkCol; i >= 0 && j >= 0 && (board[i][j] == '.' || board[i][j] == 'b' || board[i][j] == 'K'); i--, j--){
            if(board[i][j] == 'b')
               return "White King is in check.";
         }
         for(i = wkRow, j = wkCol; i >= 0 && j <= 7 && (board[i][j] == '.' || board[i][j] == 'b' || board[i][j] == 'K'); i--, j++){
            if(board[i][j] == 'b')
               return "White King is in check.";
         }
         for(i = wkRow, j = wkCol; i <= 7 && j >= 0 && (board[i][j] == '.' || board[i][j] == 'b' || board[i][j] == 'K'); i++, j--){
            if(board[i][j] == 'b')
               return "White King is in check.";
         }      
          
         return "";
      }catch(Exception e){
         System.out.println("Error in Bishop Check");
         return "";
      }
   }
   
   //Looks to see if a knight can check either king
   //======================================================================================================
   public static String knightCheck(int bkRow, int bkCol, int wkRow, int wkCol, char[][] board){
      try{      
         
         if((bkRow - 2 >= 0 && bkCol - 1 >= 0) && board[bkRow - 2][bkCol - 1] == 'N')
            return "Black King is in check.";
         else if((bkRow - 2 >= 0 && bkCol + 1 <= 7) && board[bkRow - 2][bkCol + 1] == 'N')     
            return "Black King is in check.";
         else if((bkRow + 2 <= 7 && bkCol + 1 <= 7) && board[bkRow + 2][bkCol + 1] == 'N')     
            return "Black King is in check.";
         else if((bkRow + 2 <= 7 && bkCol - 1 >= 0) && board[bkRow + 2][bkCol - 1] == 'N')     
            return "Black King is in check.";      
         else if((bkRow - 1 >= 0 && bkCol + 2 <= 7) && board[bkRow - 1][bkCol + 2] == 'N')     
            return "Black King is in check.";
         else if((bkRow + 1 <= 7 && bkCol + 2 <= 7) && board[bkRow + 1][bkCol + 2] == 'N')     
            return "Black King is in check.";
         else if((bkRow + 1 <= 7 && bkCol - 2 >= 0) && board[bkRow + 1][bkCol - 2] == 'N')     
            return "Black King is in check.";
         else if((bkRow - 1 >= 0 && bkCol - 2 >= 0) && board[bkRow - 1][bkCol - 2] == 'N')    
            return "Black King is in check.";
           
         else if((wkRow - 2 >= 0 && wkCol - 1 >= 0) && board[wkRow - 2][wkCol - 1] == 'n')
            return "White King is in check.";
         else if((wkRow - 2 >= 0 && wkCol + 1 <= 7) && board[wkRow - 2][wkCol + 1] == 'n')     
            return "White King is in check.";
         else if((wkRow + 2 <= 7 && wkCol + 1 <= 7) && board[wkRow + 2][wkCol + 1] == 'n')     
            return "White King is in check.";
         else if((wkRow + 2 <= 7 && wkCol - 1 >= 0) && board[wkRow + 2][wkCol - 1] == 'n')     
            return "White King is in check.";      
         else if((wkRow - 1 >= 0 && wkCol + 2 <= 7) && board[wkRow - 1][wkCol + 2] == 'n')     
            return "White King is in check.";
         else if((wkRow + 1 <= 7 && wkCol + 2 <= 7) && board[wkRow + 1][wkCol + 2] == 'n')     
            return "White King is in check.";
         else if((wkRow + 1 <= 7 && wkCol - 2 >= 0) && board[wkRow + 1][wkCol - 2] == 'n')     
            return "White King is in check.";
         else if((wkRow - 1 >= 0 && wkCol - 2 >= 0) && board[wkRow - 1][wkCol - 2] == 'n')     
            return "White King is in check.";

       
            
      }catch(Exception e){
         System.out.println("Error in Knight Check");
         return "";
      }
      
      return "";
   }
   
   //Looks to see if a queen can check either king
   //======================================================================================================
   public static String queenCheck(int bkRow, int bkCol, int wkRow, int wkCol, char[][] board){
      
      int i, j, k;
      try{
         //Up and Down
         for(i = bkRow; i <= 7 && (board[i][bkCol] == '.' || board[i][bkCol] == 'Q' || board[i][bkCol] == 'k'); i++){
            if(board[i][bkCol] == 'Q')
               return "Black King is in check.";
         }     
         for(i = bkRow; i >= 0 && (board[i][bkCol] == '.' || board[i][bkCol] == 'Q' || board[i][bkCol] == 'k'); i--){
            if(board[i][bkCol] == 'Q')
               return "Black King is in check.";
         }         
         for(i = bkCol; i <= 7 && (board[bkRow][i] == '.' || board[bkRow][i] == 'Q' || board[bkRow][i] == 'k'); i++){
            if(board[bkRow][i] == 'Q')
               return "Black King is in check."; 
         }
         for(i = bkCol; i >= 0 && (board[bkRow][i] == '.' || board[bkRow][i] == 'Q' || board[bkRow][i] == 'k'); i--){
            if(board[bkRow][i] == 'Q')
               return "Black King is in check."; 
         }
         
         for(i = wkRow; i <= 7 && (board[i][wkCol] == '.' || board[i][wkCol] == 'q' || board[i][wkCol] == 'K'); i++){
            if(board[i][wkCol] == 'q')
               return "White King is in check.";
         }
         for(i = wkRow; i >= 0 && (board[i][wkCol] == '.' || board[i][wkCol] == 'q' || board[i][wkCol] == 'K'); i--){
            if(board[i][wkCol] == 'q')
               return "White King is in check.";
         }
         for(i = wkCol; i <= 7 && (board[wkRow][i] == '.' || board[wkRow][i] == 'q' || board[wkRow][i] == 'K'); i++){
            if(board[wkRow][i] == 'q')
               return "White King is in check."; 
         }
         for(i = wkCol; i >= 0 && (board[wkRow][i] == '.' || board[wkRow][i] == 'q' || board[wkRow][i] == 'K'); i--){
            if(board[wkRow][i] == 'q')
               return "White King is in check."; 
         }
         
         //Diagonally
         for(j = bkRow, k = bkCol; j <= 7 && k <= 7 && (board[j][k] == '.' || board[j][k] == 'Q' || board[j][k] == 'k'); j++, k++){
            if(board[j][k] == 'Q')
               return "Black King is in check.";
         }
         for(j = bkRow, k = bkCol; j >= 0 && k >= 0 && (board[j][k] == '.' || board[j][k] == 'Q' || board[j][k] == 'k'); j--, k--){
            if(board[j][k] == 'Q')
               return "Black King is in check.";
         }
         for(j = bkRow, k = bkCol; j >= 0 && k <= 7 && (board[j][k] == '.' || board[j][k] == 'Q' || board[j][k] == 'k'); j--, k++){
            if(board[j][k] == 'Q')
               return "Black King is in check.";
         }
         for(j = bkRow, k = bkCol; j <= 7 && k >= 0 && (board[j][k] == '.' || board[j][k] == 'Q' || board[j][k] == 'k'); j++, k--){
            if(board[j][k] == 'Q')
               return "Black King is in check.";
         }
         
         for(j = wkRow, k = wkCol; j <= 7 && k <= 7 && (board[j][k] == '.' || board[j][k] == 'q' || board[j][k] == 'K'); j++, k++){
            if(board[j][k] == 'q')
               return "White King is in check.";
         }
         for(j = wkRow, k = wkCol; j >= 0 && k >= 0 && (board[j][k] == '.' || board[j][k] == 'q' || board[j][k] == 'K'); j--, k--){
            if(board[j][k] == 'q')
               return "White King is in check.";
         }
         for(j = wkRow, k = wkCol; j >= 0 && k <= 7 && (board[j][k] == '.' || board[j][k] == 'q' || board[j][k] == 'K'); j--, k++){
            if(board[j][k] == 'q')
               return "White King is in check.";
         }
         for(j = wkRow, k = wkCol; j <= 7 && k >= 0 && (board[j][k] == '.' || board[j][k] == 'q' || board[j][k] == 'K'); j++, k--){
            if(board[j][k] == 'q')
               return "White King is in check.";
         }
         return "";
      }catch(Exception e){
         System.out.println("Error in Queen Check");
         return "";
      }
      
   }
   
   //Prints the game board(s) entered (not currently used in the program)
   //======================================================================================================
   public static void printGameBoards(char[][] gameBoard){
      int row, col;
      
      for(row = 0; row < 8; row++){
			for(col = 0; col < 8 ; col++){
				System.out.print(gameBoard[row][col]);
         }
         System.out.println();         
		}
      System.out.println("\n--------\n");
   }
}