/*
ID: brian621
LANG: JAVA
TASK: sort3
*/

import java.util.*;
import java.io.*;

public class sort3{

	static int[] l;
	static int[] nums;
	static int n;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("sort3.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));

		n = Integer.parseInt(br.readLine());
		l = new int[n];
		nums = new int[4];
		for(int i = 0; i < n; i++){
			int num = Integer.parseInt(br.readLine());
			nums[num]++;
			l[i] = num;
		}

		int min = findMin();
		System.out.println(min);
		pw.println(min);

		br.close();
		pw.close();
	}

	public static int findMin(){
		int swaps = 0;
		ArrayList<Integer> nons = new ArrayList<Integer>();
		for(int i = 0; i < nums[1]; i++){
			if(l[i] != 1)
				nons.add(l[i]);
		}
		Collections.sort(nons);
		for(int i = nums[1]; i < n; i++){
			if(l[i] == 1){
				l[i] = nons.remove(0);
				swaps++;
			}
		}
		for(int i = nums[1]; i < nums[1] + nums[2]; i++){
			if(l[i] != 2)
				swaps++;
		}
		return swaps;
	}

}