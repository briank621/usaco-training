/*
ID: brian621
LANG: JAVA
TASK: stamps
*/

import java.util.*;
import java.io.*;

public class stamps{
	
	static final int SIZE = 2000000;
	static int n, k;
	static ArrayList<Integer> val = new ArrayList<Integer>();
	static boolean[] d = new boolean[SIZE + 1]; 
	static int[] a = new int[SIZE + 1];

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("stamps.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));

		String[] line = br.readLine().split(" ");
		k = Integer.parseInt(line[0]);
		n = Integer.parseInt(line[1]);

		String l;
		while((l = br.readLine()) != null){
			StringTokenizer st = new StringTokenizer(l);
			while(st.hasMoreTokens())
				val.add(Integer.parseInt(st.nextToken()));
		}

		Collections.sort(val);
		fill();
		// System.out.println("d: " + Arrays.toString(d));
		// System.out.println("a: " + Arrays.toString(a));
		int out = countRuns();

		System.out.println(out);
		pw.println(out);

		br.close();
		pw.close();
	}

	public static int countRuns(){
		int count = 0;
		for(int i = 1; i <= SIZE; i++){
			if(a[i] == 0 || a[i] > k){
				// System.out.println("i: : " + i);
				// System.out.println("i: " + a[i] + "\ti + 1: " + a[i + 1]);
				return count;
			}
			count++;
		}
		return count;
	}

	public static void fill(){
		for(int i = 0; i < n; i++){
			int v = val.get(i);
			d[v] = true;
			a[v] = 1;
			for(int j = v + 1; j <= SIZE; j++){
				d[j] |= d[j - v];
				if(d[j]){
					if(a[j] == 0)
						a[j] = a[j - v] + 1;
					if(a[j - v] != 0)
						a[j] = Math.min(a[j - v] + 1, a[j]);
				}
			}
		}
	}

}