/*
ID: brian621
LANG: JAVA
TASK: agrinet
*/

import java.util.*;
import java.io.*;

public class agrinet{
	
	static int[][] w;
	static int n;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));

		n = Integer.parseInt(br.readLine());
		w = new int[n][n];

		for(int i = 0; i < n; i++){
			int count = 0;
			while(count < n){
				StringTokenizer st = new StringTokenizer(br.readLine());
				while(st.hasMoreTokens()){
					w[i][count] = Integer.parseInt(st.nextToken());
					count++;
				}
			}
		}

		long cost = mst();

		System.out.println(cost);
		pw.println(cost);

		br.close();
		pw.close();
	}

	public static long mst(){
		long[] d = new long[n];
		boolean[] t = new boolean[n];
		int[] s = new int[n];

		for(int i = 0; i < n; i++){
			d[i] = Long.MAX_VALUE;
			t[i] = false;
			s[i] = -1;
		}

		int tsize = 1;
		int tcost = 0;
		t[0] = true;

		for(int i = 1; i < n; i++){
			d[i] = w[0][i];
			s[i] = 0;
		}

		while(tsize < n){
			long min = Long.MAX_VALUE;
			int minIndex = -1;
			for(int i = 0; i < n; i++){
				if(t[i])
					continue;
				if(d[i] < min){
					min = d[i];
					minIndex = i;
				}
			}
			tsize++;
			tcost += min;
			t[minIndex] = true;

			for(int i = 0; i < n; i++){
				if(i == minIndex)
					continue;
				if(t[i])
					continue;
				if(d[i] > w[minIndex][i]){
					d[i] = w[minIndex][i];
					s[i] = minIndex;
				}
			}
		}

		return tcost;
	}

}