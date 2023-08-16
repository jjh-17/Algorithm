package a0816.camp;


import java.util.*;
import java.io.*;

public class bj_g3_15684_사다리조작 {

	static final StringBuilder sb = new StringBuilder();
	static int N, M, H, CNT, 
			   LIM[], RESULT[], MAP[][];
	static boolean flag = false;
	static final List<int[]> LINE_CAND = new ArrayList<>(); //가로선 후보군 가로출 i에서 세로줄 j~j+1
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g3_15684_사다리조작.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//입력
		st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken()); M=Integer.parseInt(st.nextToken()); H=Integer.parseInt(st.nextToken());
		LIM = new int[N]; //세로줄 당 연결된 가로선 개수
		RESULT = new int[N]; //i번 세로선의 결과
		MAP = new int[M][N]; //0은 빈칸, 1은 가로선
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
			LIM[b-1]+=1; LIM[b]+=1;
			MAP[a-1][b-1]=1; MAP[a-1][b]=1;
		}
		
		//가로선 후보군 추출
		for(int i=0;i<M;i++) {
			for(int j=0;j<N;j++) {
				if(MAP[i][j]==0) LINE_CAND.add(new int[] {i, j});
			}
		}
		
		
		for(int[] M : MAP) System.out.println(Arrays.toString(M));
		System.out.println();
		System.out.println(Arrays.toString(LIM));
		System.out.println();
		setResult();
		System.out.println(Arrays.toString(RESULT));
		
		
		
		
		
		br.close();
		System.out.println(sb.toString());
	}
	
	
	//가로줄 a에서 
	static void dfs(int a, int b, int cnt) {
		
	}
	
	//Result 적용
	static void setResult() {
		for(int d=0;d<N;d++) {
			System.out.println(d + " " + Arrays.toString(RESULT));
			int i=0, j=d;
			while(true) {
				if(i==M) {
					RESULT[j]+=1;
					break;
				}
				if(MAP[i][j]==0) ++i; //가로선 연결X
				else { //가로선 연결 존재
					if(0<=j-1 && MAP[i][j-1]==1) { //좌측 이동
						while(true) {
							--j;
							if(j==0 || (j>=0 && MAP[i][j]==0)) break;
						}
					} 
//					else { //우측 이동
//						while(true) {
//							++j;
//							if(j==N-1 || (j<N-1 && MAP[i][j]==0)) break;
//						}
//					}
					++i;
				}
			}
		}
	}

}
