/* Team: Twenty-Hats
 * Members names: Nathan Graham, Daniel Moore, Eric Laib, Kyle Johnson
 * CSCD 350
 *
 * Description: Input file will be read and then analyzed
 * to see if that game, if a king is in check then it will return
 * who is in check or if any one is at all.
 */

import java.io.*;
import java.util.Scanner;

public class CheckTheCheck{

	public Scanner sc;
	public File file;
	public boolean endFlag;
	public String[][] board;
	public int kWLoc[];
	public int kBLoc[];
	public int gameNum;
	
	//====================================================
	
	public CheckTheCheck() throws FileNotFoundException{
		//System.setIn(new FileInputStream("input.txt"));
		
		this.sc = new Scanner(System.in);
		/*
		this.file = new File("input.txt");
		try {
			this.sc = new Scanner(file);
		} catch (FileNotFoundException e) {
		} */
		this.endFlag = false;
		this.board = new String [8][8];
		this.kWLoc = new int [2];
		this.kBLoc = new int [2];
	}
	
	//====================================================
	//Read in the board from file
	public void boardReader(){
		
		this.gameNum = 1;
		while(this.sc.hasNextLine()){
			
			int i=0;
			while(this.sc.hasNextLine()&&i<8){
				String x = sc.nextLine();
				//System.out.println(x);
				String[] xi = x.split("(?!^)");
				
				//System.out.println(xi.length);
				for(int u=0;u<8;u++){
					//System.out.print(xi[u]);
				}
				//System.out.println();
				
				for(int j=0;j<8;j++){
					this.board[i][j] = xi[j];
					
					if(xi[j].equals("k")){
						this.kBLoc[0] = i;
						this.kBLoc[1] = j;
					}
					if(xi[j].equals("K")){
						this.kWLoc[0] = i;
						this.kWLoc[1] = j;
					}
				}
				
				i++;
			}
			if(this.sc.hasNextLine()){
				String trash = this.sc.nextLine();
			}
			//print_Board();
			//End Read 
			//Start Checking
			
			boolean flagOne = checkingWhiteCheck();
			boolean flagTwo = checkingBlackCheck();
			
			if(flagOne&&flagTwo){
				noCheck();
			}
			
			//end
			this.gameNum++;
		}
		this.sc.close();
	}
	
	//====================================================
	//Call when no one wins the game
	private void noCheck() {
		System.out.println("Game #"+this.gameNum+": no king is in check.");
	}
	
	//====================================================
	//Check to see if the black king is in check
	
