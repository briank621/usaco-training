/*
ID: brian621
LANG: JAVA
TASK: castle
*/

import java.util.*;
import java.io.*;

public class castle{

	static int n,m;
	static int[][] c;
	static int[][] seen;
	static int maxSize = 0;
	static int removeMax = 0;
	static int numRoom;
	static boolean changed = false;
	static boolean same = false;
	static int[] maxWall = new int[3];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));

		String[] line = br.readLine().split(" ");
		n = Integer.parseInt(line[1]);
		m = Integer.parseInt(line[0]);

		c = new int[n][m];
		seen = new int[n][m];

		for(int i = 0; i < n; i++){
			line = br.readLine().split(" ");
			for(int j = 0; j < m; j++)
				c[i][j] = Integer.parseInt(line[j]);
		}

		findMax();
		int origSize = maxSize;
		int origNum = numRoom;

		int[] bits = new int[]{2, 4};
		for(int j = 0; j < m; j++){
			for(int i = n-1; i >= 0; i--){
				int walls = c[i][j];
				for(int k = 0; k < bits.length; k++){
					if((walls & bits[k]) == bits[k]) {
						if(k == 0 && i > 0){
							int s = c[i-1][j];
							c[i][j] = walls ^ bits[k];
							c[i-1][j] = s ^ 8;
							findMax();
							c[i][j] = walls | bits[k];
							c[i-1][j] = s | 8;
						}
						if(k == 1 && j < m - 1){
							int w = c[i][j + 1];
							c[i][j] = walls ^ bits[k];
							c[i][j + 1] = w ^ 1;
							findMax();
							c[i][j] = walls | bits[k];
							c[i][j + 1] = w | 1;
						}
						if(changed){
							changed = false;
							// if(k == 0){
							// 	maxWall[0] = i + 2;
							// 	maxWall[1] = j + 1;
							// }
							// else{
							maxWall[0] = i + 1;
							maxWall[1] = j + 1;
							// }
							maxWall[2] = k;
							// System.out.println("MAX: " + maxSize);
							// System.out.printf("%d %d %s\n\n", maxWall[0], maxWall[1],maxWall[2]);
						}
					}
				}
			}
		}

		String[] EN = new String[]{"N", "E"};

		pw.println(origNum);
		System.out.println(origNum);
		pw.println(origSize);
		System.out.println(origSize);
		pw.println(maxSize);
		System.out.println(maxSize);
		pw.printf("%d %d %s\n", maxWall[0], maxWall[1], EN[maxWall[2]]);
		System.out.printf("%d %d %s\n", maxWall[0], maxWall[1], EN[maxWall[2]]);

		br.close();
		pw.close();
	}

	public static void findMax(){
		seen = new int[n][m];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				if(seen[i][j] == 0){
					numRoom++;
					int size = dfs(i, j, numRoom);
					if(size > maxSize){
						maxSize = size;
						changed = true;
					}
					else if(size == maxSize)
						same = true;
				}
			}
		}
	}

	public static void print(int[][] x){
		for(int i = 0; i < x.length; i++){
			for(int j = 0; j < x[0].length; j++)
				System.out.print(x[i][j] + " ");
			System.out.println("");
		}
		System.out.println("");
	}

	public static int dfs(int x, int y, int s){
		if(x < 0 || x >= n || y < 0 || y >= m)
			return 0;
		if(seen[x][y] != 0)
			return 0;
		int count = 1;
		int walls = c[x][y];
		seen[x][y] = s;
		// print(seen);

		if((walls & 1) == 0) 
			count += dfs(x, y - 1, s);
		if((walls & 2) == 0) 
			count += dfs(x - 1, y, s);		
		if((walls & 4) == 0) 
			count += dfs(x, y + 1, s);		
		if((walls & 8) == 0) 
			count += dfs(x + 1, y, s);
		return count;
	}

}