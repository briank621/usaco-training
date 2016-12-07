/*
ID: brian621
LANG: JAVA
TASK: runround
*/

import java.util.*;
import java.io.*;

public class runround{
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));

		int n = Integer.parseInt(br.readLine()) + 1;
		while(true){
			// System.out.println("n: " + n);
			Node l = Node.create(n);
			if(l.confirm()){
				System.out.println(n);
				pw.println(n);
				break;
			}
			n++;
		}

		br.close();
		pw.close();
	}

	public static class Node{

		public int v;
		public Node next;
		public int length;

		public Node(int n, Node next){
			this.v = n;
			this.next = next;
		}

		public String toString(){
			String ans = "";
			Node cur = this;
			ans += cur.v + "->";
			cur = cur.next;
			while(cur != this){
				ans += cur.v + "->";
				cur = cur.next;
			}
			return ans;
		}

		public boolean confirm(){
			HashSet<Node> seen = new HashSet<Node>();
			HashSet<Integer> dig = new HashSet<Integer>();
			Node cur = this;
			int move = cur.v;
			dig.add(cur.v);
			seen.add(cur);
			while(true){
				for(int i = 0; i < move; i++)
					cur = cur.next;
				if(dig.contains(cur.v))
					break;
				if(seen.contains(cur))
					break;
				seen.add(cur);
				dig.add(cur.v);
				// System.out.println("cur: " + cur.v);
				move = cur.v;
			}
			// System.out.println("seen: " + seen);
			// System.out.println("my length: " + this.length);
			// System.out.println("self: " + this);
			// System.out.println("cur: " + cur);
			return (seen.size() == this.length && cur == this);
		}

		public static Node create(int x){
			int l = 1;
			Node tail = new Node(x%10, null);
			tail.next = tail;
			Node cur = tail;
			x/=10;
			Node head = tail;
			while(x > 0){
				head = new Node(x%10, cur);
				tail.next = head;
				cur = head;	
				x /= 10;
				l++;
			}
			head.length = l;
			return head;
		}


	}

}

