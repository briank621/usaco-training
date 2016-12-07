/*
ID: brian621
LANG: JAVA
TASK: inflate
*/

import java.util.*;
import java.io.*;

public class inflate{
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		long[] max = new long[m + 1];

		for(int i = 0; i < n; i++){
			st = new StringTokenizer(br.readLine());
			int points = Integer.parseInt(st.nextToken());
			int min = Integer.parseInt(st.nextToken());
			for(int j = min; j <= m; j++)
				max[j] = Math.max(max[j], max[j - min] + points);
		}

		long curmax = 0;
		for(int i = 0; i <= m; i++){
			if(max[i] > curmax)
				curmax = max[i];
		}

		System.out.println(curmax);
		pw.println(curmax);

		br.close();
		pw.close();
	}

}