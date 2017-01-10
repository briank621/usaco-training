/*
ID: brian621
LANG: JAVA
TASK: shuttle
*/

import java.util.*;
import java.io.*;

public class shuttle{
	
	static int n;
	static int[] b;
	static int[] w;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shuttle.out")));

		n = Integer.parseInt(br.readLine().trim());
		b = new int[n];
		w = new int[n];
		for(int i = 0; i < n; i++){
			w[i] = i + 1;
			b[i] = n + 2 + i;
		}

		ArrayList<Integer> ans = new ArrayList<Integer>();

		for(int i = 0; i < n; i++){
			if(i % 2 == 0){
				for(int j = 0; j < i; j++){
					ans.add(w[n-1-j]);
					w[n-1-j] += 2;
				}		
				ans.add(w[n-1-i]);
				w[n-1-i]++;
			}
			else{
				for(int j = 0; j < i; j++){
					ans.add(b[j]);
					b[j] -= 2;
				}		
				ans.add(b[i]);
				b[i]--;
			}			
		}

		//do the n skip whites
		for(int i = 0; i < n; i++){
			if(n % 2 == 1){
				ans.add(b[i]);
				b[i] -= 2;
			}
			else{
				ans.add(w[n - 1 - i]);
				w[n - 1 - i] += 2;
			}
		}

		for(int i = 0; i < n; i++){
			if((i + n) % 2 == 1){
				ans.add(w[n - 1 - i]);
				w[n-1-i]++;
				for(int j = n-1; j > i; j--){
					ans.add(w[j - i - 1]);
					w[j - i - 1] += 2;
				}
			}
			else{
				ans.add(b[i]);
				b[i]--;
				for(int j = i+1; j < n; j++){
					ans.add(b[j]);
					b[j] -= 2;
				}
			}
		}

		String out = new String();

		for(int i = 0; i <= ans.size()/20; i++){
			for(int j = 0; j < Math.min(20, ans.size() - i*20); j++)
				out += ans.get(i*20 + j) + " ";
			out = out.substring(0, out.length() - 1);
			out += "\n";
		}

		String s = out.substring(0, out.length() - 1);

		System.out.println(s);
		pw.println(s);

		br.close();
		pw.close();
	}

}