/*
ID: brian621
LANG: JAVA
TASK: humble
*/

import java.util.*;
import java.io.*;

public class humble{
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("humble.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));

		ArrayList<Long> h = new ArrayList<Long>();
		ArrayList<Long> s = new ArrayList<Long>();
		ArrayList<Integer> m = new ArrayList<Integer>();

		String[] line = br.readLine().split(" ");
		int k = Integer.parseInt(line[0]);
		int n = Integer.parseInt(line[1]);
		PriorityQueue<Long> q = new PriorityQueue<Long>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < k; i++){
			long toAdd = Long.parseLong(st.nextToken());
			s.add(toAdd);
			m.add(0);
		}

		h.add(1l);
		HashSet<Long> seen = new HashSet<Long>();

		for(int i = 0; i < n; i++){
			//find Min
			long min = Long.MAX_VALUE;
			int minIndex = -1;
			for(int j = 0; j < k; j++){
				long v = h.get(m.get(j)) * s.get(j);
				// System.out.println("v: " + v);
				if(! seen.contains(v) && v < min){
					min = v;
					minIndex = j;
				}
			}
			// seen.add(min);
			for(int j = 0; j < k; j++){
				long v = h.get(m.get(j)) * s.get(j);
				// System.out.println("v: " + v);
				if(v == min)
					m.set(j, m.get(j) + 1);
			}
			h.add(min);
			// System.out.println("min: " + min);
		}

		System.out.println(h.get(n));
		pw.println(h.get(n));

		br.close();
		pw.close();
	}

}