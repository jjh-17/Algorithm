package stack_queue;

import java.util.*;
import java.io.*;

public class bj_g4_1863_스카이라인쉬운거 {

	static class Cord {
		int x, y;
		public Cord(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int N, ans;
	static Cord[] cords;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_1863_스카이라인쉬운거.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		N = Integer.parseInt(br.readLine());
		
		cords = new Cord[N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			cords[i] = new Cord(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
//		알고리즘
		ans=0;
		solution();
		
//		출력
		System.out.println(ans);
		br.close();
	}

	static void solution() {
		final ArrayDeque<Cord> stack = new ArrayDeque<>();
		
//		고도 변환 좌표 배열 순회
		for(Cord cord : cords) {
			int idx=0;
			for(Cord s : stack) {
//				현재 빌딩 높이가 새로운 높이 이상인 경우
				if(s.y <= cord.y)	{
					++idx;
					continue;
				}
				
//				현재 빌딩 높이가 새로운 높이 이상인 경우
				int size = stack.size();
				for(int i=idx;i<size;i++)	stack.pollLast();
				ans += (size-idx);
				break;
			}
			
//			stack이 비었고 높이가 0 초과인 경우 || stack이 비어있지 않고 마지막 값 높이가 더 작은 경우
			if((stack.isEmpty() && 0<cord.y) || (!stack.isEmpty() && stack.peekLast().y<cord.y))
				stack.offerLast(new Cord(cord.x, cord.y));
		}
		
		ans += stack.size();
	}
	
}
