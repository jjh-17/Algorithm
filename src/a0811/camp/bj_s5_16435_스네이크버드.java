package a0811.camp;

import java.util.*;
import java.io.*;

public class bj_s5_16435_스네이크버드 {

	static int N, L;
	static final PriorityQueue<Integer> heap = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s5_16435_스네이크버드.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); L = Integer.parseInt(st.nextToken());
	
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) heap.add(Integer.parseInt(st.nextToken()));
		
		while(!heap.isEmpty()) {
			if(heap.poll() <= L) L++;
			else break;
		}
		
		System.out.println(L);
	}

}
