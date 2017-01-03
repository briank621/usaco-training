/*
ID: brian621
LANG: JAVA
TASK: test
*/

import java.util.*;
import java.io.*;

public class test{
	
	public static void main(String[] args) throws IOException{
		String file = args[0];

		BufferedReader br = new BufferedReader(new FileReader("test.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("test.out")));

		int sum = 0;
		String[] ints = br.readLine().split(" ");
		sum += Integer.parseInt(ints[0]);
		sum += Integer.parseInt(ints[1]);

		pw.println(sum);
		System.out.println(sum);

		br.close();
		pw.close();
	}

}
