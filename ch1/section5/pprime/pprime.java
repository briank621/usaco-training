/*
ID: brian621
LANG: JAVA
TASK: pprime
*/

import java.util.*;
import java.io.*;

public class pprime{
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("pprime.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));

		String[] line = br.readLine().split(" ");
		int a = Integer.parseInt(line[0]);
		int b = Integer.parseInt(line[1]);

		ArrayList<Integer> check = generatePal();

		ArrayList<Integer> ans = new ArrayList<Integer>();
		for(int i : check){
			if(i < a || i > b)
				continue;
			if(prime(i))
				ans.add(i);
		}

		Collections.sort(ans);
		for(int i: ans){
			System.out.println(i);
			pw.println(i);
		}

		br.close();
		pw.close();
	}

	public static boolean prime(int x){
		if(x == 2)
			return true;
		for(int i = 2; i <= Math.sqrt(x); i++){
			if(x % i == 0)
				return false;
		}
		return true;
	}

	public static String reverse(String s){
		String out = "";
		for(int i = 1; i <= s.length(); i++)
			out += s.charAt(s.length() - i);
		return out;
	}

	public static int pal(int i, int even){
		String p = "" + i;
		if(even == 0)
			p += reverse(p);
		else
			p = p.substring(0, p.length() - 1) + reverse(p);
		return Integer.parseInt(p);
	}

	public static ArrayList<Integer> generatePal(){
		ArrayList<Integer> out = new ArrayList<Integer>();
		for(int i = 1; i < 10; i++){
			out.add(pal(i, 0));
			out.add(pal(i, 1));
		}
		for(int i = 10; i < 100; i++){
			out.add(pal(i, 0));
			out.add(pal(i, 1));
		}
		for(int i = 100; i < 1000; i++){
			out.add(pal(i, 0));
			out.add(pal(i, 1));
		}
		for(int i = 1000; i < 10000; i++){
			out.add(pal(i, 0));
			out.add(pal(i, 1));
		}
		return out;
	}

}