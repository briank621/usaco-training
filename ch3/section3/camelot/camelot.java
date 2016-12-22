/*
ID: brian621
LANG: JAVA
TASK: camelot
*/

import java.util.*;
import java.io.*;

public class camelot{
	
	static int r,c;
	static int[][] b;
	static int[][] king;
	static int[][] cost;
	static int[][] pickUp;
	static ArrayList<Integer> knights = new ArrayList<Integer>();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("camelot.out")));

		String[] line = br.readLine().split(" ");
		r = Integer.parseInt(line[0]);
		c = Integer.parseInt(line[1]);

		b = new int[r][c];
		king = new int[r][c];
		cost = new int[r][c];
		pickUp = new int[r][c];

		String l;
		line = br.readLine().split(" ");
		int kc = line[0].charAt(0) - 'A';
		int kr = Integer.parseInt(line[1]) - 1;

		while((l = br.readLine()) != null){
			StringTokenizer st = new StringTokenizer(l);
			while(st.hasMoreTokens()){
				int col = st.nextToken().charAt(0) - 'A';
				int row = Integer.parseInt(st.nextToken()) - 1;
				knights.add(row);
				knights.add(col);
			}
		}

		kingBFS(kr, kc);
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				pickUp[i][j] = king[i][j];
			}
		}

		// print(king);
		// System.out.println("");

		int min = Integer.MAX_VALUE;

		for(int i = 0; i < knights.size()/2; i++){
			int kx = knights.get(2 * i);
			int ky = knights.get(2 * i + 1);
			int[][] norm = new int[r][c];
			int[][] out = new int[r][c];
			for(int j = 0; j < r; j++){
				Arrays.fill(norm[j], 10400);
				Arrays.fill(out[j], 10400);
			}
			knightBFS(kx, ky, king[kx][ky], norm, out);
			// System.out.println("");
			// print(norm);
			// System.out.println("---------");
			// print(out);
			for(int j = 0; j < r; j++){
				for(int k = 0; k < c; k++){
					b[j][k] += norm[j][k];
					pickUp[j][k] = Math.min(pickUp[j][k], out[j][k] - norm[j][k]);
				}
			}
		}

		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++)
				min = Math.min(b[i][j] + pickUp[i][j], min);
		}

		// System.out.println("");
		// print(pickUp);
		// System.out.println("");
		// print(b);

		System.out.println(min);
		pw.println(min);

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

	public static void knightBFS(int kr, int kc, int og, int[][] board, int[][] pick){
		Queue<ArrayList<Integer>> q = new LinkedList<ArrayList<Integer>>();
		int[][] seen = new int[r][c];
		
		int[] kx = new int[]{-2, -2, -1, 1, 2, 2, -1, 1};
		int[] ky = new int[]{-1, 1, 2, 2, -1, 1, -2, -2};

		ArrayList<Integer> start = new ArrayList<Integer>();
		start.add(kr);
		start.add(kc);
		start.add(0);
		start.add(og);
		q.offer(start);

		while(q.size() > 0){
			ArrayList<Integer> cur = q.poll();
			ArrayList<Integer> xy = new ArrayList<Integer>();
			int x = cur.get(0);
			int y = cur.get(1);
			int count = cur.get(2);
			int kdist = cur.get(3);
			if(x < 0 || y < 0)
				continue;
			if(x >= r || y >= c)
				continue;
			if(seen[x][y] == 1){
				if(kdist + count < pick[x][y])
					pick[x][y] = kdist + count;
				continue;
			}
			seen[x][y] = 1;
			kdist = Math.min(kdist, king[x][y]);

			board[x][y] = count;
			pick[x][y] = kdist + count;
			// System.out.printf("(%d, %d) : %d \t %d\n", x, y, count, og);
			for(int i = 0; i < kx.length; i++){
				int newX = x + kx[i];
				int newY = y + ky[i];
				int newCount = count + 1;
				ArrayList<Integer> toAdd = new ArrayList<Integer>();
				toAdd.add(newX);
				toAdd.add(newY);
				toAdd.add(newCount);
				toAdd.add(kdist);
				q.offer(toAdd);
			}
		}

	}

	public static void kingBFS(int kr, int kc){
		Queue<ArrayList<Integer>> q = new LinkedList<ArrayList<Integer>>();
		int[][] seen = new int[r][c];
		
		int[] kx = new int[]{0, 1, 1, 1, 0, -1, -1, -1};
		int[] ky = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};

		ArrayList<Integer> start = new ArrayList<Integer>();
		start.add(kr);
		start.add(kc);
		start.add(0);
		q.offer(start);

		while(q.size() > 0){
			ArrayList<Integer> cur = q.poll();
			int x = cur.get(0);
			int y = cur.get(1);
			if(x < 0 || y < 0)
				continue;
			if(x >= r || y >= c)
				continue;
			if(seen[x][y] == 1)
				continue;
			seen[x][y] = 1;
			int count = cur.get(2);
			king[x][y] = count;
			for(int i = 0; i < kx.length; i++){
				int newX = x + kx[i];
				int newY = y + ky[i];
				int newCount = count + 1;
				ArrayList<Integer> toAdd = new ArrayList<Integer>();
				toAdd.add(newX);
				toAdd.add(newY);
				toAdd.add(newCount);
				q.offer(toAdd);
			}
		}
	}

}