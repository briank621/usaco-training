/*
ID: brian621
LANG: JAVA
TASK: fence6
*/

import java.util.*;
import java.io.*;

public class fence6{
	
	static int n;
	static int[] w;
	static int min = Integer.MAX_VALUE;
	static ArrayList<ArrayList<Integer>> e = new ArrayList<ArrayList<Integer>>();
	static HashSet<List<Integer>> index = new HashSet<List<Integer>>();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fence6.out")));

		n = Integer.parseInt(br.readLine());
		w = new int[n + 1];

		for(int i = 0; i < 2*n + 2; i++)
			e.add(new ArrayList<Integer>());

		for(int i = 0; i < n; i++){
			String[] line1 = br.readLine().split(" ");
			int seg = Integer.parseInt(line1[0]);
			int weight = Integer.parseInt(line1[1]);
			w[seg] = weight;
			int n1 = Integer.parseInt(line1[2]);
			int n2 = Integer.parseInt(line1[3]);
			String[] line2 = br.readLine().split(" ");
			for(int j = 0; j < n1; j++){
				int left = Integer.parseInt(line2[j]);
				List<Integer> toAdd = Arrays.asList(2 * seg, left);
				index.add(toAdd);
				e.get(2 * seg).add(left);
			}
			String[] line3 = br.readLine().split(" ");
			for(int j = 0; j < n2; j++){
				int right = Integer.parseInt(line3[j]);
				List<Integer> toAdd = Arrays.asList(2 * seg + 1, right);
				index.add(toAdd);
				e.get(2 * seg + 1).add(right);
			}
		}

		for(int i = 1; i <= n; i++){
			int p1 = bfs(i, 0);
			int p2 = bfs(i, 1);
			min = Math.min(Math.min(p1, p2), min);
		}

		System.out.println(min);
		pw.println(min);

		br.close();
		pw.close();
	}

	public static int bfs(int start, int s){

		ArrayList<HashSet<Integer>> seen = new ArrayList<HashSet<Integer>>();
		Queue<List<Integer>> q = new LinkedList<List<Integer>>();

		seen.add(new HashSet<Integer>());

		//current, side, parent, distance, seenIndex
		q.offer(Arrays.asList(start, s, -1, 0, 0));

		while(q.size() > 0){
			List<Integer> cur = q.poll();
			int v = cur.get(0);
			int side = cur.get(1);
			int p = cur.get(2);
			int d = cur.get(3);
			if(d >= min)
				continue;
			int seenIndex = cur.get(4);

			if(p != -1){
				//cross the fence
				if(v == start){
					d = (s == side) ? d : d + w[start];
					min = Math.min(d, min);
					continue;
				}
				if(seen.get(seenIndex).contains(2*v+side))
					continue;
				seen.get(seenIndex).add(2*v+side);
				d += w[v];
				side = (side == 0) ? 1 : 0;

				//check if start is connected
				List<Integer> toCheck = Arrays.asList(v*2+side, start);
				if(index.contains(toCheck)){
					q.offer(Arrays.asList(start, sides(start, v), v*2+side, d, seenIndex));
					continue;
				}
			}
				//add all neighbors
			int count = seenIndex+1;
			for (int neighbor : e.get(v*2 + side)) {
				HashSet<Integer> clone = new HashSet<Integer>(seen.get(seenIndex));
				seen.add(clone);
				q.offer(Arrays.asList(neighbor, sides(neighbor, v), v*2+side, d, count++));
			}

		}
		return min;
	}

	public static int sides(int start, int end){
		//if end is to the left return 0, else return 1
		if(index.contains(Arrays.asList(start * 2, end)))
			return 0;
		else
			return 1;
	}

}