package a0809;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class bj_s1_11286_절댓값힙 {
	
	static final StringBuilder sb = new StringBuilder();
	static final PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) 
			-> Math.abs(o1)==Math.abs(o2) ? Integer.compare(o1, o2) 
										  : Integer.compare(Math.abs(o1), Math.abs(o2)));
	static int N;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s1_11286_절댓값힙.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++) {
			int temp = Integer.parseInt(br.readLine());
			
			if(temp!=0) heap.add(temp); //heap에 추가
			else {
				if(heap.isEmpty()) sb.append(0).append("\n");
				else sb.append(heap.poll()).append("\n");
			}
		}
		br.close();
		System.out.println(sb.toString());
	}

}
