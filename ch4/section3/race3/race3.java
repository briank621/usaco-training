/*
ID: brian621
LANG: JAVA
TASK: race3
*/

import java.util.*;
import java.io.*;

public class race3{

	static int n = 0;
	static ArrayList<ArrayList<Integer>> e = new ArrayList<ArrayList<Integer>>(51);
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("race3.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("race3.out")));
		String line;

		for(int i = 0; i < 51; i++)
			e.add(new ArrayList<Integer>());

		while(! (line = br.readLine()).equals("-1")){
			StringTokenizer st = new StringTokenizer(line);
			while(st.hasMoreTokens()){
				int v = Integer.parseInt(st.nextToken());
				if(v == -2)
					break;
				e.get(n).add(v);
			}
			n++;
		}
		n--;

		int unavoidable = 0;
		ArrayList<Integer> unavoid = new ArrayList<Integer>();

		for(int i = 1; i < n; i++){
			HashSet<Integer> seen = bfs(i, 0, e);
			if(! seen.contains(n))
				unavoid.add(i);
		}
		String avoid = unavoid.size() + " ";
		for(int i = 0; i < unavoid.size(); i++)
			avoid += unavoid.get(i) + " ";
		
		avoid = avoid.substring(0, avoid.length() - 1);

		ArrayList<Integer> split = new ArrayList<Integer>();
		outer:
		for(int i = 0; i < unavoid.size(); i++){
			int v = unavoid.get(i);
			HashSet<Integer> forward = bfs(v, 0, e);
			HashSet<Integer> backward = bfs(v, v, e);
			for(int j: forward){
				if(j == v)
					continue;
				if(backward.contains(j))
					continue outer;
			}
			split.add(v);
		}

		String splitter = split.size() + " ";
		for(int i = 0; i < split.size(); i++)
			splitter += split.get(i) + " ";
		splitter = splitter.substring(0, splitter.length() - 1);

		System.out.println(avoid);
		System.out.println(splitter);
		pw.println(avoid);
		pw.println(splitter);

		br.close();
		pw.close();
	}

	public static HashSet<Integer> bfs(int avoid, int start, ArrayList<ArrayList<Integer>> edges){
		Queue<Integer> q = new LinkedList<Integer>();
		HashSet<Integer> seen = new HashSet<Integer>();
		HashSet<Integer> out = new HashSet<Integer>();
		q.offer(start);

		while(q.size() > 0){
			int cur = q.poll();
			if(seen.contains(cur))
				continue;
			seen.add(cur);
			out.add(cur);

			for (int v : edges.get(cur)) {
				if(v == avoid)
					continue;
				q.offer(v);
			}
		}
		return out;
	}

}