	private boolean checkingBlackCheck() {
		int moveV[] = new int [2];
		boolean flag=true;
		
		//north
		if(this.kBLoc[0]>0&&flag){
			moveV[0] = this.kBLoc[0]-1;
			moveV[1] = this.kBLoc[1];
			
			while(this.board[moveV[0]][moveV[1]].equals(".")&&moveV[0]!=0){
				moveV[0]=moveV[0]-1;
			}
			
			if(this.board[moveV[0]][moveV[1]].equals("Q")
					||this.board[moveV[0]][moveV[1]].equals("R")){
				BlackLoses();
				flag=false;
			}
		}
		
		//south
		if(this.kBLoc[0]<7&&flag){
			moveV[0] = this.kBLoc[0]+1;
			moveV[1] = this.kBLoc[1];
			
			while(this.board[moveV[0]][moveV[1]].equals(".")&&moveV[0]!=7){
				moveV[0]=moveV[0]+1;
			}
			
			if(this.board[moveV[0]][moveV[1]].equals("Q")
					||this.board[moveV[0]][moveV[1]].equals("R")){
				BlackLoses();
				flag=false;
			}
		}
		
		//east
		if(this.kBLoc[1]<7&&flag){
			moveV[0] = this.kBLoc[0];
			moveV[1] = this.kBLoc[1]+1;
			
			while(this.board[moveV[0]][moveV[1]].equals(".")&&moveV[1]!=7){
				moveV[1]=moveV[1]+1;
			}
			
			if(this.board[moveV[0]][moveV[1]].equals("Q")
					||this.board[moveV[0]][moveV[1]].equals("R")){
				BlackLoses();
				flag=false;
			}
		}
		
		//west
		if(this.kBLoc[1]>0&&flag){
			moveV[0] = this.kBLoc[0];
			moveV[1] = this.kBLoc[1]-1;
			
			while(this.board[moveV[0]][moveV[1]].equals(".")&&moveV[1]!=0){
				moveV[1]=moveV[1]-1;
			}
			
			if(this.board[moveV[0]][moveV[1]].equals("Q")
					||this.board[moveV[0]][moveV[1]].equals("R")){
				BlackLoses();
				flag=false;
			}
		}
		
		//north east
		if(this.kBLoc[0]>0&&this.kBLoc[1]<7&&flag){
			moveV[0] = this.kBLoc[0]-1;
			moveV[1] = this.kBLoc[1]+1;
			
			while(this.board[moveV[0]][moveV[1]].equals(".")&&moveV[0]!=0&&moveV[1]!=7){
				moveV[0]=moveV[0]-1;
				moveV[1]=moveV[1]+1;
			}
			
			if(this.board[moveV[0]][moveV[1]].equals("Q")
					||this.board[moveV[0]][moveV[1]].equals("B")){
				BlackLoses();
				flag=false;
			}
		}
		
		//north west
		if(this.kBLoc[0]>0&&this.kBLoc[1]>0&&flag){
			moveV[0] = this.kBLoc[0]-1;
			moveV[1] = this.kBLoc[1]-1;
			
			while(this.board[moveV[0]][moveV[1]].equals(".")&&moveV[0]!=0&&moveV[1]!=0){
				moveV[0]=moveV[0]-1;
				moveV[1]=moveV[1]-1;
			}
			
			if(this.board[moveV[0]][moveV[1]].equals("Q")
					||this.board[moveV[0]][moveV[1]].equals("B")){
				BlackLoses();
				flag=false;
			}
		}
		
		//south east
		if(this.kBLoc[0]<7&&this.kBLoc[1]<7&&flag){
			moveV[0] = this.kBLoc[0]+1;
			moveV[1] = this.kBLoc[1]+1;
			
			while(this.board[moveV[0]][moveV[1]].equals(".")&&moveV[0]!=7&&moveV[1]!=7){
				moveV[0]=moveV[0]+1;
				moveV[1]=moveV[1]+1;
			}
			
			if(this.board[moveV[0]][moveV[1]].equals("Q")
					||this.board[moveV[0]][moveV[1]].equals("B")){
				BlackLoses();
				flag=false;
			}
		}
		
		//south west
		if(this.kBLoc[0]<7&&this.kBLoc[1]>0&&flag){
			moveV[0] = this.kBLoc[0]+1;
			moveV[1] = this.kBLoc[1]-1;
			
			while(this.board[moveV[0]][moveV[1]].equals(".")&&moveV[0]!=7&&moveV[1]!=0){
				moveV[0]=moveV[0]+1;
				moveV[1]=moveV[1]-1;
			}
			
			if(this.board[moveV[0]][moveV[1]].equals("Q")
					||this.board[moveV[0]][moveV[1]].equals("B")){
				BlackLoses();
				flag=false;
			}
		}
		
		//knights
		if(flag){
			int kMove_one[] = new int [2];
			int kMove_two[] = new int [2];
			int kMove_three[] = new int [2];
			int kMove_four[] = new int [2];
			int kMove_five[] = new int [2];
			int kMove_six[] = new int [2];
			int kMove_seven[] = new int [2];
			int kMove_eight[] = new int [2];
			
			//north
			kMove_one[0] = this.kBLoc[0]-2;
			kMove_one[1] = this.kBLoc[1]-1;
			
			kMove_five[0] = this.kBLoc[0]-2;
			kMove_five[1] = this.kBLoc[1]+1;
			
			//south
			kMove_two[0] = this.kBLoc[0]+2;
			kMove_two[1] = this.kBLoc[1]-1;
			
			kMove_six[0] = this.kBLoc[0]+2;
			kMove_six[1] = this.kBLoc[1]+1;
			
			//east
			kMove_three[0] = this.kBLoc[0]-1;
			kMove_three[1] = this.kBLoc[1]+2;
			
			kMove_seven[0] = this.kBLoc[0]+1;
			kMove_seven[1] = this.kBLoc[1]+2;
			
			//west
			kMove_four[0] = this.kBLoc[0]-1;
			kMove_four[1] = this.kBLoc[1]-2;
			
			kMove_eight[0] = this.kBLoc[0]+1;
			kMove_eight[1] = this.kBLoc[1]-2;
			
			if(kMove_one[0]>=0&&kMove_one[0]<=7&&kMove_one[1]>=0&&kMove_one[1]<=7){
				//check
				if(this.board[kMove_one[0]][kMove_one[1]].equals("N")){
					BlackLoses();
					flag=false;
				}
			}
			if(kMove_two[0]>=0&&kMove_two[0]<=7&&kMove_two[1]>=0&&kMove_two[1]<=7){
				//check
				if(this.board[kMove_two[0]][kMove_two[1]].equals("N")){
					BlackLoses();
					flag=false;
				}
			}
			if(kMove_three[0]>=0&&kMove_three[0]<=7&&kMove_three[1]>=0&&kMove_three[1]<=7){
				//check
				if(this.board[kMove_three[0]][kMove_three[1]].equals("N")){
					BlackLoses();
					flag=false;
				}
			}
			if(kMove_four[0]>=0&&kMove_four[0]<=7&&kMove_four[1]>=0&&kMove_four[1]<=7){
				//check
				if(this.board[kMove_four[0]][kMove_four[1]].equals("N")){
					BlackLoses();
					flag=false;
				}
			}
			if(kMove_five[0]>=0&&kMove_five[0]<=7&&kMove_five[1]>=0&&kMove_five[1]<=7){
				//check
				if(this.board[kMove_five[0]][kMove_five[1]].equals("N")){
					BlackLoses();
					flag=false;
				}
			}
			if(kMove_six[0]>=0&&kMove_six[0]<=7&&kMove_six[1]>=0&&kMove_six[1]<=7){
				//check
				if(this.board[kMove_six[0]][kMove_six[1]].equals("N")){
					BlackLoses();
					flag=false;
				}
			}
			if(kMove_seven[0]>=0&&kMove_seven[0]<=7&&kMove_seven[1]>=0&&kMove_seven[1]<=7){
				//check
				if(this.board[kMove_seven[0]][kMove_seven[1]].equals("N")){
					BlackLoses();
					flag=false;
				}
			}
			if(kMove_eight[0]>=0&&kMove_eight[0]<=7&&kMove_eight[1]>=0&&kMove_eight[1]<=7){
				//check
				if(this.board[kMove_eight[0]][kMove_eight[1]].equals("N")){
					BlackLoses();
					flag=false;
				}
			}
			
		}
		
		//pawn
		if(this.kBLoc[0]>0&&this.kBLoc[1]<7&&this.kBLoc[1]>0&&flag){
			int moveVV[] = new int [2];
			
			moveV[0] = this.kBLoc[0]+1;
			moveV[1] = this.kBLoc[1]+1;
			moveVV[0] = this.kBLoc[0]+1;
			moveVV[1] = this.kBLoc[1]-1;
			
			
			
			if(this.board[moveV[0]][moveV[1]].equals("P")){
				BlackLoses();
				flag=false;
			}
			else if(this.board[moveVV[0]][moveVV[1]].equals("P")){
				BlackLoses();
				flag=false;
			}
		}
		return flag;
	}

