package a0804;


import java.io.*;
import java.util.ArrayDeque;

public class bj_2164_카드2_서울_20반_지준호 {
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_2164_카드2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		
		for(int i=1;i<=N;i++)
			deque.addLast(i);
		
		
		while(deque.size() > 1) {
			deque.pollFirst();
			deque.addLast(deque.pollFirst());	
		}
		
		System.out.println(deque.pollLast());
	}

}
