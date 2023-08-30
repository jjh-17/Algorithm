package a0807;

import java.util.*;
import java.io.*;

public class bj_1158_요세푸스문제 {

	static final StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_1158_요세푸스문제.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//초기화
		int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		for(int i=1;i<=N;i++)
			queue.offerLast(i);
		
		//순회
		sb.append("<");
		while(queue.size()>1) {
			int L = queue.size();
			
			for(int i=0;i<(K-1)%L;i++) queue.offerLast(queue.pollFirst());
			sb.append(queue.pollFirst()).append(", ");
		}

		br.close();
		sb.append(queue.pollFirst()).append(">");
		System.out.println(sb.toString());
	}

}
