package a0810.camp;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class swea_d9_4012_요리사 {
	
	static final StringBuilder sb = new StringBuilder();
	static int N, DIFF; //식재료 수, 두 음식 간 맛의 차이의 최솟값
	static int[] arr1, arr2;
	static boolean[] v;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d9_4012_요리사.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			//식제료 수 입력
			N = Integer.parseInt(br.readLine());
			
			//시너지 입력
			map = new int[N][N];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			//초기화
			DIFF = Integer.MAX_VALUE; //두 음식의 차이
			arr1 = new int[N/2]; arr2 = new int[N/2];
			v = new boolean[N]; //각 음식의 사용 여부(true: 음식1, false: 음식2)
			
			
			comb(0, -1);
			
			//출력문
			sb.append("#").append(t).append(" ").append(DIFF).append("\n");
		}
		br.close();
		System.out.println(sb.toString());
	}

	//N개 재료 중 N/2를 뽑는 조합
	public static void comb(int cnt, int start) {
		if(cnt==N/2) {
			setDiff();
//			System.out.println(Arrays.toString(arr1) + " " + Arrays.toString(arr2));
			return;
		}
		
		for(int i=start+1;i<N;i++) {
			v[i] = true;
			arr1[cnt] = i;
			comb(cnt+1, i);
			v[i] = false;
		}
		
	}
	
	//두 음식의 맛의 차를 업데이트
	public static void setDiff() {
		int cnt=0;
		int sum1=0, sum2 = 0;
		
		//arr2 값 할당
		for(int i=0;i<N;i++) {
			if(!v[i]) arr2[cnt++] = i;
		}
	
		//할당
		for(int i=0;i<N/2;i++) {
			for(int j=0;j<N/2;j++) {
				sum1+=map[arr1[i]][arr1[j]];
				sum2+=map[arr2[i]][arr2[j]];
			}
		}
		
//		System.out.print(sum1 + " " + sum2 + " ");
		DIFF = Integer.min(DIFF, Math.abs(sum1-sum2));
	}
}
