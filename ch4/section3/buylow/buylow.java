/*
ID: brian621
LANG: JAVA
TASK: buylow
*/

import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class buylow{
	
	static int n;
	static int[] val;
	static int[] dp;
	static ArrayList<ArrayList<Integer>> prev;
	static BigInteger[] q;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("buylow.out")));

		n = Integer.parseInt(br.readLine().trim());
		val = new int[n];

		String line;
		int index = 0;
		while((line = br.readLine()) != null){
			StringTokenizer st = new StringTokenizer(line.trim());
			while(st.hasMoreTokens())
				val[index++] = Integer.parseInt(st.nextToken());
		}

		// System.out.println(Arrays.toString(val));

		compute();

		int max = 0;
		for(int i = 0; i < n; i++)
			max = Math.max(max, dp[i]);

		BigInteger freq = BigInteger.valueOf(0);

		for(int i = 0; i < n; i++){
			if(dp[i] == max)
				freq = freq.add(q[i]);
		}

		// for(int i = 0; i < n; i++){
		// 	System.out.print(Arrays.toString(dp[i]) + " ");
		// }
		// System.out.println("");
		// System.out.println(Arrays.toString(dp));

		System.out.println(max + " " + freq);
		pw.println(max + " " + freq);

		br.close();
		pw.close();
	}

	public static void compute(){
		dp = new int[n];
		q = new BigInteger[n];
		// prev = new ArrayList<HashSet<Integer>>(n);
		outer:
		for(int i = n - 1; i >= 0; i--){
			int amt = 0;
			BigInteger quant = BigInteger.valueOf(1);
			for(int j = i+1; j < n; j++){
				// System.out.println("val: " + val[j]);
				if(val[j] < val[i] && dp[j] != 0){
					if(amt == dp[j])
						quant = quant.add(q[j]);
					else if(amt < dp[j] ){
						amt = dp[j];
						quant = q[j];
						// prev.get(i).clear();
					}
					// prev.get(i).add(j);
				}
			}
			dp[i] = amt + 1;
			q[i] = quant;

			for(int j = i + 1; j < n; j++){
				if(val[j] == val[i]){
					dp[j] = 0;
					q[j] = BigInteger.valueOf(0);
				}
			}
		}
	}

}