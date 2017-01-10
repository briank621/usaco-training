/*
ID: brian621
LANG: JAVA
TASK: milk6
*/

import java.util.*;
import java.io.*;

public class milk6{
	
	static int n, m;
	static ArrayList<List<Integer>> edges = new ArrayList<List<Integer>>();
	static ArrayList<List<Integer>> e = new ArrayList<List<Integer>>();
	static PriorityQueue<List<Integer>> pq = new PriorityQueue<List<Integer>>(
		new Comparator<List<Integer>>(){
			public int compare(List<Integer> l1, List<Integer> l2){
				if(l1.get(1) == l2.get(1))
					return l1.get(0) - l2.get(0);
				return -(l1.get(1) - l2.get(1));
			}
		});
	static int[][] w;
	static int[][] unweighted;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk6.out")));

		String[] line = br.readLine().trim().split(" ");
		n = Integer.parseInt(line[0]);
		m = Integer.parseInt(line[1]);

		w = new int[n + 1][n + 1];
		unweighted = new int[n + 1][n + 1];

		for(int i = 0; i < n + 1; i++)
			e.add(new ArrayList<Integer>());
		
		for(int i = 0; i < m; i++){
			line = br.readLine().trim().split(" ");
			int u = Integer.parseInt(line[0]);
			int v = Integer.parseInt(line[1]);
			int weight = Integer.parseInt(line[2]);
			e.get(u).add(v);
			w[u][v] += weight;
			edges.add(Arrays.asList(u, v));
			pq.add(Arrays.asList(i, weight));
		}

		ArrayList<Integer> ans = new ArrayList<Integer>();
		int total = maxFlow();
		int runningTotal = total;
		for(int i = 0; i < m; i++){
			List<Integer> rm = pq.poll();
			int index = rm.get(0);
			int weight = rm.get(1);
			List<Integer> l = edges.get(index);
			int u = l.get(0);
			int v = l.get(1);
			w[u][v] -= weight;
			int newFlow = maxFlow();
			if(runningTotal - newFlow == weight){
				ans.add(index + 1);
				runningTotal -= weight;
			}
			else{
				w[u][v] += weight;
			}
		}

		String shutdown = total + " " + ans.size();
		Collections.sort(ans);
		String cuts = "";
		for(int i = 0; i < ans.size(); i++){
			cuts += ans.get(i) + "\n";
		}

		System.out.print(shutdown + "\n" + cuts);
		pw.print(shutdown + "\n" + cuts);

		br.close();
		pw.close();
	}

	public static int maxFlow(){
		int[] seen = new int[n + 1];
		int[] flow = new int[n + 1];
		int[] prev = new int[n + 1];

		int[][] weight = new int[n + 1][n + 1];
		for(int i = 0; i <= n; i++){
			for(int j = 0; j <= n; j++){
				weight[i][j] = w[i][j];
			}
		}

		int total = 0;

		outer:
		while(true){
			//clear all previous states
			for(int i = 1; i <= n; i++){
				seen[i] = -1;
				flow[i] = -1;
				prev[i] = -1;
			}

			flow[1] = Integer.MAX_VALUE;

			int maxIndex = -1;
			int pathCap = Integer.MAX_VALUE;
			//find max flow
			while(true){
				int max = 0;
				maxIndex = -1;
				for(int i = 1; i <= n; i++){
					if(flow[i] > max && seen[i] == -1){
						max = flow[i];
						maxIndex = i;
					}
				}
				pathCap = Math.min(pathCap, max);
				if(maxIndex == -1 || maxIndex == n)
					break;
				seen[maxIndex] = 1;
				//update neighbors
				for(int i = 1; i <= n; i++){
					if(weight[maxIndex][i] > 0 && seen[i] == -1){
						if(flow[i] < weight[maxIndex][i]){
							flow[i] = weight[maxIndex][i];
							prev[i] = maxIndex;
						}
					}
				}
			}
			if(maxIndex == -1)
				break;

			total += pathCap;
			int cur = maxIndex;
			int last;
			while(cur != 1){
				// System.out.println("cur: " + cur);
				last = prev[cur];
				weight[last][cur] -= pathCap;
				weight[cur][last] += pathCap;
				cur = last;
			}
		}
		return total;
	}
}