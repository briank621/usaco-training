/*
ID: brian621
LANG: JAVA
TASK: ditch
*/

import java.util.*;
import java.io.*;

public class ditch{
	
	static int n, m;
	static ArrayList<ArrayList<Integer>> e = new ArrayList<ArrayList<Integer>>();
	static int[][] w;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ditch.out")));

		String[] line = br.readLine().split(" ");
		n = Integer.parseInt(line[0]);
		m = Integer.parseInt(line[1]);

		w = new int[m+1][m+1];
		for(int i = 0; i < m+1; i++){
			for(int j = 0; j < m+1; j++)
				w[i][j] = -1;
		}

		for(int i = 0; i <= m; i++)
			e.add(new ArrayList<Integer>());

		for(int i = 0; i < n; i++){
			line = br.readLine().split(" ");
			int s = Integer.parseInt(line[0]);
			int end = Integer.parseInt(line[1]);
			int f = Integer.parseInt(line[2]);
			e.get(s).add(end);
			w[s][end] = w[s][end] == -1 ? f : w[s][end] + f;
			// w[s][end] = f;
		}

		

		long m = maxFlow();

		System.out.println(m);
		pw.println(m);

		br.close();
		pw.close();
	}

	public static long maxFlow(){
		int[] prev = new int[m + 1];
		int[] flow = new int[m + 1];
		int[] seen = new int[m + 1];

		long total = 0;
		outer:
		while(true){
			for(int i = 1; i <= m; i++){
				prev[i] = -1;
				flow[i] = 0;
				seen[i] = 0;
			}
			flow[1] = Integer.MAX_VALUE;

			int maxLoc;
			int max;
			inner:
			while(true){
				maxLoc = -1;
				max = 0;
				for(int i = 1; i <= m; i++){
					if(flow[i] > max && seen[i] == 0){
						max = flow[i];
						maxLoc = i;
					}
				}

				if(maxLoc == -1 || maxLoc == m)
					break inner;
				seen[maxLoc] = 1;

				// System.out.println("maxLoc is: " + maxLoc);
				for(int i = 1; i <= m; i++){
					if(w[maxLoc][i] == -1)
						continue;
					if(flow[i] < Math.min(max, w[maxLoc][i])){
						prev[i] = maxLoc;
						flow[i] = Math.min(max, w[maxLoc][i]);
					}
				}

			}

			if(maxLoc == -1)
				break outer;
			int pathCapacity = flow[m];
			total += pathCapacity;

			int cur = m;
			while(cur != 1){
				int next = prev[cur];
				w[next][cur] -= pathCapacity;
				if(w[cur][next] == -1)
					w[cur][next] = pathCapacity;
				else
					w[cur][next] += pathCapacity;
				cur = next;
			}
		}

		return total;

	}

}