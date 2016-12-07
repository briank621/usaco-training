/*
ID: brian621
LANG: JAVA
TASK: fact4
*/

import java.util.*;
import java.io.*;

public class fact4{
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("fact4.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fact4.out")));

		int n = Integer.parseInt(br.readLine());
		
		int f = 1;
		for(int i = 2; i <= n; i++){
			// System.out.println((i - 1) + "\tf: " + f);
			f *= i;
			// if(i % 5 == 0){
				// int times = numFives(i);
				while(f % 10 == 0)
					f /= 10;	
			// }
			f = f % 10000;
		}

		System.out.println(f % 10);
		pw.println(f % 10);

		br.close();
		pw.close();
	}

	public static int numFives(int n){
		int f = 0;
		while(n % 5 == 0){
			f++;
			n /= 5;
		}
		return f;
	}

}