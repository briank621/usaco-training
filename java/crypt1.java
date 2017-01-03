/*
ID: brian621
LANG: JAVA
TASK: crypt1
*/

import java.util.*;
import java.io.*;

public class crypt1{
	
	public static int[] dig;
	public static HashSet<Integer> d = new HashSet<Integer>();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("crypt1.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));

		int n = Integer.parseInt(br.readLine());
		dig = new int[n];
		String[] line = br.readLine().split(" ");

		for(int i = 0; i < n; i++){
			dig[i] = Integer.parseInt(line[i]);
			d.add(Integer.parseInt(line[i]));
		}	

		int total = 0;

		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				for(int k = 0; k < n; k++){
					int top = 100*dig[i] + 10*dig[j] + dig[k];
					for(int l = 0; l < n; l++){
						for(int m = 0; m < n; m++){
							int bot = 10*dig[l] + dig[m];
							if(valid(top, bot)){
								// System.out.printf("valid: %d, %d\n", top, bot);
								total++;
							}
						}
					}
				}
			}
		}

		System.out.println(total);
		pw.println(total);

		br.close();
		pw.close();
	}

	public static boolean valid(int t, int b){
		int p1 = t * (b % 10);
		int p2 = t * (b / 10);
		int p = t * b;
		return checkDig(p1, 3) && checkDig(p2, 3) && checkDig(p, 4);
	}

	public static boolean checkDig(int n, int g){
		int numDig = 0;
		while(n > 0){
			if(! d.contains(n % 10))
				return false;
			numDig++;
			n /= 10;
		}
		return numDig == g;
	}

}




