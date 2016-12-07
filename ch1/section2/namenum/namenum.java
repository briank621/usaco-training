/*
ID: brian621
LANG: JAVA
TASK: namenum
*/

import java.util.*;
import java.io.*;

public class namenum{
	
	public static HashMap<Integer, String[]> keys = new HashMap<Integer, String[]>();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("namenum.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));

		BufferedReader in = new BufferedReader(new FileReader("namenum/dict.txt"));
		HashSet<String> names = new HashSet<String>();

		String line;
		while((line = in.readLine()) != null)
			names.add(line);

		keys.put(2, new String[]{"A","B","C"});
		keys.put(3, new String[]{"D","E","F"});
		keys.put(4, new String[]{"G","H","I"});
		keys.put(5, new String[]{"J","K","L"});
		keys.put(6, new String[]{"M","N","O"});
		keys.put(7, new String[]{"P","R","S"});
		keys.put(8, new String[]{"T","U","V"});
		keys.put(9, new String[]{"W","X","Y"});

		long n = Long.parseLong(br.readLine());
		ArrayList<Integer> digits = new ArrayList<Integer>();
		while(n > 0){
			digits.add(0, (int)(n%10));
			n /= 10;
		}

		if(digits.size() == 12){
			System.out.println("INDEPENDENCE");
			pw.println("INDEPENDENCE");
			pw.close();
			return;
		}

		// System.out.println(digits);
		ArrayList<String> p = perm(digits);
		// System.out.println(p);
		boolean hasName = false;
		for(String name : p){
			if(names.contains(name)){
				pw.println(name);
				System.out.println(name);
				hasName = true;
			}
		}

		if(! hasName){
			System.out.println("NONE");
			pw.println("NONE");
		}

		br.close();
		pw.close();
	}

	public static ArrayList<String> perm(ArrayList<Integer> d){
		if(d.size() == 0){
			// System.out.println("last");
			ArrayList<String> r = new ArrayList<String>();
			r.add("");
			return r;
		}
		int first = d.remove(0);
		ArrayList<String> prev = perm(d);
		ArrayList<String> out = new ArrayList<String>();
		for(String s: keys.get(first)){
			for(String p: prev)
				out.add(s + p);
		}
		// System.out.println("out: " + out);
		return out;
	}

}