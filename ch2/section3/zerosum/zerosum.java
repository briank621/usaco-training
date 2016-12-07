/*
ID: brian621
LANG: JAVA
TASK: zerosum
*/

import java.util.*;
import java.io.*;

public class zerosum{
	public static int n;
	public static ArrayList<String> sol = new ArrayList<String>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));


		n = Integer.parseInt(br.readLine());
		dfs(1, "");

		Collections.sort(sol);
		for(String s: sol){
			System.out.println(s);
			pw.println(s);
		}

		br.close();
		pw.close();

	}

	public static void dfs(int c, String cur){
		if(c == n){
			cur += c;
			if(parseString(cur) == 0)
				sol.add(cur);
			return;
		}
		dfs(c + 1, cur + c +  " ");
		dfs(c + 1, cur + c + "+");
		dfs(c + 1, cur + c + "-");
	}

	public static int returnOp(int i, String op){
		if(op.equals("+"))
			return i;
		else
			return -1 * i;
	}

	public static int parseString(String s){
		// System.out.println("s: " + s);
		String[] ops = s.split("\\d+");
		// System.out.println("ops: " + Arrays.toString(ops));
		int total = 0;
		int prev = 1;
		String op = "+";
		boolean first = true;
		for(int i = 2; i <= n; i++){
			int cur = i;
			if(ops[i-1].equals(" "))
				cur = 10 * prev + cur;
			else if(ops[i-1].equals("+")){
				total += returnOp(prev, op);
				op = "+";
			}
			else{
				total += returnOp(prev, op);
				op = "-";
			}
			prev = cur;
			// System.out.println("total: " + total);
		}
		total += returnOp(prev, op);
		// System.out.println("TOTAL: " + total);
		return total;
	}

}