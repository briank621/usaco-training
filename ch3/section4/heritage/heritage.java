/*
ID: brian621
LANG: JAVA
TASK: heritage
*/

import java.util.*;
import java.io.*;

public class heritage{
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("heritage.out")));

		String inOrder = br.readLine();
		String preOrder = br.readLine();

		String postOrder = post(inOrder, preOrder);

		System.out.println(postOrder);
		pw.println(postOrder);

		br.close();
		pw.close();
	}

	public static String post(String in, String pre){
		// System.out.println("in: " + in + "\tpre: " + pre);
		if(in.length() <= 1)
			return in;
		char root = pre.charAt(0);
		int rootIndex = 0;
		for(int i = 0; i < in.length(); i++){
			if(in.charAt(i) == root){
				rootIndex = i;
				break;
			}
		}
		String inLeft = in.substring(0, rootIndex);
		String inRight = in.substring(rootIndex + 1);
		String preLeft = pre.substring(1, rootIndex + 1);
		String preRight = pre.substring(rootIndex + 1);
		String out = "";
		out += post(inLeft, preLeft);
		out += post(inRight, preRight);
		out += root;
		return out;
	}

}