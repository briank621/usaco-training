/*
ID: brian621
LANG: JAVA
TASK: ttwo
*/

import java.util.*;
import java.io.*;

public class ttwo{

	static String[][] g = new String[10][10];
	static int count = 0;
	static int fd = 0;
	static int cd = 0;
	static int[] f; 
	static int[] c; 

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));

		f = new int[2];
		c = new int[2];

		for(int i = 0; i < 10; i++){
			char[] line = br.readLine().toCharArray();
			for(int j = 0; j < 10; j++){
				g[i][j] = Character.toString(line[j]);
				if(line[j] == 'F'){
					f[0] = i;
					f[1] = j;
				}
				if(line[j] == 'C'){
					c[0] = i;
					c[1] = j;
				}
			}
		}

		bfs();

		System.out.println(count);
		pw.println(count);

		br.close();
		pw.close();
	}

	public static int[] findNext(int[] coor, int dir){
		int r = coor[0];
		int c = coor[1];
		if(dir == 0) //up
		return new int[]{r-1, c};
		if(dir == 1) //right
		return new int[]{r, c+1};
		if(dir == 2) //down
		return new int[]{r+1, c};
		if(dir == 3) //left
		return new int[]{r, c-1};	
		return null;
	}

	public static boolean isValid(int[] coor){
		int r = coor[0];
		int c = coor[1];
		if(r < 0 || c < 0 || r >= 10 || c >= 10)
			return false;
		return ! g[r][c].equals("*");
	}

	public static void updateLoc(){
		int[] nextC = findNext(c, cd);
		int[] nextF = findNext(f, fd);
		if(! isValid(nextC)){
			nextC = c;
			cd = (cd + 1) % 4;
		}
		if(! isValid(nextF)){
			nextF = f;
			fd = (fd + 1) % 4;
		}
		c = nextC;
		f = nextF;
	}

	public static void bfs(){
		HashSet<ArrayList<Integer>> states = new HashSet<ArrayList<Integer>>();
		ArrayList<Integer> loc = new ArrayList<Integer>();
		for(int i = 0; i < 6; i++)
			loc.add(0);

		while(true){
			loc.set(0, f[0]);
			loc.set(1, f[1]);
			loc.set(2, c[0]);
			loc.set(3, c[1]);
			loc.set(4, fd);
			loc.set(5, cd);

			// System.out.println("loc: " + loc);

			if(f[0] == c[0] && f[1] == c[1])
				return;

			if(states.contains(loc)){
				count = 0;
				return;
			}
			count++;
			states.add(loc);
			updateLoc();
		}

	}

}