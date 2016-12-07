/*
ID: brian621
LANG: JAVA
TASK: transform
*/

import java.util.*;
import java.io.*;

public class transform{
	static int n;


	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("transform.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));

		n = Integer.parseInt(br.readLine().trim());
		String[][] in = new String[n][n];
		String[][] out = new String[n][n];
		for(int i = 0; i < n; i++){
			String s = br.readLine().trim();
			for(int j = 0; j < n; j++)
				in[i][j] = Character.toString(s.charAt(j));
		}
		for(int i = 0; i < n; i++){
			String s = br.readLine().trim();
			for(int j = 0; j < n; j++)
				out[i][j] = Character.toString(s.charAt(j));
		}
		String[][] rot = in;
		boolean done = false;
		for(int i = 1; i <= 3; i++){
			rot = rotate90(rot);
			if(equals(rot, out)){
				System.out.println(i);
				done = true;
				break;
			}
		}

		if(! done){
			rot = rotate90(rot);
			if(equals(rot, out)){
				System.out.println("6");
				done = true;
			}
			rot = reflect(rot);
			if(equals(rot, out)){
				System.out.println("4");
				done = true;
			}
			else{
				for(int i = 1; i <= 4; i++){
					rot = rotate90(rot);
					if(equals(rot, out)){
						System.out.println("5");
						done = true;
						break;
					}
				}
			}
		}
		if(! done)
			System.out.println("7");

		br.close();
		pw.close();
	}

	public static boolean equals(String[][] a, String[][] b){
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				// System.out.printf("%d,%d\n", i, j);
				if(! a[i][j].equals(b[i][j])){
					// System.out.println("stop");
					return false;
				}
			}
		}
		return true;
	}

	public static void print(String[][] a){
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				// System.out.print(a[i][j]);
			}
			// System.out.println("");
		}
	}

	public static String[][] rotate90(String[][] a){
		String[][] out = new String[n][n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				out[i][j] = a[j][i];
			}
		}
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n/2; j++){
				String temp = out[i][j];
				out[i][j] = out[i][n-1-j];
				out[i][n-1-j] = temp;
			}
		}
		return out;
	}

	public static String[][] reflect(String[][] a){
		String[][] out = new String[n][n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				out[i][j] = a[i][n-1-j];
			}
		}
		return out;
	}

}