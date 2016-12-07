/*
ID: brian621
LANG: JAVA
TASK: friday
*/

import java.util.*;
import java.io.*;

public class friday{
	
	public static void main(String[] args) throws Exception{
		int[] remainders = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		int[] remaindersL = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		String path = args[0];

		BufferedReader br = new BufferedReader(new FileReader("friday.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));

		int n = Integer.parseInt(br.readLine());
		int[] days = new int[7];

		int start = 4;

		for(int i = 0; i < n; i++){
			int year = 1900 + i;
			int[] l;
			if(leapYear(year))
				l = remaindersL;
			else
				l = remainders;
			for(int j = 0; j < 12; j++){
				start = (start + (l[(j + 11) % 12] % 7)) %7;
				days[start]++;
			}
			//System.out.println(Arrays.toString(days));

		}


		String out = "";
		for(int i = 0; i < days.length; i++){
			out += days[i] + " ";
		}
		out = out.substring(0, out.length() - 1);

		System.out.println(out);
		pw.println(out);

		br.close();
		pw.close();
	}

	public static boolean leapYear(int n){
		return (n % 400 == 0) || ( (n%4 == 0) && (n % 100 != 0) );
	}

}