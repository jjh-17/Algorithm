package a0830.camp;

import java.util.*;
import java.io.*;

//해결 주
public class bj_g3_1600_말이되고픈원숭이 {

	static final StringBuilder sb = new StringBuilder();
	static final int[] diHorse = {-2, -1, 1, 2, 2, 1, -1, -2},
			 		   djHorse = {1, 2, 2, 1, -1, -2, -2, -1},
			 		   diMonkey = {-1, 0, 1, 0},
			 		   djMonkey = {0, 1, 0, -1};
	static int K, W, H;
	static int[][] map;

	static class Info {
		int cntK;
		int cntM;
		public Info(int cntK, int cntM) {
			this.cntK = cntK;
			this.cntM = cntM;
		}
		@Override
		public String toString() {
			return "("+cntK+" "+cntM+")";
		}
	}
	static Info[][] dp;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g3_1600_말이되고픈원숭이.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//입력
		K = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][W]; dp = new Info[H][W];
		for(int i=0;i<H;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<W;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = new Info(0, 0);
			}
		}
		
		//DP 순회		
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				//현 좌표에 장애물 위치 시 continue
				if(map[i][j]==1) continue;
				
				//현 좌표가 (0, 0)이 아니나, 들리지 않은 곳
				if((i!=0 || j!=0) && dp[i][j].cntK==0 && dp[i][j].cntM==0) continue;
				
				
				//말처럼 이동할 횟수가 남아 있다면, 말처럼 이동
				if(dp[i][j].cntK < K) {
					for(int d=0;d<8;d++) {
						//신규 좌표
						int ni = i+diHorse[d];
						int nj = j+djHorse[d];
						
						//신규 좌표가 (0, 0)이면 continue
						if(ni==0 && nj==0) continue;
						
						//신규 좌표가 필드를 벗어나면 continue
						if(ni<0 || ni>=H || nj<0 || nj>=W) continue;
						
						//신규 좌표에 장애물이 있는 경우 continue
						if(map[ni][nj]==1) continue;
						
						//신규 좌표에 최초 입성 시 새로운 Info 저장
						if(dp[ni][nj].cntK==0 && dp[ni][nj].cntM==0) {
							dp[ni][nj].cntK = dp[i][j].cntK+1;
							dp[ni][nj].cntM = dp[i][j].cntM;
							continue;
						}
						
						//신규 좌표의 이동 횟수보다 새로운 이동횟수가 더 작은 경우 새로운 Info 저장
						if(dp[ni][nj].cntK+dp[ni][nj].cntM > (dp[i][j].cntK+1)+dp[i][j].cntM) {
							dp[ni][nj].cntK = dp[i][j].cntK+1;
							dp[ni][nj].cntM = dp[i][j].cntM;
							continue;
						}
						
						//기존 Info와 새로운 Info의 횟수 합이 동일한 경우 ==> 새로운 Info의 cntK가 더 작을 경우 그것으로 변경
						if(dp[ni][nj].cntK+dp[ni][nj].cntM == (dp[i][j].cntK+1)+dp[i][j].cntM) {
							if(dp[ni][nj].cntK > dp[i][j].cntK+1) {
								dp[ni][nj].cntK = dp[i][j].cntK+1;
								dp[ni][nj].cntM = dp[i][j].cntM;
							}
						}
					}
				}
				
				//일반 원숭이 처럼 이동
				for(int d=0;d<4;d++) {
					int ni = i+diMonkey[d];
					int nj = j+djMonkey[d];
					
					//신규 좌표가 (0, 0)이면 continue
					if(ni==0 && nj==0) continue;
					
					//신규 좌표가 필드를 벗어나면 continue
					if(ni<0 || ni>=H || nj<0 || nj>=W) continue;
					
					//신규 좌표에 장애물이 있는 경우 continue
					if(map[ni][nj]==1) continue;
					
					//신규 좌표에 최초 입성 시 새로운 Info 저장
					if(dp[ni][nj].cntK==0 && dp[ni][nj].cntM==0) {
						dp[ni][nj].cntK = dp[i][j].cntK;
						dp[ni][nj].cntM = dp[i][j].cntM+1;
						continue;
					}
					
					//신규 좌표의 이동 횟수보다 새로운 이동횟수가 더 작은 경우 새로운 Info 저장
					if(dp[ni][nj].cntK+dp[ni][nj].cntM > dp[i][j].cntK+(dp[i][j].cntM+1)) {
						dp[ni][nj].cntK = dp[i][j].cntK;
						dp[ni][nj].cntM = dp[i][j].cntM+1;
						continue;
					}
					
					//기존 Info와 새로운 Info의 횟수 합이 동일한 경우 ==> 새로운 Info의 cntK가 더 작을 경우 그것으로 변경
					if(dp[ni][nj].cntK+dp[ni][nj].cntM == dp[i][j].cntK+(dp[i][j].cntM+1)) {
						if(dp[ni][nj].cntK > dp[i][j].cntK+1) {
							dp[ni][nj].cntK = dp[i][j].cntK+1;
							dp[ni][nj].cntM = dp[i][j].cntM;
						}
					}
				}
				
			}
		}
		
		
//		for(Info[] d : dp) System.out.println(Arrays.toString(d));
		
		//출력
		if(H==1 && W==1) sb.append(0).append("\n");
		else sb.append(dp[H-1][W-1].cntK==0&&dp[H-1][W-1].cntM==0 ? -1 : dp[H-1][W-1].cntK+dp[H-1][W-1].cntM);
		System.out.println(sb.toString());
		br.close();
	}

}
