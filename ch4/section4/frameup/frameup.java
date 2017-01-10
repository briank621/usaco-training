/*
ID: brian621
LANG: JAVA
TASK: frameup
*/

import java.util.*;
import java.io.*;

public class frameup{
	
	static int w, h;
	static char[][] board;
	static ArrayList<String> out = new ArrayList<String>();
	static String alph;
	static HashMap<Character, HashSet<Character>> owns = new HashMap<Character, HashSet<Character>>();
	static HashMap<Character, HashSet<Character>> needs = new HashMap<Character, HashSet<Character>>();
	static int[][] corners = new int[26][4];

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("frameup.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("frameup.out")));

		String[] line = br.readLine().trim().split(" ");
		h = Integer.parseInt(line[0]);
		w = Integer.parseInt(line[1]);

		board = new char[h][w];
		for(int i = 0; i < h; i++)
			board[i] = br.readLine().trim().toCharArray();
		
		HashSet<Character> seen = new HashSet<Character>();
		alph = "";
		for(int i = 0; i < 26; i++){
			char c = (char)(i + 'A');
			int minX = 100;
			int minY = 100;
			int maxX = -1;
			int maxY = -1;
			for(int j = 0; j < h; j++){
				for(int k = 0; k < w; k++){
					if(board[j][k] == c){
						minX = Math.min(minX, j);
						minY = Math.min(minY, k);
						maxX = Math.max(maxX, j);
						maxY = Math.max(maxY, k);
					}
				}
			}
			corners[i][0] = minX;
			corners[i][1] = maxX;
			corners[i][2] = minY;
			corners[i][3] = maxY;
			if(minX != 100)
				alph += c;
		}

		for(int i = 0; i < alph.length(); i++){
			char c = alph.charAt(i);
			char[][] copy = new char[h][w];
			for(int a = 0; a < h; a++){
				for(int j = 0; j < w; j++)
					copy[a][j] = board[a][j];
			}

			HashSet<Character> need = loops(copy, corners[c - 'A'], c);
			needs.put(c, need);
		}

		topSort(new StringBuilder(), new boolean[26]);

		Collections.sort(out);
		StringBuilder ordered = new StringBuilder();
		for(int i = 0; i < out.size(); i++)
			ordered.append(out.get(i) + "\n");

		System.out.print(ordered.toString());
		pw.print(ordered.toString());

		br.close();
		pw.close();
	}

	public static void topSort(StringBuilder cur, boolean[] seen){
		if(cur.length() == alph.length()){
			out.add(cur.toString());
			return;
		}
		for(int i = 0; i < 26; i++){
			if(seen[i] || corners[i][0] == 100)
				continue;
			boolean toAdd = true;
			char ci = (char)(i + 'A');
			for(int j = 0; j < 26; j++){
				char cj = (char)(j + 'A');
				if(!seen[j] && needs.get(ci).contains(cj)){
					toAdd = false;
					break;
				}
			}
			if(toAdd){
				seen[i] = true;
				cur.insert(0,ci);
				topSort(cur, seen);
				cur.deleteCharAt(0);
				seen[i] = false;
			}
		}

	}

	public static HashSet<Character> loops(char[][] b, int[] start, char c){
		int minX = start[0];
		int maxX = start[1];
		int minY = start[2];
		int maxY = start[3];

		HashSet<Character> out = new HashSet<Character>();

		for(int i = minX; i <= maxX; i++){
			if(b[i][minY] != c && b[i][minY] != '*')
				out.add(b[i][minY]);
			if(b[i][maxY] != c && b[i][maxY] != '*')
				out.add(b[i][maxY]);
		}

		for(int i = minY; i <= maxY; i++){
			if(b[minX][i] != c && b[minX][i] != '*')
				out.add(b[minX][i]);
			if(b[maxX][i] != c && b[maxX][i] != '*')
				out.add(b[maxX][i]);
		}

		return out;
	}

}