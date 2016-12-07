/*
ID: brian621
LANG: JAVA
TASK: nocows
*/

import java.util.*;
import java.io.*;

public class nocows{
	
	static int[][] nk;
	static int n;
	static int k;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("nocows.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));

		String[] line = br.readLine().split(" ");
		n = Integer.parseInt(line[0]);
		k = Integer.parseInt(line[1]);

		if(n%2 == 0){
			System.out.println(0);
			pw.println(0);
			pw.close();
		}

		nk = new int[n + 1][k + 1];
		nk[1][1] = 1;
		for(int i = 0; i < k+1; i++){
			nk[0][i] = 1;
		}
		
		int x = (n - 1)/2;

		for(int i = 2; i <= k; i++){
			for(int j = 1; j <= x; j++){
				int total = 0;
				for(int l = 0; l < j; l++){
					total += (nk[l][i-1] * nk[j - 1 - l][i-1]) % 9901; 
				}
				nk[j][i] = total % 9901;
			}
		}

		// print(nk);

		int ans = nk[x][k-1] - nk[x][k-2];
		ans = (ans + 9901) % 9901;

		System.out.println(ans);
		pw.println(ans);

		br.close();
		pw.close();
	}

	public static void print(int[][] x){
		for(int j = 0; j < k + 1; j++){
			for(int i = 0; i < n + 1; i++)
				System.out.print(x[i][j] + " ");
			System.out.println("");
		}
	}

}