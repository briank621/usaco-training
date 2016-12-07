/*
ID: brian621
LANG: JAVA
TASK: kimbits
*/

import java.util.*;
import java.io.*;

public class kimbits{

	static long[][] dp;
	static int n; 
	static int k; 
	static long v; 
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("kimbits.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("kimbits.out")));

		String[] line = br.readLine().split(" ");
		n = Integer.parseInt(line[0]);
		k = Integer.parseInt(line[1]);
		v = Long.parseLong(line[2]);

		dp = new long[k + 1][n + 1];
		fillDP();
		// print(dp);



		long total = v;
		int i = n - 1;
		String out = "";

		while(i >= 0){
			// System.out.println("total: " + total);
			if(total > dp[k][i]){
				out += 1;
				total -= dp[k][i];
				k--;
			}
			else
				out += 0;
			i--;
			// System.out.println("out: " + out);
		}


		System.out.println(out);
		pw.println(out);

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

	public static void fillDP(){
		for(int i = 0; i <= k; i++)
			dp[i][0] = 1;
		for(int i = 0; i <= n; i++)
			dp[0][i] = 1;
		for(int i = 1; i <= k; i++){
			// System.out.println("i: " + i);
			for(int j = 1; j <= n; j++)
				dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1];
		}
	}

}