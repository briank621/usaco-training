/*
ID: brian621
LANG: JAVA
TASK: barn1
*/

import java.util.*;
import java.io.*;

public class barn1{
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));

		String[] line = br.readLine().split(" ");
		int m = Integer.parseInt(line[0]);
		int s = Integer.parseInt(line[1]);
		int c = Integer.parseInt(line[2]);

		int last = -1;
		int first = Integer.MAX_VALUE;


		int[] stall = new int[s];
		for(int i = 0; i < c; i++){
			int cow = Integer.parseInt(br.readLine());
			stall[cow - 1] = 1;
			if(cow - 1 < first)
				first = cow - 1;
			if(cow - 1 > last)
				last = cow-1;
		}

		ArrayList<Integer> runs = new ArrayList<Integer>();
		ArrayList<Integer> eRuns = new ArrayList<Integer>();
		int run = 0;
		int erun = 0;
		for(int i = first; i <= last; i++){
			if(stall[i] == 1){
				run++;
				if(erun > 0){
					eRuns.add(erun);
					erun = 0;
				}
			}
			else{
				erun++;
				if(run > 0){
					runs.add(run);
					run = 0;
				}
			}
		}
		//add the last run
		runs.add(run);

		// System.out.println("runs: " + runs);
		// System.out.println("eruns: " + eRuns);

		int sum = 0;
		for(int i = 0; i < runs.size(); i++)
			sum += runs.get(i);
		if(runs.size() <= m){
			System.out.println(sum);
			pw.println(sum);
		}
		else{
			// System.out.println("sum: " + sum);
			int diff = runs.size() - m;
			// System.out.println("should add " + diff);
			Collections.sort(eRuns); 
			for(int i = 0; i < diff; i++){
				sum += eRuns.get(i);	
			}
			System.out.println(sum);
			pw.println(sum);
		}




		br.close();
		pw.close();
	}

}