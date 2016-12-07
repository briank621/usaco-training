/*
ID: brian621
LANG: JAVA
TASK: contact
*/

import java.util.*;
import java.io.*;

public class contact{
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("contact.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));

		String[] line = br.readLine().split(" ");

		int a = Integer.parseInt(line[0]);
		int b = Integer.parseInt(line[1]);
		int n = Integer.parseInt(line[2]);


		StringBuilder sb = new StringBuilder();
		String l;
		while((l = br.readLine()) != null)
			sb.append(l.trim());
		String s = sb.toString();
		int length = s.length();
		if(a > length){
			System.out.println("");
			pw.println("");
			return;
		}
		if(b > length){
			b = length;
		}

		ArrayList<ArrayList<Integer>> out = find(s, a, b, n);
		Collections.sort(out, new Comparator<ArrayList<Integer>>(){
			public int compare(ArrayList<Integer> x, ArrayList<Integer> y){
				if(x.get(1) == y.get(1))
					return x.get(2) - y.get(2);
				return - (x.get(1) - y.get(1));
			}
		});

		// System.out.println("out: " + out);

		int prev = out.get(0).get(1);
		StringBuilder ans = new StringBuilder();
		ans.append(prev);
		// ans.append(returnBin(pad, prev));

		// ans.append(String.format("%x"prev));
		ans.append("\n");
		int prevNum = out.get(0).get(0);
		int prevPad = out.get(0).get(2);
		int i = 1;
		int ind = 0;
		int nums = 0;
		while(ind < n){
			if(i >= out.size())
				break;
			int cur = out.get(i).get(1);
			if(nums == 6){
				ans.append("\n");
				nums = 0;
			}
			if(ans.charAt(ans.length() - 1) != '\n')
				ans.append(" ");
			ans.append(returnBin(prevPad, prevNum));
			// System.out.println("cur: " + cur);
			// System.out.println("prev: " + prev);
			nums++;
			if(cur != prev){
				// System.out.println("here?");
				ind++;
				nums = 0;
				if(ind == n)
					break;
				ans.append("\n");
				ans.append(cur);
				ans.append("\n");
			}
			prev = cur;
			prevNum = out.get(i).get(0);
			prevPad = out.get(i).get(2);
			i++;
		}
		if(ind != n)
			ans.append(returnBin(prevPad, prevNum));
		System.out.println(ans);
		pw.println(ans);


		br.close();
		pw.close();
	}

	public static String returnBin(int amt, int val){
		String f = "%" + amt + "s";
		return String.format(f, Integer.toBinaryString(val)).replace(' ', '0');
	}

	public static ArrayList<ArrayList<Integer>> find(String s, int a, int b, int n){
		int count = 0;
		ArrayList<ArrayList<Integer>> out = new ArrayList<ArrayList<Integer>>();
		for(int i = a; i <= b; i++){
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			int offset = (int) Math.pow(2, i - 1);
			int hash = 0;
			// System.out.println("i: " + i);
			// System.out.println("offset: " + offset);
			for(int j = 0; j < i; j++)
				hash = (hash * 2) + Integer.parseInt("" + s.charAt(j));
			for(int j = 0; j <= s.length() - i; j++){
				// System.out.println("j: " + j);
				// System.out.println("hash: " + hash);
				int times = 1;
				if(map.containsKey(hash))
					times = map.get(hash) + 1;
				map.put(hash, times);
				if(j < s.length() - i){
					// System.out.println("adding: " + s.charAt(j + i));
					hash = (hash % offset) * 2 + Integer.parseInt("" + s.charAt(j + i));
				}
			}
			for(int k : map.keySet()){
				int cur = map.get(k);
				ArrayList<Integer> toAdd = new ArrayList<Integer>();
				toAdd.add(k);
				toAdd.add(cur);
				toAdd.add(i);
				out.add(toAdd);
			}
		}
		return out;
	}

}