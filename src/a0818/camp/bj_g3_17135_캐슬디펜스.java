package a0818.camp;

import java.util.*;
import java.io.*;
import java.lang.Math;

//해결 중
public class bj_g3_17135_캐슬디펜스 {

	static final List<int[]> monster_origin = new ArrayList<>();
	static List<int[]> monster=new ArrayList<>(); //몬스터 위치 저장
	static int N, M, D;
	static int[][] map_origin, map; //격자판 상태 원본, 변경 될 것
	static int[] sniper; //스나이퍼의 위치
	static int MAX; //잡은 몬스터 최댓값
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g3_17135_캐슬디펜스.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		//M, M, D 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		sniper = new int[3]; MAX = Integer.MIN_VALUE;
		
		//N*M 격자판 입력
		map_origin = new int[N][M]; map = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map_origin[i][j] = Integer.parseInt(st.nextToken());
				if(map_origin[i][j]==1) monster_origin.add(new int[] {i, j});
			}
		}
		
		comb(0, 0);
		System.out.println(MAX);
	}
	
	//궁수 위치 조합
	static void comb(int cnt, int start) {
		if(cnt==3) {
			//몬스터 초기 상태 복사
			for(int m[] : monster_origin) monster.add(new int[] {m[0], m[1]});
//			game();
			game2();
//			System.out.println();
			return;
		}
		
		for(int i=start;i<M;i++) {
			sniper[cnt]=i;
			comb(cnt+1, i+1);
		}
	}

	//궁수가 올라가는 방식 ==> 메모리 사용량 감소, 시간 증가
	static void game2() {
		int sum=0;
		for(int i=0;i<N;i++) { //map 초기화
			for(int j=0;j<M;j++) map[i][j] = map_origin[i][j];
		}
		
		for(int si=N;si>0;si--) { //궁수의 가로줄 위치 N~1
			//3명의 스나이퍼의 타깃 정보 : (타깃 i, 타깃, j, 타깃과의 거리)
			int[][] targets = new int[3][3]; 
			
			//스나이퍼의 타겟 설정
			for(int j=0;j<3;j++) { //궁수의 세로출 뒤치
				int sj = sniper[j];
				
				//아래 줄 몬스터 부터
				for(int mi=si-1;mi>=0;mi--) {
					for(int mj=0;mj<M;mj++) {
						//몬스터가 위치하며, 스나이퍼 거리 내일 때
						int d = getRange(si, sj, mi, mj);
						if(map[mi][mj]==1 && d<=D) {
							//아직 타깃이 정해지지 않았음 OR 타깃보다 현재 몬스터와의 거리가 짧음 || 거리는 동일하나, 현재 몬스터가 더 좌측에 위치함
							if(targets[j][2]==0 || targets[j][2]>d || (targets[j][2]==d && targets[j][1]>mj) )
								targets[j] = new int[] {mi, mj, d};
						}
					}
				}
			}
			
			//for(int[] t : targets) System.out.print(Arrays.toString(t)+" ");
			//System.out.println();
			
			//중복 몬스터 제거
			List<int[]> result = new ArrayList<>();
			for(int i=0;i<3;i++) {
				boolean flag = true;
				if(targets[i][2]==0) continue;
				for(int[] r : result) {
					if(r[0]==targets[i][0] && r[1]==targets[i][1]) {
						flag = false;
						break;
					}
				}
				if(flag) result.add(targets[i]);
			}
			
//			for(int[] r : result) System.out.print(Arrays.toString(r) + " ");
//			System.out.println();
			
			//필드에서 몬스터 제거
			for(int[] r : result) map[r[0]][r[1]]=0;
			
			sum+=result.size();
		}
		
		MAX = Integer.max(MAX, sum);
	}
	
	
	//몬스터가 내려오는 방식
	static void game() {
		int sum=0;
		
		//거리 순 -> 가장 좌측 적 후보군
		while(!monster.isEmpty()) {
			int idx1=-1, idx2=-1, idx3=-1;
					
			//사격 시 죽을 몬스터 후보군 설정
			for(int i=0;i<monster.size();i++) {
				int[] cor = monster.get(i);
				int d1, d2;
				
				d1 = getRange(N, sniper[0], cor[0], cor[1]); //현재 스나이퍼와 몬스터와의 거리
				if(d1<=D) { //스나이퍼와 몬스터 사이의 거리가 D이하
					if(idx1==-1) idx1=i; //타겟이 정해지지 않음
					else { //타겟이 정해짐
						d2 = getRange(N, sniper[0], monster.get(idx1)[0], monster.get(idx1)[1]);
						if(d1<d2) idx1=i; //현재 몬스터와의 거리가 기존 타겟과의 거리보다 가까우면 타겟 변경
						else if(d1==d2) //현재 몬스터와의 거리와 기존 타겟과의 거리가 동일한 경우 더 좌측에 있는 것을 타겟으로 설정
							idx1 = cor[1]<monster.get(idx1)[1] ? i : idx1;
					}
				}
				d1 = getRange(N, sniper[1], cor[0], cor[1]); //현재 스나이퍼와 몬스터와의 거리
				if(d1<=D) { //스나이퍼와 몬스터 사이의 거리가 D이하
					if(idx2==-1) idx2=i; //타겟이 정해지지 않음
					else { //타겟이 정해짐
						d2 = getRange(N, sniper[1], monster.get(idx2)[0], monster.get(idx2)[1]);
						if(d1<d2) idx2=i; //현재 몬스터와의 거리가 기존 타겟과의 거리보다 가까우면 타겟 변경
						else if(d1==d2) //현재 몬스터와의 거리와 기존 타겟과의 거리가 동일한 경우 더 좌측에 있는 것을 타겟으로 설정
							idx2 = cor[1]<monster.get(idx2)[1] ? i : idx2;
					}
				}
				d1 = getRange(N, sniper[2], cor[0], cor[1]); //현재 스나이퍼와 몬스터와의 거리
				if(d1<=D) { //스나이퍼와 몬스터 사이의 거리가 D이하
					if(idx3==-1) idx3=i; //타겟이 정해지지 않음
					else { //타겟이 정해짐
						d2 = getRange(N, sniper[2], monster.get(idx3)[0], monster.get(idx3)[1]);
						if(d1<d2) idx3=i; //현재 몬스터와의 거리가 기존 타겟과의 거리보다 가까우면 타겟 변경
						else if(d1==d2) //현재 몬스터와의 거리와 기존 타겟과의 거리가 동일한 경우 더 좌측에 있는 것을 타겟으로 설정
							idx3 = cor[1]<monster.get(idx3)[1] ? i : idx3;
					}
				}
			}
			
			//죽은 몬스터 제외
			if(idx1 != -1) monster.set(idx1, null);
			if(idx2 != -1) monster.set(idx2, null);
			if(idx3 != -1) monster.set(idx3, null);
			
			//몬스터 이동
			ArrayList<int[]> monsterNew = new ArrayList<>();
			for(int[] cor : monster) {
				if(cor==null || cor[0]+1==N) {
					if(cor==null) ++sum;
					continue;
				}
				monsterNew.add(new int[] {cor[0]+1, cor[1]});
			}
			monster = monsterNew;
		}
		
		MAX = Integer.max(MAX, sum);
	}
	
	//궁수(si, sj), 몬스터(mi, mj)
	static int getRange(int si, int sj, int mi, int mj) {
		return Math.abs(si-mi) + Math.abs(sj-mj);
	}

}
