/*
ID: brian621
LANG: JAVA
TASK: testsuite
*/

import java.util.*;
import java.io.*;

public class testsuite{
	
	public static void main(String[] args) throws Exception{
		String fileName = args[0];

		PrintWriter pw = new PrintWriter(new FileWriter(args[0] + "/" + args[0]));
		BufferedReader br = new BufferedReader(
							new FileReader(args[0] + "/" + args[0] + ".test"));
		int n = Integer.parseInt(args[1]);
		br.readLine();
		String line = "";
		for(int i = 1; i <= n; i++){
			String name = args[0] + "/" +  i + ".in";
			pw = new PrintWriter(new BufferedWriter(new FileWriter(name)));
			while((line = br.readLine()) != null){
				if(line.startsWith("-")){
					pw.close();
					break;
				}
				pw.println(line);
			}
		}

		pw.close();
		br.close();

	}

}