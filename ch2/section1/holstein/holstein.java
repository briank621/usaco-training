/*
ID: brian621
LANG: JAVA
TASK: holstein
*/

import java.util.*;
import java.io.*;

public class holstein{

	static int v;
	static int[] r;
	static int g;
	static ArrayList<ArrayList<Integer>> f = new ArrayList<ArrayList<Integer>>();
	static int curMin;
	static ArrayList<Integer> curAns;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("holstein.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));

		v = Integer.parseInt(br.readLine().trim());
		r = new int[v];
		String[] line = br.readLine().split("\\s+");
		for(int i = 0; i < v; i++)
			r[i] = Integer.parseInt(line[i]);
		g = Integer.parseInt(br.readLine().trim());
		curMin = g;
		for(int i = 0; i < g; i++){
			line = br.readLine().split("\\s+");
			ArrayList<Integer> f1 = new ArrayList<Integer>();
			for(int j = 0; j < v; j++)
				f1.add(Integer.parseInt(line[j]));
			f.add(f1);
		}

		curAns = new ArrayList<Integer>();
		ArrayList<Integer> states = new ArrayList<Integer>();
		for(int i = 0; i < g; i++){
			states.add(i);
			curAns.add(i);
		}
			
		int[] cur = new int[v];

		ArrayList<Integer> ans = new ArrayList<Integer>();

		findAns(cur, ans, states);
		// System.out.println("curMIN: " + curMin);
		// System.out.println("CURANS: " + curAns);

		System.out.print(curMin + " ");
		pw.print(curMin + " ");
		for(int i = 0; i < curMin; i++){
			if(i != curMin - 1){
				System.out.print((curAns.get(i) + 1) + " ");
				pw.print((curAns.get(i) + 1) + " ");
			}
			else{
				System.out.print((curAns.get(i) + 1));
				pw.print((curAns.get(i) + 1));
			}
		}
		System.out.println("");
		pw.println("");

		br.close();
		pw.close();
	}

	public static boolean checkArr(int[] cur){
		for(int i = 0; i < v; i++){
			if(cur[i] < r[i])
				return false;
		}
		return true;
	}

	public static void findAns(int[] cur, ArrayList<Integer> ans, ArrayList<Integer> states){
		if(curMin <= ans.size())
			return;
		if(checkArr(cur) && ans.size() < curMin){
			System.out.println("cur: " + Arrays.toString(cur));
			curAns = ans;
			curMin = ans.size();
			return;
		}
		if(states.size() == 0)
			return;
		int ind = states.remove(0);
		ArrayList<Integer> toAdd = f.get(ind);
		int[] newCur = new int[v];
		for(int i = 0; i < v; i++)
			newCur[i] = cur[i] + toAdd.get(i);
		ArrayList<Integer> newAns = new ArrayList<Integer>();
		ArrayList<Integer> newStates = new ArrayList<Integer>();
		for(int i = 0; i < ans.size(); i++)
			newAns.add(ans.get(i));
		for(int i = 0; i < states.size(); i++)
			newStates.add(states.get(i));
		//with cur
		newAns.add(ind);
		findAns(newCur, newAns, newStates);
		//without cur
		findAns(cur, ans, states);
	}

}