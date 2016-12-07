/*
ID: brian621
LANG: JAVA
TASK: lamps
*/

import java.util.*;
import java.io.*;

public class lamps{
	
	static int n;
	static int c;
	static HashMap<Integer, Integer> fin = new HashMap<Integer, Integer>();
	static ArrayList<Integer> crit = new ArrayList<Integer>();
	static ArrayList<String> out = new ArrayList<String>();
	static HashSet<String> outNo = new HashSet<String>();


	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("lamps.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));

		n = Integer.parseInt(br.readLine());
		c = Integer.parseInt(br.readLine());
		c = Math.min(c, 8);
		
		int val = 1;
		for(int j = 0; j < 2; j++){	
			String[] line = br.readLine().split(" ");
			for(int i = 0; i < line.length - 1; i++){
				fin.put(Integer.parseInt(line[i]), val);
				crit.add(Integer.parseInt(line[i]));
			}
			val--;
		}


		for(int i = 0; i <= c ; i++){
			for(int j = 0; j <= c - i; j++){
				test:
				for(int k = 0; k <= c - i - j; k++){
					int m = c - i - j - k;
					// System.out.printf("testing: (%d, %d, %d, %d)\n", i, j, k, m);
					for(int l : crit){
						int start = flip(l, i, j, k, m);
						if(fin.get(l) != start)
							continue test;
					}
				//works
					// System.out.printf("works: (%d, %d, %d, %d)\n", i, j, k, m);
					String ans = "";
					for(int a = 1; a <= n; a++)
						ans += flip(a, i, j, k, m);
					outNo.add(ans);
				}
			}
		}

		// System.out.println("out: " + out);

		out = new ArrayList<String>(outNo);
		Collections.sort(out);

		for(String s: out){
			System.out.println(s);
			pw.println(s);
		}

		if(out.size() == 0){
			System.out.println("IMPOSSIBLE");
			pw.println("IMPOSSIBLE");
		}

		br.close();
		pw.close();
	}

	public static int flip(int l, int i, int j, int k, int m){
		int start = 1;
		start = bitFlip(start, m % 2);
		if(l % 2 == 0)
			start = bitFlip(start, i % 2);
		else
			start = bitFlip(start, j % 2);
		if(l % 3 == 1)
			start = bitFlip(start, k % 2);
		return start;
	}

	public static int bitFlip(int b, int n){
		for(int i = 0; i < n; i++)
			b = (b + 1) % 2;
		return b;
	}

}