/*
ID: brian621
LANG: JAVA
TASK: game1
*/

import java.util.*;
import java.io.*;

public class game1{
	
	static int[] board;
	static int[][] win;
	static int n;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("game1.out")));

		n = Integer.parseInt(br.readLine());
		board = new int[n];
		win = new int[n + 1][n];

		int count = 0;
		String line;
		while((line = br.readLine()) != null){
			StringTokenizer st = new StringTokenizer(line);
			while(st.hasMoreTokens())
				board[count++] = Integer.parseInt(st.nextToken());
		}

		// System.out.println(Arrays.toString(board));
		dp();
		// print(win);
		int maxFirst = win[n][0];
		int maxSecond = sum(board, 0, board.length) - maxFirst;

		System.out.println(maxFirst + " " + maxSecond);
		pw.println(maxFirst + " " + maxSecond);

		br.close();
		pw.close();
	}

	public static int sum(int[] b, int start, int end){
		int out = 0;
		for(int i = start; i < end; i++)
			out += b[i];
		return out;
	}

	public static void dp(){
		for(int i = 0; i < n; i++)
			win[1][i] = board[i];
		
		for(int i = 2; i <= n; i++){
			for(int j = 0; j <= n - i; j++){
				//if the player takes the leftmost piece
				int first = board[j] + sum(board, j + 1, j + i) - win[i-1][j + 1];
				//player takes rightmost piece
				int second = board[j + i - 1] + sum(board,j,j+i-1) - win[i-1][j];
				win[i][j] = Math.max(first, second);
			}
		}

	}

}