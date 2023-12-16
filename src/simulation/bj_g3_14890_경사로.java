package simulation;

import java.util.*;
import java.io.*;

// 구현
public class bj_g3_14890_경사로 {

	static final StringBuilder sb = new StringBuilder();
	static final int H=1;	// 활주로 높이
	static int N, L;		// 필드 길이, 경사로 길이
	static int map[][];		// 필드 정보
	static boolean v[][];	// 필드에 활주로가 있는지 여부
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g3_14890_경사로.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
//		입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		int ans=0;
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
//		알고리즘
//		1.1 col 순회
		v = new boolean[N][N];
		for(int i=0;i<N;i++) {
//			1.2. row 순회
			boolean flag = true, flat=true;
			int j=0;
			while(j<N-1) {
				if(map[i][j]==map[i][j+1]) ++j;									// 1.3.1. 동일한 높이로 이동하는 경우
				else if(map[i][j]+1 == map[i][j+1] && checkRowUpper(i, j)) {	// 1.3.2. 높이가 1 높은 곳으로 이동하는 경우
					++j;	flat=false;	
				}
				else if(map[i][j]-1 == map[i][j+1] && checkRowLower(i, j)) {	// 1.3.3. 높이가 1 낮은 곳으로 이동하는 경우
					j+=L;	flat=false;
				}
				else {															// 1.3.4. 높이가 2 이상 차이나는 곳으로 이동하는 경우
					flag = false;
					break;
				}
			}
			
			if(flag) {
				if(flat) {
					if(N>=L) ++ans;
				} else ++ans;
			}
		}
		
//		2.1. row 순회
		v = new boolean[N][N];
		for(int j=0;j<N;j++) {
//			2.2. col 순회
			boolean flag = true, flat=true;	// 현 col에 활주로를 올바르게 설치할 수 있는지 여부
			int i=0;
			while(i<N-1) {
				if(map[i][j]==map[i+1][j]) ++i;									// 2.3.1. 동일한 높이로 이동하는 경우
				else if(map[i][j]+1 == map[i+1][j] && checkColUpper(i, j)) {	// 2.3.2. 높이가 1 높은 곳으로 이동하는 경우
					++i;	flat=false;	
				}
				else if(map[i][j]-1 == map[i+1][j] && checkColLower(i, j)) {	// 2.3.3. 높이가 1 낮은 곳으로 이동하는 경우
					i+=L;	flat=false;	
				}
				else {															// 2.3.4. 높이가 2 이상 차이나는 곳으로 이동하는 경우
					flag = false;
					break;
				}
			}
			if(flag) {
				if(flat) {
					if(N>=L) ++ans;
				} else ++ans;
			}
		}
		
//		출력
		sb.append(ans);
		System.out.println(sb.toString());
		br.close();
	}

//	가로줄에 활주로를 높이가 증가하는 방향으로 설치 가능 한지 여부 반환
	static boolean checkRowUpper(int a, int b) {
//		1. 활주로 설치 공간이 존재하는지 판단
		int start = b-L+1;
		if(start < 0) return false;
		
//		2. 활주로 설치 공간이 평평한지, 활주로가 설치되어 있지 않은지 판단
		for(int nb=start;nb<b;nb++) {
			if(map[a][nb]!=map[a][nb+1] || v[a][nb]) return false;
			v[a][nb] = true;
		}
		
		if(v[a][b]) return false;
		v[a][b]= true;
		
		return true;
	}
	
//	가로줄에 활주로를 높이가 감소하는 방향으로 설치 가능 한지 여부 반환
	static boolean checkRowLower(int a, int b) {
//		1. 활주로 설치 공간이 존재하는지 판단
		int end = b+L;
		if(end >= N) return false;
		
//		2. 활주로 설치 공간이 평평한지, 활주로가 설치되어 있지 않은지 판단
		for(int nb=b+1;nb<end;nb++) {
			if(map[a][nb]!=map[a][nb+1] || v[a][nb]) return false;
			v[a][nb] = true;
		}
		
		if(v[a][end]) return false;
		v[a][end] = true;
		
		return true;
	}

//	세로줄에 활주로를 높이가 증가하는 방향으로 설치 가능 한지 여부 반환
	static boolean checkColUpper(int a, int b) {
//		1. 활주로 설치 공간이 존재하는지 판단
		int start = a-L+1;
		if(start < 0) return false;
		
//		2. 활주로 설치 공간이 평평한지, 활주로가 설치되어 있지 않은지 판단
		for(int na=start;na<a;na++) {
			if(map[na][b]!=map[na+1][b] || v[na][b]) return false;
			v[na][b] = true;
		}
		
		if(v[a][b]) return false;
		v[a][b] = true;
		
		return true;
	}
	
//	세로줄에 활주로를 높이가 감소하는 방향으로 설치 가능 한지 여부 반환
	static boolean checkColLower(int a, int b) {
//		1. 활주로 설치 공간이 존재하는지 판단
		int end = a+L;
		if(end >= N) return false;
		
//		2. 활주로 설치 공간이 평평한지, 활주로가 설치되어 있지 않은지 판단
		for(int na=a+1;na<end;na++) {
			if(map[na][b]!=map[na+1][b] || v[na][b]) return false;
			v[na][b] = true;
		}
		
		if(v[end][b]) return false;
		v[end][b] = true;
		
		return true;
	}
}