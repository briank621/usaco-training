/*
ID: brian621
LANG: JAVA
TASK: shopping
*/

import java.util.*;
import java.io.*;

public class shopping{

	static Queue<ArrayList<ArrayList<Integer>>> q = new LinkedList<ArrayList<ArrayList<Integer>>>();
	static HashMap<Integer, Integer> codes = new HashMap<Integer, Integer>();
	static int[][][][][] dp  = new int[6][6][6][6][6];
	static int[] amounts;
	static int[] prices;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shopping.out")));

		int s = Integer.parseInt(br.readLine());
		for(int i = 0; i < s; i++){
			String[] line = br.readLine().split(" ");
			int n = Integer.parseInt(line[0]);
			ArrayList<ArrayList<Integer>> offer = new ArrayList<ArrayList<Integer>>();
			for(int j = 0; j < n; j++){
				offer.add(new ArrayList<Integer>());
				offer.get(j).add(Integer.parseInt(line[2 * j + 1]));
				offer.get(j).add(Integer.parseInt(line[2 * j + 2]));
			}
			offer.add(new ArrayList<Integer>());
			offer.get(n).add(Integer.parseInt(line[2 * n + 1]));
			q.offer(offer);
		}

		int b = Integer.parseInt(br.readLine());
		amounts = new int[5];
		prices = new int[5];

		for(int i = 0; i < b; i++){
			String[] line = br.readLine().split(" ");
			codes.put(Integer.parseInt(line[0]), i);
			amounts[i] = Integer.parseInt(line[1]);
			prices[i] = Integer.parseInt(line[2]);
		}

		initialize();

		while(q.size() > 0){
			ArrayList<ArrayList<Integer>> offer = q.poll();
			run(offer);
		}

		System.out.println(returnVal(dp, amounts));
		pw.println(returnVal(dp, amounts));

		br.close();
		pw.close();
	}

	public static int returnVal(int[][][][][] d, int[] i){
		return d[i[0]][i[1]][i[2]][i[3]][i[4]];
	}

	public static boolean hasNext(int[] d){
		for(int i = 0; i < d.length; i++){
			if(d[i] > 5)
				return false;
		}
		return true;
	}

	public static boolean isValid(int i, int j, int k, int l, int m, int[] d){
		boolean ans = true;
		ans &= (i >= d[0]);
		ans &= (j >= d[1]);
		ans &= (k >= d[2]);
		ans &= (l >= d[3]);
		ans &= (m >= d[4]);
		return ans;
	}

	public static void run(ArrayList<ArrayList<Integer>> offer){
		int[] ind = new int[5];
		// System.out.println("codes: " + codes);
		// System.out.println("offer: " + offer);
		for(int i = 0; i < offer.size() - 1; i++){
			int item = offer.get(i).get(0);
			int amt = offer.get(i).get(1);
			// System.out.println("codes: " + codes.get(item));
			ind[codes.get(item)] = amt;
		}
		int p = offer.get(offer.size() - 1).get(0);
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 6; j++){
				for(int k = 0; k < 6; k++){
					for(int m = 0; m < 6; m++){
						for(int l = 0; l < 6; l++){
							if(isValid(i, j, k, l, m, ind)){
								int orig = dp[i][j][k][l][m];
								int prev = dp[i - ind[0]][j - ind[1]][k - ind[2]][l - ind[3]][m - ind[4]];
								prev += p;
								dp[i][j][k][l][m] = Math.min(prev, orig);
							}
						}
					}
				}
			}
		}
	}

	public static void initialize(){
		for(int i = 0; i < 6; i++){
			for(int j = 0; j < 6; j++){
				for(int k = 0; k < 6; k++){
					for(int m = 0; m < 6; m++){
						for(int l = 0; l < 6; l++){
							int cost = 0;
							cost += i * prices[0];
							cost += j * prices[1];
							cost += k * prices[2];
							cost += l * prices[3];
							cost += m * prices[4];
							dp[i][j][k][l][m] = cost;
						}
					}
				}
			}
		}
	}

}