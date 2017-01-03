/*
ID: brian621
LANG: JAVA
TASK: wormhole
*/

import java.util.*;
import java.io.*;

public class wormhole{
	
	static int n;
	static int[][] holes;
	static int[] paired;
	static int[] nextRight;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("wormhole.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));

		n = Integer.parseInt(br.readLine());
		holes = new int[n][2];
		for(int i = 0; i < n; i++){
			String[] line = br.readLine().split(" ");
			holes[i][0] = Integer.parseInt(line[0]);
			holes[i][1] = Integer.parseInt(line[1]);
		}
		paired = new int[n];
		nextRight = new int[n];
		for(int i = 0; i < n; i++){
			paired[i] = -1;
			nextRight[i] = -1;
		}


		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(holes[i][1] == holes[j][1] && i != j){
					int xcur = holes[i][0];
					int xr = holes[j][0];
					if(xr > xcur){
						if(nextRight[i] == -1 || xr < holes[nextRight[i]][0])
							nextRight[i] = j;
					}
				}
			}
		}

		// System.out.println(Arrays.toString(nextRight));

		int amt = solve();
		System.out.println(amt);
		pw.println(amt);

		br.close();
		pw.close();
	}

	public static boolean check(){

		for(int i = 0; i < n; i++){
			int cur = i;
			for(int j = 0; j <= n; j++){
				cur = nextRight[paired[cur]];
				if(cur == -1)
					break;
			}
			if(cur != -1){
				// System.out.println("cur: " + cur);
				// System.out.println(Arrays.toString(paired));
				// System.out.println(Arrays.toString(nextRight));
				return true;
			}
		}
		return false;
	}

	public static int solve(){
		//find first unpaired wormhole
		int i;
		for(i = 0; i < n; i++){
			if(paired[i] == -1)
				break;
		} 

		if(i == n){
			if(check())
				return 1;
			return 0;
		}

		//pair with everyone
		int total = 0;
		for(int j = i + 1; j < n; j++){
			if(paired[j] == -1){
				paired[i] = j;
				paired[j] = i;
				total += solve();
				paired[i] = -1; 
				paired[j] = -1;
			}
			// System.out.println("j: " + j);
		}

		return total;
	}


}