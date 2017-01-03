/*
ID: brian621
LANG: JAVA
TASK: rockers
*/

import java.util.*;
import java.io.*;

public class rockers{
	
	static int[] songs;
	static int[][] dp;
	static int n, t, m;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("rockers.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("rockers.out")));

		String[] line = br.readLine().split(" ");
		n = Integer.parseInt(line[0]);
		t = Integer.parseInt(line[1]);
		m = Integer.parseInt(line[2]);

		songs = new int[n+1];

		line = br.readLine().split(" ");
		for(int i = 1; i <= n; i++)
			songs[i] = Integer.parseInt(line[i-1]);
	

		int ans = knapsack(1, new int[]{1, 0}, 0);

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

	public static int knapsack(int start, int[] cur, int count){
		if(start > n)
			return count;
		if(songs[start] > t)
			return count;
		int curCD = cur[0];
		int curMin = cur[1];
		int nextCD = curCD;
		int nextMin = curMin;
		int noInclude = knapsack(start + 1, new int[]{curCD, curMin}, count);
		if(nextCD > m)
			return noInclude;
		nextMin += songs[start];

		if(nextMin > t){
			nextCD++;
			nextMin = songs[start];
		}
		// System.out.println("nextMIN: " + nextMin);
		// System.out.println("nextCD: " + nextCD);
		// System.out.println("m: " + m);
		if(nextCD > m)
			return noInclude;
		int include = knapsack(start + 1, new int[]{nextCD, nextMin}, count+1);
		return Math.max(include, noInclude);
	}

}