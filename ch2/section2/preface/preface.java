/*
ID: brian621
LANG: JAVA
TASK: preface
*/

import java.util.*;
import java.io.*;

public class preface{
	
	static String RN = "I:1 V:5 X:10 L:50 C:100 D:500 M:1000";
	static HashMap<Integer, String> roman = new HashMap<Integer, String>();
	static int[] rint = new int[7];
	static int[] ans = new int[7];

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("preface.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));

		int n = Integer.parseInt(br.readLine());
		String[] r = RN.split(" ");
		for(int i = 0; i < 7; i++){
			String[] pair = r[i].split(":");
			int val = Integer.parseInt(pair[1]);
			roman.put(val, pair[0]);
			rint[i] = val;
		}

		for(int i = 1; i <= n; i++)
			numerize(i);

		for(int i = 0; i < 7; i++){
			if(ans[i] != 0){
				System.out.println(roman.get(rint[i]) + " " + ans[i]);
				pw.println(roman.get(rint[i]) + " " + ans[i]);
			}
		}

		br.close();
		pw.close();
	}

	public static void numerize(int n){
		if(n == 0)
			return;
		int last = 0;
		for(int i = 0; i < 7; i++){
			if(rint[i] > n)
				break;
			last = i;
		}
		if(last == 6){
			while(n >= 1000){
				n-=1000;
				ans[6]++;
			}
			numerize(n);
			return;
		}
		//check if it's a reverse
		if(last != 0 && rint[last - 1] != 5 && rint[last - 1] != 50 && rint[last - 1] != 500){
			int next = rint[last+1] - rint[last - 1];
			if(n >= next){
				n -= next;
				ans[last+1]++;
				ans[last-1]++;
				numerize(n);
				return;
			}
		}
		int next = rint[last+1] - rint[last];
		if(n >= next && next != rint[last] && rint[last] != 5 && rint[last] != 50 && rint[last] != 500){
			n -= next;
			ans[last+1]++;
			ans[last]++;
			numerize(n);
			return;
		}
		next = 
		ans[last]++;
		numerize(n-rint[last]);
	}

}