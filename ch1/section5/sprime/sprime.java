/*
ID: brian621
LANG: JAVA
TASK: sprime
*/

import java.util.*;
import java.io.*;

public class sprime{
	
	static int[] single = {2, 3, 5, 7};
	static int[] rest = {1, 3, 5, 7, 9};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("sprime.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));

		int n = Integer.parseInt(br.readLine());
		
		ArrayList<Integer> pos = new ArrayList<Integer>();
		for (int e : single) 
			pos.add(e);
		while(n - 1 > 0){
			ArrayList<Integer> add = new ArrayList<Integer>();
			while(pos.size() != 0){
				String s = "" + pos.remove(0);
				for (int r : rest) 
					add.add(Integer.parseInt(s + r));
			}
			while(add.size() != 0){
				int e = add.remove(0);
				if(isPrime(e)){
					pos.add(e);
					// System.out.println("PRIME: " + e);
				}
			}
			n--;
		}

		Collections.sort(pos);
		for (int e: pos) {
			System.out.println(e);
			pw.println(e);
		}

		br.close();
		pw.close();
	}

	public static boolean isPrime(int n){
		if(n == 2)
			return true;
		for(int i = 2; i <= Math.sqrt(n); i++){
			if(n % i == 0)
				return false;
		}
		return true;
	}

}