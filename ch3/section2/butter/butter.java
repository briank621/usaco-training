/*
ID: brian621
LANG: JAVA
TASK: butter
*/

import java.util.*;
import java.io.*;

public class butter{
	
	static int n,p,c;
	static ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
	static int[][] ed;
	static int[] cows;
	static int[][] weights;
	static int[][] dist;
	static int minDist = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("butter.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("butter.out")));

		String[] line = br.readLine().split(" ");
		n = Integer.parseInt(line[0]);
		p = Integer.parseInt(line[1]);
		c = Integer.parseInt(line[2]);

		cows = new int[p + 1];
		weights = new int[p + 1][p + 1];
		dist = new int[p + 1][p + 1];

		ed = new int[p+1][];

		for(int i = 0; i < n; i++){
			cows[Integer.parseInt(br.readLine().trim())]++;
		}

		for(int i = 0; i < p + 1; i++){
			ArrayList<Integer> pasture = new ArrayList<Integer>();
			edges.add(pasture);
		}

		for(int i = 0; i < p + 1; i++){
			for(int j = 0; j < p + 1; j++){
				weights[i][j] = 100000;
				dist[i][j] = -1;
			}
		}

		for(int i = 0; i <= p; i++){
			weights[i][i] = 0;
		}

		for(int i = 0; i < c; i++){
			line = br.readLine().trim().split(" ");
			int u = Integer.parseInt(line[0]);
			int v = Integer.parseInt(line[1]);
			int w = Integer.parseInt(line[2]);
			edges.get(u).add(v);
			edges.get(v).add(u);
			weights[u][v] = w;
			weights[v][u] = w;
		}

		for(int i = 1; i < p + 1; i++){
			ed[i] = new int[edges.get(i).size()];
			int count = 0;
			for(int j: edges.get(i))
				ed[i][count++] = j;
		}

		for(int i = 1; i <= p; i++)
			djikstra(i);

		System.out.println(minDist);
		pw.println(minDist);

		br.close();
		pw.close();
	}

	public static void print(int[][] a){
		for(int i = 0; i < a.length; i++){
			for(int j = 0; j < a[0].length; j++)
				System.out.print(a[i][j] + " ");
			System.out.println("");
		}
	}

	public static void djikstra(int start){
		int[] seen = new int[p + 1];
		// seen.add(start);
		int[] d = new int[p + 1];
		int score = 0;
		int left = c;
		int first = start;
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		q.offer(start);

		for(int i = 1; i <= p; i++){
			d[i] = weights[start][i];
			q.offer(((d[i])<<10) | i);
		}

		while(q.size() > 0){
			int next = q.poll();
			int v = next & 0x3FF;
			int w = next >>> 10;

			if(seen[v] == 1)
				continue;
			seen[v] = 1;
			d[v] = w;
			score += cows[v] * w;
			left-=cows[v];
			if(left == 0)
				break;

			for(int i = 0; i < ed[v].length; i++){
				int j = ed[v][i];
				if(seen[j] == 1)
					continue;
				if(d[j] > w + weights[v][j]){
					q.offer(((w + weights[v][j])<<10) | j);
				}
			}
		}

		minDist = Math.min(minDist, score);
	}

}