/*
ID: brian621
LANG: JAVA
TASK: rockers
*/

import java.util.*;
import java.io.*;

public class rockersDP{
	
	static int[] songs;
	static int[][][] dp;
	static int n, t, m;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("1.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("rockers.out")));

		String[] line = br.readLine().split(" ");
		n = Integer.parseInt(line[0]);
		t = Integer.parseInt(line[1]);
		m = Integer.parseInt(line[2]);

		songs = new int[n+1];

		line = br.readLine().split(" ");
		for(int i = 1; i <= n; i++)
			songs[i] = Integer.parseInt(line[i-1]);

		dp = new int[25][25][25];

		int ans = calculateDP();

		System.out.println(ans);
		pw.println(ans);

		br.close();
		pw.close();
	}

	public static void print(int[][] a){
		for(int i = 0; i < a.length; i++){
			for(int j = 0; j < a[0].length; j++)
				System.out.print(a[i][j] + " ");
			System.out.println("");
		}
	}

	public static int calculateDP(){
		int max = 0;
		for(int i = 0; i < m; i++){
			for(int j = 0; j <= t; j++){
				for(int k = 0; k <= n; k++){
					for(int l = k+1; l <= n; l++){
						// System.out.println("l: " + l);
						if(j + songs[l] <= t)
							dp[i][j+songs[l]][l] = Math.max(dp[i][j][k]+1, dp[i][j+songs[l]][l]);
						else{
							dp[i+1][songs[l]][l] = Math.max(dp[i][j][k]+1, dp[i+1][songs[l]][l]);
						}
					}
					max = Math.max(dp[i][j][k], max);
				}
			}
		}
		return max;
	}

}