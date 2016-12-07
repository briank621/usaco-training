/*
ID: brian621
LANG: JAVA
TASK: hamming
*/

import java.util.*;
import java.io.*;

public class hamming{
	
	static int n;
	static int b;
	static int d;
	static int max;
	static PrintWriter pw;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("hamming.in"));
		pw = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));

		String[] line = br.readLine().split(" ");

		n = Integer.parseInt(line[0]);
		b = Integer.parseInt(line[1]);
		d = Integer.parseInt(line[2]);

		max = (int) Math.pow(2, b);
		// System.out.println("MAX: " + max);
		// System.out.println("0 TO 7: " + Integer.bitCount(0 ^ 7));

		ArrayList<Integer> seen = new ArrayList<Integer>();
		seen.add(0);

		ham(seen, 0);

		br.close();
		pw.close();
	}

	public static void print(ArrayList<Integer> a){
		while(a.size() != 0){
			int stop = Math.min(10, a.size());
			for(int j = 0; j < stop; j++){
				if(j == stop - 1){
					System.out.print(a.get(0) + "");
					pw.print(a.get(0) + "");
				}
				else{
					System.out.print(a.get(0) + " ");
					pw.print(a.get(0) + " ");
				}
				a.remove(0);
			}
			System.out.println("");
			pw.println("");
		}
	}

	public static boolean check(ArrayList<Integer> seen, int check){
		for(Integer s: seen){
			if(Integer.bitCount(s ^ check) < d)
				return false;
		}
		return true;
	}

	public static boolean ham(ArrayList<Integer> seen, int cur){
		// System.out.println("SEEN SIZE: " + seen.size());
		// System.out.println("SEEN: " + seen);
		// System.out.println("CUR: " + cur);
		if(seen.size() == n){
			print(seen);
			return true;
		}
		if(cur >= max - 1){
			// System.out.println("yes: " + cur);
			// System.out.println("no: " + (max - 1));
			return false;
		}
		int c = cur + 1;
		if(check(seen, c)){
			seen.add(c);
			if(! ham(seen, c)){
				seen.remove(Integer.valueOf(c));
				return ham(seen, c);
			}
			else
				return true;
		}
		else
			return ham(seen, c);
	}

}