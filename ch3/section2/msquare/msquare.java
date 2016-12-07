/*
ID: brian621
LANG: JAVA
TASK: msquare
*/

import java.util.*;
import java.io.*;

public class msquare{
	
	static String target;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("msquare.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("msquare.out")));

		target = new String();
		String[] line = br.readLine().split(" ");

		for(int i = 0; i < 8; i++)
			target += line[i];

		String s = bfs();

		System.out.println(s.length());
		System.out.println(s);
		pw.println(s.length());
		pw.println(s);

		br.close();
		pw.close();
	}

	public static ArrayList<Integer> copy(ArrayList<Integer> x){
		ArrayList<Integer> out = new ArrayList<Integer>();
		for(int i = 0; i < x.size(); i++)
			out.add(x.get(i));
		return out;
	}

	public static String applyA(String a){
		String out = "";
		for(int i = 7; i >= 0; i--){
			out += a.charAt(i);
		}
		return out;
	}

	public static String applyC(String a){
		char[] x = a.toCharArray();
		char temp = x[1];
		x[1] = x[6];
		x[6] = x[5];
		x[5] = x[2];
		x[2] = temp;
		return new String(x);
	}

	public static String applyB(String a){
		char[] x = a.toCharArray();
		String out = a;
		char tempTop1 = x[3];
		char tempTop2 = 0;
		char tempBot1 = x[4];
		char tempBot2 = 0;
		for(int i = 0; i < 4; i++){
			tempTop2 = x[i];
			x[i] = tempTop1;
			tempTop1 = tempTop2;
			tempBot2 = x[7 - i];
			x[7 - i]  = tempBot1;
			tempBot1 = tempBot2;
		}
		return new String(x);
	}

	public static String bfs(){
		String cur = new String();
		HashSet<String> seen = new HashSet<String>();
		for(int i = 0; i < 8; i++)
			cur += (i + 1); 

		String move = "";

		ArrayList<String> first = new ArrayList<String>();
		first.add(cur);
		first.add(move);

		Queue<ArrayList<String>> q = new LinkedList<ArrayList<String>>();
		q.add(first);
		// System.out.println("target: " + target);


		while(q.size() != 0){
			ArrayList<String> a = q.poll();
			// System.out.println("a: " + a);

			String state = a.get(0);
			String seq = a.get(1);
			if(seen.contains(state))
				continue;
			seen.add(state);
			if(target.equals(state))
				return seq;

			String stateA = applyA(state);
			ArrayList<String> toAdd = new ArrayList<String>();
			toAdd.add(stateA);
			toAdd.add(seq + 'A');
			String stateB = applyB(state);
			ArrayList<String> toAddB = new ArrayList<String>();
			toAddB.add(stateB);
			toAddB.add(seq + 'B');
			String stateC = applyC(state);
			ArrayList<String> toAddC = new ArrayList<String>();
			toAddC.add(stateC);
			toAddC.add(seq + 'C');
			q.offer(toAdd);
			q.offer(toAddB);
			q.offer(toAddC);
			
		}

		return null;
	}

}