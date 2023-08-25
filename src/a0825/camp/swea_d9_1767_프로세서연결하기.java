package a0825.camp;

import java.util.*;
import java.io.*;

/*
 * 가능한 많은 코어를 연결해야 하며, 그 중 가장 적은 전선을 사용하는 경우의 수 구하기
 * 		==> 코어 개수를 최대로 설정한 뒤, 이를 하나씩 줄여나가며 구하면 더 빨리 구할 수 있다!!!!
 */
public class swea_d9_1767_프로세서연결하기 {

	static final StringBuilder sb = new StringBuilder(); //출력
	static final List<int[]> CORE_IN = new ArrayList<>(); //내부 코어 위치. Last에 위치할 수록 코어 번호가 높음
	static final int[] DI = {-1, 0, 1, 0}, DJ= {0, 1, 0, -1}; //4방 탐색
	
	static int N, L, WIRE_MIN; //판 크기, 내부 코어 개수, 이때 와이어의 최소 개수
	static int[][] MAP; //N*N 판(0: 빈칸, 1: 프로세서, 2:전선)
	static boolean[] CORE_IN_V; //내부 코어의 사용 여부
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d9_1767_프로세서연결하기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			N = Integer.parseInt(br.readLine()); //판의 크기 입력
			
			//초기화
			CORE_IN.clear(); WIRE_MIN = Integer.MAX_VALUE; MAP = new int[N][N];
			
			//정보 입력
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					MAP[i][j] = Integer.parseInt(st.nextToken()); //map에 각 칸의 위치 정보 입력
					
					//현재 위치에 코어가 있으며, 가장자리가 아닌 곳에 위치함
					if(MAP[i][j]==1 && i!=0 && i!=N-1 && j!=0 && j!=N-1) CORE_IN.add(new int[] {i, j});
				}
			}
			L = CORE_IN.size();
			CORE_IN_V = new boolean[L];

			//R개 코어 활성화 시의 WIRE_MIN 구하기
			for(int r=L;r>=0;r--) {
				combR(0, 0, r);
				if(WIRE_MIN<Integer.MAX_VALUE) break;
			}
			
			sb.append("#").append(t).append(" ").append(WIRE_MIN).append("\n");
		}
		br.close();
		System.out.println(sb.toString());
	}
	
	
	//CORE_IN 중 R개를 뽑는 조합
	public static void combR(int cnt, int start, int R) {
		if(cnt==R) {
			dfs(0, 0);
			return;
		}
		
		for(int i=start;i<L;i++) {
			CORE_IN_V[i] = true;
			combR(cnt+1, i+1, R);
			CORE_IN_V[i] = false;
		}
	}
	
	//현재 사용 와아어 수, 시작 인덱스
	public static void dfs(int wire_cnt, int idx) {
		//CORE_IN 모두를 순회함
		if(idx==L) {
			WIRE_MIN = Integer.min(WIRE_MIN, wire_cnt);
			return;
		}
		
		//사용되지 않는 코어 번호가 들어온 경우 다음 코어 번호로 다시 실행
		if(!CORE_IN_V[idx]) {
			dfs(wire_cnt, idx+1);
			return;
		}
		
		//4방 탐색
		for(int d=0;d<4;d++) {
			if(checkLine(CORE_IN.get(idx), d)) {
				dfs(wire_cnt + setWire(CORE_IN.get(idx), d, true), idx+1);
				setWire(CORE_IN.get(idx), d, false);
			}
		}
	}
	
	
	//좌표(i, j)에서 방향 d로 전선 설치/제거(true/false), 설치한 전선 개수 반환
	public static int setWire(int[] ij, int d, boolean flag) {
		int ni = ij[0], nj = ij[1], cnt=0;
		do {
			ni+=DI[d]; nj+=DJ[d]; ++cnt;
			MAP[ni][nj] = flag ? 2: 0;
		} while(0<ni&&ni<N-1 && 0<nj&&nj<N-1);
		
		return cnt;
	}
	
	//좌표 (i, j)에서 방향 d로 와이어 설치 가능 여부  확인
	public static boolean checkLine(int[] ij, int d) {
		int ni = ij[0], nj = ij[1];
		do {
			ni+=DI[d]; nj+=DJ[d];
			if(MAP[ni][nj] != 0) return false;
		} while(0<ni&&ni<N-1 && 0<nj&&nj<N-1);
		
		return true;
	}
	
	public static void showInfo() {
		for(int[] m : MAP) System.out.println(Arrays.toString(m));
		System.out.println();
	}
}
