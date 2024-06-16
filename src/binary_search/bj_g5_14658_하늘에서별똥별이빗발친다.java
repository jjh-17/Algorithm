package binary_search;

import java.util.*;
import java.io.*;

public class bj_g5_14658_하늘에서별똥별이빗발친다 {

	static int ans;
	static int N, M, L, K;
	static Cord[] cords;
	static class Cord {
		int x;
		int y;
		
		Cord(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res\\input_bj_g5_14658_하늘에서별똥별이빗발친다.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		cords = new Cord[K];
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			cords[i] = new Cord(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
//		알고리즘
		solution();
		
//		출력
		System.out.println(ans);
		br.close();
	}
	
	static void solution() {
		
//		2. 두 좌표의 x, y 조합 순회
		ans = K+1;
		for(Cord cord1 : cords) {
			for(Cord cord2 : cords) {
				int cnt = 0;
				for(Cord cord3 : cords) {
					if(cord1.x <= cord3.x && cord3.x <= cord1.x + L && cord2.y <= cord3.y && cord3.y <= cord2.y + L)
						++cnt;
				}
				ans = Integer.min(ans, K - cnt);
			}
		}
	}
}
