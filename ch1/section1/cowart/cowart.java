/*
ID: brian621
LANG: JAVA
TASK: cowart
*/

import java.util.*;
import java.io.*;

public class cowart{
	
	static String[][] image;
	static int[][] seen;
	static int count = 0;
	static int n = 0;

	public static void markRegions(int i, int j, String color){
		if(i < 0 || j < 0 || i >= n || j >= n)
			return;
		if(! image[i][j].equals(color))
			return;
		if(seen[i][j] != 0)
			return;
		seen[i][j] = 1;
		markRegions(i, j-1, color);
		markRegions(i, j+1, color);
		markRegions(j, i+1, color);
		markRegions(j, i-1, color);
		return;
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("cowart.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowart.out")));

		n = Integer.parseInt(br.readLine());
		image = new String[n][n];
		seen = new int[n][n];

		for(int i = 0; i < n; i++){
			String line = br.readLine();
			for(int j = 0; j < n; j++){
				image[i][j] = line.substring(j,j+1);
				seen[i][j] = 0;
			}
		}

		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(seen[i][j] == 0){
					count++;
					markRegions(i, j, image[i][j]);
				}
			}
		}

		System.out.println(count);

		br.close();
		pw.close();
	}

}