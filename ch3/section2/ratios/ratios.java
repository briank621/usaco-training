/*
ID: brian621
LANG: JAVA
TASK: ratios
*/

import java.util.*;
import java.io.*;

public class ratios{
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("ratios.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ratios.out")));

		int[] goal = new int[3];
		int[][] feeds = new int[3][3];

		String[] line = br.readLine().split(" ");
		for(int i = 0; i < 3; i++)
			goal[i] = Integer.parseInt(line[i]);

		for(int i = 0; i < 3; i++){
			line = br.readLine().split(" ");
			for(int j = 0; j < 3; j++)
				feeds[i][j] = Integer.parseInt(line[j]);
		}

		int leftGCD = gcd(goal[0], goal[1]);
		int rightGCD = gcd(goal[1], goal[2]);
		int finalGCD = gcd(leftGCD, rightGCD);
		goal[0] /= finalGCD;
		goal[1] /= finalGCD;
		goal[2] /= finalGCD;
		int mult = finalGCD;

		// System.out.println("goals: " + Arrays.toString(goal));
		// print(feeds);

		int min = 301;
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;

		for(int i = 0; i < 100; i++){
			int first = feeds[0][0] * i;
			int second = feeds[0][1] * i;
			int third = feeds[0][2] * i;
			for(int j = 0; j < 100; j++){
				int first2 = first + feeds[1][0] * j;
				int second2 = second + feeds[1][1] * j;
				int third2 = third + feeds[1][2] * j;
				for(int k = 0; k < 100; k++){
					int first3 = first2 + feeds[2][0] * k;
					int second3 = second2 + feeds[2][1] * k;
					int third3 = third2 + feeds[2][2] * k;
					leftGCD = gcd(first3, second3);
					rightGCD = gcd(second3, third3);
					finalGCD = gcd(leftGCD, rightGCD);
					if(finalGCD == 0){
						continue;
					}
					first3 /= finalGCD;
					second3 /= finalGCD;
					third3 /= finalGCD;
					// System.out.printf("%d %d %d\n", i, j ,k);
					// System.out.printf("%d\t%d\t%d\n", first3, second3, third3);
					if(first3 == goal[0] && second3 == goal[1] && third3 == goal[2]){
						if(i + j + k < min){
							if(finalGCD < mult)
								continue;
							min = i + j + k;
							a = i;
							b = j;
							c = k;
							d = finalGCD - mult + 1;
						}
					}
				}
			}
		}

		if(min == 301){
			System.out.println("NONE");
			pw.println("NONE");
		}
		else{
			System.out.printf("%d %d %d %d\n", a, b, c, d);
			pw.printf("%d %d %d %d\n", a, b, c, d);
		}

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

	public static int gcd(int a, int b){
		if(a == 0)
			return b;
		return gcd(b % a, a);
	}

}