/*
ID: brian621
LANG: JAVA
TASK: ariprog
*/

import java.util.*;
import java.io.*;

public class ariprog{

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));

		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		HashSet<Integer> bi = new HashSet<Integer>();

		for(int i = 0; i <= m; i++){
			for(int j = 0; j <= m; j++)
				bi.add(i * i + j * j);
		}

		int maxSquare = 2 * m * m;
		boolean contains = false;

		//j represents start
		//i represents difference
		ArrayList<Integer> bSquares = new ArrayList<Integer>(bi);
		Collections.sort(bSquares);

		// System.out.println("bi: " + bSquares);
		// System.out.println("max: " + maxSquare);

		// System.out.println("size: " + bSquares.size());

		ArrayList<int[]> diffs = new ArrayList<int[]>();

		for(int i = 0; i < bSquares.size(); i++){
			int start = bSquares.get(i);
			for(int j = 1; j <= maxSquare / (n - 1); j++){
				int diff = j;
				for(int k = 1; k < n; k++){
					if(! bi.contains(start + k * diff))
						break;
					if(k == n - 1){
						// System.out.println("here");
						contains = true;
						int[] toAdd = new int[]{start, diff};
						diffs.add(toAdd);
					}
				}
			}
		}

		// System.out.println("here");

		Collections.sort(diffs, new Comparator<int[]>(){
			public int compare(int[] a, int[] b){
				if(a[1] == b[1])
					return a[0] - b[0];
				else
					return a[1] - b[1];
			}
		});

		for(int[] x : diffs){
			pw.println(x[0] + " " + x[1]);
			System.out.println(x[0] + " " + x[1]);
		}

		if(! contains){
			System.out.println("NONE");
			pw.println("NONE");
		}

		br.close();
		pw.close();
	}

}