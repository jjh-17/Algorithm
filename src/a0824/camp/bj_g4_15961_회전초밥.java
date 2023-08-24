package a0824.camp;

import java.util.*;
import java.io.*;

public class bj_g4_15961_회전초밥 {

	static final StringBuilder sb = new StringBuilder();
	static int N, D, K, C; //벨트에 놓인 접시의 수, 초밥의 가짓수, 연속해서 먹는 접시의 수, 쿠폰 번호
	static int[] list; //음식 순서대로 저장
	static int[] cnt; //각 음식을 몇개 먹었는가
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_15961_회전초밥.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		list = new int[N];
		cnt = new int[D];
		for(int i=0;i<N;i++) list[i] = Integer.parseInt(br.readLine())-1;
			
		//알고리즘
		sliding();
		
		
		System.out.println(sb.toString());
		br.close();
	}
	
	//슬라이딩 시작
	static void sliding() {
		int max = Integer.MIN_VALUE;
		int temp = 0;
		
		//0~K-1번 접시에 대하여 max 구하기
		for(int i=0;i<K;i++) {
			if(cnt[list[i]]==0) ++temp;
			++cnt[list[i]];
		}
		
		max = Integer.max(max, temp + (cnt[C-1]==0 ? 1 : 0));
		if(max==K+1) {
			sb.append(max);
			return;
		}
		
		//1번째 접시부터 max 최신화
		for(int start=1;start<N;start++) {
			//시작점 제거
			if(cnt[list[start-1]]==1) --temp;
			--cnt[list[start-1]];
			
			//끝점 추가
			int end = (start+K-1)%N;
			if(cnt[list[end]]==0) ++temp;
			++cnt[list[end]];
		
			max = Integer.max(max, temp + (cnt[C-1]==0 ? 1 : 0));
			if(max==K+1) break;
		}
		
		
		sb.append(max);
	}
}
