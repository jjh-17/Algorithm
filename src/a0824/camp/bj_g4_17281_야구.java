package a0824.camp;

import java.util.*;
import java.io.*;

//해결 중
public class bj_g4_17281_야구 {

	static final StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] info; //각 이닝 별 선수들의 결과
	static int[] map; //타순 별 선수 번호
	static boolean[] v; //순열을 위한 visited
	static int max; //점수 최댓값
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_17281_야구.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		//입력
		N = Integer.parseInt(br.readLine());
		info = new int[N][9]; 
		map = new int[9];
		v = new boolean[9];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<9;j++) info[i][j] = Integer.parseInt(st.nextToken());
		}
		
		
		//각 타순은 변경 불가
		v[3] = true;
		map[3] = 0;
		max = Integer.MIN_VALUE;
		
		perm(1); //
		
		sb.append(max);
		System.out.println(sb.toString());
		br.close();
	}
	
	static void perm(int cnt) {
		if(cnt==9) {
			game();
			return;
		}
		for(int i=0;i<9;i++) {
			if(v[i]) continue;
			v[i] = true; map[i] = cnt;
			perm(cnt+1);
			v[i] = false;
		}
	}
	
	//타순 기반 각 이닝 수행
	static void game() {
		boolean[] ru;
		int start = 0; //시작 타순
		int out=0; //현재 아웃 횟수
		int sum=0; //점수 합
		
		//이닝 순회
		for(int n=0;n<N;n++) {
			//매 이닝 마다 각루 초기화
			ru = new boolean[4];
			
			NEXT_INNING:
			while(true) { //현재 이닝 반복
				for(int i=start;i<9;i++) {
					int result = info[n][map[i]];
					if(result==0) { //아웃
						if(++out==3) { //3아웃
							start = (i+1)%9;
							out=0;
							break NEXT_INNING;
						}
					} else { //안타 2루타 3루타 홈런
						ru[0] = true;
						for(int r=3;r>=0;r--) {
							if(ru[r]) {
								if(r+result<=3) ru[r+result]=true;
								else ++sum;
								ru[r] = false;
							}
							
						}
					}
				}
				
				start=0;
			}
		}
		
		max = Integer.max(max, sum);
	}
}