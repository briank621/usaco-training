/*
ID: brian621
LANG: JAVA
TASK: concom
*/

import java.util.*;
import java.io.*;

public class concom{
	
	static int n;
	static ArrayList<LinkedList<Integer>> e = new ArrayList<LinkedList<Integer>>();
	static int[] seen;
	static int[][] own;
	static int[][] ans;
	static HashSet<Integer> nodes = new HashSet<Integer>();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("concom.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));

		n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i <= 100; i++){
			LinkedList<Integer> c = new LinkedList<Integer>();
			e.add(c);
		}
		seen = new int[101];
		own = new int[101][101];
		ans = new int[101][101];

		for(int i = 0; i < n; i++){
			String[] line = br.readLine().split(" ");
			int v1 = Integer.parseInt(line[0]);
			int v2 = Integer.parseInt(line[1]);
			int w = Integer.parseInt(line[2]);
			e.get(v1).add(v2);
			own[v1][v2] = w;
			nodes.add(v1);
			nodes.add(v2);
		}

		ArrayList<Integer> vertices = new ArrayList<Integer>(nodes);

		for(int i = 0; i < vertices.size(); i++){
			// System.out.println("v: " + vertices.get(i));
			clear(seen);
			dfs(vertices.get(i));
		}

		for(int i = 0; i <= 100; i++){
			for(int j = 0; j <= 100; j++){
				if(ans[i][j] == 1){
					System.out.println(i + " " + j);
					pw.println(i + " " + j);
				}
			}
		}

		br.close();
		pw.close();
	}

	public static void dfs(int start){
		// System.out.println("inside dfs " + start);
		Stack<ArrayList<Integer>> s = new Stack<ArrayList<Integer>>();
		HashSet<ArrayList<Integer>> seenEdges = new HashSet<ArrayList<Integer>>();
		for(int v: e.get(start)){
			ArrayList<Integer> edge = new ArrayList<Integer>();
			edge.add(start);
			edge.add(v);
			s.push(edge);
		}

		while(! s.empty()){
			// System.out.println("s: " + s);
			ArrayList<Integer> edge = s.pop();
			if(seenEdges.contains(edge))
				continue;
			seenEdges.add(edge);
			seen[edge.get(1)] += own[edge.get(0)][edge.get(1)];
			// System.out.println("edge: " + edge);
			// System.out.println("seen: " + seen[edge.get(1)]);
			if(seen[edge.get(1)] > 50 && edge.get(1) != start){
				// System.out.println("here");
				ans[start][edge.get(1)] = 1;

				for(int v: e.get(edge.get(1))){
					ArrayList<Integer> e = new ArrayList<Integer>();
					e.add(edge.get(1));
					e.add(v);
					s.push(e);
				}
				
			}
		}

	}

	public static void clear(int[] s){
		for(int i = 0; i < s.length; i++){
			s[i] = 0;
		}
	}

}