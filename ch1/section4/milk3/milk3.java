/*
ID: brian621
LANG: JAVA
TASK: milk3
*/

import java.util.*;
import java.io.*;

public class milk3{
	
	static int a;
	static int b;
	static int c;

	static HashSet<Integer> pos = new HashSet<Integer>();
	static HashSet<ArrayList<Integer>> seen = new HashSet<ArrayList<Integer>>();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("milk3.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));

		String[] line = br.readLine().split(" ");
		a = Integer.parseInt(line[0]);
		b = Integer.parseInt(line[1]);
		c = Integer.parseInt(line[2]);

		bfs(0, 0, c);		
		List<Integer> ans = new ArrayList<Integer>(pos);
		Collections.sort(ans);

		String out = "";
		for(int i = 0; i < ans.size(); i++)
			out += ans.get(i) + " ";

		out = out.substring(0, out.length() - 1);

		pw.println(out);
		System.out.println(out);

		br.close();
		pw.close();
	}

	public static void bfs(int ma, int mb, int mc){
		ArrayList<Integer> state = new ArrayList<Integer>();
		state.add(ma);
		state.add(mb);
		state.add(mc);
		// System.out.println("seen: " + seen);
		if(seen.contains(state))
			return;
		seen.add(state);
		// System.out.println(state);

		if(ma == 0)
			pos.add(mc);
		//starting from a
		int over = (mb + ma) - b;
		if(over > 0)
			bfs(over, b, mc);
		else
			bfs(0, mb + ma, mc);
		over = (mc + ma) - c;
		if(over > 0)
			bfs(over, mb, c);
		else
			bfs(0, mb, ma + mc);
		//starting from b
		over = (mb + ma) - a;
		if(over > 0)
			bfs(a, over, mc);
		else
			bfs(ma+mb, 0, mc);
		over = (mb + mc) - c;
		if(over > 0)
			bfs(ma, over, c);
		else
			bfs(ma, 0, mb + mc);
		//starting from c
		over = (ma + mc) - a;
		if(over > 0)
			bfs(a, mb, over);
		else
			bfs(ma + mc, mb, 0);
		over = (mb + mc) - b;
		if(over > 0)
			bfs(ma, b, over);
		else
			bfs(ma, mb + mc, 0);
	}

}