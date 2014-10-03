/* Twenty Hats
 * Nathan Graham, Kyle Johnson, Eric Laib, Daniel Moore
 * CSCD 350
 * 
 * This program takes input representing various states of a
 * chess board and checks each state if a king is in check.
 */

import java.io.*;

public class CheckTheCheck {

   // Main takes a filename argument from the command line. Reads from input.txt by default.
   public static void main(String args[])  {
      
      String fin;
      
      try {
         if (args.length > 0)
            fin = args[0];
         else {
            fin = "input.txt";
         }
      
         ChessBoard board = new ChessBoard(fin);
      
         while (board.readBoard() == 1) {
            if (board.findKings() == 1)
               board.checkTheCheck();
         }
         
      } catch (FileNotFoundException e) {
         System.out.println("Invalid filename!");
      }
   }
}