/*
ID: brian621
LANG: JAVA
TASK: money
*/

import java.util.*;
import java.io.*;

public class money{
	static HashSet<Integer> h = new HashSet<Integer>();
	static ArrayList<Integer> val = new ArrayList<Integer>();
	static long[][] a;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("money.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));

		String[] line = br.readLine().split(" ");
		int v = Integer.parseInt(line[0]);
		int n = Integer.parseInt(line[1]);

		String l;
		while((l = br.readLine()) != null){
			line = l.split(" ");
			for(int i = 0; i < line.length; i++)
				h.add(Integer.parseInt(line[i]));	
		}
		
		val = new ArrayList<Integer>(h);
		v = val.size();
		Collections.sort(val);

		// System.out.println("v: " + v);

		a = new long[n+1][v];

		for(int i = 0; i < v; i++){
			a[0][i] = 1;
		}

		for(int i = 1; i <= n; i++){
			int value = val.get(0);
			if(i % value == 0)
				a[i][0] = 1;
			for(int j = 1; j < v; j++){
				value = val.get(j);
				if(i / value > 0)
					a[i][j] = a[i][j-1] + a[i - value][j];
				else
					a[i][j] = a[i][j-1];
			}
		}

		// print(a);

		long total = a[n][v-1];
		// for(int i = 0; i < v; i++)
			// total += a[n][i];

		System.out.println(total);
		pw.println(total);

		br.close();
		pw.close();
	}

	public static void print(long[][] x){
		for(int i = 0; i < x.length; i++){
			for(int j = 0; j < x[0].length; j++){
				System.out.print(x[i][j] + " ");
			}
			System.out.println("");
		}
	}

}