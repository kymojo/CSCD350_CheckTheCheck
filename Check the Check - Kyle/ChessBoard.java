/* CSCD 350 // Software Engineering // Assignment 1
 * Team: Twenty Hats
 * Members:
 * - Nathan Graham
 * - Kyle Johnson
 * - Eric Laib
 * - Daniel Moore
 * 
 * This program takes input representing various states of a
 * chess board and checks each state if a king is in check.
 */

import java.util.Scanner;
import java.io.*;

public class ChessBoard {
   
   private char[][] board = new char[8][8];
   private Scanner reader;
   private int gameCount = 1;
   
   private int kingB_x, kingB_y, kingW_x, kingW_y;
   
   private int BLACK = 0;
   private int WHITE = 1;
   
   //============================================ 
     
   public ChessBoard(String fin) throws FileNotFoundException {
      reader = new Scanner(new File(fin));
   }
   
   //============================================ 
   
   //Read from the input file an 8x8 2D character array.
   public int readBoard() {
      
      if ( !reader.hasNextLine() )
         return 0;
      
      String line;
      for (int i = 0; i < 8; i++) {
         line = reader.nextLine();
         for (int j = 0; j < 8; j++) {
            board[i][j] = line.charAt(j);
         }
      }
      
      if ( reader.hasNextLine() )
         reader.nextLine();
      return 1;
   }
   
   //============================================
   
   //Locate the kings (0 if invalid, 1 if valid)
   public int findKings() {
   
      kingB_x = -1;
      kingB_y = -1;
      kingW_x = -1;
      kingW_y = -1;
      
      for (int i = 0; i < 8; i++) {
      
         for (int j = 0; j < 8; j++) {
            
            if (board[i][j] == 'k') {
               kingB_x = j;
               kingB_y = i;
            }
            
            if (board[i][j] == 'K') {
               kingW_x = j;
               kingW_y = i;
            }
         }
      }
      
      if (kingB_x == -1 || kingW_x == -1)
         return 0; 
      return 1;
   }
   
   //============================================ 
   
   //Check if either king is in check. Print result.
   public void checkTheCheck() {
      
      int check = 0;
      
      if (checkEnemies(kingB_x, kingB_y, BLACK) == 1)
         System.out.println("Game #"+gameCount+": The black king is in check.");
      else
         if (checkEnemies(kingW_x, kingW_y, WHITE) == 1)
            System.out.println("Game #"+gameCount+": The white king is in check.");
         else
            System.out.println("Game #"+gameCount+": No kings in check.");
      gameCount++;
   }
   
   //============================================
   
   //Check if a king is in check. (0 = safe, 1 = in check)
   public int checkEnemies(int x, int y, int type) {
      
      int check = 0;
      
      check += checkKnights(x, y, type);
      check += checkPawns(  x, y, type);
      check += checkLine(x,y,type,"N" );
      check += checkLine(x,y,type,"E" );
      check += checkLine(x,y,type,"S" );
      check += checkLine(x,y,type,"W" );
      check += checkLine(x,y,type,"NE");
      check += checkLine(x,y,type,"NW");
      check += checkLine(x,y,type,"SE");
      check += checkLine(x,y,type,"SW");
      
      if (check > 0)
         return 1;
      return 0;
   }
   
   //============================================ 
   
   //Check a position on the board.
   public char checkSpot(int x, int y) {
   
      if (x < 0 || x > 7 || y < 0 || y > 7)
         return '.';
          
      return board[y][x];
   }
   
   //============================================ 
   
   //Check if an enemy knight has a given king in check. (0 = safe, 1 = in check)
   public int checkKnights(int x, int y, int type) {
   
      char spots[] = {
         checkSpot(x + 1, y - 2),
         checkSpot(x + 1, y + 2),
         checkSpot(x + 2, y - 1),
         checkSpot(x + 2, y + 1),
         checkSpot(x - 1, y - 2),
         checkSpot(x - 1, y + 2),
         checkSpot(x - 2, y - 1),
         checkSpot(x - 2, y + 1),
      };
      
      for(int i = 0; i < 8; i++) {
         if (spots[i] == '.')
            continue;
         if (checkPieceType(spots[i]) != type)
            if (Character.toLowerCase(spots[i]) == 'n')
               return 1;
      }
      
      return 0;
   }
   
   //============================================
   
   //Check if an enemy pawn has a given king in check. (0 = safe, 1 = in check)
   public int checkPawns(int x, int y, int type) {
   
      char spots[] = new char[2];
   
      if (type == WHITE) {
         spots[0] = checkSpot(x+1,y-1);
         spots[1] = checkSpot(x-1,y-1);
      } else {
         spots[0] = checkSpot(x+1,y+1);
         spots[1] = checkSpot(x-1,y+1);
      }
      
      for(int i = 0; i < 2; i++) {
         if (spots[i] == '.')
            continue;
         if (checkPieceType(spots[i]) != type)
            if (Character.toLowerCase(spots[i]) == 'p')
               return 1;
      }
      
      return 0;
   }
   
   //============================================
   
   //Recursively check a direction for threats. (0 = safe, 1 = in check)
   public int checkLine(int x, int y, int type, String dir) {
      
      int diagonal = 0;
      
      switch (dir) {
         case "N" :  y--;           diagonal=0;  break;
         case "E" :           x++;  diagonal=0;  break;
         case "S" :  y++;           diagonal=0;  break;
         case "W" :           x--;  diagonal=0;  break;
         case "NE":  y--;     x++;  diagonal=1;  break;
         case "SE":  y++;     x++;  diagonal=1;  break;
         case "SW":  y++;     x--;  diagonal=1;  break;
         case "NW":  y--;     x--;  diagonal=1;  break;
      }
      
      if (x < 0 || x > 7 || y < 0 || y > 7)
         return 0;
         
      char piece = checkSpot(x,y);
      int threat = checkLineThreat(piece,type,diagonal);
      
      if (threat == 0 )
         return checkLine(x,y,type,dir);
         
      if (threat == -1)
         return 0;
      
      return 1;
   }
   
   //============================================
   
   //Check if a given piece is a linear threat. (-1 = stop, 0 = safe, 1 = in check)
   public int checkLineThreat(char piece, int type, int diagonal) {
   
      if (piece == '.')
         return 0;
      
      if (checkPieceType(piece) == type)
         return -1;
      
      piece = Character.toLowerCase(piece);
      
      if ((piece == 'r' && diagonal == 0) ||
          (piece == 'b' && diagonal == 1) ||
          (piece == 'q'))
         return 1;
      
      return -1;
   }
   
   //============================================
   
   //Check what color a given piece is. (1 = White, 0 = Black)
   public int checkPieceType(char piece) {
      if (piece == '.')
         return 0;
      if (Character.isLowerCase(piece))
         return BLACK;
      return WHITE;
   }
}