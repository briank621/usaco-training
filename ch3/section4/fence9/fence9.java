/*
ID: brian621
LANG: JAVA
TASK: fence9
*/

import java.util.*;
import java.io.*;

public class fence9{
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("fence9.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fence9.out")));

		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]);
		int p = Integer.parseInt(line[2]);

		int ux = n;
		int uy = m;
		int vx = p;
		int vy = 0;

		int cp= ux*vy - uy*vx;

		int area = Math.abs(cp)/2;

		// System.out.println("area: " + area);

		int num = 0;
		// System.out.println("gcd: " + gcd(m, n));
		// System.out.println("gcd: " + gcd(m, Math.abs(n-p)));
		num += gcd(m, n);
		num += gcd(m, Math.abs(n-p));
		num += p;

		num /= 2;

		int ans = area - num + 1;

		System.out.println(ans);
		pw.println(ans);

		br.close();
		pw.close();
	}

	public static int gcd(int a, int b){
		if(b == 0)
			return a;
		return gcd(b, a%b);
	}

}