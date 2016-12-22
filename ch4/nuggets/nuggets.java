/*
ID: brian621
LANG: JAVA
TASK: nuggets
*/

import java.util.*;
import java.io.*;

public class nuggets{

	static int[] seen = new int[64770];
	static int n;
	static int[] v;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("nuggets.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("nuggets.out")));

		n = Integer.parseInt(br.readLine());
		v = new int[n];

		for(int i = 0; i < n; i++)
			v[i] = Integer.parseInt(br.readLine());

		Arrays.sort(v);
		if(n == 1 || v[0] == 1){
			System.out.println("0");
			pw.println("0");
			pw.close();
			return;
		}

		int minCheck = Integer.MAX_VALUE;

		for(int i = 0; i < n; i++){
			for(int j = i + 1; j < n; j++){
				if(gcd(v[i], v[j]) == 1){
					int impossible = v[i] * v[j] - v[i] - v[j];
					minCheck = Math.min(minCheck, impossible);
				}
			}
		}

		if(minCheck == Integer.MAX_VALUE){
			System.out.println("0");
			pw.println("0");
			pw.close();
			return;
		}

		int numChecks = minCheck/v[0];

		// for(int i = 0; i < numChecks + 1; i++){
		// 	dfs(0, i);
		// }

		bfs();
		int ans = 0;
		for(int i = 0; i < seen.length; i++){
			if(seen[i] == 0)
				ans = i;
		}

		System.out.println(ans);
		pw.println(ans);

		br.close();
		pw.close();
	}

	public static int gcd(int a, int b){
		if(b == 0)
			return a;
		return gcd(b, a%b);
	}

	public static void bfs(){
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(0);

		while(q.size() > 0){
			int val = q.poll();
			// System.out.println("val: " + val);
			if(val > 64769)
				continue;
			if(seen[val] == 1)
				continue;
			seen[val] = 1;
			for(int i = 0; i < n; i++){
				// System.out.println("adding: " + v[i]);
				// System.out.println("offer: " + (val + v[i]));
				q.offer(val + v[i]);
			}
		}
	}

	public static void dfs(int val, int depth){
		if(depth == 0)
			return;
		if(val > 64769)
			return;
		if(seen[val] == 1)
			return;
		seen[val] = 1;
		for(int i = 0; i < n; i++){
			dfs(val + v[i], depth - 1);
		}
	}

}