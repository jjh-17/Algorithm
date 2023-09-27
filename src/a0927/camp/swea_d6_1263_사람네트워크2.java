package a0927.camp;

import java.util.*;
import java.io.*;

public class swea_d6_1263_사람네트워크2 {

	static final StringBuilder sb = new StringBuilder();
	static int N;
	static class Node{
		int vertex;
		Node link;
		public Node(int vertex, Node link) {
			this.vertex = vertex;
			this.link = link;
		}
	}
	static Node G[];
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d6_1263_사람네트워크2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	
//		입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		
//		출력
		System.out.println(sb.toString());
		br.close();
	}
}
