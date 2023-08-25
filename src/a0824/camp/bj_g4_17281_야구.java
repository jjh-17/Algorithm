package a0824.camp;

import java.util.*;
import java.io.*;

//해결 중
public class bj_g4_17281_야구 {

	static final StringBuilder sb = new StringBuilder();
	static final int RU = 4, NUM = 9;
	static int N;
	static int[][] info; //각 이닝 별 선수들의 결과
	static int[][] map; //선수들의 임의의 타순 저장
	static boolean[][] v; //순열을 위한 visited
	static boolean[] ru; //현재 각 루의 주자 위치 여부
	static int max; //점수 최댓값
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g4_17281_야구.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		//입력
		N = Integer.parseInt(br.readLine());
		info = new int[N][NUM]; 
		map = new int[N][NUM];
		v = new boolean[N][NUM];
		ru = new boolean[RU]; //타석, 1~3루
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<NUM;j++) info[i][j] = Integer.parseInt(st.nextToken());
		}
		
		
		//game
		v[0][3] = true;
		map[0][3] = info[0][0];
		max = Integer.MIN_VALUE;
		
		perm(0, 1, 0, 0, 0);
		
		sb.append(max);
		System.out.println(sb.toString());
		br.close();
	}
	
	//현재 이닝, 현재 고려 수, 시작 선수 순서
	static void perm(int in, int cnt, int start, int out, int sum) {
		if(cnt==N) {
			game(in, start, out, sum);
			return;
		}
		
		for(int i=0;i<N;i++) {
			if(v[in][i]) continue;
			v[in][i] = true; map[in][i] = info[in][i];
			perm(in, cnt+1, start, out, sum);
			v[in][i] = false;
		}
	}
	
	//현재 이닝, 현재 시작 선수 순서, 현재 아웃 누적 수
	static void game(int in, int start, int out, int sum) {
		int cout = out; //아웃 수
		int csum=0; //누적 점수
		
		for(int i=start;i<NUM;i++) {
			switch(map[in][i]) {
			case 0: //아웃
				++cout;
				break;
			case 4: //홈런
				for(int r=0;r<RU;r++) {
					if(ru[r]) ++csum;
				}
				break;
			default: //안타, 2*3루타
				for(int r=RU-1;r>=0;r--) {
					if(ru[r]) {
						if(r+map[in][i] >= RU) ++csum;
						else ru[r+map[in][i]] = ru[r];
					}
				}
			}
			
			//아웃 누적 3회
			if(cout==3) {
				if(in==N-1) { //마지막 이닝임
					max = Integer.max(max, sum+csum);
				} else {
					v[in+1][3] = true;
					map[in+1][3] = info[in+1][0];
					perm(in+1, 0, (i+1)%NUM, 0, sum+csum);
					return;
				}
			}
		}
		
		if(in==N-1) { //마지막 이닝임
			max = Integer.max(max, sum+csum);
		} else {
			v[in+1][3] = true;
			map[in+1][3] = info[in+1][0];
			perm(in+1, 0, 0, cout, sum+csum);
		}
	}
}













