	//====================================================
	//check to see if the white king is in check
	
	public boolean checkingWhiteCheck() {
		int moveV[] = new int [2];
		boolean flag=true;
		
		//north
		if(this.kWLoc[0]>0&&flag){
			moveV[0] = this.kWLoc[0]-1;
			moveV[1] = this.kWLoc[1];
			
			while(this.board[moveV[0]][moveV[1]].equals(".")&&moveV[0]!=0){
				moveV[0]=moveV[0]-1;
			}
			
			if(this.board[moveV[0]][moveV[1]].equals("q")
					||this.board[moveV[0]][moveV[1]].equals("r")){
				WhiteLoses();
				flag=false;
			}
		}
		
		//south
		if(this.kWLoc[0]<7&&flag){
			moveV[0] = this.kWLoc[0]+1;
			moveV[1] = this.kWLoc[1];
			
			while(this.board[moveV[0]][moveV[1]].equals(".")&&moveV[0]!=7){
				moveV[0]=moveV[0]+1;
			}
			
			if(this.board[moveV[0]][moveV[1]].equals("q")
					||this.board[moveV[0]][moveV[1]].equals("r")){
				WhiteLoses();
				flag=false;
			}
		}
		
		//east
		if(this.kWLoc[1]<7&&flag){
			moveV[0] = this.kWLoc[0];
			moveV[1] = this.kWLoc[1]+1;
			
			while(this.board[moveV[0]][moveV[1]].equals(".")&&moveV[1]!=7){
				moveV[1]=moveV[1]+1;
			}
			
			if(this.board[moveV[0]][moveV[1]].equals("q")
					||this.board[moveV[0]][moveV[1]].equals("r")){
				WhiteLoses();
				flag=false;
			}
		}
		
		//west
		if(this.kWLoc[1]>0&&flag){
			moveV[0] = this.kWLoc[0];
			moveV[1] = this.kWLoc[1]-1;
			
			while(this.board[moveV[0]][moveV[1]].equals(".")&&moveV[1]!=0){
				moveV[1]=moveV[1]-1;
			}
			
			if(this.board[moveV[0]][moveV[1]].equals("q")
					||this.board[moveV[0]][moveV[1]].equals("r")){
				WhiteLoses();
				flag=false;
			}
		}
		
		//north east
		if(this.kWLoc[0]>0&&this.kWLoc[1]<7&&flag){
			moveV[0] = this.kWLoc[0]-1;
			moveV[1] = this.kWLoc[1]+1;
			
			while(this.board[moveV[0]][moveV[1]].equals(".")&&moveV[0]!=0&&moveV[1]!=7){
				moveV[0]=moveV[0]-1;
				moveV[1]=moveV[1]+1;
			}
			
			if(this.board[moveV[0]][moveV[1]].equals("q")
					||this.board[moveV[0]][moveV[1]].equals("b")){
				WhiteLoses();
				flag=false;
			}
		}
		
		//north west
		if(this.kWLoc[0]>0&&this.kWLoc[1]>0&&flag){
			moveV[0] = this.kWLoc[0]-1;
			moveV[1] = this.kWLoc[1]-1;
			
			while(this.board[moveV[0]][moveV[1]].equals(".")&&moveV[0]!=0&&moveV[1]!=0){
				moveV[0]=moveV[0]-1;
				moveV[1]=moveV[1]-1;
			}
			
			if(this.board[moveV[0]][moveV[1]].equals("q")
					||this.board[moveV[0]][moveV[1]].equals("b")){
				WhiteLoses();
				flag=false;
			}
		}
		
		//south east
		if(this.kWLoc[0]<7&&this.kWLoc[1]<7&&flag){
			moveV[0] = this.kWLoc[0]+1;
			moveV[1] = this.kWLoc[1]+1;
			
			while(this.board[moveV[0]][moveV[1]].equals(".")&&moveV[0]!=7&&moveV[1]!=7){
				moveV[0]=moveV[0]+1;
				moveV[1]=moveV[1]+1;
			}
			
			if(this.board[moveV[0]][moveV[1]].equals("q")
					||this.board[moveV[0]][moveV[1]].equals("b")){
				WhiteLoses();
				flag=false;
			}
		}
		
		//south west
		if(this.kWLoc[0]<7&&this.kWLoc[1]>0&&flag){
			moveV[0] = this.kWLoc[0]+1;
			moveV[1] = this.kWLoc[1]-1;
			
			while(this.board[moveV[0]][moveV[1]].equals(".")&&moveV[0]!=7&&moveV[1]!=0){
				moveV[0]=moveV[0]+1;
				moveV[1]=moveV[1]-1;
			}
			
			if(this.board[moveV[0]][moveV[1]].equals("q")
					||this.board[moveV[0]][moveV[1]].equals("b")){
				WhiteLoses();
				flag=false;
			}
		}
		
		//knights
		if(flag){
			int kMove_one[] = new int [2];
			int kMove_two[] = new int [2];
			int kMove_three[] = new int [2];
			int kMove_four[] = new int [2];
			int kMove_five[] = new int [2];
			int kMove_six[] = new int [2];
			int kMove_seven[] = new int [2];
			int kMove_eight[] = new int [2];
			
			//north
			kMove_one[0] = this.kWLoc[0]-2;
			kMove_one[1] = this.kWLoc[1]-1;
			
			kMove_five[0] = this.kWLoc[0]-2;
			kMove_five[1] = this.kWLoc[1]+1;
			
			//south
			kMove_two[0] = this.kWLoc[0]+2;
			kMove_two[1] = this.kWLoc[1]-1;
			
			kMove_six[0] = this.kWLoc[0]+2;
			kMove_six[1] = this.kWLoc[1]+1;
			
			//east
			kMove_three[0] = this.kWLoc[0]-1;
			kMove_three[1] = this.kWLoc[1]+2;
			
			kMove_seven[0] = this.kWLoc[0]+1;
			kMove_seven[1] = this.kWLoc[1]+2;
			
			//west
			kMove_four[0] = this.kWLoc[0]-1;
			kMove_four[1] = this.kWLoc[1]-2;
			
			kMove_eight[0] = this.kWLoc[0]+1;
			kMove_eight[1] = this.kWLoc[1]-2;
			
			if(kMove_one[0]>=0&&kMove_one[0]<=7&&kMove_one[1]>=0&&kMove_one[1]<=7){
				//check
				if(this.board[kMove_one[0]][kMove_one[1]].equals("n")){
					WhiteLoses();
					flag=false;
				}
			}
			if(kMove_two[0]>=0&&kMove_two[0]<=7&&kMove_two[1]>=0&&kMove_two[1]<=7){
				//check
				if(this.board[kMove_two[0]][kMove_two[1]].equals("n")){
					WhiteLoses();
					flag=false;
				}
			}
			if(kMove_three[0]>=0&&kMove_three[0]<=7&&kMove_three[1]>=0&&kMove_three[1]<=7){
				//check
				if(this.board[kMove_three[0]][kMove_three[1]].equals("n")){
					WhiteLoses();
					flag=false;
				}
			}
			if(kMove_four[0]>=0&&kMove_four[0]<=7&&kMove_four[1]>=0&&kMove_four[1]<=7){
				//check
				if(this.board[kMove_four[0]][kMove_four[1]].equals("n")){
					WhiteLoses();
					flag=false;
				}
			}
			if(kMove_five[0]>=0&&kMove_five[0]<=7&&kMove_five[1]>=0&&kMove_five[1]<=7){
				//check
				if(this.board[kMove_five[0]][kMove_five[1]].equals("n")){
					WhiteLoses();
					flag=false;
				}
			}
			if(kMove_six[0]>=0&&kMove_six[0]<=7&&kMove_six[1]>=0&&kMove_six[1]<=7){
				//check
				if(this.board[kMove_six[0]][kMove_six[1]].equals("n")){
					WhiteLoses();
					flag=false;
				}
			}
			if(kMove_seven[0]>=0&&kMove_seven[0]<=7&&kMove_seven[1]>=0&&kMove_seven[1]<=7){
				//check
				if(this.board[kMove_seven[0]][kMove_seven[1]].equals("n")){
					WhiteLoses();
					flag=false;
				}
			}
			if(kMove_eight[0]>=0&&kMove_eight[0]<=7&&kMove_eight[1]>=0&&kMove_eight[1]<=7){
				//check
				if(this.board[kMove_eight[0]][kMove_eight[1]].equals("n")){
					WhiteLoses();
					flag=false;
				}
			}
			
		}
		
		//pawn
		if(this.kWLoc[0]>0&&this.kWLoc[1]<7&&this.kWLoc[1]>0&&flag){
			int moveVV[] = new int [2];
			
			moveV[0] = this.kWLoc[0]-1;
			moveV[1] = this.kWLoc[1]+1;
			moveVV[0] = this.kWLoc[0]-1;
			moveVV[1] = this.kWLoc[1]-1;
			
			
			
			if(this.board[moveV[0]][moveV[1]].equals("p")){
				WhiteLoses();
				flag=false;
			}
			else if(this.board[moveVV[0]][moveVV[1]].equals("p")){
				WhiteLoses();
				flag=false;
			}
		}
		return flag;
	}
	
	//====================================================
	//Call when white loses
	private void WhiteLoses() {
		System.out.println("Game #"+this.gameNum+": white king is in check.");
	}
	
	//====================================================
	//call when black loses
	private void BlackLoses() {
		System.out.println("Game #"+this.gameNum+": black king is in check.");
	}
	
	//====================================================
	//print board
	public void print_Board(){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				System.out.print(this.board[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
}
