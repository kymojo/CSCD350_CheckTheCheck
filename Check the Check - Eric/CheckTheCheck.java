/* Twenty Hats
 * Eric Laib, Kyle Johnson, Nathan Graham, Daniel Moore
 * CSCD 350
 *
 * Writen by Eric Laib, this program reads in 8 rows of 8 characters
 * intended to be a chess game, and then prints out if a next move 
 * could result in a check.
 */

import java.util.Scanner;
import java.io.File;

public class CheckTheCheck{
   public static void main(String[] args){
   
      char[][] gameBoard = null;
      Check checkCheck = new Check();
      Scanner fin = null;
		fin = new Scanner(System.in);
      int i = 0, game = 1;
      String str = "", output = "checkForChecks method didn't return a string";
      String endString = "........" +
                         "........" +
                         "........" +
                         "........" +
                         "........" +
                         "........" +
                         "........" +
                         "........";
      try{
         while(fin.hasNext()){
            while(i < 8){
               str += fin.nextLine();
               i++;               
            }            
            //Tests if the ending board has been entered
            if(str.compareTo(endString) == 0){
               System.exit(-1);
            }else{               
               gameBoard = checkCheck.fillGameBoards(str);               
               output = checkCheck.checkForChecks(gameBoard);;
               if(output.compareTo("") == 0){
                  System.out.println("Game #" + game + ": No King is in check.");
               }else
                  System.out.println("Game #" + game + ": " + output);
            }
            fin.nextLine();
            output = "checkForChecks method didn't return a string";
            str = "";
            game++;
            i = 0;                        
         }
      }catch(Exception e){
         System.out.println("Error reading from file");
      }
   }
}