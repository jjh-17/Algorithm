package a0818.camp;

import java.util.*;
import java.io.*;
import java.lang.Math;

//해결 중
public class bj_g3_17135_캐슬디펜스 {

	static final List<int[]> monster_origin = new ArrayList<>();
	static List<int[]> monster=new ArrayList<>(); //몬스터 위치 저장
	static int N, M, D;
	static int[][] map; //격자판 상태 원본, 변경 될 것
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
		map = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) monster_origin.add(new int[] {i, j});
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
			game();
//			System.out.println();
			return;
		}
		
		for(int i=start;i<M;i++) {
			sniper[cnt]=i;
			comb(cnt+1, i+1);
		}
	}
	
	static void game() {
		int sum=0;
		
		//거리 순 -> 가장 좌측 적 후보군
		while(!monster.isEmpty()) {
			int idx1=-1, idx2=-1, idx3=-1;
			
//			for(int[] m : monster) System.out.print(Arrays.toString(m));
//			System.out.println(sum);
			
			
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
//			System.out.print("SHOOT : ");
			if(idx1 != -1) {
//				System.out.print(Arrays.toString(monster.get(idx1)) + " ");
				monster.set(idx1, null);
				
			}
			if(idx2 != -1) {
//				System.out.print(Arrays.toString(monster.get(idx2)) + " ");
				monster.set(idx2, null);
			}
			if(idx3 != -1) {
//				System.out.print(Arrays.toString(monster.get(idx3)) + " ");
				monster.set(idx3, null);
			}
//			System.out.println();
			
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
