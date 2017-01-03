/*
ID: brian621
LANG: JAVA
TASK: job
*/

import java.util.*;
import java.io.*;

public class job{

	static int[] a;
	static int[] b;
	static int n, m1, m2;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("job.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("job.out")));

		String[] line = br.readLine().split(" ");
		n = Integer.parseInt(line[0]);
		m1 = Integer.parseInt(line[1]);		
		m2 = Integer.parseInt(line[2]);		

		a = new int[m1];
		b = new int[m2];


		int count = 0;
		String l;
		while((l = br.readLine()) != null){
			StringTokenizer st = new StringTokenizer(l);
			while(st.hasMoreTokens()){
				if(count<m1)
					a[count] = Integer.parseInt(st.nextToken());
				else
					b[count - m1] = Integer.parseInt(st.nextToken());
				count++;
			}
		}
		
		Arrays.sort(a);
		Arrays.sort(b);

		int[] aTimes = compute(a);
		int[] bTimes = compute(b);
		int max = 0;

		for(int i = 0; i < n; i++){
			max = Math.max(max, aTimes[i] + bTimes[n - 1 - i]);
		}

		// System.out.println("a " + Arrays.toString(aTimes));
		// System.out.println("b " + Arrays.toString(bTimes));

		pw.println(aTimes[n-1] + " " + max);
		System.out.println(aTimes[n-1] + " " + max);

		br.close();
		pw.close();
	}

	public static int[] compute(int[] p){
		PriorityQueue<Integer> qa = new PriorityQueue<Integer>();
		ArrayList<List<Integer>> activeA = new ArrayList<List<Integer>>();

		int time = 0;
		int acount = 0;
		int atime = 0;
		int[] out = new int[n];

		for(int i = 0; i < p.length; i++)
			qa.offer(i);

		outer:
		while(true){
			time++;
			// System.out.println("activeB: " + activeB);
			while(qa.size() > 0){
				int i = qa.poll();
				activeA.add(Arrays.asList(i, p[i]));				
			}
			// System.out.println("q: " + qa);
			// System.out.println("activeA: " + activeA);

			for(int i = 0; i < activeA.size(); i++){
				List<Integer> cur = activeA.get(i);
				int ind = cur.get(0);
				int t = cur.get(1);
				t--;
				if(t == 0){
					qa.offer(ind);
					activeA.remove(i);
					out[acount] = time;
					acount++;
					if(acount == n){
						atime = time;
						break outer;
					}
					i--;
				}
				else
					activeA.set(i, Arrays.asList(ind, t));
			}

		}

		return out;

	}

}