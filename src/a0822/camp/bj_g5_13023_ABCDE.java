package a0822.camp;

import java.util.*;
import java.io.*;

//해결 중
public class bj_g5_13023_ABCDE {

	static final StringBuilder sb = new StringBuilder();
	static List<Integer>[] map;
	static int N, M, ans;
	static int a, b;
	static boolean[] v;
	static boolean flag;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g5_13023_ABCDE.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new List[N];
		for(int i=0;i<N;i++) map[i] = new ArrayList<>();
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			map[a].add(b); map[b].add(a);
		}
		
		for(int i=0;i<N;i++) System.out.println(map[i]);
		
		for(int i=0;i<N;i++) {
			v = new boolean[N];
			flag = false;
			dfs(i, 0);
			if(flag) break;
		}
		
		sb.append(flag ? 1 : 0);
		System.out.println(sb.toString());
		br.close();
	}
	
	static void dfs(int a, int cnt) {
		if(flag) return;
		
		if(cnt==N) {
			System.out.println(Arrays.toString(v));
			flag = true;
			return;
		}
		
		v[a] = true;
		for(int m : map[a]) {
			if(v[m]) continue;
			System.out.println(Arrays.toString(v));
			dfs(m, cnt+1);
		}
		v[a] = false;
	}
	
	
}
