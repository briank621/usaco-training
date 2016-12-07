/*
ID: brian621
LANG: JAVA
TASK: maze1
*/

import java.util.*;
import java.io.*;

public class maze1{
	
	static int w;
	static int h;
	static char[][] maze;
	static int[] start = null;
	static int[] end = null;
	static int max;
	static int[][] dist;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("maze1.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));

		String[] line = br.readLine().split(" ");
		w = Integer.parseInt(line[0]);
		h = Integer.parseInt(line[1]);

		start = null;
		end = null;

		maze = new char[2*h+1][2*w+1];
		dist = new int[2*h+1][2*w+1];

		char[] row = br.readLine().toCharArray();
		maze[0] = row;
		check(row, 0);

		for(int i = 1; i < 2*h; i++){
			row = br.readLine().toCharArray();
			checkEnds(row, 0, i);
			checkEnds(row, 2*w, i);
			maze[i] = row;
		}

		row = br.readLine().toCharArray();
		maze[2*h] = row;
		check(row, 2*h);

		int max = 0;

		// System.out.println("start" + Arrays.toString(start));
		// System.out.println("end" + Arrays.toString(end));

		// print();

				// System.out.println("begin");
		int[] startP = convertP(start);
		int[] endP = convertP(end);
		// System.out.println("start" + Arrays.toString(startP));

		// System.out.println("start" + Arrays.toString(endP));

		int val1 = bfs(startP[0], startP[1], false);
		int val2 = bfs(endP[0], endP[1], true);
		// System.out.println("v1 " + val1);
		// System.out.println("v2 " + val2);

		max = Math.max(val1, val2);

		System.out.println(max);
		pw.println(max);

		br.close();
		pw.close();
	}

	public static int[] convertP(int[] p){
		int[] out = new int[2];
		out[0] = p[0];
		out[1] = p[1];
		if(p[0] == 0)
			out[0] = 1;
		if(p[0] == 2*h)
			out[0] = 2*h - 1;
		if(p[1] == 0)
			out[1] = 1;
		if(p[1] == 2*w)
			out[1] = 2*w - 1;
		return out;
	}

	public static void print(){
		for(int i = 0; i < maze.length; i++){
			for(int j = 0; j < maze[i].length; j++){
				System.out.print(maze[i][j] + " ");
			}
			System.out.println("");
		}
	}

	public static boolean match(int x, int y){
		return (x == start[0] && y== start[1]) || (x == end[0] && y== end[1]);
	}

	public static boolean valid(int x, int y){
		return !(x < 0 || y < 0 || x > 2*h || y > 2*w);
	}

	public static int bfs(int x, int y, boolean check){
		ArrayList<Integer> pt = new ArrayList<Integer>();
		int[][] seen = new int[2*h + 1][2*w + 1];
		LinkedList<ArrayList<Integer>> q = new LinkedList<ArrayList<Integer>>();

		int[] xd = new int[]{1, -1, 0, 0};
		int[] yd = new int[]{0, 0, 1, -1};

		int curMax = 1;

		pt.add(x);
		pt.add(y);
		pt.add(1);
		q.add(pt);

		// System.out.println("pt: " + pt);

		while(q.size() != 0){
			pt = q.pop();
			x = pt.get(0);
			y = pt.get(1);
			if(! valid(x, y))
				continue;
			if(seen[x][y] != 0)
				continue;
			int count = pt.get(2);
			if(! check)
				dist[x][y] = count;
			else{
				count = Math.min(count, dist[x][y]);
				if(count > curMax)
					curMax = count;
			}

			seen[x][y] = 1;
			for(int i = 0; i < 4; i++){
				if(maze[x + xd[i]][y + yd[i]] == ' '){
					int[] toAdd = new int[]{x + 2*xd[i], y+2*yd[i], count+1};
					pt = convert(toAdd);
					q.addLast(pt);
				}
			}
			// System.out.println("pt: " + pt);
		}
		return curMax;
	}

	public static ArrayList<Integer> convert(int[] a){
		ArrayList<Integer> out = new ArrayList<Integer>();
		for(int i = 0; i < a.length; i++){
			out.add(a[i]);
		}
		return out;
	}

	public static void checkEnds(char[] row, int col, int where){
		if(row[col] == ' '){
			if(start == null){
				start = new int[2];
				start[0] = where;
				start[1] = col;
			}
			else{
				end = new int[2];
				end[0] = where;
				end[1] = col;
			}
		}

	}

	public static void check(char[] row, int where){
		for(int i = 0; i < 2*w + 1; i++)
			checkEnds(row, i, where);
	}

}