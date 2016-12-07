/*
ID: brian621
LANG: JAVA
TASK: milk2
*/

import java.util.*;
import java.io.*;

public class milk2{
	
	public static void main(String[] args) throws Exception{
		String l = args[0];
		BufferedReader br = new BufferedReader(new FileReader("milk2.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));

		int[] times = new int[1000000];
		int n = Integer.parseInt(br.readLine());
		int startMilk = 1000000;
		int endMilk = 0;


		for(int i = 0; i < n; i++){
			String[] line = br.readLine().split(" ");
			int beg = Integer.parseInt(line[0]);
			int end = Integer.parseInt(line[1]);
			for(int j = beg; j < end; j++)
				times[j] = 1;
			if(beg < startMilk)
				startMilk = beg;
			if(end > endMilk)
				endMilk = end;
		}

		int maxrun = 0;
		int maxrest = 0;
		int run = 0;
		int rest = 0;
		for(int i = 0; i < times.length; i++){
			if(times[i] == 1){
				run++;
				if(rest > maxrest)
					maxrest = rest;
				rest = 0;
			}
			else{
				if(i >= startMilk && i <= endMilk)
					rest++;
				if(run > maxrun)
					maxrun = run;
				run = 0;
			}
		}

		//System.out.println(Arrays.toString(times));

		pw.println(maxrun + " " + maxrest);
		System.out.println(maxrun + " " + maxrest);

		br.close();
		pw.close();
	}

}