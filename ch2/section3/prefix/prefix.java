/*
ID: brian621
LANG: JAVA
TASK: prefix
*/

import java.util.*;
import java.io.*;

public class prefix{
	
	static String seq;
	static HashSet<String> prim = new HashSet<String>();
	static HashSet<Integer> l = new HashSet<Integer>();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));

		String line = br.readLine().trim();
		int maxL = 0;
		// Trie t = new Trie();
		while(! line.equals(".")){
			String[] list = line.split(" ");
			for(String s: list){
				prim.add(s);
				// t.insert(s);
				l.add(s.length());
				if(s.length() > maxL)
					maxL = s.length();
			}
			line = br.readLine().trim();
			// System.out.println("line: " + line);
		}

		seq = "";
		boolean[] pre = new boolean[200001];

		StringBuilder sb = new StringBuilder(2000000);
		while((line = br.readLine()) != null)
			sb.append(line.trim());

		seq = sb.toString();

		// System.out.println("prim: " + prim);

		// System.out.println("seq: " + seq);

		int max = 0;

		int seqLen = seq.length();

		// for(int i = 0; i < seqLen; i++){
		// 	if(i != 0 && !pre[i-1])
		// 		continue;
		// 	for(int j : l){
		// 		if(i + j <= seqLen && !pre[i+j-1] && prim.contains(seq.substring(i, i + j))){
		// 			// System.out.println("working for : " + i + "\t" + seq.substring(i, i + j));
		// 			if(i == 0)
		// 				pre[i] = true;
		// 			// int len = i + j;
		// 			// if(j != 1)
		// 			// 	len--;
		// 			pre[i + j - 1] = true;
		// 			// System.out.println("len: " + len + "\ti: " + i + "\tj: " + j);
		// 			if(i+j > max)
		// 				max = i+j;
		// 		}
		// 	}
		// }

		pre[0] = true;

		for(int i = 0; i < seqLen; i++){
			for(int j : l){
				int len = j;
				if(i - len + 1 < 0)
					continue;
				if(! pre[i - len + 1])
					continue;
				String subAfter = seq.substring(i - len + 1, i + 1);
				// if(t.check(subAfter)){
				if(prim.contains(subAfter)){
					pre[i + 1] = true;
					max = i + 1;
					break;
				}
			}
		}

		System.out.println(max);
		pw.println(max);

		br.close();
		pw.close();
	}

	// static class Trie{

	// 	Trie[] next;
	// 	boolean end;

	// 	public Trie(){
	// 		next = new Trie[27];
	// 		end = false;
	// 	}

	// 	public void insert(String a){
	// 		Trie cur = this;
	// 		for(int i = 0; i < a.length(); i++){
	// 			char c = a.charAt(i);
	// 			if(cur.next[c - 'A'] == null)
	// 				cur.next[c - 'A'] = new Trie();
	// 			cur = cur.next[c - 'A'];
	// 		}
	// 		cur.end = true;
	// 	}

	// 	public boolean check(String a){
	// 		Trie cur = this;
	// 		for(int i = 0; i < a.length(); i++){
	// 			if(cur.next[a.charAt(i) - 'A'] == null)
	// 				return false;
	// 			cur = cur.next[a.charAt(i) - 'A'];
	// 		}
	// 		return cur.end;
	// 	}

	// }

}