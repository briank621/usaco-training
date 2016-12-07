/*
ID: brian621
LANG: JAVA
TASK: ride
*/

import java.util.*;
import java.io.*;

public class ride{
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("ride.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));

		String comet = br.readLine();
		String group = br.readLine();

		if(computeMod(comet) == computeMod(group)){
			pw.println("GO");
			System.out.println("GO");
		}
		else{
			pw.println("STAY");
			System.out.println("STAY");
		}

		br.close();
		pw.close();
	}

	public static int computeMod(String s){
		// System.out.println(s);
		int out = 1;
		char[] ca = s.toCharArray();
		for(char c: ca)
			out *= (c - 'A' + 1);
		// System.out.println(out);
		return out % 47;
	}

}
