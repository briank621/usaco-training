/*
ID: brian621
LANG: JAVA
TASK: fracdec
*/

import java.util.*;
import java.io.*;

public class fracdec{
	
	static int n;
	static int d;
	static HashMap<Integer, Integer> seen = new HashMap<Integer, Integer>();
	static ArrayList<String> ord = new ArrayList<String>();
	static StringBuilder out = new StringBuilder();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("fracdec.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));

		String[] line = br.readLine().split(" ");
		n = Integer.parseInt(line[0]);
		d = Integer.parseInt(line[1]);

		int beg = n/d;
		int start = -1;
		if(beg == 0)
			ord.add("0");
		while(beg > 0){
			ord.add("" + (beg % 10));
			beg/=10;
		}
		Collections.reverse(ord);
		ord.add(".");
		int offset = ord.size();
		if(n%d == 0)
			ord.add("0");
		else{
			int r = n%d;
			int q = r * 10;
			int i = 0;
			while(true){
				int quo = q/d;
				ord.add("" + quo);
				if(q % d == 0)
					break;
				r = q%d;
				if(seen.containsKey(r)){
					start = seen.get(r) + offset;
					if(Integer.parseInt(ord.get(start)) == quo)
						ord.remove(ord.size() - 1);
					else
						start++;
					ord.add(start, "(");
					ord.add(")");
					break;
				}
				seen.put(r, i++);	
				q = r*10;
			}
		}
		if(start == -1){
			outer:
			for(int j = 0; j <= ord.size()/76; j++){
				for(int k = 0; k < 76; k++){
					int ind = j * 76 + k;
					if(ind >= ord.size())
						break outer;
					out.append(ord.get(ind));
				}
				out.append("\n");
			}
		}
		else{
			outer:
			for(int j = 0; j <= ord.size()/76; j++){
				for(int k = 0; k < 76; k++){
					int ind = j * 76 + k;
					if(ind >= ord.size())
						break outer;
					out.append(ord.get(ind));
				}
				out.append("\n");
			}
		}		

		System.out.println(out);
		pw.println(out);

		br.close();
		pw.close();
	}

}