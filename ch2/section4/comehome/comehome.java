/*
ID: brian621
LANG: JAVA
TASK: comehome
*/

import java.util.*;
import java.io.*;

public class comehome{
	
	static int n;
	static int[] dist = new int[52];
	static int[] p = new int[52];
	static int[][] w = new int[52][52];


	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("comehome.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));

		n = Integer.parseInt(br.readLine());
		for(int i = 0; i < 52; i++){
			for(int j = 0; j < 52; j++){
				w[i][j] = Integer.MAX_VALUE/2;
			}
		}

		for(int i = 0; i < n; i++){
			String[] line = br.readLine().split(" ");
			int source = convertInt(line[0].charAt(0));
			int end = convertInt(line[1].charAt(0));
			int d = Integer.parseInt(line[2]);
			if(w[source][end] == 0 || w[source][end] > d){
				w[source][end] = d;
				w[end][source] = d;
			}
		}

		// print(w);

		dist = djikstra(51); //'Z'
		// System.out.println("dist: " + Arrays.toString(dist));

		int min = Integer.MAX_VALUE;
		int index = -1;
		for(int i = 26; i < 51; i++){
			if(dist[i] < min){
				index = i;
				min = dist[i];
			}
		}

		char out = convertChar(index);
		System.out.println(out + " " + min);
		pw.println(out + " " + min);

		br.close();
		pw.close();
	}

	public static void print(int[][] d){
		for(int i = 0; i < d.length; i++){
			for(int j = 0; j < d[0].length; j++)
				System.out.printf("%d ", d[i][j]);
			System.out.println("");
		}
	}

	public static int[] djikstra(int source){
		ArrayList<Integer> set = new ArrayList<Integer>();

		int[] shortest = new int[52];
		int[] seen = new int[52];
		for(int i = 0; i < 52; i++)
			shortest[i] = Integer.MAX_VALUE/2;
		
		shortest[source] = 0;
		set.add(source);

		for(int i = 0; i < 52; i++){
			int index = -1;
			int min = Integer.MAX_VALUE;
			for(int j = 0; j < 52; j++){
				if(seen[j] == 1)
					continue;
				if(shortest[j] < min){
					min = shortest[j];
					index = j;
				}
			}

			// System.out.println("index: " + index);

			seen[index] = 1;
			for(int j = 0; j < 52; j++){
				if(seen[j] != 1){
					if(shortest[j] > shortest[index] + w[index][j])
						shortest[j] = shortest[index] + w[index][j];
				}
			}
		}

		return shortest;

	}

	public static int convertInt(char a){
		if(a >= 'a')
			return a - 'a';
		return a - 'A' + 26;
	}

	public static char convertChar(int i){
		// System.out.println("i : " + i);
		if(i < 26)
			return (char) ('a' + i);
		return (char)('A' + i - 26);
	}

}