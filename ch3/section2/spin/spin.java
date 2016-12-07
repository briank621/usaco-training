/*
ID: brian621
LANG: JAVA
TASK: spin
*/

import java.util.*;
import java.io.*;

public class spin{

	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("spin.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("spin.out")));

		BitSet[] wheel = new BitSet[5];
		int[] s = new int[5];

		ArrayList<ArrayList<Integer>> wedges = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < 5; i++){
			wedges.add(new ArrayList<Integer>());
			wheel[i] = new BitSet(360);
		}

		for(int i = 0; i < 5; i++){
			String[] line = br.readLine().split(" ");
			s[i] = Integer.parseInt(line[0]);
			for(int j = 0; j < 2 * Integer.parseInt(line[1]); j++)
				wedges.get(i).add(Integer.parseInt(line[2 + j]));
		}

		int time = -1;

		for(int i = 0; i < 360; i++){
			// System.out.println("i:------------------------- " + i);
			BitSet first = wheel[0];
			for(int j = 0; j < 5; j++){
				int speed = s[j];
				BitSet bs = wheel[j];
				bs.clear();
				for(int k = 0; k < wedges.get(j).size()/2; k++){
					int start = wedges.get(j).get(2 * k);
					int end = wedges.get(j).get(2 * k + 1);
					// System.out.println("j: " + j);
					start = (start + (speed * i)) % 360;
					end = (start + end) % 360;
					// System.out.println("start: " + start + "\tend: " + end);
					if(end >= start)
						bs.set(start, end + 1);
					else{
						bs.set(start, 360);
						bs.set(0, end + 1);
					}
					// System.out.println("cur: " + bs);
				}
				first.and(bs);
				// System.out.println("first: " + first);
			}
			// System.out.println("first: " + first.cardinality());
			if(first.cardinality() > 0){
				time = i;
				break;
			}
		}

		if(time != -1){
			System.out.println(time);
			pw.println(time);
		}
		else{
			System.out.println("none");
			pw.println("none");
		}

		br.close();
		pw.close();
	}

}