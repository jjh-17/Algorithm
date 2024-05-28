package greedy;

import java.util.*;
import java.io.*;

public class bj_g4_1715 {

	static int N, ans;
	static final PriorityQueue<Integer> pq = new PriorityQueue<Integer>((p1, p2) -> p1.compareTo(p2));
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_1715.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

//		입력
		N = Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++)	pq.add(Integer.parseInt(br.readLine()));
		
//		알고리즘
		while(pq.size()>=2) {
			int sum = pq.poll() + pq.poll();
			
			pq.add(sum);
			ans += sum;
		}
		
//		출력
		System.out.println(ans);
		br.close();
	}
}
