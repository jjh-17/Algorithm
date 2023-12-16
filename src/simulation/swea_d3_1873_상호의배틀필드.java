package simulation;


import java.util.*;
import java.io.*;

public class swea_d3_1873_상호의배틀필드 {

	static final StringBuilder sb = new StringBuilder();
	static final char EMPTY = '.', //평지
					  WALL_ROCK='*', WALL_IRON='#', //벽 - 벽돌, 강철 
					  WATER='-'; //물
	static final int[] DI = {-1, 0, 1, 0},
					   DJ = {0, 1, 0, -1};
	static int H, W, N;  //높이, 너비, 사용자 입력 명령어 수
	static int ti, tj; //탱크의 현재 위치
	static char[][] MAP; //H*M 격자판
	static String COMMAND; //명령어
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_swea_d3_1873_상호의배틀필드.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			//입력
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken()); W = Integer.parseInt(st.nextToken());
			MAP = new char[H][W];
			for(int i=0;i<H;i++) {
				String str = br.readLine();
				for(int j=0;j<W;j++) {
					MAP[i][j] = str.charAt(j);
					if(MAP[i][j]=='^' || MAP[i][j]=='>' || MAP[i][j]=='v' || MAP[i][j]=='<') {
						ti = i; tj = j;
					}
				}
			}
			N = Integer.parseInt(br.readLine());
			COMMAND = br.readLine();
			
			
			//알고리즘
			for(int i=0;i<N;i++) {
				char com = COMMAND.charAt(i);
				tank(com);
			}
			
			
			//출력
			sb.append("#").append(t).append(" ");
			for(char [] M : MAP) {
				for(char m : M) sb.append(m);
				sb.append("\n");
			}
		}
		
		br.close();
		System.out.println(sb.toString());
		
	}
	
	//명령어 com에 따른 탱크 상태 변회
	static void tank(char com) {
		if(com=='S') {
			int dir = getDirInt(MAP[ti][tj]);
			int ni = ti + DI[dir], nj = tj + DJ[dir];
			
			while(true) {
				//폭탄이 범위를 벗어나거나 강철벽을 만나면 종료
				if(ni<0 || H<=ni || nj<0 || W<=nj || MAP[ni][nj]==WALL_IRON) break;
				
				//폭탄이 벽돌 벽을 만나면 평지로 만들고 종료
				if(MAP[ni][nj]==WALL_ROCK) {
					MAP[ni][nj] = EMPTY;
					break;
				}
				ni+=DI[dir]; nj+=DJ[dir];
				
			}
		} else { //이동 명령	
			int dir = getDirCom(com);
			int ni = ti + DI[dir], nj = tj + DJ[dir];
			
			//신규 좌표가 H*W 내이고, 평지라면 해당 위치로 이동 
			if(0<=ni&&ni<H && 0<=nj&&nj<W && MAP[ni][nj]==EMPTY) {
				MAP[ni][nj] = getDirChar(dir);
				MAP[ti][tj] = EMPTY;
				ti = ni; tj = nj;
			} else MAP[ti][tj] = getDirChar(dir); //평지가 아니기에 위치만 변경
			
		}
	}
	
	
	//탱크 문자 기준 탱크의 방향 리턴
	public static int getDirInt(char c) {
		if(c=='^') return 0;
		else if(c=='>') return 1;
		else if(c=='v') return 2;
		else if(c=='<') return 3;
		
		return -1;
	}
	
	//명령어 기준 탱크의 방향 리턴
	public static int getDirCom(char c) {
		if(c=='U') return 0;
		else if(c=='R') return 1;
		else if(c=='D') return 2;
		else if(c=='L') return 3;
		
		return -1;
	}
	
	//숫자 기준 탱크의 방향 리턴
	public static char getDirChar(int d) {
		if(d==0) return '^';
		else if(d==1) return '>';
		else if(d==2) return 'v';
		else if(d==3) return '<';
		return 'X';
	}
	
	

}
