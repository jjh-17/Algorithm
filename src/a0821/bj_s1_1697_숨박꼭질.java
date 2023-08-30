package a0821;

import java.util.*;
import java.io.*;
import java.lang.Math;


public class bj_s1_1697_숨박꼭질 {

	static final StringBuilder sb = new StringBuilder();
	static int N, K, lim;
	static int ans;
	static boolean[] v;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s1_1697_숨박꼭질.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); K = Integer.parseInt(st.nextToken());
		lim = Math.abs(K-N); //1칸 씩 이동했을 때의 시간 ==> 즉, 최대 시간
		
		ans = 0;
		v = new boolean[100001];
		bfs();
		
		br.close();
		System.out.println(sb.toString());
	}
	
	static void bfs() {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		queue.offerLast(N);
		v[N] = true;
		
		while(!queue.isEmpty()) {
			int L = queue.size();
			for(int i=0;i<L;i++) {
				int n = queue.pollFirst();
				
				//K에 도달하면 종료
				if(n==K) {
					sb.append(ans);
					return;
				}
				
				//각 숫자는 최초 1회만 들러야 한다. ==> 동일한 숫자에 대한 중복 접근을 막는다.
				if(2*n<=100_000 && !v[2*n] && n!=0) {
					queue.offerLast(2*n);
					v[2*n] = true;
				}
				if(n+1<=100_000 && !v[n+1]) {
					queue.offerLast(n+1);
					v[n+1] = true;
				}
				if(n-1>=0 && !v[n-1]) {
					queue.offerLast(n-1);		
					v[n-1] = true;
				}
			}
			++ans;
		}
	}
}
