/*
ID: brian621
LANG: JAVA
TASK: lgame
*/

import java.util.*;
import java.io.*;

public class lgame{
	
	static String word;
	static int[] val = new int[]{2, 5, 4, 4, 1, 6, 5, 5, 1, 7, 6, 3, 5, 2, 3, 5, 7, 2, 1, 2, 4, 6, 6, 7, 5, 7};
	static HashMap<String, ArrayList<String>> d = new HashMap<String, ArrayList<String>>();
	static HashMap<String, Integer> out;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("lgame.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lgame.out")));

		BufferedReader dr = new BufferedReader(new FileReader("lgame.dict"));
		String line;
		while((line = dr.readLine()) != null){
			char[] sorted = line.toCharArray(); 
			Arrays.sort(sorted);
			String toAdd = new String(sorted);
			if(d.containsKey(toAdd)){
				ArrayList<String> l = d.get(toAdd);
				l.add(line);
				d.put(toAdd, l);
			}
			else{
				ArrayList<String> l = new ArrayList<String>();
				l.add(line);
				d.put(toAdd, l);
			}
		}

		word = br.readLine();

		findSubsets();

		ArrayList<String> valid = new ArrayList<String>();

		int max = 0;
		// System.out.println("out: " + out);
		// System.out.println("d: " + d);

		for(Map.Entry<String, Integer> entry : out.entrySet()){
			if(entry.getValue() == 0)
				continue;
			if(entry.getValue() >= max){
				if(entry.getValue() > max){
					max = entry.getValue();
					valid.clear();
				}
				String w = entry.getKey();
				// System.out.println("w: " + w);
				if(w.contains(" ")){
					String[] list = w.split(" ");
					String left = list[0];
					String right = list[1];
					// System.out.println("left: " + left);
					// System.out.println("right: " + right);
					ArrayList<String> lWords = d.get(left);
					ArrayList<String> rWords = d.get(right);
					for(int i = 0; i < lWords.size(); i++){
						for(int j = 0; j < rWords.size(); j++){
							if(lWords.get(i).compareTo(rWords.get(j)) > 0)
								continue;
							valid.add(lWords.get(i) + " " + rWords.get(j));
						}
					}
				}
				else{
					ArrayList<String> words = d.get(w);
					for(String toAdd: words)
						valid.add(toAdd);
				}
			}
		}

		Collections.sort(valid);
		String ans = "";
		ans += max + "\n";
		for(int i = 0; i < valid.size(); i++)
			ans += valid.get(i) + "\n";

		System.out.print(ans);
		pw.print(ans);

		br.close();
		pw.close();
	}

	public static void findSubsets(){
		Queue<List<String>> q = new LinkedList<List<String>>();
		//currently chosen, next index
		q.offer(Arrays.asList("", "", word));
		out = new HashMap<String, Integer>();

		while(q.size() > 0){
			List<String> cur = q.poll();
			String left = cur.get(0);
			String right = cur.get(1);
			String remain = cur.get(2);

			if(remain.equals("")){
				// System.out.println("left: " + left);
				// System.out.println("right: " + right);
				int v = 0;
				char[] c = left.toCharArray();
				Arrays.sort(c);
				left = new String(c);
				if(d.containsKey(left)){
					for(int i = 0; i < left.length(); i++)
						v += val[c[i] - 'a'];
				}
				out.put(left, v);

				char[] r = right.toCharArray();
				Arrays.sort(r);
				right = new String(r);
				int rVal = 0;
				String max = null;
				if(v != 0 && r.length > 0){
					if(right.length() <= 3){
						if(d.containsKey(right)){
							for(int i = 0; i < right.length(); i++){
								rVal += val[r[i] - 'a'];
							}
							max = right;
						}
						if(max != null){
							// System.out.println("WOW IM CONTAINED");
							// System.out.println("left: " + left);
							// System.out.println("right: " + max);
							// System.out.println("val: " + (v + rVal));
							out.put(left + " " + max, v + rVal);
						}
					}
					if(right.length() == 4){
						if(d.containsKey(right)){
							for(int i = 0; i < right.length(); i++){
								rVal += val[r[i] - 'a'];
							}
							max = right;
						}
						for(int i = 0; i < right.length(); i++){
							int curVal = 0;
							String sub = right.substring(0, i) + right.substring(i+1);
							if(d.containsKey(sub)){
								for(int j = 0; j < sub.length(); j++){
									curVal += val[sub.charAt(j) - 'a'];
								}
							}
							if(curVal > rVal){
								rVal = curVal;
								max = sub;
							}
						}
						if(max != null){
							// System.out.println("WOW IM CONTAINED");
							// System.out.println("left: " + left);
							// System.out.println("right: " + max);
							// System.out.println("val: " + (v + rVal));
							out.put(left + " " + max, v + rVal);
						}
					}
				}

				continue;
			}

			q.offer(Arrays.asList(left + remain.charAt(0), right, remain.substring(1)));
			q.offer(Arrays.asList(left, right + remain.charAt(0), remain.substring(1)));
		}
	}

}