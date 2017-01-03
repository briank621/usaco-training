/*
ID: brian621
LANG: JAVA
TASK: combo
*/

import java.util.*;
import java.io.*;

public class combo{
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("combo.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
		HashSet<Integer> p1 = new HashSet<Integer>();
		HashSet<Integer> p2 = new HashSet<Integer>();
		HashSet<Integer> p3 = new HashSet<Integer>();		
		HashSet<Integer> m1 = new HashSet<Integer>();
		HashSet<Integer> m2 = new HashSet<Integer>();
		HashSet<Integer> m3 = new HashSet<Integer>();


		int n = Integer.parseInt(br.readLine());
		int[] fj = new int[3];
		int[] master = new int[3];
		String[] first = br.readLine().split(" ");
		String[] second = br.readLine().split(" ");

		for(int i = 0; i < 3; i++){
			fj[i] = Integer.parseInt(first[i]);
			master[i] = Integer.parseInt(second[i]);
		}

		for(int i = -2; i <=2; i++){
			p1.add(1 + ((fj[0] + i + n) % n));
			m1.add(1 + ((master[0] + i + n) % n));			
			p2.add(1 + ((fj[1] + i + n) % n));
			m2.add(1 + ((master[1] + i + n) % n));			
			p3.add(1 + ((fj[2] + i + n) % n));
			m3.add(1 + ((master[2] + i + n) % n));
		}

		int total = p1.size() * p2.size() * p3.size();
		total += m1.size() * m2.size() * m3.size();

		//calculate intersection
		p1.retainAll(m1);
		p2.retainAll(m2);
		p3.retainAll(m3);

		total -= p1.size() * p2.size() * p3.size();

		System.out.println(total);
		pw.println(total);

		br.close();
		pw.close();
	}

}