package a0811.camp;

//아직 못품!!!
import java.util.*;
import java.io.*;

/*
 * N*N 판 존재
 * 1개 Cell에는 1개의 코어 혹은 전선 존재 가능
 * 판 가장자리에서 파워 제공 ==> 가장 자리에 위치한 코어는 전선 연결 없이 파워 공급 받음
 * 전선은 직선으로만 연결 가능, 교차 불가능
 * 
 * 최대한 많은 코어에 전원을 연결하였을 때, 전선 길이의 합 출력
 * 		==> 여러 방법이 있을 경우, 전선 길이 최소 출력
 */
public class swea_s9_1767_프로세서연결하기 {
	
	static int N, LIMIT; //판 한변의 길이, 가장자리를 제외한 프로세서의 개수
	static int P_MAX, WIRE_MIN; //활성화 프로세서 최대 개수, 이때 전선 수의 최솟값
	static int[][] map;
	static final int[] di = {-1, 0, 1, 0}, dj = {0, 1, 0, -1}; //4방향 이동
	static final List<int[]> list = new ArrayList<>(); //프로세서 위치 정보(가장자리 제외)
	static final StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d9_1767_프로세서연결하기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			N = Integer.parseInt(br.readLine());
			P_MAX=0; WIRE_MIN=Integer.MAX_VALUE; list.clear();
			
			map = new int[N][N];
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						if(i==0 || i==N-1 || j==0 || j==N-1) P_MAX++; //가장자리 프로세서 ==> 고려대상이 아님
						else list.add(new int[] {i, j});
					}
					
				}
			}
			LIMIT = list.size();
			subS(0, P_MAX, 0);
			
			sb.append("#").append(t).append(" ")
						.append(WIRE_MIN==Integer.MAX_VALUE ? 0 : WIRE_MIN).append("\n");
		}
		br.close();
		System.out.println(sb.toString());
	}
	
	//부분 집합
	public static void subS(int start, int cnt, int wire) {//고려할 프로세서 인덱스, 현재 활성화 프로세서 개수, 현재 전선 수
		if(start==LIMIT) {
			if(P_MAX <= cnt) {
				P_MAX = cnt;
				if(P_MAX==cnt) WIRE_MIN = Integer.min(WIRE_MIN, wire);
				else WIRE_MIN = wire;
				showInfo();
			}
			return;
		}
		
		for(int i=start;i<LIMIT;i++) {
			for(int d=0;d<4;d++) { //4방 탐색
				if(checkLine(list.get(i), d)) { //현 방향으로 전선 뻗기 가능
					subS(start+1, cnt+1, wire+setLine(list.get(i), d, 2));
					setLine(list.get(i), d, 0);
				}
			}
			
			//4방 탐색을 했지만 전선 연결 불가 시
			subS(start+1, cnt, wire);
		}
		
	}
	
	//전선을 연결할 때, 다른 전선 혹은 프로세서를 거치는지 확인(현재 i 좌표, 현재 j 좌표, 방향)
	public static boolean checkLine(int[] ij, int d) {
		int cnt=1;	
		
		while(true) {
			int ni = ij[0] + di[d]*cnt;
			int nj = ij[1] + dj[d]*cnt;
			
			//다른 전선/프로세서 침범 함
			if(0<=ni&&ni<N && 0<=nj&&nj<N && map[ni][nj]!=0) return false;
			
			//종료 조건
			if(ni==0||ni==N-1 && nj==0||nj==N-1) break;
			
			++cnt;
		}
		
		return true;
	}
	
	//flag==1: 전선 연결, flag==0: 전선 제거
	public static int setLine(int[] ij, int d, int flag) {
		int cnt=1;
		while(true) {
			int ni = ij[0] + di[d]*cnt;
			int nj = ij[1] + dj[d]*cnt;
			
			map[ni][nj] = flag;
			if(ni==0||ni==N-1 && nj==0||nj==N-1) break;
			++cnt;
		}
		
		return cnt;
	}
	
	public static void showInfo() {
		for(int[] m : map) System.out.println(Arrays.toString(m));
//		for(int[] l : list) System.out.print(Arrays.toString(l) + "\t");
		System.out.println("\n");
	}
}
