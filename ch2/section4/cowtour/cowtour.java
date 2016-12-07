/*
ID: brian621
LANG: JAVA
TASK: cowtour
*/

import java.util.*;
import java.io.*;

public class cowtour{
	
	static int n;
	static double myMax = Math.pow(2, 200);
	static ArrayList<ArrayList<Integer>> pts = new ArrayList<ArrayList<Integer>>();
	static ArrayList<ArrayList<Integer>> scc = new ArrayList<ArrayList<Integer>>();
	static int[][] e;
	static double[][] dist;
	static int[] seen;
	static double[] diam;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("cowtour.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));

		n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++){
			ArrayList<Integer> p = new ArrayList<Integer>();
			String[] line = br.readLine().split(" ");
			p.add(Integer.parseInt(line[0]));
			p.add(Integer.parseInt(line[1]));
			pts.add(p);
		}

		// System.out.println("pts: " + pts);

		e = new int[n][n];
		seen = new int[n];
		dist = new double[n][n];

		for(int i = 0; i < n; i++){
			String line = br.readLine();
			for(int j = 0; j < n; j++)
				e[i][j] = line.charAt(j) - '0';
		}

		// print(e);

		for(int i = 0; i < n; i++){
			if(seen[i] == 0){
				ArrayList<Integer> comp = dfs(i);
				scc.add(comp);
			}
		}

		// System.out.println("scc: " + scc);

		calculateWeights();
		double[][] norm = shortestPairs(dist);
		double[] maxNorm = findMax(norm);
		diam = new double[scc.size()];

		for(int i = 0; i < scc.size(); i++){
			double d = 0;
			for(int j: scc.get(i)){
				if(maxNorm[j] > d)
					d = maxNorm[j];
			}
			diam[i] = d;
		}

		// if(n > 104)
			// System.out.println("maxNormy : " + norm[104]);
			// norm[104];

		// System.out.println("maxNorm: " + Arrays.toString(maxNorm));

		double min = myMax;
		for(int i = 0; i < scc.size(); i++){
			for(int j = 0; j < scc.size(); j++){
				if(i == j)
					continue;
				for(int x: scc.get(i)){
					for(int y: scc.get(j)){
						double di = maxNorm[x];
						double dj = maxNorm[y];
						double sum = di + dj + calculateDistance(x, y);
						double diams = Math.max(diam[i], diam[j]);
						double total = Math.max(diams, sum);
						if(total < min){
							min = total;
						}
					}
				}
			}
		}

		System.out.printf("%.6f\n", min);
		pw.printf("%.6f\n", min);

		br.close();
		pw.close();
	}

	public static void print(double[][] d){
		for(int i = 0; i < d.length; i++){
			for(int j = 0; j < d[0].length; j++)
				System.out.printf("%.2f ", d[i][j]);
			System.out.println("");
		}
	}

	public static void print(int[][] d){
		for(int i = 0; i < d.length; i++){
			for(int j = 0; j < d[0].length; j++)
				System.out.printf("%d ", d[i][j]);
			System.out.println("");
		}
	}

	public static ArrayList<Integer> dfs(int i){
		Stack<Integer> s = new Stack<Integer>();
		ArrayList<Integer> out = new ArrayList<Integer>();
		s.push(i);

		while(! s.empty()){
			int cur = s.pop();
			if(seen[cur] == 1)
				continue;
			seen[cur] = 1;
			out.add(cur);
			for(int j = 0; j < n; j++){
				if(e[cur][j] == 1)
					s.push(j);
			}
		}

		return out;
	}

	public static double[] findMax(double[][] d){
		double[] out = new double[n];
		for(int i = 0; i < n; i++){
			double max = 0;
			for(int j = 0; j < n; j++){
				if(d[i][j] >= myMax-1)
					continue;
				if(d[i][j] > max)
					max = d[i][j];
			}
			out[i] = max;
		}
		return out;
	}

	public static double[][] shortestPairs(double[][] d){
		double[][] out = new double[n][n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++)
				out[i][j] = d[i][j];
		}

		for(int k = 0; k < n; k++){
			for(int i = 0; i < n; i++){
				for(int j = 0; j < n; j++){
					if(out[i][k] + out[k][j] < out[i][j])
						out[i][j] = out[i][k] + out[k][j];
				}
			}
		}
		return out;
	}

	public static double calculateDistance(int i, int j){
		ArrayList<Integer> p1 = pts.get(i);
		ArrayList<Integer> p2 = pts.get(j);
		double x2 = Math.pow(p1.get(0) - p2.get(0), 2);
		double y2 = Math.pow(p1.get(1) - p2.get(1), 2);
		return Math.sqrt(x2 + y2);
	}

	public static void calculateWeights(){
		for(int i = 0; i < n; i++){
			for(int j = i+1; j < n; j++){
				if(e[i][j] == 1){
					dist[i][j] = calculateDistance(i, j);
					dist[j][i] = calculateDistance(i, j);
				}
				else{
					dist[i][j] = myMax;
					dist[j][i] = myMax;
				}
			}
		}
	}

}