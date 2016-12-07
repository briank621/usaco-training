/*
ID: brian621
LANG: JAVA
TASK: subset
*/

import java.util.*;
import java.io.*;

public class subset{
	
	static long[][] x;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("subset.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));

		int n = Integer.parseInt(br.readLine());
		int goal = (n * (n + 1))/4;
		// System.out.println("goal: " + goal);
		if(n % 4 != 0 && n % 4 != 3){
			System.out.println("0");
			pw.println("0");
			pw.close();
			return;
		}

		x = new long[n + 1][goal + 1];
		x[0][0] = 1;
		for(int i = 1; i <= n; i++){
			for(int j = 0; j <= goal; j++){
				if(x[i-1][j] != 0){
					int newSum = i + j;
					if(newSum <= goal)
						x[i][newSum] += x[i-1][j];
					x[i][j] += x[i-1][j];
				}
			}
		}

		// print(x);

		System.out.println(x[n][goal]/2);
		pw.println(x[n][goal]/2);


		br.close();
		pw.close();
	}

	public static void print(int[][] x){
		for(int i = 0; i < x.length; i++){
			for(int j = 0; j < x[0].length; j++)
				System.out.print(x[i][j] + " ");
			System.out.println("");
		}
	}

}