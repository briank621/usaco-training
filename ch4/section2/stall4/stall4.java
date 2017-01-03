/*
ID: brian621
LANG: JAVA
TASK: stall4
*/

import java.util.*;
import java.io.*;

public class stall4{

	static int n,m;
	static int[][] w;
	static int sink;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("stall4.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("stall4.out")));

		String[] line = br.readLine().split(" ");
		n = Integer.parseInt(line[0]);
		m = Integer.parseInt(line[1]);
		sink = 2*n + 2*m + 1;
		w = new int[sink + 1][sink + 1];

		for(int i = 0; i <= sink; i++){
			for(int j = 0; j <= sink; j++)
				w[i][j] = -1;
		}

		for(int i = 1; i <= n; i++){
			line = br.readLine().split(" ");
			int stalls = Integer.parseInt(line[0]);
			w[0][i] = 1;
			w[i][n + i] = 1;
			for(int j = 1; j <= stalls; j++){
				int s = Integer.parseInt(line[j]);
				w[n + i][2*n+s] = 1;
			}
		}

		for(int i = 1; i <= m; i++){
			w[2*n + i][2*n + m + i] = 1;
			w[2*n + m + i][sink] = 1;
		}

		long ans = flow();

		System.out.println(ans);
		pw.println(ans);

		br.close();
		pw.close();
	}

	public static long flow(){
		int[] flow = new int[sink + 1];
		int[] seen = new int[sink + 1];
		int[] prev = new int[sink + 1];

		long total = 0;
		outer:
		while(true){
			for(int i = 0; i <= sink; i++){
				prev[i] = -1;
				flow[i] = 0;
				seen[i] = 0;
			}

			flow[0] = Integer.MAX_VALUE;

			int maxLoc;
			int max;
			inner:
			while(true){
				maxLoc = -1;
				max = 0;
				for(int i = 0; i <= sink; i++){
					if(flow[i] > max && seen[i] == 0){
						max = flow[i];
						maxLoc = i;
					}
				}

				if(maxLoc == -1 || maxLoc == sink)
					break inner;
				seen[maxLoc] = 1;
				for(int i = 0; i <= sink; i++){
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
			int pathCapacity = flow[sink];
			total += pathCapacity;

			int cur = sink;
			while(cur != 0){
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