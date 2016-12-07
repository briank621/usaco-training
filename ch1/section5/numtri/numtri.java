/*
ID: brian621
LANG: JAVA
TASK: numtri
*/

import java.util.*;
import java.io.*;

public class numtri{
	
	static int n;
	static int[][] data;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("numtri.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));

		n = Integer.parseInt(br.readLine());
		data = new int[n][n];

		for(int i = 0; i < n; i++){
			String[] line = br.readLine().split(" ");
			for(int j = 0; j <= i; j++)
				data[i][j] = Integer.parseInt(line[j]);
		}

		int val = calcMax();

		System.out.println(val);
		pw.println(val);

		br.close();
		pw.close();
	}

	public static int calcMax(){
		for(int i = n-2; i >= 0; i--){
			for(int j = 0; j <= i; j++)
				data[i][j] += Math.max(data[i+1][j], data[i+1][j+1]);
		}
		// printA();

		return data[0][0];
	}

	public static void printA(){
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				System.out.print(data[i][j] + " ");
			}
			System.out.println("");
		}
	}

}