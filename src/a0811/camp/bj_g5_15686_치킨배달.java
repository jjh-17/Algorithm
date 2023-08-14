package a0811.camp;


//아직 못품!!!!
import java.util.*;
import java.io.*;
import java.lang.Math;

public class bj_g5_15686_치킨배달 {

	static int N, M; //도시 크기, 치킨집의 최대 개수
	static int[][] map, cArr; //도시 정보, 선정한 치킨 가게 위치 정보
	static int L_MIN; //도시의 치킨 거리 최소
	static final List<int[]> HOME = new ArrayList<>(), CHICKEN = new ArrayList<>(); //집, 치킨집 위치정보
	static boolean[] v; //각 치킨 집의 할당 여부
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_g5_15686_치킨배달.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//N: 집의 개수, M 최대 치킨집의 개수
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
		
		//치킨 거리 초기화
		L_MIN = Integer.MAX_VALUE; cArr = new int[M][2];	
		
		//도시 정보
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) HOME.add(new int[] {i, j});
				else if(map[i][j]==2) CHICKEN.add(new int[] {i, j});
			}
		}
		v = new boolean[CHICKEN.size()];
		
		
		comb(0, 0);
		System.out.println(L_MIN);
	}
	
	//N개 중 M개 조합
	public static void comb(int cnt, int start) {
		if(cnt==M) {
			setL(cnt);
			return;
		}
		
		for(int i=start;i<CHICKEN.size();i++) {
			if(v[i]) continue;
			
			//현재 치킨집을 선택하지 않음
			comb(cnt, i+1);
			
			//현재 치킨집을 선택함
			cArr[cnt] = CHICKEN.get(i);
			v[i] = true;
			comb(cnt+1, i+1);
			v[i] = false;
		}
	}
	
	//최단 치킨 거리 설정
	public static void setL(int cnt) {
		int total = 0;
		for(int[] home : HOME) {
			int temp = Integer.MAX_VALUE;
			for(int i=0;i<cnt;i++) {
				temp = Integer.min(temp, Math.abs(home[0] - cArr[i][0])+Math.abs(home[1] - cArr[i][1]));		
			}
			total += temp;
		}
		L_MIN = Integer.min(L_MIN, total);
	}

}
