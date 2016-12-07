/*
ID: brian621
LANG: JAVA
TASK: fence
*/

import java.util.*;
import java.io.*;

public class fence{
	
	static ArrayList<ArrayList<Integer>> e = new ArrayList<ArrayList<Integer>>();
	static ArrayList<Integer> c = new ArrayList<Integer>();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fence.out")));

		int n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < 501; i++)
			e.add(new ArrayList<Integer>());

		for(int i = 0; i < n; i++){
			String[] line = br.readLine().split(" ");
			int u = Integer.parseInt(line[0]);
			int v = Integer.parseInt(line[1]);
			addEdge(u, v);
		}

		int start = 1;
		for(int i = 1; i < 501; i++){
			if(e.get(i).size() % 2 == 1){
				start = i;
				break;
			}
		}

		// System.out.println("start: " + start);
		findCircuit(start);
		Collections.reverse(c);

		StringBuilder out = new StringBuilder();
		for(int i = 0; i < c.size(); i++)
			out.append(c.get(i) + "\n");

		String s = out.substring(0, out.length() - 1);
		System.out.println(s);
		pw.println(s);

		br.close();
		pw.close();
	}

	public static void findCircuit(int i){
		if(e.get(i).size() == 0){
			c.add(i);
			return;
		}
		ArrayList<Integer> cur = e.get(i);
		while(e.get(i).size() != 0){
			int j = Collections.min(cur);
			deleteEdge(i, j);
			findCircuit(j);
		}
		c.add(i);
	}

	public static void deleteEdge(int u, int v){
		e.get(u).remove(Integer.valueOf(v));
		e.get(v).remove(Integer.valueOf(u));
	}

	public static void addEdge(int u, int v){
		e.get(u).add(v);
		e.get(v).add(u);
	}

}