/*
ID: brian621
LANG: JAVA
TASK: frac1
*/

import java.util.*;
import java.io.*;

public class frac1{
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));

		int n = Integer.parseInt(br.readLine());

		ArrayList<Fraction> f = new ArrayList<Fraction>();
		for(int i = 1; i <= n; i++){
			for(int j = i+1; j <=n; j++){
				if(gcd(i,j) == 1)
					f.add(new Fraction(i, j));
			}
		}

		f.add(new Fraction(0, 1));
		f.add(new Fraction(1, 1));

		Collections.sort(f, new Comparator<Fraction>() {
			public int compare(Fraction c1, Fraction c2){
				return c1.n*c2.d - c2.n*c1.d;
			}
		});


		for(Fraction fr: f){
			System.out.println(fr);
			pw.println(fr);
		}

		br.close();
		pw.close();
	}

	public static int gcd(int a, int b){
		if(b > a)
			return gcd(b, a);
		if(a % b == 0)
			return b;
		return gcd(a-b, b);
	}

	public static class Fraction{
		int n;
		int d;

		public Fraction(int n, int d){
			this.n = n;
			this.d = d;
		}

		public String toString(){
			String ans = "";
			ans += String.format("%d/%d", this.n, this.d);
			return ans;
		}

	}

}