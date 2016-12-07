/*
ID: brian621
LANG: JAVA
TASK: beads
*/

import java.util.*;
import java.io.*;

public class beads{
	
	public static void main(String[] args) throws Exception{
		String file = args[0];
		BufferedReader br = new BufferedReader(new FileReader("beads.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));

		int n = Integer.parseInt(br.readLine());
		String b = br.readLine();
		b = b + b;

		int prevRun = 0;
		int curRun = 1;
		char prev = b.charAt(0);
		char cur = ' ';
		int max = 0;
		int numWhite = (prev == 'w' ? 1 : 0);
		boolean white = false;

		for(int i = 1; i < 2*n; i++){
			cur = b.charAt(i);

			if(i == 2*n - 1){
				if(prev == 'w' || cur == 'w' || prev == cur)
					curRun++;
				if(curRun + prevRun > max){
					max = curRun + prevRun + numWhite;
					if(white)
						max--;
				}
				if(max >= n)
					max = n;
			}

			if(prev == 'w' && cur != 'w'){
				prev = cur;
				curRun++;
			}
			else if(cur == 'w'){
				curRun++;
				numWhite++;
			}
			else if(cur != prev){
				prev = cur;
				if(curRun + prevRun > max){					
					max = curRun + prevRun;
					if(white)
						max--;
				}
				//System.out.println(max);
				prevRun = curRun;
				if(numWhite > 0)
					white = true;
				else
					white = false;
				curRun = 1 + numWhite;
				numWhite = 0;
			}
			//nonwhite with nonwhite
			else{
				curRun++;
				numWhite = 0;
			}
		}

		pw.println(max);
		System.out.println(max);

		br.close();
		pw.close();
	}

}