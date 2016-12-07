/*
ID: brian621
LANG: JAVA
TASK: milk
*/

import java.util.*;
import java.io.*;

public class milk{
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));


		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int m = Integer.parseInt(line[1]);
		
		bottle[] b = new bottle[m];

		for(int i = 0; i < m; i++){
			line = br.readLine().split(" ");
			bottle toAdd = new bottle(Integer.parseInt(line[1]), Integer.parseInt(line[0]));
			b[i] = toAdd;
		}

		Arrays.sort(b);
		// System.out.println(Arrays.toString(b));

		int total = 0;
		int i = 0;
		while(n > 0){
			// System.out.println("n: " + n);
			int deduct = Math.min(n, b[i].milk);
			total += deduct * b[i].cost;
			n -= deduct;
			i++;
		}

		System.out.println(total);
		pw.println(total);

		br.close();
		pw.close();
	}

	public static class bottle implements Comparable{

		int milk;
		int cost;

		public bottle(int m, int c){
			milk = m;
			cost = c;
		}

		public int compareTo(Object o){
			return this.cost - ((bottle)o).cost;
		}

		public String toString(){
			return "Milk: " + milk + "\tCost: " + cost;
		}

	}

}