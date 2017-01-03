/*
ID: brian621
LANG: JAVA
TASK: skidesign
*/

import java.util.*;
import java.io.*;

public class skidesign{
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("skidesign.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));

		int n = Integer.parseInt(br.readLine());
		int[] h = new int[n];
		for(int i = 0; i < n; i++)
			h[i] = Integer.parseInt(br.readLine());

		int max = -1;
		int min = Integer.MAX_VALUE;
		int minI = -1;
		int maxI = -1;

		for(int i = 0; i < n; i++){
			if(min > h[i]){
				min = h[i];
				minI = i;
			}
			if(max < h[i]){
				max = h[i];
				maxI = i;
			}
		}

		int diff = max - min;
		diff -= 17;
		// System.out.println("diff: " + diff);
		int minTotal = Integer.MAX_VALUE;
		if(diff <= 0){
			System.out.println("0");
			pw.println("0");
		}
		else{
			for(int j = 0; j <= diff; j++){
				int total = 0;
				int newMin = h[minI] + (diff - j);
				int newMax = h[maxI] - j;

				for(int i = 0; i < n; i++){
					if(h[i] < newMin)
						total += Math.pow((newMin - h[i]), 2);
					else if(h[i] > newMax)
						total += Math.pow((h[i] - newMax), 2);
				}

				if(total < minTotal)
					minTotal = total;
			}
			System.out.println(minTotal);
			pw.println(minTotal);
		}



		br.close();
		pw.close();
	}

}