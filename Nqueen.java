import java.util.*;
public class Nqueen{
	public static void solveNqueen(int n){
		int board[][] = new int[n][n];
		solveNqueenutil(board,0,n);
	}
	public static void solveNqueenutil(int board[][],int col,int n){
		if(col>=n){
			printSolution(board);
			System.out.println();	
			return;
		}
		for(int i=0;i<n;i++){
			if(isSafe(board,i,col,n)){
				board[i][col] = 1;
				solveNqueenutil(board,col+1,n);
				board[i][col] = 0;
			}		
		}
	}
	public static boolean isSafe(int board[][],int row,int col,int n){
		int i,j;
		for(i=0;i<col;i++){
			if(board[row][i]==1)
				return false;
		}
		
		for(i=row,j=col;i>=0&&j>=0;i--,j--){
			if(board[i][j]==1)
				return false;
		}
		for(i=row,j=col;i<n&&j>=0;i++,j--){
			if(board[i][j]==1)
				return false;
		}
		return true;
	}
	
	public static void printSolution(int board[][]){
		for(int row[]:board){
			for(int cell:row)
				System.out.print(cell==1?"Q ":". ");
			System.out.println();	
		}
	
	}
	public static void main(String []args){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the size of board:");
		int n = sc.nextInt();
		solveNqueen(n);
	}
} 
