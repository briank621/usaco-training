/*
ID: brian621
LANG: JAVA
TASK: dualpal
*/

import java.util.*;
import java.io.*;

public class dualpal{
	
	static String[] digits = new String[] {"0", "1", "2", "3","4","5","6","7","8","9",
	"A","B","C","D","E","F","G","H","I","J","K","L","M","N"};

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("dualpal.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));

		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int s = Integer.parseInt(line[1]) + 1;

		while(n > 0){
			int numPal = 0;
			for(int i = 2; i <= 10; i++){
				String b = baseB(i, s);
				if(isPal(b))
					numPal++;
				if(numPal == 2)
					break;
			}
			if(numPal == 2){
				pw.println(s);	
				System.out.println(s);
				n--;
			}
			s++;
		}

		br.close();
		pw.close();
	}

	public static String baseB(int b, int v){
		String s = "";
		while(v > 0){
			s = digits[v%b] + s;
			v /= b;
		}
		return s;
	}

	public static boolean isPal(String s){
		boolean isPal = true;
		int n = s.length();
		for(int i = 0; i < n/2; i++){
			if(s.charAt(i) != s.charAt(n - 1 - i))
				isPal = false;
		}
		return isPal;
	}

}