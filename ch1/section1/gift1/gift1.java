/*
ID: brian621
LANG: JAVA
TASK: gift1
*/

import java.util.*;
import java.io.*;

public class gift1{
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));

		int np = Integer.parseInt(br.readLine());
		HashMap<String, Integer> money = new HashMap<String, Integer>();
		HashMap<String, Integer> start = new HashMap<String, Integer>();

		String[] names = new String[np];

		for(int i = 0; i < np; i++){
			String name = br.readLine();
			money.put(name, 0);
			start.put(name, 0);
			names[i] = name;
		}

		int cur = np;
		String name = "";
		while(cur > 0){
			name = br.readLine();
			String mp = br.readLine();
			int m = Integer.parseInt(mp.split(" ")[0]);
			int p = Integer.parseInt(mp.split(" ")[1]);
			start.put(name, m);
			if(p == 0)
				money.put(name, money.get(name) + (m));
			else
				money.put(name, money.get(name) + (m % p));
			for(int i = 0; i < p; i++){
				name = br.readLine();
				money.put(name, money.get(name) + (m/p));
			}
			cur--;
			// System.out.println(money);
		}

		for(int i = 0; i < np; i++){
			int diff = money.get(names[i]) - start.get(names[i]);
			pw.println(names[i] + " " +diff);
			System.outc.println(names[i] + " " +diff);
		}

		br.close();
		pw.close();
	}

}