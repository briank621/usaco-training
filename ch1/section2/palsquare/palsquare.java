/*
ID: brian621
LANG: JAVA
TASK: palsquare
*/

import java.util.*;
import java.io.*;

public class palsquare{
	
	static String[] digits = new String[] {"0", "1", "2", "3","4","5","6","7","8","9",
	"A","B","C","D","E","F","G","H","I","J","K","L","M","N"};

	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new FileReader("palsquare.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));

		int b = Integer.parseInt(br.readLine());
		for(int i = 1; i < 300; i++){
			String o = baseB(b, i);
			String a = baseB(b, i*i);
			if(isPal(a)){
				System.out.println(o + " " + a);
				pw.println(o + " " + a);
			}
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