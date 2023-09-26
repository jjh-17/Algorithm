package a0809.camp;

import java.util.*;
import java.io.*;

public class bj_s5_2563_색종이 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res\\input_bj_s5_2563_색종이.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		final int W = 100, B = 10; //흰색 도화지의 한 변의 길이, 검은 색종이의 한 변의 길이
		final boolean[][] map = new boolean[W][W]; //true면 색종이가 붙은 구역
		int answer = 0;
		
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			int si = Integer.parseInt(st.nextToken()), sj = Integer.parseInt(st.nextToken());
			
			//색종이 좌하단 꼭지점에서 우상단 꼭지점까지 순회
			for(int x=si;x<si+B;x++) { //
				for(int y=sj;y<sj+B;y++) {
					if(!map[y][x]) ++answer;
					map[y][x] = true;
				}
			}
		}
		System.out.println(answer);
	}

}
