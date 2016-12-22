/*
ID: brian621
LANG: JAVA
TASK: range
*/

import java.util.*;
import java.io.*;

public class range{

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("range.out")));

		int n = Integer.parseInt(br.readLine());
		int[][] prev = new int[n][n];

		for(int i = 0; i < n; i++){
			String line = br.readLine();
			for(int j = 0; j < n; j++){
				int val = line.charAt(j) - '0';
				prev[i][j] = val;
			}
		}

		ArrayList<Integer> answers = new ArrayList<Integer>();
		int step = 2;

		for(int k = 2; k <= n; k++){
			int[][] cur = new int[n][n];
			int count = 0;
			for(int i = 0; i <= n - step; i++){
				for(int j = 0; j <= n - step; j++){
					if (prev[i][j] == 1 && prev[i+1][j] == 1 && prev[i][j+1] == 1 && prev[i+1][j+1] == 1){
						count++;
						cur[i][j] = 1;
					}
				}
			}
			if(count == 0)
				break;
			answers.add(count);
			prev = cur;
		}

		String ans = "";
		for(int i = 0; i < answers.size(); i++)
			ans += (i + 2) + " " + answers.get(i) + "\n";

		ans = ans.substring(0, ans.length() - 1);
		pw.println(ans);

		br.close();
		pw.close();
	}

}