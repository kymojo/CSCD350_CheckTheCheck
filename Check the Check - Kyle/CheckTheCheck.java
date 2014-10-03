/* Twenty Hats
 * Nathan Graham, Kyle Johnson, Eric Laib, Daniel Moore
 * CSCD 350
 * 
 * This program takes input representing various states of a
 * chess board and checks each state if a king is in check.
 */

import java.io.*;

public class CheckTheCheck {

   public static void main(String args[])  {
      
      ChessBoard board = new ChessBoard();
      
      while (board.readBoard() == 1) {
         if (board.findKings() == 1)
            board.checkTheCheck();
      }
   }
}